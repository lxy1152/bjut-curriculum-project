package com.finalwork.android.e_commerce.view.component;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.model.HomeCategory;
import com.finalwork.android.e_commerce.model.HomeCategoryList;
import com.finalwork.android.e_commerce.model.HomeSection;

import java.util.ArrayList;

public class HomeListViewAdapter extends BaseAdapter {
    private ArrayList<HomeSection> mainCategories;
    private Context context;

    public HomeListViewAdapter(ArrayList<HomeSection> sections, Context context) {
        this.mainCategories = sections;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mainCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return mainCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        HomeViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.home_listview_item, null);
            holder = new HomeViewHolder();
            //此处进行查找
            holder.setTitle((TextView) convertView.findViewById(R.id.text_prime));
            holder.setFirstLine((ImageView) convertView.findViewById(R.id.img_firstline));
            holder.setSlf((ImageView) convertView.findViewById(R.id.img_slf));
            holder.setSls((ImageView) convertView.findViewById(R.id.img_sls));
            holder.setSlt((ImageView) convertView.findViewById(R.id.img_slt));
            holder.setSlfTv((TextView) convertView.findViewById(R.id.tvf));
            holder.setSlsTv((TextView) convertView.findViewById(R.id.tvs));
            holder.setSltTv((TextView) convertView.findViewById(R.id.tvt));
            convertView.setTag(holder);
        } else {
            holder = (HomeViewHolder) convertView.getTag();
        }
        //此处进行修改操作
        HomeSection section = mainCategories.get(position);
        holder.getTitle().setText(section.getTitle());
        holder.getFirstLine().setBackgroundResource(section.getFirst());
        holder.getSlf().setBackgroundResource(section.getSf());
        holder.getSls().setBackgroundResource(section.getSs());
        holder.getSlt().setBackgroundResource(section.getSt());
        holder.getSlfTv().setText(section.getSsf());
        holder.getSlsTv().setText(section.getSss());
        holder.getSltTv().setText(section.getSst());
        return convertView;
    }

}
