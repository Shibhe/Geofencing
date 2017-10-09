package com.example.josephsibiya.geoalert.Configuration;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by reversidesoftwaresolutions on 10/9/17.
 */

public class GetMacAddress {

    private Context context;

    public GetMacAddress(Context context) {
        this.context = context;
    }

    public String getMacAddress() {
        WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();
        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }
        return macAddress;
    }
}
