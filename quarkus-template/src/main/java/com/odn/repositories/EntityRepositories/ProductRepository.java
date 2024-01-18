package com.odn.repositories.EntityRepositories;

import com.odn.entities.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProductRepository  implements PanacheRepositoryBase<ProductEntity, Long> {
    public ProductEntity findOneBySellerIdAndSku(Long sellerId, String sku) {
        sku = sku.toUpperCase();
        Map<String, Object> params = new HashMap<>();
        String query = "sellerId = :sellerId and (sku = :sku or partner_sku = :sku)";
        params.put("sellerId", sellerId);
        params.put("sku", sku);

        List<ProductEntity> records = find(query, params).page(0, 1).list();

        return records.isEmpty() ? null : records.get(0);
    }

    public long countBySellerIdAndIds(Long sellerId, List<Long> ids) {
        Map<String, Object> params = new HashMap<>();
        String query = "sellerId = :sellerId and id in (:ids)";
        params.put("sellerId", sellerId);
        params.put("ids", ids);

        return count(query, params);
    }
}
