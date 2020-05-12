package com.example.wordapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        //加载动态库
        System.load("E:\\新建文件夹\\ndk\\example\\CMakeProject17\\out\\build\\x64-Debug\\lay.dll");

        add(1,2);
    }

    native void add(int i,int j);
}