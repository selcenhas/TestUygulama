package com.example.tcmhaskiris.testuygulama.product;

import android.util.Log;

import com.example.tcmhaskiris.testuygulama.product.model.ProductDetail;
import com.example.tcmhaskiris.testuygulama.network.ApiClient;
import com.example.tcmhaskiris.testuygulama.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TCMHASKIRIS on 30.03.2018.
 */

public class GetProductDetailData implements GetContract.GetProductDetailData {

    private GetContract.onGetDataDetailListener onGetDataDetailListener;

    public GetProductDetailData(GetContract.onGetDataDetailListener onGetDataDetailListener) {
        this.onGetDataDetailListener = onGetDataDetailListener;
    }

    @Override
    public void initRetrofitCall(String product_id) {
        ApiService apiService = ApiClient
                .getClient()
                .create(ApiService.class);

        Call<ProductDetail> call = apiService.getProductDetails(product_id);

        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                ProductDetail productDetail = response.body();
                onGetDataDetailListener.onSuccess(productDetail);
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                Log.e("TAG", "FAIL");
                onGetDataDetailListener.onFailure(t.getMessage());
            }
        });

    }
}
