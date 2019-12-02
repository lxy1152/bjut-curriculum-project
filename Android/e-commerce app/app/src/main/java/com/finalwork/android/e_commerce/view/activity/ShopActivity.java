package com.finalwork.android.e_commerce.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.fragment.fragmentshop.FragmentShopCommodity;
import com.finalwork.android.e_commerce.view.fragment.fragmentshop.FragmentShopIntro;

public class ShopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdeatail_main);

        ImageView btnReturn = findViewById(R.id.btn_shop_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putString("what", "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.shop_detail_fragment, new FragmentShopIntro()).commit();

        final TextView intro = findViewById(R.id.tv_shopdetail_intro);
        final TextView commo = findViewById(R.id.tv_shopdetail_commodity);

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment current = getSupportFragmentManager().findFragmentById(R.id.shop_detail_fragment);
                if (current != null && current instanceof FragmentShopCommodity) {
                    try {
                        intro.setTextColor(Color.parseColor("#FFFF0000"));
                        commo.setTextColor(Color.parseColor("#000000"));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.shop_detail_fragment, new FragmentShopIntro()).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        commo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment current = getSupportFragmentManager().findFragmentById(R.id.shop_detail_fragment);
                if (current != null && current instanceof FragmentShopIntro) {
                    try {
                        commo.setTextColor(Color.parseColor("#FFFF0000"));
                        intro.setTextColor(Color.parseColor("#000000"));
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.shop_detail_fragment, new FragmentShopCommodity()).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
