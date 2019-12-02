package com.finalwork.android.e_commerce.view.fragment.fragmentmain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.model.HomeSection;
import com.finalwork.android.e_commerce.view.activity.SearchActivity;
import com.finalwork.android.e_commerce.view.component.GlideImageLoader;
import com.finalwork.android.e_commerce.view.component.HomeListViewAdapter;
import com.finalwork.android.e_commerce.view.component.ListViewForScrollView;
import com.youth.banner.Banner;

import java.util.ArrayList;

class Category {
    private RelativeLayout relativeLayout;
    private String name;

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    private void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Category(RelativeLayout relativeLayout, String name) {
        this.setRelativeLayout(relativeLayout);
        this.setName(name);
    }
}

public class FragmentHome extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        TextView search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putString("what", "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        UserController userController = new UserController();
        ArrayList<HomeSection> sections = new ArrayList<>();
        HomeSection cloth = new HomeSection();
        cloth.setFirst(R.drawable.listviewback);
        cloth.setSf(R.drawable.img_manshirt);
        cloth.setSs(R.drawable.img_manshirt);
        cloth.setSt(R.drawable.img_manshirt);
        cloth.setSsf("精品男装");
        cloth.setSss("精品男装");
        cloth.setSst("精品男装");
        cloth.setTitle("时尚服装");
        HomeSection food = new HomeSection();
        food.setFirst(R.drawable.u103);
        food.setSf(R.drawable.u106);
        food.setSs(R.drawable.u106);
        food.setSt(R.drawable.u106);
        food.setSsf("精品蔬菜");
        food.setSss("精品蔬菜");
        food.setSst("精品蔬菜");
        food.setTitle("食品生鲜");
        sections.add(cloth);
        sections.add(food);

        ListViewForScrollView listView = view.findViewById(R.id.mainlistview);
        listView.setFocusable(false);
        listView.setAdapter(new HomeListViewAdapter(sections, getContext()));
        setBanner(view);
        setTransition(view);
        return view;
    }

    private ArrayList<String> initImageList() {
        ArrayList<String> images = new ArrayList<>();
        images.add("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg");
        images.add("http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545478841&di=f7be568d7dfd698d7db09556b4b8d04e&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01381b56692ff532f875a5282d7efc.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544884148587&di=7cc18b41d55e39c848f2f552421d2b54&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017f9d57638f3a0000018c1b111d2f.jpg");
        return images;
    }

    private void setBanner(View view) {
        Banner banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(initImageList());
        banner.start();
    }

    private ArrayList<Category> initCategoryList(View view) {
        final int CATEGORY_NUM = 10;
        ArrayList<RelativeLayout> relativeLayouts = new ArrayList<>();
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.choice));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.food));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.baby));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.office));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.birth));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.pets));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.publication));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.threec));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.cloth));
        relativeLayouts.add((RelativeLayout) view.findViewById(R.id.etc));

        ArrayList<String> names = new ArrayList<>();
        names.add(((TextView) view.findViewById(R.id.tv_choice)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_food)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_baby)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_office)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_birth)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_pets)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_publication)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_threec)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_cloth)).getText().toString());
        names.add(((TextView) view.findViewById(R.id.tv_etc)).getText().toString());

        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < CATEGORY_NUM; i++) {
            Category category = new Category(relativeLayouts.get(i), names.get(i));
            categories.add(category);
        }
        return categories;
    }

    private void setTransition(View view) {
        final ArrayList<Category> categories;
        categories = initCategoryList(view);
        for (int i = 0; i < categories.size(); i++) {
            final Category category = categories.get(i);
            category.getRelativeLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 2);
                    bundle.putString("what", category.getName());
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }
}
