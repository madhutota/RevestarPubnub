package com.sparity.revestarpubnub.app.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sparity.revestarpubnub.R;
import com.sparity.revestarpubnub.app.activity.BaseActivity;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;


/**
 * Created by Pavan
 */

public class Utility {
    public static final boolean logMessageOnOrOff = true;
    public static final boolean toastmessagesEnable = true;


    public static ContentValues videoContentValues = null;


    public static final int DIALOG_FAILURE = 3;
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private static int imageWidth = 0;
    private static int imageHeight = 0;

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    private BaseActivity activity;


    public static boolean isMarshmallowOS() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public static boolean isNougatOS() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N);
    }

    /**
     * a
     * ASSIGN THE COLOR
     **/
    @SuppressWarnings("deprecation")
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23)
            return ContextCompat.getColor(context, id);
        else
            return context.getResources().getColor(id);
    }

    /**
     * ASSIGN THE DRAWABLE
     **/
    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            return ContextCompat.getDrawable(context, id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    /**
     * ASSIGN THE DIMENS
     **/
    public static int getDimen(Context context, int id) {
        return (int) context.getResources().getDimension(id);
    }

    /**
     * ASSIGN THE STRINGS
     **/
    public static String getStrings(Context context, int id) {
        String value = null;
        if (context != null && id != -1) {
            value = context.getResources().getString(id);
        }
        return value;
    }


    /**
     * HIDE THE KEYBOARD
     **/
    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }


    /*
   * CHANGE THE IMAGE_VIEW ICONS COLOR
   * */
    public static PorterDuffColorFilter getDrawableColor(int color) {
        return new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    public static void hideSoftKeyPad(Context context) {
        Activity activity = (Activity) context;
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValueNullOrEmpty(String value) {
        boolean isValue = false;
        if (value == null || value.equals("") || value.equals("null")
                || value.trim().length() == 0) {
            isValue = true;
        }
        return isValue;
    }

   /* public static void navigateFragment(Fragment fragment, String tag, Bundle bundle, FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.window_enter, R.anim.window_close);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(R.id.main_content, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }*/





   /* public static void navigateCommunityFragment(Fragment fragment, String tag, Bundle bundle, FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

       *//* if (fragmentManager.getBackStackEntryCount() > 0) {

            fragmentManager.popBackStackImmediate();
*//**//*
            if (!tag.equalsIgnoreCase(HomeFeedsFragment.TAG))
                fragmentManager.popBackStackImmediate();
            else
                fragmentTransaction.addToBackStack(tag);*//**//*
        }*//*
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(R.id.main_content, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

    }
*/

    public static void removeFragment(FragmentActivity fragmentActivity) {
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        manager.popBackStack();


    }


    public static boolean checkContactsReadPermission(BaseActivity parent) {
        String permission = "android.permission.READ_CONTACTS";
        int res = parent.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    /**
     * To Print logcat this method is used.
     *
     * @param logMsg Purpose of the log
     * @param logVal What you want to print
     */
    public static void showLog(String logMsg, String logVal) {
        try {
            if (logMessageOnOrOff) {
                if (!isValueNullOrEmpty(logMsg) && !isValueNullOrEmpty(logVal)) {
                    Log.e(logMsg, logVal);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    /*
   SET THE VIEWPAGER TYPE FACE
   * */
    public static void setViewPageTypeface(BaseActivity parent, TabLayout tabLayout) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    TextView tvTab = ((TextView) tabViewChild);

                }
            }
        }
    }


    public static int getDisplayWidth(BaseActivity baseActivity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        baseActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static int getDisplayHeight(BaseActivity baseActivity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        baseActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }


    public static int getScreenHeight(BaseActivity c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;
        }

        return screenHeight;
    }


    public static int getScreenWidth(BaseActivity c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
            screenHeight = size.y;
        }

        return screenWidth;
    }

    public static int getUserMusicImageHeight(BaseActivity parent) {
        int ScreenHeight = getScreenHeight(parent);
        double height = ((ScreenHeight * 33.5) / 100);
        return (int) height;
    }

    public static int getUserMusicImageWidth(BaseActivity parent) {
        int screenWidth = getScreenWidth(parent);
        double width = ((screenWidth * 32) / 100);
        return (int) width;
    }


    public static String milliSecondsToTimer(int milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (milliseconds / (1000 * 60 * 60));
        int minutes = (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     *
     * @param currentDuration
     * @param totalDuration
     */
    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    public static String showToastMessage(Context context, String message) {

        if (toastmessagesEnable && !isValueNullOrEmpty(message) && context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

        return message;
    }

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        String name = tz.getDisplayName();
        long now = cal.getTimeInMillis();
        if (time > now || time <= 0) {
            return "Just now";
        }
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "Just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1 minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }


    public static void setTranslateStatusBar(AppCompatActivity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Utility.getColor(context, R.color.colorAccent));
        }
    }

    public static void hideStatusBar(AppCompatActivity activity) {
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();
    }







   /* public static int getDisplayWidth() {

        return new DisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight() {

        return new DisplayMetrics().heightPixels;
    }*/

    public static int calculatePadding(BaseActivity baseActivity) {
        int screenHeight = getDisplayHeight(baseActivity);
        double padding = ((screenHeight * 11.26) / 100);
        return (int) padding;
    }

    public static void keyBoardNextButton(final BaseActivity parent, final EditText editText) {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // PERFORM ACTION ON ENTER KEY PRESS
                    Utility.hideSoftKeyboard(parent, editText);
                    return true;
                }
                return false;
            }
        });
    }


   /* public static ArrayList<Frame> getFrameDetails(Context context) {

        ArrayList<Frame> results = new ArrayList<Frame>();
        ImageSize imglist = new ImageSize("", "", "", "", "", "", "", "");
        FrameMedia fmedia = new FrameMedia("", "", "", imglist);
        UserInfo userInfo = new UserInfo("", "", "", "", fmedia);
        UserTags userTags = new UserTags("", "", null);
        ArrayList<TagsModel> uTagsList = new ArrayList<>();
        uTagsList.add(userTags);
        Frame details = new Frame("", "Hi Have a nice day!!..", "", null, uTagsList, null, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", userInfo, "Seattle, WA", "", "", "");
        results.add(details);
        return results;
    }*/


    public static boolean isNetworkAvailable(BaseActivity context) {
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState() == NetworkInfo.State.CONNECTED
                    || connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .getState() == NetworkInfo.State.CONNECTING) {
                return true;
            } else return connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState() == NetworkInfo.State.CONNECTED
                    || connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .getState() == NetworkInfo.State.CONNECTING;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeDirectory(File directory) {
        if (directory == null)
            return false;
        if (!directory.exists())
            return true;
        if (!directory.isDirectory())
            return false;

        String[] list = directory.list();

        // Some JVMs return null for File.list() when the
        // directory is empty.
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                File entry = new File(directory, list[i]);
                if (entry.isDirectory()) {
                    if (!removeDirectory(entry))
                        return false;
                } else {
                    if (!entry.delete())
                        return false;
                }
            }
        }

        return directory.delete();
    }

}
