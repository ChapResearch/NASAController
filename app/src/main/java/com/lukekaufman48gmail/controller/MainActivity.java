package com.lukekaufman48gmail.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TableRow;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.widget.ToggleButton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    NASA_BLE ble;
    TextView matchNum_field;

    // dataStatus - monitors the current status of data that has been transmitted to the Controller
    //   from the contributors.  True means "hasData".  In java, boolean arrays init to false;
    private boolean[] dataStatus = new boolean[6];

    private void dataStatusClear() {
        for(int i=0; i < dataStatus.length; i++) {
            dataStatus[i] = false;
        }
    }

    private final NASA_BLE_Interface bleCallbacks = new NASA_BLE_Interface() {

        @Override
        public String NASA_controllerName() {
            final Globals globals = (Globals)getApplicationContext();
            return (globals.getConfigData().getName().toString().trim());
        }


        @Override
        public String NASA_year() {
            final Globals globals = (Globals)getApplicationContext();
            return (globals.getConfigData().getYear().toString().trim());
        }

        @Override
        public String NASA_competition() {
            final Globals globals = (Globals)getApplicationContext();
            return (globals.getConfigData().getCompetition().toString().trim());
        }

        @Override
        public String NASA_password() {
            final Globals globals = (Globals)getApplicationContext();
            return (globals.getConfigData().getPassword().toString().trim());
        }

        @Override
        public String NASA_match() {
            Globals globals = (Globals) getApplicationContext();
            TextView matchNum = findViewById(R.id.matchNum_field);
            int matchNumb = Integer.parseInt(matchNum.getText().toString());
            if(globals.isMatchType()) {
                return (matchNumb + globals.getSelectedCompetition().getQualMatches().size() + "");
            }
            else{
                return matchNum.getText().toString().trim();
            }
        }

        @Override
        public void NASA_slotChange(int slot, boolean claimed) {
            TableRow indicator = null;
            TextView CN = null;
            TextView TN = null;
            RadioButton DS = null;
            ImageView C = null;
            Globals globals = (Globals) getApplicationContext();

            switch (slot) {
                case 0:
                    indicator = findViewById(R.id.Contributor_A_display);
                    CN = findViewById(R.id.A_name);
                    TN = findViewById(R.id.A_teamNumber);
                    DS = findViewById(R.id.A_status);
                    C = findViewById(R.id.A_color);
                    break;
                case 1:
                    indicator = findViewById(R.id.Contributor_B_display);
                    CN = findViewById(R.id.B_name);
                    TN = findViewById(R.id.B_teamNumber);
                    DS = findViewById(R.id.B_status);
                    C = findViewById(R.id.B_color);
                    break;
                case 2:
                    indicator = findViewById(R.id.Contributor_C_display);
                    CN = findViewById(R.id.C_name);
                    TN = findViewById(R.id.C_teamNumber);
                    DS = findViewById(R.id.C_status);
                    C = findViewById(R.id.C_color);
                    break;
                case 3:
                    indicator = findViewById(R.id.Contributor_D_display);
                    CN = findViewById(R.id.D_name);
                    TN = findViewById(R.id.D_teamNumber);
                    DS = findViewById(R.id.D_status);
                    C = findViewById(R.id.D_color);
                    break;
                case 4:
                    indicator = findViewById(R.id.Contributor_E_display);
                    CN = findViewById(R.id.E_name);
                    TN = findViewById(R.id.E_teamNumber);
                    DS = findViewById(R.id.E_status);
                    C = findViewById(R.id.E_color);
                    break;
                case 5:
                    indicator = findViewById(R.id.Contributor_F_display);
                    CN = findViewById(R.id.F_name);
                    TN = findViewById(R.id.F_teamNumber);
                    DS = findViewById(R.id.F_status);
                    C = findViewById(R.id.F_color);
                    break;
            }
            if (claimed) {
                indicator.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.claimedborder));
                //globals.getMainFragmentData().setConnectionStatus(slot, true);
            } else {
                indicator.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.border));
                CN.setText(" No Name ");
                TN.setText("#####");
                DS.setActivated(false);
                C.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.colorborder));
                globals.getMainFragmentData().setConnectionStatus(slot, false);
            }
        }

        @Override
        public void NASA_teamColor(int slot, int givenColor)    // 0x00 gray, 0x01 blue, 0x02 red, else gray
        {
            ImageView teamColor;
            switch (slot) {
                default:
                case 0:
                    teamColor = findViewById(R.id.A_color);
                    break;
                case 1:
                    teamColor = findViewById(R.id.B_color);
                    break;
                case 2:
                    teamColor = findViewById(R.id.C_color);
                    break;
                case 3:
                    teamColor = findViewById(R.id.D_color);
                    break;
                case 4:
                    teamColor = findViewById(R.id.E_color);
                    break;
                case 5:
                    teamColor = findViewById(R.id.F_color);
                    break;
            }

            switch (givenColor) {
                case 1:
                    teamColor.setBackgroundColor(Color.BLUE);
                    Log.v(TAG, "Setting contributor color to BLUE");
                    break;
                case 2:
                    teamColor.setBackgroundColor(Color.RED);
                    Log.v(TAG, "Setting contributor color to RED");
                    break;
                default:
                    teamColor.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.transparent));
                    break;
            }


        }

        @Override
        public void NASA_teamNumber(int slot, String number) {
            TextView team;
            switch (slot) {
                default:
                case 0:
                    team = (TextView) findViewById(R.id.A_teamNumber);
                    break;
                case 1:
                    team = (TextView) findViewById(R.id.B_teamNumber);
                    break;
                case 2:
                    team = (TextView) findViewById(R.id.C_teamNumber);
                    break;
                case 3:
                    team = (TextView) findViewById(R.id.D_teamNumber);
                    break;
                case 4:
                    team = (TextView) findViewById(R.id.E_teamNumber);
                    break;
                case 5:
                    team = (TextView) findViewById(R.id.F_teamNumber);
                    break;
            }
            team.setText(number);
        }

        @Override
        public void NASA_dataTransmission(int slot, boolean finalChunk, String jsonData) {
            RadioButton box = null;

            switch (slot) {
                default:
                case 0:
                    box = findViewById(R.id.A_status);
                    break;
                case 1:
                    box = findViewById(R.id.B_status);
                    break;
                case 2:
                    box = findViewById(R.id.C_status);
                    break;
                case 3:
                    box = findViewById(R.id.D_status);
                    break;
                case 4:
                    box = findViewById(R.id.E_status);
                    break;
                case 5:
                    box = findViewById(R.id.F_status);
                    break;
            }
            box.setChecked(finalChunk);
	        dataStatus[slot] = finalChunk;
        }

        @Override
        public void NASA_dataUploadStatus(int slot, boolean success) {

        }

        @Override
        public void NASA_contributorName(int slot, String contributorName) {
            TextView name;
            switch (slot) {
                default:
                case 0:
                    name = findViewById(R.id.A_name);
                    break;
                case 1:
                    name = findViewById(R.id.B_name);
                    break;
                case 2:
                    name = findViewById(R.id.C_name);
                    break;
                case 3:
                    name = findViewById(R.id.D_name);
                    break;
                case 4:
                    name = findViewById(R.id.E_name);
                    break;
                case 5:
                    name = findViewById(R.id.F_name);
                    break;
            }
            name.setText(contributorName);
        }


    };




    public MainFragment mainFragment = new MainFragment();
    public ConfigFragment configFragment = new ConfigFragment(bleCallbacks);
    public CompetitionFragment competitionFragment = new CompetitionFragment(configFragment);
    public Activity mainActivity = this;
    public String conF = "conF";
    public String mainF = "mainF";
    public String compF = "compF";
    private String TAG = "LUKER";
    public String currentFragment;
    public Competition selectedCompetition;
    public final boolean UP = true;
    public final boolean DOWN = false;
    public int matchTimeMilli = 150 * 1000; // default 2.5 min
    public CountDownTimer animationCountdown;
    public CountDownTimer matchUpdateCoolDownCountdown;
    public CountDownTimer startButtonCoolDownCountdown;
    public boolean matchUpdateCooledDown = true;
    public TextView MT; //match time display on Main Activity
    public boolean timerOff; // checks if timer is Off


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Globals globals = (Globals)getApplicationContext();

        globals.setBleCallbacks(bleCallbacks);

        setContentView(R.layout.activity_main);
        MT = findViewById(R.id.matchTime);
        loadFragment(configFragment);


        Log.v("LUKER", "On create for MainActivity");

        ble = new NASA_BLE(this, bleCallbacks);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device does not support bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (mBluetoothAdapter.isEnabled()) {
                ble.startServer();
            } else {
                Toast.makeText(getApplicationContext(), "Check if bluetooth is enabled.", Toast.LENGTH_LONG).show();
            }
        }

        //Buttons to load different fragments
        final Button config_button = findViewById(R.id.config_button);
        config_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(configFragment);
            }
        });

        //Main Button
        final Button main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get Match Time from the Config page
                if (ConfigFragment.matchTimeSec > 1 && ConfigFragment.matchTimeSec < 999)
                    matchTimeMilli = ConfigFragment.matchTimeSec * 1000;
                else {
                    makeTextToast("Check match time on Config Screen", Toast.LENGTH_LONG);
                }

                if (timerOff)
                    MT.setText(" " + ((matchTimeMilli) / 1000) + " sec");

                //check if competition field is empty - if not - then assign selectedCompetition
                String compCode = globals.getConfigData().getCompetition();

                if(compCode!=null && !compCode.equals("")){
                    selectedCompetition = globals.getSelectedCompetition();
                    selectedCompetition.setCode(compCode);
                }
                /*if (competitionFragment.getSelectedCompetitionCode().equals("")) {
                    selectedCompetition = competitionFragment.getSelectedCompetition();
                    matchList = selectedCompetition.getMatches();
                }*/
                loadFragment(mainFragment);
                Log.v(TAG, "current fragment (supposed to be Main): " + currentFragment);
            }
        });

        //Match Number Counter vvv
        final Button subtract_button = findViewById(R.id.subtract_button);
        final Button add_button = findViewById(R.id.add_button);
        final TextView matchNum_field = findViewById(R.id.matchNum_field);
        matchNum_field.setText("0");

        matchNum_field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        if(globals.isUploaded()){
                            updateMatch();
                            hideKeyboard(mainActivity);
                            globals.setUploaded(false);
                        }
                        else{
                            showMatchUpdateConfirmation(2);
                        }

                        return true;
                }
                return false;
            }
        });



            subtract_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    int currentMatch = Integer.parseInt(matchNum_field.getText().toString());
                    if(!(globals.getSelectedCompetition().getCode()==null) || !globals.getSelectedCompetition().getCode().equals("")){
                        if(currentMatch>1) {
                            if (matchUpdateCooledDown) {
                                if (globals.isUploaded()) {
                                    matchUpdateCooledDown = false;
                                    matchNum_field.setText(currentMatch - 1 + "");
                                    updateMatch();
                                    matchUpdateCoolDownTimer(5000);
                                    globals.setUploaded(false);
                                } else {
                                    showMatchUpdateConfirmation(0);
                                }
                            }
                        }
                        else{
                            makeTextToast("Please select a competition from the list.", Toast.LENGTH_LONG);
                        }
                    }

                }
            });

            add_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    int currentMatch = Integer.parseInt(matchNum_field.getText().toString());
                    if(!(globals.getSelectedCompetition().getCode()==null) || !globals.getSelectedCompetition().getCode().equals(""))
                        if(matchUpdateCooledDown && globals.isUploaded()){
                            matchUpdateCooledDown = false;
                            matchNum_field.setText(currentMatch + 1 + "");
                            updateMatch();
                            matchUpdateCoolDownTimer(5000);
                            globals.setUploaded(false);
                        }
                        else{
                            showMatchUpdateConfirmation(1);
                        }
                    else{
                        makeTextToast("Please select a competition from the list", Toast.LENGTH_LONG);
                    }
                }
            });


        // Match Number Counter ^^^

        //qual/playoff button
        final ToggleButton matchTypeToggle = findViewById(R.id.matchTypeToggle);
        matchTypeToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    matchNum_field.setText("0");
                    globals.setMatchType(true);
                    globals.getSelectedCompetition().setPlayoffMatches(globals.getFms().readMatchJSON(getBaseContext() ,globals.getSelectedCompetition().getCode(), "playoff"));
                    Log.v(TAG, "Match Type (True, playoffs): " + globals.isMatchType());
                } else {
                    matchNum_field.setText("0");
                    globals.setMatchType(false);
                    globals.getSelectedCompetition().setQualMatches(globals.getFms().readMatchJSON(getBaseContext() ,globals.getSelectedCompetition().getCode(), "qual"));
                    Log.v(TAG, "Match Type (false, quals): " + globals.isMatchType());

                }
            }
        });

        //upload button
        final Button uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeAnim(uploadButton);
                //ble.transmitContributors();
                showUploadConfirmation();
                playBruh();
            }
        });

        final Button startButton = findViewById(R.id.start_button);
        startButton.setEnabled(true); //have the start button be enabled on launch

        final Button stopButton = findViewById(R.id.stop_button);
        stopButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.greyed_out));


        stopButton.setEnabled(false);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start button animations
                shakeAnim(startButton);
                timerAnimationAndStartButtonCoolDown(startButton, matchTimeMilli, stopButton);
                //start timer
                ble.startContributors();
                makeTextToast("Timer Started", Toast.LENGTH_LONG);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeAnim(stopButton);
                ble.stopContributors();
                if (!(animationCountdown == null)) {
                    Log.v(TAG, "IS animationCountdown null? if im seeing this then no.");
                    animationCountdown.onFinish();
                    animationCountdown.cancel();
                }
            }
        });
    }


    private void loadFragment(Fragment fragment) {
    // create a FragmentManager
        FragmentManager fm = getFragmentManager();
    // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
    // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit(); // save the changes

        if(fragment == mainFragment){
            currentFragment = mainF;
        }
        else if(fragment == configFragment){
            currentFragment = conF;
        }
    }


    //TODO - add confirmation of closing app will make you lose connections
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("LUKER", "onDestroy for mainActivity");
        ble.stopServer();

    }

    public void shakeAnim(Button b){
        Animation shake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
        b.startAnimation(shake);
    }

    public void timerAnimationAndStartButtonCoolDown(Button startBtn, final int matchTimeMilli, Button stopBtn){
        //make text green
        final Button startButton = startBtn;
        final Button stopButton = stopBtn;
        startButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.brightgreen));

        //fade in and out animation
        final Animation fade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade);

        //
        timerOff = false;

        stopButton.setEnabled(true); //enable stop button when timer is going
        stopButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        startButton.setEnabled(false);

        //timer set for however long it is set in config page (match length) and checks every second
        animationCountdown = new CountDownTimer(matchTimeMilli, 1000) {
            int i=0;
            public void onTick(long millisUntilFinished) {
                startButton.setAnimation(fade);
                MT.setText((matchTimeMilli/1000)-i + " sec");
                i++;
            }

            public void onFinish() {
                timerOff = true;
                ble.stopContributors();
                stopButton.setEnabled(false);
                stopButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.greyed_out));
                startButton.setEnabled(true);
                startButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                makeTextToast("Timer Stopped", Toast.LENGTH_LONG);
                MT.setText(" " +(matchTimeMilli/1000) + " sec"); //resets MT display
            }
        }.start();
    }

    public void matchUpdateCoolDownTimer(int matchUpdateCoolDownTimeMilli){
        matchUpdateCoolDownCountdown = new CountDownTimer(matchUpdateCoolDownTimeMilli, 1000) {

            public void onTick(long millisUntilFinished) {
                makeTextToast("Wait: " + millisUntilFinished/1000, Toast.LENGTH_SHORT);
            }

            public void onFinish() {
                matchUpdateCooledDown=true;
                makeTextToast("You can update the match now.", Toast.LENGTH_SHORT);
            }
        }.start();
    }



    public void playBruh(){
         MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.bruh);
            ring.start();
    }

    public void makeTextToast(String message, int duration){
        Toast toast = Toast.makeText(getApplicationContext(),
                message,
                duration);

        toast.show();
    }


    public void updateMatch(){
        final Globals globals = (Globals)getApplicationContext();

        final TextView matchNum_field = findViewById(R.id.matchNum_field);

        if (globals.getConfigData().getCompetition()!= null && !globals.getConfigData().getCompetition().equals("")) {
            ble.resetContributors();
            ble.matchUpdateContributors();
            ArrayList<MatchData> matchList;
            MatchData currentMatch = null;

            if(globals.isMatchType()){
                matchList = globals.getSelectedCompetition().getPlayoffMatches();
            }
            else{
                matchList = globals.getSelectedCompetition().getQualMatches();
            }

            if(matchList != null || matchList.size()!=0) {
                for (int x = 0; x < matchList.size(); x++) {
                    if (matchList.get(x).getMatchNumber().equals(matchNum_field.getText().toString())) {
                        currentMatch = matchList.get(x);
                        Log.v(TAG, "Number in field: " + matchNum_field.getText().toString() + " | " + "Match Number selected: " + matchList.get(x).getMatchNumber());
                    }
                }
            }
            else{
                Log.v(TAG, "Matches haven't been scraped yet");
                makeTextToast("Matches haven't been scraped yet OR match list empty", Toast.LENGTH_LONG);
            }

            ArrayList<TeamMatchData> blueTeams = currentMatch.getBlueTeams();
            ArrayList<TeamMatchData> redTeams = currentMatch.getRedTeams();

            Log.v(TAG, "Number of blue teams: " + blueTeams.size());
            Log.v(TAG, "Number of red teams: " + redTeams.size());

            //Blue teams will sends to slots 0,1,2 | Red teams will send to slots 3,4,5
            //send blue teams
            for (int i = 0; i < 3; i++) {

                Map<String, String> blueTeamMap = new HashMap<>();
                if (blueTeams.size() == 3) {
                    blueTeamMap.put("teamNumber", blueTeams.get(i).getTeamNumber());
                    blueTeamMap.put("teamColor", blueTeams.get(i).getTeamColor().toLowerCase());
                    blueTeamMap.put("defTeam1", blueTeams.get(i).getDefTeam1());
                    blueTeamMap.put("defTeam2", blueTeams.get(i).getDefTeam2());
                    blueTeamMap.put("defTeam3", blueTeams.get(i).getDefTeam3());
                } else {
                    Log.v(TAG, "Pulled wrong number of teams, pulled: " + blueTeams.size() + " teams");
                }

                ble.dataPushContributor(i, blueTeamMap);
                Log.v(TAG, "Blue: " + blueTeamMap);
            }

            //send red teams
            for (int i = 3; i < 6; i++) {

                Map<String, String> redTeamMap = new HashMap<>();
                if (redTeams.size() == 3) {
                    redTeamMap.put("teamNumber", redTeams.get(i - 3).getTeamNumber());
                    redTeamMap.put("teamColor", redTeams.get(i - 3).getTeamColor().toLowerCase());
                    redTeamMap.put("defTeam1", redTeams.get(i - 3).getDefTeam1());
                    redTeamMap.put("defTeam2", redTeams.get(i - 3).getDefTeam2());
                    redTeamMap.put("defTeam3", redTeams.get(i - 3).getDefTeam3());
                } else {
                    Log.v(TAG, "Pulled wrong number of teams, pulled: " + redTeams.size() + " teams");
                }

                ble.dataPushContributor(i, redTeamMap);
                Log.v(TAG, "RED: " + redTeamMap);
            }
            dataStatusClear();
        } else {
            Log.v(TAG, "NO SELECTED COMPETITION");
            makeTextToast("No selected Competition", Toast.LENGTH_LONG);
        }
    }


    public void showMatchUpdateConfirmation(int state) {

        if (currentFragment == mainF) {
            //state = 0 - down 1 match\
            //state = 1 - up 1 match
            //state = 2 - match num change
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you want to update match?");

            builder.setMessage("There might be un-uploaded data."); // should be warning variable
            builder.setCancelable(false);

            final Globals globals = (Globals)getApplicationContext();
            final int direction = state;// finalization required for use in builder

            builder.setPositiveButton("Update Match", new DialogInterface.OnClickListener() {

                final TextView matchNum_field = findViewById(R.id.matchNum_field);

                int currentMatch = Integer.parseInt(matchNum_field.getText().toString());
                @Override
                public void onClick(DialogInterface dialog, int which){
                        matchUpdateCooledDown = false;
                        matchUpdateCoolDownTimer(5000);
                        switch(direction){
                            case 0:
                                matchNum_field.setText(currentMatch - 1 + "");
                                updateMatch();
                                break;
                            case 1:
                                matchNum_field.setText(currentMatch + 1 + "");
                                updateMatch();
                                break;
                            case 2:
                                updateMatch();
                                hideKeyboard(mainActivity);
                                break;
                        }

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    makeTextToast("Match update Canceled", Toast.LENGTH_SHORT);
                }
            });

            builder.show();

        }
        else{
            makeTextToast("Please leave the Config Page.", Toast.LENGTH_SHORT);
        }
    }


    public void showUploadConfirmation() {

        if (currentFragment == mainF) {

            RadioButton a = findViewById(R.id.A_status);
            Log.v("LUKER", "is a activated? :" + a.isActivated());
            //check which contributors have sent in data

            String warning = "These Scouters have not sent in data: ";
            String append = "";

            for(int i=0; i < dataStatus.length; i++) {
                if(!dataStatus[i]) {
                    if(append.length()>0) { append += ", "; }
                    switch(i) {
                        case 0: append += "A"; break;
                        case 1: append += "B"; break;
                        case 2: append += "C"; break;
                        case 3: append += "D"; break;
                        case 4: append += "E"; break;
                        case 5: append += "F"; break;
                    }
                }
            }

            Log.v("LUKER", append);
            warning = warning + append;


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you want to upload?");

            builder.setMessage(warning); // should be warning variable
            builder.setCancelable(false);

            final Globals globals = (Globals)getApplicationContext();

            builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    //check for empty config fields
                    boolean configCompleted = true;
                    if(globals.getConfigData().getCompetition() == null ||
                            globals.getConfigData().getCompetition().equals("") ||
                            globals.getConfigData().getPassword() == null ||
                            globals.getConfigData().getPassword().equals("") ||
                            globals.getConfigData().getYear() == null||
                            globals.getConfigData().getYear().equals("") ||
                            globals.getConfigData().getName() == null ||
                            globals.getConfigData().getName().equals("") ||
                            globals.getConfigData().getMatchTime() == null ||
                            globals.getConfigData().getMatchTime().equals("")

                    ){
                        configCompleted=false;
                    }

                    //allow upload only if all fields are filled
                    if(configCompleted){
                        ble.transmitContributors();
                        globals.setUploaded((true));
                        makeTextToast("Data Uploaded", Toast.LENGTH_SHORT);
                    }
                    else
                        makeTextToast("Check Config page for empty field", Toast.LENGTH_LONG);

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    makeTextToast("Data Upload Canceled", Toast.LENGTH_SHORT);
                }
            });

            builder.show();

        }
        else{
            makeTextToast("Please leave the Config page.", Toast.LENGTH_LONG);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
