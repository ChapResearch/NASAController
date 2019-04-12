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
        try {
            Log.v("LUKER", "Before auth");
            Authenticator.setDefault (new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication (APIusername, APIkey.toCharArray());
                }
            });
            Log.v("LUKER", "After auth");

            URL url = new URL(reqUrl);
            Log.v("LUKER", "Before url open");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            Log.v("LUKER", "After url open");

            // read the response
            Log.v("LUKER", "Before IS creation");
            if(conn != null) {
                Log.v("LUKER", "CONNECTION IS NOT NULL");
                InputStream in = conn.getInputStream();
                Log.v("LUKER", "CONNECTION IS NOT NULL AFTER IS CREATION");
                if (in != null) {
                    Log.v("LUKER", "After IS creation: " + convertStreamToString(in));
                    Log.v("LUKER", "INPUT STREAM IS NOT NULL");
                    response = convertStreamToString(in);
                }
            }



        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        }
        catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        Log.v("LUKER", "Before return response");
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}