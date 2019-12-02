package com.finalwork.android.e_commerce.view.fragment.fragmentmain;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.activity.SearchActivity;

import java.util.ArrayList;

class CategoryName {
    private View border;
    private TextView name;
    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public View getBorder() {
        return border;
    }

    public void setBorder(View border) {
        this.border = border;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    CategoryName(View border, TextView name, boolean isSelect) {
        this.setBorder(border);
        this.setName(name);
        this.setSelect(isSelect);
    }

    public void Click() {
        if (!this.isSelect()) {
            this.getName().getPaint().setFakeBoldText(true);
            this.getName().setBackgroundColor(Color.parseColor("#FFFFFF"));
            this.getBorder().setBackgroundColor(Color.parseColor("#DD0E23"));
            this.setSelect(true);
        } else {
            Log.w("CategorySelectWarning:", "this is selected!");
        }
    }

    public void Normal() {
        if (this.isSelect()) {
            this.setSelect(false);
        }
        this.getName().getPaint().setFakeBoldText(false);
        this.getName().setBackgroundColor(Color.parseColor("#DDDDDD"));
        this.getBorder().setBackgroundColor(Color.parseColor("#DDDDDD"));
    }
}

public class FragmentCategory extends Fragment {
    private ArrayList<CategoryName> categoryNames;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInInstanceState) {
        View view = inflater.inflate(R.layout.category, container, false);
        TextView search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        addListener(view);
        setDefaultFragment();
        return view;
    }

    private ArrayList<CategoryName> initCategoryName(View view) {
        ArrayList<CategoryName> categoryNames = new ArrayList<>();

        ArrayList<View> borders = new ArrayList<>();
        borders.add(view.findViewById(R.id.border_first));
        borders.add(view.findViewById(R.id.border_second));
        borders.add(view.findViewById(R.id.border_third));
        borders.add(view.findViewById(R.id.border_forth));

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add((TextView) view.findViewById(R.id.tv_first));
        textViews.add((TextView) view.findViewById(R.id.tv_second));
        textViews.add((TextView) view.findViewById(R.id.tv_third));
        textViews.add((TextView) view.findViewById(R.id.tv_forth));

        boolean[] isSelect = new boolean[]{true, false, false, false};

        for (int i = 0; i < borders.size(); i++) {
            CategoryName categoryName = new CategoryName(borders.get(i), textViews.get(i), isSelect[i]);
            categoryNames.add(categoryName);
        }
        return categoryNames;
    }

    private void addListener(View view) {
        categoryNames = initCategoryName(view);
        for (int i = 0; i < categoryNames.size(); i++) {
            final CategoryName categoryName = categoryNames.get(i);
            categoryName.getName().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (CategoryName categoryName1 : categoryNames) {
                        categoryName1.Normal();
                    }
                    categoryName.Click();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", categoryName.getName().getText().toString());
                    FragmentDetail fragmentDetail = new FragmentDetail();
                    fragmentDetail.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragmentpagecategory, fragmentDetail).commit();
                }
            });
        }
    }

    private void setDefaultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("title", categoryNames.get(0).getName().getText().toString());
        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragmentpagecategory, fragmentDetail).commit();
    }
}
