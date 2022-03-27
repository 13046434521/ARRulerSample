package com.jtl.arruler.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author：TianLong
 * @date：2022/3/26 11:29
 * @detail：
 */
public interface BaseRender<T> {
    void createGL(Context context);

    void onSurfaceCreated();

    void onSurfaceChanged(int width, int height);

    void onDrawFrame(T data);
}
