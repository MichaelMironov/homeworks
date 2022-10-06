package utils.api;

import io.qameta.allure.Allure;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.IOUtils;
import utils.FileUtil;
import utils.JsonUtil;
import utils.RegexUtil;
import utils.RestConfiguration;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.api.ContextHolder.replaceVarsIfPresent;

public class ApiRequest {

    private final static RestConfiguration CONFIGURATIONS = ConfigFactory.create(RestConfiguration.class,
            System.getProperties(),
            System.getenv());

    private String baseUrl;
    private String path;
    private Method method;
    private String body;
    private String fullUrl;
    private Response response;

    private RequestSpecBuilder builder;

    public ApiRequest(RequestModel requestModel) {
        this.builder = new RequestSpecBuilder();

        this.baseUrl = CONFIGURATIONS.getBaseUrl();
        this.path = replaceVarsIfPresent(requestModel.getPath());
        this.method = Method.valueOf(requestModel.getMethod());
        this.body = requestModel.getBody();
        this.fullUrl = replaceVarsIfPresent(requestModel.getUrl());

        URI uri;

        if (!fullUrl.isEmpty()) {
            uri = URI.create(fullUrl.replace(" ", "+"));
        } else {
            uri = URI.create(baseUrl);
            builder.setBasePath(path);
        }

        this.builder.setBaseUri(uri);
        setBodyFromFile();
    }

    public Response getResponse() {
        return response;
    }

    /**
     * Установка заголовков
     */
    public void setHeaders(Map<String, String> headers) {
        headers.forEach((k, v) -> {
            builder.addHeader(k, v);
        });
    }

    /**
     * Установка query-параметров
     */
    public void setQuery(Map<String, String> query) {
        query.forEach((k, v) -> {
            builder.addQueryParam(k, v);
        });
    }

    /**
     * Отправляет сформированный запрос
     */
    public void sendRequest() {
        RequestSpecification requestSpecification = builder.build();

        Response response = given()
                .spec(requestSpecification)
                .request(method);

        attachRequestResponseToAllure(response, body);
        this.response = response;
    }

    /**
     * Установка тела запроса из файла
     */
    private void setBodyFromFile() {
        if (body != null && RegexUtil.getMatch(body, ".*\\.json")) {
            body = replaceVarsIfPresent(FileUtil.readBodyFromJsonDir(body));
            builder.setBody(body);
        }
    }

    /**
     * Добавление тела запроса и тела ответа в шаг отправки запроса
     */
    private void attachRequestResponseToAllure(Response response, String requestBody) {
        if (requestBody != null) {
            Allure.addAttachment(
                    "Request",
                    "application/json",
                    IOUtils.toInputStream(requestBody, StandardCharsets.UTF_8),
                    ".txt");
        }
        String responseBody = JsonUtil.jsonToUtf(response.body().prettyPrint());
        Allure.addAttachment("Response", "application/json", responseBody, ".txt");
    }

}
