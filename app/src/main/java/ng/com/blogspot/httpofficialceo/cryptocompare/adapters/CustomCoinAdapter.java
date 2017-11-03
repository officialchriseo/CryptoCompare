package ng.com.blogspot.httpofficialceo.cryptocompare.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ng.com.blogspot.httpofficialceo.cryptocompare.BTCActivity;
import ng.com.blogspot.httpofficialceo.cryptocompare.ETHActivity;
import ng.com.blogspot.httpofficialceo.cryptocompare.R;
import ng.com.blogspot.httpofficialceo.cryptocompare.models.CoinListModel;

/**
 * Created by official on 10/25/17.
 */

public class CustomCoinAdapter extends BaseAdapter {

    Context mContext;
    Intent myIntent;

    ArrayList<CoinListModel> coinListModels;

    public CustomCoinAdapter(Context context, ArrayList<CoinListModel> coinListModels){
        this.mContext = context;
        this.coinListModels = coinListModels;
    }

    @Override
    public int getCount() {
        return coinListModels.size();
    }

    @Override
    public Object getItem(int position) {
        return coinListModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.activity_main,parent,false);
        }
        final CoinListModel coinListModel= (CoinListModel) this.getItem(position);
        ImageView coinImage =  convertView.findViewById(R.id.coinImage);
        TextView coinName=  convertView.findViewById(R.id.coin_name);
        TextView coinCode=  convertView.findViewById(R.id.coin_code);
        coinName.setText(coinListModel.getCoinName());
        coinCode.setText(coinListModel.getCoinCode());
        coinImage.setImageResource(coinListModel.getCoinImage());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
               switch (position){
                   case 0:
                       myIntent = new Intent(mContext, BTCActivity.class);
                       context.startActivity(myIntent);
                       break;

                   case 1:
                       myIntent = new Intent(mContext, ETHActivity.class);
                       context.startActivity(myIntent);
                       break;

               }
            }
        });
        return convertView;
    }
}
