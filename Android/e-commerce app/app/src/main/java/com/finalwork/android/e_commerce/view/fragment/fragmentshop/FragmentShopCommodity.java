package com.finalwork.android.e_commerce.view.fragment.fragmentshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.component.SearchListViewAdapter;

import java.util.ArrayList;

public class FragmentShopCommodity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopdetail_commo, container, false);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("123");
//        strings.add("2344");
//        strings.add("22222");
//        strings.add("33333");
//
//        ArrayList<Integer> images = new ArrayList<>();
//        images.add(R.drawable.img_milk);
//        images.add(R.drawable.img_head);
//        images.add(R.drawable.img_manshirt);
//        images.add(R.drawable.img_shophead);
//        ListView listView = view.findViewById(R.id.shopdetail_listview);
//        listView.setAdapter(new SearchListViewAdapter(strings, getContext(), images));

        return view;
    }
}
