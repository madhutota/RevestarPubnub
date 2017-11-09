package com.sparity.revestarpubnub.app.callback;

import com.google.gson.Gson;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.sparity.revestarpubnub.app.chat.ConversationRecyclerView;
import com.sparity.revestarpubnub.app.model.ChatModel;
import com.sparity.revestarpubnub.app.utils.Utils;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class PubSubPnCallback extends SubscribeCallback {
    private static final String TAG = PubSubPnCallback.class.getName();
    private final ConversationRecyclerView pubSubListAdapter;
    private String username="";
    private List<ChatModel> chatModels;

    public PubSubPnCallback(ConversationRecyclerView pubSubListAdapter, String username, List<ChatModel> chatModels) {
        this.pubSubListAdapter = pubSubListAdapter;
        this.username=username;
        this.chatModels=chatModels;
    }

    @Override
    public void status(PubNub pubnub, PNStatus status) {
        /*
        switch (status.getCategory()) {
             // for common cases to handle, see: https://www.pubnub.com/docs/java/pubnub-java-sdk-v4
             case PNStatusCategory.PNConnectedCategory:
             case PNStatusCategory.PNUnexpectedDisconnectCategory:
             case PNStatusCategory.PNReconnectedCategory:
             case PNStatusCategory.PNDecryptionErrorCategory:
         }
        */

        // no status handling for simplicity
    }

    @Override
    public void message(PubNub pubnub, PNMessageResult message) {
        try {


            Gson gson = new Gson();
            String json = gson.toJson(message);
            JSONObject obj=new JSONObject(json)  ;
            JSONObject obj1=obj.getJSONObject("message");

            ChatModel dsMsg = new ChatModel(obj1,username);
            /*ChatItem item= new ChatItem();
            item.setChatModel(dsMsg);*/

            chatModels.add(dsMsg);
            Map<String, List<ChatModel>> events = Utils.toMap(chatModels);
            this.pubSubListAdapter.setMessages(Utils.getChatListMessages(events));
            //this.pubSubListAdapter.addItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void presence(PubNub pubnub, PNPresenceEventResult presence) {

    }
}
