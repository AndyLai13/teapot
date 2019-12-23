package com.google.developers.teapot.ui;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.google.developers.teapot.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String WORKER_TAG = "notification_worker";

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        final String notificationKey = getString(R.string.pref_notification_key);
        if (preference.getKey().equals(notificationKey)) {
            boolean on = ((SwitchPreference) preference).isChecked();

        }
        return super.onPreferenceTreeClick(preference);
    }
}
