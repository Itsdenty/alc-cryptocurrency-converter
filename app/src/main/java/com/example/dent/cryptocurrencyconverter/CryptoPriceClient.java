package com.example.dent.cryptocurrencyconverter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by dent4 on 10/20/2017.
 */

public class CryptoPriceClient {
    private static final String API_BASE_URL = "https://min-api.cryptocompare.com/data/price?";
    private AsyncHttpClient client;
//    public static String currency;

    public CryptoPriceClient() {
        this.client = new AsyncHttpClient();
//        this.currency = currency;
    }
    public String getUrl(String crypto, String currency, String url){
        return  url + "fsym=" + crypto + "&tsyms=" +  currency;
    }

    // Method for accessing the github API
    public void getDevelopers(final String bitCurrency, final String regCurrency, JsonHttpResponseHandler handler) {
        try {
            String url = API_BASE_URL;
            url = getUrl(bitCurrency, regCurrency, url);
            client.setUserAgent("UBrowser/7.0.6.1042");
            client.get(url, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
