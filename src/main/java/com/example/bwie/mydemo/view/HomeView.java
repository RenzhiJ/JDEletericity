package com.example.bwie.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;



public class HomeView extends RecyclerView {
    public HomeView(Context context) {
        super(context);
    }

    public HomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

    }
}
