package ru.mirea.data.MainClasses;

public class Currency {
    String type;
    int curToEUR;
    int curToUSD;
    int curToRUB;

    public Currency(String type, int curToEUR, int curToUSD, int curToRUB) {
        this.type = type;
        this.curToEUR = curToEUR;
        this.curToUSD = curToUSD;
        this.curToRUB = curToRUB;
    }

    public int getCurToRUB() {
        return curToRUB;
    }

    public void setCurToRUB(int curToRUB) {
        this.curToRUB = curToRUB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurToEUR() {
        return curToEUR;
    }

    public void setCurToEUR(int curToEUR) {
        this.curToEUR = curToEUR;
    }

    public int getCurToUSD() {
        return curToUSD;
    }

    public void setCurToUSD(int curToUSD) {
        this.curToUSD = curToUSD;
    }
}
