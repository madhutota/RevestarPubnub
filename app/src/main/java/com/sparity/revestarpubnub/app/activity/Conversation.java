package com.sparity.revestarpubnub.app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsAddChannelResult;
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsAllChannelsResult;
import com.pubnub.api.models.consumer.history.PNHistoryResult;
import com.pubnub.api.models.consumer.presence.PNGetStateResult;
import com.pubnub.api.models.consumer.presence.PNHereNowChannelData;
import com.pubnub.api.models.consumer.presence.PNHereNowOccupantData;
import com.pubnub.api.models.consumer.presence.PNHereNowResult;
import com.pubnub.api.models.consumer.presence.PNSetStateResult;
import com.sparity.revestarpubnub.R;
import com.sparity.revestarpubnub.app.callback.PubSubPnCallback;
import com.sparity.revestarpubnub.app.chat.ConversationRecyclerView;
import com.sparity.revestarpubnub.app.chat.ListItem;
import com.sparity.revestarpubnub.app.model.ChatListModel;
import com.sparity.revestarpubnub.app.model.ChatModel;
import com.sparity.revestarpubnub.app.utils.Constant;
import com.sparity.revestarpubnub.app.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Conversation extends BaseActivity implements View.OnClickListener {



    @BindView(R.id.recyclerView)
     RecyclerView mRecyclerView;
    @BindView(R.id.et_message)
     EditText text;
    @BindView(R.id.bt_send)
     Button send;
    @BindView(R.id.bt_attachment)
    ImageButton bt_attachment;

    @BindView(R.id.tv_isTyping)
    TextView tv_isTyping;



    private ConversationRecyclerView mAdapter;
    private PNConfiguration pnConfiguration;
    private PubNub mPubNub;
    private String username;
    private String channel  = "";
    private PubSubPnCallback mPubSubPnCallback;
    private String loggedUserId="";
    private String selectedUserId="";
    ChatListModel model;
    Map<String , List<ChatModel>> events;
    List<ChatModel> chatModels= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            model = (ChatListModel) bundle.getSerializable("data");
            loggedUserId = bundle.getString(Constant.CHAT_USERNAME);
        }

        ButterKnife.bind(this);

        setupToolbarWithUpNav(R.id.toolbar, model.getmName(), model.getmImage());

        SharedPreferences preference = getSharedPreferences(loggedUserId, Context.MODE_PRIVATE);
        channel = preference.getString("channel", channel);


        username = loggedUserId;
        assert model != null;
        selectedUserId = model.getmChatId();


        initPubNub();
        mAdapter = new ConversationRecyclerView(this, new ArrayList<ListItem>());
        mRecyclerView.setAdapter(mAdapter);
        if (mRecyclerView.getAdapter().getItemCount() > 0) {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());
                }
            }, 500);
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bt_attachment.setOnClickListener(this);


        this.mPubSubPnCallback = new PubSubPnCallback(this.mAdapter,username,chatModels);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount() );
                    }
                }, 500);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!text.getText().toString().equalsIgnoreCase(""))
                    sendMessage();
                else
                    Toast.makeText(Conversation.this,"Enter text",Toast.LENGTH_SHORT).show();
            }
        });
        text.addTextChangedListener(textWatcher);
    }



    private void initPubNub(){
        pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(Constant.SUBSCRIBE_KEY);
        pnConfiguration.setPublishKey(Constant.PUBLISH_KEY);
        pnConfiguration.setUuid(this.username) ;
        pnConfiguration.setSecure(true);

        mPubNub = new PubNub(pnConfiguration);



        if (channel.equals("")) {
            listAllChannelsInGroup(loggedUserId);
        } else {
            history();

            subscribeWithPresence();
        }

        //setStateLogin();

    }

    public void sendMessage(){

        final Map<?, ?> message = ImmutableMap.of("sender", Conversation.this.loggedUserId, "message", text.getText().toString(), "timestamp", System.currentTimeMillis());

        Conversation.this.mPubNub.publish().channel(channel).message(message).shouldStore(true).async(
                new PNCallback<PNPublishResult>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status) {
                        try {
                            if (!status.isError()) {
                                text.setText("");
                                mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());
                                text.addTextChangedListener(textWatcher);


                            } else {
                                //set your error message
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
    private void history() {

        Conversation.this.mPubNub.history()
                .channel(channel) // where to fetch history from
                .count(100) // how many items to fetch
                .reverse(true)
                .includeTimetoken(true)
                .async(new PNCallback<PNHistoryResult>() {
                    @Override
                    public void onResponse(PNHistoryResult result, PNStatus status) {
                        try {
                            Object resultList=result.getMessages();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ChatModel>>() {}.getType();
                            String json = gson.toJson(resultList, type);
                            JSONArray jsonObj = new JSONArray(json);
                            System.out.println(jsonObj);



                            for (int i=0;i< jsonObj.length();i++) {
                                JSONObject obj=jsonObj.getJSONObject(i);

                                JSONObject obj1=obj.getJSONObject("entry");

                                ChatModel poj= new ChatModel(obj1,username);
                                chatModels.add(poj);
                                events = Utils.toMap(chatModels);


                            }
                            /*if(events.size()>0) {

                                for (String date : events.keySet()) {
                                    DateItem dateItem = new DateItem();
                                    dateItem.setDate(date);
                                    consolidatedList.add(dateItem);

                                    for (ChatModel chatModel : events.get(date)) {
                                        ChatItem chatItem = new ChatItem();
                                        chatItem.setChatModel(chatModel);
                                        consolidatedList.add(chatItem);
                                    }


                                }
                            }*/
                            Conversation.this.mAdapter.setMessages(Utils.getChatListMessages(events));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    private void subscribeWithPresence() {
        this.mPubNub.addListener(this.mPubSubPnCallback);
        this.mPubNub.subscribe().channels(Arrays.asList(channel)).withPresence().execute();

    }

    private void hereNow(boolean b) {


        mPubNub.hereNow()
                .channels(Arrays.asList(channel))
                .includeUUIDs(true)
                .async(new PNCallback<PNHereNowResult>() {
                    @Override
                    public void onResponse(PNHereNowResult result, PNStatus status) {
                        if (status.isError()) {
                            // handle error
                            return;
                        }

                        for (PNHereNowChannelData channelData : result.getChannels().values()) {
                            System.out.println("---");
                            System.out.println("channel:" + channelData.getChannelName());
                            System.out.println("occupancy: " + channelData.getOccupancy());
                            System.out.println("occupants:");
                            for (PNHereNowOccupantData occupant : channelData.getOccupants()) {
                                System.out.println("uuid: " + occupant.getUuid() + " state: " + occupant.getState());
                            }
                        }
                    }
                });

    }

    private void getState() {

        mPubNub.getPresenceState()
                .channels(Arrays.asList(channel)) // channels to fetch state for
                .uuid(this.username) // uuid of user to fetch, or omit own uuid
                .async(new PNCallback<PNGetStateResult>() {
                    @Override
                    public void onResponse(PNGetStateResult result, PNStatus status) {

                        Log.i("Hello","State"+result.getStateByUUID().toString())      ;
                        // handle me
                    }
                });

    }

    private void listAllChannelsInGroup(String user){

        mPubNub.listChannelsForChannelGroup()
                .channelGroup(user)
                .async(new PNCallback<PNChannelGroupsAllChannelsResult>() {
                    @Override
                    public void onResponse(PNChannelGroupsAllChannelsResult result, PNStatus status) {
                        String existedChannel = parseChannels(result);
                        checkChannelExist(existedChannel);

                    }
                });

    }

    private String parseChannels(Object response) {
        String existedChannelName = "";
        try {
            Gson gson = new Gson();
            String json = gson.toJson(response);
            JSONObject mainObject = new JSONObject(json);

            JSONArray channelArray = mainObject.getJSONArray("channels");
            for (int j = 0; j < channelArray.length(); j++) {
                String channel = channelArray.getString(j);
                if (channel.contains("_")) {
                    String users[] = channel.split("_");

                    if ((users[0].equals(loggedUserId) && users[1].equals(selectedUserId)) || (users[0].equals(selectedUserId) && users[1].equals(loggedUserId))) {
                        existedChannelName = channel;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return existedChannelName;
    }

    private void subscribeSelectedUser() {

        PNConfiguration    pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(Constant.SUBSCRIBE_KEY);
        pnConfiguration.setPublishKey(Constant.PUBLISH_KEY);
        pnConfiguration.setUuid(selectedUserId) ;
        pnConfiguration.setSecure(true);

        new PubNub(pnConfiguration);
        try {

            addChannelTOChannelGroup(selectedUserId);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }


    }

    private void addChannelTOChannelGroup(String selectedUserId) {
        mPubNub.addChannelsToChannelGroup()
                .channelGroup(selectedUserId)
                .channels(Arrays.asList(channel))
                .async(new PNCallback<PNChannelGroupsAddChannelResult>() {
                    @Override
                    public void onResponse(PNChannelGroupsAddChannelResult result, PNStatus status) {

                    }
                });
    }
    private void checkChannelExist(String existedChannel){

        if (!existedChannel.equalsIgnoreCase("")) {
            channel = existedChannel;
            history();

        } else {
            channel = loggedUserId + "_" + selectedUserId;
            addChannelTOChannelGroup(loggedUserId);
            addChannelTOChannelGroup(selectedUserId);

        }


        subscribeWithPresence();

    }


    private void setStateLogin(){


        JsonObject state = new JsonObject();
        state.addProperty(Constant.STATE_LOGIN, System.currentTimeMillis());
        state.addProperty(Constant.STATUS, Constant.STATUS_ONLINE);


        mPubNub.setPresenceState()
                .channelGroups(Arrays.asList(channel)) // apply on those channel groups
                .state(state) // the new state
                .async(new PNCallback<PNSetStateResult>() {
                    @Override
                    public void onResponse(PNSetStateResult result, PNStatus status) {
                        // on new state for those channels

                        Log.i("Hello","State"+result.getState().toString());
                        getState();
                    }
                });
    }

    public void setStateLoginOffiline() {

        JsonObject state = new JsonObject();
        state.addProperty(Constant.STATE_LOGIN, System.currentTimeMillis());
        state.addProperty(Constant.STATUS, Constant.STATUS_OFFLINE);


        mPubNub.setPresenceState()
                .channelGroups(Arrays.asList(channel)) // apply on those channel groups
                .state(state) // the new state
                .async(new PNCallback<PNSetStateResult>() {
                    @Override
                    public void onResponse(PNSetStateResult result, PNStatus status) {
                        // on new state for those channels
                    }
                });
    }







    @Override
    public void onClick(View view) {



    }
    int lenghtAfterType;
    int lenghtBeforeType;
    TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            lenghtBeforeType = text.getText().toString().length();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            lenghtAfterType = text.getText().toString().length();
            tv_isTyping.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        if((lenghtAfterType-lenghtBeforeType)<0){
                            tv_isTyping.setText("");
                        }else{
                            tv_isTyping.setText("isTyping...");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 500)  ;
        }
    }  ;
}
