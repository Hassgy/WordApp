package com.example.wordapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Words {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "words")
    private String words;
    @ColumnInfo(name = "words_info")
    private String wordsInfo;
    @ColumnInfo(name = "target")
    private String target;

    public Words(String username, String words, String wordsInfo, String target) {
        this.username = username;
        this.words = words;
        this.wordsInfo = wordsInfo;
        this.target = target;
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

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getWordsInfo() {
        return wordsInfo;
    }

    public void setWordsInfo(String wordsInfo) {
        this.wordsInfo = wordsInfo;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
