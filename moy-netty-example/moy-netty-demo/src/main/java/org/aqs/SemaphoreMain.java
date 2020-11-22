package org.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * say something
 *
 * @author <a href="moy25@foxmail.com">Ye Xiang Yang</a>
 * @since 2020/10/18
 */
public class SemaphoreMain {
    private final static Logger LOG = LoggerFactory.getLogger(CountDownLatchMain.class);

    public static void main(String[] args) throws InterruptedException {
        final Integer threadCount = 20;
        Semaphore semaphore = new Semaphore(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final Integer loopNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    doSomething(loopNum);
                } catch (InterruptedException e) {
                    LOG.error("Exception", e);
                } finally {
                    semaphore.release();

                }
            });
        }
        LOG.info("finish");
        executorService.shutdown();
    }

    private static void doSomething(Integer loopNum) throws InterruptedException {
        LOG.info("{}", loopNum);
        Thread.sleep(1000);

    }
}
