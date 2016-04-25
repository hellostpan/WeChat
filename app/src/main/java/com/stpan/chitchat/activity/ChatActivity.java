package com.stpan.chitchat.activity;

import android.content.pm.InstrumentationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.stpan.chitchat.R;
import com.stpan.chitchat.adapter.ChatAdapter;
import com.stpan.chitchat.mina.Result;
import com.stpan.chitchat.mina.SessionUtil;
import com.stpan.chitchat.mina.SocketClient;
import com.stpan.chitchat.utils.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 创建时间:2016/4/6 20:29
 * 作者:pst
 */
public class ChatActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private EditText editText;
    private Button button;
    private List<Result> list;
    private ChatAdapter adapter;
    private Gson gson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s){
        Result result = gson.fromJson(s,Result.class);
        if (result!=null){
            list.add(result);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(list.size()-1);
        }

    }

    private void initView(){
        list = new ArrayList<>();
        adapter = new ChatAdapter(this,list);
        gson = GsonUtil.getGson();
        recyclerView = (RecyclerView) findViewById(R.id.rv_act_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.et_act_chat_message);
        button = (Button) findViewById(R.id.btn_act_chat_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString().trim();
                int number = (int) (Math.random()*10%2);
                SessionUtil.getInstance().sendMessage(number+"",message);
            }
        });
    }

}
