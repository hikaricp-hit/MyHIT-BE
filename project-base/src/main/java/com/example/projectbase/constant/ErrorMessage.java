package com.example.projectbase.constant;

public class ErrorMessage {

  public static final String ERR_EXCEPTION_GENERAL = "exception.general";
  public static final String UNAUTHORIZED = "exception.unauthorized";
  public static final String FORBIDDEN = "exception.forbidden";
  public static final String FORBIDDEN_UPDATE_DELETE = "exception.forbidden.update-delete";

  //error validation dto
  public static final String INVALID_SOME_THING_FIELD = "invalid.general";
  public static final String INVALID_FORMAT_SOME_THING_FIELD = "invalid.general.format";
  public static final String INVALID_SOME_THING_FIELD_IS_REQUIRED = "invalid.general.required";
  public static final String NOT_BLANK_FIELD = "invalid.general.not-blank";
  public static final String INVALID_FORMAT_PASSWORD = "invalid.password-format";
  public static final String INVALID_DATE = "invalid.date-format";
  public static final String INVALID_DATE_FEATURE = "invalid.date-future";
  public static final String INVALID_DATETIME = "invalid.datetime-format";

  public static class Auth {
    public static final String ERR_INCORRECT_USERNAME = "exception.auth.incorrect.username";
    public static final String ERR_INCORRECT_PASSWORD = "exception.auth.incorrect.password";
    public static final String ERR_INCORRECT_EMAIL = "exception.auth.incorrect.email";
    public static final String ERR_INCORRECT_OTP = "exception.auth.incorrect.otp";
    public static final String ERR_OTP_EXPIRED = "exception.auth.otp.expired";
    public static final String ERR_ACCOUNT_NOT_ENABLED = "exception.auth.account.not.enabled";
    public static final String ERR_ACCOUNT_LOCKED = "exception.auth.account.locked";
    public static final String INVALID_REFRESH_TOKEN = "exception.auth.invalid.refresh.token";
    public static final String EXPIRED_REFRESH_TOKEN = "exception.auth.expired.refresh.token";
  }

  public static class User {
    public static final String ERR_NOT_FOUND_USERNAME = "exception.user.not.found.username";
    public static final String ERR_NOT_FOUND_ID = "exception.user.not.found.id";
  }

  public static class Event {
    public static final String EVENT_NOT_FOUND = "Event not found with id: ";
    public static final String INVALID_EVENT_TYPE = "Invalid event type";
    public static final String EMPTY_EVENT_NAME = "Event name cannot be empty";
    public static final String INVALID_EVENT_DATE = "Invalid event type";
  }
  public static class Notification {
    public static final String ERR_NOT_FOUND_USERNAME = "exception.notification.not.found.username";
    public static final String ERR_NOT_FOUND_ID = "exception.notification.not.found.id";
  }

  public static class Course{
    public static final String ERR_NOT_FOUND_USERNAME = "exception.course.not.found.username";
    public static final String ERR_NOT_FOUND_ID = "exception.course.not.found.id";
  }

  public static class Register{
    public static final String ERR_NOT_FOUND_USERNAME = "exception.register.not.found.username";
    public static final String ERR_NOT_FOUND_ID = "exception.register.not.found.id";
  }

  public static class Member{
    public static final String ERR_NOT_FOUND_USERNAME = "exception.member.not.found.username";
    public static final String ERR_NOT_FOUND_ID = "exception.member.not.found.id";
  }
}
