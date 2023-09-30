package com.usarb.pscs2lab02.csvimport.models;

import jakarta.persistence.*;

@Entity(name = "products")
public class Product extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private ProductCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private ProductBox box;

    @Column(unique = true)
    private String name;

    @Column
    private Float baseMargin;

    @Column
    private Float price;

    @Column
    private Float shippingPrice;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Float shippingPrice) {
        this.shippingPrice = shippingPrice;
    }
}
