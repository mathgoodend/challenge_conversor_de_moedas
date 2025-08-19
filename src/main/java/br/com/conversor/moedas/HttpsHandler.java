package br.com.conversor.moedas;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpsHandler {
    private HttpClient client;
    private HttpRequest request;
    private final String baseURI;

    public HttpsHandler(String key) {
        this.connect();
        this.baseURI = "https://v6.exchangerate-api.com/v6/%s/latest/".formatted(key);
    }

    public void connect() {
        try {
            this.client = HttpClient.newHttpClient();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void build(String uri) {
        try {
            this.request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> send() {
        this.connect();
        try {
            return getClient().send(this.getRequest(), HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HttpClient getClient() {
        return this.client;
    }

    public HttpRequest getRequest() {
        return this.request;
    }

    public String getBaseURI() {
        return this.baseURI;
    }
}
