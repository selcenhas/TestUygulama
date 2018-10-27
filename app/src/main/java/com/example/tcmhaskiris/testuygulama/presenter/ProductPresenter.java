package com.example.tcmhaskiris.testuygulama.presenter;

import com.example.tcmhaskiris.testuygulama.product.GetContract;
import com.example.tcmhaskiris.testuygulama.product.GetProductData;
import com.example.tcmhaskiris.testuygulama.product.model.Product;

import java.util.ArrayList;

/**
 * Created by TCMHASKIRIS on 30.03.2018.
 */

public class ProductPresenter implements GetContract.onGetDataListener, GetContract.GetDataPresenter {

    private GetContract.ProductView getDataView;
    private GetProductData getProductData;

    public ProductPresenter(GetContract.ProductView getDataView) {
        this.getDataView = getDataView;
        getProductData = new GetProductData(this);
    }

    @Override
    public void onSuccess(ArrayList<Product> list) {
        getDataView.onGetDataSuccess(list);
    }

    @Override
    public void onFailure(String message) {
        getDataView.onGetDataFailure(message);
    }

    @Override
    public void onGetDataFromURL() {
        getProductData.initRetrofitCall();
    }
}

