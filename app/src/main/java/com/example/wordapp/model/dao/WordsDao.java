package com.example.wordapp.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wordapp.model.entity.Words;

import java.util.List;

@Dao
public interface WordsDao {
    //插入单词到数据库
    @Insert
    void InsertWords(Words... words);

    //查询全部单词信息
    @Query("select * from Words where username = :username")
    List<Words> getAllWords(String username);

    //获取全部的标签
    @Query("select target from Words where username = :username")
    List<String> getAllTargets(String username);

    //根据标签获取全部的单词
    @Query("select words from words where target = :target")
    List<String> getWordsByTarget(String target);

    //根据id获取单词
    @Query("select words from Words where _id = :id")
    String getWords(int id);

    //获取单词表中的全部单词
    @Query("select words from Words where username = :username")
    List<String> getAllWordsByUsername(String username);

    //根据单词获取单词释义
    @Query("select words_info from Words where words = :words")
    String getWordsInfo(String words);
}
