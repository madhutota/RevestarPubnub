package com.sparity.revestarpubnub.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sparity.revestarpubnub.app.chat.ChatItem;
import com.sparity.revestarpubnub.app.chat.DateItem;
import com.sparity.revestarpubnub.app.chat.ListItem;
import com.sparity.revestarpubnub.app.model.ChatListModel;
import com.sparity.revestarpubnub.app.model.ChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Pavan
 */

public class Utils {

    public static Typeface setProximaNovaReguler(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Nunito_Regular.ttf");
    }

    public static Typeface setProximaNovaSemiBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Nunito_Light.ttf");
    }




    public static ArrayList<ChatListModel> getData(){

        ArrayList<ChatListModel> model= new ArrayList<>();


                //String mChatId, String mName, String mLastChat, String mTime, String mImage,String mChatCount
        ChatListModel model1=new ChatListModel("1","Sravan","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","12.00 AM","https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg","10","Video");
        ChatListModel model2=new ChatListModel("100","Pavan","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","1.45PM","https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg","5","Image");
        ChatListModel model3=new ChatListModel("3","Vidya","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","Yesterday","https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s1024/Another%252520Rockaway%252520Sunset.jpg","2","");
        ChatListModel model4=new ChatListModel("4","Madhu","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","20/01/2017","https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s1024/Antelope%252520Butte.jpg","1","Image");
        ChatListModel model5=new ChatListModel("5","Haseen","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","27/01/2017","https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg","35","");
        ChatListModel model6=new ChatListModel("6","Anil","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","31/01/2017","https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s1024/Apre%2525CC%252580s%252520la%252520Pluie.jpg","100","Video");
        ChatListModel model7=new ChatListModel("7","Shanker","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","10/02/2017","https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s1024/Antelope%252520Walls.jpg","2","Image");
        ChatListModel model8=new ChatListModel("8","Mani","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","15/02/2017","https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s1024/Backlit%252520Cloud.jpg","7","Video");
        ChatListModel model9=new ChatListModel("9","Testing","Nulla lectus velit massa, in ut massa dui, rm non eros dictum auctor...","15/02/2017","https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s1024/Backlit%252520Cloud.jpg","7","Video");

        model.add(model1);
        model.add(model2);
        model.add(model3);
        model.add(model4);
        model.add(model5);
        model.add(model6);
        model.add(model7);
        model.add(model8);
        model.add(model9);

        model.add(model1);
        model.add(model2);
        model.add(model3);
        model.add(model4);
        model.add(model5);
        model.add(model6);
        model.add(model7);
        model.add(model8);
        model.add(model9);




        return model;
    }

    /**
     * UNIVERSAL IMAGE LOADER
     * <p>
     * to load image uri to image
     */
    public static void UILpicLoading(ImageView ivImageView, String ImageUrl, final ProgressBar progressBar, int placeholder) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(placeholder)
                .showImageForEmptyUri(placeholder)
                .showImageOnFail(placeholder)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        if (progressBar != null) {
            ImageLoader.getInstance().displayImage(ImageUrl, ivImageView, options, new SimpleImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }


            });
        } else {
            ImageLoader.getInstance().displayImage(ImageUrl, ivImageView, options);
        }

    }

    public static void UILcirclePicLoading(ImageView ivImageView, String ImageUrl, final ProgressBar progressBar, int placeholder) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                /*.displayer(new CircleBitmapDisplayer())*/
                .displayer(new RoundedBitmapDisplayer(1000))
                .showImageOnLoading(placeholder)
                .showImageForEmptyUri(placeholder)
                .showImageOnFail(placeholder)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        if (progressBar != null) {
            ImageLoader.getInstance().displayImage(ImageUrl, ivImageView, options, new SimpleImageLoadingListener() {

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }


            });
        } else {
            ImageLoader.getInstance().displayImage(ImageUrl, ivImageView, options);
        }

    }

    @NonNull
    public static Map<String, List<ChatModel>> toMap(@NonNull List<ChatModel> events) {
        Map<String, List<ChatModel>> map = new TreeMap<>();
        for (ChatModel event : events) {
            List<ChatModel> value = map.get(event.getTimestamp());
            if (value == null) {
                value = new ArrayList<>();
                map.put(event.getTimestamp(), value);
            }
            value.add(event);
        }
        return map;
    }

         public static  List<ListItem> getChatListMessages(Map<String , List<ChatModel>> events){
             List<ListItem> consolidatedList = new ArrayList<>();
             if(events.size()>0) {

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
             }

             return consolidatedList;
         }
    
}
