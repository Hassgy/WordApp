package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.WordsDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Words;
import com.example.wordapp.ui.adapter.WordsListAdapter;
import com.example.wordapp.utils.RvDecoration;
import com.example.wordapp.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class WordsRecordActivity extends AppCompatActivity {
    private TextView tv_include;
    //列表
    private RecyclerView rv_words_list;
    private ImageView iv_title_back;

    //获取单词表
    private WordsDao wordsDao;

    //获取用户名
    private String username;

    //标签List集合
    private List<String> targetList;

    //适配器
    private WordsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_record);
        findView();

        wordsDao = DBManager.getInstance().getWordsDao();
        username = SPUtils.getInstance().getStringInfo("username",null);
        //获取全部的单词信息
        List<Words> wordsList = wordsDao.getAllWords(username);

        //标签集合
        targetList = new ArrayList<>();
        for (int i = 0; i < wordsList.size(); i++) {
            Words words = wordsList.get(i);
            String target = words.getTarget();
            if(!targetList.contains(target)){
                targetList.add(target);
            }
        }

        initAdapter();

        iv_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出当前界面
                finish();
            }
        });

    }

    private void initAdapter() {
        //设置适配器
        adapter = new WordsListAdapter(this,targetList);
        rv_words_list.setAdapter(adapter);
        //设置布局管理器
        rv_words_list.setLayoutManager(new LinearLayoutManager(this));
        //设置间隔
        rv_words_list.addItemDecoration(new RvDecoration(10));
    }

    private void findView() {

        tv_include = findViewById(R.id.tv_include);
        tv_include.setText("单词表");
        rv_words_list = findViewById(R.id.rv_words_list);
        iv_title_back = findViewById(R.id.iv_title_back);
    }
}
