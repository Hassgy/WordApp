package com.example.wordapp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wordapp.R;
import com.example.wordapp.model.entity.Sign;
import com.example.wordapp.ui.fragment.BaseFragment;

import java.util.List;

public class SignInfoAdapter extends BaseAdapter {
    private final Context context;
    private final List<Sign> signInfo;

    public SignInfoAdapter(Context context, List<Sign> signInfo){
        this.context = context;
        this.signInfo = signInfo;
    }

    @Override
    public int getCount() {
        return signInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.layout_sign_info_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_sign_username = convertView.findViewById(R.id.tv_sign_username);
            viewHolder.tv_sign_info = convertView.findViewById(R.id.tv_sign_info);
            viewHolder.tv_sign_time = convertView.findViewById(R.id.tv_sign_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sign sign = signInfo.get(position);
        viewHolder.tv_sign_username.setText(sign.getUsername());
        viewHolder.tv_sign_time.setText(sign.getTime());
        if(sign.getIsSign().equals("已签到")){
            viewHolder.tv_sign_info.setText(sign.getIsSign());
        }else{
            viewHolder.tv_sign_info.setText("未签到！");
        }
        return convertView;
    }

    static class ViewHolder{
        TextView tv_sign_username,tv_sign_time,tv_sign_info;
    }
}
