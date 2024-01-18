package com.odn.databaseQueryModels;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class NextValQueryModel {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "next_val")
    private Long nextVal;
}
