package common;

import com.google.gson.Gson;
import globals.ConfigsGlobal;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keywords.ApiKeyword;
import lombok.extern.log4j.Log4j2;
import model.data.LoginPOJOLombok;
import model.data.LoginPOJOLombokBuilder;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

@Log4j2
public class BaseTest {

    @BeforeSuite

    public void login(){
        LoginPOJOLombok loginPOJOLombok = LoginPOJOLombokBuilder.getLoginData();
        Gson gson = new Gson();
        System.out.println("21: " + gson.toJson(loginPOJOLombok));
        System.out.println("22: " + EndPointGlobal.EP_LOGIN);
//        RequestSpecification requestSpecification = given();
//        requestSpecification.baseUri(ConfigsGlobal.BASE_URI)
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .body(gson.toJson(loginPOJOLombok));
        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJOLombok));
        TokenGlobal.TOKEN = response.getBody().path("token");

    }
}
