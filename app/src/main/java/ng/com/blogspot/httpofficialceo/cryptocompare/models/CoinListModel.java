package ng.com.blogspot.httpofficialceo.cryptocompare.models;

/**
 * Created by official on 10/25/17.
 */

public class CoinListModel {

    String coinName;
    String coinCode;
    int coinImage;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public int getCoinImage() {
        return coinImage;
    }

    public void setCoinImage(int coinImage) {
        this.coinImage = coinImage;
    }
}
