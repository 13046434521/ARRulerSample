package com.jtl.arruler.gl;

import android.content.Context;
import android.opengl.GLES20;

import com.jtl.arruler.helper.ShaderHelper;

/**
 * @author：TianLong
 * @date：2022/3/26 11:44
 * @detail：
 */
class YuvRenderer implements BaseRenderer{
    private static final String fragShaderPath = "/assets/shader/background_frag.glsl";
    private static final String vertexShaderPath = "/assets/shader/background_frag.glsl";
    @Override
    public void createOnGLThread(Context context) {
        int [] textureId = new int[0];
        GLES20.glGenTextures(1,textureId,0);

        int fragShader = ShaderHelper.loadGLShader(context,GLES20.GL_FRAGMENT_SHADER,fragShaderPath);

    }

    @Override
    public void onSurfaceCreated() {

    }

    @Override
    public void onSurfaceChanged(int width, int height) {

    }

    @Override
    public void onDrawFrame() {

    }
}
