package com.example.pharmaapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pharmaapp.Models.ProductsModel;
import com.example.pharmaapp.R;

import java.util.List;

    public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{
        Context context;
        List<ProductsModel> list;

        public ProductsAdapter() {
        }
        public ProductsAdapter(Context context, List<ProductsModel> list) {
            this.context = context;
            this.list = list;
        }
        @NonNull
        @Override
        public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.products, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.item_image.setImageResource(list.get(position).getImg_url());
            holder.item_name.setText("View Details");
            holder.item_cost.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getWebUrl()));
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView item_image;
            TextView item_cost, item_name;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_image = itemView.findViewById(R.id.item_image);
                item_cost = itemView.findViewById(R.id.item_cost);
                item_name = itemView.findViewById(R.id.item_name);
            }
        }
    }

