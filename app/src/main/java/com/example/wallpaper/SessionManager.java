package com.example.wallpaper;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.Key;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "id";

    public SessionManager(Context context) {
        this.context = context;
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String id) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_ID, id);

        editor.commit();
    }

    public String getIdFromSession() {
        return userSession.getString(KEY_ID, null);
    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_ID, userSession.getString(KEY_ID, null));
        return userData;
    }

    public Boolean checkLogin() {
        if (userSession.getBoolean(IS_LOGIN, false)) {
            return true;
        } else {
            return false;
        }
    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();
    }

}
