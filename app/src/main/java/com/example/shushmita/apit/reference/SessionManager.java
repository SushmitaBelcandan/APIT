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
    private static final String KEY_PROF_PIC = "profilepic";

    private static final String KEY_IMG_Id = "image_id";
    private static final String KEY_CB_STATUS = "false";

    private static final String KEY_GRNS_VRTY = "verity";

    private static final String KEY_ENQ_ID = "enquiry_id";
    private static final String KEY_ENQ_USRID = "user_id";
    private static final String KEY_PRCS_ID = "process_id";
    private static final String KEY_IMG_ID = "process_image_id";
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_CONTCT = "contact_person";
    private static final String KEY_MOBILE_NO = "mobileno";
    private static final String KEY_COUNTRY_ID = "country_id";
    private static final String KEY_PINCODE = "pincode";
    private static final String KEY_STATE_ID = "state_id";
    private static final String KEY_DIST_ID = "district_id";
    private static final String KEY_TALUK_ID = "taluk_id";
    private static final String KEY_VLLG_ID = "village_id";
    private static final String KEY_GST_NO = "gst_no";
    private static final String KEY_SOIL = "soil_bearing";
    private static final String KEY_WIND_SPEED = "wind_speed";
    private static final String KEY_AVG_RAINFALL = "avg_rainfall";
    private static final String KEY_PADDY_AGE = "paddy_age";
    private static final String KEY_PADDY_DENSITY = "paddy_density";
    private static final String KEY_CHECKED_POSITION = "chked_pos";




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
        editor.commit();
    }


    public void saveCHeckPosition(int pos)
    {
        editor.putInt(KEY_CHECKED_POSITION, pos);
        editor.commit();
    }


    public int getCHeckPosition() {
        return shref.getInt(KEY_CHECKED_POSITION, 0);
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

    public void selectedImage(Integer img_id, Boolean chkbx_flag) {
        editor.putInt(KEY_IMG_Id, img_id);
        editor.putBoolean(KEY_CB_STATUS, chkbx_flag);
        editor.commit();
    }

    public Integer getImageID() {
        return shref.getInt(KEY_IMG_Id, 0);
    }

    public Boolean getChkBoxStatus() {
        return shref.getBoolean(KEY_CB_STATUS, false);
    }

    public void setVarietyGrains(String grains_name) {
        editor.putString(KEY_GRNS_VRTY, grains_name);
        editor.commit();
    }

    public String getVarietyGrains() {
        return shref.getString(KEY_GRNS_VRTY, null);
    }

    public void saveEnquiryResponse(String enquiry_id, String user_id, String process_id, String process_image_id,
                                    String first_name, String contact_person, String mobileno, String country_id,
                                    String pincode, String state_id, String district_id, String taluk_id,
                                    String village_id, String gst_no, String soil_bearing, String wind_speed,
                                    String avg_rainfall, String paddy_age, String paddy_density) {
        editor.putBoolean(IS_REG, true);
        editor.putString(KEY_ENQ_ID, enquiry_id);
        editor.putString(KEY_ENQ_USRID, user_id);
        editor.putString(KEY_PRCS_ID, process_id);
        editor.putString(KEY_IMG_ID, process_image_id);
        editor.putString(KEY_FNAME, first_name);
        editor.putString(KEY_CONTCT, contact_person);
        editor.putString(KEY_MOBILE_NO, mobileno);
        editor.putString(KEY_COUNTRY_ID, country_id);
        editor.putString(KEY_PINCODE, pincode);
        editor.putString(KEY_STATE_ID, state_id);
        editor.putString(KEY_DIST_ID, district_id);
        editor.putString(KEY_TALUK_ID, taluk_id);
        editor.putString(KEY_VLLG_ID, village_id);
        editor.putString(KEY_GST_NO, gst_no);
        editor.putString(KEY_SOIL, soil_bearing);
        editor.putString(KEY_WIND_SPEED, wind_speed);
        editor.putString(KEY_AVG_RAINFALL, avg_rainfall);
        editor.putString(KEY_PADDY_AGE, paddy_age);
        editor.putString(KEY_PADDY_DENSITY, paddy_density);
        editor.commit();
    }
    public String getEnquiryId() {
        return shref.getString(KEY_ENQ_ID, null);
    }

    public String getEnqUserId() {
        return shref.getString(KEY_ENQ_USRID, null);
    }

    public String getEnqPrcsId() {
        return shref.getString(KEY_PRCS_ID, null);
    }

    public String getEnqImgId() {
        return shref.getString(KEY_IMG_ID, null);
    }

    public String getEnqFName() {
        return shref.getString(KEY_FNAME, null);
    }

    public String getEnqContact() {
        return shref.getString(KEY_CONTCT, null);
    }

    public String getEnqMobile() {
        return shref.getString(KEY_MOBILE, null);
    }

    public String getEnqCountry() {
        return shref.getString(KEY_COUNTRY_ID, null);
    }

    public String getEnqPincode() {
        return shref.getString(KEY_PINCODE, null);
    }

    public String getEnqState() {
        return shref.getString(KEY_STATE_ID, null);
    }

    public String getEnqDistrict() {
        return shref.getString(KEY_DIST_ID, null);
    }

    public String getEnqTaluk() {
        return shref.getString(KEY_TALUK_ID, null);
    }

    public String getEnqVillageId() {
        return shref.getString(KEY_VLLG_ID, null);
    }

    public String getGstNo() {
        return shref.getString(KEY_GST_NO, null);
    }

    public String getEnqSoil() {
        return shref.getString(KEY_SOIL, null);
    }

    public String getEnqWindSpeed() {
        return shref.getString(KEY_WIND_SPEED, null);
    }

    public String getEnqAvgRainFall() {
        return shref.getString(KEY_AVG_RAINFALL, null);
    }

    public String getEnqPaddysAge() {
        return shref.getString(KEY_PADDY_AGE, null);
    }

    public String getPaddyDensity() {
        return shref.getString(KEY_PADDY_DENSITY, null);
    }


   /* public void clearEnquiryForm()
    {
        editor.putBoolean(IS_REG, true);
        editor.putString(KEY_IMG_ID, "");
        editor.putString(KEY_FNAME, "");
        editor.putString(KEY_CONTCT, "");
        editor.putString(KEY_MOBILE_NO, "");
        editor.putString(KEY_COUNTRY_ID, "");
        editor.putString(KEY_PINCODE, "");
        editor.putString(KEY_STATE_ID, "");
        editor.putString(KEY_DIST_ID, "");
        editor.putString(KEY_TALUK_ID, "");
        editor.putString(KEY_VLLG_ID, "");
        editor.putString(KEY_GST_NO, "");
        editor.putString(KEY_SOIL, "");
        editor.putString(KEY_WIND_SPEED, "");
        editor.putString(KEY_AVG_RAINFALL, "");
        editor.putString(KEY_PADDY_AGE, "");
        editor.putString(KEY_PADDY_DENSITY, "");
        editor.commit();
    }*/
}
