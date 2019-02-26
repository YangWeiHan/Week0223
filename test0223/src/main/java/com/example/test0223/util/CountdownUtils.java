package com.example.test0223.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CountdownUtils {
    public static Observable<Integer> countdown(int time){
        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0,1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(o ->{
                    return countTime - o.intValue();
                }).take(countTime + 1);
    }
}
