package com.odn.entities;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "Category")
@Table(name = "category")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "seller_id")
    private Long sellerId;
}
