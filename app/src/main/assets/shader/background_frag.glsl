precision mediump float;

varying vec2 v_TextureCoord; // 纹理坐标
uniform sampler2D sampler; //采样器

void main(){
    gl_FragColor = texture2D(sampler, v_TextureCoord);
}