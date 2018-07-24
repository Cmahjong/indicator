# indicator
init Indicator  
[![](https://jitpack.io/v/yinjinyj/indicator.svg)](https://jitpack.io/#yinjinyj/indicator)


效果图

![sample](/gif/1.gif)

如何使用

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.yinjinyj:indicator:1.0.0'
	}
  
  
  
 使用方法
 
 1XML直接引用布局就行了
 
  （1）CircleIndicatorView
  
  ![img](/img/1.png)
  

 （2）RectIndicatorView
 
  ![img](/img/2.png)

（3）TextIndicatorView

  ![img](/img/3.png)
  
（4）CircleMoveIndicatorView

 ![img](/img/4.png)
 
   ![img](/img/4_1.png)
  
  
  最后都要添加一个 indicator_view.start()就可以了

viewpage的轻量级指示器（有需求请在issues里面提，到时候会添加上去的）
