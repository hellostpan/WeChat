package com.stpan.chitchat.mina;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionInitializer;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by STPAN on 2016/4/3.
 */
public class MyHandler extends IoHandlerAdapter {
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
        SessionUtil.getInstance().setIoSession(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
        Result result = new Result();
        result.setFrom("1");
        session.write(new Gson().toJson(result));
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
        if (session!=null){
            session.closeNow();
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught: "+session.getId());
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String s = (String) message;
        System.out.println("messageReceived: "+s);
        EventBus.getDefault().post(s);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent: "+String.valueOf(message));
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        System.out.println("inputClosed");
        session.closeNow();
    }
}
