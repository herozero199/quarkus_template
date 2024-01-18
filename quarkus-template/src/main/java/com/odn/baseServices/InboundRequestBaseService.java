package com.odn.baseServices;

import com.odn.common.Constants;
import com.odn.entities.InboundRequestEntity;
import com.odn.entities.SellerEntity;
import com.odn.repositories.EntityRepositories.InboundRequestRepository;
import com.odn.repositories.NativeQueryRepositories.NextValRepository;
import com.odn.requests.inboundControllerRequests.SellerCreateNewInboundRequestRequest;
import com.odn.utils.CodeGenerator;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.odn.common.ColumnValue.INBOUND_REQUEST_STATUS_NEW;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class InboundRequestBaseService {
    private InboundRequestRepository repository;
    private NextValRepository nextValRepository;

    private String standardizePartnerIrCode(String code) {
        return code == null ? null : code.trim();
    }

    public Long getNextVal() {
        return nextValRepository.inboundRequest();
    }

    public long countByPartnerIrCode(Long sellerId, String partnerIrCode) {
        return repository.countByPartnerIrCode(sellerId, standardizePartnerIrCode(partnerIrCode));
    }

    public InboundRequestEntity sellerCreateNewInboundRequest(Long creatorUserId, SellerCreateNewInboundRequestRequest request, SellerEntity seller, ZonedDateTime estimateArrivalDate, ZonedDateTime arrivalDeadline) {
        Long newId = getNextVal();
        String irCode =  "IR" + seller.getCode() + CodeGenerator.genUniqueCodeFromNumber(newId);
        String partnerIrCode = standardizePartnerIrCode(request.getPartnerIrCode());
        partnerIrCode = StringUtils.isBlank(partnerIrCode) ? irCode : partnerIrCode;

        InboundRequestEntity newRecord = new InboundRequestEntity();
        newRecord.setId(newId);
        newRecord.setWarehouseId(request.getWarehouseId());
        newRecord.setSellerId(seller.getId());
        newRecord.setIrCode(irCode);
        newRecord.setPartnerIrCode(partnerIrCode);
        newRecord.setEstimateArrivalDate(estimateArrivalDate);
        newRecord.setArrivalDeadline(arrivalDeadline);
        newRecord.setSupplierName(request.getSupplierName());
        newRecord.setIrType(request.getIrType());
        newRecord.setConditionType(request.getConditionType());
        newRecord.setReferenceCode(request.getReferenceCode());
        newRecord.setPartnerName(request.getPartnerName());
        newRecord.setPartnerReturnAddress(request.getPartnerReturnAddress());
        newRecord.setContactName(request.getContactName());
        newRecord.setContactPhone(request.getContactPhone());
        newRecord.setContactEmail(request.getContactEmail());
        newRecord.setContactAddress(request.getContactAddress());
        newRecord.setDriverName(request.getDriverName());
        newRecord.setVehicleNumber(request.getVehicleNumber());
        newRecord.setContainerNumber(request.getContainerNumber());
        newRecord.setNote(request.getNote());
        newRecord.setCreatedAt(ZonedDateTime.now());
        newRecord.setCreatedBy(creatorUserId);
        newRecord.setStatus(INBOUND_REQUEST_STATUS_NEW.getValue());
        newRecord.setSaleChannelId(Constants.FixedIds.SALE_CHANNEL_PARTNER_PORTAL_ID);

        repository.persist(newRecord);
        return newRecord;
    }
}
