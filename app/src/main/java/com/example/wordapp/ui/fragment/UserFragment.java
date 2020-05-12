package com.example.wordapp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wordapp.R;
import com.example.wordapp.ui.activity.ContestInfoActivity;
import com.example.wordapp.ui.activity.LoginActivity;
import com.example.wordapp.ui.activity.SignInfoActivity;
import com.example.wordapp.utils.SPUtils;

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_include,tv_username,tv_signInfo,tv_testInfo;
    private ImageView iv_touxiang,iv_title_back;
    private Button btn_loginout;
    //获取登录用户名
    private String username;
    @Override
    protected void initView(View view) {
        findView(view);
        initEvent();
    }

    private void initEvent() {
        //设置单击事件
        tv_signInfo.setOnClickListener(this);
        tv_testInfo.setOnClickListener(this);
        btn_loginout.setOnClickListener(this);
    }

    private void findView(View view) {
        tv_include = view.findViewById(R.id.tv_include);
        iv_title_back = view.findViewById(R.id.iv_title_back);
        iv_title_back.setVisibility(View.GONE);
        tv_include.setText("用户");
        tv_username = view.findViewById(R.id.tv_username);
        tv_signInfo = view.findViewById(R.id.tv_signInfo);
        tv_testInfo = view.findViewById(R.id.tv_testInfo);
        iv_touxiang = view.findViewById(R.id.iv_touxiang);
        btn_loginout = view.findViewById(R.id.btn_loginout);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initData() {
        super.initData();
        //设置用户名
        username = SPUtils.getInstance().getStringInfo("username",null);
        tv_username.setText(username);
        //设置头像（Glide圆角处理）
        Glide.with(this).load(R.drawable.icon).circleCrop().into(iv_touxiang);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_signInfo:
                //签到信息
                startActivity(new Intent(getActivity(), SignInfoActivity.class));
                break;
            case R.id.tv_testInfo:
                //答题信息
                startActivity(new Intent(getActivity(), ContestInfoActivity.class));
                break;
            case R.id.btn_loginout:
                //退出登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //将登录信息清除
                SPUtils.getInstance().saveInfo("login",false);
                getActivity().finish();
                break;
        }
    }
}
