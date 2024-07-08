/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

* See the License for the specific language governing permissions and
 * limitations under the License.
 * limitations under the License.
 */
 */

package org.lineageos.settings.doze;
package org.lineageos.settings.doze;


import android.app.Activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler;
import android.widget.Switch;
import android.widget.CompoundButton;
import androidx.preference.ListPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreference;


import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;


import org.lineageos.settings.R;
import org.lineageos.settings.R;
import org.lineageos.settings.utils.FileUtils;
import org.lineageos.settings.utils.FileUtils;


public class DozeSettingsFragment extends PreferenceFragment
public class DozeSettingsFragment extends PreferenceFragment
        implements OnPreferenceChangeListener, OnMainSwitchChangeListener {
        implements OnPreferenceChangeListener {
    private MainSwitchPreference mSwitchBar;
    private MainSwitchPreference mSwitchBar;


    private SwitchPreference mAlwaysOnDisplayPreference;
    private SwitchPreference mAlwaysOnDisplayPreference;
@@ -62,8 +58,10 @@ public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        boolean dozeEnabled = DozeUtils.isDozeEnabled(getActivity());
        boolean dozeEnabled = DozeUtils.isDozeEnabled(getActivity());


        mSwitchBar = (MainSwitchPreference) findPreference(DozeUtils.DOZE_ENABLE);
        mSwitchBar = (MainSwitchPreference) findPreference(DozeUtils.DOZE_ENABLE);
        mSwitchBar.addOnSwitchChangeListener(this);
        if (mSwitchBar != null) {
        mSwitchBar.setChecked(dozeEnabled);
            mSwitchBar.addOnSwitchChangeListener((buttonView, isChecked) -> handleSwitchChange(isChecked));
            mSwitchBar.setChecked(dozeEnabled);
        }


        mAlwaysOnDisplayPreference = (SwitchPreference) findPreference(DozeUtils.ALWAYS_ON_DISPLAY);
        mAlwaysOnDisplayPreference = (SwitchPreference) findPreference(DozeUtils.ALWAYS_ON_DISPLAY);
        mAlwaysOnDisplayPreference.setEnabled(dozeEnabled);
        mAlwaysOnDisplayPreference.setEnabled(dozeEnabled);
        mAlwaysOnDisplayPreference.setChecked(DozeUtils.isAlwaysOnEnabled(getActivity()));
        mAlwaysOnDisplayPreference.setChecked(DozeUtils.isAlwaysOnEnabled(getActivity()));
        mAlwaysOnDisplayPreference.setOnPreferenceChangeListener(this);
        mAlwaysOnDisplayPreference.setOnPreferenceChangeListener(this);
        mDozeBrightnessPreference = (ListPreference) findPreference(DozeUtils.DOZE_BRIGHTNESS_KEY);
        mDozeBrightnessPreference = (ListPreference) findPreference(DozeUtils.DOZE_BRIGHTNESS_KEY);
        mDozeBrightnessPreference.setEnabled(
        mDozeBrightnessPreference.setEnabled(
                dozeEnabled && DozeUtils.isAlwaysOnEnabled(getActivity()));
                dozeEnabled && DozeUtils.isAlwaysOnEnabled(getActivity()));
        mDozeBrightnessPreference.setOnPreferenceChangeListener(this);
        mDozeBrightnessPreference.setOnPreferenceChangeListener(this);
        // Hide AOD and doze brightness if not supported and set all its dependents otherwise
        // Hide AOD and doze brightness if not supported and set all its dependents otherwise
        if (!DozeUtils.alwaysOnDisplayAvailable(getActivity())) {
        if (!DozeUtils.alwaysOnDisplayAvailable(getActivity())) {
            getPreferenceScreen().removePreference(mAlwaysOnDisplayPreference);
            getPreferenceScreen().removePreference(mAlwaysOnDisplayPreference);
            getPreferenceScreen().removePreference(mDozeBrightnessPreference);
            getPreferenceScreen().removePreference(mDozeBrightnessPreference);
        } else {
        } else {
            if (!FileUtils.isFileWritable(DozeUtils.DOZE_MODE_PATH)) {
            if (!FileUtils.isFileWritable(DozeUtils.DOZE_MODE_PATH)) {
                getPreferenceScreen().removePreference(mDozeBrightnessPreference);
                getPreferenceScreen().removePreference(mDozeBrightnessPreference);
            } else {
            } else {
                DozeUtils.updateDozeBrightnessIcon(getContext(), mDozeBrightnessPreference);
                DozeUtils.updateDozeBrightnessIcon(getContext(), mDozeBrightnessPreference);
            }
            }
        }
        }
    }
    }
    @Override
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (DozeUtils.ALWAYS_ON_DISPLAY.equals(preference.getKey())) {
        if (DozeUtils.ALWAYS_ON_DISPLAY.equals(preference.getKey())) {
            DozeUtils.enableAlwaysOn(getActivity(), (Boolean) newValue);
            DozeUtils.enableAlwaysOn(getActivity(), (Boolean) newValue);
            if (!(Boolean) newValue) {
            if (!(Boolean) newValue) {
                mDozeBrightnessPreference.setValue(DozeUtils.DOZE_BRIGHTNESS_LBM);
                mDozeBrightnessPreference.setValue(DozeUtils.DOZE_BRIGHTNESS_LBM);
                DozeUtils.setDozeMode(DozeUtils.DOZE_BRIGHTNESS_LBM);
                DozeUtils.setDozeMode(DozeUtils.DOZE_BRIGHTNESS_LBM);
            }
            }
            mDozeBrightnessPreference.setEnabled((Boolean) newValue);
            mDozeBrightnessPreference.setEnabled((Boolean) newValue);
        } else if (DozeUtils.DOZE_BRIGHTNESS_KEY.equals(preference.getKey())) {
        } else if (DozeUtils.DOZE_BRIGHTNESS_KEY.equals(preference.getKey())) {
            if (!DozeUtils.DOZE_BRIGHTNESS_AUTO.equals((String) newValue)) {
            if (!DozeUtils.DOZE_BRIGHTNESS_AUTO.equals((String) newValue)) {
                DozeUtils.setDozeMode((String) newValue);
                DozeUtils.setDozeMode((String) newValue);
            }
            }
        }
        }
        mHandler.post(() -> {
        mHandler.post(() -> {
            DozeUtils.checkDozeService(getActivity());
            DozeUtils.checkDozeService(getActivity());
            DozeUtils.updateDozeBrightnessIcon(getContext(), mDozeBrightnessPreference);
            DozeUtils.updateDozeBrightnessIcon(getContext(), mDozeBrightnessPreference);
        });
        });
        return true;
        return true;
    }
    }


    @Override
    private void handleSwitchChange(boolean isChecked) {
    public void onSwitchChanged(Switch switchView, boolean isChecked) {
        DozeUtils.enableDoze(getActivity(), isChecked);
        DozeUtils.enableDoze(getActivity(), isChecked);
        DozeUtils.checkDozeService(getActivity());
        DozeUtils.checkDozeService(getActivity());


        mSwitchBar.setChecked(isChecked);

        if (!isChecked) {
        if (!isChecked) {
            DozeUtils.enableAlwaysOn(getActivity(), false);
            DozeUtils.enableAlwaysOn(getActivity(), false);
            mAlwaysOnDisplayPreference.setChecked(false);
            mAlwaysOnDisplayPreference.setChecked(false);
