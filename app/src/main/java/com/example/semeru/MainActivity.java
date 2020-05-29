package com.example.semeru;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;

    private ProgressDialog progressDialog;
    private Handler handler = new Handler();
    private Runnable refresh;
    private Intent intent;
    private JSONArray jsonArray;
    private String url;
    private int interval_seconds = 10;
    private int NOTIFICATION_ID = 1;
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 134;

    private FloatingActionButton fabRefresh;

    private TextView textViewNetwork;
    private LinearLayout linearLayoutNetwork;
    private TextView textViewService;
    private LinearLayout linearLayoutService;
    private TextView textViewInfoPenting;
    private LinearLayout linearLayoutInfoPenting;

    private LinearLayout buttonPln;
    private LinearLayout buttonGenset;
    private LinearLayout buttonTransportBB;
    private LinearLayout buttonMetro;
    private LinearLayout buttonBroadBand;
    private LinearLayout buttonUnderspec;
    private LinearLayout buttonGamasFeeder;
    private LinearLayout buttonVoice;
    private LinearLayout buttonTelin;
    private LinearLayout buttonGlobal;
    private LinearLayout buttonBussines;
    private LinearLayout buttonGovernment;
    private LinearLayout buttonEnterprise;
    private LinearLayout buttonWholesale;
    private LinearLayout buttonIndihome;
    private LinearLayout buttonVvip;

    private LinearLayout buttonWifi;
    private LinearLayout buttonNodeAccess;
    private LinearLayout buttonNodeG;
    private LinearLayout buttonNodeM;
    private LinearLayout buttonNodeD;
    private LinearLayout buttonOsase;
    private LinearLayout buttonOcc;
    private LinearLayout buttonBackboneTsel;
    private LinearLayout buttonBackboneTera;

    private TextView textViewPln;
    private TextView textViewGenset;
    private TextView textViewTransport;
    private TextView textViewMetro;
    private TextView textViewWifi;
    private TextView textViewBroadband;
    private TextView textViewIptv;
    private TextView textViewUnderspec;
    private TextView textViewNAG;
    private TextView textViewNAM;
    private TextView textViewNAD;
    private TextView textViewOsase;
    private TextView textViewGamasProactive;
    private TextView textViewGamasManual;
    private TextView textViewVoiceKbl;
    private TextView textViewVoiceRkt;
    private TextView textViewTelkomcel;
    private TextView textViewOcc;

    private TextView textViewTselJatim;
    private TextView textViewTselBalnus;
    private TextView textViewTera;
    private TextView textViewGlobal;
    private TextView textViewBussinesD;
    private TextView textViewBussinesND;
    private TextView textViewGovernmentD;
    private TextView textViewGovernmentND;
    private TextView textViewEnterpriseD;
    private TextView textViewEnterpriseND;
    private TextView textViewWholesaleT;
    private TextView textViewWholesaleO;
    private TextView textViewIndihomeVoice;
    private TextView textViewIndihomeInet;
    private TextView textViewIndihomeIptv;
    private TextView textViewVvip;

    private ViewPager viewPagerPemkot;
    private ViewPager viewPagerCnn;
    private ViewPager viewPagerBmkg;
    private ViewPager viewPagerRadar;
    private MyAdapter adapterPemkots;
    private MyAdapter adapterCnn;
    private MyAdapter adapterBmkg;
    private MyAdapter adapterRadar;
    private Timer timer;
    private int currentPositionPemkot = 0;
    private int currentPositionCnn = 0;
    private int currentPositionBmkg = 0;
    private int currentPositionRadar = 0;

    private ArrayList<InfoPenting> infoBmkg = new ArrayList<>();
    private ArrayList<InfoPenting> infoCnn = new ArrayList<>();
    private ArrayList<InfoPenting> infoPemkot = new ArrayList<>();
    private ArrayList<InfoPenting> infoRadar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        Intent alarmIntent = new Intent(MainActivity.this, AppReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQUEST_CODE, alarmIntent, 0);
        stopAlarmManager();
        startAlarmManager();

        fabRefresh = (FloatingActionButton) findViewById(R.id.fabRefresh);
        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadValue();
                loadBerita();
            }
        });

        buttonPln = (LinearLayout) findViewById(R.id.buttonPln);
        buttonPln.setOnClickListener(this);
        buttonGenset = (LinearLayout) findViewById(R.id.buttonGenset);
        buttonGenset.setOnClickListener(this);
        buttonTransportBB = (LinearLayout) findViewById(R.id.buttonTransportBB);
        buttonTransportBB.setOnClickListener(this);
        buttonMetro = (LinearLayout) findViewById(R.id.buttonMetro);
        buttonMetro.setOnClickListener(this);
        buttonBroadBand = (LinearLayout) findViewById(R.id.buttonBroadBand);
        buttonBroadBand.setOnClickListener(this);
        buttonUnderspec = (LinearLayout) findViewById(R.id.buttonUnderspec);
        buttonUnderspec.setOnClickListener(this);
        buttonGamasFeeder = (LinearLayout) findViewById(R.id.buttonGamasFeeder);
        buttonGamasFeeder.setOnClickListener(this);
        buttonVoice = (LinearLayout) findViewById(R.id.buttonVoice);
        buttonVoice.setOnClickListener(this);
        buttonTelin = (LinearLayout) findViewById(R.id.buttonTelin);
        buttonTelin.setOnClickListener(this);
        buttonGlobal = (LinearLayout) findViewById(R.id.buttonGlobal);
        buttonGlobal.setOnClickListener(this);
        buttonBussines = (LinearLayout) findViewById(R.id.buttonBussines);
        buttonBussines.setOnClickListener(this);
        buttonGovernment = (LinearLayout) findViewById(R.id.buttonGovernment);
        buttonGovernment.setOnClickListener(this);
        buttonEnterprise = (LinearLayout) findViewById(R.id.buttonEnterprise);
        buttonEnterprise.setOnClickListener(this);
        buttonWholesale = (LinearLayout) findViewById(R.id.buttonWholesale);
        buttonWholesale.setOnClickListener(this);
        buttonIndihome = (LinearLayout) findViewById(R.id.buttonIndihome);
        buttonIndihome.setOnClickListener(this);
        buttonVvip = (LinearLayout) findViewById(R.id.buttonVvip);
        buttonVvip.setOnClickListener(this);

        //link to url
        buttonWifi = (LinearLayout) findViewById(R.id.buttonWifi);
        buttonWifi.setOnClickListener(this);
        buttonNodeAccess = (LinearLayout) findViewById(R.id.buttonNodeAccess);
        buttonNodeAccess.setOnClickListener(this);
        buttonNodeG = (LinearLayout) findViewById(R.id.buttonNodeG);
        buttonNodeG.setOnClickListener(this);
        buttonNodeM = (LinearLayout) findViewById(R.id.buttonNodeM);
        buttonNodeM.setOnClickListener(this);
        buttonNodeD = (LinearLayout) findViewById(R.id.buttonNodeD);
        buttonNodeD.setOnClickListener(this);
        buttonOsase = (LinearLayout) findViewById(R.id.buttonOsase);
        buttonOsase.setOnClickListener(this);
        buttonOcc = (LinearLayout) findViewById(R.id.buttonOcc);
        buttonOcc.setOnClickListener(this);
        buttonBackboneTsel = (LinearLayout) findViewById(R.id.buttonBackboneTsel);
        buttonBackboneTsel.setOnClickListener(this);
        buttonBackboneTera = (LinearLayout) findViewById(R.id.buttonBackboneTera);
        buttonBackboneTera.setOnClickListener(this);

        textViewPln = (TextView) findViewById(R.id.textViewPln);
        textViewGenset = (TextView) findViewById(R.id.textViewGenset);
        textViewTransport = (TextView) findViewById(R.id.textViewTransport);
        textViewMetro = (TextView) findViewById(R.id.textViewMetro);
        textViewWifi = (TextView) findViewById(R.id.textViewWifi);
        textViewBroadband = (TextView) findViewById(R.id.textViewBroadband);
        textViewIptv = (TextView) findViewById(R.id.textViewIptv);
        textViewUnderspec = (TextView) findViewById(R.id.textViewUnderspec);
        textViewNAG = (TextView) findViewById(R.id.textViewNAG);
        textViewNAM = (TextView) findViewById(R.id.textViewNAM);
        textViewNAD = (TextView) findViewById(R.id.textViewNAD);
        textViewOsase = (TextView) findViewById(R.id.textViewOsase);
        textViewGamasProactive = (TextView) findViewById(R.id.textViewGamasProactive);
        textViewGamasManual = (TextView) findViewById(R.id.textViewGamasManual);
        textViewVoiceKbl = (TextView) findViewById(R.id.textViewVoiceKbl);
        textViewVoiceRkt = (TextView) findViewById(R.id.textViewVoiceRkt);
        textViewTelkomcel = (TextView) findViewById(R.id.textViewTelkomcel);
        textViewOcc = (TextView) findViewById(R.id.textViewOcc);

        textViewTselJatim = (TextView) findViewById(R.id.textViewTselJatim);
        textViewTselBalnus = (TextView) findViewById(R.id.textViewTselBalnus);
        textViewTera = (TextView) findViewById(R.id.textViewTera);
        textViewGlobal = (TextView) findViewById(R.id.textViewGlobal);
        textViewBussinesD = (TextView) findViewById(R.id.textViewBussinesD);
        textViewBussinesND = (TextView) findViewById(R.id.textViewBussinesND);
        textViewGovernmentD = (TextView) findViewById(R.id.textViewGovernmentD);
        textViewGovernmentND = (TextView) findViewById(R.id.textViewGovernmentND);
        textViewEnterpriseD = (TextView) findViewById(R.id.textViewEnterpriseD);
        textViewEnterpriseND = (TextView) findViewById(R.id.textViewEnterpriseND);
        textViewWholesaleT = (TextView) findViewById(R.id.textViewWholesaleT);
        textViewWholesaleO = (TextView) findViewById(R.id.textViewWholesaleO);
        textViewIndihomeVoice = (TextView) findViewById(R.id.textViewIndihomeVoice);
        textViewIndihomeInet = (TextView) findViewById(R.id.textViewIndihomeInet);
        textViewIndihomeIptv = (TextView) findViewById(R.id.textViewIndihomeIptv);
        textViewVvip = (TextView) findViewById(R.id.textViewVvip);

        linearLayoutNetwork = (LinearLayout) findViewById(R.id.linearLayoutNetwork);
        textViewNetwork = (TextView) findViewById(R.id.textViewNetwork);
        textViewNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = linearLayoutNetwork.getLayoutParams();
                if (linearLayoutNetwork.getLayoutParams().height==ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height=0;
                    linearLayoutNetwork.setLayoutParams(params);
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    linearLayoutNetwork.setLayoutParams(params);
                }
            }
        });

        linearLayoutService = (LinearLayout) findViewById(R.id.linearLayoutService);
        textViewService = (TextView) findViewById(R.id.textViewService);
        textViewService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = linearLayoutService.getLayoutParams();
                if (linearLayoutService.getLayoutParams().height==ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height=0;
                    linearLayoutService.setLayoutParams(params);
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    linearLayoutService.setLayoutParams(params);
                }
            }
        });

        linearLayoutInfoPenting = (LinearLayout) findViewById(R.id.linearLayoutInfoPenting);
        textViewInfoPenting = (TextView) findViewById(R.id.textViewInfoPenting);
        textViewInfoPenting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = linearLayoutInfoPenting.getLayoutParams();
                if (linearLayoutInfoPenting.getLayoutParams().height==ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height=0;
                    linearLayoutInfoPenting.setLayoutParams(params);
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    linearLayoutInfoPenting.setLayoutParams(params);
                }
            }
        });

        loadValue();
        loadBerita();

        refresh = new Runnable() {
            public void run() {
                loadValue();
                handler.postDelayed(refresh, 90000);
            }
        };
        handler.post(refresh);
    }

    public void startAlarmManager() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, interval_seconds);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    public void stopAlarmManager() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
