package ru.ifellow.api.com.rickandmortyapi;

import ru.ifellow.api.com.rickandmortyapi.pojo.characters.Person;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps {

    public static Person getCharacterByName(String name){
        return  given()
                .basePath("/character")
                .queryParam("name",name)
                .when().get()
                .then().statusCode(200)
                .log().headers()
                .extract().body().jsonPath().getObject("results[0]", Person.class);
    }

    public static List<String> getCharacterEpisodes(ObjectNode character) {

        return  given()
                .basePath("/character")
                .body(character)
                .when().get()
                .then().statusCode(200)
                .extract()
                .jsonPath().getList("results[0].episode");

    }

    public static List<String> getCharactersOfEpisode(Integer episodeId){

        return  given()
                .basePath("/episode/"+episodeId)
                .when().get()
                .then().statusCode(200)
                .log().body()
                .extract()
                .jsonPath().getList("characters");
    }

    public static int getId(String title){
        return Integer.parseInt(Arrays.stream(title.split("/"))
                .reduce((first, second) -> second).get());
    }

    public static Person getCharacterById(int id){

        return  given()
                .basePath("/character/"+id)
                .when().get()
                .then().statusCode(200)
                .extract()
                .body().as(Person.class);
    }
}
