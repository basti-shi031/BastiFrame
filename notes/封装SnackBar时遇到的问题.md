# 封装Snackbar时遇到的问题
## 问题
使用Snackbarr和Toast有些类似，调用的API为
```java
Snackbar mSnackbar = Snackbar.make(View v,CharSequence text,int duration);
mSnackbar.show();
```
和Toast不同的是，Snackbar初始化时需要传递一个View，这个View的作用是：通过这个View，找到整个布局的根布局，这个和Snackbar的机制有关，SnackBar显示时，是将调用根布局的addView方法，
所以这个view必须存在。
在普通的XXActivity中，这是一个很简单的调用，任意给Snackbar传递一个View即可，但是在BaseActivity中，onCreate方法中还没有调用setContentView方法，也就是说此时界面中还没有View生成，这是这个View应该怎么传？

## 方法
我们可以在BaseActivity中给Snackbar传递activity的根视图，即
```java
this.getWindow().getDecorView().findViewById(android.R.id.content)
```
or
```java
this.findViewById(android.R.id.content)
```
or
```java
this.findViewById(android.R.id.content).getRootView()
```
这三个方法都可以得到当前activity的根布局，直接将返回结果传给Snackbar即可使用。