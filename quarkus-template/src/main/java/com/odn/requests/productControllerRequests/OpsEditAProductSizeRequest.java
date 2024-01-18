package com.odn.requests.productControllerRequests;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OpsEditAProductSizeRequest {
    @DecimalMin(value = "0.1", message = "Chiều dài không được nhỏ hơn 0.1 cm")
    @DecimalMax(value = "10000", message = "Chiều dài không được lớn hơn 100 m")
    private Double length;

    @DecimalMin(value = "0.1", message = "Chiều rộng không được nhỏ hơn 0.1 cm")
    @DecimalMax(value = "10000", message = "Chiều rộng không được lớn hơn 100 m")
    private Double width;

    @DecimalMin(value = "0.1", message = "Chiều cao không được nhỏ hơn 0.1 cm")
    @DecimalMax(value = "10000", message = "Chiều cao không được lớn hơn 100 m")
    private Double height;

    @DecimalMin(value = "1", message = "Cân nặng không được nhỏ hơn 1 gram")
    @DecimalMax(value = "10000000", message = "Cân nặng không được lớn hơn 10 tấn")
    private Double weight;
}
