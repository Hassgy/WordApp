package com.example.wordapp.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.RecordDao;
import com.example.wordapp.model.dao.WordsDao;
import com.example.wordapp.model.db.DBManager;
import com.example.wordapp.model.entity.Record;
import com.example.wordapp.utils.RandomUtils;
import com.example.wordapp.utils.SPUtils;

import java.security.SecurityPermission;
import java.util.Calendar;
import java.util.List;

public class ContestFragment extends BaseFragment {

    private Button btn_contest,btn_res,btn_apply;
    private ConstraintLayout cl_contest;
    private TextView tv_include,tv_context_info,tv_right_answer;
    private LinearLayout ll_contest,ll_toast,ll_right;
    //获取单词表
    private WordsDao wordsDao;
    //获取用户名
    private String username;
    //所有的单词
    private  List<String> wordsList;
    //随机获取的单词
    private String words;
    //正确的个数
    private int rightNum;
    //错误的个数
    private int ErrorNum;
    //切换试题
    private static final int DELAY_WHAT = 1;
    //题目个数
    private int totalNum;
    //获取答题记录表
    private RecordDao recordDao;
    private ImageView iv_title_back,iv_point;

    //做延时，题目切换
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case DELAY_WHAT:
                    //移除上一道题
                    ll_contest.removeAllViews();
                    ll_right.setVisibility(View.GONE);
                    //重新选题
                    //随机获取一个单词
                    words = wordsDao.getWords(RandomUtils.getRandom(wordsList.size()));
                    char[] chars = words.toCharArray();
                    //出第一道题
                    for (int i = 0; i < chars.length; i++) {
                        EditText editText = new EditText(getContext());
                        if(i % 2 == 0){
                            editText.setText("");
                        }else{
                            editText.setText(chars[i]+"");
                        }
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        editText.setLayoutParams(params);
                        ll_contest.addView(editText);
                    }
                    //根据单词查看解释
                    String wordsInfo = wordsDao.getWordsInfo(words);
                    tv_context_info.setText(wordsInfo);
                    break;
            }
        }
    };


    @Override
    protected void initView(View view) {
        btn_contest = view.findViewById(R.id.btn_contest);
        btn_res = view.findViewById(R.id.btn_res);
        btn_apply = view.findViewById(R.id.btn_apply);
        cl_contest = view.findViewById(R.id.cl_contest);
        tv_include = view.findViewById(R.id.tv_include);
        tv_include.setText("答题界面");
        tv_context_info = view.findViewById(R.id.tv_context_info);
        tv_right_answer = view.findViewById(R.id.tv_right_answer);
        ll_contest = view.findViewById(R.id.ll_contest);
        ll_toast = view.findViewById(R.id.ll_toast);
        ll_right = view.findViewById(R.id.ll_right);
        iv_title_back = view.findViewById(R.id.iv_title_back);
        iv_point = view.findViewById(R.id.iv_point);
        iv_title_back.setVisibility(View.GONE);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_contest;
    }

    @Override
    protected void initData() {
        super.initData();
        wordsDao = DBManager.getInstance().getWordsDao();
        recordDao = DBManager.getInstance().getRecordDao();
        username = SPUtils.getInstance().getStringInfo("username",null);
        //获取全部的单词
        wordsList = wordsDao.getAllWordsByUsername(username);
        //单击开始做题
        btn_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_contest.setVisibility(View.GONE);
                iv_point.setVisibility(View.GONE);
                cl_contest.setVisibility(View.VISIBLE);
                btn_res.setVisibility(View.VISIBLE);
                btn_apply.setVisibility(View.VISIBLE);
                ll_toast.setVisibility(View.VISIBLE);

                //随机获取一个单词
                words = wordsDao.getWords(RandomUtils.getRandom(wordsList.size()));
                char[] chars = words.toCharArray();
                //出第一道题
                for (int i = 0; i < chars.length; i++) {
                    EditText editText = new EditText(getContext());
                    if(i % 2 == 0){
                        editText.setText("");
                    }else{
                        editText.setText(chars[i]+"");
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    editText.setLayoutParams(params);
                    ll_contest.addView(editText);
                }
                //根据单词查看解释
                String wordsInfo = wordsDao.getWordsInfo(words);
                tv_context_info.setText(wordsInfo);
            }
        });
        //提交答案
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < ll_contest.getChildCount(); i++) {
                    EditText child = (EditText) ll_contest.getChildAt(i);
                    String s = child.getText().toString();
                    sb.append(s);
                }
                if(sb.toString().equals(words)){
                    Toast.makeText(getActivity(),"正确",Toast.LENGTH_SHORT).show();
                    rightNum++;
                }else{
                    ErrorNum++;
                }
                //题目++
                totalNum++;

                if(totalNum >= 10){

                    Toast.makeText(getActivity(),"已完成",Toast.LENGTH_SHORT).show();
                    //停止发送消息
                    handler.removeCallbacksAndMessages(null);
                    //按钮不可点击
                    btn_apply.setEnabled(false);
                    //初始化成绩单
                    View view = View.inflate(getActivity(),R.layout.layout_contest_result_dialog,null);
                    TextView tv_result_right,tv_result_error,tv_result_total;
                    tv_result_right = view.findViewById(R.id.tv_result_right);
                    tv_result_error = view.findViewById(R.id.tv_result_error);
                    tv_result_total = view.findViewById(R.id.tv_result_total);

                    tv_result_right.setText(rightNum+"");
                    tv_result_error.setText(ErrorNum+"");
                    tv_result_total.setText((rightNum*10)+"分");

                    ///弹出Dialog，显示成绩单
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                            .setView(view)
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Calendar calendar = Calendar.getInstance();
                                    int year = calendar.get(Calendar.YEAR);
                                    int month = calendar.get(Calendar.MONTH);
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                                    String nowTime = year+"/"+(month+1)+"/"+day;

                                    //将答题记录保存在数据库
                                    Record record = new Record(username,rightNum,ErrorNum,rightNum*10,nowTime);
                                    recordDao.InsertRecord(record);
                                    //恢复答题之前的状态
                                    cl_contest.setVisibility(View.GONE);
                                    btn_contest.setVisibility(View.VISIBLE);
                                    iv_point.setVisibility(View.VISIBLE);
                                    btn_apply.setEnabled(true);

                                    rightNum = 0;
                                    ErrorNum = 0;
                                    totalNum = 0;
                                    //清除面板
                                    ll_contest.removeAllViews();
                                    //发送消息
                                    handler.sendEmptyMessage(DELAY_WHAT);
                                }
                            });
                    //显示
                    dialog.show();
                }

                //发送延迟消息切换下一题
                handler.sendEmptyMessageDelayed(DELAY_WHAT,2000);
            }
        });
        //查看答案解析
        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示解析
//                ll_toast.setVisibility(View.VISIBLE);
                ll_right.setVisibility(View.VISIBLE);
                tv_right_answer.setText(words);
            }
        });
    }
}
