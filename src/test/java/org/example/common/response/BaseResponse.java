package org.example.common.response;


import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class BaseResponse {

    public BaseResponse() {}
    public BaseResponse(int code, String message) {
        this.returnedStatusCode = code;
        this.errorMessage = message;
    }

    private int returnedStatusCode;
    private String errorMessage;

    public int getReturnedStatusCode() {
        return returnedStatusCode;
    }

    public void setReturnedStatusCode(int returnedStatusCode) {
        this.returnedStatusCode = returnedStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void verifyStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public int returnStatusCode(Response response) {
        return response.getStatusCode();
    }

    public String returnHeader(Response response, String headerName) {
        return response.getHeader(headerName);
    }

    public void verifyHeader(Response response, String headerName, String expectedValue) {
        response.then().header(headerName, expectedValue);
    }

    protected void verifyJsonKeyWithValue(Response response, String jsonKey, String expectedValue) {
        response.then().body(jsonKey, equalTo(expectedValue));
    }

    protected void verifyJsonKeyWithValue(Response response, String jsonKey, int expectedValue) {
        response.then().body(jsonKey, equalTo(expectedValue));
    }
}
