package ru.mirea.data.MainClasses;

public class Balance {
    int id;
    long balance;
    Currency curr;

    public Balance(int id, long balance, Currency curr) {
        this.id = id;
        this.balance = balance;
        this.curr = curr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Currency getCurr() {
        return curr;
    }

    public void setCurr(Currency curr) {
        this.curr = curr;
    }
}
