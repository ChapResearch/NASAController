package com.lukekaufman48gmail.controller;
import android.app.Activity;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Button;


import org.w3c.dom.Text;

import java.util.*;

public class Contributor extends Activity {
    public Activity activity;

    public Activity MainAct;
    public int idContributor, idTeamNumber, idColor, idDataStatus;
    public String TAG = "LUKER";
    /*private TextView contributor_name;
    private TextView team_number;
    private ImageView color;
    private RadioButton data_status;*/

    public static String cn = "";
    public static String tn = "";
    public static ColorFilter c;
    public static boolean ds;



    public Contributor(Activity activity ,int cn, int tn, int c, int ds) {
        this.MainAct = activity;
        this.idContributor = cn;
        this.idTeamNumber = tn;
        this.idColor = c;
        this.idDataStatus = ds;
    }

    public void storeDisplay(){

        TextView contributorName = (MainAct).findViewById(this.idContributor);
        TextView teamNumber = (MainAct).findViewById(this.idTeamNumber);
        ImageView color = (MainAct).findViewById(this.idColor);
        RadioButton dataStatus = (MainAct).findViewById(this.idDataStatus);

        this.cn = contributorName.getText().toString();
        this.tn = teamNumber.getText().toString();
        this.c = color.getColorFilter();
        this.ds = dataStatus.isActivated();
    }

    public void loadDisplay(){
        TextView contributorName = (MainAct).findViewById(this.idContributor);
        TextView teamNumber = (MainAct).findViewById(this.idTeamNumber);
        ImageView color = (MainAct).findViewById(this.idColor);
        RadioButton dataStatus = (MainAct).findViewById(this.idDataStatus);

        boolean isNull = false;
        if(contributorName == null)
            isNull= true;

        Log.v(TAG, "Contributor is Null? " + isNull);
        Log.v(TAG, this.cn + " " + this.tn + " " + this.ds);
        try {
            if (this.cn != null)
                contributorName.setText(this.cn);
            if (this.tn != null)
                teamNumber.setText(this.tn);
            if (this.c != null)
                color.setColorFilter(this.c);
            //boolean default is false
            dataStatus.setActivated(this.ds);
        }
        catch (Exception e){
            Log.v(TAG, "Contributor is Null? " + isNull);
            Log.v(TAG, this.cn + " " + this.tn + " "+ this.ds);
            Log.v(TAG, "Something is Null here");
        }

    }




}
