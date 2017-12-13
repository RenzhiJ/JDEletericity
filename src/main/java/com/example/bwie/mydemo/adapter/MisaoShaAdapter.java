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


public class MisaoShaAdapter extends RecyclerView.Adapter<MisaoShaAdapter.ViewHolder> {
    private static final String TAG = "MisaoShaAdapter";
    private Context context;
    private List<DataList> datas;

    public MisaoShaAdapter(Context context, List<DataList> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_miaosha, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String images = datas.get(position).getImages();
        //分割
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.miaoshaImg);
        holder.txtPrice.setText("￥"+datas.get(position).getPrice());
        int pid = datas.get(position).getPid();
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
        private ImageView miaoshaImg;
        private TextView txtPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            miaoshaImg = (ImageView) itemView.findViewById(R.id.miaosha_img);
            txtPrice = (TextView) itemView.findViewById(R.id.miaosha_tv);

        }
    }

}
