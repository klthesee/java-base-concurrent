package com.zk.real.sem;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ZkSemaphore zkSemaphore = new ZkSemaphore(5);
        zkSemaphore.acquire();
        zkSemaphore.acquire();

        zkSemaphore.release();
        zkSemaphore.acquire();

    }
}
