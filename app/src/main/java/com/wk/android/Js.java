package com.wk.android;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class Js {
    private long time;//设置倒计时 X 秒
    private int interval = 1;//设置间隔时间
    private ChjTimerInter chjTimerInter; //回调
    private Timer timer; // 定时器
    private static final int WHAT_REFREH = 0;//刷新
    /**
     * 创建对象则开始计时
     *
     * @param chjTimerInter 接口回调
     */
    public Js(ChjTimerInter chjTimerInter) {
        this.chjTimerInter = chjTimerInter;
    }

    /**
     * 创建对象开始计时
     * @param interval      间隔时间通知(使用第一个方法，默认1秒钟刷新一次)
     * @param chjTimerInter 接口回调
     */
    public Js(int interval, ChjTimerInter chjTimerInter) {
        this.chjTimerInter = chjTimerInter;
        this.interval = interval;
    }

    public void reset(){
        this.time = 0;
        if (chjTimerInter != null) chjTimerInter.second(time);
    }

    /**
     * 开始计时
     */
    public void start() {
        if (timer == null){
            timer = new Timer();
        } else {
            stop();
            return;
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timesss.sendMessage(new Message());
            }
        }, interval);
    }

    /**
     * 终止计时
     */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timesss != null) timesss.removeMessages(WHAT_REFREH);
        if (chjTimerInter != null)chjTimerInter.stop(time);
    }

    @SuppressLint("HandlerLeak")
    private Handler timesss = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != WHAT_REFREH) return;
                time += 1;
            if (chjTimerInter != null) chjTimerInter.second(time);
            if (time == 0) {
                if (timer == null) return;
                timer.cancel();
                timer = null;
                if (chjTimerInter != null) chjTimerInter.expire();
            } else if (time > 0) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timesss.sendMessage(new Message());
                    }
                }, interval);
            }
        }
    };

    /**
     * 接口
     */
    public interface ChjTimerInter {

        /**
         * 间隔时间内回调
         */
        void second(long time);

        /**
         * 完成回调
         */
        void expire();

        /**
         * 终止计时
         */
        void stop(long time);

    }

}
