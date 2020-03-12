package com.lukekaufman48gmail.controller;

import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

public class HttpHandler {

    private static final String TAG = "LUKER";
    private String APIusername = "nasascouting";
    private String APIkey = "5CDF57A7-7890-4B29-96BC-DF60EB2AC2CD";

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;

        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);

        try {
            Log.v("LUKER", "Before auth");
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(APIusername, APIkey.toCharArray());
                }
            });
            Log.v("LUKER", "After auth");

            URL url = new URL(reqUrl);
            Log.v("LUKER", "Before url open");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(10 * 1000);
            //conn.connect();

            Log.v("LUKER", "So far so good");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            Log.v("LUKER", "after the getInputString()");

            StringBuilder stringBuilder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();
        } catch (Exception e) {
            Log.e("LUKER", "Exception: " + e.getMessage());
        }
        Log.v("LUKER", "Before return response");
        return response;
    }
}
