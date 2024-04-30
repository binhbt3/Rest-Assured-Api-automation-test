package testcase;

import common.BaseTest;
import common.VerifyDataResponse;
import globals.ConfigsGlobal;
import globals.EndPointGlobal;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keywords.ApiKeyword;
import lombok.extern.log4j.Log4j2;
import model.data.BookingPOJOLombok;
import model.data.BookingPOJOLombokBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Log4j2
public class testBookManagement extends BaseTest {

    public Response postBook(BookingPOJOLombok bookingPOJOLombok){
        Response response = ApiKeyword.post(EndPointGlobal.EP_BOOK_MANAGEMENT, bookingPOJOLombok);
        ApiKeyword.validateJsonSchema(response, ConfigsGlobal.SCHEMA_BOOK);
        VerifyDataResponse.verifyBookData(response, bookingPOJOLombok);
        return response;
    }

    public Response deleteBook(BookingPOJOLombok bookingPOJOLombok, String id){
        Response response = ApiKeyword.delete(EndPointGlobal.EP_BOOK_MANAGEMENT + "/" + id);
        ApiKeyword.validateJsonSchema(response,ConfigsGlobal.SCHEMA_BOOK);
        VerifyDataResponse.verifyBookData(response, bookingPOJOLombok);
        return response;
    }

    @Test
    public void testGetBooks(){
        Response response = ApiKeyword.get(EndPointGlobal.EP_USERS);
        ApiKeyword.validateJsonSchema(response, ConfigsGlobal.SCHEMA_ALL_BOOKS);
        ApiKeyword.verifyResponseContentType(response);
        ApiKeyword.verifyStatusCode(response, 200);
    }

    @Test
    public void testPostBook(){
        BookingPOJOLombok bookingPOJOLombok = BookingPOJOLombokBuilder.getBookingData();
        Response response = postBook(bookingPOJOLombok);
    }
    @Test
    public void testGetBookById(){
        System.out.println("testGetBookById");
        BookingPOJOLombok bookingPOJOLombok = BookingPOJOLombokBuilder.getBookingData();
        Response response = postBook(bookingPOJOLombok);
        String id = response.jsonPath().get("response.id").toString();

        Response responseGetBookById = ApiKeyword.get(EndPointGlobal.EP_BOOK_MANAGEMENT +  "/" + id);
        VerifyDataResponse.verifyBookData(responseGetBookById, bookingPOJOLombok);

        deleteBook(bookingPOJOLombok, id);
    }

    @Test
    public void testPutBook(){
        BookingPOJOLombok bookingPOJOLombokOriginal = BookingPOJOLombokBuilder.getBookingData();
        Response response = postBook(bookingPOJOLombokOriginal);
        String id = response.jsonPath().get("response.id").toString();

        BookingPOJOLombok bookingPOJOLombokUpdate = BookingPOJOLombokBuilder.getBookingData();
        Response responsePutBook = ApiKeyword.put(EndPointGlobal.EP_BOOK_MANAGEMENT +  "/" + id, bookingPOJOLombokUpdate);
        VerifyDataResponse.verifyBookData(responsePutBook,bookingPOJOLombokUpdate);

        deleteBook(bookingPOJOLombokUpdate, id);
    }

    @Test
    public void testPatchBook(){
        BookingPOJOLombok bookingPOJOLombokOriginal = BookingPOJOLombokBuilder.getBookingData();
        Response response = postBook(bookingPOJOLombokOriginal);
        String id = response.jsonPath().get("response.id").toString();

        BookingPOJOLombok bookingPOJOLombokUpdate = BookingPOJOLombokBuilder.getBookingData();
        Response responsePutBook = ApiKeyword.put(EndPointGlobal.EP_BOOK_MANAGEMENT +  "/" + id, bookingPOJOLombokUpdate);
        VerifyDataResponse.verifyBookData(responsePutBook,bookingPOJOLombokUpdate);
    }

    @Test
    public void testGetBookFindByStatus(){

        Response responseGetBookById = ApiKeyword.get(EndPointGlobal.EP_FIND_BOOK_MANAGEMENT_BY_STATUS, "status", true);
//        ApiKeyword.validateJsonSchema(response, );
    }

}
