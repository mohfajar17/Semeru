package com.example.semeru;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppReceiver extends BroadcastReceiver {

    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 134;

    //set interval notifikasi 60 detik
    private int interval_seconds = 30;
    private int net=0;
    private NotificationManager alarmNotificationManager;
    private NotificationManager alarmNotificationNetwork;
    String NOTIFICATION_CHANNEL_ID = "rasupe_channel_id";
    String NOTIFICATION_CHANNEL_NAME = "rasupe channel";
    private int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, AppReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent, 0);

        //set waktu sekarang berdasarkan interval
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, interval_seconds);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //set alarm manager dengan memasuk kan waktu yang telah dikonversi menjadi milliseconds
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else if (android.os.Build.VERSION.SDK_INT >= 19) {
            manager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        }

        final StringRequest request;
        request = new StringRequest(Request.Method.GET, "http://10.11.4.52/roc_api/semeru/draft.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(Integer.parseInt(jsonArray.getJSONObject(1).getString("backbone_transport"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("backbone_transport")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("metro"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("metro")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("wifi_network"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("wifi_network")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("broadband_network"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("broadband_network")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("nossa_unspec"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("nossa_unspec")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_gpon"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("node_acces_gpon")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_msan"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("node_acces_msan")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("node_acces_dslam"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("node_acces_dslam")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("osase_alarm"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("osase_alarm")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("gamas_feeder_proactive"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("gamas_feeder_proactive")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("gamas_feeder_manual_gamas"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("gamas_feeder_manual_gamas")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("telin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("telin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("occ_me_olt"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("occ_me_olt")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_jatim"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("ip_backbone_tsel_jatim")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone_tsel_balnus"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("ip_backbone_tsel_balnus")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("ip_backbone"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("ip_backbone")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("global"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("global")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("bussines_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("bussines_datin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("bussines_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("bussines_nondatin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("goverment_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("goverment_datin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("goverment_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("goverment_nondatin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("enterprise_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("enterprise_datin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("enterprise_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("enterprise_nondatin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("wholesale_datin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("wholesale_datin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("wholesale_nondatin"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("wholesale_nondatin")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_voice"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("indihome_voice")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_inet"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("indihome_inet")) ||
                                    Integer.parseInt(jsonArray.getJSONObject(1).getString("indihome_iptv"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("indihome_iptv")))
                                sendNotification(context);
                            else if (Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_rkt"))<10000 && Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_rkt"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("voice_register_rkt")))
                                sendNotification(context);
                            else if (Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_kbl"))<800000 && Integer.parseInt(jsonArray.getJSONObject(1).getString("voice_register_kbl"))>Integer.parseInt(jsonArray.getJSONObject(0).getString("voice_register_kbl")))
                                sendNotification(context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        Volley.newRequestQueue(context).add(request);
    }

    //handle notification
    private void sendNotification(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String datetimex = sdf.format(new Date());
        String notif_title = "SEMERU Alarm";
        String notif_content = datetimex;
        alarmNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent newIntent = new Intent(context,MainActivity.class);
        newIntent.putExtra("notifkey", "notifvalue");
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //cek jika OS android Oreo atau lebih baru, kalau tidak di set maka notifikasi tidak akan muncul di OS tersebut
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            alarmNotificationManager.createNotificationChannel(mChannel);
        }

        //Buat notification
        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        alamNotificationBuilder.setContentTitle(notif_title);
        alamNotificationBuilder.setSmallIcon(R.drawable.semeru_logo_color);
        alamNotificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        alamNotificationBuilder.setContentText(notif_content);
        alamNotificationBuilder.setAutoCancel(true);
        alamNotificationBuilder.setContentIntent(contentIntent);

        //Tampilkan notifikasi
        alarmNotificationManager.notify(NOTIFICATION_ID, alamNotificationBuilder.build());
    }

    //handle notification
    private void sendNetworkAvailable(Context context) {
        String notif_title = "";
        String notif_content = "";
        if (net>0){
            notif_title = "ATTENTION!";
            notif_content = "Network is available";
        } else if (net==0) {
            notif_title = "WARNING!";
            notif_content = "Network not available";
        }
        alarmNotificationNetwork = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.putExtra("notifkey", "notifvalue");
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //cek jika OS android Oreo atau lebih baru, kalau tidak di set maka notifikasi tidak akan muncul di OS tersebut
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            alarmNotificationNetwork.createNotificationChannel(mChannel);
        }

        //Buat notification
        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        alamNotificationBuilder.setContentTitle(notif_title);
        alamNotificationBuilder.setSmallIcon(R.drawable.semeru_logo_color);
        alamNotificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        alamNotificationBuilder.setContentText(notif_content);
        alamNotificationBuilder.setAutoCancel(true);
        alamNotificationBuilder.setContentIntent(contentIntent);

        //Tampilkan notifikasi
        alarmNotificationNetwork.notify(NOTIFICATION_ID, alamNotificationBuilder.build());
    }
}