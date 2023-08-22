package org.example.objects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.common.response.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponse extends BaseResponse {
    //coming from GET request with query params
    private CarResponse args;
    //coming from POST request
    private CarResponse json;
    private String data;

    private HeadersResponse headers;
    private String origin;
    private String url;

    public CarResponse getArgs() {
        return args;
    }

    public void setArgs(CarResponse args) {
        this.args = args;
    }

    public HeadersResponse getHeaders() {
        return headers;
    }

    public void setHeaders(HeadersResponse headers) {
        this.headers = headers;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CarResponse getJson() {
        return json;
    }

    public void setJson(CarResponse data) {
        this.json = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
