package com.example.zhang.fiberdetectivesystem;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class MySocketService extends Service {

    MyTimerTask myTimerTask = null;
    Timer myTimer = null;

    public MySocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //dynamic register filter
        MySocketServiceBroadcastReceiver mySocketServiceBroadcastReceiver = new MySocketServiceBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.zhang.fiberdetectivesystem.MySocketBroadcastReceiver");
        registerReceiver(mySocketServiceBroadcastReceiver,intentFilter);

        //activate a timer myTimerTask period is 20s
        myTimerTask = new MyTimerTask();
        myTimer = new Timer();
        myTimer.schedule(myTimerTask,1000,500);

        Socket socket = new Socket();

        return super.onStartCommand(intent, flags, startId);
    }

    public class MySocketServiceBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            /*try {
                Thread.sleep(8000);
                //send a broadcast
                Intent intent1 = new Intent();
                intent1.setAction("com.example.zhang.fiberdetectivesystem.MyMainActivityBroadcastReceiver");
                sendBroadcast(intent1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //send a broadcast
                    Intent intent1 = new Intent();
                    intent1.setAction("com.example.zhang.fiberdetectivesystem.MyMainActivityBroadcastReceiver");
                    sendBroadcast(intent1);
                }
            },3000);/**/

            //receive the value from SplashActivity broadcast send
            String serverIP = intent.getStringExtra("serverIP");
            String userName = intent.getStringExtra("userName");
            String passWord = intent.getStringExtra("passWord");


        }
    }

    public class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            //send a broadcast to DisplayActivity
            Intent intent1 = new Intent();
            intent1.putExtra("String1","Display Succeed");
            intent1.putExtra("String2","Display failed");
            intent1.setAction("com.example.zhang.fiberdetectivesystem.MyDisplayActivityBroadcastReceiver");
            sendBroadcast(intent1);
        }
    }

}
