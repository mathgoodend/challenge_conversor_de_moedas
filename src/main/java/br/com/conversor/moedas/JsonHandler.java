package br.com.conversor.moedas;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHandler {
    public JsonObject data;

    // When instantiating a new handler, recieves an string and transforms into JsonObject
    public JsonHandler(String json) {
        this.data = JsonParser.parseString(json).getAsJsonObject();
    }

    public void setData(String json) {
        this.data = JsonParser.parseString(json).getAsJsonObject();
    }

    public JsonObject getData() {
        return this.data;
    }

    // Get a specific key from a JsonObject
    public JsonObject getAsJson(String key) {
        return this.getData().get(key).getAsJsonObject();
    }

    public String getAsString(String key) {
        return this.getData().get(key).getAsString();
    }

    public JsonObject getConversionRates() {
        return this.getAsJson("conversion_rates");
    }

    public String getRate(String rate) {
        return this.getConversionRates().get(rate).getAsString();
    }
}
