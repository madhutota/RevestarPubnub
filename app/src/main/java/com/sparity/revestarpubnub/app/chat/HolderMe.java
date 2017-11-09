package com.sparity.revestarpubnub.app.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sparity.revestarpubnub.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Pavan.
 */

public class HolderMe extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_chat_text_in)
     TextView chatText_in;
    @BindView(R.id.tv_time_in)
     TextView time_in;
    @BindView(R.id.tv_time_out)
     TextView time_out;
    @BindView(R.id.tv_chat_text_out)
     TextView chatText_out;

    public HolderMe(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public TextView getTime_in() {
        return time_in;
    }

    public void setTime_in(TextView time_in) {
        this.time_in = time_in;
    }

    public TextView getChatText_in() {
        return chatText_in;
    }

    public void setChatText_in(TextView chatText_in) {
        this.chatText_in = chatText_in;
    }

    public TextView getTime_out() {
        return time_out;
    }

    public void setTime_out(TextView time_out) {
        this.time_out = time_out;
    }

    public TextView getChatText_out() {
        return chatText_out;
    }

    public void setChatText_out(TextView chatText_out) {
        this.chatText_out = chatText_out;
    }

   /* public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public TextView getChatText() {
        return chatText;
    }

    public void setChatText(TextView chatText) {
        this.chatText = chatText;
    }*/
}
