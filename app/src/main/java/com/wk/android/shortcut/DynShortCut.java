package com.wk.android.shortcut;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;

import com.wk.android.MainActivity;
import com.wk.android.R;

import java.util.Arrays;

public class DynShortCut {
    public void addShortCuts(Activity activity) {
        //判断sdk版本，高于7.1添加shortcut才生效
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            return;
        }
        //①、创建动态快捷方式的第一步，创建ShortcutManager
        ShortcutManager scManager = activity.getSystemService(ShortcutManager.class);
        //②、构建动态快捷方式的详细信息
//        ShortcutInfo scInfoOne  = new ShortcutInfo.Builder(activity, "dynamic_one")
//                .setShortLabel("Dynamic Web site")
//                .setLongLabel("to open Dynamic Web Site")
//                .setIcon(Icon.createWithResource(activity, R.mipmap.ic_launcher))
//                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")))
//                .build();
        ShortcutInfo scInfoTwo = new ShortcutInfo.Builder(activity, "dynamic_two")
                .setShortLabel("Dynamic Activity")
                .setLongLabel("to open dynamic one activity")
                .setIcon(Icon.createWithResource(activity, R.mipmap.ic_launcher))
                .setIntents(new Intent[]{
                        new Intent(Intent.ACTION_MAIN, Uri.EMPTY, activity, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),//加该FLAG的目的是让MainActivity作为根activity，清空已有的任务
                })
                .build();
        //③、为ShortcutManager设置动态快捷方式集合
        scManager.setDynamicShortcuts(Arrays.asList(scInfoTwo));
        //如果想为两个动态快捷方式进行排序，可执行下面的代码
        ShortcutInfo dynamicWebShortcut = new ShortcutInfo.Builder(activity, "dynamic_one")
                .setRank(0)
                .build();
        ShortcutInfo dynamicActivityShortcut = new ShortcutInfo.Builder(activity, "dynamic_two")
                .setRank(1)
                .build();
        //④、更新快捷方式集合
        scManager.updateShortcuts(Arrays.asList(dynamicWebShortcut, dynamicActivityShortcut));
    }
}
