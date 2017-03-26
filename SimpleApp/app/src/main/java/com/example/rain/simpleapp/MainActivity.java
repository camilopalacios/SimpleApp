package com.example.rain.simpleapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private TextView tv1;
    private TextView tv2;
    private SeekBar sb1;

    private float amount;
    private float tipPerc = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        et1 = (EditText) findViewById(R.id.et1);
        sb1 = (SeekBar) findViewById(R.id.sb1);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sb1.setProgress(0);
                tipPerc = Float.parseFloat(String.valueOf(findViewById(checkedId).getTag()));
                computeTip();
            }
        });

        et1.addTextChangedListener(new addListenerOnTextChange(this, et1));
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb1, int progresValue, boolean fromUser) {
                tv2.setText(String.valueOf(progresValue) + "%");
                tipPerc = progresValue / (float) 100;
                computeTip();
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb1) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar sb1) {
            }
        });
    }

    private void computeTip() {
        if (!et1.getText().toString().equals("")) {
            amount = Float.parseFloat(et1.getText().toString());
            float result = amount * tipPerc;
            tv1.setText(String.valueOf(result));
        }
    }

    private class addListenerOnTextChange implements TextWatcher {
        Context mContext;
        EditText mEdittext;

        private addListenerOnTextChange(Context context, EditText edittextview) {
            super();
            this.mContext = context;
            this.mEdittext = edittextview;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            computeTip();
        }
    }
}
