package com.zk.real.sem;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 分别以公平锁请求，非公平锁请求，如果请求不到就进入CHL队列
 */
public class ZkSemaphore {
    int permits;
    boolean isFair;
    private AbstractQueuedSynchronizer syn;

    public void acquire() throws InterruptedException {
        syn.acquireSharedInterruptibly(1);
    }

    public ZkSemaphore(int permits) {
        syn = new NoFairSem(permits);
    }

    public ZkSemaphore(int permits,boolean isFair) {
        syn = isFair?new NoFairSem(permits):new FairSem(permits);
    }

    /**
     * 不公平的也写在到，
     */
    static class Syn extends AbstractQueuedSynchronizer{
        public Syn(int permits) {
            setState(permits);
        }
        // 获取锁逻辑下到这个方法：获取锁,获取失败进入CLH队列
        final int nonfairTryAcquireShared(int acquires) {
            for (;;) {
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 ||
                        compareAndSetState(available, remaining))
                    return remaining;
            }
        }

        // 重写父类，让父类多态调用
        @Override
        protected int tryAcquireShared(int acquires) {
            return nonfairTryAcquireShared(acquires);
        }

    }

    static class NoFairSem extends Syn{
        public NoFairSem(int permits) {
            super(permits);
        }
    }

    /**
     * 公平的写到这
     */
    static class FairSem extends Syn{
        public FairSem(int permits) {
            super(permits);
        }
    }

}
