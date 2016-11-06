package virtusx.androidstudyapplication;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static virtusx.androidstudyapplication.MainActivity.ordersQueue;

class Client extends Thread {

    private MainActivity sp;


    Client(MainActivity _sp) {
        sp = _sp;
    }

    private final Lock lock = new ReentrantLock();

    private void orderHamburger(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.hamburger));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.orderHamburger));
        }
    }

    private void orderSandwich(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.cheeseburger));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.orderCheeseburger));
        }
    }

    private void orderFry(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.fry));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.orderFry));
        }
    }

    private void orderNuggets(List<String> i) {

        if (lock.tryLock()) {
            i.add(sp.getString(R.string.mcNuggets));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.orderMcNuggets));
        }
    }

    private void orderMuffin(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.muffin));
            sp.runOnUiThread(() -> sp.doClient.setText(R.string.orderMuffin));
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
                                this.orderHamburger(ordersQueue);
                                break;
                            case 1:
                                this.orderSandwich(ordersQueue);
                                break;
                            case 2:
                                this.orderFry(ordersQueue);
                                break;
                            case 3:
                                this.orderNuggets(ordersQueue);
                                break;
                            case 4:
                                this.orderMuffin(ordersQueue);
                                break;
                        }
                        if (ordersQueue.size() < 10)
                            sp.runOnUiThread(() -> sp.doClient.setText(sp.getString(R.string.inOrder) + ordersQueue.size()+sp.getString(R.string.orders)));
                        else
                            sp.runOnUiThread(() -> sp.doClient.setText(sp.getString(R.string.tooManyOrders) + ordersQueue.size() + sp.getString(R.string.tooManyOrders2)));
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
