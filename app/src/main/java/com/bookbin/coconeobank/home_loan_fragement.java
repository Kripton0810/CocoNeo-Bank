package com.bookbin.coconeobank;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class home_loan_fragement extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_loan_fragement() {
        // Required empty public constructor
    }


    public static home_loan_fragement newInstance(String param1, String param2) {
        home_loan_fragement fragment = new home_loan_fragement();
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
    private EditText p;
    private EditText r;
    private EditText t;
    private TextView res;
    homeloanHelper helper;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private Button cal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_loan_fragement, container, false);
        p =view.findViewById(R.id.p);
        r =view.findViewById(R.id.r);
        t =view.findViewById(R.id.t);
        res = view.findViewById(R.id.home_loan_result);
        cal = view.findViewById(R.id.home_loan);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(p.getText()))
                {
                    if(!TextUtils.isEmpty(r.getText()))
                    {
                        if(!TextUtils.isEmpty(t.getText()))
                        {
                            double principal = Double.valueOf(p.getText().toString());
                            double rate = Double.valueOf(r.getText().toString());
                            double time = Double.valueOf(t.getText().toString());
                            double emi = calc(principal,time,rate);
                            res.setText(String.valueOf(Math.round(emi)));
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference().child("Home_Loan");
                            helper = new homeloanHelper(principal,rate,time,emi);
                            myRef.push().setValue(helper);
                            Toast.makeText(getContext(),"This Data has been upload to firebase",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            t.setError("Fill tenure");
                        }
                    }
                    else
                    {
                        r.setError("Fill Rate of intrest");
                    }
                }
                else
                {
                    p.setError("Fill Loan Amount");
                }

            }
        });
        return view;
    }
    private double calc(double p,double t,double r)
    {
        double res=1.0;
        double rate = r/1200;
        double time = t*12;
        res = (double)(p*rate*Math.pow((1+rate),time))/(Math.pow((1+rate),time)-1);
        return res;
    }

}