package com.btl.menu.constant;

public class Constant {

    // API
    public static final String BASE_API_URL = "http://localhost:8080/";
    public static final String BASE_ACCOUNT_URL = "accounts/";
    public static final String ACCOUNT_URL = BASE_API_URL + BASE_ACCOUNT_URL;
    public static final String LOGIN_URL = ACCOUNT_URL + "login";
    public static final String TEST_URL = BASE_API_URL + "test1";

    // HTTP REQUEST
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    // LOCAL STORAGE
    public static final String ACCOUNT_KEY = "account";
}
