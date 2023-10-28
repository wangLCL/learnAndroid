# 使用RecyclerView

支持列表显示。

数据放在modelView中，那么modelView在fragment中的生命周期是怎么样的？

- 不同于activity,只要fragment试图还在就会一直在活跃状态，即使设备旋转当前fragment实例不存在了，数据依旧在，还可以给新的页面使用。
- fragment销毁了就没有了，
- fragment被替换了，数据也会被删除，但是回滚就不会被清除。

## 添加RecycleView

RecycleView在Jetpack中

**直接使用布局即可， 不需要外面加其他东西**

RecycleView使用的时候还需要一个LinearLayoutManager,没有它将无法工作。
RecycleView无法将数据展示的，需要通过LayoutManager来完成，不仅仅安排位置，还会负责如何进行滚屏。

## 创建列表试图布局

列表项都是一个个view，每一个列表展现的是数据集合里的单个对象，这些view可简单可复杂。

## ViewHolder实现

显示数据害包含viewholder和adapter。

使用方法:

- 首先接受view
- 将参数传递给构造函数

ViewHolder主要是用来管理页面的条目的内容，Adapter主要显示数据。


## 循环使用试图

100个view意味着100个列表项？

完全没必要展示所有的条目，






































