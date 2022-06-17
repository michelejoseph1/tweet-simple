package com.example.cocktailme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.cocktailme.models.Ingredients;
import com.parse.ParseObject;

import org.json.JSONObject;

import okhttp3.Headers;
import okhttp3.internal.http2.Header;

public class MainActivity extends AppCompatActivity {

    MultiAutoCompleteTextView multiAutoCompleteTextViewDefault;

    // This is the second one and required for custom features

    // As a sample, few text are given below which can be populated in dropdown, when user starts typing
    // For example, when user types "a", text whichever starting with "a" will be displayed in dropdown
    // As we are using two MultiAutoCompleteTextView components, using two string array separately
    String[] fewRandomSuggestedText = {"ingredient1", "ingredient2", "ingredient3"};


    public static final String INGREDIENT_LIST_URL ="https://the-cocktail-db.p.rapidapi.com/filter.php";
    public static final String TAG = "MainActivity";
    ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSettingsActivity();
            }
        });


        multiAutoCompleteTextViewDefault = findViewById(R.id.multiAutoCompleteTextViewDefault);

        // In order to show the substring options in a dropdown, we need ArrayAdapter
        // and here it is using simple_list_item_1
        ArrayAdapter<String> randomArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fewRandomSuggestedText);
        multiAutoCompleteTextViewDefault.setAdapter(randomArrayAdapter);

        // setThreshold() is used to specify the number of characters after which
        // the dropdown with the autocomplete suggestions list would be displayed.
        // For multiAutoCompleteTextViewDefault, after 1 character, the dropdown shows substring
        multiAutoCompleteTextViewDefault.setThreshold(1);

        // Default CommaTokenizer is used here
        multiAutoCompleteTextViewDefault.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        // As multiAutoCompleteTextViewCustom is customized , we are using SpaceTokenizer
        // which is written as a separate java class to handle space
        // SpaceTokenizer can be customized as per our needs, here for this example,
        // after user types 2 character
        // the substring of the text is shown in the dropdown and once selected,
        // a space is appended at the
        // end of the substring. So for customized MultiAutoCompleteTextView,
        // we need to write code like SpaceTokenizer
        // It has 3 methods namely findTokenStart,findTokenEnd and terminateToken




/////

        AsyncHttpClient client = new AsyncHttpClient();
        RequestHeaders params = new RequestHeaders();
        params.put("X-RapidAPI-Key", "c13dadbdfdmshb5f916990392087p1e49ccjsnae8ca04335f1");
        params.put("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");


        client.get(INGREDIENT_LIST_URL, params,new RequestParams(),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess. The response is " + json.toString());
                JSONObject jsonObject = json.jsonObject;
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure"+statusCode+response);
            }
        });



        ParseObject firstObject = new  ParseObject("FirstClass");
        firstObject.put("message","Hey ! First message from android. Parse is now connected");
        firstObject.saveInBackground(e -> {
            if (e != null){
                Log.e("MainActivity", e.getLocalizedMessage());
            }else{
                Log.d("MainActivity","Object saved.");
            }
        });
    }
    private void goSettingsActivity() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        finish();
    }
}