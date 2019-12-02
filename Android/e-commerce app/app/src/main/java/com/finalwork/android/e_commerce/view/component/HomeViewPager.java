package com.finalwork.android.e_commerce.view.component;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.fragment.fragmentmain.FragmentCart;
import com.finalwork.android.e_commerce.view.fragment.fragmentmain.FragmentCategory;
import com.finalwork.android.e_commerce.view.fragment.fragmentmain.FragmentHome;
import com.finalwork.android.e_commerce.view.fragment.fragmentmain.FragmentProfile;

import java.util.ArrayList;

public class HomeViewPager {
    private ViewPager viewPager;
    private FragmentHome fragmentHome;
    private FragmentCategory fragmentCategory;
    private FragmentCart fragmentCart;
    private FragmentProfile fragmentProfile;

    public FragmentProfile getFragmentProfile() {
        return fragmentProfile;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    private void init() {
        fragmentHome = new FragmentHome();
        fragmentCategory = new FragmentCategory();
        fragmentCart = new FragmentCart();
        fragmentProfile = new FragmentProfile();
    }

    private void addListener(final BottomNavigationView navigationView) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationView.getMenu().getItem(position).setChecked(true);
                //滑动页面后做的事，这里与BottomNavigationView结合，使其与正确page对应
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<Fragment> initPage() {
        final ArrayList<Fragment> fgLists = new ArrayList<>(4);
        fgLists.add(fragmentHome);
        fgLists.add(fragmentCategory);
        fgLists.add(fragmentCart);
        fgLists.add(fragmentProfile);
        return fgLists;
    }

    private void setAdapter(final ArrayList<Fragment> fgLists, FragmentManager fragmentManager) {
        FragmentPagerAdapter mPagerAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getCount() {
                return fgLists.size();
            }
        };
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }


    public HomeViewPager(ViewPager v, BottomNavigationView navigationView, FragmentManager fragmentManager) {
        this.viewPager = v;
        init();
        addListener(navigationView);
        setAdapter(initPage(), fragmentManager);
    }
}
