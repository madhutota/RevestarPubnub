package com.sparity.revestarpubnub.app.chat;

import com.sparity.revestarpubnub.app.model.ChatModel;

/**
 * Created by Pavan
 */

public class ChatItem extends ListItem {

    private ChatModel chatModel;

    public ChatModel getChatModel() {
        return chatModel;
    }

    public void setChatModel(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
