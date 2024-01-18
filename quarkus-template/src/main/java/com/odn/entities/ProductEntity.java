package com.odn.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "Product")
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "name")
    private String name;

    @Column(name = "sku")
    private String sku;

    @Column(name = "partner_sku")
    private String partnerSku;

    @Column(name = "status")
    private Integer status;

    @Column(name = "outbound_type")
    private Integer outboundType;

    @Column(name = "asset_type")
    private Integer assetType;

    @Column(name = "preserve_type")
    private Integer preserveType;

    @Column(name = "is_lot_number")
    private Integer isLotNumber;

    @Column(name = "store_type")
    private Integer storeType;

    @Column(name = "is_tracking_serial")
    private Integer isTrackingSerial;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "is_expiry_date")
    private Integer isExpiryDate;

    @Column(name = "days_warning_exp")
    private Integer daysWarningExp;

    @Column(name = "days_export_before_exp")
    private Integer daysExportBeforeExp;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "packing_note")
    private String packingNote;

    @Column(name = "description")
    private String description;

    @Column(name = "length")
    private Double length;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;
}
