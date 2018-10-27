package com.example.tcmhaskiris.testuygulama.product.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TCMHASKIRIS on 28.03.2018.
 */

public class ProductModel {
    @SerializedName("products")
    @Expose
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
