package com.odn.services;

import com.odn.baseServices.CategoryBaseService;
import com.odn.common.Constants;
import com.odn.common.PaginationResult;
import com.odn.common.PaginationSelect;
import com.odn.entities.CategoryEntity;
import com.odn.exception.ServiceException;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryBaseService categoryBaseService;

    @Transactional
    public CategoryEntity sellerGetACategory(Long sellerId, Long recordId) {
        CategoryEntity category = categoryBaseService.findById(recordId);
        if (category == null || category.getSellerId() != sellerId) {
            throw new ServiceException(Constants.ErrorMessage.Category.ERR_001);
        }

        return category;
    }

    @Transactional
    public Long sellerCreateNewCategory(Long sellerId, SellerCreateNewCategoryRequest request) {
        long count = categoryBaseService.countByCode(null, sellerId, request.getCode());

        if (count > 0) {
            throw new ServiceException(Constants.ErrorMessage.Category.ERR_002);
        }

        CategoryEntity newRecord =  categoryBaseService.sellerCreateNewCategory(request, sellerId);
        return newRecord.getId();
    }

    @Transactional
    public void sellerEditACategory(Long userId, Long sellerId, Long recordId, SellerCreateNewCategoryRequest request) {
        CategoryEntity category = categoryBaseService.findById(recordId);
        if (category == null || !Objects.equals(category.getSellerId(), sellerId)) {
            throw new ServiceException(Constants.ErrorMessage.Category.ERR_001);
        }

        if (!category.getCode().equals(request.getCode())) {
            long count = categoryBaseService.countByCode(recordId, sellerId, request.getCode());

            if (count > 0) {
                throw new ServiceException(Constants.ErrorMessage.Category.ERR_002);
            }
        }

        categoryBaseService.sellerEditACategory(userId, category, request);
    }

    public PaginationResult<List<CategoryEntity>> sellerGetListCategoriesPagination(Long sellerId, int page, int size, String filter) {
        PaginationSelect<List<CategoryEntity>> categories = categoryBaseService.findPaginated(page, size, sellerId, filter);

        return new PaginationResult<>(categories.getRecordCount(), page, size, categories.getRecords());
    }
}
