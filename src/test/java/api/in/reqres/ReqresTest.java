package api.in.reqres;

import api.in.reqres.pojo.Potato;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static api.Specification.installSpecification;
import static api.Specification.requestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static utils.Configuration.getConfigurationValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReqresTest {

    private static String ID;
    private static Potato potato;

    @BeforeAll
    public static void prepare() {

        installSpecification(requestSpec(getConfigurationValue("reqresUrl")));

    }

    @Order(1)
    @Test
    void creatingTest() throws IOException {

        ObjectNode json = new ObjectMapper().readValue(new File("src/test/resources/json/potato.json"), ObjectNode.class);

        potato =
                given()
                        .body(json)
                        .basePath("/users")
                        .when().post()
                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().body().as(Potato.class);

        ID = potato.getId();

    }

    @Order(2)
    @Test
    void changingTest() {

        potato.setName("Tomato");
        potato.setJob("Eat Maket");

        given()
                .body(potato)
                .basePath("/users/" + ID)
                .when().put()
                .then().statusCode(200)
                .body("name", equalTo("Tomato"))
                .body("job", equalTo("Eat Maket"))
                .body("id", equalTo(ID));

    }

}
