package org.example.tests;

import org.example.common.RequestFailedException;
import org.example.common.logger.TestLogger;
import org.example.common.response.BaseResponse;
import org.example.objects.CarRequest;
import org.example.objects.GetResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HttpMethodsTest extends BaseTest {
    private final TestLogger logger = new TestLogger(HttpMethodsTest.class);

    @DisplayName("GET request test with no params")
    @Test
    public void testEmptyGetRequest() {
        logger.info("Sending empty GET request");
        BaseResponse baseResponse = sendGetRequest(BaseResponse.class);
        assertEquals(200, baseResponse.returnStatusCode(response), "Returned Status Code should be 200 OK");
        assertEquals("application/json", baseResponse.returnHeader(response, "Content-Type"), "Content-Type should contain application/json");
        logger.info("Body content for an empty GET request" + response.getBody().asString());
    }

    @DisplayName("GET request test with Query Parameters")
    @Test
    public void testGetRequestWithQueryParams() {
        String queryParameters = "car=Ford,fuel=Diesel";
        logger.info("Sending GET request with Query Params");

        try {
            GetResponse getResponse = sendGetRequest(queryParameters, GetResponse.class);
            assertNotNull(getResponse);
            assertEquals(200, getResponse.returnStatusCode(response), "Returned Status Code should be 200 OK");
            assertEquals("application/json", getResponse.returnHeader(response, "Content-Type"), "Content-Type should contain application/json");

            logger.info("Extracted data from the response JSON will follow");
            logger.info("--> url: " + getResponse.getUrl());
            logger.info("--> origin: " + getResponse.getOrigin());
            logger.info("--> args.car: " + getResponse.getArgs().getCar());
            logger.info("--> args.fuel: " + getResponse.getArgs().getFuel());
            logger.info("--> headers.Accept: " + getResponse.getHeaders().getAccept());
            logger.info("--> headers.Accept-Encoding, headers.Host and headers.User-Agent and X-Amzn-Trace-Id are ignored");

            assertEquals("*/*", getResponse.getHeaders().getAccept(), "Incorrect header \"Accept\"");
            assertTrue(baseUrl.contains(getResponse.getHeaders().getHost()));
            assertEquals("Ford", getResponse.getArgs().getCar(), "Incorrect car manufacturer");
            assertEquals("Diesel", getResponse.getArgs().getFuel(), "Incorrect fuel");
            assertEquals(baseUrl + "get?car=Ford&fuel=Diesel", getResponse.getUrl(), "url should exist and should contain " + baseUrl + "get?car=Ford&fuel=Diesel");
            assertFalse(getResponse.getOrigin().isEmpty(), "origin should contain something");
        } catch (RequestFailedException e) {
            fail("Error message: " + e.getMessage());
        }
    }

    @DisplayName("POST request test with JSON body")
    @Test
    public void testPostRequest() {
        CarRequest carRequest = new CarRequest("BMW", "EV");
        try {
            GetResponse getResponse = sendPostRequest(carRequest, GetResponse.class);
            assertNotNull(getResponse);
            assertEquals(200, getResponse.returnStatusCode(response), "Returned Status Code should be 200 OK");
            assertEquals("application/json", getResponse.returnHeader(response, "Content-Type"), "Content-Type should contain application/json");
            logger.info(response.getBody().asString());
            logger.info("Extracted data from the response JSON will follow");
            logger.info("--> url: " + getResponse.getUrl());
            logger.info("--> origin: " + getResponse.getOrigin());
            logger.info("--> args.car (should be empty): " + getResponse.getArgs().getCar());
            logger.info("--> args.fuel (should be empty): " + getResponse.getArgs().getFuel());
            logger.info("--> headers.Accept: " + getResponse.getHeaders().getAccept());
            logger.info("--> headers.Accept-Encoding, headers.Host and headers.User-Agent and X-Amzn-Trace-Id are ignored");
            logger.info("--> data (a string representation of the JSON that was sent): " + getResponse.getData());
            logger.info("--> json.car: " + getResponse.getJson().getCar());
            logger.info("--> json.fuel: " + getResponse.getJson().getFuel());

            assertNull(getResponse.getArgs().getCar(), "args should not contain car");
            assertNull(getResponse.getArgs().getFuel(), "args should not contain fuel");
            assertEquals("{\"car\":\"BMW\",\"fuel\":\"EV\"}", getResponse.getData());
            assertEquals("*/*", getResponse.getHeaders().getAccept(), "Incorrect header \"Accept\"");
            assertTrue(baseUrl.contains(getResponse.getHeaders().getHost()));
            assertEquals("BMW", getResponse.getJson().getCar(), "Incorrect car manufacturer");
            assertEquals("EV", getResponse.getJson().getFuel(), "Incorrect fuel");
            assertEquals(baseUrl + "post", getResponse.getUrl(), "url should exist and should contain " + baseUrl + "post");
            assertFalse(getResponse.getOrigin().isEmpty(), "origin should contain something");

        } catch (RequestFailedException e) {
            fail("Error message: " + e.getMessage());
        }
    }

    @DisplayName("PATCH request test with JSON body")
    @Test
    public void testPatchRequest() {
        CarRequest carRequest = new CarRequest("BMW", "Diesel");
        try {
            GetResponse getResponse = sendPostRequest(carRequest, GetResponse.class);
            assertNotNull(getResponse);
            assertEquals(200, getResponse.returnStatusCode(response), "Returned Status Code should be 200 OK");
            assertEquals("application/json", getResponse.returnHeader(response, "Content-Type"), "Content-Type should contain application/json");
            logger.info(response.getBody().asString());
            logger.info("Extracted data from the response JSON will follow");
            logger.info("--> url: " + getResponse.getUrl());
            logger.info("--> origin: " + getResponse.getOrigin());
            logger.info("--> args.car (should be empty): " + getResponse.getArgs().getCar());
            logger.info("--> args.fuel (should be empty): " + getResponse.getArgs().getFuel());
            logger.info("--> headers.Accept: " + getResponse.getHeaders().getAccept());
            logger.info("--> headers.Accept-Encoding, headers.Host and headers.User-Agent and X-Amzn-Trace-Id are ignored");
            logger.info("--> data (a string representation of the JSON that was sent): " + getResponse.getData());
            logger.info("--> json.car: " + getResponse.getJson().getCar());
            logger.info("--> json.fuel: " + getResponse.getJson().getFuel());

            assertNull(getResponse.getArgs().getCar(), "args should not contain car");
            assertNull(getResponse.getArgs().getFuel(), "args should not contain fuel");
            assertEquals("{\"car\":\"BMW\",\"fuel\":\"Diesel\"}", getResponse.getData());
            assertEquals("*/*", getResponse.getHeaders().getAccept(), "Incorrect header \"Accept\"");
            assertTrue(baseUrl.contains(getResponse.getHeaders().getHost()));
            assertEquals("BMW", getResponse.getJson().getCar(), "Incorrect car manufacturer");
            assertEquals("Diesel", getResponse.getJson().getFuel(), "Incorrect fuel");
            assertEquals(baseUrl + "post", getResponse.getUrl(), "url should exist and should contain " + baseUrl + "post");
            assertFalse(getResponse.getOrigin().isEmpty(), "origin should contain something");

        } catch (RequestFailedException e) {
            fail("Error message: " + e.getMessage());
        }
    }
}
