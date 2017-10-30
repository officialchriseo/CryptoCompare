package ng.com.blogspot.httpofficialceo.cryptocompare.models;

/**
 * Created by official on 10/23/17.
 */

public class CurrencyBTC {
    private String name;
    private double rate;

    public CurrencyBTC(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

}
