package virtusx.androidstudyapplication;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static virtusx.androidstudyapplication.MainActivity.order;

class Client extends Thread {

    private MainActivity sp;


    Client(MainActivity _sp) {
        sp = _sp;
    }

    private final Lock lock = new ReentrantLock();

    void orderHamburger(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.Hamburger));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.OrderHamburger));
        }
    }

    void orderSandwich(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.Cheeseburger));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.OrderCheeseburger));
        }
    }

    void orderFry(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.Fry));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.OrderFry));
        }
    }

    void orderNuggets(List<String> i) {

        if (lock.tryLock()) {
            i.add(sp.getString(R.string.McNuggets));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.OrderMcNuggets));
        }
    }

    void orderMuffin(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.Muffin));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.OrderMuffin));
        }
    }

    public void run() {
            while(true)
            {
                System.out.print("Client" + this.toString() + "alive is " + sp.alive + "\n");
                while (sp.alive) {
                    Random rand = new Random();
                    try {
                        Thread.sleep((rand.nextInt(3)) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (sp.alive) {
                        switch (rand.nextInt(5)) {
                            case 0:
                                this.orderHamburger(order);
                                break;
                            case 1:
                                this.orderSandwich(order);
                                break;
                            case 2:
                                this.orderFry(order);
                                break;
                            case 3:
                                this.orderNuggets(order);
                                break;
                            case 4:
                                this.orderMuffin(order);
                                break;
                        }
                        if (order.size() < 10)
                            sp.runOnUiThread(() -> sp.doClient.setText(sp.getString(R.string.InOrder) + order.size()));
                        else
                            sp.runOnUiThread(() -> sp.doClient.setText(sp.getString(R.string.TooManyOrders) + order.size() + sp.getString(R.string.TooManyOrders2)));
                        try {
                            Thread.sleep(7000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
