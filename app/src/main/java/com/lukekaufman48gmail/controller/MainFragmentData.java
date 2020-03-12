package com.lukekaufman48gmail.controller;

import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;

public class MainFragmentData {

    private String contributorNameA; // contributor name
    private String teamNumeberA; // team number
    private int colorA; // color
    private boolean dataStatusA; // data status
    private boolean connectionStatusA; // the entire row

    private String contributorNameB;
    private String teamNumberB;
    private int colorB;
    private boolean dataStatusB;
    private boolean connectionStatusB;

    private String contributorNameC;
    private String teamNumberC;
    private int colorC;
    private boolean datStatussC;
    private boolean connectionStatusC;

    private String contributorNameD;
    private String teamNumberD;
    private int colorD;
    private boolean dataStatusD;
    private boolean connectionStatusD;

    private String contributorNameE;
    private String teamNumberE;
    private int colorE;
    private boolean dataStatusE;
    private boolean connectionStatusE;

    private String contributorNameF;
    private String teamNumberF;
    private int colorF;
    private boolean dataStatusF;
    private boolean connectionStatusF;



    public MainFragmentData(){}


    public boolean getConnectionStatus(int slot){

        switch(slot) {
            case 0:
                return connectionStatusA;
            case 1:
                return connectionStatusB;
            case 2:
                return connectionStatusC;
            case 3:
                return connectionStatusD;
            case 4:
                return connectionStatusE;
            case 5:
                return connectionStatusF;
        }

        return false;

    }

    public void setConnectionStatus(int slot, boolean connectionStatus){

        switch(slot) {
            case 0:
                this.connectionStatusA = connectionStatus;
                break;

            case 1:
                this.connectionStatusB = connectionStatus;
                break;

            case 2:
                this.connectionStatusC = connectionStatus;
                break;

            case 3:
                this.connectionStatusD = connectionStatus;
                break;
            case 4:
                this.connectionStatusE = connectionStatus;
                break;
            case 5:
                this.connectionStatusF = connectionStatus;
                break;
        }

    }

    public String getContributorNameA() {
        return contributorNameA;
    }

    public void setContributorNameA(String contributorNameA) {
        this.contributorNameA = contributorNameA;
    }

    public String getTeamNumeberA() {
        return teamNumeberA;
    }

    public void setTeamNumeberA(String teamNumeberA) {
        this.teamNumeberA = teamNumeberA;
    }

    public int getColorA() {
        return colorA;
    }

    public void setColorA(int colorA) {
        this.colorA = colorA;
    }

    public boolean isDataStatusA() {
        return dataStatusA;
    }

    public void setDataStatusA(boolean dataStatusA) {
        this.dataStatusA = dataStatusA;
    }

    // CONTRIBUTOR B


    public String getContributorNameB() {
        return contributorNameB;
    }

    public void setContributorNameB(String contributoNameB) {
        this.contributorNameB = contributoNameB;
    }

    public String getTeamNumberB() {
        return teamNumberB;
    }

    public void setTeamNumberB(String teamNumberB) {
        this.teamNumberB = teamNumberB;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public boolean isDataStatusB() {
        return dataStatusB;
    }

    public void setDataStatusB(boolean dataStatusB) {
        this.dataStatusB = dataStatusB;
    }

    // CONTRIBUTOR C


    public String getContributorNameC() {
        return contributorNameC;
    }

    public void setContributorNameC(String contributorNameC) {
        this.contributorNameC = contributorNameC;
    }

    public String getTeamNumberC() {
        return teamNumberC;
    }

    public void setTeamNumberC(String teamNumberC) {
        this.teamNumberC = teamNumberC;
    }

    public int getColorC() {
        return colorC;
    }

    public void setColorC(int colorC) {
        this.colorC = colorC;
    }

    public boolean isDatStatussC() {
        return datStatussC;
    }

    public void setDatStatussC(boolean datStatussC) {
        this.datStatussC = datStatussC;
    }

    // CONTRIBUTOR D


    public String getContributorNameD() {
        return contributorNameD;
    }

    public void setContributorNameD(String contributorNameD) {
        this.contributorNameD = contributorNameD;
    }

    public String getTeamNumberD() {
        return teamNumberD;
    }

    public void setTeamNumberD(String teamNumberD) {
        this.teamNumberD = teamNumberD;
    }

    public int getColorD() {
        return colorD;
    }

    public void setColorD(int colorD) {
        this.colorD = colorD;
    }

    public boolean isDataStatusD() {
        return dataStatusD;
    }

    public void setDataStatusD(boolean dataStatusD) {
        this.dataStatusD = dataStatusD;
    }

    // CONTRIBUTOR E


    public String getContributorNameE() {
        return contributorNameE;
    }

    public void setContributorNameE(String contributorNameE) {
        this.contributorNameE = contributorNameE;
    }

    public String getTeamNumberE() {
        return teamNumberE;
    }

    public void setTeamNumberE(String teamNumberE) {
        this.teamNumberE = teamNumberE;
    }

    public int getColorE() {
        return colorE;
    }

    public void setColorE(int colorE) {
        this.colorE = colorE;
    }

    public boolean isDataStatusE() {
        return dataStatusE;
    }

    public void setDataStatusE(boolean dataStatusE) {
        this.dataStatusE = dataStatusE;
    }

    // CONTRIBUTOR F


    public String getContributorNameF() {
        return contributorNameF;
    }

    public void setContributorNameF(String contributorNameF) {
        this.contributorNameF = contributorNameF;
    }

    public String getTeamNumberF() {
        return teamNumberF;
    }

    public void setTeamNumberF(String teamNumberF) {
        this.teamNumberF = teamNumberF;
    }

    public int getColorF() {
        return colorF;
    }

    public void setColorF(int colorF) {
        this.colorF = colorF;
    }

    public boolean isDataStatusF() {
        return dataStatusF;
    }

    public void setDataStatusF(boolean dataStatusF) {
        this.dataStatusF = dataStatusF;
    }
}
