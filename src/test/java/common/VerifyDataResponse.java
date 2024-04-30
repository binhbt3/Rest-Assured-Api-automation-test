package common;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.data.BookingPOJOLombok;
import model.data.RegisterPOJOLombok;
import org.testng.Assert;

import java.util.List;

public class VerifyDataResponse {
    public static void verifyUserData(Response response, RegisterPOJOLombok registerPOJOLombok){
        ApiKeyword.verifyResponseContentType(response);
        ApiKeyword.verifyStatusCode(response, 200);
        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message is not match");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.username"), registerPOJOLombok.getUsername(), "User name is not match");
        Assert.assertEquals(jsonPath.get("response.firstName"), registerPOJOLombok.getFirstName(), "First name is not match");
        Assert.assertEquals(jsonPath.get("response.lastName"), registerPOJOLombok.getLastName(), "Last name is not match");
        Assert.assertEquals(jsonPath.get("response.email"), registerPOJOLombok.getEmail(), "Email is not match");
        Assert.assertEquals(jsonPath.get("response.phone"), registerPOJOLombok.getPhone(), "Phone is not match");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("response.userStatus").toString()), registerPOJOLombok.getUserStatus(), "User Status is not match");
    }

    public static void verifyUsersData(Response response, List<RegisterPOJOLombok> registerPOJOLomboksList){
        ApiKeyword.verifyResponseContentType(response);
        ApiKeyword.verifyStatusCode(response, 200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message is not match");

        JsonPath jsonPath = response.jsonPath();
        for(int i = 0 ; i < registerPOJOLomboksList.size() ; i++) {
            Assert.assertEquals(jsonPath.get("response[%d].username".formatted(i)), registerPOJOLomboksList.get(i).getUsername(), "User name is not match");
            Assert.assertEquals(jsonPath.get("response[%d].firstName".formatted(i)), registerPOJOLomboksList.get(i).getFirstName(), "First name is not match");
            Assert.assertEquals(jsonPath.get("response[%d].lastName".formatted(i)), registerPOJOLomboksList.get(i).getLastName(), "Last name is not match");
            Assert.assertEquals(jsonPath.get("response[%d].email".formatted(i)), registerPOJOLomboksList.get(i).getEmail(), "Email is not match");
            Assert.assertEquals(jsonPath.get("response[%d].phone".formatted(i)), registerPOJOLomboksList.get(i).getPhone(), "Phone is not match");
            Assert.assertEquals(Integer.parseInt(jsonPath.get("response[%d].userStatus".formatted(i)).toString()), registerPOJOLomboksList.get(i).getUserStatus(), "User Status is not match");
        }
    }

    public static void verifyBookData(Response response, BookingPOJOLombok bookingPOJOLombok){
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyResponseContentType(response);

        String message = response.jsonPath().get("message");
        Assert.assertEquals(message, "Success", "The message is not match");

        Assert.assertEquals(response.jsonPath().get("response.name"), bookingPOJOLombok.getName(), "Name is not match");
        Assert.assertEquals(Integer.parseInt(response.jsonPath().get("response.category_id").toString()), bookingPOJOLombok.getCategory_id(), "Category id is not match");
        Assert.assertEquals(Integer.parseInt(response.jsonPath().get("response.price").toString()), bookingPOJOLombok.getPrice(), "Price is not match");
        Assert.assertEquals(Integer.parseInt(response.jsonPath().get("response.image[0].id").toString()), bookingPOJOLombok.getImage_ids().get(0).intValue(), "image id is not match");
        Assert.assertEquals(response.jsonPath().get("response.release_date"), bookingPOJOLombok.getRelease_date(), "Release date is not match");
        Assert.assertEquals(Boolean.parseBoolean(response.jsonPath().get("response.status").toString()), bookingPOJOLombok.isStatus(), "Status is not match");
    }

}

