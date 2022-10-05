package api.ru.jira;

import io.restassured.response.Response;
import utils.api.Specification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static api.ru.jira.Steps.getSessionId;
import static io.restassured.RestAssured.*;
import static java.util.Optional.empty;
import static org.hamcrest.core.IsNot.not;
import static utils.Configuration.getConfigurationValue;

public class JiraTest {

    public static String sessionID;
    public static final String BOARDS = getConfigurationValue("jiraBoardsEndpoint");

    @BeforeAll
    static void prepare() {

        baseURI = getConfigurationValue("jiraUrl");

        Specification.installSpecification(Specification.requestSpec(baseURI));

        sessionID = getSessionId(getConfigurationValue("user"), getConfigurationValue("password"), "JSESSIONID");

        Specification.installSpecification(Specification.requestSpecWithSession(baseURI, sessionID));

    }

    @Test
    void getTasksList() {
        Response tasksList =
                (Response)
                        when()
                                .get(BOARDS)
                                .then()
                                .body("results", not(empty()))
                                .extract().body();


        tasksList.getBody().prettyPrint();
    }
}
