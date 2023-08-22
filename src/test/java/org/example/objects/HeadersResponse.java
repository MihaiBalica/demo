package org.example.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadersResponse {
    private String Host;
    private String Accept;


    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getAccept() {
        return Accept;
    }

    public void setAccept(String accept) {
        Accept = accept;
    }
}
