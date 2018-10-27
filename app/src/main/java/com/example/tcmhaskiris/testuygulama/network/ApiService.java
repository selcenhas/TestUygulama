package com.example.tcmhaskiris.testuygulama.network;

import com.example.tcmhaskiris.testuygulama.product.model.ProductDetail;
import com.example.tcmhaskiris.testuygulama.product.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by TCMHASKIRIS on 28.03.2018.
 */

public interface ApiService {
    @GET("list")
    Call<ProductModel> getProduct();

    @GET("{product_id}/detail")
    Call<ProductDetail> getProductDetails(@Path("product_id") String id);
}
