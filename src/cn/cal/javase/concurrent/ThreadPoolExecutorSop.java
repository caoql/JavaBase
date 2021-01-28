package cn.cal.javase.concurrent;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorSop {
    public static void main(String[] args) {
        ExecutorService executorService = getExecutorService();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "---");
            });
        }
        executorService.shutdown();
    }

    private static ExecutorService getExecutorService() {
        int cpuCoreSize = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(10);
        ThreadFactory threadFactory = new MyThreadFactory("调度线程");
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(cpuCoreSize + 1, 2 * cpuCoreSize, 1000, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);
        int n = threadPoolExecutor.prestartAllCoreThreads();
        boolean flag = threadPoolExecutor.prestartCoreThread();
        return threadPoolExecutor;
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(0);

        private final String namePrefix;

        public MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, namePrefix + "_" + threadNumber.getAndIncrement());
        }

    }
}
