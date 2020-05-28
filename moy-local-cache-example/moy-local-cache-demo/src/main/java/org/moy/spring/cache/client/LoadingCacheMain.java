package org.moy.spring.cache.client;


import com.google.common.cache.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoadingCacheMain {

    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            // 设置大小，条目数
            .maximumSize(3000)
            // 设置失效时间，创建时间
            .expireAfterWrite(20, TimeUnit.SECONDS)
            // 设置时效时间，最后一次被访问
            .expireAfterAccess(10, TimeUnit.SECONDS)
            // 移除缓存的监听器
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                }
            })
            // 缓存构建的回调
            .build(new CacheLoader<String, String>() {
                //加载缓存
                @Override
                public String load(String key) throws Exception {
                    System.err.println(Thread.currentThread().getName() + " : load ..." + key);
                    return getString(key);
                }
            });

    private static String getString(String key) {
        System.err.println(Thread.currentThread().getName() + " : 加载数据..." + key);
        String format = getTimeString();
        return format + "_" + key;
    }

    private static String getTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss ");
        return simpleDateFormat.format(new Date());
    }


    public static void main(String[] args) throws Exception {
        //全部清除缓存
        cache.invalidateAll();
        String key = "test_key";
        // 写
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000 * 10);
                        cache.put(key, getString(key));
                        System.err.println(getTimeString() + Thread.currentThread().getName() + " key : " + key + " asMap :" + cache.asMap());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "writeThread");
//        writeThread.start();

        // 读
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000 * 3);
                        System.err.println(getTimeString() + Thread.currentThread().getName() + " getIfPresent : " + cache.getIfPresent(key));
                        System.err.println(getTimeString() + Thread.currentThread().getName() + " get : " + cache.get(key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "readThread");
        readThread.start();

        readThread.join();
        writeThread.join();
    }
}
