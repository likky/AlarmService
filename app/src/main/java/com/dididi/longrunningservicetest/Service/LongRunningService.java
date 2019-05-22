package com.dididi.longrunningservicetest.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;


/**
 * Created by dididi
 * on 17/07/2018 .
 */

public class LongRunningService extends Service {
    public static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //执行具体逻辑操作
            }
        }).start();

        handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            public void run(){
                Toast.makeText(getApplicationContext(), "Service is on!", Toast.LENGTH_LONG).show();
            }
        });
        //获取AlarmManager实例
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //设置延迟执行的时间为一个小时
        //int anHour = 60 * 60 * 1000;
        int seconds = 10 * 1000;
        //触发时间为系统开机至今的时间加上延迟时间
        long triggerAtTime = SystemClock.elapsedRealtime() + seconds;
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        //因为这里传入ELAPSED_REALTIME...所以触发事件为开机至今的时间+延迟执行的时间
        //如果这里传入RTC...触发时间应为1970-1-1至今的时间再加上延迟执行的时间
        //这样就可以时间后台任务定时刷新,不关闭.
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
