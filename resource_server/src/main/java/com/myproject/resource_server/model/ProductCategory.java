package com.myproject.resource_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductCategory extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

}