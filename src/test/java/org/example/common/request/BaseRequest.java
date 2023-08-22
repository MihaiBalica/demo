package org.example.common.request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BaseRequest {
    protected Response sendGetRequest(String url) {
        return get(url);
    }

    protected Response sendPostRequest(String url, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
    }

    protected Response sendPatchRequest(String url, String requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .patch(url);
    }
}
