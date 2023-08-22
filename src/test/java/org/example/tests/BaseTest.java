package org.example.tests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.common.RequestFailedException;
import org.example.common.logger.TestLogger;
import org.example.common.response.BaseResponse;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected static String baseUrl;
    private static final TestLogger logger = new TestLogger(BaseTest.class);
    protected final Gson gson = new Gson();
    protected Response response;

    @BeforeAll
    public static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Properties properties = new Properties();
        try (InputStream inputStream = BaseTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputStream);
            baseUrl = properties.getProperty("base.url");
            logger.info("Using baseURL: " + baseUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI = baseUrl;
    }

    private static Map<String, String> parseQueryParams(String queryParamString) {
        Map<String, String> map = new HashMap<>();
        String[] keyValuePairs = queryParamString.split(",");

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        return map;
    }

    public void sendGetRequest() {
        response = RestAssured.get(baseUrl + "/get");
    }

    public <T> T sendGetRequest(Class<T> responseClass) {
        response = RestAssured.get(baseUrl + "/get");
        return gson.fromJson(response.getBody().asString(), responseClass);
    }

    public void sendGetRequest(String queryParams) {
        response = RestAssured.given().queryParams(parseQueryParams(queryParams)).get(baseUrl + "/get");
    }

    public <T> T sendGetRequest(String queryParams, Class<T> responseClass) throws RequestFailedException {
        try {
            response = RestAssured.given().queryParams(parseQueryParams(queryParams)).get(baseUrl + "/get");
            return gson.fromJson(response.getBody().asString(), responseClass);
        } catch (Exception e) {
            throw new RequestFailedException("Failed to send GET request or parse response.", e);
        }
    }

    public <T> T sendPostRequest(Object requestClass, Class<T> responseClass) throws RequestFailedException {
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(gson.toJson(requestClass)).post(baseUrl + "/post");
            return gson.fromJson(response.getBody().asString(), responseClass);
        } catch (Exception e) {
            throw new RequestFailedException("Failed to send GET request or parse response.", e);
        }
    }

    public <T> T sendPatchRequest(Object requestClass, Class<T> responseClass) throws RequestFailedException {
        try {
            response = RestAssured.given().contentType(ContentType.JSON).body(gson.toJson(requestClass)).patch(baseUrl + "/patch");
            return gson.fromJson(response.getBody().asString(), responseClass);
        } catch (Exception e) {
            throw new RequestFailedException("Failed to send GET request or parse response.", e);
        }
    }

}
