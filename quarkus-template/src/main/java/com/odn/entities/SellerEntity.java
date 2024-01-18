package com.odn.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Seller")
@Table(schema = "common", name = "seller")
@Data
public class SellerEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;
}
