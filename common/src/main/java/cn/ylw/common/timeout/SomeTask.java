package cn.ylw.common.timeout;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 任务
 *
 * @author yanluwei
 * @date 2021/8/12
 */
public class SomeTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return get();
    }

    private String get() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> stringFutureTask = new FutureTask<>(new SomeTask());
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        String s = stringFutureTask.get(6000, TimeUnit.MILLISECONDS);
        System.out.println(s);
    }
}
