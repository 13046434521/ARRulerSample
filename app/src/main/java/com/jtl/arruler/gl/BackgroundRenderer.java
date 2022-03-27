package com.jtl.arruler.gl;

import android.content.Context;
import android.opengl.GLES20;

import com.jtl.arruler.helper.ShaderHelper;

/**
 * @author：TianLong
 * @date：2022/3/26 11:44
 * @detail：背景Renderer
 */
class BackgroundRenderer implements BaseRenderer{
    /**
     * 顶点着色器
     */
    private static final String VERTEX_SHADER_NAME = "shader/background_vert.glsl";
    /**
     * 片元着色器
     */
    private static final String FRAGMENT_SHADER_NAME = "shader/background_frag.glsl";

    private int [] textureId = new int[1];
    @Override
    public void createOnGLThread(Context context) {
        GLES20.glGenTextures(1,textureId,0);

        int vertShader = ShaderHelper.loadGLShader(context,GLES20.GL_VERTEX_SHADER,VERTEX_SHADER_NAME);
        int fragShader = ShaderHelper.loadGLShader(context,GLES20.GL_FRAGMENT_SHADER,FRAGMENT_SHADER_NAME);
        ShaderHelper.checkGLError("jtl");
        int program = GLES20.glCreateProgram();

        ShaderHelper.checkGLError("jtl");
        GLES20.glAttachShader(program,fragShader);
        ShaderHelper.checkGLError("jtl");
        GLES20.glAttachShader(program,vertShader);
        ShaderHelper.checkGLError("jtl");
        GLES20.glLinkProgram(program);
        ShaderHelper.checkGLError("jtl");
        GLES20.glUseProgram(program);
        ShaderHelper.checkGLError("jtl");
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

    public int getTextureId() {
        return textureId[0];
    }
}
