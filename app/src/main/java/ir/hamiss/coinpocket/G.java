package ir.hamiss.coinpocket;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

/**
 * Created by behrad on 9/6/2017.
 */
public class G extends Application {
    public static Context context;
    public static LayoutInflater inflater;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

}
