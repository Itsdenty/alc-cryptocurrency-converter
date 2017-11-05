package com.example.dent.cryptocurrencyconverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by dent4 on 10/20/2017.
 */

public class CryptoPrice implements Serializable {
    private String price;
    public static String currency;

    public String getCrptoPrice() {
        return price;
    }
    public CryptoPrice(String currency){
        this.currency = currency;
    }

    // Returns a Developer given the expected JSON
    public static CryptoPrice fromJson(JSONObject jsonObject, String curr) {
        CryptoPrice cryptoPrice = new CryptoPrice(curr);
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            cryptoPrice.price = jsonObject.getString(currency);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return cryptoPrice;
    }
}
