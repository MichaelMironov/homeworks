package api.com.rickandmortyapi.steps;

import api.com.rickandmortyapi.pojo.characters.Person;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps {

    public static List<String> getCharacterEpisodes(JSONObject character) {

        return  given()
                .basePath("/character")
                .body(character)
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract()
                .jsonPath().getList("results[0].episode");

    }

    public static List<String> getCharactersOfEpisode(Integer episodeId){

        return  given()
                .basePath("/episode/"+episodeId)
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200).log().headers()
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
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract()
                .body().as(Person.class);
    }
}
