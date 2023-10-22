# 第八章 UI fragment与fragment管理器

为了适应用户或设备的需求，activity界面可以在运行时组装，甚至重新组装,
但是Activity并不是灵活。

## fragment

它并不是activity来管理的，应用UI可让应用更具有灵活性

fragment是一个控制器对象，activity可以委派他执行任务，通常是用来管理用户界面。可以是一个整屏一个部分。

管理用户界面的fragment又成为UI fragment，存在自己的试图。

activity中可以预留位置，用来插入fragment布局。使用activity和fragment共同组装布局。

### 如何支持列表和显示明细

使用组成部分，一个fragment和一个明细fragment.试图切换过程中不需要销毁activity。

这个还可以做侧边栏导航。fragment没有显示的能力，他必须依赖于activity。

## UI Fragment如何使用

创建UI Fragment和创建activity的步骤相同。

1.定义布局文件
2.创建fragment类并设置其试图为第一步定义的布局。
3.编写实例化代码

### 创建布局

没啥可说的

### Fragment类

继承Fragment

**补充：**
> 一般的会包含两个，一个是android自带的，一个是Jetpack包中的东西。新版开发会看到androidx,就版看到的是另外两个（系统和v4）
> API 11引入，随后为了支持老版本就添加了v4
> API 28之后就放弃了fragment，然后全部移动到jetpack中。

fragment的生命周期：包含了模型与试图对象交互的控制器。之前是通过activity进行控制的。

oncreate方法，它也存在保存获取bundle的功能。创建试图在createView方法中，该函数会实例化布局，然后将view托管给acitivity

activity的onCreate方法是受保护的，不允许其他人调用，fragment是需要activity调用的
```java
inflater.inflate(R.layout.fragment_view,container,false);
R.layout.fragment_view:第一个参数传入的是布局的资源id
container:第二个参数是试图的俯视图
false:是否将生成的试图立即给父视图。
```

如果正确的引用布局，在createView中进行，对于布局的监听在onstart方法中进行。

## UI fragment托管

为了托管UI fragment,activity必须：

- 其布局中为fragment的试图安排位置
- 管理fragment实例的生命周期

### 在Activity中加入布局容器

```xml
<FragmentLayout />
```
### 使用FragmentManager添加UI fragment

FragmentManager可以管理的又fragent队列，fragment事务，负责将试图添加都试图层的结构中。

fragment事务可以用来添加、移除、附加、分离和替换fragment中的fragment，它运行执行多个操作，是的fragment动态组装和重新组装用户界面的关键。

activity的生命周期是系统调用，fragment的生命周期是activity在调用。

## 合理的使用fragment

xx











