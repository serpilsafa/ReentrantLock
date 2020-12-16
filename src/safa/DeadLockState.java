package safa;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockState {
    private Account account1 = new Account();
    private Account account2 = new Account();
    private Random random = new Random();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void checkLock(Lock a, Lock b){

        boolean a_lock_have = false;
        boolean b_lock_have = false;

        while (true){

            a_lock_have = a.tryLock();
            b_lock_have = b.tryLock();

            if (a_lock_have & b_lock_have){
                return;
            }

            if (a_lock_have){
                a.unlock();
            }

            if (b_lock_have){
                b.unlock();
            }


        }


    }

    public void methodeOfThread1(){

        checkLock(lock1, lock2);

        for (int i = 0; i <5000; i++){
            Account.moneyTransfer(account1, account2, random.nextInt(100));

        }

        lock1.unlock();
        lock2.unlock();

    }

    public void methodeOfThread2(){
        checkLock(lock2, lock1);

        for (int i = 0; i <5000; i++){
            Account.moneyTransfer(account2, account1, random.nextInt(100));

        }

        lock1.unlock();
        lock2.unlock();

    }

    public void threadOver(){
        int total = account1.getBalance() + account2.getBalance();

        System.out.println("Account 1 balance: "+ account1.getBalance() + " Account 2 balance: "+ account2.getBalance());
        System.out.println("Total balance: "+ total);

    }
}
