# myHttpSdkDemo
此版本不支持android X的api，后续会有androidX的包
# 如何在项目中快速集成
第一步：在项目根目录的build.gradle的repositories 下面添加
andoid X使用
    maven { url "https://raw.githubusercontent.com/guozaizai/mavenAndroidX/master" }
android X以下的使用
    maven { url "https://raw.githubusercontent.com/guozaizai/androidsdk/master" }

第二步：在app目录的build.gradle的dependencies  添加依赖
#andoid X使用
    #implementation 'com.xm:httpApiX:1.2'
#android X以下的使用
    #implementation 'com.xm:httpapi:1.2'

第三步：在app目录的build.gradle的android的defaultConfig 下面需要加如下配置
 javaCompileOptions {
     annotationProcessorOptions {
          includeCompileClasspath true
      }
 }

