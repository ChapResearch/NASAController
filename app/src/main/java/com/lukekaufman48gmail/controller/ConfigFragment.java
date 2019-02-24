package com.lukekaufman48gmail.controller;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ConfigFragment extends Fragment {

    //public static String[] configContents = {"","","",""};
    private final static String BUNDLE_KEY_MAP_STATE = "BUNDLE";
    private final static String CONTROLLERNAME_KEY_MAP_STATE = "CN";
    private final static String YEAR_KEY_MAP_STATE = "YEAR";
    private final static String COMPETITION_KEY_MAP_STATE = "COMPETITION";
    private final static String PASSWORD_KEY_MAP_STATE = "PASSWORD";
    //public static String[] configContents = {"Luke","2019","Home","doopy"};
    NASA_BLE ble;
    NASA_BLE_Interface ble_int;

    private Bundle savedState = null;
    private TextView controllerName;
    private TextView year;
    private TextView competition;
    private TextView password;
    public static CharSequence[] configContents;
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
        year = view.findViewById(R.id.year_field);
        controllerName = view.findViewById(R.id.controllerName_field);

        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle(BUNDLE_KEY_MAP_STATE);
        }
        if(savedState != null) {
            controllerName.setText(savedState.getCharSequence(CONTROLLERNAME_KEY_MAP_STATE));
            year.setText(savedState.getCharSequence(YEAR_KEY_MAP_STATE));
            competition.setText(savedState.getCharSequence(COMPETITION_KEY_MAP_STATE));
            password.setText(savedState.getCharSequence(PASSWORD_KEY_MAP_STATE));
        }
        savedState = null;
        return view;

    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.v("LUKER", "On create for Config Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState();
       controllerName = null;
       year = null;
       competition = null;
       password = null;
    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putCharSequence(CONTROLLERNAME_KEY_MAP_STATE, controllerName.getText());
        state.putCharSequence(YEAR_KEY_MAP_STATE, year.getText());
        state.putCharSequence(COMPETITION_KEY_MAP_STATE, competition.getText());
        state.putCharSequence(PASSWORD_KEY_MAP_STATE, password.getText());

        configContents = new String[4];

        configContents[0] = controllerName.getText().toString();
        configContents[1] = year.getText().toString();
        configContents[2] = competition.getText().toString();
        configContents[3] = password.getText().toString();
        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* If onDestroyView() is called first, we can use the previously savedState but we can't call saveState() anymore */
        /* If onSaveInstanceState() is called first, we don't have savedState, so we need to call saveState() */
        /* => (?:) operator inevitable! */
        outState.putBundle(BUNDLE_KEY_MAP_STATE, (savedState != null) ? savedState : saveState());
    }

}

