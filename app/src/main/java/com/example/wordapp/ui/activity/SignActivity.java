package com.example.wordapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.SignDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Sign;
import com.example.wordapp.utils.SPUtils;

public class SignActivity extends AppCompatActivity {
    private CalendarView calendar;
    //获取签到数据库表
    private SignDao signDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        calendar = findViewById(R.id.calendar);

        //点击签到
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String time = year+"/"+(month+1)+"/"+dayOfMonth;
                String username = SPUtils.getInstance().getStringInfo("username", null);
                signDao = DBManager.getInstance().getSignDao();
                //将点击的当前时间插入数据库
                Sign sign = new Sign(username,"已签到",time);
                signDao.InsertSignInfo(sign);
                //退出
                finish();
            }
        });
    }
}
