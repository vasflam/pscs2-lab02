package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "products")
public class Product extends BaseModel {
    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategory category;

    @OneToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private ProductCategory subCategory;

    @OneToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private ProductBox box;

    @Column
    private Float baseMargin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductCategory subCategory) {
        this.subCategory = subCategory;
    }

    public ProductBox getBox() {
        return box;
    }

    public void setBox(ProductBox box) {
        this.box = box;
    }

    public Float getBaseMargin() {
        return baseMargin;
    }

    public void setBaseMargin(Float baseMargin) {
        this.baseMargin = baseMargin;
    }
}
