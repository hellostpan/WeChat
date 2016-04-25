package com.stpan.chitchat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.stpan.chitchat.MyApplication;
import com.stpan.chitchat.R;
import com.stpan.chitchat.mina.Result;
import com.stpan.chitchat.mina.SessionUtil;
import com.stpan.chitchat.mina.SocketClient;
import com.stpan.chitchat.service.MessageService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        startActivity(new Intent(this,ChatActivity.class));
        startService(new Intent(this, MessageService.class));
        MyApplication.getInstance().setUserId("123456");
    }
}
