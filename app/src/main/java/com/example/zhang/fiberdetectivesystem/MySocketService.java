package com.example.zhang.fiberdetectivesystem;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

public class MySocketService extends Service {
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
        }
    }

}
