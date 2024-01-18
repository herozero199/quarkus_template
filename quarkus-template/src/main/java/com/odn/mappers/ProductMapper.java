package com.odn.mappers;

import com.odn.entities.ProductEntity;
import com.odn.responses.productControllerResponses.OpsGetAProductSizeResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductMapper {
    public OpsGetAProductSizeResponse toOpsGetAProductSizeResponse(ProductEntity input) {
        if ( input == null ) {
            return null;
        }

        OpsGetAProductSizeResponse output = new OpsGetAProductSizeResponse();
        output.setId(input.getId());
        output.setName(input.getName());
        output.setSku(input.getSku());
        output.setLength(input.getLength());
        output.setWidth(input.getWidth());
        output.setHeight(input.getHeight());
        output.setWeight(input.getWeight());

        return output;
    }
}
