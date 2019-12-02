package com.finalwork.android.e_commerce.view.component;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.finalwork.android.e_commerce.view.fragment.fragmentcommodity.FragmentComment;
import com.finalwork.android.e_commerce.view.fragment.fragmentcommodity.FragmentDetail;
import com.finalwork.android.e_commerce.view.fragment.fragmentcommodity.FragmentMain;

import java.util.ArrayList;

public class CommodityViewPager {
    private ViewPager viewPager;
    private FragmentMain fragmentMain;
    private FragmentDetail fragmentDetail;
    private FragmentComment fragmentComment;

    public ViewPager getViewPager() {
        return viewPager;
    }

    private void init(Bundle bundle) {
        fragmentMain = new FragmentMain();
        fragmentMain.setArguments(bundle);
        fragmentDetail = new FragmentDetail();
        fragmentComment = new FragmentComment();
    }

    private ArrayList<Fragment> initPage() {
        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(fragmentMain);
        fgLists.add(fragmentDetail);
        fgLists.add(fragmentComment);
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
        viewPager.setOffscreenPageLimit(2);
    }

    private void addListener(final ArrayList<TextView> topNavigation) {
        topNavigation.get(0).setTextColor(Color.parseColor("#FFFF0000"));
        getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < topNavigation.size(); j++) {
                    if (j == i) {
                        topNavigation.get(j).setTextColor(Color.parseColor("#FFFF0000"));
                    } else {
                        topNavigation.get(j).setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    public CommodityViewPager(ViewPager v, FragmentManager fragmentManager, ArrayList<TextView> topNavigation, Bundle bundle) {
        this.viewPager = v;
        init(bundle);
        addListener(topNavigation);
        setAdapter(initPage(), fragmentManager);
    }
}
