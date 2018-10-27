package com.example.tcmhaskiris.testuygulama.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tcmhaskiris.testuygulama.activity.ProductDetailActivity;
import com.example.tcmhaskiris.testuygulama.product.model.Product;
import com.example.tcmhaskiris.testuygulama.network.utils.NetworkUtils;
import com.example.tcmhaskiris.testuygulama.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by TCMHASKIRIS on 26.03.2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<Product> productList = Collections.emptyList();
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productPrice;
        public ImageView productImage;
        public CardView productCard;

        public ViewHolder(View itemView) {
            super(itemView);
            productCard = itemView.findViewById(R.id.card_view);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }


    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product productInfo = productList.get(position);

        String productName = productInfo.getName();
        int productPrice = productInfo.getPrice();
        String productImage = productInfo.getImage();

        holder.productName.setText(productName);
        holder.productPrice.setText(" " + productPrice);


        if (NetworkUtils.isNetworkAvailable(context)) {
            Picasso.with(context)
                    .load(productImage)
                    .into(holder.productImage);
        } else {
            Picasso.with(context)
                    .load(productImage)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder.productImage);
        }


        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(context, ProductDetailActivity.class);
                    i.putExtra("Product Id", productInfo.getProductId());
                    context.startActivity(i);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
