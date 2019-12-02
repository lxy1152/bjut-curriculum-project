package com.finalwork.android.e_commerce.view.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.model.HomeSection;
import com.finalwork.android.e_commerce.model.entity.Trolley;

import java.util.ArrayList;

public class CartListViewAdapter extends BaseAdapter {
    private ArrayList<Trolley> trolleys;
    private Context context;

    public CartListViewAdapter(ArrayList<Trolley> trolley, Context context) {
        this.trolleys = trolley;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trolleys.size();
    }

    @Override
    public Object getItem(int position) {
        return trolleys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        CartViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.shoppingcart_item, null);
            holder = new CartViewHolder();
            //此处进行查找
            holder.setName((TextView) convertView.findViewById(R.id.item_name));
            holder.setDes((TextView) convertView.findViewById(R.id.text_shoppingcart_describe));
            holder.setPrice((TextView) convertView.findViewById(R.id.item_price));
            convertView.setTag(holder);
        } else {
            holder = (CartViewHolder) convertView.getTag();
        }
        //此处进行修改操作
        Trolley trolley = trolleys.get(position);
        holder.getName().setText(trolley.getSpecItem().getProduct().getProductName());
        holder.getDes().setText(trolley.getSpecItem().getSpecItemDescript());
        holder.getPrice().setText(trolley.getSpecItem().getSpecItemPrice().toString());
        return convertView;
    }
}
