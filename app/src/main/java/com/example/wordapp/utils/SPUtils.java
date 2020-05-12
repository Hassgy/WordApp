package com.example.wordapp.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.wordapp.MyApplication;

public class SPUtils {

    private static final String SP_NAME = "WordApp";
    private static SharedPreferences sp;
    static {
        sp = MyApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
    private SPUtils(){}
    private static SPUtils instance;
    public static SPUtils getInstance(){
        if(instance == null){
            synchronized (SPUtils.class){
                if(instance == null){
                    instance = new SPUtils();
                }
            }
        }
        return instance;
    }
    //保存信息
    public void saveInfo(String key,Object value){
        if(value instanceof Integer){
            sp.edit().putInt(key, (Integer) value).commit();
        }else if(value instanceof Boolean){
            sp.edit().putBoolean(key, (Boolean) value).commit();
        }else if(value instanceof String){
            sp.edit().putString(key, (String) value).commit();
        }
    }
    //获取信息
    public Integer getIntInfo(String key,int defaultValue){
        return sp.getInt(key,defaultValue);
    }

    public boolean getBooleanInfo(String key,boolean defaultValue){
        return sp.getBoolean(key,defaultValue);
    }

    public String getStringInfo(String key,String defaultValue){
        return sp.getString(key,defaultValue);
    }
}
