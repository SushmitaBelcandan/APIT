package com.example.shushmita.apit.reference;

import android.os.Parcel;
import android.os.Parcelable;

public class GrainsParcelable implements Parcelable {
    private String verity;


    protected GrainsParcelable(Parcel in) {
        verity  = in.readString();
    }

    public GrainsParcelable(String grainsVerity) {
        this.verity = grainsVerity;
    }


    public static final Creator<GrainsParcelable> CREATOR = new Creator<GrainsParcelable>() {
        @Override
        public GrainsParcelable createFromParcel(Parcel in) {
            return new GrainsParcelable(in);
        }

        @Override
        public GrainsParcelable[] newArray(int size) {
            return new GrainsParcelable[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }


    public String getVerity() {
        return verity;
    }

    public void setVerity(String verity) {
        this.verity = verity;
    }


    public static Creator<GrainsParcelable> getCREATOR() {
        return CREATOR;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(verity);

    }
}