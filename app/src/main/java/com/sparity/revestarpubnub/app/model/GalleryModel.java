package com.sparity.revestarpubnub.app.model;

import android.graphics.Bitmap;

/**
 * Created by Madhu
 */

public class GalleryModel {

    private String imagePath;
    private Bitmap imageBitmap;


    public GalleryModel(String imagePath, Bitmap imageBitmap) {
        this.imagePath = imagePath;
        this.imageBitmap = imageBitmap;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }
}
