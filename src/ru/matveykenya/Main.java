package ru.matveykenya;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop();
        AutoMaker autoMaker = new AutoMaker(shop, 10, 2000);
        Buyer buyer = new Buyer(shop, 1000);

        Thread buyer1 = new Thread(null, buyer, "Вася");
        //buyer1.setDaemon(true);
        Thread buyer2 = new Thread(null, buyer, "Петя");
        //buyer2.setDaemon(true);
        Thread buyer3 = new Thread(null, buyer, "Лариса");
        //buyer3.setDaemon(true);
        Thread buyer4 = new Thread(null, buyer, "Катя");
        //buyer4.setDaemon(true);

        //стартуем
        new Thread(autoMaker).start();
        buyer1.start();
        buyer2.start();
        buyer3.start();
        buyer4.start();

    }
}
