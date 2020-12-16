package safa;

public class Main {
    public static void main(String[] args) {
        DeadLockState deadLockState = new DeadLockState();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadLockState.methodeOfThread1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadLockState.methodeOfThread2();
            }
        });


        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        deadLockState.threadOver();


    }
}
