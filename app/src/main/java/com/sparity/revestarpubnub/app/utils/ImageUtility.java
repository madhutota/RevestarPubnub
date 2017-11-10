package com.sparity.revestarpubnub.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;


import com.sparity.revestarpubnub.app.activity.BaseActivity;
import com.sparity.revestarpubnub.app.model.GalleryModel;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ImageUtility {


    /**
     * Decode and sample down a bitmap from a byte stream
     */
    public static Bitmap decodeSampledBitmapFromByte(Context context, byte[] bitmapBytes) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int reqWidth, reqHeight;
        Point point = new Point();

        display.getSize(point);
        reqWidth = point.x;
        reqHeight = point.y;


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        options.inBitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Load & resize the image to be 1/inSampleSize dimensions
        // Use when you do not want to scale the image with a inSampleSize that is a power of 2
        options.inScaled = true;
        options.inDensity = options.outWidth;
        options.inTargetDensity = reqWidth * options.inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false; // If set to true, the decoder will return null (no bitmap), but the out... fields will still be set, allowing the caller to query the bitmap without having to allocate the memory for its pixels.
        options.inPurgeable = true;         // Tell to gc that whether it needs free memory, the Bitmap can be cleared
        options.inInputShareable = true;    // Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future

        return BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length, options);
    }

    /**
     * Calculate an inSampleSize for use in a {@link BitmapFactory.Options} object when decoding
     * bitmaps using the decode* methods from {@link BitmapFactory}. This implementation calculates
     * the closest inSampleSize that is a power of 2 and will result in the final decoded bitmap
     * having a width and height equal to or larger than the requested width and height
     * <p>
     * The function rounds up the sample size to a power of 2 or multiple
     * of 8 because BitmapFactory only honors sample size this way.
     * For example, BitmapFactory downsamples an image by 2 even though the
     * request is 3. So we round up the sample size to avoid OOM.
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int initialInSampleSize = computeInitialSampleSize(options, reqWidth, reqHeight);

        int roundedInSampleSize;
        if (initialInSampleSize <= 8) {
            roundedInSampleSize = 1;
            while (roundedInSampleSize < initialInSampleSize) {
                // Shift one bit to left
                roundedInSampleSize <<= 1;
            }
        } else {
            roundedInSampleSize = (initialInSampleSize + 7) / 8 * 8;
        }

        return roundedInSampleSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final double height = options.outHeight;
        final double width = options.outWidth;

        final long maxNumOfPixels = reqWidth * reqHeight;
        final int minSideLength = Math.min(reqHeight, reqWidth);

        int lowerBound = (maxNumOfPixels < 0) ? 1 :
                (int) Math.ceil(Math.sqrt(width * height / maxNumOfPixels));
        int upperBound = (minSideLength < 0) ? 128 :
                (int) Math.min(Math.floor(width / minSideLength),
                        Math.floor(height / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if (maxNumOfPixels < 0 && minSideLength < 0) {
            return 1;
        } else if (minSideLength < 0) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


    public static String saveBitmap(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/HomeFrame");
        if (myDir.exists()) {
            boolean status = myDir.delete();
            Log.v("FOLDER:--", "FOLDER STATUS:" + status);
        } else {
            boolean status = myDir.mkdirs();
            Log.v("FOLDER:--", "FOLDER STATUS:" + status);
        }

        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fname = "Photo_" + ts + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) {
            boolean status = file.delete();
            Log.v("DELETE:--", "DELETE STATUS:" + status);
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public static Bitmap getRotatedBitmap(int rotation, String mPath) {
        File f = new File(mPath);
        Bitmap mBitMap = BitmapFactory.decodeFile(f.getAbsolutePath());
        if (mBitMap != null) {
            if (rotation != 0) {
                Bitmap oldBitmap = mBitMap;
                Matrix matrix = new Matrix();
                matrix.postRotate(rotation);
                mBitMap = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(), matrix, false);
                oldBitmap.recycle();
            }
        }
        return mBitMap;
    }


    public static ArrayList<GalleryModel> getAllImagesVideos(Context mContext) {

        Cursor cursor;
        int column_index_data;
        ArrayList<GalleryModel> listOfAllImages = new ArrayList<>();
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

        Uri queryUri = MediaStore.Files.getContentUri("external");

        String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        cursor = mContext.getContentResolver().query(queryUri, null, selection, null, orderBy);
        assert cursor != null;
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            String absolutePathOfImage = cursor.getString(column_index_data);
            if (!listOfAllImages.contains(absolutePathOfImage)) {
                GalleryModel model = new GalleryModel("file://" + absolutePathOfImage, null);
                listOfAllImages.add(model);
            }
        }

        return listOfAllImages;
    }


    public static ArrayList<GalleryModel> getAllImages(Context mContext) {
        Cursor cursor;
        int column_index_data;
        ArrayList<GalleryModel> listOfAllImages = new ArrayList<>();
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

        Uri queryUri = MediaStore.Files.getContentUri("external");

        String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        cursor = mContext.getContentResolver().query(queryUri, null, selection, null, orderBy);
        assert cursor != null;
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            String absolutePathOfImage = cursor.getString(column_index_data);
            if (!listOfAllImages.contains(absolutePathOfImage)) {
                GalleryModel model = new GalleryModel("file://" + absolutePathOfImage, null);
                listOfAllImages.add(model);
            }
        }
        return listOfAllImages;
    }





}
