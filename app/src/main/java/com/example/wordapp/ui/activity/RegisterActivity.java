package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.UserDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.User;

import org.w3c.dom.Text;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_re_username, et_re_password, et_re_repassword;
    private Button btn_register;
    //获取用户信息表
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
        initEvent();
        userDao = DBManager.getInstance().getUserDao();
    }

    private void initEvent() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到用户表中的全部用户信息
                List<String> allUser = userDao.getAllUser();
                //得到输入的用户信息
                String username = et_re_username.getText().toString();
                String password = et_re_password.getText().toString();
                String repassword = et_re_repassword.getText().toString();
                if(TextUtils.isEmpty(username) ||
                        TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(repassword)){
                    //只要上述信息不完整，则提示补全信息
                    Toast.makeText(RegisterActivity.this,"请完整填写信息",Toast.LENGTH_SHORT).show();
                }else if(allUser.contains(username)){
                    Toast.makeText(RegisterActivity.this,"用户名已注册！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(repassword)){
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }else {

                    //将用户信息插入数据库
                    User user =  new User(username,password);
                    userDao.InsertUser(user);

                    //跳转到登录界面
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }

            }
        });
    }

    private void findView() {
        et_re_username = findViewById(R.id.et_re_username);
        et_re_password = findViewById(R.id.et_re_password);
        et_re_repassword = findViewById(R.id.et_re_repassword);
        btn_register = findViewById(R.id.btn_register);
    }
}
