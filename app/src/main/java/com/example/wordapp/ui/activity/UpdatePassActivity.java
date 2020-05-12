package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.UserDao;
import com.example.wordapp.model.db.DBManager;

import org.w3c.dom.Text;

public class UpdatePassActivity extends AppCompatActivity {
    private EditText et_update_username,et_update_password,et_update_repassword;
    private TextView tv_include;
    private ImageView iv_title_back;
    private Button btn_update;
    //获取用户表
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass);
        userDao = DBManager.getInstance().getUserDao();

        findView();
        initEvent();
    }

    private void initEvent() {
        //点击修改密码
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_update_username.getText().toString();
                String password = et_update_password.getText().toString();
                String repassword = et_update_repassword.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                        || TextUtils.isEmpty(repassword)){
                    Toast.makeText(UpdatePassActivity.this,"请输入完整的信息",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(repassword)){
                    Toast.makeText(UpdatePassActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    //修改密码
                    userDao.updatePassword(password,username);
                    Toast.makeText(UpdatePassActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    //退出
                    finish();
                }
            }
        });
    }

    private void findView() {
        et_update_username = findViewById(R.id.et_update_username);
        et_update_password = findViewById(R.id.et_update_password);
        et_update_repassword = findViewById(R.id.et_update_repassword);
        btn_update = findViewById(R.id.btn_update);
        tv_include = findViewById(R.id.tv_include);
        tv_include.setText("修改密码界面");
        iv_title_back = findViewById(R.id.iv_title_back);
        iv_title_back.setVisibility(View.GONE);
    }
}
