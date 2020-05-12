package com.example.wordapp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.SignDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.ui.activity.SearchActivity;
import com.example.wordapp.ui.activity.SignActivity;
import com.example.wordapp.ui.activity.WordsActivity;
import com.example.wordapp.ui.activity.WordsRecordActivity;
import com.example.wordapp.utils.SPUtils;

import java.util.Calendar;

/**
 * 主页
 */
public class MainFragment extends BaseFragment {
    private ImageView iv_search;
    private RelativeLayout rl_main,rl_words,rl_words_record;
    private TextView tv_sign;
    //获取签到表
    private SignDao signDao;
    //获取用户名
    private String username;
    @Override
    protected void initView(View view) {
        iv_search = view.findViewById(R.id.iv_search);
        rl_main = view.findViewById(R.id.rl_main);
        rl_words = view.findViewById(R.id.rl_words);
        rl_words_record = view.findViewById(R.id.rl_words_record);
        tv_sign = view.findViewById(R.id.tv_sign);
        initEvent();
    }

    private void initEvent() {

        //执行查询操作
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        //签到
        rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SignActivity.class));
            }
        });
        //记单词
        rl_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入记单词界面
                startActivity(new Intent(getActivity(), WordsActivity.class));

            }
        });
        //查看记录的单词
        rl_words_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WordsRecordActivity.class));
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        super.initData();
        username = SPUtils.getInstance().getStringInfo("username",null);
        signDao = DBManager.getInstance().getSignDao();
        //根据当前时间，查询是否签到
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String nowTime = year+"/"+(month+1)+"/"+day;
        String sign = signDao.isSign(nowTime, username);
        if(sign != null){
            rl_main.setEnabled(false);
            tv_sign.setText("已签到");
        }else{
            rl_main.setEnabled(true);
            tv_sign.setText("签到");
        }
        //查询当前的签到情况
        signDao.getSignInfo(nowTime,username).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //当状态发生变化时，在这里回调

                if( s != null && s.equals("已签到")) {
                    tv_sign.setText(s);
                    //不可点击
                    rl_main.setEnabled(false);
                }
//                }else{
//                    rl_main.setEnabled(true);
//                    tv_sign.setText("签到");
//                }
            }
        });
    }
}
