package com.cloudskol.moviedroid.settings;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.cloudskol.moviedroid.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class MovieSettingsActivity extends AppCompatPreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_general);
        bindPreferenceValue(findPreference(getString(R.string.pref_sort_by_key)));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String prefValue = value.toString();
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference)preference;
            final int prefIndex = listPreference.findIndexOfValue(prefValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }

        return true;
    }

    private void bindPreferenceValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);

        final String preferenceSortBy = PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), "");
        onPreferenceChange(preference, preferenceSortBy);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
