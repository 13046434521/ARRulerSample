package com.jtl.arruler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;

public class MainActivity extends AppCompatActivity {
    private boolean isInstall = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isInstall = SessionHelper.getInstance().checkHasARCore(this);
        if (isInstall){
            boolean is = SessionHelper.getInstance().init(this);
            Toast.makeText(this.getApplicationContext(),is+"",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        SessionHelper.getInstance().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SessionHelper.getInstance().onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SessionHelper.getInstance().onDestroy();
    }
}