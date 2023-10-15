package com.wk.android.shortcut;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.widget.Toast;

import com.wk.android.MainActivity;
import com.wk.android.R;

public class Guding {
    @SuppressLint("SuspiciousIndentation")
    public void gd(Activity context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
            if (!shortcutManager.isRequestPinShortcutSupported()) return;

                if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                    Intent shortcutInfoIntent = new Intent(context, MainActivity.class);
                    shortcutInfoIntent.setAction(Intent.ACTION_VIEW);

                    ShortcutInfo info = new ShortcutInfo.Builder(context, "id" + 3)
                            .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                            .setShortLabel("xxxxxxxxxxxxxxxxxx").setIntent(shortcutInfoIntent).build();

                    PendingIntent shortcutCallbackIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_IMMUTABLE);
                    shortcutManager.requestPinShortcut(info, shortcutCallbackIntent.getIntentSender());
                }
            } else {
                Toast.makeText(context, "设备不支持在桌面创建快捷图标！", Toast.LENGTH_LONG).show();
            }
    }
}
