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
import com.example.wordapp.model.dao.WordsDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Words;
import com.example.wordapp.utils.SPUtils;

import org.w3c.dom.Text;

public class WordsActivity extends AppCompatActivity {
    private TextView tv_include;
    private ImageView iv_title_back;
    private EditText et_newwords,et_wordsInfo,et_target;
    private Button btn_save;
    //获取单词表数据库
    private WordsDao wordsDao;
    //获取用户名
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        findView();
        initEvent();

        wordsDao = DBManager.getInstance().getWordsDao();
        username = SPUtils.getInstance().getStringInfo("username",null);
    }

    private void initEvent() {

        iv_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一级
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的信息
                String newwords = et_newwords.getText().toString();
                String wordsInfo = et_wordsInfo.getText().toString();
                String target = et_target.getText().toString();
                if(TextUtils.isEmpty(newwords) || TextUtils.isEmpty(wordsInfo)
                || TextUtils.isEmpty(target)){
                    Toast.makeText(WordsActivity.this,"请补全信息",Toast.LENGTH_SHORT).show();
                }else {
                    //保存单词到数据库
                    Words words = new Words(username, newwords, wordsInfo, target);
                    wordsDao.InsertWords(words);
                    //退出界面
                    finish();
                }
            }
        });
    }

    private void findView() {
        tv_include = findViewById(R.id.tv_include);
        iv_title_back = findViewById(R.id.iv_title_back);
        et_newwords = findViewById(R.id.et_newwords);
        et_wordsInfo = findViewById(R.id.et_wordsInfo);
        et_target = findViewById(R.id.et_target);
        btn_save = findViewById(R.id.btn_save);
        //设置为记单词
        tv_include.setText("记单词界面");
    }
}
