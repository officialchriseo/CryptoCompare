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


import ng.com.blogspot.httpofficialceo.cryptocompare.POJO.ETH_POJO;
import ng.com.blogspot.httpofficialceo.cryptocompare.adapters.ETHCurrencyAdapter;
import ng.com.blogspot.httpofficialceo.cryptocompare.interfaces.ETHCurrencyItemClickListener;
import ng.com.blogspot.httpofficialceo.cryptocompare.interfaces.ETHExchangeService;
import ng.com.blogspot.httpofficialceo.cryptocompare.models.CurrencyETH;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ETHActivity extends AppCompatActivity implements Callback<ETH_POJO>, ETHCurrencyItemClickListener {

    private ListView currencyList;
    private ProgressBar progressBar;
    private ImageView ethWifi;
    private Button ethRefresh;
    String fsym = "ETH";
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
        setContentView(R.layout.activity_eth);


        currencyList = (ListView) findViewById(R.id.eth_currency_list);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ethWifi = findViewById(R.id.eth_wifi);
        ethRefresh = findViewById(R.id.eth_refresh_button);

        ethRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ethRefresh.setVisibility(View.GONE);
                ethWifi.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadETHExchangeData();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        loadETHExchangeData();
    }

    private void loadETHExchangeData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://min-api.cryptocompare.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ETHExchangeService service = retrofit.create(ETHExchangeService.class);
        Call<ETH_POJO> call = service.loadETHExchange(fsym,tsyms);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ETH_POJO> call, Response<ETH_POJO> response) {
        progressBar.setVisibility(View.GONE);
        ethWifi.setVisibility(View.GONE);
        ethRefresh.setVisibility(View.GONE);
        ETH_POJO ethPOJO = response.body();
        currencyList.setAdapter(new ETHCurrencyAdapter(this, ethPOJO.getCurrencyETHList(), this, flagImages));
    }

    @Override
    public void onFailure(Call<ETH_POJO> call, Throwable t) {
        progressBar.setVisibility(View.GONE);
        ethWifi.setVisibility(View.VISIBLE);
        ethRefresh.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Unable to fetch data. Please try again later.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onETHCurrencyItemClick(CurrencyETH currencyETH) {

        Intent myIntent = new Intent(this, ETHConversion.class);
        myIntent.putExtra("currency_name", currencyETH.getName());
        myIntent.putExtra("currency_rate", currencyETH.getRate());
        startActivity(myIntent);

    }
}

