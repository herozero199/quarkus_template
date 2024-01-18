package com.odn.requests.inboundControllerRequests;

import com.odn.requests.requestValidations.InboundProductsValidation;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SellerCreateNewInboundRequestRequest {
    @NotNull(message = "Kho không được để trống")
    private Long warehouseId;

    @NotBlank(message = "Ngày dự kiến đến không được để trống")
    @Size(min=10, max = 10, message = "Ngày dự kiến đến không hợp lệ")
    private String estimateArrivalDate;

    @Size(max = 100, message = "Độ dài của Mã IR đối tác không quá 100 ký tự")
    private String partnerIrCode;

    @Size(min=10, max = 10, message = "Ngày hết hạn không hợp lệ")
    private String arrivalDeadline;

    @Size(max = 255, message = "Độ dài của Nhà cung cấp không quá 255 ký tự")
    private String supplierName;

    @NotNull(message = "Loại nhập kho không được để trống")
    private Integer irType;

    @NotNull(message = "Tình trạng hàng hóa không được để trống")
    private Integer conditionType;

    @Size(max = 100, message = "Độ dài của Mã tham chiếu không quá 100 ký tự")
    private String referenceCode;

    @Size(max = 255, message = "Độ dài của Tên đối tác trả hàng không quá 255 ký tự")
    private String partnerName;

    @Size(max = 255, message = "Độ dài của Địa chỉ trả hàng không quá 255 ký tự")
    private String partnerReturnAddress;

    @Size(max = 255, message = "Độ dài của Người liên hệ không quá 255 ký tự")
    private String contactName;

    @Size(max = 255, message = "Độ dài của Địa chỉ người liên hệ không quá 255 ký tự")
    private String contactAddress;

    @Size(max = 20, message = "Độ dài của Số điện thoại người liên hệ không quá 20 ký tự")
    private String contactPhone;

    @Size(max = 100, message = "Độ dài của Email người liên hệ không quá 100 ký tự")
    private String contactEmail;

    @Size(max = 255, message = "Độ dài của Tên tài xế không quá 255 ký tự")
    private String driverName;

    @Size(max = 255, message = "Độ dài của Số xe không quá 255 ký tự")
    private String vehicleNumber;

    @Size(max = 255, message = "Độ dài của Số container không quá 255 ký tự")
    private String containerNumber;

    @Size(max = 510, message = "Độ dài của Ghi chú không quá 510 ký tự")
    private String note;

    @NotNull(message = "Chưa chọn sản phẩm")
    @Size(min = 1, message = "Chưa chọn sản phẩm")
    @Valid
    @InboundProductsValidation(message = "Xuất hiện sản phẩm trùng lặp")
    private List<SellerCreateNewInboundRequestRequestProduct> products;
}

