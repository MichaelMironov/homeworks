package ru.ifellow.api;

import ru.ifellow.api.model.RequestModel;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.ifellow.api.context.ContextHolder;
import ru.ifellow.utils.FileUtil;
import ru.ifellow.utils.RegexUtil;
import ru.ifellow.utils.configurations.RestConfiguration;

import java.net.URI;
import java.util.Map;

import static io.restassured.RestAssured.given;

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
        this.path = ContextHolder.replaceVarsIfPresent(requestModel.getPath());
        this.method = Method.valueOf(requestModel.getMethod());
        this.body = requestModel.getBody();
        this.fullUrl = ContextHolder.replaceVarsIfPresent(requestModel.getUrl());

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

    public void setHeaders(Map<String, String> headers) {
        headers.forEach((k, v) -> {
            builder.addHeader(k, v);
        });
    }

    public void setQuery(Map<String, String> query) {
        query.forEach((k, v) -> {
            builder.addQueryParam(k, v);
        });
    }

    public void sendRequest() {
        RequestSpecification requestSpecification = builder.build();

        this.response = given()
                .spec(requestSpecification)
                .request(method);
    }

    private void setBodyFromFile() {
        if (body != null && RegexUtil.getMatch(body, ".*\\.json")) {
            body = ContextHolder.replaceVarsIfPresent(FileUtil.readBodyFromJsonDir(body));
            builder.setBody(body);
        }
    }

}
