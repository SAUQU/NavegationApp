package com.example.segundoauqui.navegationapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class EMI_Calculator extends Fragment {



    EditText etLoanAmount;
    EditText etDuration;
    Button btnCalculate;
    TextView tvMonthlyResults;
    TextView tvYearlyResults;
    TextView tvMiddle1;
    SeekBar seekBarID;
    TextView tvYearlyResult;
    double rate;
    int year;
    double amount;
    double emi;
    double emiY;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        getActivity().setTitle("EMI Calculator");


        View view = inflater.inflate(R.layout.fragment_emi__calculator, container, false);


        etLoanAmount = (EditText) view.findViewById(R.id.etLoanAmount);

        etDuration = (EditText) view.findViewById(R.id.etDuration);


        tvYearlyResult = (TextView) view.findViewById(R.id.tvYearlyResult);


        tvMonthlyResults = (TextView) view.findViewById(R.id.tvMonthlyResult);

        tvYearlyResults = (TextView) view.findViewById(R.id.tvYearlyResult);

        seekBarID = (SeekBar) view.findViewById(R.id.seekBarID);

        tvMiddle1 = (TextView) view.findViewById(R.id.tvMiddle1);

        btnCalculate = (Button) view.findViewById(R.id.btnCalculate);



        seekBarID.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double progress = (double) i;
                String val1 = getResources().getString(R.string.seek_bar_value);

                rate = progress;


                tvMiddle1.setText(val1 + progress);


            }




            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }



            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (etLoanAmount.getText().toString().isEmpty() && etDuration.getText().toString().isEmpty() && seekBarID.getProgress() == 0) {
                    Toast.makeText(getActivity(), "Missing Values", Toast.LENGTH_LONG).show();


                } else {

                    year = Integer.parseInt(etDuration.getText().toString());

                    amount = Double.parseDouble(etLoanAmount.getText().toString());

                    rate = rate / (12 * 100);
                    year = year * 12;

                    emi = (amount * rate * Math.pow(1 + rate, year)) / (Math.pow(1 + rate, year) - 1);


                    emiY = (emi * year);

                    tvYearlyResult.setText("$ " + emiY + "");


                    tvMonthlyResults.setText("$ " + emi + "");

                    seekBarID.setProgress(0);
                    etLoanAmount.setText("");
                    etDuration.setText("");
                }




            }
        });

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savedT", tvMonthlyResults.getText().toString());
        outState.putString("savedYear", tvYearlyResult.getText().toString());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            tvMonthlyResults.setText(savedInstanceState.getString("savedT"));
            tvYearlyResult.setText(savedInstanceState.getString("savedYear"));


        }
    }





    public interface OnFragmentInteractionListener {
    }
}
