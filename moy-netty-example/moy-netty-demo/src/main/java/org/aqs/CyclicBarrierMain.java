package org.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * say something
 *
 * @author <a href="moy25@foxmail.com">Ye Xiang Yang</a>
 * @since 2020/10/18
 */
public class CyclicBarrierMain {

    private final static Logger LOG = LoggerFactory.getLogger(CyclicBarrierMain.class);

    static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        LOG.info("await finish");
    });

    public static void main(String[] args) throws InterruptedException {
        final Integer taskNum = 10;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < taskNum; i++) {
            final Integer loopNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    doSomething(loopNum);
                } catch (InterruptedException | BrokenBarrierException e) {
                    LOG.error("exception", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void doSomething(Integer loopNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        LOG.info("{} is ready", loopNum);
        cyclicBarrier.await();
        LOG.info("{} continue", loopNum);

    }
}
