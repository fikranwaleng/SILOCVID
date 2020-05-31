package com.fikran.silocvid;

public class AppVar {

    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "https://silcovid.000webhostapp.com/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "kata_sandi";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
}
