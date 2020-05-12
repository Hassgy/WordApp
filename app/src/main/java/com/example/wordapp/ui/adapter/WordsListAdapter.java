package com.example.wordapp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapp.R;
import com.example.wordapp.model.dao.WordsDao;
import com.example.wordapp.model.db.DBManager;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class WordsListAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<String> targetList;

    //获取单词表
    private WordsDao wordsDao = DBManager.getInstance().getWordsDao();

    public WordsListAdapter(Context context, List<String> targetList){
        this.context = context;
        this.targetList = targetList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_words_list_item,
                parent,false);
        return new WordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //绑定数据
        if(holder instanceof WordsViewHolder){
            String target = targetList.get(position);
            ((WordsViewHolder) holder).tv_target.setText(target);
            //根据target获取当前target下的单词
            List<String> wordsByTarget = wordsDao.getWordsByTarget(target);
            for (int i = 0; i < wordsByTarget.size(); i++) {
                TextView textView = new TextView(context);
                textView.setText(wordsByTarget.get(i));
                textView.setTextSize(20);
                textView.setPadding(5,5,5,5);
                textView.setTextColor(Color.BLACK);
                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.rightMargin = 10;
                params.leftMargin  =10;
                params.topMargin  =10;
                params.bottomMargin = 10;
                textView.setLayoutParams(params);
                textView.setBackgroundResource(R.drawable.tv_bg);
                ((WordsViewHolder) holder).fl_words.addView(textView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return targetList.size();
    }

    class WordsViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_target;
        private FlexboxLayout fl_words;
        public WordsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_target = itemView.findViewById(R.id.tv_target);
            fl_words = itemView.findViewById(R.id.fl_words);
        }
    }
}
