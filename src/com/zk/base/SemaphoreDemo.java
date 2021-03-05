package com.zk.base;

import java.util.concurrent.Semaphore;

/**
 * new Semaphore(n)：最多允许n个线程进入
 */

public class SemaphoreDemo {
    static int count = 1;

    static class MyThread extends Thread{
        private String threadName;
        private Semaphore sem;
        public MyThread(String threadName,Semaphore sem) {
            this.threadName = threadName;
            this.sem = sem;
        }

        @Override
        public void run() {
            try {
                while (count<=100){
                    sem.acquire();
                    if ("a".equals(threadName)){
                        if (count%3==1&&count<=100){
                            System.out.println(Thread.currentThread().getName() +" count " + count);
                            count++;
                        }
                    }else if ("b".equals(threadName)){
                        if (count%3==2&&count<=100){
                            System.out.println(Thread.currentThread().getName() +" count " + count);
                            count++;
                        }
                    }else if ("c".equals(threadName)){
                        if (count%3==0&&count<=100){
                            System.out.println(Thread.currentThread().getName() +" count " + count);
                            count++;
                        }
                    }
                    sem.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        MyThread t1 = new MyThread("a",sem);
        MyThread t2 = new MyThread("b",sem);
        MyThread t3 = new MyThread("c",sem);
        t1.setName("a");
        t2.setName("b");
        t3.setName("c");
        t1.start();
        t2.start();
        t3.start();
    }
}
