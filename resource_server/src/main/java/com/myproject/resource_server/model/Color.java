package com.myproject.resource_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "color")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "productVariantList")
public class Color extends BaseModel {

    @OneToMany(mappedBy = "color")
    private List<ProductVariant> productVariantList;

    @Column(name = "name")
    private String name;

    @Column(name = "hex")
    private String hex;

}
