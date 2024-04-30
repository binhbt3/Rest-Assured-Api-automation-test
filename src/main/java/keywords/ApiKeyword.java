package keywords;

import globals.ConfigsGlobal;
import helpers.SystemHelper;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Log4j2
public class ApiKeyword {
    @Step("GET: {0}")
    public static Response get(String path){
        log.info("GET: " + path);
        Response response = given(SpecBuilder.getRequestSpecification())
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET: {0} with headers: {1}")
    public static Response get(String path, Map<String, String> headers){
        log.info("GET: " + path);
        log.info("HEADERS: " + headers);
        Response response = given(SpecBuilder.getRequestSpecification().headers(headers))
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET: {0} with key param: {1} and value param: {2}")
    public static Response get(String path, String keyParam, boolean valueParam){
        log.info("GET: " + path);
        log.info("keyParam: " + keyParam);
        log.info("valueParam: " + valueParam);
        Response response = given(SpecBuilder.getRequestSpecification())
                .queryParams(keyParam, valueParam)
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    public static Response get(String path, String keyParam, String valueParam){
        log.info("GET: " + path);
        log.info("keyParam: " + keyParam);
        log.info("valueParam: " + valueParam);
        Response response = given(SpecBuilder.getRequestSpecification())
                .queryParams(keyParam, valueParam)
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    public static Response getNotAuth(String path){
        log.info("GET: " + path);
        Response response = given(SpecBuilder.getSpecificationNotAuth())
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return  response;
    }

    public static Response post(String path, Object payload){
        log.info("POST: " + path);
        log.info("PAYLOAD: " + payload);
        Response response = given(SpecBuilder.getRequestSpecification())
                .body(payload)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {0} with payload: {1}")
    public static Response postNotAuth(String path, Object payload){
        log.info("POST: " + path);
        log.info("PAYLOAD: " + payload);
        Response response = given(SpecBuilder.getSpecificationNotAuth())
                .body(payload)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {1} with json file path {1}")
    public static Response post(String path, String filePathPayLoad){
        log.info("POST: " + path);
        log.info("FILE PATH PAYLOAD: " + filePathPayLoad);
        Response response = given(SpecBuilder.getRequestSpecification())
                .body(new File(filePathPayLoad))
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("PUT {0} with payload {1}")
    public static Response put(String path, Object payload){
        log.info("PUT: " + path);
        log.info("PAYLOAD: " + payload);

        Response response = given(SpecBuilder.getRequestSpecification())
                .body(payload)
                .when()
                .put(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("PATCH {0} with payload {1}")
    public static Response patch(String path, Object payload){
        log.info("PATH: " + path);
        log.info("PAYLOAD: " + payload);

        Response response = given(SpecBuilder.getRequestSpecification())
                .body(payload)
                .when()
                .patch(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("DELETE {0} with payload {1}")
    public static Response delete(String path){
        log.info("DELETE: " + path);

        Response response = given(SpecBuilder.getRequestSpecification())
                .when()
                .delete(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }


    @Step("DELETE {0} with payload {1}")
    public static Response delete(String path, Object payload){
        log.info("DELETE: " + path);
        log.info("PAYLOAD: " + payload);

        Response response = given(SpecBuilder.getRequestSpecification())
                .body(payload)
                .when()
                .delete(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("DELETE {0} with payload {1}")
    public static Response delete(String path, String paramKey, String paramValue){
        log.info("DELETE: " + path);
        log.info("paramKey: " + paramKey);
        log.info("paramValue: " + paramValue);

        Response response = given(SpecBuilder.getRequestSpecification())
                .queryParams(paramKey, paramValue)
                .when()
                .delete(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();
        log.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("Get response {0} from responseKey {1}")
    public static String getResponseKeyValue(Response response, String responseKey){
        JsonPath jsonPath = response.jsonPath();
        String responseValue = jsonPath.get(responseKey).toString();
        log.info("Get response Key " + responseKey + " by response value: " + responseValue);
        return responseValue;
    }

    @Step("Get response {0} from responseKey {1}")
    public static String getResponseKeyValue(String response, String responseKey){
        JsonPath jsonPath = new JsonPath(response);
        String responseValue = jsonPath.get(responseKey).toString();
        log.info("Get response Key " + responseKey + " by response value: " + responseValue);
        return responseValue;
    }

    @Step("Get status code from response {0}")
    public static int getStatusCode(Response response){
        int statusCode = response.getStatusCode();
        log.info("Status code: " + statusCode);
        return statusCode;
    }

    @Step("Get header from response {0} with header key {1}")
    public static String getResponseHeader(Response response, String headerKey){
        String responseHeader = response.getHeader(headerKey);
        log.info("Get header: " + responseHeader);
        return responseHeader;
    }

    @Step("Get cookie name from response {0} with cookie name {1}")
    public static String getResponseCookieName(Response response, String cookieName){
        String cookieValue = response.getCookie(cookieName);
        log.info("Get cookie name: " + cookieValue);
        return cookieValue;
    }

    @Step("Get content type from response {0} ")
    public static String getResponseContentType(Response response){
        String responseContentType = response.getContentType();
        log.info("Get response cntent type: " + responseContentType);
        return responseContentType;
    }

    @Step("Verify content type from response {0} ")
    public static void verifyResponseContentType(Response response){
        String responseContentType = response.getContentType();
        log.info("Get response cntent type: " + responseContentType);
        Assert.assertEquals(responseContentType, ConfigsGlobal.CONTENT_TYPE, "Content type is not match");
    }

    @Step("Verify status code")
    public static void verifyStatusCode(Response response, int expectedStatus){
        log.info("Verify status code " + response.getStatusCode() + "== " + expectedStatus);
        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Status is not match");
    }

    @Step("Validate Json Schema")
    public static void validateJsonSchema(Response response, String schemaFileName){
        String filePath = SystemHelper.getCurrentDir() + ConfigsGlobal.TEST_DATA_RESOURCES + schemaFileName;
        File getSchemaFile = new File(filePath);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(getSchemaFile));
    }

}
