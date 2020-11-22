package org.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * say something
 *
 * @author <a href="moy25@foxmail.com">Ye Xiang Yang</a>
 * @since 2020/10/20
 */
public class FutureMain {
    private final static Logger LOG = LoggerFactory.getLogger(FutureMain.class);


    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            LOG.info("do something in callable");
            Thread.sleep(5000);
            return "done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new MyCallable());
        LOG.info("do something in main");
        Thread.sleep(1000);
        String result = submit.get();
        LOG.info("result = {}", result);
        executorService.shutdown();
    }
}
