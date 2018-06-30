package com.example.zhang.fiberdetectivesystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //dynamic register filter
        MyDisplayActivityBroadcastReceiver myDisplayActivityBroadcastReceiver = new MyDisplayActivityBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.zhang.fiberdetectivesystem.MyDisplayActivityBroadcastReceiver");
        registerReceiver(myDisplayActivityBroadcastReceiver,intentFilter);/**/


    }

    public class MyDisplayActivityBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getBaseContext(),"Received Socket Broadcast",Toast.LENGTH_LONG).show();
        }
    }
}
