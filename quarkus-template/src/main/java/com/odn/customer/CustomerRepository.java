package com.odn.customer;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<CustomerEntity, Integer> {
}