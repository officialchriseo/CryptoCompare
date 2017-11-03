package ng.com.blogspot.httpofficialceo.cryptocompare;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ng.com.blogspot.httpofficialceo.cryptocompare.adapters.CustomCoinAdapter;
import ng.com.blogspot.httpofficialceo.cryptocompare.models.CoinListModel;

public class MainActivity extends AppCompatActivity {

    CustomCoinAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        lv = (ListView) findViewById(R.id.coins_lv);
        adapter = new CustomCoinAdapter(this, getData());
        lv.setAdapter(adapter);


    }

    private ArrayList getData(){
        ArrayList<CoinListModel> coinListModels=new ArrayList<>();
        CoinListModel coinListModel = new CoinListModel();

        coinListModel.setCoinImage(R.drawable.bitcoin);
        coinListModel.setCoinName("Bitcoin");
        coinListModel.setCoinCode("BTC");
        coinListModels.add(coinListModel);

        coinListModel = new CoinListModel();
        coinListModel.setCoinImage(R.drawable.ethereum);
        coinListModel.setCoinName("Ethereum");
        coinListModel.setCoinCode("ETH");
        coinListModels.add(coinListModel);

        return coinListModels;
    }



}
