package com.example.wordapp.model.db;

import android.media.tv.TvContract;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.wordapp.model.dao.RecordDao;
import com.example.wordapp.model.dao.SignDao;
import com.example.wordapp.model.dao.UserDao;
import com.example.wordapp.model.dao.WordsDao;
import com.example.wordapp.model.entity.Record;
import com.example.wordapp.model.entity.Sign;
import com.example.wordapp.model.entity.User;
import com.example.wordapp.model.entity.Words;

@Database(entities = {User.class, Sign.class, Words.class, Record.class},version = 1,exportSchema = false)
public abstract class WordAppDataBase extends RoomDatabase {

    //获取用户表的操作类
    public abstract UserDao getUserDao();

    //获取签到表的操作类
    public abstract SignDao getSignDao();

    //获取单词表
    public abstract WordsDao getWordsDao();

    //答题记录
    public abstract RecordDao getRecordDao();
}
