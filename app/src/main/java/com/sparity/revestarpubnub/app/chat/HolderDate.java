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

public class HolderDate extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_date)
     TextView date;

    public HolderDate(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }
}
