# Android 开发初体验

目标：
1.用户点击 按钮  然后程序做出反馈


## 创建项目

关闭Android studio的Instant Run功能，它是用来修改代码之后，无需生成新的apk，开发者可以立即看到变化。

11以上的手机，无法执行这种操作。
Starting from Android , for apps targeting API level or higher, this method is a no-op when called on
text toasts.Build.VERSION_CODES#RBuild.VERSION_CODES#R

## 如何编译

android开发工具将文件、代码以及AndroidMainfest生成apk文件，然后进行签名，aapt2将布局资源编译为压缩紧凑后，打包到apk。

MainActivity使用LayoutInflater类实例化布局文件中定义的每一个view对象，

编译工具gradlew



















