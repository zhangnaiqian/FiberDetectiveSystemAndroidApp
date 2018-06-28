package com.example.zhang.fiberdetectivesystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT =  8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean v = requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        //active service
        Intent intent = new Intent();
        intent.setClass(SplashActivity.this,MySocketService.class);
        startService(intent);

        initUI();
        getVersionName();


        //post delay to splash activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                //send a broadcast
                Intent intent1 = new Intent();
                intent1.setAction("com.example.zhang.fiberdetectivesystem.MySocketBroadcastReceiver");
                sendBroadcast(intent1);
            }
        }, SPLASH_DISPLAY_LENGHT);


    }

    //get the version name
    private String getVersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    //init the UI of VersionName Text View
    private void initUI() {
        TextView textViewVersionName = (TextView) findViewById(R.id.textViewVersionName);
        textViewVersionName.setText("Version Name:" + getVersionName());
    }

    public class MySplashActivityBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

}
