package ir.hamiss.coinpocket;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import module.Coin;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//set
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);



        Api api = new Api();
        api.execute();




    }




    private class Api extends AsyncTask<URI,Integer,String> {

        @Override
        protected String doInBackground(URI... params) {

            URL url = null;
            try {
                url = new URL(getString(R.string.api_url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String result = null;
            try {
                result = getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }



        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s==null || s.length()==0){
                noConnection();
                return;
            }
            isNetwork(s);





        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onCancelled(String s) {

        }
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {

            InputStream in = urlConnection.getInputStream();



            Scanner scanner = new Scanner(in);

            scanner.useDelimiter("\\A");



            boolean hasInput = scanner.hasNext();

            if (hasInput) {

                return scanner.next();

            } else {

                return null;

            }

        } finally {

            urlConnection.disconnect();

        }

    }












    private void isNetwork(String json){
        Intent i = new Intent(getApplicationContext(),ListOfCoins.class);
        i.putExtra("jsonOfCoins",(json));
        startActivity(i);
        finish();

    }



    private void noConnection(){
        Toast.makeText(getApplicationContext(),"you dont have connection!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,NoConnection.class));
        finish();

    }


    private boolean mobileData(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isAvailable() && activeNetwork.isConnected();

        if (!isConnected)
        {
            return false;
        }
        return true;

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

