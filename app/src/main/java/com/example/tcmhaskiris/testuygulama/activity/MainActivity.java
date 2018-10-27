package com.example.tcmhaskiris.testuygulama.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tcmhaskiris.testuygulama.R;
import com.example.tcmhaskiris.testuygulama.adapter.ProductAdapter;
import com.example.tcmhaskiris.testuygulama.presenter.ProductPresenter;
import com.example.tcmhaskiris.testuygulama.product.GetContract;
import com.example.tcmhaskiris.testuygulama.product.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetContract.ProductView {

    RecyclerView recyclerView;
    ProductPresenter presenter;
    private ProductAdapter productAdapter;
    LinearLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new ProductPresenter(this);
        presenter.onGetDataFromURL();

    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        progress = findViewById(R.id.linlaHeaderProgress);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDataSuccess(ArrayList<Product> list) {
        setProgressBarIndeterminateVisibility(false);
        productAdapter = new ProductAdapter(list);
        recyclerView.setAdapter(productAdapter);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetDataFailure(String message) {
        progress.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Fail", Toast.LENGTH_LONG);
        Log.d("TAG", message);

    }
}
