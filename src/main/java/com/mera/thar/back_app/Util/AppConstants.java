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

    // LOGIN MESSAGE CONTAINER

    public static final String MSG_NO_USER_EXIST = "No User Exist!";
    public static final String MSG_INVALID_CREDENTIALS = "Invalid -or- Empty Credentials";
    public static final String MSG_LOGIN_SUCCESSFUL_TOURIST = "TOURIST - Login Successful!";
    public static final String MSG_LOGIN_SUCCESSFUL_ADMIN = "ADMIN - Login Successful!";

    // TOURIST POINTS MESSAGE CONTAINER
    public static final String MSG_INVALID_TOURIST_POINT_ID = "Invalid -or- Empty -or- Wrong Tourist Id";
    public static final String MSG_INVALID_TOURIST_POINT_NAME = "Invalid -or- Empty -or- Wrong Tourist Point Name";

    // CITY MESSAGE CONTAINER
    public static final String MSG_INVALID_CITY_ID = "Invalid -or- Empty -or- Wrong City Id";
    public static final String MSG_INVALID_CITY_NAME = "Invalid -or- Empty -or- Wrong City Name";
    public static final String MSG_NO_CITY_FOUND = "Requested City is not Available";


    // HOTEL MESSAGE CONTAINER
    public static final String MSG_NO_HOTELS_AVAILABLE = "Current City don't have Hotels";
    public static final String MSG_HOTELS_AVAILABLE = "All Available Hotels in the City are shown below!";
    public static final String MSG_DUPLICATE_HOTEL = "Requested Hotel Already Exists";
    public static final String MSG_HOTEL_ADDED_SUCCESSFULLY = "Hotel Added Successfully";

    public static final String MSG_NO_ROOM_FOUND = "No Such Room Founded against the Provided Room's Parameters";
    public static final String MSG_ROOM_NUMBER_EMPTY = "Room Number can't be Empty -or- Null";
    public static final String MSG_DUPLICATE_ROOM_NUMBER = "Requested Room Already Available";
    public static final String MSG_ROOM_ADDED_SUCCESSFULLY = "Room Added Successfully";
    public static final String MSG_NO_ROOM_AVAILABLE = "No Room Available against the provided request parameter";
    public static final String MSG_ROOM_AVAILABLE = "Available Room in the Hotel is shown below!";

}