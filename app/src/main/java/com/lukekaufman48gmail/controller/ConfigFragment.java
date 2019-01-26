package com.lukekaufman48gmail.controller;

import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;


public class ConfigFragment extends Fragment {

    private static String[] configContents = {"","","",""};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.config_fragment, container, false);
        return view;




    }

    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView controllerName = view.findViewById(R.id.controllerName_field);
        final TextView year = view.findViewById(R.id.year_field);
        final TextView competition = view.findViewById(R.id.competition_field);
        final TextView password = view.findViewById(R.id.password_field);
        final TextView[] configFields =  {controllerName,year,competition,password};
        Button dismiss = view.findViewById(R.id.dismiss_button);

        for(int i=0; i<configFields.length; i++){
            if(!configContents[i].equals(""))
                configFields[i].setText(configContents[i]);
        }

        dismiss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i=0; i<configFields.length; i++){
                    configContents[i] = configFields[i].getText().toString();
                }
            }
        });


    }






}

