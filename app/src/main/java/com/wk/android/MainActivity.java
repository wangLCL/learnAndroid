package com.wk.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wk.android.dialog.MySetting;
import com.wk.android.dialog.RestDialog;
import com.wk.android.dialog.SettingDialog;
import com.wk.android.notification.NotificationUtil;
import com.wk.android.permission.TonyPermission;

public class MainActivity extends AppCompatActivity implements Js.ChjTimerInter {
    private TextView hour;
    private TextView minutes;
    private TextView seconds;
    private Js js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeUtils themeUtils = new ThemeUtils(this);
        themeUtils.initStautarbar(R.color.bg_color);
        themeUtils.updateSystemBarContent(false);
        themeUtils.hideNav();
        hour = findViewById(R.id.hour);
        minutes = findViewById(R.id.minutes);
        seconds = findViewById(R.id.seconds);

        //接受通知
        TonyPermission permission = new TonyPermission(this);
        permission.requestNotificationPermission();

//        show3();
        View resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                js.reset();
                RestDialog dialog = RestDialog.newInstance();
                dialog.show(getSupportFragmentManager(),"");
                dialog.setRestListener(new Runnable() {
                    @Override
                    public void run() {
                        js.reset();
                    }
                });
            }
        });
        View bg = findViewById(R.id.bg);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                js.start();
            }
        });

        js= new Js(this);
    }

    public void start(){

    }

    private void show3() {
        MySetting mySetting = MySetting.newInstance();
        mySetting.show(getSupportFragmentManager(),"test");
    }

    public void showDialog1(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.ic_launcher_background);
        alert.setTitle("弹窗1");
        alert.setMessage("一个弹窗");
        alert.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        alert.show();
        // 显示

    }

    @Override
    public void second(long time) {
//        tiems.setText(time + "");
        long xx = time;
        long secondsV = (xx) % 60;
        long minutesV = (xx / (60)) % 60;
        long hoursV = (xx / (60 * 60));
        if (hoursV<=9){
            hour.setText("0"+hoursV);
        }else {
            hour.setText(hoursV+"");
        }
        if (minutesV<=9){
            minutes.setText("0"+minutesV);
        }else {
            minutes.setText(minutesV+"");
        }
        if (secondsV<=9){
            seconds.setText("0"+secondsV+"");
        }else {
            seconds.setText(secondsV+"");
        }
    }

    @Override
    public void expire() {
//        timnew.setText("计时完成");
    }

    @Override
    public void stop(long time) {
//        timnew.setText("计时终止" + time);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationUtil.cancelAll(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NotificationUtil.add(this);
    }
}