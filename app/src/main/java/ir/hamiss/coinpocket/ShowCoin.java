package ir.hamiss.coinpocket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import module.Coin;

public class ShowCoin extends AppCompatActivity {
private ImageView image;
    private TextView textView;
    Coin coin = new Coin();
    TextView price;
    TextView change24;
    TextView change7;
    TextView ava;
    TextView name;
    ImageButton share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coin);

        price = (TextView) findViewById(R.id.price);
        change7 = (TextView) findViewById(R.id.change7);
        change24 = (TextView) findViewById(R.id.change24);
        ava = (TextView) findViewById(R.id.ava);
        name = (TextView) findViewById(R.id.name_txt);
        share_btn = (ImageButton) findViewById(R.id.share_btn);

        if (savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {
                coin=(Coin)(extras.getSerializable("COIN"));
                intialialize();

            }

        }




        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    String shareBody="";
                    sharingIntent.setType("text/plain");
                shareBody+="Coin :\t"+name.getText().toString()+"\n";

                shareBody+="price:\t"+price.getText().toString()+"\n";
                shareBody+="change(24H):\t"+change24.getText().toString()+"\n";

                shareBody+="Change(7d)\t"+change7.getText().toString()+"\n";

                shareBody+="Available supply:\t"+ava.getText().toString()+"\n";


                shareBody +="\n\n\n @hamiss_team";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "share Coin");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });









//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        int height = size.y;
//
////        textView.set
//
////        Button button = (Button) findViewById(R.id.price_btn) ;
////        setMargins(button,0,50,0,0);








    }

    private void intialialize() {
        name.setText(coin.getSymbol());
        price.setText(coin.getPrice_usd()+" $");
        ava.setText(coin.getAvailable_supply()+" $");
        change24.setText(coin.getPercent_change_24h()+" %");
        if (coin.getPercent_change_24h()>0){
            change24.setTextColor(getResources().getColor(R.color.increase));
        }
        else {
            change24.setTextColor(getResources().getColor(R.color.decrease));

        }
        change7.setText(coin.getPercent_change_7d()+" %");
        if (coin.getPercent_change_7d()>0){
            change7.setTextColor(getResources().getColor(R.color.increase));
        }
        else {
            change7.setTextColor(getResources().getColor(R.color.decrease));

        }

    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(getApplicationContext(),SplashActivity.class));
        finish();
    }

}
