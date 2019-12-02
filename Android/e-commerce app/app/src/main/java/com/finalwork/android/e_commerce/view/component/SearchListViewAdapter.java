package com.finalwork.android.e_commerce.view.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.model.entity.SearchItem;
import com.finalwork.android.e_commerce.view.activity.CommodityActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchListViewAdapter extends BaseAdapter {
    private List<Map<String, Object>> mapList;
    private Context context;
    private boolean hide = false;
    private int line = 0;
    private ArrayList<SearchItem> searchItems;

    public SearchListViewAdapter(List<Map<String, Object>> mapList, Context context) {
        this.mapList = mapList;
        this.context = context;
        if (mapList.size() % 2 == 1) {
            hide = true;
            line = mapList.size() / 2;
            int index = 0;
            int row = 0;
            searchItems = new ArrayList<>();
            for (int i = 0; i < line; i++) {
                row = i;
                searchItems.add(new SearchItem(row, mapList.get(index), mapList.get(index + 1)));
                index = index + 2;
            }
            searchItems.add(new SearchItem(row + 1, mapList.get(mapList.size() - 1), null));
            line++;
        } else {
            line = mapList.size() / 2;
            int index = 0;
            int row = 0;
            ArrayList<SearchItem> searchItems = new ArrayList<>();
            for (int i = 0; i < line; i++) {
                row = i;
                searchItems.add(new SearchItem(row, mapList.get(index), mapList.get(index + 1)));
                index = index + 2;
            }
        }
    }

    private Context getContext() {
        return this.context;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        SearchViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.searching_item, null);
            holder = new SearchViewHolder();
            holder.setSeaching_item_left((RelativeLayout) convertView.findViewById(R.id.seaching_item_left));
            holder.setItemNameLeft((TextView) convertView.findViewById(R.id.item_name_left));
            holder.setItemCommentLeft((TextView) convertView.findViewById(R.id.item_comment_left));
            holder.setItemPriceLeft((TextView) convertView.findViewById(R.id.item_price_left));
            holder.setGoodRateLeft((TextView) convertView.findViewById(R.id.item_goodrate_left));
            holder.setItemImageLeft((ImageView) convertView.findViewById(R.id.item_img_left));

            holder.setSeaching_item_right((RelativeLayout) convertView.findViewById(R.id.seaching_item_right));
            holder.setItemNameRight((TextView) convertView.findViewById(R.id.item_name_right));
            holder.setItemCommentRight((TextView) convertView.findViewById(R.id.item_comment_right));
            holder.setItemPriceRight((TextView) convertView.findViewById(R.id.item_price_right));
            holder.setGoodRateRight((TextView) convertView.findViewById(R.id.item_goodrate_right));
            holder.setItemImageRight((ImageView) convertView.findViewById(R.id.item_img_right));
            convertView.setTag(holder);
        } else {
            holder = (SearchViewHolder) convertView.getTag();
        }
        if (hide && position == searchItems.size() - 1) {
            holder.getSeaching_item_right().setVisibility(View.INVISIBLE);
            holder.getGoodRateLeft().setText("98%好评");
            holder.getItemImageLeft().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), CommodityActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", searchItems.get(position).getLeft().get("productName").toString());
                    bundle.putString("price", searchItems.get(position).getLeft().get("discountPrice").toString());
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }
            });
            holder.getItemCommentLeft().setText("1000条好评");
            holder.getItemNameLeft().setText(searchItems.get(position).getLeft().get("productName").toString());
            holder.getItemPriceLeft().setText("￥" + searchItems.get(position).getLeft().get("discountPrice").toString());
            holder.getItemImageLeft().setBackgroundResource(R.drawable.computer);
            holder.getSeaching_item_right().setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
}
