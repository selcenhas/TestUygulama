package com.example.tcmhaskiris.testuygulama.presenter;

import com.example.tcmhaskiris.testuygulama.product.GetContract;
import com.example.tcmhaskiris.testuygulama.product.GetProductDetailData;
import com.example.tcmhaskiris.testuygulama.product.model.ProductDetail;

/**
 * Created by TCMHASKIRIS on 30.03.2018.
 */

public class ProductDetailPresenter implements GetContract.onGetDataDetailListener, GetContract.GetDataDetailPresenter {
    private GetContract.ProductDetailView getDataDetailView;
    private GetProductDetailData getProductDetailData;

    public ProductDetailPresenter(GetContract.ProductDetailView getDataDetailView) {
        this.getDataDetailView = getDataDetailView;
        getProductDetailData = new GetProductDetailData(this);
    }

    @Override
    public void onGetDataDetailFromURL(String product_id) {
        getProductDetailData.initRetrofitCall(product_id);

    }

    @Override
    public void onSuccess(ProductDetail list) {
        getDataDetailView.onGetDataSuccess(list);
    }

    @Override
    public void onFailure(String message) {
        getDataDetailView.onGetDataFailure(message);
    }
}
