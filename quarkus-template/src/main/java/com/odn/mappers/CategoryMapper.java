package com.odn.mappers;

import com.odn.entities.CategoryEntity;
import com.odn.requests.categoryControllerRequests.SellerCreateNewCategoryRequest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryMapper {
    public CategoryEntity toEntity(SellerCreateNewCategoryRequest request, Long sellerId) {
        if ( request == null ) {
            return null;
        }

        CategoryEntity newRecord = new CategoryEntity();

        newRecord.setCode(request.getCode().trim());
        newRecord.setName(request.getName());
        newRecord.setSellerId(sellerId);

        return newRecord;
    }
}
