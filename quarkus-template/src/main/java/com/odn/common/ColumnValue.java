package com.odn.common;

import javax.ws.rs.core.Response;

public enum ColumnValue {
    // Inbound Request
    INBOUND_REQUEST_STATUS_NEW(0, Constants.ColumnValueCode.INBOUND_REQUEST_STATUS,"Mới"),
    // Product
    PRODUCT_STATUS_INACTIVE(0, Constants.ColumnValueCode.PRODUCT_STATUS,"Đã khóa"),
    PRODUCT_STATUS_ACTIVE(1, Constants.ColumnValueCode.PRODUCT_STATUS,"Hoạt động"),
    PRODUCT_OUTBOUND_TYPE_NOT_DEFINED(0, Constants.ColumnValueCode.PRODUCT_OUTBOUND_TYPE,"Không định nghĩa"),
    PRODUCT_OUTBOUND_TYPE_FIFO(1, Constants.ColumnValueCode.PRODUCT_OUTBOUND_TYPE,"Nhập kho trước xuất trước (FIFO)"),
    PRODUCT_OUTBOUND_TYPE_LIFO(2, Constants.ColumnValueCode.PRODUCT_OUTBOUND_TYPE,"Nhập kho sau xuất trươc (LIFO)"),
    PRODUCT_OUTBOUND_TYPE_FEFO(3, Constants.ColumnValueCode.PRODUCT_OUTBOUND_TYPE,"Hạn sử dụng thấp xuất trước (FEFO)"),
    PRODUCT_OUTBOUND_TYPE_LEFO(4, Constants.ColumnValueCode.PRODUCT_OUTBOUND_TYPE,"Hạn sử dụng lâu xuất trước (LEFO)");


    private final Integer value;
    private final String code;
    private final String name;

    private ColumnValue(Integer value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static ColumnValue fromValueAndCode(Integer value, String code) {
        ColumnValue[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ColumnValue s = var1[var3];
            if (s.value == value && s.code == code) {
                return s;
            }
        }

        return null;
    }
}
