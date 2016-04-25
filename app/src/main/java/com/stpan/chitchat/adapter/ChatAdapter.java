package com.stpan.chitchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stpan.chitchat.R;
import com.stpan.chitchat.mina.Result;

import java.util.List;
import java.util.zip.Inflater;

/**
 * 功能：
 * 创建时间:2016/4/6 20:57
 * 作者:pst
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHolder> {

    private List<Result> list;
    private Context context;

    public ChatAdapter(Context context, List<Result> list) {
        this.list = list;
        this.context = context;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Result result = list.get(position);
        if ("1".equals(result.getTo())) {
            holder.getRl_right().setVisibility(View.GONE);
            holder.getLl_left().setVisibility(View.VISIBLE);
            holder.getTextView_left().setText(result.getMessage());
        } else {
            holder.getLl_left().setVisibility(View.GONE);
            holder.getRl_right().setVisibility(View.VISIBLE);
            holder.getTextView_right().setText(result.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_left;
        private TextView textView_left;
        private RelativeLayout rl_right;
        private TextView textView_right;

        private MyHolder(View itemView) {
            super(itemView);
            ll_left = (LinearLayout) itemView.findViewById(R.id.ll_item_left);
            rl_right = (RelativeLayout) itemView.findViewById(R.id.rl_item_right);
            textView_left = (TextView) itemView.findViewById(R.id.tv_ada_left_chat_message);
            textView_right = (TextView) itemView.findViewById(R.id.tv_ada_right_chat_message);
        }

        public LinearLayout getLl_left() {
            return ll_left;
        }

        public TextView getTextView_left() {
            return textView_left;
        }

        public RelativeLayout getRl_right() {
            return rl_right;
        }

        public TextView getTextView_right() {
            return textView_right;
        }
    }
}
