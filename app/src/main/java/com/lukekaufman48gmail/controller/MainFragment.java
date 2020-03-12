package com.lukekaufman48gmail.controller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private final String BUNDLE= "BUNDLED";

    private final String A_CN_KEY = "ACN";
    private final String A_TN_KEY = "ATN";
    private final String A_C_KEY = "AC";
    private final String A_DS_KEY = "ADS";
    private final String A_CS_KEY = "ACS";

    private final String B_CN_KEY = "BCN";
    private final String B_TN_KEY = "BTN";
    private final String B_C_KEY = "BC";
    private final String B_DS_KEY = "BDS";
    private final String B_CS_KEY = "BCS";

    private final String C_CN_KEY = "CCN";
    private final String C_TN_KEY = "CTN";
    private final String C_C_KEY = "CC";
    private final String C_DS_KEY = "CDS";
    private final String C_CS_KEY = "CCS";

    private final String D_CN_KEY = "DCN";
    private final String D_TN_KEY = "DTN";
    private final String D_C_KEY = "DC";
    private final String D_DS_KEY = "DDS";
    private final String D_CS_KEY = "DCS";

    private final String E_CN_KEY = "ECN";
    private final String E_TN_KEY = "ETN";
    private final String E_C_KEY = "EC";
    private final String E_DS_KEY = "EDS";
    private final String E_CS_KEY = "ECS";

    private final String F_CN_KEY = "FCN";
    private final String F_TN_KEY = "FTN";
    private final String F_C_KEY = "FC";
    private final String F_DS_KEY = "FDS";
    private final String F_CS_KEY = "FCS";
