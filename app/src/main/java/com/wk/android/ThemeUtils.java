package com.wk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class ThemeUtils {
    private Activity context;
    public ThemeUtils(Activity context){
        this.context = context;
    }

    public void initStautarbar(int colorId) {
        int color = context.getResources().getColor(colorId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().setStatusBarColor(color);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            View view = new View(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(context)));
            view.setBackgroundColor(color);
        }
//        ThemeUtils.updateSystemBarContent(context,true);
    }

    public void updateSystemBarContent(boolean isStatus) {
        final View decorView = context.getWindow().getDecorView();
        final int systemUiVisibility = decorView.getSystemUiVisibility();
        if (isStatus) {
//        黑
            decorView.setSystemUiVisibility(systemUiVisibility
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
//        白   颜色根据colorPri 变化
            decorView.setSystemUiVisibility(systemUiVisibility &
                    ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public void hideNav(){
        View decorView = context.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void hideStatus(){
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
