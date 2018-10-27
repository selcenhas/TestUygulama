package com.example.tcmhaskiris.testuygulama.product;

import android.util.Log;

import com.example.tcmhaskiris.testuygulama.product.model.Product;
import com.example.tcmhaskiris.testuygulama.product.model.ProductModel;
import com.example.tcmhaskiris.testuygulama.network.ApiClient;
import com.example.tcmhaskiris.testuygulama.network.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TCMHASKIRIS on 30.03.2018.
 */

public class GetProductData implements GetContract.GetProductData {

    private ArrayList<Product> productData;
    private GetContract.onGetDataListener onGetDatalistener;

    public GetProductData(GetContract.onGetDataListener onGetDatalistener) {
        this.onGetDatalistener = onGetDatalistener;
    }

    @Override
    public void initRetrofitCall() {

        ApiService apiService = ApiClient
                .getClient()
                .create(ApiService.class);

        Call<ProductModel> call = apiService.getProduct();

        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                ProductModel product = response.body();
                productData = new ArrayList<>(product.getProducts());
                Log.d("TAG", "Number of product " + productData.size());
                onGetDatalistener.onSuccess(productData);
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.v("Error", t.getMessage());
                onGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
