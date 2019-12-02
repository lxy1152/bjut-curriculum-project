package com.finalwork.android.e_commerce.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.fragment.fragmentsearch.FragmentSearch;
import com.finalwork.android.e_commerce.view.fragment.fragmentsearch.FragmentSearchResult;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        FragmentSearch fragmentSearch = new FragmentSearch();
        FragmentSearchResult fragmentSearchResult = new FragmentSearchResult();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            int type = bundle.getInt("type", 0);
            String what = bundle.getString("what", "");
            if (type == 1) {
                Bundle bundleTo = new Bundle();
                bundleTo.putInt("container", R.id.fragment_searchbar_container);
                fragmentSearch.setArguments(bundleTo);
                fragmentTransaction.replace(R.id.fragment_searchbar_container, fragmentSearch).commit();
                //显示软键盘
                this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            } else if (type == 2) {
                Bundle bundleTo = new Bundle();
                bundleTo.putInt("container", R.id.fragment_searchbar_container);
                bundleTo.putString("what", what);
                fragmentSearchResult.setArguments(bundleTo);
                fragmentTransaction.replace(R.id.fragment_searchbar_container, fragmentSearchResult).commit();
            } else {
                Log.w("SearchError:", "bundle error!");
                this.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
