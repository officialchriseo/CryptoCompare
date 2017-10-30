package ng.com.blogspot.httpofficialceo.cryptocompare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ng.com.blogspot.httpofficialceo.cryptocompare.R;
import ng.com.blogspot.httpofficialceo.cryptocompare.interfaces.BTCCurrencyItemClickListener;
import ng.com.blogspot.httpofficialceo.cryptocompare.models.CurrencyBTC;


/**
 * Created by official on 10/23/17.
 */

public class  BTCCurrencyAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater layoutInflater;
    private List<CurrencyBTC> currencyBTCs;
    private BTCCurrencyItemClickListener btcCurrencyItemClickListener;
    private int flagImages[];

    public BTCCurrencyAdapter(Context context, List<CurrencyBTC> currencyBTCs, BTCCurrencyItemClickListener btcCurrencyItemClickListener, int[] flagImages) {
        this.context = context;
        this.currencyBTCs = currencyBTCs;
        this.btcCurrencyItemClickListener = btcCurrencyItemClickListener;
        this.flagImages = flagImages;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return currencyBTCs.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyBTCs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View btcCurrencyItemView = layoutInflater.inflate(R.layout.btc_currency_item, null);
        TextView currencyName = btcCurrencyItemView.findViewById(R.id.currency_name);
        TextView currencyAmount =  btcCurrencyItemView.findViewById(R.id.currency_amount);
        ImageView flagImage = btcCurrencyItemView.findViewById(R.id.flag_image_view_btc);

        Glide.with(context).load(flagImages[position]).into(flagImage);

        final CurrencyBTC currencyBTC = currencyBTCs.get(position);
        currencyName.setText(currencyBTC.getName());
        currencyAmount.setText(Double.toString(currencyBTC.getRate()));

        btcCurrencyItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btcCurrencyItemClickListener.onBTCCurrencyItemClick(currencyBTC);
            }
        });
        return btcCurrencyItemView;
    }
}

