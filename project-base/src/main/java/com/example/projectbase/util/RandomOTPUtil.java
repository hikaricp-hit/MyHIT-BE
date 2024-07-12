package com.example.projectbase.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomOTPUtil {
    public static String random() {
        int length = 6; // Độ dài mã OTP
        return RandomStringUtils.randomNumeric(length);
    }
}
