package com.lukekaufman48gmail.controller;

import android.app.Application;

public class Globals extends Application {
    public ConfigData configData;
    public MainFragmentData mainFragmentData;
    public Competition selectedCompetition;
    public boolean matchType=false;
    public boolean uploaded=false;
    public FMSInterface fms;
    public NASA_BLE_Interface bleCallbacks;

    public ConfigData getConfigData() {
        return configData;
    }

    public void setConfigData(ConfigData configData) {
        this.configData = configData;
    }

    public MainFragmentData getMainFragmentData() {
        return mainFragmentData;
    }

    public void setMainFragmentData(MainFragmentData mainFragmentData) {
        this.mainFragmentData = mainFragmentData;
    }

    public Competition getSelectedCompetition() {
        return selectedCompetition;
    }

    public void setSelectedCompetition(Competition selectedCompetition) {
        this.selectedCompetition = selectedCompetition;
    }

    public NASA_BLE_Interface getBleCallbacks() {
        return bleCallbacks;
    }

    public void setBleCallbacks(NASA_BLE_Interface bleCallbacks) {
        this.bleCallbacks = bleCallbacks;
    }

    public FMSInterface getFms() {
        return fms;
    }

    public void setFms(FMSInterface fms) {
        this.fms = fms;
    }

    public boolean isMatchType() {
        return matchType;
    }

    public void setMatchType(boolean matchType) {
        this.matchType = matchType;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
