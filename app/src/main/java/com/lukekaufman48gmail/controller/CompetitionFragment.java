package com.lukekaufman48gmail.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.widget.Toast;

public class CompetitionFragment extends Fragment {

    private final String TAG = "LUKER";
    private FMSInterface fms;
    private RecyclerView recyclerView;
    private CompetitionsAdapter cAdapter;
    private ConfigFragment revertToFragment;
    private String selectedCompetition = "";


    public CompetitionFragment(ConfigFragment rTF) {
        this.revertToFragment = rTF;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.competition_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.competition_list);


        fms = new FMSInterface();
        fms.readJSON(getContext());
        cAdapter = new CompetitionsAdapter(fms.getCompetitionList(), getContext());


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(cAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Competition competition = fms.getCompetitionList().get(position);
                selectedCompetition = competition.getCode();
              //revertToFragment.makeToast(competition.getVenue() + " competition in " + competition.getCity()+ " was selected", Toast.LENGTH_LONG);
                loadFragment(revertToFragment);
                //
                //
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

    public String getSelectedCompetition() {
        return selectedCompetition;
    }
}