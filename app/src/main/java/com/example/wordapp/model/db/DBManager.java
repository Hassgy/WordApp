package com.example.wordapp.model.db;

import androidx.room.Room;

import com.example.wordapp.MyApplication;
import com.example.wordapp.model.dao.RecordDao;
import com.example.wordapp.model.dao.SignDao;
import com.example.wordapp.model.dao.UserDao;
import com.example.wordapp.model.dao.WordsDao;

/**
 * 数据库操作类 在Application中初始化SQLite数据库
 */
public class DBManager {
    private static WordAppDataBase dataBase;
    static {
        //静态代码块在类加载的时候完成初始化
        dataBase = Room.databaseBuilder(MyApplication.getContext(),
                WordAppDataBase.class,"word_db")
                .allowMainThreadQueries()
                .build();
    }
    //基础的单例模式
    private static DBManager manager;
    private DBManager(){}
    public static DBManager getInstance() {
        if(manager == null){
            synchronized (DBManager.class){
                if(manager == null) {
                    manager = new DBManager();
                }
            }
        }
        return manager;
    }

    public void init(){
        //只是完成初始化，其中没有操作
    }
    //获取数据库对象
    public WordAppDataBase getDataBase(){
        return dataBase;
    }
    //获取用户数据表对象
    public UserDao getUserDao(){
        return dataBase.getUserDao();
    }
    //获取签到表
    public SignDao getSignDao(){
        return dataBase.getSignDao();
    }
    //获取单词表
    public WordsDao getWordsDao(){
        return dataBase.getWordsDao();
    }
    //获取答题记录表
    public RecordDao getRecordDao(){
        return dataBase.getRecordDao();
    }
}
