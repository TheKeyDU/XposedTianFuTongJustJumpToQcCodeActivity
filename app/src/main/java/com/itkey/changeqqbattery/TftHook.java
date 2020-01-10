package com.itkey.changeqqbattery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Administrator on 2020/1/7 11:43
 */
public class TftHook implements IXposedHookLoadPackage {

    String 天府通app = "com.chinarainbow.tft";
    String 天府通启动类 = "com.chinarainbow.tft.mvp.ui.activity.SplashActivity";
    String 天府通二维码类 = "com.chinarainbow.tft.mvp.ui.activity.TFTQRActivity";
    String 天府通创建 = "onCreate";
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals(天府通app)) {
            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ClassLoader cl = ((Context) param.args[0]).getClassLoader();
                 final Context context= (Context) param.args[0];
                    Class<?> hookclass1 = null;
                    Class<?> hookclass2 = null;
                    hookclass1 = cl.loadClass(天府通启动类);
                    hookclass2 = cl.loadClass(天府通二维码类);
                    final Class<?> finalHookclass = hookclass2;
                    XposedHelpers.findAndHookMethod(hookclass1, 天府通创建, Bundle.class, new XC_MethodHook() {
                        @SuppressLint("WrongConstant")
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                        context.startActivity(new Intent(context, finalHookclass).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        Toast.makeText(context,"已直接跳转到扫码界面",1000).show();
                        ((Activity) context).finish();
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);

                        }
                    });
                }
            });


        }
    }




}