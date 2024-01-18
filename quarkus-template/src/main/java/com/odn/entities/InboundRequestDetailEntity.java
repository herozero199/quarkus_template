package com.odn.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "InboundRequestDetail")
@Table(name = "inbound_request_detail")
@Data
public class InboundRequestDetailEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "inbound_request_id")
    private Long inboundRequestId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;
}
