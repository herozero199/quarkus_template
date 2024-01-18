package com.odn.common;

public class Constants {
    public static class ErrorCode {
        public static final int SUCCESS = 0;
        public static final int UNKNOWN_ERROR = 99;
        public static final int SERVICE_EXCEPTION = 1;
        public static final int VALIDATION_ERROR = 2;
    }

    public static class ErrorMessage {
        public static final String SUCCESS = "success";

        public static class Common {
            public static final String ERR_001 = "Đối tác không tồn tại";
        }

        public static class Category {
            public static final String ERR_001 = "Danh mục sản phẩm không tồn tại";
            public static final String ERR_002 = "Trùng Mã với danh mục đã tồn tại";
        }

        public static class InboundRequest {
            public static final String ERR_001 = "Trùng Mã IR đối tác với dữ liệu đã tồn tại";
            public static final String ERR_002 = "Ngày dự kiến đến không hợp lệ";
            public static final String ERR_003 = "Ngày hết hạn không hợp lệ";
            public static final String ERR_004 = "Xuất hiện sản phẩm hợp lệ";
        }

        public static class Product {
            public static final String ERR_001 = "Sản phẩm không tồn tại";
        }
    }

    public static class Roles {
        public static final String ODN_ADMIN = "ODN_ADMIN";
        public static final String ODN_USER = "ODN_USER";
        public static final String SELLER_ADMIN = "SELLER_ADMIN";
        public static final String SELLER_USER = "SELLER_USER";
    }

    public static class FixedIds {
        public static final Long SALE_CHANNEL_PARTNER_PORTAL_ID = 1L;
    }

    public static class ColumnValueCode {
        public static final String PRODUCT_STATUS = "PRODUCT_STATUS";
        public static final String PRODUCT_OUTBOUND_TYPE = "PRODUCT_OUTBOUND-TYPE";
        public static final String PRODUCT_ASSET_TYPE = "PRODUCT_ASSET-TYPE";
        public static final String PRODUCT_PRESERVE_TYPE = "PRODUCT_PRESERVE-TYPE";
        public static final String PRODUCT_STORE_TYPE = "PRODUCT_STORE-TYPE";

        public static final String INBOUND_REQUEST_STATUS = "INBOUND_REQUEST_STATUS";
    }

    public static class BICode {

    }
}
