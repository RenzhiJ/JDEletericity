package com.example.bwie.mydemo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.bean.MessageBean;
import com.example.bwie.mydemo.entity.MessageEvent;
import com.example.bwie.mydemo.entity.PriceNumer;
import com.example.bwie.mydemo.entity.ById;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
*作者：任志军
*编辑时间：2017/11/20
*更新时间：10:07
*用途
 */
//购物车
public class CartAdapter extends BaseExpandableListAdapter {
    private Context con;
    private List<MessageBean.DataBean> groupList;
    private List<List<MessageBean.DataBean.ListBean>> childList;
    private LinearLayout cart_empty;
    private int count = 0;

    public boolean flag = true;
    public CartAdapter(Context con, LinearLayout cart_empty, List<MessageBean.DataBean> groupList, List<List<MessageBean.DataBean.ListBean>> childList) {
        this.con=con;
        this.groupList=groupList;
        this.childList= childList;
        this.cart_empty=cart_empty;
    }


    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final GroupViewHolder holder;
        if (view == null) {
            holder = new GroupViewHolder();
            view = view.inflate(con,R.layout.group_layout, null);
            holder.cbGroup = view.findViewById(R.id.group_check);
            holder.tv_number = view.findViewById(R.id.group_tv);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }
        final MessageBean.DataBean dataBean = groupList.get(i);
        holder.cbGroup.setChecked(dataBean.isChecked());
//        holder.tv_number.setText(dataBean.getTitle());
        holder.tv_number.setText(dataBean.getSellerName());
        //一级checkbox
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(holder.cbGroup.isChecked());
                changeChildCbState(i, holder.cbGroup.isChecked());
                EventBus.getDefault().post(compute());
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final ChildViewHolder holder;
        if (view == null) {
            holder = new ChildViewHolder();
            view = view.inflate(con,R.layout.child_layout, null);
            holder.cbChild = view.findViewById(R.id.child_check);
            holder.tv_tel = view.findViewById(R.id.tv_child);
            holder.imgIcon = view.findViewById(R.id.child_img);
            holder.tv_price = view.findViewById(R.id.tv_price);
            holder.tv_del = view.findViewById(R.id.tv_remove);
            holder.iv_add = view.findViewById(R.id.tv_add);
            holder.iv_del = view.findViewById(R.id.tv_del);
            holder.tv_num = view.findViewById(R.id.edt_number);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }
        final MessageBean.DataBean.ListBean datasBean = childList.get(i).get(i1);

        holder.cbChild.setChecked(datasBean.isChecked());
        holder.tv_tel.setText(datasBean.getTitle());
        holder.tv_price.setText("￥"+datasBean.getPrice() );
        holder.tv_num.setText(datasBean.getNum() + "");
        String images = datasBean.getImages().trim();
        String[] split = images.split("[|]");
        Glide.with(con).load(split[0]).into(holder.imgIcon);

        //二级checkbox
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里的checked属性值
                datasBean.setChecked(holder.cbChild.isChecked());
                PriceNumer priceAndCountEvent = compute();
                EventBus.getDefault().post(priceAndCountEvent);

                if (holder.cbChild.isChecked()) {
                    //当前checkbox是选中状态
                    if (isAllChildCbSelected(i)) {
                        changGroupCbState(i, true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                } else {
                    changGroupCbState(i, false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                notifyDataSetChanged();
            }


        });
        //加号
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = datasBean.getNum();
                holder.tv_num.setText(++num + "");
                datasBean.setNum(num);
                if (holder.cbChild.isChecked()) {
                    EventBus.getDefault().post(compute());
                }
            }
        });
        //减号
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = datasBean.getNum();
                if (num == 1) {
                    return;
                }
                holder.tv_num.setText(--num + "");
                datasBean.setNum(num);
                if (holder.cbChild.isChecked()) {

                    EventBus.getDefault().post(compute());
                }
            }
        });
        //删除
        holder.tv_del.setOnClickListener(new View.OnClickListener() {

            private AlertDialog dialog;

            @Override
            public void onClick(View v) {
                final List<MessageBean.DataBean.ListBean> datasBeen = childList.get(i);


                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                builder.setTitle("提示");
                builder.setMessage("确认是否删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int ii) {
                        MessageBean.DataBean.ListBean remove = datasBeen.remove(i1);
                        if (datasBeen.size() == 0) {
                            childList.remove(i);
                            groupList.remove(i);
                            int pid = datasBean.getPid();
//                            int pids = childList.get(i).get(i1).getPid();
//                            Log.e("zxz",pids+"id");
                            ById someId = new ById();
                            someId.setPid(pid);
                            someId.setSize(childList.size());
                            EventBus.getDefault().post(someId);
                            if(childList.isEmpty()||childList.size()==0){
                                cart_empty.setVisibility(View.VISIBLE);
                            }
                        }
                        EventBus.getDefault().post(compute());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog = builder.create();
                dialog.show();

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    class GroupViewHolder {
        CheckBox cbGroup;
        TextView tv_number;
    }

    class ChildViewHolder {
        CheckBox cbChild;
        TextView tv_tel;
        ImageView imgIcon;
        TextView tv_price;
        TextView tv_del;
        ImageView iv_del;
        ImageView iv_add;
        TextView tv_num;
    }
    /**
     * 改变全选的状态
     *
     * @param flag
     */
    private void changeAllCbState(boolean flag) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }
    /**
     * 改变一级列表checkbox状态
     *
     * @param groupPosition
     */
    private void changGroupCbState(int groupPosition, boolean flag) {
//        GoosBean.DataBean dataBean = groupList.get(groupPosition);
        MessageBean.DataBean dataBean = groupList.get(groupPosition);

        dataBean.setChecked(flag);
    }

    /**
     * 改变二级列表checkbox状态
     *
     * @param groupPosition
     * @param flag
     */
    private void changeChildCbState(int groupPosition, boolean flag) {
        List<MessageBean.DataBean.ListBean> datasBeen = childList.get(groupPosition);

        for (int i = 0; i < datasBeen.size(); i++) {
            MessageBean.DataBean.ListBean datasBean = datasBeen.get(i);
            datasBean.setChecked(flag);
        }
    }
    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupCbSelected() {
        for (int i = 0; i < groupList.size(); i++) {
            MessageBean.DataBean dataBean = groupList.get(i);

            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表是否全部选中
     *
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbSelected(int groupPosition) {
        List<MessageBean.DataBean.ListBean> datasBeen = childList.get(groupPosition);

        for (int i = 0; i < datasBeen.size(); i++) {
            MessageBean.DataBean.ListBean datasBean = datasBeen.get(i);

            if (!datasBean.isChecked()) {
                return false;
            }
        }
        return true;
    }
    /**
     * 计算列表中，选中的钱和数量
     */
    private PriceNumer compute() {
        int count = 0;
        int price = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<MessageBean.DataBean.ListBean> datasBeen = childList.get(i);

            for (int j = 0; j < datasBeen.size(); j++) {
                MessageBean.DataBean.ListBean datasBean = datasBeen.get(j);

                if (datasBean.isChecked()) {
                    price += datasBean.getNum() * datasBean.getPrice();
                    count += datasBean.getNum();
                }
            }
        }
        PriceNumer priceAndCountEvent = new PriceNumer();
        priceAndCountEvent.setNum(count);
        priceAndCountEvent.setPrice(price);
        return priceAndCountEvent;
    }
    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            changGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        EventBus.getDefault().post(compute());
        notifyDataSetChanged();
    }

}
