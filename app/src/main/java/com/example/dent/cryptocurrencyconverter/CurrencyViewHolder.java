package com.example.dent.cryptocurrencyconverter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ECJ4REAL on 10/28/2017.
 */

public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    protected TextView firstCurrency;
    protected TextView secondCurrency;
    protected TextView firstAmount;
    protected TextView secondAmount;

    public CurrencyViewHolder(View v){
        super(v);
        firstCurrency = (TextView) v.findViewById(R.id.first_currency_text);
        secondCurrency = (TextView) v.findViewById(R.id.second_currency_text);
        firstAmount = (TextView) v.findViewById(R.id.first_currency_amount);
        secondAmount = (TextView) v.findViewById(R.id.second_currency_amount);

    }
}
