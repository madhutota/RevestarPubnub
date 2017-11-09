package com.sparity.revestarpubnub.app.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Pavan
 */


public class ChatModel {
    private String sender;
    private String message;
    private String timestamp;
    private String Type;
    private String time;

    public ChatModel(Object jobject, String username) throws JSONException {
        JSONObject obj = (JSONObject) jobject;

        if (obj != null) {

            if (obj.has("sender"))
                setSender(obj.getString("sender"));
            if (obj.has("message"))
                setMessage(obj.getString("message"));
            if (obj.has("timestamp")) {
                setTimestamp(formatDate(obj.getString("timestamp")));
            }
            if (obj.has("timestamp")) {
                setTime(formatTime(obj.getString("timestamp")));

            }

            if (sender.equalsIgnoreCase(username)) {
                this.setType("1");
            } else {
                this.setType("2");
            }
        }


    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String formatDate(String timeStamp) {
        Timestamp timestamp = new Timestamp(Long.parseLong(timeStamp));
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }

    private static String formatTime(String timeStamp) {
        Timestamp timestamp = new Timestamp(Long.parseLong(timeStamp));
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        return simpleDateFormat.format(date);
    }
}
