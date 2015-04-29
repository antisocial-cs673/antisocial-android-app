package com.antisocial.app.webapi.communication;


public class RestConstants {

  public static final String TAG = RestConstants.class.getSimpleName();

  public static final String BASE_URL = "http://antisocial.azurewebsites.net";

  public static final String API_VERSION = "v1";

  public static final String USERINFO_URL = BASE_URL + "/api/Account/UserInfo";
  
  public static final String GROUPS_URL = BASE_URL + "/" + API_VERSION + "/groups";

  public static final String USER_DATA_URL = BASE_URL + "/" + API_VERSION + "/userdata";
  
  public static final String USER_REGISTER_URL = BASE_URL + "/api/Account/Register";

  public static final String TOKEN_URL = BASE_URL + "/token";
}

