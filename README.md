# XposedDemo
a Xposed Moudle Demo ,hook a hello word application.just change a textview 
hook 一个hello word软件 也就是最简单的empty Activity的模板
被hook的activity

package com.itkey.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv);
        textView.setText(returnString());

    }

    private String returnString() {
    return "hello";
    }
}
