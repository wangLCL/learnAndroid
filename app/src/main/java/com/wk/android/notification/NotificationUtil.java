package com.wk.android.notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.wk.android.MainActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NotificationUtil {
    private static boolean before12 = false;
    public static String[] active = {
            getEmo(0x1F5E3)+"Knock knock! Here is Art Puzzle with lots of fun!"+getEmo(0x1F334),
            getEmo(0x2615)+"Take a break! Your unfinished puzzle is waiting for you!",
            getEmo(0x1F9E9)+"Put puzzle pieces together and discover a new story!"+getEmo(0x1F50D),
            "Solve new puzzles and have a relaxing time!"+getEmo(0x1F3A1)
    };

    public static String[] inActive = {
            getEmo(0x2B50) + "Come back and play with fun"+getEmo(0x2B50),
    };

    private static String getEmo(int code) {
//        char[] chars = Character.toChars(code);
//        return new String(chars);

        char[] chars = Character.toChars(code);
        String emojiString = new String(chars);
        return emojiString;
    }

    /**
     * 6次推送
     * 1.第一次为活跃的
     * 2，3，4是不活跃的   再第一类中找
     * 5，6 为不活跃       第二类中找
     *
     * @param random
     * @param index
     * @return
     */
    public static String appendTextRule(Random random, int index) {
        if (index == 0) {
            return active[random.nextInt(active.length)];
        } else {
            if (index<4){
                return active[random.nextInt(4)];
            }else {
                return inActive[random.nextInt(1)];
            }
        }
    }

    static long todayZero(int nowHour) {
        Calendar day = Calendar.getInstance();
        Date date = new Date();
        day.setTime(date);
        int hour = day.get(Calendar.HOUR_OF_DAY);
        if (hour >= nowHour) {
            before12 = false;
            day.set(Calendar.HOUR_OF_DAY, 24);
        } else {
            before12 = true;
            day.set(Calendar.HOUR_OF_DAY, 0);
        }
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime().getTime();
    }

    static int[] days = {
            0, 1, 2, 3, 6, 13
    };

    public static void add(Context context) {
        try {
            Random random = new Random();

            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            {
                long time_zero = todayZero(12);
                int dayOffset = 0;
                if (!before12) {
                    dayOffset = 1;
                }
                long time_ms = time_zero + 12 * 60L * 60L * 1000L + 30L * 60L * 1000L;
                Intent intent = new Intent(context, MYReceiver.class).setAction(MainActivity.class.getName());
                intent.putExtra("id", 0);
                intent.putExtra("text", appendTextRule(random, 0));
                PendingIntent pendingIntent ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE);
                }else{
                    pendingIntent = PendingIntent.getBroadcast(context, 0,
                            intent, PendingIntent.FLAG_UPDATE_CURRENT);
                }
                am.set(AlarmManager.RTC_WAKEUP, time_ms, pendingIntent);
                Log.i("Notification", "addNotification" + 0 + "     " + time_ms);
            }
            long time_zero = todayZero(12);
            for (int i = 1; i < 6; i++) {  //6次推送  0 1 2 3 4 5
                long time_ms = time_zero + ((days[i]) * 24 + 12) * 60L * 60L * 1000L + 30 * 60L * 1000L;
                Intent intent = new Intent(context, MYReceiver.class).setAction(MainActivity.class.getName());
                intent.putExtra("id", i);
                intent.putExtra("text", appendTextRule(random, i));
                PendingIntent pendingIntent ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    pendingIntent = PendingIntent.getBroadcast(context, i, intent, PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE);
                }else{
                    pendingIntent = PendingIntent.getBroadcast(context, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                }
                am.set(AlarmManager.RTC_WAKEUP, time_ms, pendingIntent);
                Log.i("Notification", "addNotification" + i + "     " + time_ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelAll(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < 6; i++) {
                alarmManager.cancel(makePendingIntent(context, i, PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE));
            }
        }else{
            for (int i = 0; i < 6; i++) {
                alarmManager.cancel(makePendingIntent(context, i, PendingIntent.FLAG_UPDATE_CURRENT));
            }
        }

    }

    public static PendingIntent makePendingIntent(Context context, int id, int flag) {
        return PendingIntent.getBroadcast(context, id,
                new Intent(context, MYReceiver.class).setAction(MainActivity.class.getName()).putExtra("id", id),
                flag);
    }
}
