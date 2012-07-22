package com.phonegap.plugins;

import org.json.JSONArray;

import android.content.Intent;
import android.content.IntentFilter;

import com.phonegap.api.PhonegapActivity;
import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

public class Battery extends Plugin {
    private BatteryReceiver bReceiver;

    public void setContext(PhonegapActivity ctx) {
        super.setContext(ctx);
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        bReceiver = new BatteryReceiver();
        ctx.registerReceiver(bReceiver, batteryLevelFilter);
    }

    public PluginResult execute(String action, JSONArray args, String callinglbackId) {
        int level = bReceiver.getLevel();
        try {
            return new PluginResult(PluginResult.Status.OK, "{\"level\":" + level + "}");
        } catch(Exception e) {
            return new PluginResult(PluginResult.Status.INVALID_ACTION, "error: could not read battery!");
        }
    }
}
