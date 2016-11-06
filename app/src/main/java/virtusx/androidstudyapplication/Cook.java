package virtusx.androidstudyapplication;


import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static virtusx.androidstudyapplication.MainActivity.foodQueue;

class Cook extends Thread {
    private final Lock lock = new ReentrantLock();
    private MainActivity sp;
    private String name;
    private int number = 0;

    Cook(MainActivity _sp, String i, int n) {
        sp = _sp;
        name = i;
        number = n;
    }

    private void doHamburger(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.hamburger));
        }
    }

    private void doSandwich(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.cheeseburger));
    }

    private void doFry(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.fry));
    }

    private void doNuggets(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.mcNuggets));
    }

    private void doMuffin(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.muffin));
    }

    @Override
    public void run() {
        int n = 0;
        while (!currentThread().isInterrupted()) {
            while (sp.alive) {
                try {
                    Thread.sleep(number * 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (sp.alive) {
                    if (number == 1) {
                        for (int i = 0; i < 2; i++) {
                            while (!sp.alive) {
                            }
                            switch (i) {
                                case 0:
                                    this.doHamburger(foodQueue);
                                    n++;
                                    cooking(n);
                                    n = n > 7 ? 0 : n;
                                    break;
                                case 1:
                                    this.doFry(foodQueue);
                                    n++;
                                    cooking(n);
                                    n = n > 7 ? 0 : n;
                                    break;
                            }
                        }
                    } else if (number == 2) {
                        for (int i = 0; i < 3; i++) {
                            while (!sp.alive) {
                            }
                            switch (i) {
                                case 0:
                                    this.doSandwich(foodQueue);
                                    n++;
                                    cooking(n);
                                    n = n > 7 ? 0 : n;
                                    break;
                                case 1:
                                    this.doNuggets(foodQueue);
                                    n++;
                                    cooking(n);
                                    n = n > 7 ? 0 : n;
                                    break;
                                case 2:
                                    this.doMuffin(foodQueue);
                                    n++;
                                    cooking(n);
                                    n = n > 7 ? 0 : n;
                                    break;
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void cooking(int n) {
        System.out.print(name + " " + n + "\n");
        sp.runOnUiThread(() -> sp.doCookSize.setText(sp.getString(R.string.cookingSize) + foodQueue.size()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (n > 7) {
            try {
                sp.runOnUiThread(() -> sp.doCook.setText(name + sp.getString(R.string.cooksRest)));
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sp.runOnUiThread(() -> sp.doCook.setText( name + sp.getString(R.string.returns)));
        }
    }
}
