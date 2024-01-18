package com.odn.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "InboundRequest")
@Table(name = "inbound_request")
@Data
public class InboundRequestEntity {
    @Id
    //@GenericGenerator(name="IdOrGenerated", strategy="com.odn.entities.UseIdOrGenerate")
    //@GeneratedValue(strategy= GenerationType.IDENTITY, generator="IdOrGenerated")
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "ir_code")
    private String irCode;

    @Column(name = "partner_ir_code")
    private String partnerIrCode;

    @Column(name = "estimate_arrival_date")
    private ZonedDateTime estimateArrivalDate;

    @Column(name = "arrival_deadline")
    private ZonedDateTime arrivalDeadline;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "ir_type")
    private Integer irType;

    @Column(name = "condition_type")
    private Integer conditionType;

    @Column(name = "reference_code")
    private String referenceCode;

    @Column(name = "partner_name")
    private String partnerName;

    @Column(name = "partner_return_address")
    private String partnerReturnAddress;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_address")
    private String contactAddress;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "container_number")
    private String containerNumber;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sale_channel_id")
    private Long saleChannelId;

    @Column(name = "completed_date")
    private ZonedDateTime completedDate;
}
