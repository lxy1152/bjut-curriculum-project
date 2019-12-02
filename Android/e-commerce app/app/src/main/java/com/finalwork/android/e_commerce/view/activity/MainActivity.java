package com.finalwork.android.e_commerce.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.view.component.HomeViewPager;
import com.finalwork.android.e_commerce.view.fragment.fragmentmain.FragmentProfile;

public class MainActivity extends AppCompatActivity {
    private HomeViewPager homeViewPager;
    private final UserController userController = UserController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        homeViewPager = new HomeViewPager((ViewPager) findViewById(R.id.mViewPager), navigation, getSupportFragmentManager());

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        homeViewPager.getViewPager().setCurrentItem(0);
                        return true;
                    case R.id.navigation_category:
                        homeViewPager.getViewPager().setCurrentItem(1);
                        return true;
                    case R.id.navigation_cart:
                        homeViewPager.getViewPager().setCurrentItem(2);
                        return true;
                    case R.id.navigation_profile:
                        homeViewPager.getViewPager().setCurrentItem(3);
                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.mViewPager);
        if (current != null && current instanceof FragmentProfile) {
            if (!userController.getInfo(getApplicationContext()).get(2).equals("login")) {
                homeViewPager.getViewPager().setCurrentItem(0);
            }
            Log.w("IsProfile:", "true");
        }
    }
}
