package org.cascomio.springbatchexample.parsers;

import java.util.Map;

public class ParserPayload {
    private String url;
    private String method = "GET";
    private Map<String, String> data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
