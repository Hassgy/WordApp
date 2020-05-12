package com.example.wordapp;

import android.app.Application;
import android.content.Context;

import com.example.wordapp.model.db.DBManager;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        //初始化数据库，在App启动的时候完成
        DBManager.getInstance().init();
    }

    public static Context getContext(){
        return context;
    }

}
