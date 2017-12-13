package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.DataBean;

import java.util.List;

/**
*作者：任志军
*编辑时间：2017/11/11
*更新时间：10:22
*用途
 */

public class LeftAdapter extends BaseAdapter {
    private Context con;
    private List<DataBean> data;
    private int selectItem = 0;

    public LeftAdapter(Context con, List<DataBean> data) {
        this.con = con;
        this.data = data;
    }
    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null){
            view = View.inflate(con, R.layout.left_item,null);
            holder = new ViewHolder();
//            view.set
            holder.tvLeft = view.findViewById(R.id.tv_left);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvLeft.setText(data.get(i).getName());

        return view;
    }
    class ViewHolder{
        TextView tvLeft;
    }
}
