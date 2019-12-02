package com.finalwork.android.e_commerce.view.fragment.fragmentmain;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;

public class FragmentDetail extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdetail, container, false);
        try{
            Bundle bundle = getArguments();
            String title = bundle.getString("title", "");
            TextView textView = view.findViewById(R.id.detail_title);
            textView.setText(title);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
