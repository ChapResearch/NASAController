package com.lukekaufman48gmail.controller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tn_A = view.findViewById(R.id.A_teamNumber);
        ImageView c_A = view.findViewById(R.id.A_color);
        ImageView s_A = view.findViewById(R.id.A_status_image);
        TextView tn_B = view.findViewById(R.id.B_teamNumber);
        ImageView c_B = view.findViewById(R.id.B_color);
        ImageView s_B = view.findViewById(R.id.B_status_image);
        TextView tn_C = view.findViewById(R.id.C_teamNumber);
        ImageView c_C = view.findViewById(R.id.C_color);
        ImageView s_C = view.findViewById(R.id.C_status_image);
        TextView tn_D = view.findViewById(R.id.D_teamNumber);
        ImageView c_D = view.findViewById(R.id.D_color);
        ImageView s_D = view.findViewById(R.id.D_status_image);
        TextView tn_E = view.findViewById(R.id.E_teamNumber);
        ImageView c_E = view.findViewById(R.id.E_color);
        ImageView s_E = view.findViewById(R.id.E_status_image);
        TextView tn_F = view.findViewById(R.id.F_teamNumber);
        ImageView c_F = view.findViewById(R.id.F_color);
        ImageView s_F = view.findViewById(R.id.F_status_image);













        //String[] data_arr = {"2468", "t", "t", "1489", "t", "f", "2584", "f", "t", "2568", "f", "f", "5628", "t", "f", "4321", "f", "t"};
        /*String[][] data_mat = new String[6][3];
        int i = 0;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                data_mat[r][c] = data_arr[i];
                i++;
            }
        }*/
        //Contributor A
        /*final ContributorA instance_A = new ContributorA(data_mat[0][0], toBoolean(data_mat[0][1]), toBoolean(data_mat[0][2]), this, tn_A, c_A, s_A);
        instance_A.UpdateDisplay();
        final Button buttonA = view.findViewById(R.id.A_Xbutton);
        buttonA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_A.ClearDisplay();
            }
        });
        //Contributor B
        final ContributorB instance_B = new ContributorB(data_mat[1][0], toBoolean(data_mat[1][1]), toBoolean(data_mat[1][2]), this, tn_B, c_B, s_B);
        instance_B.UpdateDisplay();
        final Button buttonB = view.findViewById(R.id.B_Xbutton);
        buttonB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_B.ClearDisplay();
            }
        });
        //Contributor C
        final ContributorC instance_C = new ContributorC(data_mat[2][0], toBoolean(data_mat[2][1]), toBoolean(data_mat[2][2]), this, tn_C, c_C, s_C);
        instance_C.UpdateDisplay();
        final Button buttonC = view.findViewById(R.id.C_Xbutton);
        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_C.ClearDisplay();
            }
        });
        //Contributor D
        final ContributorD instance_D = new ContributorD(data_mat[3][0], toBoolean(data_mat[3][1]), toBoolean(data_mat[3][2]), , tn_D, c_D, s_D);
        instance_D.UpdateDisplay();
        final Button buttonD = view.findViewById(R.id.D_Xbutton);
        buttonD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_D.ClearDisplay();
            }
        });
        //Contributor E
        final ContributorE instance_E = new ContributorE(data_mat[4][0], toBoolean(data_mat[4][1]), toBoolean(data_mat[4][2]), this, tn_E, c_E, s_E);
        instance_E.UpdateDisplay();
        final Button buttonE = view.findViewById(R.id.E_Xbutton);
        buttonE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_E.ClearDisplay();
            }
        });
        //Contributor F
        final ContributorF instance_F = new ContributorF(data_mat[5][0], toBoolean(data_mat[5][1]), toBoolean(data_mat[5][2]), 4, tn_F, c_F, s_F);
        instance_F.UpdateDisplay();final Button buttonF = view.findViewById(R.id.F_Xbutton);
        buttonF.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                instance_F.ClearDisplay();
            }
        });
        //Match Num Field
        final Button uploadbutton = view.findViewById(R.id.upload_button);
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //final EditText matchnum = findViewById(R.id.matchNum_field);
                //String num = "Match Num: " + matchnum.getText().toString();
                //final Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), num,
                //Snackbar.LENGTH_SHORT);
                // mySnackbar.show();
            }
        });*/
    }



    /*public boolean toBoolean(String torf) {
        if (torf.equals("t"))
            return true;
        else if (torf.equals("f"))
            return false;
        else
            return true;
    }*/
}
