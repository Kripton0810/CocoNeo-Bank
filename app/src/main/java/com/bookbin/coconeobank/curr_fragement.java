package com.bookbin.coconeobank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bookbin.coconeobank.Retrofit.RetrofitBuilder;
import com.bookbin.coconeobank.Retrofit.RetrofitInterface;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class curr_fragement extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    currhelper helper;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public curr_fragement() {
    }

    public static curr_fragement newInstance(String param1, String param2) {
        curr_fragement fragment = new curr_fragement();
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
    private EditText curr_amt;
    private TextView result;
    private Spinner from;
    private Spinner to;
    private Button calc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curr_fragement, container, false);
        result = view.findViewById(R.id.result);
        from =  view.findViewById(R.id.from_spinner);
        to =  view.findViewById(R.id.to_spinner);
        curr_amt = view.findViewById(R.id.curr_amt);
        calc = view.findViewById(R.id.calculate);
        String[] list = {"USD","INR","EUR","NZD","CAD","HKD","ISK","PHP","DKK",};
        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        from.setAdapter(adapter);
        to.setAdapter(adapter);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(curr_amt.getText())) {
                    RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                    Call<JsonObject> call = retrofitInterface.getExchangeCurrency(from.getSelectedItem().toString());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            JsonObject res = response.body();
                            JsonObject rates = res.getAsJsonObject("conversion_rates");
                            Log.d("response", String.valueOf(response.body()));
                            double curr = Double.valueOf(curr_amt.getText().toString());
                            double mult = Double.valueOf(rates.get(to.getSelectedItem().toString()).toString());
                            double ress = curr * mult;
                            result.setText(String.format("%.2f", ress));
                            helper = new currhelper(to.getSelectedItem().toString(),from.getSelectedItem().toString(),String.format("%.2f", ress),curr_amt.getText().toString());
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference().child("currency");
                            myRef.push().setValue(helper);
                            Toast.makeText(getContext(),"This Data has been upload to firebase",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }
                else
                {
                    curr_amt.setError("Fill Amount");
                }
            }
        });
        return view;
    }
}