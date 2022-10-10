package api;

import api.loggers.RestAssuredLogger;
import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specification {
    public static RequestSpecification requestSpec(String url){

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addFilter(new SwaggerCoverageRestAssured())
                .addFilter(new RestAssuredLogger())
                .build();
    }

    public static RequestSpecification requestSpecWithSession(String url, String sessionId){

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setSessionId(sessionId)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addFilter(new RestAssuredLogger())
                .build();
    }


    public static void installSpecification(RequestSpecification request){
        RestAssured.requestSpecification = request;
    }

}
