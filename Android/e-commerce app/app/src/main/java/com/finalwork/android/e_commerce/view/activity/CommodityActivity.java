package com.finalwork.android.e_commerce.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.component.CommodityViewPager;

import java.util.ArrayList;

public class CommodityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commdity);

        ArrayList<TextView> topNavigation = new ArrayList<>();
        topNavigation.add((TextView) findViewById(R.id.commo_main));
        topNavigation.add((TextView) findViewById(R.id.commo_detail));
        topNavigation.add((TextView) findViewById(R.id.commo_comment));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final CommodityViewPager commodityViewPager = new CommodityViewPager
                ((ViewPager) findViewById(R.id.commo_viewpager), getSupportFragmentManager(), topNavigation, bundle);

        for (int i = 0; i < topNavigation.size(); i++) {
            final int index = i;
            topNavigation.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commodityViewPager.getViewPager().setCurrentItem(index);
                }
            });
        }

        ImageView ivShop = findViewById(R.id.iv_shop);
        TextView tvShop = findViewById(R.id.tv_shop);
        ivShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
        tvShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        ImageView btnRetrun = findViewById(R.id.btn_commo_return);
        btnRetrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button ok = findViewById(R.id.commo_buy);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CommodityActivity.this, "成功加入购物车！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
