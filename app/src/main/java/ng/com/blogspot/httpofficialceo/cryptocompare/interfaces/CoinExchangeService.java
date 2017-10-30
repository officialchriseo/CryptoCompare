package ng.com.blogspot.httpofficialceo.cryptocompare.interfaces;


import ng.com.blogspot.httpofficialceo.cryptocompare.POJO.BTC_POJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by official on 10/23/17.
 */

public interface CoinExchangeService {
    @GET("data/price?")
    Call<BTC_POJO> loadBTCExchange(@Query("fsym") String coins, @Query("tsyms") String currency);
}
