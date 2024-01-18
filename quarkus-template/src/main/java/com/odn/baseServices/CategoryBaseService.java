package com.odn.baseServices;

import com.odn.common.PaginationSelect;
import com.odn.entities.CategoryEntity;
import com.odn.repositories.EntityRepositories.CategoryRepository;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CategoryBaseService {
    private final CategoryRepository repository;

    private String standardizeCode(String code) {
        return code == null ? null : code.trim();
    }

    public CategoryEntity findById(@NonNull Long recordId) {
        return repository.findById(recordId);
    }

    public CategoryEntity sellerCreateNewCategory(SellerCreateNewCategoryRequest request, Long sellerId) {
        CategoryEntity newRecord = new CategoryEntity();
        newRecord.setCode(standardizeCode(request.getCode()));
        newRecord.setName(request.getName());
        newRecord.setSellerId(sellerId);

        repository.persist(newRecord);
        return newRecord;
    }

    public CategoryEntity sellerEditACategory(Long updaterUserId, CategoryEntity record, SellerCreateNewCategoryRequest request) {
        record.setCode(standardizeCode(request.getCode()));
        record.setName(request.getName());

        repository.persist(record);
        return record;
    }

    public PaginationSelect<List<CategoryEntity>> findPaginated(int page, int size, Long sellerId, String filter) {
        return repository.findPaginated(page, size, sellerId, filter);
    }

    public long countByCode(Long recrodId, Long sellerId, String code) {
        return repository.countByCode(recrodId, sellerId, standardizeCode(code));
    }
}
