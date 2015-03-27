package com.antisocial.app.util;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Igibek on 3/27/2015.
 */

public class ProfileUtils {

    private static final String PROFILES = "profiles";

    public static String[] getProfiles(Context context)
    {

        String[] arr = new String[]{};

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> set = sharedPreferences.getStringSet(PROFILES, null);

        if(set == null){
            set = new HashSet<String>();
            set.add("All");
            set.add("Home");
            set.add("Work");

            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putStringSet(PROFILES, set);
        }

        return set.toArray(arr);
    }
}
