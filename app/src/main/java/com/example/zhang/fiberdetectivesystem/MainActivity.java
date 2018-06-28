package com.example.zhang.fiberdetectivesystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dynamic register filter
        MyMainActivityBroadcastReceiver myMainActivityBroadcastReceiver = new MyMainActivityBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.zhang.fiberdetectivesystem.MyMainActivityBroadcastReceiver");
        registerReceiver(myMainActivityBroadcastReceiver,intentFilter);/**/

        sharedPreferences = this.getSharedPreferences("SettingFile",MODE_PRIVATE);
        String serverIP = sharedPreferences.getString("ServerIP","110.249.128.24");
        String userName = sharedPreferences.getString("userName","ZNQ");
        String passWord = sharedPreferences.getString("passWord","");

        EditText editTextServerAddress = (EditText) findViewById(R.id.editTextServerAddress);
        EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        editTextServerAddress.setText(serverIP);
        editTextUserName.setText(userName);
        editTextPassword.setText(passWord);


        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);

        /*//send a broadcast
        Intent intent1 = new Intent();
        intent1.setAction("com.example.zhang.fiberdetectivesystem.MySocketBroadcastReceiver");
        sendBroadcast(intent1);*/


    }

    public class MyMainActivityBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(getBaseContext(),"Received Broadcast",Toast.LENGTH_LONG).show();
        }
    }

}
