package org.shreya.project.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.shreya.project.sample.Activities.LoginActivity;

public class DbHandler {

    public static void putInt(Context context, String Key, int value) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(Key, value);
            editor.commit();
        }
    }

    public static void putString(Context context, String Key, String value) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(Key, value);
            editor.commit();
        }
    }

    public static void putBoolean(Context context, String Key, Boolean value) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(Key, value);
            editor.commit();
        }
    }

    public static Boolean contains(Context context, String key) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            return prefs.contains(key);
        } else return null;
    }

    public static int getInt(Context context, String Key, int Alternate) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);

            return prefs.getInt(Key, Alternate);
        } else return 0;
    }

    public static String getString(Context context, String Key, String Alternate) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            return prefs.getString(Key, Alternate);
        } else return null;
    }

    public static Boolean getBoolean(Context context, String Key, Boolean Alternate) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            return prefs.getBoolean(Key, Alternate);
        } else return false;
    }

    public static void remove(Context context, String key) {
        if (context != null) {
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("Sample", Context.MODE_PRIVATE);
            if (DbHandler.contains(context, key)) {
                prefs.edit().remove(key).commit();
            }
        }
    }

    public static void clearDb(Context context) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences("Sample", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public static void setSession(Context context, UserClass userClass) {
        if (context != null) {
            DbHandler.putString(context, "name", userClass.getName());
            DbHandler.putString(context, "mobile", userClass.getPhoneNo());
            DbHandler.putString(context, "email", userClass.getEmail());
            DbHandler.putString(context, "address", userClass.getAddress());
            DbHandler.putBoolean(context, "isLoggedIn", true);

        }
    }

    @TargetApi(16)
    public static void unsetSession(Context context, String type) {
        if (context != null) {
            DbHandler.clearDb(context);
            DbHandler.putBoolean(context, "isLoggedIn", false);
            Bundle b = new Bundle();
            b.putBoolean(type, true);


            Intent i = new Intent(context, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtras(b);
            context.startActivity(i);
            ((Activity) context).finishAffinity();
        }

    }
}
