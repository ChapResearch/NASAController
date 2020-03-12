package com.lukekaufman48gmail.controller;

import android.app.Activity;
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
    private ArrayList<MatchData> quals;
    private ArrayList<MatchData> playoffs;
    private Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    public Activity mainActivity;

    public FMSInterface(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void readCompetitionJSON(final Context applicationContext) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String eventsUrl = "https://frc-api.firstinspires.org/v2.0/" + localCalendar.get(Calendar.YEAR) + "/events";

        String jsonEventsStr = sh.makeServiceCall(eventsUrl);

        Log.v(TAG, "Response from url: " + jsonEventsStr);
        if (jsonEventsStr != null) {
            try {
                JSONObject jsonEventsObj = new JSONObject(jsonEventsStr);

                // Getting JSON Array node
                JSONArray events = jsonEventsObj.getJSONArray("Events");
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


    public ArrayList<MatchData> readMatchJSON(final Context applicationContext, String competitionCode, String matchType) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        ArrayList<MatchData> matchList= new ArrayList<>();
        // matchType==true - (playoff)
        // matchType==false - (qual)

        final Globals globals = (Globals) mainActivity.getApplicationContext();

        String matchUrl = "https://frc-api.firstinspires.org/v2.0/" + localCalendar.get(Calendar.YEAR) + "/schedule" + "/" + competitionCode + "/" + matchType;

        String jsonMatchStr = sh.makeServiceCall(matchUrl);

        Log.v(TAG, "Response from url: " + jsonMatchStr);
        if (jsonMatchStr != null) {
            try {
                JSONObject jsonMatchObj = new JSONObject(jsonMatchStr);
                // Getting JSON Array node and teams
                JSONArray matches = jsonMatchObj.getJSONArray("Schedule");

                // looping through All matches
                for (int i = 0; i < matches.length(); i++) {

                    JSONObject match = matches.getJSONObject(i);
                    String matchNumber = match.getString("matchNumber");
                    JSONArray teams = match.getJSONArray("teams");
                    ArrayList<TeamMatchData> teamsMatchData = new ArrayList<>();

                    // looping through all teams in match
                    for(int j=0; j<teams.length(); j++){

                        JSONObject team = teams.getJSONObject(j);
                        String teamNumber = team.getString("teamNumber");
                        String color = team.getString("station").substring(0,team.getString("station").length()-1);

                       TeamMatchData teamData = new TeamMatchData(teamNumber, color, "","","");
                       teamsMatchData.add(teamData);

                    }


                    ArrayList<TeamMatchData> redTeams = new ArrayList<>();
                    ArrayList<TeamMatchData> blueTeams = new ArrayList<>();

                    //separating teams into red and blue
                    for(int x=0; x<teamsMatchData.size(); x++){
                        if(teamsMatchData.get(x).getTeamColor().equals("Red")){
                            redTeams.add(teamsMatchData.get(x));
                        }
                        else if(teamsMatchData.get(x).getTeamColor().equals("Blue")){
                            blueTeams.add(teamsMatchData.get(x));
                        }

                    }

                    for(int j=0; j<3; j++){
                        redTeams.get(j).setDefTeam1(blueTeams.get(0).getTeamNumber());
                        redTeams.get(j).setDefTeam2(blueTeams.get(1).getTeamNumber());
                        redTeams.get(j).setDefTeam3(blueTeams.get(2).getTeamNumber());
                        blueTeams.get(j).setDefTeam1(redTeams.get(0).getTeamNumber());
                        blueTeams.get(j).setDefTeam2(redTeams.get(1).getTeamNumber());
                        blueTeams.get(j).setDefTeam3(redTeams.get(2).getTeamNumber());

                    }


                    MatchData matchData = new MatchData(globals.isMatchType(), matchNumber, blueTeams, redTeams);
                    matchList.add(matchData);

                }

            } catch(final JSONException e) {
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

        Log.v("LUKER", "matchList.size() at readMatchJSON " + matchList.size());
        return matchList;


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
                upcoming.add(new Competition(code, venue, city, compDayOfYearStart, compDayOfYearEnd, 2,null, null));
            } else if ((currentDayOfYear - (compDayOfYearEnd)) > 0) {
                finished.add(new Competition(code, venue, city, compDayOfYearStart, compDayOfYearEnd,  0,null, null));
            } else {
                active.add(new Competition(code, venue, city, compDayOfYearStart, compDayOfYearEnd,  1,null, null));
            }

    }

    //return null;

    public int convertToDayOfYear(int month, int dayOfMonth) {
        int daysBeforeMonth;
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



