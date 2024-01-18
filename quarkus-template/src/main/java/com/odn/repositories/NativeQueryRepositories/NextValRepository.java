package com.odn.repositories.NativeQueryRepositories;

import com.odn.databaseQueryModels.NextValQueryModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class NextValRepository {
    @Inject
    EntityManager entityManager;

    public Long inboundRequest() {
        List<NextValQueryModel> result = entityManager.createNativeQuery("select row_number() over() as id, nextval('inbound_request_id_seq') as next_val", NextValQueryModel.class).getResultList();
        return result.isEmpty() ? null : result.get(0).getNextVal();
    }
}
