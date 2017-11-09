package com.sparity.revestarpubnub.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sparity.revestarpubnub.R;
import com.sparity.revestarpubnub.app.model.ChatListModel;
import com.sparity.revestarpubnub.app.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dytstudio.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private List<ChatListModel> mArrayList;
    private Context mContext;
    private ViewHolder.ClickListener clickListener;
    private int lastPosition = -1;



    public ChatListAdapter(Context context, List<ChatListModel> arrayList, ViewHolder.ClickListener clickListener) {
        this.mArrayList = arrayList;
        this.mContext = context;
        this.clickListener = clickListener;

    }

    // Create new views
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_chat, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView,clickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        viewHolder.itemView.startAnimation(animation);
        lastPosition = position;

        viewHolder.tvName.setText(mArrayList.get(position).getmName());
        viewHolder.tvTime.setText(mArrayList.get(position).getmTime());
        Utils.UILcirclePicLoading( viewHolder.userPhoto,mArrayList.get(position).getmImage(),null,R.drawable.userpic);
        viewHolder.tvLastChat.setText(mArrayList.get(position).getmLastChat());
        viewHolder.tv_chat_count.setText(mArrayList.get(position).getmChatCount());
        if(mArrayList.get(position).getmMedia().equalsIgnoreCase("Video")&&!mArrayList.get(position).getmMedia().equalsIgnoreCase("")){
            viewHolder.iv_media.setBackgroundResource(R.drawable.ic_media_video);
        } else if(mArrayList.get(position).getmMedia().equalsIgnoreCase("Image")&&!mArrayList.get(position).getmMedia().equalsIgnoreCase("")){
            viewHolder.iv_media.setBackgroundResource(R.drawable.ic_media_image);
        } else{
            viewHolder.llMedia.setVisibility(View.INVISIBLE);
        }
        viewHolder.tv_media_name.setText(mArrayList.get(position).getmMedia());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener  {


        @BindView(R.id.rlChat)
        RelativeLayout rlChat;
        @BindView(R.id.tv_user_name)
        TextView tvName;
        @BindView(R.id.tv_time)
         TextView tvTime;
        @BindView(R.id.tv_last_chat)
         TextView tvLastChat;
        @BindView(R.id.iv_user_photo)
         ImageView userPhoto;
        @BindView(R.id.tv_chat_count)
        TextView tv_chat_count;
        @BindView(R.id.iv_media)
        ImageView iv_media;
        @BindView(R.id.tv_media_name)
        TextView tv_media_name;
        @BindView(R.id.llMedia)
        LinearLayout llMedia;
        private ClickListener listener;



        public ViewHolder(View itemLayoutView, ClickListener listener) {
            super(itemLayoutView);

            this.listener = listener;
            ButterKnife.bind(this, itemLayoutView);

            itemLayoutView.setOnClickListener(this);

            itemLayoutView.setOnLongClickListener (this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(getAdapterPosition ());
            }
        }
        @Override
        public boolean onLongClick (View view) {
            if (listener != null) {
                return listener.onItemLongClicked(getAdapterPosition ());
            }
            return false;
        }

        public interface ClickListener {
            public void onItemClicked(int position);

            public boolean onItemLongClicked(int position);

            boolean onCreateOptionsMenu(Menu menu);
        }
    }
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
