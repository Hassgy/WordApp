package com.example.wordapp.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wordapp.model.entity.Record;

import java.util.List;

@Dao
public interface RecordDao {
    //插入数据库数据
    @Insert
    void InsertRecord(Record... records);

    //根据用户名查询答题记录
    @Query("select * from Record where username = :username")
    List<Record> getAllRecord(String username);


}
