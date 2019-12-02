package com.finalwork.android.e_commerce.view.fragment.fragmentsearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.activity.RegisterActivity;

public class FragmentSearch extends Fragment {
    private EditText search;
    private Bundle bundleTo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchbar, container, false);

        search = view.findViewById(R.id.search);
        search.setFocusable(true);
        search.setFocusableInTouchMode(true);
        search.requestFocus();

        Bundle bundleFrom = getArguments();
        final int id = bundleFrom.getInt("container", 0);

        TextView commit = view.findViewById(R.id.searchcommit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search.getText().toString().equals("")){
                    Toast.makeText(getContext(), "请输入要搜索的内容！", Toast.LENGTH_SHORT).show();
                } else{
                    bundleTo = new Bundle();
                    bundleTo.putInt("container", id);
                    bundleTo.putString("what", search.getText().toString());
                    FragmentSearchResult fragmentSearchResult = new FragmentSearchResult();
                    fragmentSearchResult.setArguments(bundleTo);
                    try {
                        getActivity().getSupportFragmentManager().beginTransaction().
                                replace(id, fragmentSearchResult).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return view;
    }
}
