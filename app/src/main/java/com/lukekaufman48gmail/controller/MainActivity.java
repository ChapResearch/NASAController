package com.lukekaufman48gmail.controller;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Config;
import android.widget.CheckBox;
import android.widget.RadioButton;
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
import android.util.Log;

import android.os.ParcelUuid;
import org.w3c.dom.Text;
import java.net.InterfaceAddress;
import java.util.*;


public class MainActivity extends AppCompatActivity{

    NASA_BLE ble;
    TextView matchNum_field;

    private final NASA_BLE_Interface bleCallbacks = new NASA_BLE_Interface() {

        @Override
        public String NASA_controllerName() {

            return (ConfigFragment.configContents[0].toString().trim());
        }


        @Override
        public String NASA_year() {

            return (ConfigFragment.configContents[1].toString().trim());
        }

        @Override
        public String NASA_competition() {

            return (ConfigFragment.configContents[2].toString().trim());
        }

        @Override
        public String NASA_password() {

            return (ConfigFragment.configContents[3].toString().trim());
        }

        @Override
        public String NASA_match() {
            TextView matchNum = findViewById(R.id.matchNum_field);
            return (matchNum.getText().toString());
        }

        @Override
        public void NASA_slotChange(int slot, boolean claimed) {
            TableRow indicator = null;

            switch (slot) {
                case 0:
                    indicator = findViewById(R.id.Contributor_A_display);
                    break;
                case 1:
                    indicator = findViewById(R.id.Contributor_B_display);
                    break;
                case 2:
                    indicator = findViewById(R.id.Contributor_C_display);
                    break;
                case 3:
                    indicator = findViewById(R.id.Contributor_D_display);
                    break;
                case 4:
                    indicator = findViewById(R.id.Contributor_E_display);
                    break;
                case 5:
                    indicator = findViewById(R.id.Contributor_F_display);
                    break;
            }
            if (claimed) {
                indicator.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.yellow_indicator));
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

            int color;
            switch (givenColor) {
                case 1:
                    color = Color.parseColor("#ea5165a6");
                    break;
                case 2:
                    color = Color.parseColor("#d7ce6d6d");
                    break;
                default:
                    color = Color.parseColor("#b1cbdb");
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
        public void NASA_dataTransmission(int slot, boolean finalChunk, String jsonData)
        {
            RadioButton box = null;

            switch(slot) {
                default:
                case 0:    	box =  findViewById(R.id.A_status); break;
                case 1:    	box =  findViewById(R.id.B_status); break;
                case 2:    	box =  findViewById(R.id.C_status); break;
                case 3:    	box =  findViewById(R.id.D_status); break;
                case 4:    	box =  findViewById(R.id.E_status); break;
                case 5:    	box =  findViewById(R.id.F_status); break;
            }
            box.setChecked(finalChunk);
        }

        @Override
        public void NASA_dataUploadStatus(int slot, boolean success)
        {

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
    //public static ArrayList<Contributor> contributors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(mainFragment);

        //Set up all contributors with IDs for storing data into Contributor class
        //contributors.add(new Contributor(this,R.id.A_name, R.id.A_teamNumber, R.id.A_color, R.id.A_status));
        //contributor_A = findViewById(R.id.A_contributorName);

        final Button config_button = findViewById(R.id.config_button);
        config_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*for(Contributor c: contributors){
                    c.storeDisplay();

                }*/


                //Log.v("LUKER", "PLEASE WORK " + MainFragment.AcontributorName + " " + MainFragment.AteamNumber + " " + MainFragment.Astatus);
                loadFragment(configFragment);
            }
        });

        final Button main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(mainFragment);
                /*for(Contributor c: contributors){
                    c.loadDisplay();
                }*/

            /*
                ((TextView)findViewById(R.id.A_name)).setText(MainFragment.AcontributorName);
                ((TextView)findViewById(R.id.A_teamNumber)).setText(MainFragment.AcontributorName);
                ((ImageView)findViewById(R.id.A_color)).setColorFilter(MainFragment.Acolor);
                (findViewById(R.id.A_status)).setActivated(MainFragment.Astatus);*/

            }
        });

        ble = new NASA_BLE(this);
        ble.startServer(bleCallbacks);

        final Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ble.startContributors();
            }
        });

        // monitor the stop button

        final Button stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ble.stopContributors();
            }
        });

        // monitor the reset button

        final Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ble.resetContributors();
            }
        });

        final Button uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ble.transmitContributors();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Data Uploaded - maybe?",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });


        //Match Number Counter vvv
        final Button subtract_button = findViewById(R.id.subtract_button);
        final Button add_button = findViewById(R.id.add_button);
        final TextView matchNum_field = findViewById(R.id.matchNum_field);
        matchNum_field.setText("1");

        matchNum_field.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                ble.resetContributors();
            }
        });

        subtract_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!matchNum_field.getText().toString().equals("")){
                    int num = Integer.parseInt(matchNum_field.getText().toString());
                        if(num>1){
                            num--;
                            matchNum_field.setText("" + num);
                            ble.resetContributors();
                        }
                    }
                }
            });

        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(matchNum_field.getText().toString().equals("")) {
                    matchNum_field.setText("" + 1);
                }
                else{
                    int num = Integer.parseInt(matchNum_field.getText().toString()) + 1;
                    matchNum_field.setText("" + num);
                }
                ble.resetContributors();

            }
        });
        // Match Number Counter ^^^
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



}
