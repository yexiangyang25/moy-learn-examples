package org.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * say something
 *
 * @author <a href="moy25@foxmail.com">Ye Xiang Yang</a>
 * @since 2020/10/20
 */
public class FutureTaskMain {
    private final static Logger LOG = LoggerFactory.getLogger(FutureTaskMain.class);

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                LOG.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        LOG.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        LOG.info("result：{}", result);
    }
}
