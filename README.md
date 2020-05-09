# myHttpSdkDemo
# 如何在项目中快速集成
第一步：在项目根目录的build.gradle的repositories 下面添加    
andoid X使用          
    maven { url "https://gitee.com/richard-guo/androidsdkX/raw/master" }     
android X以下的使用         
    maven { url "https://gitee.com/richard-guo/androidsdk/raw/master" }   

第二步：在app目录的build.gradle的dependencies  添加依赖       
andoid X使用       
    implementation 'com.xm:httpApiX:1.0.0'    
android X以下的使用    
    implementation 'com.xm:httpapi:1.0.0'    

第三步：在app目录的build.gradle的android的defaultConfig 下面需要加如下配置      
 javaCompileOptions {
     annotationProcessorOptions {
          includeCompileClasspath true
      }
 }
 #
 # 1.0.0
 用rxjava写的基于okhttp的网络请求框架，支持activity和fragment的mvp形式或者页面直接请求，还有一些常用的工具类。

