package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.mydemo.DetailActivity;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.DataList;

import java.util.List;


public class HomeBottomAdapter extends RecyclerView.Adapter<HomeBottomAdapter.ViewHolder> {
    private Context context;
    private List<DataList> datas;

    public HomeBottomAdapter(Context context, List<DataList> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.home_bottom_layout, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String images = datas.get(position).getImages();
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.bottomImg);
        holder.txtPrice.setText("￥"+datas.get(position).getPrice());
        holder.txtName.setText(datas.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pid",datas.get(position).getPid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bottomImg;
        private TextView txtPrice;
        private TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            bottomImg = (ImageView) itemView.findViewById(R.id.home_bottom_img);
            txtName = (TextView) itemView.findViewById(R.id.home_bottom_name);
            txtPrice = (TextView) itemView.findViewById(R.id.home_bottom_price);

        }
    }

}
