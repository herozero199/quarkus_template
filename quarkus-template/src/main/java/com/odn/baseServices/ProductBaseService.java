package com.odn.baseServices;

import com.odn.entities.CategoryEntity;
import com.odn.entities.ProductEntity;
import com.odn.repositories.EntityRepositories.ProductRepository;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import com.odn.requests.productControllerRequests.OpsEditAProductSizeRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class ProductBaseService {
    private final ProductRepository repository;

    public Double roundSize(Double input) {
        if (input == null) {
            return null;
        }

        DecimalFormat format = new DecimalFormat("#.#");
        return Double.parseDouble(format.format(input));
    }

    public Double roundWeight(Double input) {
        if (input == null) {
            return null;
        }

        DecimalFormat format = new DecimalFormat("#");
        return Double.parseDouble(format.format(input));
    }

    public ProductEntity findById(@NonNull Long recordId) {
        return repository.findById(recordId);
    }

    public ProductEntity findOneBySellerIdAndSku(Long sellerId, String sku) {
        return  repository.findOneBySellerIdAndSku(sellerId, sku);
    }

    public ProductEntity opsEditAProductSize(Long updaterUserId, ProductEntity record, OpsEditAProductSizeRequest request) {
        record.setLength(roundSize(request.getLength()));
        record.setWidth(roundSize(request.getWidth()));
        record.setHeight(roundSize(request.getHeight()));
        record.setWeight(roundWeight(request.getWeight()));
        record.setUpdatedAt(ZonedDateTime.now());
        record.setUpdatedBy(updaterUserId);

        repository.persist(record);
        return record;
    }

    public long countBySellerIdAndIds(Long sellerId, List<Long> ids) {
        return  repository.countBySellerIdAndIds(sellerId, ids);
    }
}
