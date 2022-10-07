package api.ru.jira;

import static io.restassured.RestAssured.given;

public class Steps {

    public static String getSessionId(String login, String password, String cookie){
        return given()
                .auth()
                .preemptive().basic(login, password)
                .when().post()
                .then().log().body()
                .extract().cookie(cookie);
    }
}
