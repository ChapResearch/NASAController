package com.lukekaufman48gmail.controller;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Config;
import android.widget.TableRow;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.Switch;
import android.widget.Toast;
import android.graphics.Color;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;

import android.os.ParcelUuid;
import java.util.*;


public class MainActivity extends AppCompatActivity{

    Switch onSwitch;
    NASA_BLE ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new MainFragment());

        final Button config_button = findViewById(R.id.config_button);
        config_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConfigFragment c = new ConfigFragment();
                loadFragment(c);
            }
        });

        Button main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(new MainFragment());
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
    }

    private final NASA_BLE_Interface bleCallbacks = new NASA_BLE_Interface() {



        @Override
        public String NASA_controllerName() {

            // TODO - these should access some UI component for the name of the controller
            //       or even go find the bluetooth name
            TextView controllerName = findViewById(R.id.controllerName_field);


            return (controllerName.getText().toString());
        }

        @Override
        public String NASA_password() {

            // TODO - these should access some UI component for the password
            TextView password = findViewById(R.id.password_field);

            return (password.getText().toString());
        }

        @Override
        public void NASA_slotChange(int slot, boolean claimed) {
            ImageView indicator = null;

            switch (slot) {
                case 0:
                    indicator = findViewById(R.id.A_status_image);
                    break;
                case 1:
                    indicator = findViewById(R.id.B_status_image);
                    break;
                case 2:
                    indicator = findViewById(R.id.C_status_image);
                    break;
                case 3:
                    indicator = findViewById(R.id.D_status_image);
                    break;
                case 4:
                    indicator = findViewById(R.id.E_status_image);
                    break;
                case 5:
                    indicator = findViewById(R.id.F_status_image);
                    break;
            }
            int id = getResources().getIdentifier("com.lukekaufman48gmail.controller:drawable/" + "on_star.png", null, null);
            if (claimed)
                indicator.setImageResource(id);
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

            int color;
            switch (givenColor) {
                case 1:
                    color = Color.BLUE;
                    break;
                case 2:
                    color = Color.RED;
                    break;
                default:
                    color = Color.LTGRAY;
                    break;
            }

            teamColor.setBackgroundColor(color);
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
        public void NASA_dataTransmission(int slot, String jsonData) {

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

}
