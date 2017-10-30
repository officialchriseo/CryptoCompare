package ng.com.blogspot.httpofficialceo.cryptocompare.interfaces;



import ng.com.blogspot.httpofficialceo.cryptocompare.POJO.ETH_POJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by official on 10/22/17.
 */

public interface ETHExchangeService {
    @GET("data/price?")
    Call<ETH_POJO> loadETHExchange(@Query("fsym") String coins, @Query("tsyms") String currency);
}


