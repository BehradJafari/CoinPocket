package ir.hamiss.coinpocket;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import module.Coin;

public class ListOfCoins extends AppCompatActivity {
    ArrayList<Coin> coins = new ArrayList<>();
    ArrayAdapter arrayAdapter ;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_coins);
        if (savedInstanceState==null){
                Bundle extras = getIntent().getExtras();
                if(extras == null) {
                } else {
                    coins=jsonToObj( extras.getString("jsonOfCoins"));

                }

        }

        listView = (ListView) findViewById(R.id.listCoin);

        arrayAdapter = new ListAdapter(coins);

        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Coin select = (Coin) arrayAdapter.getItem(position);


                Intent i = new Intent(getApplicationContext(),ShowCoin.class);
                i.putExtra("COIN",(select));
                startActivity(i);



            }
        });



    }
    private ArrayList<Coin> jsonToObj(String json){
        return new Gson().fromJson(json,new TypeToken<List<Coin>>(){}.getType());
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog =new  AlertDialog.Builder(this);
        alertDialog.setTitle("Exit");
        alertDialog.setMessage("are you sure?");
        alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);

            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(getApplicationContext(),SplashActivity.class));
        finish();
    }
}
