package com.jtl.arruler.gl;

import android.content.Context;
import android.media.Image;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.NotYetAvailableException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author：TianLong
 * @date：2022/3/26 11:47
 * @detail：
 */
class YuvGLSurface extends BaseGLSurface implements GLSurfaceView.Renderer {
    private YuvRenderer yuvRenderer;
    Session mSession;
    public YuvGLSurface(Context context) {
        this(context,null);
    }

    public YuvGLSurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        yuvRenderer = new YuvRenderer();
        yuvRenderer.createOnGLThread(context);
        pushRender(yuvRenderer);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        try {
           Frame frame = mSession.update();
           Image image = frame.acquireCameraImage();
           image.getPlanes()[0].getBuffer();
        } catch (CameraNotAvailableException e) {
            e.printStackTrace();
        } catch (NotYetAvailableException e) {
            e.printStackTrace();
        }
    }
}
