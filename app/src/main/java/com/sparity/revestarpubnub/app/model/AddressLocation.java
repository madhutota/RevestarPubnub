package com.sparity.revestarpubnub.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Madhu
 */

public class AddressLocation implements Parcelable {
    String City;
    String State;
    String Country;

    public AddressLocation(String city, String state, String country) {
        City = city;
        State = state;
        Country = country;
    }

    protected AddressLocation(Parcel in) {
        City = in.readString();
        State = in.readString();
        Country = in.readString();
    }

    public static final Creator<AddressLocation> CREATOR = new Creator<AddressLocation>() {
        @Override
        public AddressLocation createFromParcel(Parcel in) {
            return new AddressLocation(in);
        }

        @Override
        public AddressLocation[] newArray(int size) {
            return new AddressLocation[size];
        }
    };

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(City);
        dest.writeString(State);
        dest.writeString(Country);
    }
}