package com.odn.responses.productControllerResponses;

import lombok.Data;

@Data
public class OpsGetAProductSizeResponse {
    private Long id;

    private String name;

    private String sku;

    private Double length;

    private Double width;

    private Double height;

    private Double weight;
}
