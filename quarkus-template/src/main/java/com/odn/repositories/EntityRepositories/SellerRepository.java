package com.odn.repositories.EntityRepositories;

import com.odn.entities.SellerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SellerRepository implements PanacheRepositoryBase<SellerEntity, Long> {
}
