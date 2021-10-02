package ru.matveykenya;

import java.util.Random;

public class AutoMaker implements Runnable{
    private final Shop shop;
    private final int maxCountAuto;
    private final Random random = new Random();
    private int productionTime = 3000;

    public AutoMaker(Shop shop, int maxCountAuto) {
        this.shop = shop;
        this.maxCountAuto = maxCountAuto;
    }

    public AutoMaker(Shop shop, int maxCountAuto, int productionTime) {
        this.shop = shop;
        this.maxCountAuto = maxCountAuto;
        this.productionTime = productionTime;
    }

    @Override
    public void run() {
        for (int i = 1; i <= maxCountAuto; i++) {
            try {
                Thread.sleep(productionTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == maxCountAuto) {
                System.out.println("*** Производится последняя машина ***");
                shop.close();
            }
            shop.put(makeAuto());
        }
    }

    private String makeAuto() {
        return Auto.values()[random.nextInt(Auto.values().length)].toString();
    }

    enum Auto{
        Volvo,
        Reno,
        BMW,
        Audi,
        Toyota,
        Suzuki
    }
}
