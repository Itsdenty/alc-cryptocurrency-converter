package com.example.dent.cryptocurrencyconverter;

/**
 * Created by ECJ4REAL on 10/28/2017.
 */

public class Currency {
    protected String firstCurrency;
    protected String secondCurrency;
    protected String firstAmount;
    protected String secondAmount;


    public Currency(String first, String second, String amount, String convertedAmount){
        firstCurrency = first;
        secondCurrency = second;
        firstAmount = amount;
        secondAmount = convertedAmount;
    }


}
