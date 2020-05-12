package com.example.wordapp.utils;

import java.util.Random;

public class RandomUtils {
    /**
     * 随机取出max以内的随机数
     * @param max
     * @return
     */
    public static int getRandom(int max){
        Random random = new Random();
        return random.nextInt(max) + 1;
    }
}
