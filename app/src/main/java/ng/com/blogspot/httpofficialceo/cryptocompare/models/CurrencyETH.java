package ng.com.blogspot.httpofficialceo.cryptocompare.models;

/**
 * Created by official on 10/22/17.
 */

public class CurrencyETH {
    private String name;
    private double rate;

    public CurrencyETH(String name, double rate) {
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
