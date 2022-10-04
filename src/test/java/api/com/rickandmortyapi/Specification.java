package api.com.rickandmortyapi.spec;

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
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification requestSpecWithHeader(String url, String header){

        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", header)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static void installSpecification(RequestSpecification request){
        RestAssured.requestSpecification = request;
    }

}
