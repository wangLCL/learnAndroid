# 第二个Activity

添加第二个Activity.本地目标是先看答案会返回一条信息，猜测使用startActivityResult.

## 完成应用升级

- 创建Activity以及配套布局
- 从一个activity中启动另一个。
- 在父子之间传递数据、

## 创建第二个

### 启动第二个Activity

启动最简单的方式startActivity(intent)函数。

ActivityManager负责创建Activity实例并调用onCreate方法。


### 基于Intent的通信

intent是compent用来与操作系统通信的一种媒介，compent还包含service broadcast receiver以及content provider

intent是告诉ActivityManager应用从那个Activity启动，并且去哪里可以找到。

在启动之前会判断指定的class是否在mainfest文件中声明，如果声明完成，那么就启动，否则抛出异常。

### 显示Intent和隐式的intent

通过指定context与class对象，然后调用intent的构造函数来创建intent,这种是显式的intent。

同一个应用需要使用ActivityManager启动，这对于不同应用这是存在好处的，不同应用间会更加方便点。

一个应用如果需要启动另一个activity，可以通过隐式来处理。

## Activity之间数据传递

MainActivity和CheatActivity之间数据传递，用户点击cheat按钮，会跳转到cheat页面，然后用户点击返回，会将CheatActivity销毁，它会将值返回给MainActivity.

我们是没有办法自定义Activity构造的，需要将数据存放到Intent,让在目标里面进行接收数据。

### 将数据返回给上一个Activity

```java
Activity.startActivityForResult(intent,code);
```

请求码先发送给子activity，然后在返回给父activity的整数值，用户可以自定义。

在一个activity启动多个不同类型的activity需要判断消息回馈就需要使用请求码。虽然MainActivity只启动一种类型的子Activity，但为应对未来需求变化，尽量设置为常量。

#### 实现子activity发送返回给父亲信息

```java
setResult(resultCode)
setResult(result,data)
```

根据子类的操作，返回不同的状态码

数据回传需要使用intent

## activity的使用和管理

acitivity之间，操作系统做了什么？

- 点击屏幕启动器，启动应用程序中的一个activity，启动了Launcher activity.
- 点击check的时候，会在MainActivty上层启动一个Activity
- 都处于activity栈中
- 返回的时候，上层的会被移除，调用finish方法