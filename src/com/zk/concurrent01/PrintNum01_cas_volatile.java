package com.zk.concurrent01;

/**
 *3个线程依次打印1-100
 * 例如 a1 b2 c3 a4 b5 ...
 */
public class PrintNum01_cas_volatile {
    static volatile int count = 1;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (count<=100){
                while (count%3!=1&&count<=100){

                }

                if (count<=100){
                    System.out.println(Thread.currentThread().getName()+" count:"+ count);
                    count++;
                }
            }

        });
        t1.setName("a");

        Thread t2 = new Thread(()->{
            while (count<=100) {
                while (count%3!=2&&count<=100){

                }
                if (count<=100){
                    System.out.println(Thread.currentThread().getName()+" count:"+ count);
                    count++;
                }
            }

        });
        t2.setName("b");

        Thread t3 = new Thread(()->{
            while (count<=100) {
                while (count%3!=0&&count<=100){
                }
                if (count<=100){
                    System.out.println(Thread.currentThread().getName()+" count:"+ count);
                    count++;
                }

            }

        });
        t3.setName("c");

        t1.start();
        t2.start();
        t3.start();
    }
}
