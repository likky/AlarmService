package com.dididi.longrunningservicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dididi.longrunningservicetest.Service.LongRunningService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用方法
        Intent intent = new Intent(MainActivity.this, LongRunningService.class);
        startService(intent);
    }
}
