package api.ru.jira;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static api.Specification.*;
import static io.restassured.RestAssured.*;
import static java.util.Optional.empty;
import static org.hamcrest.core.IsNot.not;
import static utils.configurations.Configuration.getConfigurationValue;

public class JiraTest {

    private static final String BOARDS = getConfigurationValue("jiraBoardsEndpoint");

    @BeforeAll
    static void prepare() {

        baseURI = getConfigurationValue("jiraUrl");

        installSpecification(requestSpec(baseURI));

        String sessionID = Steps.getSessionId(getConfigurationValue("user"), getConfigurationValue("password"), "JSESSIONID");

        installSpecification(requestSpecWithSession(baseURI, sessionID));

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


//        tasksList.getBody().prettyPrint();
    }
}
