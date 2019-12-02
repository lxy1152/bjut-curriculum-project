package com.finalwork.android.e_commerce.view.fragment.fragmentcommodity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.finalwork.android.e_commerce.R;

public class FragmentDetailIntro extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commodetail_intro, container, false);
        ImageView img = view.findViewById(R.id.commo_detail_intro_img);
        img.setImageDrawable(getResources().getDrawable(R.drawable.detail_intro));
        return view;
    }
}
