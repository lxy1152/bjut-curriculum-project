package com.finalwork.android.e_commerce.view.fragment.fragmentfollowing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.finalwork.android.e_commerce.R;

public class FragmentFollowingItemNull extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.itemfollowing_null, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.btn_itemfollowing_null_return);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
        return view;
    }
}
//httpclient
//ip+port/user/1
