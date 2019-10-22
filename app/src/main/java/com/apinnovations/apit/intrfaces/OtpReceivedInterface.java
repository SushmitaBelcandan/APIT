package com.apinnovations.apit.intrfaces;

public interface OtpReceivedInterface {

    void onOtpReceived(String otp);
    void onOtpTimeout();
}
