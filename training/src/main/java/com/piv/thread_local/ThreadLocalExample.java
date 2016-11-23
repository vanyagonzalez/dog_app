package com.piv.thread_local;

/**
 * commit 1
 */
public class ThreadLocalExample {
    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>();

        @Override
        public void run() {
            Thread t = Thread.currentThread();
            int randomInt = (int) (Math.random() * 100D);
            System.out.println(t.getName() + " " + randomInt);
            threadLocal.set(randomInt);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(t.getName() + " " + threadLocal.get());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}