//
    private Bundle savedState = null;
    private TextView MT;

    private TextView cnA;
    private TextView tnA;
    private ImageView cA;
    private RadioButton dsA;
    private TableRow csA;
    private int cAnum;

    private TextView cnB;
    private TextView tnB;
    private ImageView cB;
    private RadioButton dsB;
    private TableRow csB;
    private int cBnum;

    private TextView cnC;
    private TextView tnC;
    private ImageView cC;
    private RadioButton dsC;
    private TableRow csC;
    private int cCnum;

    private TextView cnD;
    private TextView tnD;
    private ImageView cD;
    private RadioButton dsD;
    private TableRow csD;
    private int cDnum;

    private TextView cnE;
    private TextView tnE;
    private ImageView cE;
    private RadioButton dsE;
    private TableRow csE;
    private int cEnum;

    private TextView cnF;
    private TextView tnF;
    private ImageView cF;
    private RadioButton dsF;
    private TableRow csF;
    private int cFnum;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        Globals globals = (Globals) getActivity().getApplicationContext();
        globals.setMainFragmentData(new MainFragmentData());

        //used to refresh contributor background when switching to config and back to main while connected
        for(int slot=0;slot<6;slot++)
            //globals.getBleCallbacks().NASA_slotChange(slot, globals.getMainFragmentData().getConnectionStatus(slot));

        cnA = view.findViewById(R.id.A_name);
        // ****find text change method for textView OR change textview to editText with editing disabled cn.addOnTextChanged ****
        tnA = view.findViewById(R.id.A_teamNumber);
        cA = view.findViewById(R.id.A_color);
        csA = view.findViewById(R.id.Contributor_A_display);
        cAnum = 0;

        dsA = view.findViewById(R.id.A_status);
        dsA.setClickable(false);

        cnB = view.findViewById(R.id.B_name);
        tnB = view.findViewById(R.id.B_teamNumber);
        cB = view.findViewById(R.id.B_color);
        csB = view.findViewById(R.id.Contributor_B_display);
        cBnum = 0;

        dsB = view.findViewById(R.id.B_status);
        dsB.setClickable(false);

        cnC = view.findViewById(R.id.C_name);
        tnC = view.findViewById(R.id.C_teamNumber);
        cC = view.findViewById(R.id.C_color);
        csC = view.findViewById(R.id.Contributor_C_display);
        cCnum = 0;

        dsC = view.findViewById(R.id.C_status);
        dsC.setClickable(false);

        cnD = view.findViewById(R.id.D_name);
        tnD = view.findViewById(R.id.D_teamNumber);
        cD = view.findViewById(R.id.D_color);
        csD = view.findViewById(R.id.Contributor_D_display);
        cDnum = 0;

        dsD = view.findViewById(R.id.D_status);
        dsD.setClickable(false);

        cnE = view.findViewById(R.id.E_name);
        tnE = view.findViewById(R.id.E_teamNumber);
        cE = view.findViewById(R.id.E_color);
        csE= view.findViewById(R.id.Contributor_E_display);
        cEnum = 0;

        dsE = view.findViewById(R.id.E_status);
        dsE.setClickable(false);

        cnF = view.findViewById(R.id.F_name);
        tnF = view.findViewById(R.id.F_teamNumber);
        cF = view.findViewById(R.id.F_color);
        csF = view.findViewById(R.id.Contributor_F_display);
        cFnum = 0;

        dsF = view.findViewById(R.id.F_status);
        dsF.setClickable(false);

        if (savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle(BUNDLE);
        }
        if (savedState != null) {

            cnA.setText(savedState.getCharSequence(A_CN_KEY));
            tnA.setText(savedState.getCharSequence(A_TN_KEY));
            cA.setBackgroundColor(savedState.getInt(A_C_KEY));

            cnB.setText(savedState.getCharSequence(B_CN_KEY));
            tnB.setText(savedState.getCharSequence(B_TN_KEY));
            cB.setBackgroundColor(savedState.getInt(B_C_KEY));

            cnC.setText(savedState.getCharSequence(C_CN_KEY));
            tnC.setText(savedState.getCharSequence(C_TN_KEY));
            cC.setBackgroundColor(savedState.getInt(C_C_KEY));

            cnD.setText(savedState.getCharSequence(D_CN_KEY));
            tnD.setText(savedState.getCharSequence(D_TN_KEY));
            cD.setBackgroundColor(savedState.getInt(D_C_KEY));

            cnE.setText(savedState.getCharSequence(E_CN_KEY));
            tnE.setText(savedState.getCharSequence(E_TN_KEY));
            cE.setBackgroundColor(savedState.getInt(E_C_KEY));

            cnF.setText(savedState.getCharSequence(F_CN_KEY));
            tnF.setText(savedState.getCharSequence(F_TN_KEY));
            cF.setBackgroundColor(savedState.getInt(F_C_KEY));

        }
        savedState = null;
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("LUKER", "On create for Main Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState();
        cnA = null;
        tnA = null;
        cA = null;
        dsA = null;

        cnB = null;
        tnB = null;
        cB = null;
        dsB = null;

        cnC = null;
        tnC = null;
        cC = null;
        dsC = null;

        cnD = null;
        tnD = null;
        cD = null;
        dsD = null;

        cnE = null;
        tnE = null;
        cE = null;
        dsE = null;

        cnF = null;
        tnF = null;
        cF = null;
        dsF = null;

        cAnum = 0;
        cBnum = 0;
        cCnum = 0;
        cDnum = 0;
        cEnum = 0;
        cFnum = 0;
    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();

        /*ColorDrawable coA = (ColorDrawable) cA.getBackground();
        ColorDrawable coB = (ColorDrawable) cB.getBackground();
        ColorDrawable coC = (ColorDrawable) cC.getBackground();
        ColorDrawable coD = (ColorDrawable) cD.getBackground();
        ColorDrawable coE = (ColorDrawable) cE.getBackground();
        ColorDrawable coF = (ColorDrawable) cF.getBackground();*/

        state.putCharSequence(A_CN_KEY, cnA.getText());
        state.putCharSequence(A_TN_KEY, tnA.getText());
        //state.putInt(A_C_KEY, coA.getColor());
        state.putBoolean(A_DS_KEY, dsA.isActivated());

        state.putCharSequence(B_CN_KEY, cnB.getText());
        state.putCharSequence(B_TN_KEY, tnB.getText());
        //state.putInt(B_C_KEY, coB.getColor());
        state.putBoolean(B_DS_KEY, dsB.isActivated());

        state.putCharSequence(C_CN_KEY, cnC.getText());
        state.putCharSequence(C_TN_KEY, tnC.getText());
        //state.putInt(C_C_KEY,coC.getColor());
        state.putBoolean(C_DS_KEY, dsC.isActivated());

        state.putCharSequence(D_CN_KEY, cnD.getText());
        state.putCharSequence(D_TN_KEY, tnD.getText());
        //state.putInt(D_C_KEY, coD.getColor());
        state.putBoolean(D_DS_KEY, dsD.isActivated());

        state.putCharSequence(E_CN_KEY, cnE.getText());
        state.putCharSequence(E_TN_KEY, tnE.getText());
        //state.putInt(E_C_KEY, coE.getColor());
        state.putBoolean(E_DS_KEY, dsE.isActivated());

        state.putCharSequence(F_CN_KEY, cnF.getText());
        state.putCharSequence(F_TN_KEY, tnF.getText());
        //state.putInt(F_C_KEY, coF.getColor());
        state.putBoolean(F_DS_KEY, dsF.isActivated());

        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* If onDestroyView() is called first, we can use the previously savedState but we can't call saveState() anymore */
        /* If onSaveInstanceState() is called first, we don't have savedState, so we need to call saveState() */
        /* => (?:) operator inevitable! */
        outState.putBundle(BUNDLE, (savedState != null) ? savedState : saveState());
    }



}