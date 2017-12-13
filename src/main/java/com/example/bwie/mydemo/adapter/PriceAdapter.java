package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.mydemo.DetailActivity;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.YueBingBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
*作者：任志军
*编辑时间：2017/11/21
*更新时间：10:09
*用途
 */

public class PriceAdapter extends XRecyclerView.Adapter<PriceAdapter.ViewHolder> {
    private Context con;
    private List<YueBingBean.DataBean> list;

    public PriceAdapter(Context con, List<YueBingBean.DataBean> list) {
        this.con = con;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.sublist_item, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.price.setText("￥"+list.get(position).getBargainPrice());
//        list.get(position).get
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(con).load(split[0]).into(holder.img);
        int pid = list.get(position).getPid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, DetailActivity.class);
                intent.putExtra("pid",list.get(position).getPid()+"");
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView price;

        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.sublist_title);
            price = itemView.findViewById(R.id.sublist_price);

            img = itemView.findViewById(R.id.sublist_img);

        }
    }
}
