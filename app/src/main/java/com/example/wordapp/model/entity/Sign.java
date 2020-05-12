package com.example.wordapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sign {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "sign")
    private String isSign;
    @ColumnInfo(name = "time")
    private String time;

    public Sign(String username, String isSign, String time) {
        this.username = username;
        this.isSign = isSign;
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

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
