package com.example.pharmaapp;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.example.pharmaapp.Adapters.ProductsAdapter;
import com.example.pharmaapp.Models.ProductsModel;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity{
    private RecyclerView recyclerView;
    private ProductsAdapter productAdapter;
    List<ProductsModel> listProductsModel;
    ProductsModel model;

    ImageView item_image;
    TextView detailed_name, rating, detailed_desc, detailed_price, quantity;
    Button logOut;
    ImageView addItems, removeItems;
    Toolbar toolbar;
    public static final String SHARED_PREFS = "sharedPrefs";


    public Product() {
    }
//    ProductsModel listProductsModel = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        item_image = findViewById(R.id.item_image);
        logOut = findViewById(R.id.logOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Product.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.show_all_rec);
        toolbar = findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listProductsModel = new ArrayList<>();
        listProductsModel.add(new ProductsModel(R.drawable.one, "View details", "Avastin 400mg Injection", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/avastin"));
        listProductsModel.add(new ProductsModel(R.drawable.two, "View Details", "Augmentin 625 Duo Tablet", "2.2", "", "http://pharma-verify.vercel.app/admin/drugs-details/augmentin"));
        listProductsModel.add(new ProductsModel(R.drawable.three, "View details", "Azithral 500 Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/azithral"));
        listProductsModel.add(new ProductsModel(R.drawable.four, "View details", "Ascoril LS Syrup", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/ascoril"));
        listProductsModel.add(new ProductsModel(R.drawable.five, "View details", "Aciloc 150 Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/aciloc"));
        listProductsModel.add(new ProductsModel(R.drawable.six, "View details", "Allegra 120mg Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/allegra120"));
        listProductsModel.add(new ProductsModel(R.drawable.seven, "View details", "Avil 25 Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/avil"));
        listProductsModel.add(new ProductsModel(R.drawable.eight, "View details", "Aricep 5 Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/aricep"));
        listProductsModel.add(new ProductsModel(R.drawable.nine, "View details", "Amoxyclav 625 Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/amoxyclav"));
        listProductsModel.add(new ProductsModel(R.drawable.ten, "View details", "Atarax 25mg Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/atarax"));
        listProductsModel.add(new ProductsModel(R.drawable.eleven, "View details", "Azee 500 Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/azee"));
        listProductsModel.add(new ProductsModel(R.drawable.twelve, "View details", "Anovate Cream", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/anovate"));
        listProductsModel.add(new ProductsModel(R.drawable.thirteen, "View details", "Allegra-M Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/allegra"));
        listProductsModel.add(new ProductsModel(R.drawable.fourteen, "View details", "Ascoril D Plus Syrup Sugar Free", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/ascorilD"));
        listProductsModel.add(new ProductsModel(R.drawable.fifteen, "View details", "Alex Syrup", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/alex"));
        listProductsModel.add(new ProductsModel(R.drawable.sixteen, "View details", "Armotraz Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/armotraz"));
        listProductsModel.add(new ProductsModel(R.drawable.seventeen, "View details", "Augmentin Duo Oral Suspension", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/augmentin"));
        listProductsModel.add(new ProductsModel(R.drawable.eighteen, "View details", "Albendazole 400mg Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/albendazole"));
        listProductsModel.add(new ProductsModel(R.drawable.nineteen, "View details", "Arkamin Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/arkamin"));
        listProductsModel.add(new ProductsModel(R.drawable.twenty, "View details", "Allegra 180mg Tablet", "2.4", "22", "http://pharma-verify.vercel.app/admin/drugs-details/allegra180"));

        productAdapter = new ProductsAdapter((Context) Product.this, listProductsModel);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(Product.this, 2));
    }

}
