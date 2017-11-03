package ng.com.blogspot.httpofficialceo.cryptocompare;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BTCConversion extends AppCompatActivity {

    private TextView tvTitle, tvSubtitle, tvOutputName, tvOutputRate;
    private EditText etInput;
    private String currencyName;
    private Double currencyRate;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btcconversion);

        tvTitle =  findViewById(R.id.tvTitle);
        tvSubtitle = findViewById(R.id.tv_SubTitle);
        tvOutputName =  findViewById(R.id.tvOutputName);
        tvOutputRate =  findViewById(R.id.tvOutputRate);
        etInput = findViewById(R.id.etInput);
        shareButton = findViewById(R.id.btc_share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSharedResult();
            }
        });
        Intent myIntent = getIntent();
        currencyName = myIntent.getStringExtra("currency_name");
        currencyRate = myIntent.getDoubleExtra("currency_rate", 0);

        tvSubtitle.setText("1 BTC = " + currencyRate +" "+ currencyName.toUpperCase());
        tvOutputName.setText(currencyName.toUpperCase() + " Rate: ");


        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(etInput.getText().toString().length() == 0 || etInput.getText().toString().isEmpty()){
                    tvOutputRate.setText("0");
                }else

                    calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    private  void calculate(){

        if(etInput.getText().toString().length() == 0){
            return;
        }

        double input;
        input = Double.parseDouble(etInput.getText().toString());

        double output;
        output = input * currencyRate;

        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        tvOutputRate.setText(decimalFormat.format(output));
    }

    public void setSharedResult(){

        if(etInput.getText().toString().isEmpty() || etInput.getText().length() == 0){

            Toast.makeText(this, "Nothing to share", Toast.LENGTH_SHORT).show();
        }else if (etInput.getText().toString().equals("0")){

            Toast.makeText(this, "Sorry, can't share zero result", Toast.LENGTH_SHORT).show();
        }

        else{
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, etInput.getText().toString()
                + " BTC" + " = " + tvOutputRate.getText().toString() + " " +currencyName);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));

    }
    }



}

