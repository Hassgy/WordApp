package com.example.wordapp.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.wordapp.model.entity.User;

import java.util.List;

/**
 * User 用户表的操作类
 */
@Dao
public interface UserDao {
    //往数据表中插入用户信息
    @Insert
    void InsertUser(User... users);

    //根据用户名查询密码
    //select password from user where username = ?;
    @Query("select password from User where username = :username")
    String getPassFromUser(String username);

    //查询全部的用户名
    //select username from User;
    @Query("select username from User")
    List<String> getAllUser();

    //根据用户名修改密码
    @Query("update User set password = :password where username = :username")
    void updatePassword(String password,String username);
}
