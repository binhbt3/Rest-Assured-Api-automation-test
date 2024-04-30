package model.data;

import globals.ConfigsGlobal;

public class LoginPOJOLombokBuilder {
    public static LoginPOJOLombok getLoginData(){
        return LoginPOJOLombok.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD).build();

    }
}
