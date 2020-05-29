package com.example.semeru;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoPenting implements Parcelable {

    private String keterangan;
    private String img;
    private String href;
    private String kategori;

    public InfoPenting(String keterangan, String img, String href, String kategori){
        this.keterangan = keterangan;
        this.img = img;
        this.href = href;
        this.kategori = kategori;
    }

    protected InfoPenting(Parcel in) {
        keterangan = in.readString();
        img = in.readString();
        href = in.readString();
        kategori = in.readString();
    }

    public InfoPenting (JSONObject jsonObject){
        try {
            this.keterangan = jsonObject.getString("keterangan");
            this.img = jsonObject.getString("img");
            this.href = jsonObject.getString("href");
            this.kategori = jsonObject.getString("kategori");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<InfoPenting> CREATOR = new Creator<InfoPenting>() {
        @Override
        public InfoPenting createFromParcel(Parcel in) {
            return new InfoPenting(in);
        }

        @Override
        public InfoPenting[] newArray(int size) {
            return new InfoPenting[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(keterangan);
        dest.writeString(img);
        dest.writeString(href);
        dest.writeString(kategori);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getImg() {
        return img;
    }

    public String getHref() {
        return href;
    }

    public String getKategori() {
        return kategori;
    }
}
