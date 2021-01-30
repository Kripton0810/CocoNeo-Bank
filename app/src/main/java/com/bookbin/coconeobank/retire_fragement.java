package com.bookbin.coconeobank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link retire_fragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class retire_fragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    retHelper helper;
    FirebaseDatabase database;
    DatabaseReference myRef;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public retire_fragement() {
        // Required empty public constructor
    }

    public static retire_fragement newInstance(String param1, String param2) {
        retire_fragement fragment = new retire_fragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText cage;
    EditText rage;
    EditText eage;
    EditText monthly_exp;
    EditText roi;
    EditText inflation;
    TextView yeargap;
    TextView yeargapafter;
    TextView inflation_adjust;
    TextView anualexp;
    TextView future_value;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retire_fragement, container, false);
        cage = view.findViewById(R.id.cage);
        rage = view.findViewById(R.id.rage);
        eage = view.findViewById(R.id.eage);
        monthly_exp = view.findViewById(R.id.monthlyexp);
        roi = view.findViewById(R.id.roi);
        inflation = view.findViewById(R.id.inflation_rate);
        yeargap = view.findViewById(R.id.yeargap);
        yeargapafter = view.findViewById(R.id.yeargapafter);
        inflation_adjust = view.findViewById(R.id.inflation_adjust);
        anualexp = view.findViewById(R.id.anualexp);
        future_value = view.findViewById(R.id.future_value);
        button = view.findViewById(R.id.submit_for_ret);

        ////TODO: next step
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(cage.getText()))
                {
                    if (!TextUtils.isEmpty(rage.getText()))
                    {
                        if(!TextUtils.isEmpty(eage.getText()))
                        {
                            if(!TextUtils.isEmpty(monthly_exp.getText()))
                            {
                                if(!TextUtils.isEmpty(roi.getText()))
                                {
                                    if(!TextUtils.isEmpty(inflation.getText()))
                                    {
                                        int rem = Integer.parseInt(rage.getText().toString()) - Integer.parseInt(cage.getText().toString());
                                        yeargap.setText(String.valueOf(rem));

                                        int rem1 = Integer.parseInt(eage.getText().toString()) - Integer.parseInt(rage.getText().toString());
                                        yeargapafter.setText(String.valueOf(rem1));
                                        double a = Double.parseDouble(roi.getText().toString()) / 100;
                                        double b = Double.parseDouble(inflation.getText().toString()) / 100;
                                        double inf_adj = (((1 + a) / (1 + b)) - 1) * 100;
                                        inflation_adjust.setText(String.format("%.2f", inf_adj));
                                        double anulxp = 12 * Double.parseDouble(monthly_exp.getText().toString());
                                        anualexp.setText(String.format("%.2f", anulxp));

                                        double amt_afte_inf = anulxp * Math.pow((1 + Double.parseDouble(inflation.getText().toString()) / 100), rem);
                                        future_value.setText(String.format("%.2f", amt_afte_inf));
                                        helper = new retHelper(Integer.parseInt(cage.getText().toString()), Integer.parseInt(rage.getText().toString())
                                                , Integer.parseInt(eage.getText().toString()), rem, rem1, Double.valueOf(roi.getText().toString()), Double.valueOf(inflation.getText().toString())
                                                , inf_adj, anulxp, amt_afte_inf);
                                        database = FirebaseDatabase.getInstance();
                                        myRef = database.getReference().child("retirment");
                                        myRef.push().setValue(helper);
                                        Toast.makeText(getContext(), "This Data has been upload to firebase", Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        inflation.setError("Fill inflation rate");
                                    }
                                }
                                else {
                                    roi.setError("Fill Return of intrest");
                                }
                            }
                            else
                            {
                                monthly_exp.setError("Fill monthly expences");
                            }
                        }
                        else {
                            eage.setError("Fill expected age");
                        }
                    }else {
                        rage.setError("Fill retirment age");
                    }
                }else {
                    cage.setError("Fill current age");
                }

            }
        });

        return view;
    }
}