package com.lukekaufman48gmail.controller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class CompetitionFragment extends Fragment {

    private final String TAG = "LUKER";
    private FMSInterface fms;
    private RecyclerView recyclerView;
    private CompetitionsAdapter cAdapter;
    private ConfigFragment revertToFragment;
    public Activity mainActivity = getActivity();
    public Competition selectedCompetition = new Competition("","","",0,0,0,null, null);
    private String selectedCompetitionCode = "";


    public CompetitionFragment(ConfigFragment rTF) {
        this.revertToFragment = rTF;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.competition_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.competition_list);

        final Globals globals = (Globals) getActivity().getApplicationContext();


        globals.getFms().readCompetitionJSON(getContext());

        cAdapter = new CompetitionsAdapter(globals.getFms().getCompetitionList(), getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(cAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Competition competition = globals.getFms().getCompetitionList().get(position);

                selectedCompetitionCode = competition.getCode();
                ArrayList<MatchData> qualMatchList = globals.getFms().readMatchJSON(getContext(), selectedCompetitionCode, "qual");

                if(qualMatchList.size()>5) {
                    globals.setSelectedCompetition(competition);
                    globals.getSelectedCompetition().setQualMatches(qualMatchList);
                }
                else{
                   makeTextToast("This Competition does not have qual matches set", Toast.LENGTH_LONG);
                   selectedCompetitionCode = "";
                }

                //revertToFragment.makeToast(competition.getVenue() + " competition in " + competition.getCity()+ " was selected", Toast.LENGTH_LONG);
                loadFragment(revertToFragment);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;

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

    public String getSelectedCompetitionCode() {
        Log.v(TAG, "selectedCompetitionCode (in method): " + selectedCompetition);
        return selectedCompetitionCode;
    }

    public Competition getSelectedCompetition() { return selectedCompetition; }

    public void makeTextToast(String message, int duration){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                message,
                duration);

        toast.show();
    }

}