package ru.matveykenya;

public class Buyer implements Runnable{
    private final Shop shop;
    private final int TIME_OUT;

    public Buyer(Shop shop, int TIME_OUT) {
        this.shop = shop;
        this.TIME_OUT = TIME_OUT;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        while (true){
            System.out.println(name + " зашел в автосалон.");
            String auto = shop.get();
            if (auto != null){
                System.out.println(name + "------> покупает " + auto);
                System.out.println(name + " уезжает на новеньком авто");
                try {
                    Thread.sleep(TIME_OUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(name + " уходит домой пешком");
                break;
            }
        }
    }
}
