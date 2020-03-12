package com.lukekaufman48gmail.controller;

import android.app.Activity;
import android.app.Application;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ConfigFragment extends Fragment {

    //public static String[] configContents = {"","","",""};
    private final static String BUNDLE_KEY_MAP_STATE = "BUNDLE";
    private final static String CONTROLLERNAME_KEY_MAP_STATE = "CN";
    private final static String YEAR_KEY_MAP_STATE = "YEAR";
    private final static String COMPETITION_KEY_MAP_STATE = "COMPETITION";
    private final static String PASSWORD_KEY_MAP_STATE = "PASSWORD";
    private final static String MATCHTIME_KEY_MAP_STATE = "MATCHTIME";
    private final String TAG = "LUKER";
    public FMSInterface fms;
    //public static String[] configContents = {"Luke","2019","Home","doopy"};
    NASA_BLE ble;
    NASA_BLE_Interface ble_int;


    private Bundle savedState = null;
    private TextView controllerName;
    private TextView year;
    private TextView competition;
    private Button complistButton;
    private TextView password;
    private TextView matchTimeField;
    private Button fmsRefresh;
    private Button testButton;
    public static int matchTimeSec;
    public static CharSequence[] configContents;
    public CompetitionFragment CompetitionFragment = new CompetitionFragment(this);
    public String currentComp;


    //public static MainFragment mainFragment = MainActivity.mainFragment;

    public ConfigFragment(NASA_BLE_Interface i){
        ble_int = i;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.config_fragment, container, false);
        password = view.findViewById(R.id.password_field);
        competition = view.findViewById(R.id.competition_field);
        complistButton = view.findViewById(R.id.complist_button);
        year = view.findViewById(R.id.year_field);
        controllerName = view.findViewById(R.id.controllerName_field);
        matchTimeField = view.findViewById(R.id.matchtime_field);
        fmsRefresh = view.findViewById(R.id.fmsRefreshButton);

        Log.v("LUKER", "onCreateView for Config Fragment");

        final Globals globals = (Globals) getActivity().getApplicationContext();

        fms = new FMSInterface(getActivity());
        globals.setFms(fms);

        ConfigData configData = new ConfigData();
        globals.setConfigData(configData);

        controllerName.setText(globals.getConfigData().getName());
        year.setText(globals.getConfigData().getYear());
        competition.setText(globals.getConfigData().getCompetition());
        password.setText(globals.getConfigData().getCompetition());
        matchTimeField.setText(globals.getConfigData().getMatchTime());

        matchTimeField.setText("150");
        matchTimeField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(!matchTimeField.getText().toString().trim().equals("")) {
                    matchTimeSec = Integer.parseInt(matchTimeField.getText().toString().trim());
                    globals.getConfigData().setMatchTime(matchTimeField.getText().toString());
                }
                else {
                    matchTimeSec = 0;
                }
            }
        });

        fmsRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globals.getSelectedCompetition() == null || globals.getSelectedCompetition().getQualMatches().size()<5 ) {
                    if(globals.isMatchType())
                        globals.getSelectedCompetition().setPlayoffMatches(globals.getFms().readMatchJSON(getActivity().getApplicationContext(), globals.getSelectedCompetition().getCode(), "playoff"));
                    else
                        globals.getSelectedCompetition().setQualMatches(globals.getFms().readMatchJSON(getActivity().getApplicationContext(), globals.getSelectedCompetition().getCode(), "qual"));
                }
            }
        });


        controllerName.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                globals.getConfigData().setName(controllerName.getText().toString());
            }
        });


        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                globals.getConfigData().setPassword(password.getText().toString());
            }
        });


        year.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                globals.getConfigData().setYear(year.getText().toString());
            }
        });


        competition.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                globals.getConfigData().setCompetition(competition.getText().toString());
            }
        });



        complistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haveNetworkConnection(getContext())){
                    loadFragment(CompetitionFragment);
                    Log.v(TAG, "Competition Fragment Loaded");
                } else{
                    Toast.makeText(getContext(),"Check Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;

    }

    @Override
    public void onResume()
    {
        super.onResume();

        if(!CompetitionFragment.getSelectedCompetitionCode().equals("")) {
            competition.setText(CompetitionFragment.getSelectedCompetitionCode());
        }
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.v("LUKER", "On create for Config Fragment");


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();




    }

    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit(); // save the changes

    }

    private boolean haveNetworkConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public String getSelectedCompetition(){
        return currentComp;
    }

}
