package com.example.projectbase.constant;

public class UrlConstant {

  public static class Auth {
    private static final String PRE_FIX = "/auth";

    public static final String LOGIN = PRE_FIX + "/login";
    public static final String LOGOUT = PRE_FIX + "/logout";
    public static final String FORGOT_PASSWORD = PRE_FIX + "/forgot-password";
    public static final String CONFIRM_OTP_AND_CHAGE_PASSWORD = PRE_FIX + "/confirm-otp";

    private Auth() {
    }
  }

  public static class User {
    private static final String PRE_FIX = "/user";

    public static final String GET_USERS = PRE_FIX;
    public static final String GET_USER = PRE_FIX + "/{userId}";
    public static final String GET_CURRENT_USER = PRE_FIX + "/current";

    private User() {
    }
  }

}
