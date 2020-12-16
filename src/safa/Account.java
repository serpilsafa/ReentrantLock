package safa;

public class Account {
    private int balance = 10000;

    private void withdrawMoney(int amount){
        balance = balance - amount;
    }

    private void depositMoney(int amount){
        balance = balance + amount;
    }

    public static void moneyTransfer(Account account1, Account account2, int amount){
        account1.withdrawMoney(amount);
        account2.depositMoney(amount);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
