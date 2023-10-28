# 第四章 ui状态的保存和恢复

目标：

- 屏幕旋转之后的数据保存问题
- 进程消亡导致UI状态丢失的问题

横竖屏切换会导致activity重启，但是数据如何保存？？如何复用上次的数据？

使用ViewModel来保存UI数据

## ViewModel

ViewModel与某种特殊用户的屏相关联，适合存储管理那些屏幕显示的数据逻辑。可以将所有将用户显示的数据管理在一处，然后统一管理。

它存在声明周期感知类在声明周期中，其他感知部件还有LiveData

## 使用

- 引入ViewModel依赖(lifecycle-extensions的Android Jetpack库)
- 创建model实例

```shell
//首次调用会返回一个新的QuestionModel，再次访问的时候，返回上次创建的。
//当activity销毁掉的时候，model也就销毁了。
QuestionViewModel questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
```

## model的生命周期

activity处于运行、暂停、停止、不存在的状态，activity销毁的两种情况，一种是用户关闭，系统配置。

一般的自己销毁都希望重置，系统销毁又希望一致。

1.如何感知赈灾被销毁？

检查isFinishing属性，如果为true，那么activity正在被销毁（用户操作）为false，是被系统销毁，因为设备的配置变了。

ViewModel，当属性为fale的时候，就将状态保存在内存中，应对配置改变。ViewModel在关联的activity销毁之后才会一起销毁。

试图或者控制器可以关联model，但是model绝不可以反向关联，容易内存泄露。

当某个对象强引用另一个要被销毁的对象时，内存泄漏就会发生。这样的强引用会阻止垃圾回收器从内存里清理对象。


## model中加入数据

将数据复制到modelView进行存储。

## 进程销毁的时候保存数据

用户离开应用或者是Android需要回收内存的时候，进程就会被操作系统销毁，进程销毁后，内存存储的数据也被删除了。

进程包含；前台进程和后台进程，activity被杀死是正常的事情，例如用户按下主屏幕，当销毁的时候，数据都被删除，并不去调用完整的生命周期。

1.如何保存UI状态保存，重建activity？

然后重建，让用户感受不到应用程序被杀死了。

> 可以将数据保存在bundle中，只有为结束使用进入停止状态，操作系统就会调用onSaveInstanceState

将数据保存在bundle,只有为使用的activity进入停止状态的时候，操作系统都会调用onSaveInstanceState，停止的会被标记为killable，

## 复写onSaveInstanceState函数

通过onSaveInstanceState将一些数据保存在bundle中，在onCreate中取回数据，设备旋转的时候保存序号。

```shell
savedInstanceState.put(key,value);
```

## 案例

使用modelView进行数据存储，在发生旋转的时候，会发生销毁操作，在重新创建的时候，会重新获取modelView中的值

从此看出Model的生命周期是activity在旋转的时候重新创建并不会进行销毁。

saveInstanceState在应用程序自己重建的时候就会调用。










