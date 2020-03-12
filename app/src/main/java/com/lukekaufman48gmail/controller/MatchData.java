package com.lukekaufman48gmail.controller;

import android.app.Activity;

import java.util.ArrayList;

public class MatchData extends Activity {

    String competition;
    boolean matchType = false; //false-qual | true-playoff
    String matchNumber;
    ArrayList<TeamMatchData> blueTeams;
    ArrayList<TeamMatchData> redTeams;


    public MatchData(boolean matchType, String matchNumber, ArrayList<TeamMatchData> blueTeams, ArrayList<TeamMatchData> redTeams){
        this.matchType = matchType;
        this.matchNumber = matchNumber;
        this.blueTeams = blueTeams;
        this.redTeams = redTeams;

    }

    public String getMatchNumber() { return this.matchNumber;}

    public boolean isMatchType() {
        return this.matchType;
    }

    public ArrayList<TeamMatchData> getBlueTeams() {
        return this.blueTeams;
    }

    public ArrayList<TeamMatchData> getRedTeams() {
        return this.redTeams;
    }
}
