package com.example.semeru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.semeru.R;

public class VoiceActivity extends AppCompatActivity {

    private int total;
    private String dataKebalen;
    private String dataRungkut;

    private TextView textViewTotal;
    private TextView textViewTotalKbl;
    private TextView textViewTotalRkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        dataKebalen = getIntent().getStringExtra("kebalen");
        dataRungkut = getIntent().getStringExtra("rungkut");

        textViewTotal = (TextView) findViewById(R.id.totalKblRkt);
        textViewTotalKbl = (TextView) findViewById(R.id.totalKbl);
        textViewTotalRkt = (TextView) findViewById(R.id.totalRkt);

        total = Integer.parseInt(dataKebalen)+Integer.parseInt(dataRungkut);
        textViewTotal.setText(total+"");
        textViewTotalKbl.setText(dataKebalen);
        textViewTotalRkt.setText(dataRungkut);
    }
}