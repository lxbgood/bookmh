package com.book.dfapp.util;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

/**
 * Created by sunpeng on 16/9/9 18:36
 */
public class CountDownTask {

    private Timer timer;
    private int count;
    private CountDownCallBack callBack;

    private CountDownTask() {
    }

    public CountDownTask(CountDownCallBack callBack) {
        if (callBack == null) throw new NullPointerException("CountDownCallBack can not be null");
        this.callBack = callBack;
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                stop();
                callBack.onCountDownOver();
            } else {
                callBack.onCounting(msg.what);
            }
        }
    };

    /**
     * @param expireTime 计时时长（秒）
     */
    public void start(int expireTime) {
        if (expireTime < 1) {
            return;
        }
        this.count = expireTime;
        stop();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(count);
                if (count > 0) {
                    count--;
                }
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public interface CountDownCallBack {
        void onCounting(int currentCount);

        void onCountDownOver();
    }
}
