package com.mera.thar.back_app.Util;

public class AppConstants {

    // APP CODES
    public static final String OK = "SUCCESS_200";
    public static final String CREATED = "SUCCESS_201";
    public static final String ACCEPTED = "SUCCESS_202";
    public static final String NO_CONTENT = "SUCCESS_204";
    public static final String FOUND = "SUCCESS_302";
    public static final String BAD_REQUEST = "ERROR_400";
    public static final String FORBIDDEN = "ERROR_403";
    public static final String NOT_FOUND = "ERROR_404";
    public static final String INTERNAL_SERVER_ERROR = "ERROR_500";
    public static final String APPLICATION = "APPLICATION";

    // LOGIN CODES
    public static final String USER_LOGIN = "SUCCESS_200";
    public static final String USER_CREATED = "SUCCESS_201";
    public static final String USER_ALREADY_EXISTS = "ERROR_203";
    public static final String USER_INPUT_EMPTY = "ERROR_204";
    public static final String USER_SIGNUP_FAILED = "ERROR_400";
    public static final String USER_LOGIN_FAILED = "ERROR_401";
    public static final String USER_NOT_FOUND = "ERROR_404";
    public static final String USER_EMAIL_INVALID = "ERROR_405";
    public static final String USER_PASSWORD_INVALID = "ERROR_406";
    public static final String USER_PARAMETERS_INVALID = "ERROR_407";

    // APP MESSAGES
    public static final String MSG_RESOURCE_DELETED = "Requested resource Deleted Successfully!";
    public static final String MSG_RESOURCE_FOUND = "Requested Resources Found Successfully";
    public static final String MSG_SOMETHING_WENT_WRONG = "Something went Wrong!";
    public static final String MSG_RESOURCE_NOT_FOUND = "No Record Found against the Provided Requested";
    public static final String MSG_RESOURCE_ALREADY_AVAILABLE = "Requested record already available!";
    public static final String MSG_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String MSG_NO_INPUT = "No Input/Empty Found";
    public static final String MSG_INVALID_REQUEST_PARAMETERS = "Invalid Request Parameters -or- Missing Request Parameters";
    public static final String MSG_DUPLICATE_USERNAME = "Requested UserName Already Exists";
    public static final String MSG_USERNAME_REQUIRED = "Username Required in Request to fetch Response";
    public static final String MSG_DATA_SAVED = "Requested Data Saved Successfully!";
    public static final String MSG_DELETED = "Requested Data Deleted Successfully!";
    public static final String MSG_RESOURCE_UPDATED = "Requested Resource Updated Successfully!";

    // TOURIST POINTS MESSAGE CONTAINER
    public static final String MSG_INVALID_TOURIST_POINT_ID = "Invalid -or- Empty -or- Wrong Tourist Point Id";
    public static final String MSG_INVALID_TOURIST_POINT_NAME = "Invalid -or- Empty -or- Wrong Tourist Point Name";

    // CITY MESSAGE CONTAINER
    public static final String MSG_INVALID_CITY_ID = "Invalid -or- Empty -or- Wrong City Id";
    public static final String MSG_INVALID_CITY_NAME = "Invalid -or- Empty -or- Wrong City Name";
    public static final String MSG_NO_CITY_FOUND = "Requested City is not Available";

    // HOTEL MESSAGE CONTAINER
    public static final String MSG_INVALID_HOTEL_ID = "Invalid -or- Empty -or- Wrong Hotel Id";
    public static final String MSG_INVALID_HOTEL_NAME = "Invalid -or- Empty -or- Wrong Hotel Name";

    // RESTAURANTS MESSAGE CONTAINER
    public static final String MSG_INVALID_RESTAURANT_ID = "Invalid -or- Empty -or- Wrong Restaurant Id";
    public static final String MSG_INVALID_RESTAURANT_NAME = "Invalid -or- Empty -or- Wrong Restaurant Name";

    // VEHICLE MESSAGE CONTAINER
    public static final String MSG_INVALID_VEHICLE_ID = "Invalid -or- Empty -or- Wrong Vehicle Id";
    public static final String MSG_INVALID_VEHICLE_NAME = "Invalid -or- Empty -or- Wrong Vehicle Name";

    // DRIVER MESSAGE CONTAINER
    public static final String MSG_INVALID_DRIVER_ID = "Invalid -or- Empty -or- Wrong Driver Id";
    public static final String MSG_INVALID_DRIVER_NAME = "Invalid -or- Empty -or- Wrong Driver Name";

    // USER MESSAGE CONTAINER
    public static final String MSG_USER_ALREADY_EXIST = "Requested User is Already Available";
    public static final String MSG_USER_LOGIN_SUCCESSFULLY = "Requested User is Login Successfully";
    public static final String MSG_USER_LOGIN_FAILED = "Requested User is Login Failed Due to Some Issue";
    public static final String MSG_NO_USER_EXIST = "Requested User is Not Available";
    public static final String MSG_USER_PASSWORD_INCORRECT = "Requested User's Password is Incorrect";
    public static final String MSG_USER_SIGNUP_SUCCESSFULLY = "Requested User is Registered Successfully";
    public static final String MSG_USER_SIGNUP_FAILED = "Requested User is Registered Failed Due to Some Issue";
    public static final String MSG_USER_PARAMETERS_UNAVAILABLE = "Some Requested Parameters are Unavailable";
    public static final String MSG_USER_PARAMETERS_INVALID = "Input Fields are Empty or Invalid";
    public static final String MSG_USER_EMAIL_POLICY = "Provided Email doesn't meet the policy";
    public static final String MSG_USER_PASSWORD_POLICY = "Provided Password doesn't meet the policy";

}