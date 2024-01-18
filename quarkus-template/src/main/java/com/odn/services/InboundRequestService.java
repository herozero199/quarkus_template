package com.odn.services;

import com.odn.baseServices.InboundRequestBaseService;
import com.odn.baseServices.InboundRequestDetailBaseService;
import com.odn.baseServices.ProductBaseService;
import com.odn.baseServices.SellerBaseService;
import com.odn.common.Constants;
import com.odn.entities.InboundRequestEntity;
import com.odn.entities.ProductEntity;
import com.odn.entities.SellerEntity;
import com.odn.exception.ServiceException;
import com.odn.requests.inboundControllerRequests.SellerCreateNewInboundRequestRequest;
import com.odn.utils.DateUtils;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.mutable.MutableBoolean;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class InboundRequestService {
    private InboundRequestBaseService inboundRequestBaseService;
    private InboundRequestDetailBaseService inboundRequestDetailBaseService;
    private ProductBaseService productBaseService;
    private SellerBaseService sellerBaseService;

    @Transactional
    public Long sellerCreateNewInboundRequest(Long creatorUserId, Long sellerId, SellerCreateNewInboundRequestRequest request) {
        if (StringUtils.isNotBlank(request.getPartnerIrCode())) {
            long count = inboundRequestBaseService.countByPartnerIrCode(sellerId, request.getPartnerIrCode());

            if (count > 0) {
                throw new ServiceException(Constants.ErrorMessage.InboundRequest.ERR_001);
            }
        }

        MutableBoolean check = new MutableBoolean(true);
        ZonedDateTime estimateArrivalDate = DateUtils.stringDate02_GmtPlus7_ToTime(request.getEstimateArrivalDate(), check);
        if (check.isFalse()) {
            throw new ServiceException(Constants.ErrorMessage.InboundRequest.ERR_002);
        }

        ZonedDateTime arrivalDeadline = DateUtils.stringDate02_GmtPlus7_ToTime(request.getArrivalDeadline(), check);
        if (check.isFalse()) {
            throw new ServiceException(Constants.ErrorMessage.InboundRequest.ERR_003);
        }

        SellerEntity seller = sellerBaseService.findById(sellerId);
        if (seller == null) {
            throw new ServiceException(Constants.ErrorMessage.Common.ERR_001);
        }

        List<Long> productIds = new ArrayList<>();
        request.getProducts().forEach(element -> {
            productIds.add(element.getProductId());
        });

        long productCount = productBaseService.countBySellerIdAndIds(sellerId, productIds);
        if (productCount != request.getProducts().size()) {
            throw new ServiceException(Constants.ErrorMessage.InboundRequest.ERR_004);
        }

        InboundRequestEntity newInboundRequest = inboundRequestBaseService.sellerCreateNewInboundRequest(creatorUserId, request, seller, estimateArrivalDate, arrivalDeadline);
        inboundRequestDetailBaseService.sellerCreateNewInboundRequest(request.getProducts(), newInboundRequest.getId());

        return newInboundRequest.getId();
    }
}
