package com.maomkt.product.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private String name;
    private String description;

    private Number quantity;

    private Number minimalQuantity;

    private Number unitPrice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    public Number getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(Number minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public Number getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Number unitPrice) {
        this.unitPrice = unitPrice;
    }
}
