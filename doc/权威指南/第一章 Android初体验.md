# Android 开发初体验

目标：
1.定义布局
2.用户点击按钮  
3.然后程序做出反馈

## 创建项目

关闭Android studio的Instant Run功能，它是用来修改代码之后，无需生成新的apk，开发者可以立即看到变化。

### 定义布局

```java
<?xml version="1.0" encoding="utf-8"?>
<!--gravity = center 是的内部的布局居中-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    tools:context="com.wk.learn.MainView"
    android:orientation="vertical">
    <TextView
        android:id="@+id/question_text_view"
        android:gravity="center"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

    </TextView>
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        <Button
            android:id="@+id/true_btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/true_btn">

        </Button>
        <Button
            android:id="@+id/false_btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/false_btn">

        </Button>
    </LinearLayout>
    <Button
        android:id="@+id/next_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/next_btn">

    </Button>

</LinearLayout>
```

### Activity中进行响应

```java
        Button trueBtn = findViewById(R.id.true_btn);
        Button falseBtn = findViewById(R.id.false_btn);
        Button nextBtn = findViewById(R.id.next_button);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkAnswer(true);
                Toast.makeText(MainView.this,"true btn",Toast.LENGTH_SHORT).show();
            }
        });

        falseBtn.setOnClickListener(view->{
//            checkAnswer(false);
            Toast.makeText(MainView.this,"false btn",Toast.LENGTH_SHORT).show();
        });
        nextBtn.setOnClickListener(view->{
//            updateQuestion();
            Toast.makeText(MainView.this,"next btn",Toast.LENGTH_SHORT).show();
        });
```

Toast的位置，可以设置在底部 顶部等

toast在android11以上，无法执行顶部提示。
11以上的手机，无法执行这种操作。
Starting from Android , for apps targeting API level or higher, this method is a no-op when called on
text toasts.Build.VERSION_CODES#RBuild.VERSION_CODES#R

## 如何编译

android开发工具将文件、代码以及AndroidMainfest生成apk文件，然后进行签名，aapt2将布局资源编译为压缩紧凑后，打包到apk。

MainActivity使用LayoutInflater类实例化布局文件中定义的每一个view对象，

编译工具gradlew



















