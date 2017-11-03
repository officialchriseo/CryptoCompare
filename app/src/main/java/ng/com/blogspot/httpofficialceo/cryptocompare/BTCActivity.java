package ng.com.blogspot.httpofficialceo.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import ng.com.blogspot.httpofficialceo.cryptocompare.POJO.BTC_POJO;
import ng.com.blogspot.httpofficialceo.cryptocompare.adapters.BTCCurrencyAdapter;
import ng.com.blogspot.httpofficialceo.cryptocompare.interfaces.BTCCurrencyItemClickListener;
import ng.com.blogspot.httpofficialceo.cryptocompare.interfaces.CoinExchangeService;
import ng.com.blogspot.httpofficialceo.cryptocompare.models.CurrencyBTC;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BTCActivity extends AppCompatActivity implements Callback<BTC_POJO>, BTCCurrencyItemClickListener {

    private ListView currencyList;
    private ImageView flagImage, network;
    private ProgressBar progressBar;
    private Button refreshButton;
    String fsym = "BTC";
    String tsyms = "NGN" + "," + "USD" +
            "," + "EUR" + "," + "JPY" + "," +
            "GBP" + "," + "AUD" + "," + "CAD" + "," +
            "CHF" + "," + "SEK" + "," + "NZD" + "," +
            "MXN" + "," + "SGD" + "," + "HKD" + "," +
            "NOK" + "," + "KRW" + "," + "TRY" + "," +
            "RUB" + "," + "INR" + "," + "BRL" + "," + "ZAR";

    int[] flagImages = new int[]{R.drawable.nigeria, R.drawable.usflag,
                        R.drawable.europeflag, R.drawable.japan,
                        R.drawable.britain, R.drawable.australia,
                        R.drawable.canada, R.drawable.switzerland,
                        R.drawable.sweden, R.drawable.newzealand,
                        R.drawable.mexico, R.drawable.singapore,
                        R.drawable.hongkong, R.drawable.norway,
                        R.drawable.korea, R.drawable.turkey,
                        R.drawable.russia, R.drawable.india,
                        R.drawable.brazil, R.drawable.southafrica};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btc);


        currencyList =  findViewById(R.id.btc_currency_list);
        progressBar =  findViewById(R.id.progress_bar);
        flagImage =  findViewById(R.id.flag_image_view_btc);
        refreshButton = findViewById(R.id.btc_refresh_button);
        network = findViewById(R.id.btc_wifi);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                network.setVisibility(View.GONE);
                refreshButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadBTCExchangeData();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        loadBTCExchangeData();
    }

    private void loadBTCExchangeData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://min-api.cryptocompare.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoinExchangeService service = retrofit.create(CoinExchangeService.class);
        Call<BTC_POJO> call = service.loadBTCExchange(fsym,tsyms);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<BTC_POJO> call, Response<BTC_POJO> response) {
        progressBar.setVisibility(View.GONE);
        refreshButton.setVisibility(View.GONE);
        network.setVisibility(View.GONE);
        BTC_POJO BTC_POJO = response.body();
        currencyList.setAdapter(new BTCCurrencyAdapter(this, BTC_POJO.getCurrencyBTCList(), this, flagImages));
    }

    @Override
    public void onFailure(Call<BTC_POJO> call, Throwable t) {
        progressBar.setVisibility(View.INVISIBLE);
        refreshButton.setVisibility(View.VISIBLE);
        network.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Unable to fetch data. Please try again later.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBTCCurrencyItemClick(CurrencyBTC currencyBTC) {

        Intent myIntent = new Intent(this, BTCConversion.class);
        myIntent.putExtra("currency_name", currencyBTC.getName());
        myIntent.putExtra("currency_rate", currencyBTC.getRate());
        startActivity(myIntent);

    }

}

