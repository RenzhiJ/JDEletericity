package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.MessagesBean;

import java.util.List;

/**
*作者：任志军
*编辑时间：2017/11/25
*更新时间：10:22
*用途
 */

public class RightAdapter extends BaseAdapter {
    private Context con;
    private List<MessagesBean.DataBean> data;

    public RightAdapter(Context con, List<MessagesBean.DataBean> data) {
        this.con = con;
        this.data = data;
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
        List<MessagesBean.DataBean.ListBean> list = data.get(i).getList();
        if(view==null){
            view = View.inflate(con, R.layout.right_item,null);
            holder = new ViewHolder();
            holder.tvRight = view.findViewById(R.id.tv_right);
            holder.rightRcy = view.findViewById(R.id.right_rcy);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvRight.setText(data.get(i).getName());
        TitleAdapter adapter = new TitleAdapter(con, list);
        GridLayoutManager manager = new GridLayoutManager(con,3);
        holder.rightRcy.setLayoutManager(manager);
        holder.rightRcy.setAdapter(adapter);
        return view;
    }
    class ViewHolder{
        TextView tvRight;
        RecyclerView rightRcy;
    }
}
