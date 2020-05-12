package com.example.wordapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "right")
    private int right;
    @ColumnInfo(name = "error")
    private int error;
    @ColumnInfo(name = "total")
    private int total;
    @ColumnInfo(name = "time")
    private String time;

    public Record(String username, int right, int error, int total,String time) {
        this.username = username;
        this.right = right;
        this.error = error;
        this.total = total;
        this.time = time;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
