package com.lukekaufman48gmail.controller;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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

        //tn-team number c-color s-status
        TextView tn_A = findViewById(R.id.A_teamNumber);
        ImageView c_A = findViewById(R.id.A_color);
        ImageView s_A = findViewById(R.id.A_status_image);
        TextView tn_B = findViewById(R.id.B_teamNumber);
        ImageView c_B = findViewById(R.id.B_color);
        ImageView s_B = findViewById(R.id.B_status_image);
        TextView tn_C = findViewById(R.id.C_teamNumber);
        ImageView c_C = findViewById(R.id.C_color);
        ImageView s_C = findViewById(R.id.C_status_image);
        TextView tn_D = findViewById(R.id.D_teamNumber);
        ImageView c_D = findViewById(R.id.D_color);
        ImageView s_D = findViewById(R.id.D_status_image);
        TextView tn_E = findViewById(R.id.E_teamNumber);
        ImageView c_E = findViewById(R.id.E_color);
        ImageView s_E = findViewById(R.id.E_status_image);
        TextView tn_F = findViewById(R.id.F_teamNumber);
        ImageView c_F = findViewById(R.id.F_color);
        ImageView s_F = findViewById(R.id.F_status_image);

        //Config Activity

        Button config_button = findViewById(R.id.config_button);
        config_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(new ConfigFragment());
            }
        });



        //Sample Data in array format
        String[] data_arr = {"2468", "t", "t", "1489", "t", "f", "2584", "f", "t", "2568", "f", "f", "5628", "t", "f", "4321", "f", "t"};

        String[][] data_mat = new String[6][3];
        int i = 0;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                data_mat[r][c] = data_arr[i];
                i++;
            }
        }


        //Contributor A
        final ContributorA instance_A = new ContributorA(data_mat[0][0], toBoolean(data_mat[0][1]), toBoolean(data_mat[0][2]), this, tn_A, c_A, s_A);
        instance_A.UpdateDisplay();
        final Button buttonA = findViewById(R.id.A_Xbutton);
        buttonA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_A.ClearDisplay();
            }
        });

        //Contributor B
        final ContributorB instance_B = new ContributorB(data_mat[1][0], toBoolean(data_mat[1][1]), toBoolean(data_mat[1][2]), this, tn_B, c_B, s_B);
        instance_B.UpdateDisplay();
        final Button buttonB = findViewById(R.id.B_Xbutton);
        buttonB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_B.ClearDisplay();
            }
        });

        //Contributor C
        final ContributorC instance_C = new ContributorC(data_mat[2][0], toBoolean(data_mat[2][1]), toBoolean(data_mat[2][2]), this, tn_C, c_C, s_C);
        instance_C.UpdateDisplay();
        final Button buttonC = findViewById(R.id.C_Xbutton);
        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_C.ClearDisplay();
            }
        });

        //Contributor D
        final ContributorD instance_D = new ContributorD(data_mat[3][0], toBoolean(data_mat[3][1]), toBoolean(data_mat[3][2]), this, tn_D, c_D, s_D);
        instance_D.UpdateDisplay();
        final Button buttonD = findViewById(R.id.D_Xbutton);
        buttonD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_D.ClearDisplay();
            }
        });

        //Contributor E
        final ContributorE instance_E = new ContributorE(data_mat[4][0], toBoolean(data_mat[4][1]), toBoolean(data_mat[4][2]), this, tn_E, c_E, s_E);
        instance_E.UpdateDisplay();
        final Button buttonE = findViewById(R.id.E_Xbutton);
        buttonE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_E.ClearDisplay();
            }
        });

        //Contributor F
        final ContributorF instance_F = new ContributorF(data_mat[5][0], toBoolean(data_mat[5][1]), toBoolean(data_mat[5][2]), this, tn_F, c_F, s_F);
        instance_F.UpdateDisplay();final Button buttonF = findViewById(R.id.F_Xbutton);
        buttonF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_F.ClearDisplay();
            }
        });

        //Match Num Field
        final Button uploadbutton = findViewById(R.id.upload_button);
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //final EditText matchnum = findViewById(R.id.matchNum_field);
                //String num = "Match Num: " + matchnum.getText().toString();
                //final Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), num,
                //Snackbar.LENGTH_SHORT);
                // mySnackbar.show();
            }
        });

        Switch ad_switch = findViewById(R.id.advert_switch);
        //ad_switch.setOnClickListener(this);


    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.ContributorLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    public boolean toBoolean(String torf) {
        if (torf.equals("t"))
            return true;
        else if (torf.equals("f"))
            return false;
        else
            return true;
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
