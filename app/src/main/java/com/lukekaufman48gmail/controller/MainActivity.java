package com.lukekaufman48gmail.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableRow;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.Switch;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.AdvertisingSet;
import android.bluetooth.le.AdvertisingSetCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.bluetooth.le.AdvertiseSettings;
//import java.nio.charset.


import android.os.ParcelUuid;
import java.util.*;


public class MainActivity extends AppCompatActivity {

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
        ImageView s_F = findViewById(R.id.E_status_image);




        //Sample Data in array format
        String[] data_arr={"2468","t","t","1489","t","f","2584","f","t","2568","f","f","5628","t","f","4321","f","t"};

        String[][] data_mat = new String[6][3];
        int i=0;
        for(int r=0;r<6;r++) {
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
        instance_F.UpdateDisplay();
        final Button buttonF = findViewById(R.id.F_Xbutton);
        buttonF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_F.ClearDisplay();
            }
        });

        //Match Num Field
        final Button upbutton = findViewById(R.id.upload_button);
        upbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText matchnum = findViewById(R.id.matchNum_field);
                String num = "Match Num: " + matchnum.getText().toString();
                final Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), num,
                        Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        });

        Switch ad_switch = findViewById(R.id.advert_switch);
        ad_switch.setOnClickListener(new View.OnClickListener() {
            BluetoothLeAdvertiser advertiser = BluetoothAdapter.getDefaultAdapter().getBluetoothLeAdvertiser();
            AdvertiseSettings settings = new AdvertiseSettings.Builder()
                    .setAdvertiseMode( AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY )
                    .setTxPowerLevel( AdvertiseSettings.ADVERTISE_TX_POWER_HIGH )
                    .setConnectable( false )
                    .build();

            ParcelUuid pUuid = new ParcelUuid( UUID.fromString( getString( R.string.ble_uuid ) ) );

            AdvertiseData data = new AdvertiseData.Builder()
                    .setIncludeDeviceName( true )
                    .addServiceUuid( pUuid )
                    .addServiceData( pUuid, "Data".getBytes( Charset.forName( "UTF-8" ) ) )
                    .build();
            }
        });
    }

    public boolean toBoolean(String torf){
        if(torf.equals("t"))
            return true;
        else if(torf.equals("f"))
            return false;
        else
            return true;
    }
}
