package com.wk.android.notification;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.wk.android.MainActivity;
import com.wk.android.R;

//推送  更改图片
public class MYReceiver extends BroadcastReceiver {
    String channelID = "my_channel_01";
    String channelName = "androidLearn";
    static String ColorStr = "#53c95b";

    @Override
    @SuppressLint("WrongConstant")
    public void onReceive(Context context, Intent intent) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            String title = context.getResources().getString(R.string.app_name);
            int id = intent.getIntExtra("id", 0);
            String content = intent.getStringExtra("text");
            if (id == 0) {
                //活跃
                content = intent.getStringExtra("text");
            } else {
                //不活跃
                if (id == 4 || id == 5) {
                    String[] split = content.split("!");
                    if (split.length == 2) {
                        title = split[0] + "!";
                        content = split[1] + "!";
                    } else {
                        content = intent.getStringExtra("text");
                    }
                } else {
                    content = intent.getStringExtra("text");
                }
            }
            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT |
                        PendingIntent.FLAG_IMMUTABLE);
            } else {
                contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel mChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(mChannel);
                Notification.Builder builder1 = new Notification.Builder(context, channelID);
                Drawable drawable = context.getApplicationInfo().loadIcon(context.getPackageManager());
                Bitmap bitmap = getBitmapFromDrawable(drawable);
                builder1.setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(bitmap)
                        .setContentIntent(contentIntent)
                        .setContentTitle(title)
                        .setContentText(content);
                notificationManager.notify(id, builder1.build());
            } else if (Build.VERSION.SDK_INT >= 11) {
                androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(context);
                Drawable drawable = context.getApplicationInfo().loadIcon(context.getPackageManager());
                Bitmap bitmap = getBitmapFromDrawable(drawable);
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(bitmap)
                        .setContentIntent(contentIntent)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setColor(Color.parseColor(ColorStr));
                notificationManager.notify(id, builder.build());
            }
        }catch (Exception e){

        }
    }


    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }


}
