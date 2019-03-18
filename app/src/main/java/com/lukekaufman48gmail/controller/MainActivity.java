package com.lukekaufman48gmail.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;

import org.w3c.dom.Text;


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
            TextView CN = null;
            TextView TN = null;
            RadioButton DS = null;
            ImageView C = null;

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
                indicator.setBackground(ContextCompat.getDrawable(getBaseContext(),R.drawable.claimedborder));
            }
            else{
                indicator.setBackground(ContextCompat.getDrawable(getBaseContext(),R.drawable.border));
                CN.setText(" No Name ");
                TN.setText("#####");
                DS.setActivated(false);
                C.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.colorborder));
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
                    teamColor.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.blue_tint));
                    break;
                case 2:
                    teamColor.setBackgroundColor(ContextCompat.getColor(getBaseContext(),R.color.red_tint));
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
    public String conF = "conF";
    public String mainF = "mainF";
    public String currentFragment;
    public boolean toastCanceled = false;
    public boolean toastUploaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(configFragment);
        Log.v("LUKER", "On create for MainActivity");

        ble = new NASA_BLE(this);
        ble.startServer(bleCallbacks);

        final Button config_button = findViewById(R.id.config_button);
        config_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(configFragment);
            }
        });

        final Button main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(mainFragment);

            }
        });



        final Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeAnim(startButton);
                ble.startContributors();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Time started",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });

        // monitor the stop button

        final Button stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shakeAnim(stopButton);
                ble.stopContributors();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Time Stopped",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });


        final Button uploadButton = findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeAnim(uploadButton);
                //ble.transmitContributors();
                showUploadConfirmation();
            }
        });


        //Match Number Counter vvv
        final Button subtract_button = findViewById(R.id.subtract_button);
        final Button add_button = findViewById(R.id.add_button);
        final TextView matchNum_field = findViewById(R.id.matchNum_field);
        matchNum_field.setText("1");

        matchNum_field.addTextChangedListener(new TextWatcher() {

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
                ble.resetContributors();
                ble.matchUpdateContributors();
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
                            ble.matchUpdateContributors();
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
                ble.matchUpdateContributors();
                playBruh();

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

        if(fragment == mainFragment){
            currentFragment = mainF;
        }
        else if(fragment == configFragment){
            currentFragment = conF;
        }
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("LUKER", "onDestroy for mainActivity");
        ble.stopServer();

    }*/

    //TODO - add confirmation of closing app will make you lose connections
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("LUKER", "onStop for mainActivity");
        ble.stopServer();

    }

    public void shakeAnim(Button b){
        Animation shake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
        b.startAnimation(shake);
    }

    public void playBruh(){
        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.bruh);
        ring.start();
    }

    public void showUploadConfirmation() {

        if (currentFragment == mainF) {

            //check which contributors have sent in data
            RadioButton[] statuses = {findViewById(R.id.A_status),
                    findViewById(R.id.B_status),
                    findViewById(R.id.C_status),
                    findViewById(R.id.D_status),
                    findViewById(R.id.E_status),
                    findViewById(R.id.F_status)
            };

            String warning = "These Scouters have not sent in data: ";
            String append = "";
            for (int i = 0; i < statuses.length; i++) {
                if (!statuses[i].isActivated()) {
                    if (i == 0)
                        append += "A, ";
                    else if (i == 1)
                        append += "B, ";
                    else if (i == 2)
                        append += "C, ";
                    else if (i == 3)
                        append += "D, ";
                    else if (i == 4)
                        append += "E, ";
                    else if (i == 5)
                        append += "F, ";
                }
            }
            if(append.length()>2)
            append = append.substring(0, append.length() - 2);


            Log.v("LUKER", append);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure you want to upload?");

            warning = warning + append;
            builder.setMessage(warning);
            builder.setCancelable(false);


            if(append.length()>1) {
                builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                ble.transmitContributors();
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Data Uploaded",
                                        Toast.LENGTH_SHORT);

                                toast.show();
                            }
                });
            }

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Data Upload Canceled",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
            });

            builder.show();


            /*LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

            final TextView mainToastText = (TextView) layout.findViewById(R.id.toast_text);
            mainToastText.setText("Are you sure you want to upload?");
            RadioButton[] statuses = {findViewById(R.id.A_status),
                    findViewById(R.id.B_status),
                    findViewById(R.id.C_status),
                    findViewById(R.id.D_status),
                    findViewById(R.id.E_status),
                    findViewById(R.id.F_status)};

            final TextView warningText = layout.findViewById(R.id.warning_text);
            String warning = "These Scouters have not sent in data: ";
            for (int i = 0; i < statuses.length; i++) {
                if (!statuses[i].isActivated()) {
                    if (i == 0)
                        warning += "A, ";
                    else if (i == 1)
                        warning += "B, ";
                    else if (i == 2)
                        warning += "C, ";
                    else if (i == 3)
                        warning += "D, ";
                    else if (i == 4)
                        warning += "E, ";
                    else if (i == 5)
                        warning += "F";
                }
            }

            //Cancel button closes Toast
            final Button cancelButton = layout.findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    toastCanceled = true;
                }
            });

            final Button uploadButton = layout.findViewById(R.id.uploadConfirmButton);
            uploadButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ble.uploadContributorData();
                    warningText.setVisibility(View.INVISIBLE);
                    //uploadButton.setVisibility(View.GONE);
                    //cancelButton.setVisibility(View.GONE);
                    mainToastText.setText("Data Uploaded.");
                    mainToastText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    toastUploaded = true;
                }
            });*/

        }
    }
}
