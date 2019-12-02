package com.finalwork.android.e_commerce.view.fragment.fragmentcommodity;

import android.graphics.Color;
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
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commoditydetail, container, false);

        final TextView introduction = view.findViewById(R.id.commo_detail_intro);
        final TextView para = view.findViewById(R.id.commo_detail_para);

        introduction.setTextColor(Color.parseColor("#FFFF0000"));
        try {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.commo_detail_fragment, new FragmentDetailIntro()).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment current = getActivity().getSupportFragmentManager().findFragmentById(R.id.commo_detail_fragment);
                if (current != null && current instanceof FragmentDetailPara) {
                    para.setTextColor(Color.parseColor("#000000"));
                    introduction.setTextColor(Color.parseColor("#FFFF0000"));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.commo_detail_fragment, new FragmentDetailIntro()).commit();
                }
            }
        });
        para.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment current = getActivity().getSupportFragmentManager().findFragmentById(R.id.commo_detail_fragment);
                if (current != null && current instanceof FragmentDetailIntro) {
                    para.setTextColor(Color.parseColor("#FFFF0000"));
                    introduction.setTextColor(Color.parseColor("#000000"));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.commo_detail_fragment, new FragmentDetailPara()).commit();
                }
            }
        });
        return view;
    }
}
