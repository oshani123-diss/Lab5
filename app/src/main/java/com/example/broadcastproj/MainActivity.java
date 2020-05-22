package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public final static String BROADCAST_ACTION = "android.intent.action.MAIN";
    Receiver localListener;
    TextView txtViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewMsg = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentfilter =new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener, intentfilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public  void onClick(View view){
        if(view.getId() == R.id.button){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = txtViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived "+ message;
            txtViewMsg.setText(currentText);
        }
    }
}


