package com.odn.requests.categoryControllerRequests;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SellerCreateNewCategoryRequest {
    @NotBlank(message = "Mã không được để trống")
    @Size(max = 100, message = "Độ dài của mã không quá 100 ký tự")
    private String code;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 255, message = "Độ dài của tên không quá 100 ký tự")
    private String name;
}
