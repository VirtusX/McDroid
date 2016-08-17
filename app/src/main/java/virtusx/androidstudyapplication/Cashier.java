package virtusx.androidstudyapplication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;
import static virtusx.androidstudyapplication.MainActivity.Cooking;
import static virtusx.androidstudyapplication.MainActivity.order;

class Cashier extends Thread {


    private MainActivity sp;

    private final Lock lock = new ReentrantLock();

    Cashier(MainActivity _sp) {
        sp = _sp;
    }


    /*synchronized String Manual() throws InterruptedException {
        if (lock.tryLock()) {
            while (order.isEmpty() || Cooking.isEmpty()) {
                sp.MainText.setText("Чи ти кудись спішиш?");
            }
            while (!Cooking.contains(order.get(0))) {
                sp.MainText.setText("Заказ ще не зготували, не зли мене!");
            }
        }
        sp.i++;
        String ordered = order.get(0);
        double price = 0;
        switch (ordered) {
            case "гамбургер":
                price = 10.00;
                break;
            case "чізбургер":
                price = 12.00;
                break;
            case "картопля фрі":
                price = 9.00;
                break;
            case "макНагетси":
                price = 11.00;
                break;
            case "мафін":
                price = 20.00;
                break;
        }
        sp.MainText.setText("Замовлення №" + sp.i + ", " + ordered + ", виконано.\n З вас " + price + " гривень. Ідіть нахуй");
        Cooking.remove(order.get(0));
        order.remove(0);
        sp.money += price;
        return "Замовлення №" + sp.i + ", " + ordered + ", виконано. З вас " + price + " гривень. Ідіть нахуй";
    }
    */

    public void run() {
        while (true) {
            System.out.print("Cashier" + this.toString() + "alive is " + sp.alive + "\n");
            while (sp.alive) {
                while (order.isEmpty() || Cooking.isEmpty()) {
                }
                while (!Cooking.contains(order.get(0))) {
                }
                sp.i++;
                String ordered = order.get(0);
                double price = 0;
                if(ordered.equals(sp.getString(R.string.Hamburger)))
                    price = 10.00;
                else if (ordered.equals(sp.getString(R.string.Cheeseburger)))
                    price = 12.00;
                else if(ordered.equals(sp.getString(R.string.Fry)))
                    price = 9.00;
                else if (ordered.equals(sp.getString(R.string.McNuggets)))
                    price = 11.00;
                else if(ordered.equals(sp.getString(R.string.Muffin)))
                    price = 20.00;
                final double finalPrice = price;
                sp.runOnUiThread(() -> sp.MainText.setText(sp.getString(R.string.OrderN) + sp.i + ", " + ordered + sp.getString(R.string.OrdersDone) + finalPrice + sp.getString(R.string.Gtfo)));
                if (lock.tryLock()) {
                    Cooking.remove(order.get(0));
                    order.remove(0);
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


