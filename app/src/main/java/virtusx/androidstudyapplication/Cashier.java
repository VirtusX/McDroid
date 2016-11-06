package virtusx.androidstudyapplication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;
import static virtusx.androidstudyapplication.MainActivity.foodQueue;
import static virtusx.androidstudyapplication.MainActivity.ordersQueue;

class Cashier extends Thread {


    private MainActivity sp;

    private final Lock lock = new ReentrantLock();

    Cashier(MainActivity _sp) {
        sp = _sp;
    }

    public void run() {
        while (true) {
            System.out.print("Cashier" + this.toString() + "alive is " + sp.alive + "\n");
            while (sp.alive) {
                while (ordersQueue.isEmpty() || foodQueue.isEmpty()) {
                }
                while (!foodQueue.contains(ordersQueue.get(0))) {
                }
                sp.i++;
                String ordered = ordersQueue.get(0);
                double price = 0;
                if(ordered.equals(sp.getString(R.string.hamburger)))
                    price = 10.00;
                else if (ordered.equals(sp.getString(R.string.cheeseburger)))
                    price = 12.00;
                else if(ordered.equals(sp.getString(R.string.fry)))
                    price = 9.00;
                else if (ordered.equals(sp.getString(R.string.mcNuggets)))
                    price = 11.00;
                else if(ordered.equals(sp.getString(R.string.muffin)))
                    price = 20.00;
                final double finalPrice = price;
                sp.runOnUiThread(() -> sp.mainText.setText(sp.getString(R.string.orderN) + sp.i + ", " + ordered + sp.getString(R.string.ordersDone) + finalPrice + sp.getString(R.string.bye)));
                if (lock.tryLock()) {
                    foodQueue.remove(ordersQueue.get(0));
                    ordersQueue.remove(0);
                }
                sp.money += price;
                try {
                    sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


