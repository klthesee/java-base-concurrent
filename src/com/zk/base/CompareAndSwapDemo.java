package com.zk.base;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;



public class CompareAndSwapDemo extends AbstractQueuedSynchronizer {
    static class T03 extends AbstractQueuedSynchronizer{
        boolean myCompareAndSetState(){
            // expect是和state比较 cas的关键
            setState(1);
            boolean b = compareAndSetState(1, 1);
            boolean c = compareAndSetState(1, 0);
            return b;
        }
    }

    public static void main(String[] args) {
        T03 t03 = new T03();
        boolean b = t03.myCompareAndSetState();
        System.out.println(b);

    }

}
