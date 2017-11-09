package com.sparity.revestarpubnub.app.model;

import java.io.Serializable;

/**
 * Created by Pavan.
 */

public class ChatListModel implements Serializable{

    private String mChatId;
    private String mName;
    private String mLastChat;
    private String mTime;
    private String mImage;
    private String mChatCount;
    private String mMedia;


    public ChatListModel(String mChatId, String mName, String mLastChat, String mTime, String mImage, String mChatCount,String mMedia) {
        this.mChatId = mChatId;
        this.mName = mName;
        this.mLastChat = mLastChat;
        this.mTime = mTime;
        this.mImage = mImage;
        this.mChatCount=mChatCount;
        this.mMedia=mMedia;
    }

    public String getmChatId() {
        return mChatId;
    }

    public void setmChatId(String mChatId) {
        this.mChatId = mChatId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmLastChat() {
        return mLastChat;
    }

    public void setmLastChat(String mLastChat) {
        this.mLastChat = mLastChat;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmChatCount() {
        return mChatCount;
    }

    public void setmChatCount(String mChatCount) {
        this.mChatCount = mChatCount;
    }

    public String getmMedia() {
        return mMedia;
    }

    public void setmMedia(String mMedia) {
        this.mMedia = mMedia;
    }
}