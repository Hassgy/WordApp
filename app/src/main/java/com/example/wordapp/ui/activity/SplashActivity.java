package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wordapp.R;
import com.example.wordapp.utils.SPUtils;

import javax.security.auth.login.LoginException;

public class SplashActivity extends AppCompatActivity {

    private Button btn_login;
    private ImageView iv_bg;

    //延时
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btn_login = findViewById(R.id.btn_login);
        iv_bg = findViewById(R.id.iv_bg);

        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(2000);
        iv_bg.setAnimation(animation);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否已经登录过
                if(SPUtils.getInstance().getBooleanInfo("login",false)){
                    //如果登录过直接进入主界面
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else{
                    btn_login.setVisibility(View.VISIBLE);
                }
            }
        },2000);

        //点击进入登录界面
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
