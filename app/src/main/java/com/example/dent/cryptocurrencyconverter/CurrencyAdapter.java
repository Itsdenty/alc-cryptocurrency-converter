package com.example.dent.cryptocurrencyconverter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ECJ4REAL on 10/28/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<com.example.dent.cryptocurrencyconverter.CurrencyViewHolder> {
    private List<Currency> currencyList;

    public CurrencyAdapter(List<Currency> currencyList){
        this.currencyList = currencyList;
    }

    @Override
    public int getItemCount(){
        return currencyList.size();
    }

    @Override
    public void onBindViewHolder(com.example.dent.cryptocurrencyconverter.CurrencyViewHolder currencyViewHolder, int i){
        Currency ci = currencyList.get(i);
        currencyViewHolder.firstCurrency.setText(ci.firstCurrency);
        currencyViewHolder.firstAmount.setText(ci.firstAmount);
        currencyViewHolder.secondCurrency.setText(ci.secondCurrency);
        currencyViewHolder.secondAmount.setText(ci.secondAmount);

    }

    @Override
    public com.example.dent.cryptocurrencyconverter.CurrencyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_list_view, viewGroup, false);

        return new com.example.dent.cryptocurrencyconverter.CurrencyViewHolder(itemView);
    }
}
