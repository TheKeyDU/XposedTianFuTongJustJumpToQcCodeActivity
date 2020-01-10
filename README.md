傻逼智障天府通，我已经反馈了三次添加快捷二维码的建议。然后每次都是敷衍了事。
每次都在刷卡前广播都在叫 “请提前打开扫码界面”，你不知道自己加一个快捷方式么
好了 ，我哔哔完了，接下来讲一下技术实现。#
##第一版：本来使用另外一个activity，通过intent可以直接跨应用打开的。##
 `````` 
ComponentName apk2Component1 =
new ComponentName(the second appliaction's package name, the second appliaction's activity class name);
Intent mIntent = new Intent();
mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
mIntent.setComponent(apk2Component1);
startActivity(mIntent);
 `````` 
但是后面就不能用了，应该是更新之后 别人改了android:exported="fasle"
但是还专门去stackoverflow提了问
https://stackoverflow.com/questions/56400040/i-use-intent-to-start-an-another-appliactions-activity-class-why-i-fail
这样的话要解决这个问题就只有反编译天府通然后修改manifest的android:exported="true"
但是这个亲测是加了壳的，反编译没那么简单。
###所以只有万能的xposed来杀鸡用牛刀了。###
这个模块只有17kb，但是需要xposed的支持。需要安装xposed install和附带的系统组件。
之前：
 ![image](https://github.com/TheKeyDU/XposedTianFuTongJustJumpToQcCodeActivity/blob/master/%E6%8F%92%E4%BB%B6%E5%90%8E.gif?raw=true)
之后：
 ![image](https://github.com/TheKeyDU/XposedTianFuTongJustJumpToQcCodeActivity/blob/master/%E5%8E%9F%E5%A7%8B.gif?raw=true)
 
 
