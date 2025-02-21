package com.btl.menu.constant;

public class Constant {

    // API ENDPOINTS
    public static final String BASE_API_URL = "http://localhost:8080/";
        // AUTH
    public static final String AUTH_URL = BASE_API_URL + "auth/";
    public static final String REGISTER_URL = AUTH_URL + "register";
    public static final String LOGOUT = AUTH_URL + "logout";
        // ACCOUNT
    public static final String BASE_ACCOUNT_URL = "accounts/";
    public static final String ACCOUNT_URL = BASE_API_URL + BASE_ACCOUNT_URL;
        // TEST
    public static final String TEST_PUBLIC_URL = BASE_API_URL + "public";
    public static final String TEST_USER_URL = BASE_API_URL + "user";
    public static final String TEST_ADMIN_URL = BASE_API_URL + "admin";


    // HTTP METHOD
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    // LOCAL STORAGE
    public static final String ACCOUNT_KEY = "account";
    public static final String CHARACTER_KEY = "character";
    public static final String INVENTORY_KEY = "inventory";
    public static final String ITEM_KEY = "item";

}
