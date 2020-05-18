#!/bin/sh

set Pan=D:\
set AFolder=maven\
set AFolder2=androidsdk\
set AFolder3=.git\
set sdkDir=https://gitee.com/richard-guo/androidsdk.git

if not exist %Pan%%AFolder% (		
	md %Pan%%AFolder%
) 
cd\
D:
cd %AFolder%
if not exist %AFolder2% (		
	git clone %sdkDir%
) else (
	cd %AFolder2%
	git pull
	git add . 
	git commit -m "Ìá½»´úÂë"
	git push origin master
)

pause

