package com.odn.services;

import com.odn.baseServices.ProductBaseService;
import com.odn.common.Constants;
import com.odn.entities.ProductEntity;
import com.odn.exception.ServiceException;
import com.odn.mappers.ProductMapper;
import com.odn.requests.productControllerRequests.OpsEditAProductSizeRequest;
import com.odn.responses.productControllerResponses.OpsGetAProductSizeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class ProductService {
    private final ProductBaseService productBaseService;
    private final ProductMapper productMapper;

    @Transactional
    public OpsGetAProductSizeResponse opsGetAProductSize(Long sellerId, String sku) {
        ProductEntity product = productBaseService.findOneBySellerIdAndSku(sellerId, sku);
        if (product == null) {
            throw new ServiceException(Constants.ErrorMessage.Product.ERR_001);
        }

        return productMapper.toOpsGetAProductSizeResponse(product);
    }

    @Transactional
    public void opsEditAProductSize(Long userId, Long recordId, OpsEditAProductSizeRequest request) {
        ProductEntity product = productBaseService.findById(recordId);
        if (product == null) {
            throw new ServiceException(Constants.ErrorMessage.Product.ERR_001);
        }

        productBaseService.opsEditAProductSize(userId, product, request);
    }
}
