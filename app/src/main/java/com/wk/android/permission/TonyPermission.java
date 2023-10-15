package com.wk.android.permission;

import android.Manifest;
import android.app.Activity;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TonyPermission {
    private Activity context;
    public TonyPermission(Activity context){
        this.context = context;
    }

    public void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT < 33 || hasNotificationPermission(Manifest.permission.POST_NOTIFICATIONS)) {
            return;
        }
        ActivityCompat.requestPermissions(context, new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
    }


    public void requestShortCutPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || hasNotificationPermission(Manifest.permission.INSTALL_SHORTCUT)) {
            return;
        }
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.INSTALL_SHORTCUT}, 102);
    }

    public  boolean hasNotificationPermission(String permission) {
        try {
            return ContextCompat.checkSelfPermission(context, permission) == 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
