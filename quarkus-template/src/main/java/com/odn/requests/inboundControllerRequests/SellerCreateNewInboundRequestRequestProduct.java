package com.odn.requests.inboundControllerRequests;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SellerCreateNewInboundRequestRequestProduct {
    @NotNull(message = "Id sản phẩm không được để trống")
    private Long productId;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng không được nhỏ hơn 1")
    @Max(value = 1000000, message = "Số lượng không được lớn hơn 1 triệu")
    private Long quantity;
}
