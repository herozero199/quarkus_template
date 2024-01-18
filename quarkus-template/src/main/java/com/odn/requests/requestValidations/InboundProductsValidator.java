package com.odn.requests.requestValidations;

import com.odn.requests.inboundControllerRequests.SellerCreateNewInboundRequestRequestProduct;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InboundProductsValidator implements ConstraintValidator<InboundProductsValidation, List<SellerCreateNewInboundRequestRequestProduct>> {
    @Override
    public boolean isValid(List<SellerCreateNewInboundRequestRequestProduct> childList, ConstraintValidatorContext constraintValidatorContext) {

        //Create empty ProductIdList
        List<Long> ProductIdList = new ArrayList<>();

        //Iterate on childList
        for (int i = 0; i < childList.size(); i++) {
            var child = childList.get(i);

            //Checks if the ProductIdList has the child's productId
            if (ProductIdList.contains(child.getProductId())) {

                //Found Duplicate email
                return false;
            }

            //Add the child's productId to ProductIdList (If duplicate productId is not found)
            ProductIdList.add(child.getProductId());

        };


        //There is no duplicate productId
        return true;
    }
}
