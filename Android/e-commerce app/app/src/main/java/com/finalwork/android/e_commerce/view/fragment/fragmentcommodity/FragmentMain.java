package com.finalwork.android.e_commerce.view.fragment.fragmentcommodity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.component.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class FragmentMain extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commoditymain, container, false);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545566413&di=517cb46e15d40db1faaeee84ba364838&imgtype=jpg&er=1&src=http%3A%2F%2F2.zol-img.com.cn%2Fproduct%2F180_450x337%2F877%2Fce5lmgfL3eCc.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545566439&di=de319ed6e2b3f97ed7f16569036886d1&imgtype=jpg&er=1&src=http%3A%2F%2F2.zol-img.com.cn%2Fproduct%2F180_450x337%2F884%2Fce7SIYHcxTmE.jpg");

        Banner banner = view.findViewById(R.id.commo_banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.isAutoPlay(false);
        banner.start();

        try {
            Bundle bundle = getArguments();
            String nameStr = bundle.getString("name");
            String priceStr = bundle.getString("price");
            System.out.println(nameStr + "  " + priceStr);
            TextView price = view.findViewById(R.id.commo_price);
            price.setText("￥" + priceStr);
            TextView name = view.findViewById(R.id.commo_name);
            name.setText(nameStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