//        notificationManager.cancelAll();
    }

    private void loadValue(){
        progressDialog.show();
        final StringRequest request;
        request = new StringRequest(Request.Method.GET, "http://10.11.4.52/roc_api/semeru/draft.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            jsonArray = new JSONArray(response);

                            textViewGenset.setText(jsonArray.getJSONObject(1).getString("genset"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("genset"))>0) {
                                textViewGenset.setTextColor(getResources().getColor(R.color.colorRed));
                                if(Integer.parseInt(jsonArray.getJSONObject(1).getString("genset"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("genset")))
                                    blinkText(textViewGenset, 1, 0);
                                else blinkText(textViewGenset, 1, 1);
                            }

                            textViewPln.setText(jsonArray.getJSONObject(1).getString("pln"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("pln"))>0) {
                                textViewPln.setTextColor(getResources().getColor(R.color.colorRed));
                                if(Integer.parseInt(jsonArray.getJSONObject(1).getString("pln"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("pln")))
                                    blinkText(textViewPln, 1, 0);
                                else blinkText(textViewPln, 1, 1);
                            }

                            textViewTransport.setText(jsonArray.getJSONObject(1).getString("backbone_transport"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("backbone_transport"))>0) {
                                textViewTransport.setTextColor(getResources().getColor(R.color.colorRed));
                                if(Integer.parseInt(jsonArray.getJSONObject(1).getString("backbone_transport"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("backbone_transport")))
                                    blinkText(textViewTransport, 1, 0);
                                else blinkText(textViewTransport, 1, 1);
                            }

                            textViewMetro.setText(jsonArray.getJSONObject(1).getString("metro"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("metro"))>0) {
                                textViewMetro.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("metro"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("metro")))
                                    blinkText(textViewMetro, 1, 0);
                                else blinkText(textViewMetro, 1, 1);
                            }

                            textViewWifi.setText(jsonArray.getJSONObject(1).getString("wifi_network"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("wifi_network"))>0) {
                                textViewWifi.setTextColor(getResources().getColor(R.color.colorRed));
                                if(Integer.parseInt(jsonArray.getJSONObject(1).getString("wifi_network"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("wifi_network")))
                                    blinkText(textViewWifi, 1, 0);
                                else blinkText(textViewWifi, 1, 1);
                            }

                            textViewBroadband.setText(jsonArray.getJSONObject(1).getString("broadband_network"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("broadband_network"))>0) {
                                textViewBroadband.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("broadband_network"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("broadband_network")))
                                    blinkText(textViewBroadband, 1, 0);
                                else blinkText(textViewBroadband, 1, 1);
                            }

//                            textViewIptv.setText(jsonArray.getJSONObject(1).getString("-"));

                            textViewUnderspec.setText(jsonArray.getJSONObject(1).getString("nossa_unspec"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("nossa_unspec"))>0) {
                                textViewUnderspec.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("nossa_unspec"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("nossa_unspec")))
                                    blinkText(textViewUnderspec, 0, 0);
                                else blinkText(textViewUnderspec, 0, 1);
                            }

                            textViewNAG.setText(jsonArray.getJSONObject(1).getString("node_acces_gpon"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_gpon"))>0){
                                textViewNAG.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_gpon"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("node_acces_gpon")))
                                    blinkText(textViewNAG, 1, 0);
                                else blinkText(textViewNAG, 1, 1);
                            }

                            textViewNAM.setText(jsonArray.getJSONObject(1).getString("node_acces_msan"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_msan"))>0) {
                                textViewNAM.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_msan"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("node_acces_msan")))
                                    blinkText(textViewNAM, 0, 0);
                                else blinkText(textViewNAM, 0, 1);
                            }

                            textViewNAD.setText(jsonArray.getJSONObject(1).getString("node_acces_dslam"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_dslam"))>0) {
                                textViewNAD.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_dslam"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("node_acces_dslam")))
                                    blinkText(textViewNAD, 0, 0);
                                else blinkText(textViewNAD, 0, 1);
                            }

                            textViewOsase.setText(jsonArray.getJSONObject(1).getString("osase_alarm"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("osase_alarm"))>0) {
                                textViewOsase.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("osase_alarm"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("osase_alarm")))
                                    blinkText(textViewOsase, 1, 0);
                                else blinkText(textViewOsase, 1, 1);
                            }

                            textViewGamasProactive.setText(jsonArray.getJSONObject(1).getString("gamas_feeder_proactive"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("gamas_feeder_proactive"))>0) {
                                textViewGamasProactive.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("gamas_feeder_proactive"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("gamas_feeder_proactive")))
                                    blinkText(textViewGamasProactive, 1, 0);
                                else blinkText(textViewGamasProactive, 1, 1);
                            }

                            textViewGamasManual.setText(jsonArray.getJSONObject(1).getString("gamas_feeder_manual_gamas"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("gamas_feeder_manual_gamas"))>0) {
                                textViewGamasManual.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("gamas_feeder_manual_gamas"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("gamas_feeder_manual_gamas")))
                                    blinkText(textViewGamasManual, 1, 0);
                                else blinkText(textViewGamasManual, 1, 1);
                            }

                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_rkt"))<10000) {
                                textViewVoiceRkt.setText("1");
                                textViewVoiceRkt.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_rkt"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("voice_register_rkt")))
                                    blinkText(textViewVoiceRkt, 1, 0);
                                else blinkText(textViewVoiceRkt, 1, 1);
                            }else textViewVoiceRkt.setText("0");

                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_kbl"))<800000) {
                                textViewVoiceKbl.setText("1");
                                textViewVoiceKbl.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_kbl"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("voice_register_kbl")))
                                    blinkText(textViewVoiceKbl, 1, 0);
                                else blinkText(textViewVoiceKbl, 1, 1);
                            }else textViewVoiceKbl.setText("0");

                            textViewTelkomcel.setText(jsonArray.getJSONObject(1).getString("telin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("telin"))>0) {
                                textViewTelkomcel.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("telin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("telin")))
                                    blinkText(textViewTelkomcel, 1, 0);
                                else blinkText(textViewTelkomcel, 1, 1);
                            }

                            textViewOcc.setText(jsonArray.getJSONObject(1).getString("occ_me_olt"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("occ_me_olt"))>0) {
                                textViewOcc.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("occ_me_olt"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("occ_me_olt")))
                                    blinkText(textViewOcc, 1, 0);
                                else blinkText(textViewOcc, 1, 1);
                            }

                            textViewTselJatim.setText(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_jatim"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_jatim"))>0) {
                                textViewTselJatim.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_jatim"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("ip_backbone_tsel_jatim")))
                                    blinkText(textViewTselJatim, 1, 0);
                                else blinkText(textViewTselJatim, 1, 1);
                            }

                            textViewTselBalnus.setText(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_balnus"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_balnus"))>0) {
                                textViewTselBalnus.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_balnus"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("ip_backbone_tsel_balnus")))
                                    blinkText(textViewTselBalnus, 1, 0);
                                else blinkText(textViewTselBalnus, 1, 1);
                            }

                            textViewTera.setText(jsonArray.getJSONObject(1).getString("ip_backbone"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone"))>0) {
                                textViewTera.setTextColor(getResources().getColor(R.color.colorRed));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("ip_backbone")))
                                    blinkText(textViewTera, 1, 0);
                                else blinkText(textViewTera, 1, 1);
                            }

                            textViewGlobal.setText(jsonArray.getJSONObject(1).getString("global"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("global"))>0) {
                                textViewGlobal.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("global"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("global")))
                                    blinkText(textViewGlobal, 0, 0);
                                else blinkText(textViewGlobal, 0, 1);
                            }

                            textViewBussinesD.setText(jsonArray.getJSONObject(1).getString("bussines_datin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("bussines_datin"))>0) {
                                textViewBussinesD.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("bussines_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("bussines_datin")))
                                    blinkText(textViewBussinesD, 0, 0);
                                else blinkText(textViewBussinesD, 0, 1);
                            }

                            textViewBussinesND.setText(jsonArray.getJSONObject(1).getString("bussines_nondatin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("bussines_nondatin"))>0) {
                                textViewBussinesND.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("bussines_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("bussines_nondatin")))
                                    blinkText(textViewBussinesND, 0, 0);
                                else blinkText(textViewBussinesND, 0, 1);
                            }

                            textViewGovernmentD.setText(jsonArray.getJSONObject(1).getString("goverment_datin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("goverment_datin"))>0) {
                                textViewGovernmentD.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("goverment_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("goverment_datin")))
                                    blinkText(textViewGovernmentD, 0, 0);
                                else blinkText(textViewGovernmentD, 0, 1);
                            }

                            textViewGovernmentND.setText(jsonArray.getJSONObject(1).getString("goverment_nondatin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("goverment_nondatin"))>0) {
                                textViewGovernmentND.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("goverment_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("goverment_nondatin")))
                                    blinkText(textViewGovernmentND, 0, 0);
                                else blinkText(textViewGovernmentND, 0, 1);
                            }

                            textViewEnterpriseD.setText(jsonArray.getJSONObject(1).getString("enterprise_datin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("enterprise_datin"))>0) {
                                textViewEnterpriseD.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("enterprise_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("enterprise_datin")))
                                    blinkText(textViewEnterpriseD, 0, 0);
                                else blinkText(textViewEnterpriseD, 0, 1);
                            }

                            textViewEnterpriseND.setText(jsonArray.getJSONObject(1).getString("enterprise_nondatin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("enterprise_nondatin"))>0) {
                                textViewEnterpriseND.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("enterprise_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("enterprise_nondatin")))
                                    blinkText(textViewEnterpriseND, 0, 0);
                                else blinkText(textViewEnterpriseND, 0, 1);
                            }

                            textViewWholesaleT.setText(jsonArray.getJSONObject(1).getString("wholesale_datin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("wholesale_datin"))>0) {
                                textViewWholesaleT.setTextColor(getResources().getColor(R.color.colorPrimary));
                                if (Integer.parseInt(jsonArray.getJSONObject(1).getString("wholesale_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("wholesale_datin")))
                                    blinkText(textViewWholesaleT, 0, 0);
                                else blinkText(textViewWholesaleT, 0, 1);
                            }

                            textViewWholesaleO.setText(jsonArray.getJSONObject(1).getString("wholesale_nondatin"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("wholesale_nondatin"))>0){
                                textViewWholesaleO.setTextColor(getResources().getColor(R.color.colorPrimary));
                            	if (Integer.parseInt(jsonArray.getJSONObject(1).getString("wholesale_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("wholesale_nondatin")))
                                    blinkText(textViewWholesaleO, 0, 0);
                                else blinkText(textViewWholesaleO, 0, 1);
                            }

                            textViewIndihomeVoice.setText(jsonArray.getJSONObject(1).getString("indihome_voice"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_voice"))>0){
                                textViewIndihomeVoice.setTextColor(getResources().getColor(R.color.colorPrimary));
                            	if (Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_voice"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("indihome_voice")))
                                    blinkText(textViewIndihomeVoice, 0, 0);
                                else blinkText(textViewIndihomeVoice, 0, 1);
                            }

                            textViewIndihomeInet.setText(jsonArray.getJSONObject(1).getString("indihome_inet"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_inet"))>0){
                                textViewIndihomeInet.setTextColor(getResources().getColor(R.color.colorPrimary));
                            	if (Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_inet"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("indihome_inet")))
                                    blinkText(textViewIndihomeInet, 0, 0);
                                else blinkText(textViewIndihomeInet, 0, 1);
                            }

                            textViewIndihomeIptv.setText(jsonArray.getJSONObject(1).getString("indihome_iptv"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_iptv"))>0){
                                textViewIndihomeIptv.setTextColor(getResources().getColor(R.color.colorPrimary));
                            	if (Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_iptv"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("indihome_iptv")))
                                    blinkText(textViewIndihomeIptv, 0, 0);
                                else blinkText(textViewIndihomeIptv, 0, 1);
                            }

                            textViewVvip.setText(jsonArray.getJSONObject(1).getString("vvip"));
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("vvip"))>0){
                                textViewVvip.setTextColor(getResources().getColor(R.color.colorPrimary));
                            	if (Integer.parseInt(jsonArray.getJSONObject(1).getString("vvip"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("vvip")))
                                    blinkText(textViewVvip, 0, 0);
                                else blinkText(textViewVvip, 0, 1);
                            }
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Success refresh the data", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Failed refresh the data", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Failed refresh the data, check your network", Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(request);
    }

    public void blinkText(TextView textBlink, int warna, int kat){
        ObjectAnimator textAnim = null;
        if (kat==0){
            if (warna==1)
                textAnim = ObjectAnimator.ofInt(textBlink, "textColor", Color.rgb(252,68,69), Color.TRANSPARENT);
            else textAnim = ObjectAnimator.ofInt(textBlink, "textColor", Color.rgb(251,198,88), Color.TRANSPARENT);
            textAnim.setDuration(1000);
            textAnim.setEvaluator(new ArgbEvaluator());
            textAnim.setRepeatCount(ValueAnimator.INFINITE);
            textAnim.setRepeatMode(ValueAnimator.REVERSE);
            textAnim.start();
        } else {
            if(textAnim != null) {
                textAnim.cancel();
                if (warna==1)
                    textBlink.setTextColor(getResources().getColor(R.color.colorRed));
                else if (warna==2)
                    textBlink.setTextColor(getResources().getColor(R.color.colorPrimary));
                else textBlink.setTextColor(getResources().getColor(R.color.colorGreen));
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPln:
                Intent openPlnActivity = new Intent(this, PlnActivity.class);
                startActivity(openPlnActivity);
                break;
            case R.id.buttonGenset:
                Intent openGensetActivity = new Intent(this, GensetActivity.class);
                startActivity(openGensetActivity);
                break;
            case R.id.buttonTransportBB:
                Intent openTransportActivity = new Intent(this, TransportActivity.class);
                startActivity(openTransportActivity);
                break;
            case R.id.buttonMetro:
                Intent openMetroActivity = new Intent(this, MetroActivity.class);
                startActivity(openMetroActivity);
                break;
            case R.id.buttonBroadBand:
                Intent openBroadbandActivity = new Intent(this, BroadbandActivity.class);
                startActivity(openBroadbandActivity);
                break;
            case R.id.buttonUnderspec:
                Intent openUnderspecActivity = new Intent(this, UnderspecActivity.class);
                startActivity(openUnderspecActivity);
                break;
            case R.id.buttonGamasFeeder:
                Intent openGamasFeederActivity = new Intent(this, GamasFeederActivity.class);
                startActivity(openGamasFeederActivity);
                break;
            case R.id.buttonVoice:
                Intent openVoiceActivity = new Intent(this, VoiceActivity.class);
                try {
                    openVoiceActivity.putExtra("kebalen", jsonArray.getJSONObject(1).getString("voice_register_kbl"));
                    openVoiceActivity.putExtra("rungkut", jsonArray.getJSONObject(1).getString("voice_register_rkt"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(openVoiceActivity);
                break;
            case R.id.buttonOsase:
                Intent openOsaseActivity = new Intent(this, OssaseActivity.class);
                startActivity(openOsaseActivity);
                break;
            case R.id.buttonTelin:
                Intent openTelinActivity = new Intent(this, TelinActivity.class);
                startActivity(openTelinActivity);
                break;
            case R.id.buttonBackboneTera:
                Intent openBackboneTeraActivity = new Intent(this, BackboneTeraActivity.class);
                startActivity(openBackboneTeraActivity);
                break;
            case R.id.buttonGlobal:
                Intent openGlobalActivity = new Intent(this, GlobalActivity.class);
                startActivity(openGlobalActivity);
                break;
            case R.id.buttonBussines:
                Intent openBussinesActivity = new Intent(this, BussinesActivity.class);
                startActivity(openBussinesActivity);
                break;
            case R.id.buttonGovernment:
                Intent openGovernmentActivity = new Intent(this, GovernmentActivity.class);
                startActivity(openGovernmentActivity);
                break;
            case R.id.buttonEnterprise:
                Intent openEnterpriseActivity = new Intent(this, EnterpriseActivity.class);
                startActivity(openEnterpriseActivity);
                break;
            case R.id.buttonWholesale:
                Intent openWholesaleActivity = new Intent(this, WholesaleActivity.class);
                startActivity(openWholesaleActivity);
                break;
            case R.id.buttonIndihome:
                Intent openIndihomeActivity = new Intent(this, IndihomeActivity.class);
                startActivity(openIndihomeActivity);
                break;
            case R.id.buttonVvip:
                Intent openVvipActivity = new Intent(this, VvipActivity.class);
                startActivity(openVvipActivity);
                break;
            case R.id.buttonWifi:
                url = "http://10.37.41.240/cacti/plugins/monitor/monitor.php";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.buttonNodeAccess:
                url = "http://10.11.4.57/symfony/ggn_cacti";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.buttonNodeG:
                url = "http://10.11.4.57/symfony/ggn_cacti-ALL-gpon";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.buttonNodeM:
                url = "http://10.11.4.57/symfony/ggn_cacti-ALL-msan";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.buttonNodeD:
                url = "http://10.11.4.57/symfony/ggn_cacti-ALL-dslam";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.buttonOcc:
                url = "http://10.32.221.186/smart_surveillence/nossa_ibosster_metro_down_.php?tipe=utiliz&regional=REG-5";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.buttonBackboneTsel:
                url = "http://10.11.4.57/symfony/mibt";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1000);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void loadBerita() {
        StringRequest requestInfo;
        requestInfo = new StringRequest(Request.Method.GET, "http://10.11.4.52/roc_api/semeru/news/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArrayInfo = new JSONArray(response);
                            for (int i = 0; i<jsonArrayInfo.length(); i++) {
                                if (jsonArrayInfo.getJSONObject(i).getString("kategori").contains("pemkot"))
                                    infoPemkot.add(new InfoPenting(jsonArrayInfo.getJSONObject(i)));
                                else if (jsonArrayInfo.getJSONObject(i).getString("kategori").contains("cnn"))
                                    infoCnn.add(new InfoPenting(jsonArrayInfo.getJSONObject(i)));
                                else if (jsonArrayInfo.getJSONObject(i).getString("kategori").contains("bmkg"))
                                    infoBmkg.add(new InfoPenting(jsonArrayInfo.getJSONObject(i)));
                                else if (jsonArrayInfo.getJSONObject(i).getString("kategori").contains("rss"))
                                    infoRadar.add(new InfoPenting(jsonArrayInfo.getJSONObject(i)));
                            }

                            viewPagerPemkot = (ViewPager) findViewById(R.id.viewPagerPemkot);
                            viewPagerCnn = (ViewPager) findViewById(R.id.viewPagerBerita);
                            viewPagerBmkg = (ViewPager) findViewById(R.id.viewPagerBmkg);
                            viewPagerRadar = (ViewPager) findViewById(R.id.viewPagerRadar);

                            adapterPemkots = new MyAdapter(MainActivity.this, infoPemkot, 1);
                            adapterCnn = new MyAdapter(MainActivity.this, infoCnn, 0);
                            adapterBmkg = new MyAdapter(MainActivity.this, infoBmkg, 2);
                            adapterRadar = new MyAdapter(MainActivity.this, infoRadar, 0);

                            viewPagerPemkot.setAdapter(adapterPemkots);
                            viewPagerCnn.setAdapter(adapterCnn);
                            viewPagerBmkg.setAdapter(adapterBmkg);
                            viewPagerRadar.setAdapter(adapterRadar);

                            slideShow();
                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(this).add(requestInfo);
    }

    private void slideShow(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPositionPemkot == infoPemkot.size()-1)
                    currentPositionPemkot = 0;
                viewPagerPemkot.setCurrentItem(currentPositionPemkot++, true);

                if (currentPositionCnn == infoCnn.size()-1)
                    currentPositionCnn = 0;
                viewPagerCnn.setCurrentItem(currentPositionCnn++, true);

                if (currentPositionBmkg == infoBmkg.size()-1)
                    currentPositionBmkg = 0;
                viewPagerBmkg.setCurrentItem(currentPositionBmkg++, true);

                if (currentPositionRadar == infoRadar.size()-1)
                    currentPositionRadar = 0;
                viewPagerRadar.setCurrentItem(currentPositionRadar++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 500, 5000);
    }

    public class MyAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private ArrayList<InfoPenting> beritaPentings;
        private int kategori;

        public MyAdapter(Context context, ArrayList<InfoPenting> beritaPentings, int kategori){
            this.context = context;
            this.beritaPentings = beritaPentings;
            this.kategori = kategori;
        }

        @Override
        public int getCount() {
            return beritaPentings.size()-1;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view==(RelativeLayout)object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.layout_recycler, container, false);
            ImageView imageViewInfoPenting = (ImageView) itemView.findViewById(R.id.imageViewInfoPenting);
            TextView textViewKeteranganInfo = (TextView) itemView.findViewById(R.id.textViewKeteranganInfo);

            if (kategori==1)
                Picasso.get().load("https://www.surabaya.go.id"+beritaPentings.get(position).getImg()).into(imageViewInfoPenting);
            else if (kategori==2)
                imageViewInfoPenting.setImageResource(R.drawable.mountains_bg);
            else Picasso.get().load(""+beritaPentings.get(position).getImg()).into(imageViewInfoPenting);
            textViewKeteranganInfo.setText(beritaPentings.get(position).getKeterangan());
            imageViewInfoPenting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url;
                    if (kategori==1)
                        url = "https://www.surabaya.go.id"+beritaPentings.get(position).getHref();
                    else url = ""+beritaPentings.get(position).getHref();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((RelativeLayout) object);
        }
    }
}