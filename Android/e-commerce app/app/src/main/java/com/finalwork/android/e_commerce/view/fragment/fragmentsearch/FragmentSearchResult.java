package com.finalwork.android.e_commerce.view.fragment.fragmentsearch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.controller.HttpController;
import com.finalwork.android.e_commerce.controller.IpController;
import com.finalwork.android.e_commerce.controller.ResultController;
import com.finalwork.android.e_commerce.model.SearchItem;
import com.finalwork.android.e_commerce.model.entity.Search;
import com.finalwork.android.e_commerce.view.component.SearchListViewAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.finalwork.android.e_commerce.controller.ResultController.DEFAULT;

public class FragmentSearchResult extends Fragment {
    Bundle bundleTo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.searchresult, container, false);

        String what = bundle.getString("what", "");
        TextView search = view.findViewById(R.id.search);
        search.setText(what);

        Search search1 = new Search();
        search1.setClip(what);
        search1.setOrder("discountPrice");
        search1.setSort("desc");
        search1.setPage("1");
        search1.setRows("1");
        String json = JSON.toJSONString(search1);

        ArrayList<NameValuePair> pairs = new ArrayList<>();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("target", json);
        pairs.add(basicNameValuePair);

        HttpController.getInstance().requestThread(new IpController().getProductAddress(), pairs);
        String rec;
        while (true) {
            rec = ResultController.getInstance().getResult();
            if (!rec.equals(DEFAULT)) {
                System.out.println("rec:" + rec);
                ResultController.getInstance().setResult(DEFAULT);
                break;
            }
        }
        try {
            TextView textView = view.findViewById(R.id.text_nothing);
            ListView listView = view.findViewById(R.id.listview);
            Map map = (Map) JSON.parse(rec);
            try {
                JSONArray jsonArray = JSON.parseArray(map.get("list").toString());
                List<Map<String, Object>> mapListJson = (List) jsonArray;
                if (mapListJson.size() == 0) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("找不到该商品！");
                    listView.setVisibility(View.INVISIBLE);
                } else {
                    textView.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(new SearchListViewAdapter(mapListJson, getContext()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundleTo = new Bundle();
                bundleTo.putInt("container", bundle.getInt("container"));
                FragmentSearch fragmentSearch = new FragmentSearch();
                fragmentSearch.setArguments(bundleTo);
                try {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(bundle.getInt("container"), fragmentSearch).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                //隐藏输入法软键盘
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
