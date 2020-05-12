package com.example.wordapp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapp.R;
import com.example.wordapp.model.entity.Record;

import java.util.List;

public class RecordListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Record> record;

    public RecordListAdapter(Context context, List<Record> record){
        this.context  = context;
        this.record = record;
    }

    @Override
    public int getCount() {
        return record.size();
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
            convertView = View.inflate(context, R.layout.layout_record_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_record_username = convertView.findViewById(R.id.tv_record_username);
            viewHolder.tv_record_time = convertView.findViewById(R.id.tv_record_time);
            viewHolder.tv_record_right = convertView.findViewById(R.id.tv_record_right);
            viewHolder.tv_record_error = convertView.findViewById(R.id.tv_record_error);
            viewHolder.tv_record_total = convertView.findViewById(R.id.tv_record_total);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_record_username.setText(record.get(position).getUsername());
        viewHolder.tv_record_time.setText(record.get(position).getTime());
        viewHolder.tv_record_right.setText(record.get(position).getRight()+"");
        viewHolder.tv_record_error.setText(record.get(position).getError()+"");
        viewHolder.tv_record_total.setText(record.get(position).getTotal()+"");
        return convertView;
    }

    static class ViewHolder{
        TextView tv_record_username,tv_record_time,tv_record_right,tv_record_error,tv_record_total;
    }
}
