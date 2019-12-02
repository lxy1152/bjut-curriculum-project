package com.finalwork.android.e_commerce.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.fragment.fragmentfollowing.FragmentFollowingItem;
import com.finalwork.android.e_commerce.view.fragment.fragmentfollowing.FragmentFollowingItemNull;
import com.finalwork.android.e_commerce.view.fragment.fragmentfollowing.FragmentFollowingShop;
import com.finalwork.android.e_commerce.view.fragment.fragmentfollowing.FragmentFollowingShopNull;

public class FollowingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        int id = getIntent().getIntExtra("id", 0);
        switch (id){
            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.followingfragment_container, new FragmentFollowingItem())
                        .commit();
                break;
            case 2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.followingfragment_container, new FragmentFollowingShop())
                        .commit();
                break;
            case -1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.followingfragment_container, new FragmentFollowingItemNull())
                        .commit();
                break;
            case -2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.followingfragment_container, new FragmentFollowingShopNull())
                        .commit();
                break;
            case 7:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.followingfragment_container, new FragmentFollowingItemNull())
                        .commit();
                break;
        }
    }
}
