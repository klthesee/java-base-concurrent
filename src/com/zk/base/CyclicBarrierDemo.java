package com.zk.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10);
        Thread[] threadArr = new Thread[10];
        int n = 10;
        for (int i=0;i<n;i++){
            threadArr[i] = new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"----"+"开始");
                try {
                    // 所有子线程会停在这里，等待所有子线程执行完成，当前子线程才继续执行
                    // countdownlatct 是阻塞主线程，latct.await()后子线程会继续执行
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()+"----"+"await");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"----"+"结束");
            });
        }
        for (int i=0;i<n;i++){
            threadArr[i].start();
        }
    }
}
