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
    TextView MainText;
    TextView doCook;
    TextView doCookSize;
    TextView doClient;
    Button StartButton;
    Button CountButton;
    boolean alive = true;
    int i = 0;
    double money = 0;
    private Cook doCook1;
    private Cook doCook2;
    private Client doOrder1 = new Client(this);
    private Client doOrder2 = new Client(this);
    private Cashier doCash1 = new Cashier(this);
    static List<String> Cooking = synchronizedList(new ArrayList<>());
    static List<String> order = synchronizedList(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        MainText = (TextView)findViewById(R.id.MainText);
        doCook = (TextView)findViewById(R.id.CookingRest);
        doCookSize = (TextView)findViewById(R.id.CookingSize);
        doClient = (TextView)findViewById(R.id.ClientSize);
        doCook1 = new Cook(this, getString(R.string.Cook1),1);
        doCook2= new Cook(this, getString(R.string.Cook2),2);
        StartButton = (Button)findViewById(R.id.Start);
        CountButton = (Button)findViewById(R.id.Count);
    }


    public void SaveClick(View view) {
        try{
            doCook1.start();
            doCook2.start();
            doOrder1.start();
            doOrder2.start();
            doCash1.start();
            CountButton.setVisibility(View.VISIBLE);
            CountButton.setEnabled(true);
            StartButton.setVisibility(View.INVISIBLE);
        }
        catch (Exception ex)
        {
            System.out.print(ex.toString());
        }
    }

    public void CountClick(View view) {
        alive = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.Report)
                .setMessage(getString(R.string.ReportNumber) + i + getString(R.string.MoneyGet) + money + getString(R.string.MoneyGet2))
                .setCancelable(false)
                .setNegativeButton(R.string.Ok,
                        (dialog, id) -> {
                            alive = true;
                            dialog.cancel();
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
