package com.sparity.revestarpubnub.app.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparity.revestarpubnub.R;

import java.util.List;

/**
 * Created by Pavan.
 */

@SuppressWarnings("ALL")
public class ConversationRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    // The items to display in your RecyclerView
    private List<ListItem> items;
    private Context mContext;

    private final int DATE = 0, YOU = 1, ME = 2;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ConversationRecyclerView(Context context, List<ListItem> items) {
        this.mContext = context;
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {

        return items.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {

            case ListItem.TYPE_DATE:
                View v1 = inflater.inflate(R.layout.layout_holder_date, viewGroup,
                        false);
                viewHolder = new HolderDate(v1);
                break;

            case ListItem.TYPE_GENERAL:
                View v2 = inflater.inflate(R.layout.row_item_chat, viewGroup, false);
                viewHolder = new HolderMe(v2);
                break;
        }

        return viewHolder;
    }



    public void addItem(ListItem item) {
        items.add(item);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) items.get(position);
                HolderDate vh1 = (HolderDate) viewHolder;
                configureViewHolder1(vh1, dateItem);
                break;
            case ListItem.TYPE_GENERAL:
                ChatItem chatItem = (ChatItem) items.get(position);
                HolderMe vh2 = (HolderMe) viewHolder;
                configureViewHolder2(vh2, chatItem);
                break;
            default:
                break;
        }
    }


    private void configureViewHolder2(HolderMe vh1, ChatItem chatItem) {

        if(chatItem.getChatModel().getType().equalsIgnoreCase("1")) {
            vh1.getTime_out().setText(chatItem.getChatModel().getTime());
            vh1.getChatText_out().setText(chatItem.getChatModel().getMessage());
            vh1.getTime_out().setVisibility(View.VISIBLE);
            vh1.getChatText_out().setVisibility(View.VISIBLE);
            vh1.getTime_in().setVisibility(View.GONE);
            vh1.getChatText_in().setVisibility(View.GONE);
        } else{
            vh1.getTime_in().setVisibility(View.VISIBLE);
            vh1.getChatText_in().setVisibility(View.VISIBLE);
            vh1.getTime_out().setVisibility(View.GONE);
            vh1.getChatText_out().setVisibility(View.GONE);
            vh1.getTime_in().setText(chatItem.getChatModel().getTime());
            vh1.getChatText_in().setText(chatItem.getChatModel().getMessage());
        }



    }
    private void configureViewHolder1(HolderDate vh1, DateItem dateItem) {
        vh1.getDate().setText(dateItem.getDate());
    }


    public void setMessages(List<ListItem> consolidatedList) {
        items.clear();
        items.addAll(consolidatedList);
        notifyDataSetChanged();

    }


}
