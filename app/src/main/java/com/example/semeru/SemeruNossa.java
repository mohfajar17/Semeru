package com.example.semeru;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class SemeruNossa implements Parcelable {

    private String tiket;
    private String witel;
    private String workzone;
    private String summary;
    private String ownergroup;
    private String reportedDate;
    private String status;
    private String type;
    private String jenis;
    private String segment;

    public SemeruNossa(String tiket, String witel, String workzone, String summary, String ownergroup, String reportedDate, String status, String type, String jenis, String segment){
        this.tiket = tiket;
        this.witel = witel;
        this.workzone = workzone;
        this.summary = summary;
        this.ownergroup = ownergroup;
        this.reportedDate = reportedDate;
        this.status = status;
        this.type = type;
        this.jenis = jenis;
        this.segment = segment;
    }

    protected SemeruNossa(Parcel in) {
        tiket = in.readString();
        witel = in.readString();
        workzone = in.readString();
        summary = in.readString();
        ownergroup = in.readString();
        reportedDate = in.readString();
        status = in.readString();
        type = in.readString();
        jenis = in.readString();
        segment = in.readString();
    }

    public SemeruNossa (JSONObject jsonObject){
        try {
            this.tiket = jsonObject.getString("tiket");
            this.witel = jsonObject.getString("witel");
            this.workzone = jsonObject.getString("workzone");
            this.summary = jsonObject.getString("summary");
            this.ownergroup = jsonObject.getString("ownergroup");
            this.reportedDate = jsonObject.getString("reported_date");
            this.status = jsonObject.getString("status");
            this.type = jsonObject.getString("type");
            this.jenis = jsonObject.getString("jenis");
            this.segment = jsonObject.getString("segment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tiket);
        dest.writeString(witel);
        dest.writeString(workzone);
        dest.writeString(summary);
        dest.writeString(ownergroup);
        dest.writeString(reportedDate);
        dest.writeString(status);
        dest.writeString(type);
        dest.writeString(jenis);
        dest.writeString(segment);
    }

    public static final Creator<SemeruNossa> CREATOR = new Creator<SemeruNossa>() {
        @Override
        public SemeruNossa createFromParcel(Parcel in) {
            return new SemeruNossa(in);
        }

        @Override
        public SemeruNossa[] newArray(int size) {
            return new SemeruNossa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }

    public String getWitel() {
        return witel;
    }

    public void setWitel(String witel) {
        this.witel = witel;
    }

    public String getWorkzone() {
        return workzone;
    }

    public void setWorkzone(String workzone) {
        this.workzone = workzone;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getOwnergroup() {
        return ownergroup;
    }

    public void setOwnergroup(String ownergroup) {
        this.ownergroup = ownergroup;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }
}
