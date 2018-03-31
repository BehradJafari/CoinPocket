package module;

import java.io.Serializable;

/**
 * Created by behrad on 3/11/2018.
 */

public class Coin implements Serializable{
    private String id;
    private String name;
    private String symbol;
    private String rank;
    private double price_usd;
    private double price_btc;

    private double h_volume_usd;

    private double available_supply;
    private double total_supply;
    private double max_supply;
    private double percent_change_1h;
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    private double market_cap_usd;
    @Override
    public String toString() {
        return "Coin{" +
                "" + id + '\'' +
                "" + name + '\'' +
                "'" + symbol + '\'' +
                "" + rank + '\'' +
                "" + price_usd +
                "" + price_btc +
                ", h_volume_usd=" + h_volume_usd +
                ", market_cap_usd=" + market_cap_usd +
                ", available_supply=" + available_supply +
                ", total_supply=" + total_supply +
                ", max_supply=" + max_supply +
                ", percent_change_1h=" + percent_change_1h +
                ", percent_change_24h=" + percent_change_24h +
                ", percent_change_7d=" + percent_change_7d +
                ", last_updated=" + last_updated +
                '}';
    }

    public String getRank() {
        return rank;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public double getPrice_btc() {
        return price_btc;
    }

    public double getH_volume_usd() {
        return h_volume_usd;
    }

    public double getMarket_cap_usd() {
        return market_cap_usd;
    }

    public double getAvailable_supply() {
        return available_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public double getMax_supply() {
        return max_supply;
    }

    public double getPercent_change_1h() {
        return percent_change_1h;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }

    public double getPercent_change_7d() {
        return percent_change_7d;
    }

    public long getLast_updated() {
        return last_updated;
    }

    private double percent_change_24h;
    private double percent_change_7d;
    private long last_updated;



}
