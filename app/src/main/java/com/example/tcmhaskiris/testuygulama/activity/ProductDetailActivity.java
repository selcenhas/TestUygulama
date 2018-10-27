package com.example.tcmhaskiris.testuygulama.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tcmhaskiris.testuygulama.product.GetContract;
import com.example.tcmhaskiris.testuygulama.product.model.ProductDetail;
import com.example.tcmhaskiris.testuygulama.network.utils.NetworkUtils;
import com.example.tcmhaskiris.testuygulama.presenter.ProductDetailPresenter;
import com.example.tcmhaskiris.testuygulama.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by TCMHASKIRIS on 29.03.2018.
 */

public class ProductDetailActivity extends AppCompatActivity implements GetContract.ProductDetailView {
    ImageView ivProductImage;
    TextView tvProductName;
    TextView tvProductPrice;
    TextView tvProductDesc;
    LinearLayout progress;

    private ProductDetailPresenter presenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);

        ivProductImage = findViewById(R.id.imageView);
        tvProductName = findViewById(R.id.textView);
        tvProductPrice = findViewById(R.id.textView2);
        tvProductDesc = findViewById(R.id.textView3);

        Intent i = getIntent();
        String productId = i.getExtras().getString("Product Id");

        presenter = new ProductDetailPresenter(this);
        presenter.onGetDataDetailFromURL(productId);
        progress = findViewById(R.id.linlaHeaderProgress);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDataSuccess(ProductDetail productDetail) {
        tvProductName.setText(productDetail.getName());
        tvProductPrice.setText("" + productDetail.getPrice());
        tvProductDesc.setText(productDetail.getDescription());
        if (NetworkUtils.isNetworkAvailable(ProductDetailActivity.this)) {
            Picasso.with(getApplicationContext())
                    .load(productDetail.getImage())
                    .into(ivProductImage);
        } else {
            Picasso.with(getApplicationContext())
                    .load(productDetail.getImage())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(ivProductImage);
        }
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetDataFailure(String message) {
        progress.setVisibility(View.INVISIBLE);
        Log.d("TAG", message);

    }
}


