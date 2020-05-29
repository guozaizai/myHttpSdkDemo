# myHttpSdkDemo
# 如何在项目中快速集成
第一步：在项目根目录的build.gradle的repositories 下面添加    
andoid X使用          
    maven { url "https://gitee.com/richard-guo/androidsdkX/raw/master" }     
android X以下的使用         
    maven { url "https://gitee.com/richard-guo/androidsdk/raw/master" }   
在dependencies中添加  classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4'

第二步：在app目录的build.gradle的dependencies  添加依赖       
andoid X使用       
    implementation 'com.xm:httpApiX:1.0.2'    
android X以下的使用    
    implementation 'com.xm:httpapi:1.0.4'    

第三步：在app目录的build.gradle的android的defaultConfig 下面需要加如下配置      
 javaCompileOptions {
     annotationProcessorOptions {
          includeCompileClasspath true
      }
 }   
在app目录顶部添加 apply plugin: 'android-aspectjx'
 
 第四步：在你自己的applicaiton中添加：HApi.init(getApplication());
 #
 # V1.0.4
 网络请求可以随时取消，新增一些常用的注解，自定义一个code用于处理类似于 token失效跳其他页面的效果，需要配置arouter ，并且在
 你需要跳转的页面需要加注解: @Route(path = "/ui/loginActivity") 
  
 # V1.0.3
   支持自定义请求code成功的值，支持自定义loading，支持请求返回的code可以映射成后台的key为｛code，statusCode｝，
   message可以映射成后台的key为 {message,Msg, msg, error, err, errorMsg, errorMessage},
   data可以映射成后台的key为｛data，datas，response｝
   后续有合理的key再增加
 # V1.0.2
  支持自定义请求头，支持https请求
 # V1.0.1
  把retrofit的构建放在api里面，app直接引用。                     
 # V1.0.0
  用rxjava写的基于okhttp的网络请求框架，支持activity和fragment的mvp形式或者页面直接请求，还有一些常用的工具类。


