package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.SignDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Sign;
import com.example.wordapp.ui.adapter.SignInfoAdapter;
import com.example.wordapp.utils.SPUtils;

import java.util.List;

public class SignInfoActivity extends AppCompatActivity {
    private TextView tv_include;
    private ListView lv_sign;
    private ImageView iv_title_back;
    //获取用户名
    private String username;
    //适配器
    private SignInfoAdapter adapter;
    //获取签到表
    private SignDao signDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_info);
        username = SPUtils.getInstance().getStringInfo("username",null);

        tv_include = findViewById(R.id.tv_include);
        tv_include.setText(username+"签到信息");

        //获取全部签到信息
        signDao = DBManager.getInstance().getSignDao();
        List<Sign> signInfo = signDao.getAllSignInfo(username);

        lv_sign = findViewById(R.id.lv_sign);
        //设置适配器
        adapter = new SignInfoAdapter(this,signInfo);
        lv_sign.setAdapter(adapter);

        //返回按钮
        iv_title_back = findViewById(R.id.iv_title_back);
        iv_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一栈
                finish();
            }
        });
    }
}
