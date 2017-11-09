package com.sparity.revestarpubnub.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.sparity.revestarpubnub.R;
import com.sparity.revestarpubnub.app.activity.Conversation;
import com.sparity.revestarpubnub.app.activity.MainActivity;
import com.sparity.revestarpubnub.app.adapters.ChatListAdapter;
import com.sparity.revestarpubnub.app.model.ChatListModel;
import com.sparity.revestarpubnub.app.utils.Constant;
import com.sparity.revestarpubnub.app.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pavan.
 */

public class FragmentHome extends Fragment implements ChatListAdapter.ViewHolder.ClickListener{


   // private RecyclerView mRecyclerView;
    private ChatListAdapter mAdapter;

    @BindView(R.id.recyclerView)
     RecyclerView mRecyclerView;

    public FragmentHome(){
        setHasOptionsMenu(true);
    }
    public void onCreate(Bundle a){
        super.onCreate(a);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null, false);

        ButterKnife.bind(this, view);

        getActivity().supportInvalidateOptionsMenu();
        ((MainActivity)getActivity()).changeTitle(R.id.toolbar, "Messages");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ChatListAdapter(getContext(), Utils.getData(),this);
        mRecyclerView.setAdapter (mAdapter);

        return view;
    }

    @Override
    public void onItemClicked (int position) {
        ChatListModel model=Utils.getData().get(position);
        Intent intent= new Intent(getActivity(),Conversation.class);
        intent.putExtra("data",model);
        intent.putExtra(Constant.CHAT_USERNAME,String.valueOf(2));
        intent.putExtra("channel","");
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClicked (int position) {
        toggleSelection(position);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    private void toggleSelection(int position) {
       // mAdapter.toggleSelection (position);



    }

}
