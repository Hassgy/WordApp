package com.example.wordapp.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wordapp.model.entity.Sign;

import java.util.List;

@Dao
public interface SignDao {
    //插入签到信息
    @Insert
    void InsertSignInfo(Sign... signs);

    //根据用户名查询签到信息
    @Query("select sign from Sign where time = :time and username = :username")
    LiveData<String> getSignInfo(String time,String username);

    //根据用户名查询签到时间
    @Query("select time from Sign where username = :username")
    String getSignTime(String username);

    //根据用户名查询签到信息
    @Query("select * from Sign where username = :username")
    List<Sign> getAllSignInfo(String username);
    //根据时间查询签到时间
    @Query("select sign from Sign where time = :time and username = :username")
    String isSign(String time,String username);
}
