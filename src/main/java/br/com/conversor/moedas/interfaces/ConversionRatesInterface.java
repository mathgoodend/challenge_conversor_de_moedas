package br.com.conversor.moedas.interfaces;

import com.google.gson.JsonObject;

public interface ConversionRatesInterface {
    JsonObject getConversionRates();
    String getRate(String rate);
}
