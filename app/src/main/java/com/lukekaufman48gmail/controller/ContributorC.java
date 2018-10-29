package com.lukekaufman48gmail.controller;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableRow;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Button;


import org.w3c.dom.Text;

import java.util.*;

public class ContributorC extends Activity {
    public Activity activity;
    private String team_number;
    private boolean color;
    private boolean status;
    private boolean changed = false; // when recieved first time-set false | second time - set true;
    private final String C_recieve = "b5c1d1ae-eb68-42f2-bc5f-278902252ca9";
    private final String C_transmit = "1a17ae70-1d3f-4193-81c1-99a40cd219cf";
    private TextView team_num;
    private ImageView team_color;
    private ImageView data_status;

    public ContributorC(String team_number, boolean color, boolean status, Activity activity, TextView team_num, ImageView c, ImageView s) {
        this.team_number = team_number;
        this.color = color;
        this.status = status;
        this.activity = activity;
        this.team_num = team_num;
        this.team_color = c;
        this.data_status = s;
    }

    public String getTeamNumber(){
        return this.team_number;
    }

    public boolean getColor(){
        return this.color;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setStatus(boolean s){
        this.status = s;
    }

    public void setTeamNumber(String team_number){
        this.team_number = team_number;
    }

    public void  setColor(boolean color){
        this.color = color;
    }

    public void UpdateDisplay(){
        //team number
        team_num.setText(this.team_number);

        //color=true-->blue color=false-->red
        if(this.color)
            team_color.setColorFilter(Color.argb(150, 0, 0, 255));
        else if(!this.color)
            team_color.setColorFilter(Color.argb(150, 0,0,225));

        //status
        if(this.status)
            this.data_status.setImageResource(R.drawable.frc_logo);

        // * turn off if slot empty turn on if filled
        // * update time
    }
    public void ClearDisplay(){
        team_num.setText("####");
        team_color.setColorFilter(Color.argb(150, 255, 0, 255));
    }



}