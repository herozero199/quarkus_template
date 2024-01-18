package com.odn.repositories.EntityRepositories;

import com.odn.entities.InboundRequestEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class InboundRequestRepository implements PanacheRepositoryBase<InboundRequestEntity, Long> {
    public long countByPartnerIrCode(Long sellerId, String partnerIrCode) {
        Map<String, Object> params = new HashMap<>();
        String query = "sellerId = :sellerId and partnerIrCode = :partnerIrCode";
        params.put("sellerId", sellerId);
        params.put("partnerIrCode", partnerIrCode);

        return count(query, params);
    }
}
