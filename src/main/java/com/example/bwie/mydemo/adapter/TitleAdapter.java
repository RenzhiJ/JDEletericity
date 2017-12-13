package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.mydemo.GlideCircleTransform;
import com.example.bwie.mydemo.Main2Activity;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.MessagesBean;

import java.util.List;



public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {
    private Context con;
    private List<MessagesBean.DataBean.ListBean> list;
    public TitleAdapter(Context con, List<MessagesBean.DataBean.ListBean> list) {
        this.con=con;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.right_item_layout, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemName.setText(list.get(position).getName());
        Glide.with(con).load(list.get(position).getIcon()).transform(new GlideCircleTransform(con)).into(holder.itemImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, Main2Activity.class);
                int pscid = list.get(position).getPscid();
                intent.putExtra("pscid",pscid+"");
                con.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView itemName;
        private ImageView itemImg;
        private LinearLayout rightLlt;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_home_name);
            itemImg = itemView.findViewById(R.id.item_img);
            rightLlt = itemView.findViewById(R.id.right_llt);
        }
    }
}
