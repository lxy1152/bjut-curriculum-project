package com.finalwork.android.e_commerce.view.fragment.fragmentmain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.HttpController;
import com.finalwork.android.e_commerce.controller.IpController;
import com.finalwork.android.e_commerce.controller.ResultController;
import com.finalwork.android.e_commerce.controller.UserController;
import com.finalwork.android.e_commerce.model.entity.Trolley;
import com.finalwork.android.e_commerce.view.activity.FollowingActivity;
import com.finalwork.android.e_commerce.view.activity.LoginActivity;
import com.finalwork.android.e_commerce.view.activity.SearchActivity;
import com.finalwork.android.e_commerce.view.component.CartListViewAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import static com.finalwork.android.e_commerce.controller.ResultController.DEFAULT;

public class FragmentCart extends Fragment {
    private boolean isEmpty = false;

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shoppingcart_null, container, false);
        TextView followingItem = view.findViewById(R.id.btn_shoppingcart_null_followed);
        followingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UserController.getInstance().getInfo(getContext()).get(2).equals("login")) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), FollowingActivity.class);
                    intent.putExtra("id", -1);
                    startActivity(intent);
                }
            }
        });
        TextView recommend = view.findViewById(R.id.btn_shoppingcart_null_recommend);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                bundle.putString("what", "为您推荐");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ArrayList<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("loginName", UserController.getInstance().getInfo(getContext()).get(0)));
            HttpController.getInstance().requestThread(new IpController().getCartAddress(), pairs);
            String rec;
            while (true) {
                rec = ResultController.getInstance().getResult();
                if (!rec.equals(DEFAULT)) {
                    ResultController.getInstance().setResult(DEFAULT);
                    break;
                }
            }
            if (!rec.equals("null")) {
                RelativeLayout relativeLayout = getView().findViewById(R.id.cart_all);
                ListView listView = getView().findViewById(R.id.cart_listview);
                relativeLayout.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
                Trolley trolley = JSON.toJavaObject((JSON) JSON.parse(rec), Trolley.class);
                ArrayList<Trolley> trolleys = new ArrayList<>();
                trolleys.add(trolley);
                listView.setAdapter(new CartListViewAdapter(trolleys, getContext()));
            }
        }
    }
}
