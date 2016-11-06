package virtusx.androidstudyapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;
import static virtusx.androidstudyapplication.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    TextView mainText;
    TextView doCook;
    TextView doCookSize;
    TextView doClient;
    Button startButton;
    Button countButton;
    boolean alive = true;
    int i = 0;
    double money = 0;
    private Cook doCook1;
    private Cook doCook2;
    private Client doOrder1 = new Client(this);
    private Client doOrder2 = new Client(this);
    private Cashier doCash1 = new Cashier(this);
    static List<String> foodQueue = synchronizedList(new ArrayList<>());
    static List<String> ordersQueue = synchronizedList(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        mainText = (TextView)findViewById(R.id.MainText);
        doCook = (TextView)findViewById(R.id.CookingRest);
        doCookSize = (TextView)findViewById(R.id.CookingSize);
        doClient = (TextView)findViewById(R.id.ClientSize);
        doCook1 = new Cook(this, getString(R.string.cook1),1);
        doCook2= new Cook(this, getString(R.string.cook2),2);
        startButton = (Button)findViewById(R.id.Start);
        countButton = (Button)findViewById(R.id.Count);
    }


    public void SaveClick(View view) {
        try{
            doCook1.start();
            doCook2.start();
            doOrder1.start();
            doOrder2.start();
            doCash1.start();
            countButton.setVisibility(View.VISIBLE);
            countButton.setEnabled(true);
            startButton.setVisibility(View.INVISIBLE);
        }
        catch (Exception ex)
        {
            System.out.print(ex.toString());
        }
    } 

    public void CountClick(View view) {
        alive = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.report)
                .setMessage(getString(R.string.reportNumber) + i + getString(R.string.moneyGet) + money + getString(R.string.moneyGet2))
                .setCancelable(false)
                .setNegativeButton(R.string.ok,
                        (dialog, id) -> {
                            alive = true;
                            dialog.cancel();
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
