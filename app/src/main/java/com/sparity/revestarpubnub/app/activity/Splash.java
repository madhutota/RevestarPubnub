package com.sparity.revestarpubnub.app.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sparity.revestarpubnub.R;
import com.sparity.revestarpubnub.app.permissions.Permissions;
import com.sparity.revestarpubnub.app.utils.Utility;

public class Splash extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, 5 * 1000);


       /* if (Utility.isMarshmallowOS()) {
            Permissions.getInstance().setActivity(this);
            CheckForPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.RECORD_AUDIO);
        } else {
            // initilizeViews();
        }*/
    }

    private void CheckForPermissions(final String... mPermisons) {
        Permissions.getInstance().requestPermissions(new Permissions.IOnPermissionResult() {
            @Override
            public void onPermissionResult(Permissions.ResultSet resultSet) {
                if (resultSet.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                        resultSet.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                        resultSet.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        resultSet.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION) &&
                        resultSet.isPermissionGranted(Manifest.permission.ACCESS_NETWORK_STATE) &&
                        resultSet.isPermissionGranted(Manifest.permission.CALL_PHONE) &&
                        resultSet.isPermissionGranted(Manifest.permission.WRITE_CONTACTS) &&
                        resultSet.isPermissionGranted(Manifest.permission.CAMERA) &&
                        resultSet.isPermissionGranted(Manifest.permission.READ_CONTACTS) &&
                        resultSet.isPermissionGranted(Manifest.permission.RECORD_AUDIO)) {

                    // initilizeViews();
                } else {
                    android.app.AlertDialog.Builder adb = new android.app.AlertDialog.Builder(Splash.this);
                    adb.setTitle(Permissions.TITLE);
                    adb.setMessage(Permissions.MESSAGE);
                    adb.setCancelable(false);
                    adb.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            CheckForPermissions(mPermisons);
                        }
                    });
                    adb.setNegativeButton(getString(R.string.CANCEL), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                        }
                    });
                    adb.show();
                }
            }


            @Override
            public void onRationaleRequested(Permissions.IOnRationaleProvided callback, String... permissions) {
                Permissions.getInstance().showRationaleInDialog(Permissions.TITLE,
                        Permissions.MESSAGE, "Retry", callback);
            }
        }, mPermisons);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Utility.isMarshmallowOS()) {
            Permissions.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults);
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
