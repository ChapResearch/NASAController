package com.lukekaufman48gmail.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class FMSInterface {

    private final static String TAG = "LUKER";
    private ArrayList<Competition> competitionList;
    private ArrayList<Competition> active;
    private ArrayList<Competition> upcoming;
    private ArrayList<Competition> finished;
    private Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

    public FMSInterface() {

    }

    public void readJSON(final Context applicationContext) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "https://frc-api.firstinspires.org/v2.0/" + localCalendar.get(Calendar.YEAR) + "/events";

        String jsonStr = sh.makeServiceCall(url);

        Log.v(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray events = jsonObj.getJSONArray("Events");
                competitionList = new ArrayList<>();
                active = new ArrayList<>();
                upcoming = new ArrayList<>();
                finished = new ArrayList<>();

                // looping through All events
                for (int i = 0; i < events.length(); i++) {
                    JSONObject event = events.getJSONObject(i);
                    String code = event.getString("code");
                    String city = event.getString("city");
                    String venue = event.getString("venue");
                    String timeStart = event.getString("dateStart").substring(5, 10);
                    String timeEnd = event.getString("dateEnd").substring(5, 10);
                    makeSortedCompetitionList(code,venue,city,timeStart,timeEnd);
                }
                Log.v(TAG, active.size() + " " + upcoming.size() + " " + finished.size());
                competitionList.addAll(active);
                competitionList.addAll(upcoming);
                competitionList.addAll(finished);

                Log.v(TAG, "number of competitions at readJSON: " + competitionList.size());

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                Toast.makeText(applicationContext,
                        "Json parsing error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
            Toast.makeText(applicationContext,
                    "Couldn't get json from server. Check LogCat for possible errors!",
                    Toast.LENGTH_LONG).show();

        }
    }

    //Sorts Competition list to put active competitions at top, then upcoming, then finished
    //JSON data date format YYYY-MM-DD (YYYY not used) (everything in terms of days)

    public void makeSortedCompetitionList(String code, String venue, String city, String timeStart, String timeEnd) {

        int currentDayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);

            int compMonthStart = Integer.parseInt(timeStart.substring(0, 2));
            int compMonthEnd = Integer.parseInt(timeEnd.substring(0, 2));

            int compDayStart = Integer.parseInt(timeStart.substring(3, 5));
            int compDayEnd = Integer.parseInt(timeEnd.substring(3, 5));

            int compDayOfYearStart = convertToDayOfYear(compMonthStart, compDayStart);
            int compDayOfYearEnd = convertToDayOfYear(compMonthEnd, compDayEnd);

            if ((currentDayOfYear - compDayOfYearStart) < 0) {
                upcoming.add(new Competition(code, venue, city, compDayOfYearStart, compDayOfYearEnd, 2));
            } else if ((currentDayOfYear - compDayOfYearEnd) > 0) {
                finished.add(new Competition(code, venue, city, compDayOfYearStart, compDayOfYearEnd,  0));
            } else {
                active.add(new Competition(code, venue, city, compDayOfYearStart, compDayOfYearEnd,  1));
            }

    }

    //return null;

    public int convertToDayOfYear(int month, int dayOfMonth) {
        int daysBeforeMonth = 0;
        int dayOfYear = dayOfMonth;
        switch (month) {
            case 1:
                break;
            case 2:
                daysBeforeMonth = 31;
                dayOfYear += daysBeforeMonth;
                break;
            case 3:
                daysBeforeMonth = 31 + 28;
                dayOfYear += daysBeforeMonth;
                break;
            case 4:
                daysBeforeMonth = 31 + 28 + 31;
                dayOfYear += daysBeforeMonth;
                break;
            case 5:
                daysBeforeMonth = 31 + 28 + 31 + 30;
                dayOfYear += daysBeforeMonth;
                break;
            case 6:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31;
                dayOfYear += daysBeforeMonth;
                break;
            case 7:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30;
                dayOfYear += daysBeforeMonth;
                break;
            case 8:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31;
                dayOfYear += daysBeforeMonth;
                break;
            case 9:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
                dayOfYear += daysBeforeMonth;
                break;
            case 10:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
                dayOfYear += daysBeforeMonth;
                break;
            case 11:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
                dayOfYear += daysBeforeMonth;
                break;
            case 12:
                daysBeforeMonth = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
                dayOfYear += daysBeforeMonth;
                break;

        }

        return dayOfYear;
    }

    public ArrayList<Competition> getCompetitionList() {
        return competitionList;
    }
}



