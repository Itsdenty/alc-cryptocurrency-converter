package com.example.dent.cryptocurrencyconverter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class CryptoMainActivity extends AppCompatActivity {
    private CryptoPriceClient client;
    private CryptoPrice crypto;
    private List<Currency> currenciesList = new ArrayList<>();
    private CurrencyAdapter ca;
    double amount;
    double convertedAmount;
    String firstCurrency;
    String secondCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_main);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ca = new com.example.dent.cryptocurrencyconverter.CurrencyAdapter(currenciesList);
        recList.setAdapter(ca);
//        ca.setOnCardClickListener((com.example.dent.cryptocurrencyconverter.CurrencyAdapter.OnCardClickListener) this);
//        fetchDevelopers();
    }
    public void check(List<Currency> aList){
        TextView mainText = (TextView) findViewById(R.id.main_txt);
        Button mainButton = (Button) findViewById(R.id.main_btn);
        if(aList.size() > 0){
            mainText.setVisibility(View.GONE);
            mainButton.setVisibility(View.GONE);
        }
        else{
            mainText.setVisibility(View.VISIBLE);
            mainButton.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int selectedMenuItem = item.getItemId();
        if(selectedMenuItem == R.id.action_search){
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Add Card");

            Button dialogCancel = (Button) dialog.findViewById(R.id.cancel_button);

            dialogCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Button dialogOk = (Button) dialog.findViewById(R.id.ok_button);
            final Spinner cryptoSpin = (Spinner) dialog.findViewById(R.id.cryto_currencies);
            final Spinner worldSpin = (Spinner) dialog.findViewById(R.id.world_currencies);

            dialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText cAmount = (EditText)dialog.findViewById(R.id.crypto_amount);
                    String cryptoText = cryptoSpin.getSelectedItem().toString();
                    String worldText = worldSpin.getSelectedItem().toString();
                    String nAmount = cAmount.getText().toString();
                    try {
                        amount = Float.parseFloat(nAmount);
                    } catch(NumberFormatException nfe) {
                        Log.d("error", "a  number was not entered");
                    }
                    fetchDevelopers(worldText, cryptoText);
                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void fetchDevelopers(final String worldText, final String cryptoText) {
//        progress.setVisibility(ProgressBar.VISIBLE);
        client = new CryptoPriceClient();
        client.getDevelopers(cryptoText, worldText, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    progress.setVisibility(ProgressBar.GONE);
//                    JSONArray devs = null;
                    if(response != null) {
//                        // Get the docs json array
//                        devs = response.getJSONArray(currency);
                        crypto = CryptoPrice.fromJson(response,worldText);
//                        crypto = new CryptoPrice(worldText);
                        String cAmount = crypto.getCrptoPrice();
                        try {
                            convertedAmount = Double.parseDouble(cAmount);
                        } catch(NumberFormatException nfe) {
                            Log.d("error", "a  number was not entered");
                        }
                        convertedAmount = convertedAmount *  amount;
                        String firstAmount = String.valueOf(amount);
                        String secondAmount = String.valueOf(convertedAmount);
                        BigDecimal a = new BigDecimal(firstAmount);
                        BigDecimal roundOff = a.setScale(5, BigDecimal.ROUND_HALF_EVEN);
                        firstAmount = "" + roundOff;
                        BigDecimal b = new BigDecimal(secondAmount);
                        BigDecimal roundOff2 = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        secondAmount = "" + roundOff2;
                        currenciesList.add(new Currency(cryptoText, worldText, firstAmount, secondAmount));
                        Log.i("response", crypto.getCrptoPrice());
                        Log.i("response", firstAmount);
                        Log.i("response", secondAmount + "");
                        ca.notifyItemInserted(currenciesList.size() - 1);
                        // Parse json array into array of model objects
//                        final ArrayList<Developer> developers = Developer.fromJson(devs);
//                        // Remove all developers from the adapter
//                        developerAdapter.clear();
                        // Load model objects into the adapter
//                        for (Developer developer : developers) {
//                            developerAdapter.add(developer); // add developer through the adapter
//                        }
//                        developerAdapter.notifyDataSetChanged();
                    }
//                } catch (JSONException e) {
//                    // Invalid JSON format, show appropriate error.
//                    e.printStackTrace();
//                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                progress.setVisibility(ProgressBar.GONE);
                Log.d("Failed: ", ""+statusCode);
                Log.d("Error : ", "" + throwable);
                log.d("failure:", "" + responseString);
            }
        });
    }
}
