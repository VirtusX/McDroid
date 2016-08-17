package virtusx.androidstudyapplication;


import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static virtusx.androidstudyapplication.MainActivity.Cooking;

class Cook extends Thread {
    private final Lock lock = new ReentrantLock();
    private MainActivity sp;
    private String Name;
    private int number = 0;

    public Cook(MainActivity _sp, String i, int n) {
        sp = _sp;
        Name = i;
        number = n;
    }

    private void doHamburger(List<String> i) {
        if (lock.tryLock()) {
            i.add(sp.getString(R.string.Hamburger));
        }
    }

    private void doSandwich(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.Cheeseburger));
    }

    private void doFry(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.Fry));
    }

    private void doNuggets(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.McNuggets));
    }

    private void doMuffin(List<String> i) {
        if (lock.tryLock())
            i.add(sp.getString(R.string.Muffin));
    }

    @Override
    public void run() {
        int n = 0;
        while (true) {
            //System.out.print("Cook" + this.toString()+ "alive is " + sp.alive+"\n");
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
                                    this.doHamburger(Cooking);
                                    n++;
                                    System.out.print(Name+" "+n+"\n");
                                    sp.runOnUiThread(() -> sp.doCookSize.setText(sp.getString(R.string.CookingSize) + Cooking.size()));
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.CooksRest)));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.Return)));
                                        n = 0;
                                    }
                                    break;
                                case 1:
                                    this.doFry(Cooking);
                                    n++;
                                    System.out.print(Name+" "+n+"\n");
                                    sp.runOnUiThread(() -> sp.doCookSize.setText(sp.getString(R.string.CookingSize) + Cooking.size()));
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.CooksRest)));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.Return)));
                                        n = 0;
                                    }
                                    break;
                            }
                        }
                    } else if (number == 2) {
                        for (int i = 0; i < 3; i++) {
                            while (!sp.alive) {
                            }
                            switch (i) {
                                case 0:
                                    this.doSandwich(Cooking);
                                    n++;
                                    System.out.print(Name + " " + n + "\n");
                                    sp.runOnUiThread(() -> sp.doCookSize.setText(sp.getString(R.string.CookingSize) + Cooking.size()));
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.CooksRest)));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.Return)));
                                        n = 0;
                                    }
                                    break;
                                case 1:
                                    this.doNuggets(Cooking);
                                    n++;
                                    System.out.print(Name + " " + n + "\n");
                                    sp.runOnUiThread(() -> sp.doCookSize.setText(sp.getString(R.string.CookingSize) + Cooking.size()));
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.CooksRest)));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.Return)));
                                        n = 0;
                                    }
                                    break;
                                case 2:
                                    this.doMuffin(Cooking);
                                    n++;
                                    System.out.print(Name + " " + n + "\n");
                                    sp.runOnUiThread(() -> sp.doCookSize.setText(sp.getString(R.string.CookingSize) + Cooking.size()));
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.CooksRest)));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        sp.runOnUiThread(() -> sp.doCook.setText(sp.getString(R.string.CooksName) + Name + sp.getString(R.string.Return)));
                                        n = 0;
                                    }
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


}
