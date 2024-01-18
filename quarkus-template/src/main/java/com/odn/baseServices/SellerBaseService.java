package com.odn.baseServices;

import com.odn.entities.SellerEntity;
import com.odn.repositories.EntityRepositories.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class SellerBaseService {
    private final SellerRepository repository;

    public SellerEntity findById(@NonNull Long recordId) {
        return repository.findById(recordId);
    }
}
