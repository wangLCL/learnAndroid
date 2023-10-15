package com.wk.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SelfView extends View {

    public SelfView(Context context) {
        super(context);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension();
    }
}
