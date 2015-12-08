package com.tt.testdialogdemo;

import android.app.AlertDialog;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //不同机型，表现形式不同，自测
    //笔者测了红米１S和红米２Ａ，１ｓ报错但对话框不消失，２Ａ不报错也不消失．
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Dialog")
                    .setMessage("thisis a dialog")
                    .show();
    }
}
