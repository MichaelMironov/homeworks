package api.oi.petstore;

import api.oi.petstore.pojo.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static api.Specification.installSpecification;
import static api.Specification.requestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;
import static utils.configurations.Configuration.getConfigurationValue;

@Tag("@api")
@Tag("@native")
public class PetStoreApiTest {

    @BeforeAll
    protected static void prepare() {
        installSpecification(requestSpec(getConfigurationValue("petstoreUrl")));
    }

    @Test
    void newPetAddingTest() {

        Pet pet = new Pet();
        pet.setName("Hatiko");
        pet.setStatus("waits");

        pet = given()
                .basePath("/pet")
                .body(pet)
                .when().post()
                .then().statusCode(200)
                .body(containsString("id"), notNullValue())
                .log().body()
                .extract().as(Pet.class);

        given()
                .when()
                .get("/pet/{id}", pet.getId())
                .then().statusCode(200)
                .log().body()
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Test
    void updatePetTest() throws JsonProcessingException {

        Pet pet = new Pet();
        pet.setName("Hatiko");
        pet.setStatus("waits");

        pet = given()
                .body(pet)
                .when().post("/pet")
                .then().statusCode(200)
                .log().body()
                .body(containsString("id"), notNullValue())
                .body("status", equalTo(pet.getStatus()))
                .extract().as(Pet.class);

        pet.setStatus("waited");
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(pet);

        given()
                .body(body)
                .when().put("/pet")
                .then().statusCode(200)
                .log().body()
                .body("id", not(empty()))
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }

    @Test
    void findPetByStatusTest() {

        ArrayList<String> soldPets =
                given()
                        .queryParam("status", "sold")
                        .when().get("/pet/findByStatus")
                        .then().statusCode(200)
                        .log().body()
                        .body("status", hasItems("sold"))
                        .extract().body().jsonPath().get();

        Assertions.assertNotNull(soldPets);
    }


    @Test
    void logoutTest() {

        Response response = given()
                .when()
                .get("user/logout")
                .then()
                .body("message", equalTo("ok"))
                .statusCode(200)
                .extract().response();

        Assertions.assertEquals(200,response.statusCode());
    }
}
