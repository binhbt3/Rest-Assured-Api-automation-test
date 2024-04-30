package keywords;

import globals.ConfigsGlobal;
import globals.TokenGlobal;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
    public static RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigsGlobal.BASE_URI)
                .setBasePath(ConfigsGlobal.BASE_PATH)
                .addHeader("Authorization" , "Bearer " + TokenGlobal.TOKEN)
                .setContentType(ConfigsGlobal.CONTENT_TYPE)
                .setAccept(ConfigsGlobal.CONTENT_TYPE)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecBuilder(){
        return new ResponseSpecBuilder()
                .expectContentType(ConfigsGlobal.CONTENT_TYPE)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getSpecificationNotAuth(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigsGlobal.BASE_URI)
                .setBasePath(ConfigsGlobal.BASE_PATH)
                .setContentType(ConfigsGlobal.CONTENT_TYPE)
                .setAccept(ConfigsGlobal.CONTENT_TYPE)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .log(LogDetail.ALL)
                .build();
    }

}
