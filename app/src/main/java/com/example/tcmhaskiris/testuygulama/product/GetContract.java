package com.example.tcmhaskiris.testuygulama.product;

import com.example.tcmhaskiris.testuygulama.product.model.Product;
import com.example.tcmhaskiris.testuygulama.product.model.ProductDetail;

import java.util.ArrayList;

/**
 * Created by TCMHASKIRIS on 30.03.2018.
 */

public interface GetContract {

    interface GetDataPresenter {
        void onGetDataFromURL();
    }

    interface GetDataDetailPresenter {
        void onGetDataDetailFromURL(String product_id);
    }

    interface GetProductData {
        void initRetrofitCall();
    }

    interface GetProductDetailData {
        void initRetrofitCall(String product_id);
    }

    interface onGetDataListener {
        void onSuccess(ArrayList<Product> list);

        void onFailure(String message);
    }

    interface onGetDataDetailListener {
        void onSuccess(ProductDetail list);

        void onFailure(String message);
    }

    interface ProductView {
        void onGetDataSuccess(ArrayList<Product> list);

        void onGetDataFailure(String message);
    }

    interface ProductDetailView {
        void onGetDataSuccess(ProductDetail list);

        void onGetDataFailure(String message);
    }

}
