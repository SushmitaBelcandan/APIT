package com.example.shushmita.apit.reference;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences shref;
    SharedPreferences.Editor editor;
    Context mContext;
    int MODE_PRIVATE = 0;

    private static final String PREF_NAME = "SessionData";
    private static final String IS_REG = "IsRegTrue";

    private static final String KEY_UID = "user_id";
    private static final String KEY_UNAME = "user_name";
    private static final String KEY_MOBILE = "mobileno";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CTYPE_ID = "customer_type_id";
    private static final String KEY_GEO_DETAIL = "geo_details";
    private static final String KEY_GST_NO = "gst_no";
    private static final String KEY_PASS = "password";
    private static final String KEY_PROF_PIC = "profilepic";

    public SessionManager(Context contxt) {
        this.mContext = contxt;
        shref = mContext.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = shref.edit();
    }

    public void saveLoginData(String str_usr_id, String str_usr_name, String str_email_id, String cust_type_id,
                                   String str_mobile_number, String profile_pic) {
        editor.putBoolean(IS_REG, true);
        editor.putString(KEY_UID, str_usr_id);
        editor.putString(KEY_UNAME, str_usr_name);
        editor.putString(KEY_MOBILE, str_mobile_number);
        editor.putString(KEY_EMAIL, str_email_id);
        editor.putString(KEY_CTYPE_ID, cust_type_id);
        editor.putString(KEY_PROF_PIC, profile_pic);
    }

    public String getUsrId() {
        return shref.getString(KEY_UID, null);
    }

    public String getUserName() {
        return shref.getString(KEY_UNAME, null);
    }

    public String getUsrMobile() {
        return shref.getString(KEY_MOBILE, null);
    }

    public String getUsrEmail() {
        return shref.getString(KEY_EMAIL, null);
    }

    public String getCustTypeId() {
        return shref.getString(KEY_CTYPE_ID, null);
    }

    public String getProfilePic() {
        return shref.getString(KEY_PROF_PIC, null);
    }
}
