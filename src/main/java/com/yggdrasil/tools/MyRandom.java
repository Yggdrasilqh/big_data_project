package com.yggdrasil.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by yggdrasil on 2017/3/16.
 */
public class MyRandom {

    public int getRandomNumberBetween(int min,int max) {
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }

    public <E> E getRandomNumberIn(List<E> list) {
        int min = 0;
        int max = list.size();
        return list.get(getRandomNumberBetween(min, max));
    }

    public Date getRandomDate(Date min, Date max) {
        double random = Math.random();
        double interval = (random * (max.getTime() - min.getTime()));
        long timeStamp = min.getTime() + (long) interval;
        return new Date(timeStamp);
    }
}
