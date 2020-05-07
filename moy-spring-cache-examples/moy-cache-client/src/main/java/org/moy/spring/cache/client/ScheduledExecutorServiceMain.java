package org.moy.spring.cache.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceMain {

    /**
     * 线程池
     */
    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        int time = 1000 * 3;
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
                    String format = simpleDateFormat.format(new Date());
                    System.err.println(Thread.currentThread().getName() + ":" + format);
                    // 出现异常 后续任务都不会执行
                    if (true) {
                        throw new RuntimeException("中断任务");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }, time, time, TimeUnit.MILLISECONDS);

        Thread.sleep(1000 * 10);
        service.shutdown();
    }
}
