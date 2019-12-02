package com.finalwork.android.e_commerce.view.fragment.fragmentpending;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.finalwork.android.e_commerce.R;

public class FragmentPayNull extends Fragment{
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.readytopay_null, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.btn_readytopay_null_return);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
        return view;
    }
}
