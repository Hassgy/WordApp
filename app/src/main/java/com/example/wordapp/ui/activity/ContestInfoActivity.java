package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.RecordDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Record;
import com.example.wordapp.ui.adapter.RecordListAdapter;
import com.example.wordapp.utils.SPUtils;

import java.util.List;

public class ContestInfoActivity extends AppCompatActivity {
    private TextView tv_include;
    private ListView lv_contest;
    private ImageView iv_title_back;
    //获取用户名
    private String username;
    //获取成绩单表
    private RecordDao recordDao;
    //适配器
    private RecordListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_info);
        username = SPUtils.getInstance().getStringInfo("username",null);

        //成绩单
        recordDao = DBManager.getInstance().getRecordDao();
        List<Record> record = recordDao.getAllRecord(username);
        findView();

        adapter = new RecordListAdapter(this,record);
        lv_contest.setAdapter(adapter);

        //返回按钮
        iv_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一栈
                finish();
            }
        });
    }

    private void findView() {
        tv_include = findViewById(R.id.tv_include);
        tv_include.setText(username+"答题信息");

        lv_contest = findViewById(R.id.lv_contest);
        iv_title_back = findViewById(R.id.iv_title_back);
    }
}
