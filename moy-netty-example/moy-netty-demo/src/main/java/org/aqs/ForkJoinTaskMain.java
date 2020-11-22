package org.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * say something
 *
 * @author <a href="moy25@foxmail.com">Ye Xiang Yang</a>
 * @since 2020/10/20
 */
public class ForkJoinTaskMain  extends RecursiveTask<Integer> {

    private final static Logger LOG = LoggerFactory.getLogger(ForkJoinTaskMain.class);

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskMain(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskMain leftTask = new ForkJoinTaskMain(start, middle);
            ForkJoinTaskMain rightTask = new ForkJoinTaskMain(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool();

        //生成一个计算任务，计算1+2+3+4
        ForkJoinTaskMain task = new ForkJoinTaskMain(1, 100);

        //执行一个任务
        Future<Integer> result = forkjoinPool.submit(task);

        try {
            LOG.info("result:{}", result.get());
        } catch (Exception e) {
            LOG.error("exception", e);
        }
    }
}
