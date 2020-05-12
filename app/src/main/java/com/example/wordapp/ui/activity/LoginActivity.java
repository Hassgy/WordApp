package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.UserDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.utils.SPUtils;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username,et_password;
    private Button btn_login;
    private TextView tv_newuser_register,tv_forget_password;
    //获取用户表
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        initEvent();
        userDao = DBManager.getInstance().getUserDao();
    }

    private void initEvent() {
        //点击去注册
        tv_newuser_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        //忘记密码
        tv_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,UpdatePassActivity.class));
            }
        });

        //点击登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的用户信息
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                //获取全部的用户信息
                List<String> allUser = userDao.getAllUser();
                //根据用户名获取密码
                String pass = userDao.getPassFromUser(username);

                if(!allUser.contains(username)){
                    //用户名不存在
                    Toast.makeText(LoginActivity.this,"用户名不存在，请注册！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(pass)){
                    //密码不正确
                    Toast.makeText(LoginActivity.this,"密码不正确！",Toast.LENGTH_SHORT).show();
                }else{
                    //登录
                    //保存登录信息
                    SPUtils.getInstance().saveInfo("login",true);
                    //保存用户名到本地
                    SPUtils.getInstance().saveInfo("username",username);
                    //进入主界面
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void findView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_newuser_register = findViewById(R.id.tv_newuser_register);
        tv_forget_password = findViewById(R.id.tv_forget_password);
    }
}
