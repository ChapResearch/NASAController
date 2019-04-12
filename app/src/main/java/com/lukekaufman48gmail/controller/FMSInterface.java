package com.lukekaufman48gmail.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FMSInterface {

    private String TAG = "LUKER";

    public FMSInterface(){

    }

    public void readJSON(final Context applicationContext) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "https://frc-api.firstinspires.org/v2.0/2019/events";

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray events = jsonObj.getJSONArray("Events");

                // looping through All events
                for (int i = 0; i < events.length(); i++) {
                    JSONObject event = events.getJSONObject(i);
                    String code = event.getString("code");
                    Log.v(TAG, "> codes: "+ code);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                        Toast.makeText(applicationContext,
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
            }

        }
        else {
            Log.e(TAG, "Couldn't get json from server.");
                    Toast.makeText(applicationContext,
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();

            }
        }

        //return null;
}


