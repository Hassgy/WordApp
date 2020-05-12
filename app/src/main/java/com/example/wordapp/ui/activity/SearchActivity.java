package com.example.wordapp.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.WordsDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Words;
import com.example.wordapp.net.api.TranslateApi;
import com.example.wordapp.net.bean.Translate;
import com.example.wordapp.net.retrofit.RetrofitUtils;
import com.example.wordapp.utils.SPUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ImageView iv_back;
    private SearchView sv_main;
    private TextView tv_translate,tv_translate_view,tv_search_view,tv_search;
    //获取单词表
    private WordsDao wordsDao;
    //获取用户名
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        wordsDao = DBManager.getInstance().getWordsDao();
        username = SPUtils.getInstance().getStringInfo("username",null);
        findView();
        initEvent();

    }

    private void initEvent() {
        //返回
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上级
                finish();
            }
        });
        //开始搜索
        sv_main.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("TAG",query);
                //执行网络请求
                TranslateApi translateApi = RetrofitUtils.getRetrofit().create(TranslateApi.class);
                Call<Translate> call = translateApi.
                        getTranslate("gtx", "t", 1, "UTF-8", "auto", "zh", query);
                call.enqueue(new Callback<Translate>() {
                    @Override
                    public void onResponse(Call<Translate> call, Response<Translate> response) {
                        Translate translate = response.body();
                        Translate.SentencesBean sentencesBean = translate.getSentences().get(0);
                        String trans = sentencesBean.getTrans();
                        //显示翻译
                        tv_translate.setVisibility(View.VISIBLE);
                        tv_translate_view.setVisibility(View.VISIBLE);
                        tv_search.setVisibility(View.VISIBLE);
                        tv_search_view.setVisibility(View.VISIBLE);
                        tv_search.setText(query);
                        tv_translate.setText(trans);
                        Log.e("TAG",translate.toString());
                    }

                    @Override
                    public void onFailure(Call<Translate> call, Throwable t) {
                        Log.e("TAG","请求失败");
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //点击生词保存
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出Dialog提示是否保存
                AlertDialog.Builder dialog = new AlertDialog.Builder(SearchActivity.this)
                        .setTitle("要保存该单词吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = new EditText(SearchActivity.this);
                                editText.setHint("请为该单词设置标签");
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT);
                                editText.setLayoutParams(params);
                                editText.setTextSize(20);
                                //弹出新的Dialog，为该单词选择标签
                                AlertDialog.Builder saveDialog = new AlertDialog.Builder(SearchActivity.this)
                                        .setView(editText)
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //保存单词到数据库
                                                String target = editText.getText().toString();
                                                String words = tv_search.getText().toString();
                                                String wordsinfo = tv_translate.getText().toString();
                                                Words newwords = new Words(username,words,wordsinfo,target);
                                                wordsDao.InsertWords(newwords);
                                                dialog.dismiss();
                                            }
                                        });
                                saveDialog.show();
                            }
                        });
                dialog.show();
            }
        });
    }

    private void findView() {
        iv_back = findViewById(R.id.iv_back);
        sv_main = findViewById(R.id.sv_main);
        tv_translate = findViewById(R.id.tv_translate);
        tv_translate_view = findViewById(R.id.tv_translate_view);
        tv_search_view = findViewById(R.id.tv_search_view);
        tv_search = findViewById(R.id.tv_search);
    }
}
