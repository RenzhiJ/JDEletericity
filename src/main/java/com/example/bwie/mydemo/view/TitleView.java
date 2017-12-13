package com.example.bwie.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class TitleView extends LinearLayout {
    public TitleView(Context context){
        this(context,null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        View.inflate(context,R.layout.top_layout,null);
    }
}
