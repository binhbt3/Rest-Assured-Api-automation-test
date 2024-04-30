package testcase;

import common.BaseTest;
import common.VerifyDataResponse;
import globals.ConfigsGlobal;
import globals.EndPointGlobal;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import lombok.extern.log4j.Log4j2;
import model.data.RegisterPOJOLombok;
import model.data.RegisterPOJOLombokBuilder;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class testUserManagementv2 extends BaseTest {

    public Response postUser(RegisterPOJOLombok registerPOJOLombok){
        Response response = ApiKeyword.post(EndPointGlobal.EP_USER, registerPOJOLombok);
        ApiKeyword.validateJsonSchema(response, ConfigsGlobal.SCHEMA_USER);
        ApiKeyword.verifyResponseContentType(response);
        VerifyDataResponse.verifyUserData(response, registerPOJOLombok);
        return response;
    }


    public Response deleteUser(String userName, RegisterPOJOLombok registerPOJOLombok){
        Response response = ApiKeyword.delete(EndPointGlobal.EP_USER, "username", userName);
        ApiKeyword.validateJsonSchema(response, ConfigsGlobal.SCHEMA_USER);
        ApiKeyword.verifyResponseContentType(response);
        ApiKeyword.verifyStatusCode(response, 200);
        VerifyDataResponse.verifyUserData(response, registerPOJOLombok);
        return response;
    }

    @Test
    public void testGetUsers(){
        Response response = ApiKeyword.get(EndPointGlobal.EP_USERS);
        ApiKeyword.validateJsonSchema(response, ConfigsGlobal.SCHEMA_ALL_USERS);
    }

    @Test
    public void testPostUser(){
        RegisterPOJOLombok registerPOJOLombok = RegisterPOJOLombokBuilder.getRegisterData();
        postUser(registerPOJOLombok);
        deleteUser(registerPOJOLombok.getUsername(), registerPOJOLombok);
    }

    @Test()
    public void testGetUserByUserName(){
        RegisterPOJOLombok registerPOJOLombok = RegisterPOJOLombokBuilder.getRegisterData();
        postUser(registerPOJOLombok);
        Map<String, String> username = new HashMap<String, String>();
        username.put("username", registerPOJOLombok.getUsername());
        Response response = ApiKeyword.get(EndPointGlobal.EP_USER, "username", registerPOJOLombok.getUsername());
        ApiKeyword.validateJsonSchema(response, ConfigsGlobal.SCHEMA_USER);
        ApiKeyword.verifyResponseContentType(response);
        VerifyDataResponse.verifyUserData(response,registerPOJOLombok);
    }

    @Test
    public void testDeleteUser(){
        RegisterPOJOLombok registerPOJOLombok = RegisterPOJOLombokBuilder.getRegisterData();
        postUser(registerPOJOLombok);
        deleteUser(registerPOJOLombok.getUsername(), registerPOJOLombok);
    }

    @Test
    public void testPutUser(){
        log.info("Create data test");
        RegisterPOJOLombok registerPOJOLombokOriginal = RegisterPOJOLombokBuilder.getRegisterData();
        Response response = postUser(registerPOJOLombokOriginal);

        int id = response.jsonPath().get("response.id");
        log.info("id: " + id);
        RegisterPOJOLombok registerPOJOLombokUpdate = RegisterPOJOLombokBuilder.getRegisterData();
        Response responseUpdate = ApiKeyword.put(EndPointGlobal.EP_USER + "/" + id, registerPOJOLombokUpdate);
        ApiKeyword.validateJsonSchema(responseUpdate, ConfigsGlobal.SCHEMA_USER);
        ApiKeyword.verifyStatusCode(responseUpdate, 200);
        ApiKeyword.verifyResponseContentType(responseUpdate);
        VerifyDataResponse.verifyUserData(responseUpdate,registerPOJOLombokUpdate);

        log.info("Clean data test");
        deleteUser(registerPOJOLombokUpdate.getUsername(), registerPOJOLombokUpdate);
    }

    @Test
    public void testPatchUser(){
        log.info("Create data test");
        RegisterPOJOLombok registerPOJOLombokOriginal = RegisterPOJOLombokBuilder.getRegisterData();
        Response response = postUser(registerPOJOLombokOriginal);

        int id = response.jsonPath().get("response.id");
        log.info("id: " + id);
        RegisterPOJOLombok registerPOJOLombokUpdate = RegisterPOJOLombokBuilder.getRegisterData();
        Response responseUpdate = ApiKeyword.patch(EndPointGlobal.EP_USER + "/" + id, registerPOJOLombokUpdate);
        ApiKeyword.validateJsonSchema(responseUpdate, ConfigsGlobal.SCHEMA_USER);
        ApiKeyword.verifyStatusCode(responseUpdate, 200);
        ApiKeyword.verifyResponseContentType(responseUpdate);
        VerifyDataResponse.verifyUserData(responseUpdate,registerPOJOLombokUpdate);

        log.info("Clean data test");
        deleteUser(registerPOJOLombokUpdate.getUsername(), registerPOJOLombokUpdate);
    }

    @Test
    public void testPostUsers(){
        List<RegisterPOJOLombok> registerPOJOLomboksList = new ArrayList();
        registerPOJOLomboksList.add(RegisterPOJOLombokBuilder.getRegisterData());

        Response response = ApiKeyword.post(EndPointGlobal.EP_USERS, registerPOJOLomboksList);
        VerifyDataResponse.verifyUsersData(response, registerPOJOLomboksList);
        // Clean data: Delete users
        String userName;
        for(int i = 0; i < registerPOJOLomboksList.size(); i++){
            userName = response.jsonPath().get("response[%d].username".formatted(i));
            log.info("Delete user name: " + userName);
            Response responseDelete = ApiKeyword.delete(EndPointGlobal.EP_USER, "username", userName);
            ApiKeyword.validateJsonSchema(responseDelete, ConfigsGlobal.SCHEMA_USER);
            VerifyDataResponse.verifyUserData(responseDelete, registerPOJOLomboksList.get(i));
        }
    }

}
