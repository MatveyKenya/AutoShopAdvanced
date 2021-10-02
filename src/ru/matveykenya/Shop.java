package ru.matveykenya;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {

    private final List<String> listAuto = new ArrayList<>();
    private boolean autoInProduction = true;
    Lock seller = new ReentrantLock(true);
    Condition condition = seller.newCondition();

    public void put(String auto){
        seller.lock();
        try {
            listAuto.add(auto);
            System.out.println("\nПроизведен новый " + auto + " --- " + this);
            condition.signalAll();
        } finally {
            seller.unlock();
        }
    }

    public String get() {
        seller.lock();
        try{
            while (!isAuto()){
                if (!isAutoInFuture()){
                    return null;
                }
                System.out.println("Машин нет - " + Thread.currentThread().getName() + " ждет...");
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            seller.unlock();
        }

        return isAuto() ? listAuto.remove(0) : null;
    }

    public boolean isAuto() {
        return listAuto.size() > 0;
    }

    public boolean isAutoInFuture() {
        return autoInProduction;
    }

    public void close() {
        autoInProduction = false;
    }

    @Override
    public String toString() {
        return "В Автосалоне в наличии - " + listAuto;
    }
}
