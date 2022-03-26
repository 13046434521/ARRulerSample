package com.jtl.arruler;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.CameraConfig;
import com.google.ar.core.CameraConfigFilter;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

import java.util.List;

import static com.google.ar.core.ArCoreApk.InstallStatus.INSTALLED;
import static com.google.ar.core.ArCoreApk.InstallStatus.INSTALL_REQUESTED;

/**
 * @author：TianLong
 * @date：2022/3/26 9:08
 * @detail：arcore Session 单例类
 */
public class SessionHelper {
    private static String TAG =  SessionHelper.class.getSimpleName();
    private boolean installRequested;
    private Session session;
    private SessionHelper(){
    }
    public static SessionHelper getInstance(){
        return SessionHelperHolder.SESSION_HELPER;
    }

    private static class SessionHelperHolder{
        private static final SessionHelper SESSION_HELPER = new SessionHelper();
    }

    public boolean checkHasARCore(Activity activity)  {
        try {
            ArCoreApk.InstallStatus installStatus=  ArCoreApk.getInstance().requestInstall(  activity, !installRequested);
            if (installStatus == INSTALL_REQUESTED) {
                installRequested = true;
                return false;
            }else if (installStatus == INSTALLED){
                return true;
            }
        } catch (UnavailableDeviceNotCompatibleException e) {
            Log.e(TAG,"This device does not support AR");
            e.printStackTrace();
        } catch (UnavailableUserDeclinedInstallationException e) {
            Log.e(TAG,"User does not install AR");
            e.printStackTrace();
        }
        return false;
    }


    public boolean init(Context context){
        if (session == null) {
            Exception exception = null;
            String message = null;
            try {
                //初始化Session
                session = new Session(context);
                CameraConfigFilter cameraConfigFilter = new CameraConfigFilter(session);
                cameraConfigFilter.setFacingDirection(CameraConfig.FacingDirection.BACK);

                // 检查设备是否有相机
                List<CameraConfig> cameraConfigs = session.getSupportedCameraConfigs(cameraConfigFilter);
                if (!cameraConfigs.isEmpty()) {
                    // Element 0 contains the camera config that best matches the session feature
                    // and filter settings.
                    session.setCameraConfig(cameraConfigs.get(0));
                } else {
                    message = "This device does not have a front-facing (selfie) camera";
                    exception = new UnavailableDeviceNotCompatibleException(message);
                }

                // 配置Session相应配置
                configureSession();
            } catch (UnavailableArcoreNotInstalledException e) {
                message = "Please install ARCore";
                exception = e;
            }  catch (Exception e) {
                message = "Failed to create AR session:"+e.getMessage();
                exception = e;
            }

            if (message != null) {

                Log.e(TAG, "Exception creating session:"+message, exception);
                return false;
            }
        }

        return true;
    }

    public void onResume(){
        if (session!=null){
            // Note that order matters - see the note in onPause(), the reverse applies here.
            try {
                session.resume();
            } catch (CameraNotAvailableException e) {
                session = null;
            }
        }
    }

    public void onPause(){
        if (session!=null){
            // Note that order matters - see the note in onPause(), the reverse applies here.
            session.pause();
        }
    }
    public void onDestroy(){
        if (session!=null){
            session.close();
            session = null;
        }
    }

    /** Configures the session with feature settings. */
    private void configureSession() {
        Config config = session.getConfig();

        // 启用光照估计，在线性色彩空间中生成推断的环境 HDR 光照估计
        config.setLightEstimationMode(Config.LightEstimationMode.ENVIRONMENTAL_HDR);

        // 相机是否支持深度模式
        if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
            config.setDepthMode(Config.DepthMode.AUTOMATIC);
        } else {
            config.setDepthMode(Config.DepthMode.DISABLED);
        }
        // 启用及时放置模式
        config.setInstantPlacementMode(Config.InstantPlacementMode.LOCAL_Y_UP);
        session.configure(config);
    }
}
