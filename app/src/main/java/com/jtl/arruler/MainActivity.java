package com.jtl.arruler;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jtl.arruler.helper.SessionHelper;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //是否安装了ARCore
        boolean isInstall = SessionHelper.getInstance().checkHasARCore(this);
        if (isInstall){
            // 相机权限申请
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 这里简写的。
        if (requestCode!=200||grantResults[0]!=0)
            return;

        boolean initSuccess = SessionHelper.getInstance().init(this);
        Toast.makeText(this.getApplicationContext(),initSuccess+"",Toast.LENGTH_SHORT).show();
    }
}