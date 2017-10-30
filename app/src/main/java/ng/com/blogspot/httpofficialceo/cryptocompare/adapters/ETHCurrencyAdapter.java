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
import ng.com.blogspot.httpofficialceo.cryptocompare.interfaces.ETHCurrencyItemClickListener;
import ng.com.blogspot.httpofficialceo.cryptocompare.models.CurrencyETH;


/**
 * Created by official on 10/22/17.
 */

public class ETHCurrencyAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater layoutInflater;
    private List<CurrencyETH> currencyETHs;
    private ETHCurrencyItemClickListener ethCurrencyItemClickListener;
    private int flagImages[];

    public ETHCurrencyAdapter(Context context, List<CurrencyETH> currencyETHs, ETHCurrencyItemClickListener ethCurrencyItemClickListener, int[] flagImages) {
        this.context = context;
        this.currencyETHs = currencyETHs;
        this.ethCurrencyItemClickListener = ethCurrencyItemClickListener;
        this.flagImages = flagImages;
       layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return currencyETHs.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyETHs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View ethCurrencyItemView = layoutInflater.inflate(R.layout.eth_currency_item, null);
        TextView currencyName = ethCurrencyItemView.findViewById(R.id.currency_name);
        TextView currencyAmount =  ethCurrencyItemView.findViewById(R.id.currency_amount);
        ImageView flagImage = ethCurrencyItemView.findViewById(R.id.flag_image_view_eth);
        Glide.with(context).load(flagImages[position]).into(flagImage);

        final CurrencyETH currencyETH = currencyETHs.get(position);
        currencyName.setText(currencyETH.getName());
        currencyAmount.setText(Double.toString(currencyETH.getRate()));

        ethCurrencyItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ethCurrencyItemClickListener.onETHCurrencyItemClick(currencyETH);
            }
        });
        return ethCurrencyItemView;
    }
}
