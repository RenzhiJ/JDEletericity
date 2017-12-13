package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.HomeListBean;

import java.util.List;


public class ZhuListAdapter extends RecyclerView.Adapter<ZhuListAdapter.ViewHolder> {
    private Context context;
    private List<HomeListBean.DataBean> datas;

    public ZhuListAdapter(Context context, List<HomeListBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.home_grid_list, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String images = datas.get(position).getIcon();
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.miaoshaImg);
        holder.txtPrice.setText(datas.get(position).getName());
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
            miaoshaImg = (ImageView) itemView.findViewById(R.id.home_list_img);
            txtPrice = (TextView) itemView.findViewById(R.id.home_list_tv);

        }
    }

}
