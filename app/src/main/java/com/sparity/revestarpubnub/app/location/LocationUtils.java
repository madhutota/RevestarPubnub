package com.sparity.revestarpubnub.app.location;

import android.location.Address;
import android.location.Geocoder;

import com.sparity.revestarpubnub.app.activity.BaseActivity;
import com.sparity.revestarpubnub.app.model.AddressLocation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ${VIDYA}
 */

public class LocationUtils {


    public static AddressLocation getAddressLocation(BaseActivity parent) {

        AddressLocation location = null;
        GPSTracker gps = new GPSTracker(parent);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            try {
                Geocoder geocoder = new Geocoder(parent, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0) {
                    String stateName = addresses.get(0).getAdminArea();
                    String cityName = addresses.get(0).getLocality();
                    String CountryName = addresses.get(0).getCountryName();
                    location = new AddressLocation(cityName, stateName, CountryName);
                }
            } catch (IOException e) {
                location = new AddressLocation("Hyderabad", "Telangana", "India");
                e.printStackTrace();
                return location;
            }
        }
        return location;
    }


    public static double getLatitude(double latitude) {
        if (latitude == 0.0) {
            return 17.447412;
        } else
            return latitude;
    }

    public static double getLongitude(double longitude) {
        if (longitude == 0.0) {
            return 78.376230;
        } else
            return longitude;
    }
}
