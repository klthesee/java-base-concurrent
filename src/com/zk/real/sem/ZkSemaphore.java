package com.zk.real.sem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 分别以公平锁请求，非公平锁请求，如果请求不到就进入CHL队列
 */
public class ZkSemaphore {

    static class Syn extends AbstractQueuedSynchronizer{
        public Syn(int permits) {
            setState(permits);
        }

        @Override
        // 申请锁 重写aqs流程方法给acquireSharedInterruptibly()调用
        protected int tryAcquireShared(int acquires) {
            for (; ; ) {
                int available = getState();
                int remaining = available - acquires;
                boolean b = compareAndSetState(available, remaining);
                if (remaining < 0 ||
                        b) { // 如果没有资源可以获取，或者获取锁成功。则返回
                    return remaining;
                }

            }
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            for (; ; ) {
                int current = getState();
                int next = current + releases;
                if (next<current) throw new Error("Maximum permit count exceeded");
                if (compareAndSetState(current, next)) {
                    return true;
                }
            }
        }
    }
    private final Syn syn;

    public ZkSemaphore(int avl) {
        this.syn = new Syn(avl);
    }

    void acquire() throws InterruptedException {
        syn.acquireSharedInterruptibly(1); // aqs写好了
    }

    void release(){
        syn.releaseShared(1);
    }

}
