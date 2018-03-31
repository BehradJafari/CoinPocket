package ir.hamiss.coinpocket;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

import module.Coin;

/**
 * Created by behrad on 9/6/2017.
 */
public class ListAdapter extends ArrayAdapter<Coin> {
    public ListAdapter(ArrayList<Coin> array){
        super(G.context,R.layout.listview_base,array);
    }


    public static class ViewHolder{
        public TextView coin_txt;
        public TextView price_txt;
        public ImageView coin_img;
        public ImageView inc_img;


        public ViewHolder(View view){
            coin_txt = (TextView) view.findViewById(R.id.coin_name);
            price_txt = (TextView) view.findViewById(R.id.price_txt);
            coin_img = (ImageView) view.findViewById(R.id.coin_image);
            inc_img = (ImageView) view.findViewById(R.id.inc_img);


        }


        public void fill(ArrayAdapter<Coin> adapter,Coin item,int position){
            coin_txt.setText(item.getName());
            price_txt.setText(Double.toString(item.getPrice_usd())+" $");
            setImage(coin_img,item,inc_img,item.getPercent_change_24h());
        }

        private void setImage(ImageView coin_img, Coin item,ImageView inc_img,double perc) {
        if (item.getPercent_change_24h()>0){
            inc_img.setImageResource(R.drawable.ic_action_up);

        }
        else {
            inc_img.setImageResource(R.drawable.ic_action_dawn);
        }

        if (item.getSymbol().equals("BTC")){
            coin_img.setImageResource(R.drawable.ic_btc);
        }
        else if(item.getSymbol().equals("ETH")){
            coin_img.setImageResource(R.drawable.ic_eths);
        }
        else if(item.getSymbol().equals("XRP")){
            coin_img.setImageResource(R.drawable.ic_ripple);
        }
        else if(item.getSymbol().equals("BCH")){
            coin_img.setImageResource(R.drawable.cash);
        }
        else if(item.getSymbol().equals("LTC")){
            coin_img.setImageResource(R.drawable.ic_ltc);
        }
        else if(item.getSymbol().equals("ADA")){
            coin_img.setImageResource(R.drawable.cardano);
        }
        else if(item.getSymbol().equals("XLM")){
            coin_img.setImageResource(R.drawable.stellar);
        }
        else if(item.getSymbol().equals("NEO")){
            coin_img.setImageResource(R.drawable.ic_neo);
        }
        else if(item.getSymbol().equals("EOS")){
            coin_img.setImageResource(R.drawable.eos);
        }
        else if(item.getSymbol().equals("MIOTA")){
            coin_img.setImageResource(R.drawable.ic_iota);
        }
        else if(item.getSymbol().equals("XMR")){
            coin_img.setImageResource(R.drawable.ic_monero);
        }
        else if(item.getSymbol().equals("DASH")){
            coin_img.setImageResource(R.drawable.ic_dash);
        }
        else if(item.getSymbol().equals("TRX")){
            coin_img.setImageResource(R.drawable.ic_tron);
        }
        else {
            coin_img.setImageResource(R.drawable.logo);
        }

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Coin item = getItem(position);

        if (convertView == null){
            convertView = G.inflater.inflate(R.layout.listview_base,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.fill(this,item,position);
        return convertView;

    }


}
