attribute vec4 a_Posistion;
attribute vec2 a_TextureCoord;
varying vec2 v_TextureCoord;

void main(){
    v_TextureCoord = a_TextureCoord;
    gl_Position = a_Posistion;
}