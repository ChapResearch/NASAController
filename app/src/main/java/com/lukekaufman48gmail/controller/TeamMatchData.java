package com.lukekaufman48gmail.controller;

public class TeamMatchData {
    String teamNumber;
    String teamColor;
    String defTeam1;
    String defTeam2;
    String defTeam3;

    public TeamMatchData(String teamNumber, String teamColor, String defTeam1, String defTeam2, String defTeam3 ){
        this.teamNumber = teamNumber;
        this.teamColor = teamColor;
        this.defTeam1 = defTeam1;
        this.defTeam2 = defTeam2;
        this.defTeam3 = defTeam3;
    }

    public String getTeamNumber() {
        return this.teamNumber;
    }

    public String getTeamColor() {
        return this.teamColor;
    }

    public String getDefTeam1() {
        return this.defTeam1;
    }

    public String getDefTeam2() {
        return this.defTeam2;
    }

    public String getDefTeam3() {
        return this.defTeam3;
    }

    public void setDefTeam1(String defTeam1) {
        this.defTeam1 = defTeam1;
    }

    public void setDefTeam2(String defTeam2) {
        this.defTeam2 = defTeam2;
    }

    public void setDefTeam3(String defTeam3) {
        this.defTeam3 = defTeam3;
    }
}
