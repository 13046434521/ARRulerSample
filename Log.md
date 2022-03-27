# ARRuler

## DAY1:2022-3-26
### ARCore
    1. 集成最新的ARCore
    2. 初始化ARCore
    3. 退出时关闭ARCore
    4. Manifest中配置 ：<meta-data android:name="com.google.ar.core" android:value="required" />
    5. 动态授权

### OpenGL
    1. 写OpenGL相关渲染
    2. 渲染ARCore的图像

### TODO
    1. 动态授权：Camera等

### 注意事项：
    1. 配置ARCore时：Manifest中配置 ：<meta-data android:name="com.google.ar.core" android:value="required" />
    2. 生产shader等相关的代码要运行在gl线程

### 顺序：
    glGenTexture->glCreatProgram->