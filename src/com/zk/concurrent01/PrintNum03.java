package com.zk.concurrent01;

import java.util.concurrent.Exchanger;

/**
 *
 */

public class PrintNum03 {

//    public static void main(String[] args) {
//        Exchanger<Integer> aEcg = new Exchanger<>();
//        Exchanger<Integer> bEcg = new Exchanger<>();
//        Exchanger<Integer> cEcg = new Exchanger<>();
//        // a-->b-->c-->a
//        Runnable aR = ()->{
//            int a = 0;
//            try {
//                a = bEcg.exchange(a);
//                System.out.println(Thread.currentThread().getName()+ "a " + a);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//        Runnable bR = ()->{
//          int b = 2;
//          try {
//              b = cEcg.exchange(b);
//              System.out.println(Thread.currentThread().getName()+ "b " + b);
//          }catch (InterruptedException e){
//              e.printStackTrace();
//          }
//        };
//        Runnable cR = ()->{
//            int c = 3;
//            try {
//                c = aEcg.exchange(c);
//                System.out.println(Thread.currentThread().getName()+ "c " + c);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        };
//        Thread t1 = new Thread(aR);
//        Thread t2 = new Thread(bR);
//        Thread t3 = new Thread(cR);
//        t1.start();
//        t2.start();
//        t3.start();
//
//    }
}
