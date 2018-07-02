package com.example.zhang.fiberdetectivesystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayActivity extends AppCompatActivity {

    TextView textViewDisplayActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //dynamic register filter
        MyDisplayActivityBroadcastReceiver myDisplayActivityBroadcastReceiver = new MyDisplayActivityBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.zhang.fiberdetectivesystem.MyDisplayActivityBroadcastReceiver");
        registerReceiver(myDisplayActivityBroadcastReceiver,intentFilter);/**/

        textViewDisplayActivity = (TextView) findViewById(R.id.textViewDisplayActivity);

    }

    public class MyDisplayActivityBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getBaseContext(),"Received Socket Broadcast",Toast.LENGTH_LONG).show();
            String stringFromSocketService = intent.getStringExtra("String1");
            String stringDate = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss ", Locale.getDefault()).format(new Date());
            textViewDisplayActivity.setText(stringDate + stringFromSocketService);
        }
    }
}
