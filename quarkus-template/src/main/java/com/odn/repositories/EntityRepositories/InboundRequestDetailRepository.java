package com.odn.repositories.EntityRepositories;

import com.odn.entities.InboundRequestDetailEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InboundRequestDetailRepository implements PanacheRepositoryBase<InboundRequestDetailEntity, Long> {
}
