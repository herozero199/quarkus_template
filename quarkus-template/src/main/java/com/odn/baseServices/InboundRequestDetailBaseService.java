package com.odn.baseServices;

import com.odn.entities.CategoryEntity;
import com.odn.entities.InboundRequestDetailEntity;
import com.odn.repositories.EntityRepositories.InboundRequestDetailRepository;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import com.odn.requests.inboundControllerRequests.SellerCreateNewInboundRequestRequestProduct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class InboundRequestDetailBaseService {
    private final InboundRequestDetailRepository repository;

    public List<InboundRequestDetailEntity> sellerCreateNewInboundRequest(List<SellerCreateNewInboundRequestRequestProduct> request, Long inboundRequestId) {
        List<InboundRequestDetailEntity> newRecords = new ArrayList<>();

        request.forEach(element -> {
            InboundRequestDetailEntity newRecord = new InboundRequestDetailEntity();
            newRecord.setInboundRequestId(inboundRequestId);
            newRecord.setProductId(element.getProductId());
            newRecord.setQuantity(element.getQuantity());

            newRecords.add(newRecord);
        });


        repository.persist(newRecords);
        return newRecords;
    }
}
