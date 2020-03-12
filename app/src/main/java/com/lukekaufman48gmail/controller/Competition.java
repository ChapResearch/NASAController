package com.lukekaufman48gmail.controller;

import android.util.Log;

import java.util.ArrayList;

public class Competition {

    private String code;
    private String venue;
    private String city;
    private int dayOfYearStart;
    private int dayOfYearEnd;
    //0=finished / 1-ongoing / 2-upcoming / 3-unknown
    private int state;
    private ArrayList<MatchData> qualMatches;
    private ArrayList<MatchData> playoffMatches;

    public Competition(String code, String venue, String city, int dayOfYearStart, int dayOfYearEnd, int state, ArrayList<MatchData> qualMatches, ArrayList<MatchData> playoffMatches ){
        this.code = code;
        this.venue = venue;
        this.city = city;
        this.dayOfYearStart = dayOfYearStart;
        this.dayOfYearEnd = dayOfYearEnd;
        this.state = state;
        this.qualMatches = qualMatches;
        this.playoffMatches = playoffMatches;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getDayOfYearStartStart(){
        return dayOfYearStart;
    }

    public void setDayOfYearStartStart(int time){
        this.dayOfYearStart = time;
    }

    public int getDayOfYearEndEnd(){
        return dayOfYearEnd;
    }

    public void setDayOfYearEndEnd(int time){
        this.dayOfYearEnd = time;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int s){
        this.state = s;
    }

    public ArrayList<MatchData> getQualMatches() {
        return qualMatches;
    }

    public void setQualMatches(ArrayList<MatchData> qualMatches) {
        this.qualMatches = qualMatches;
    }

    public ArrayList<MatchData> getPlayoffMatches() {
        return playoffMatches;
    }

    public void setPlayoffMatches(ArrayList<MatchData> playoffMatches) {
        this.playoffMatches = playoffMatches;
    }
}
