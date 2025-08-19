package br.com.conversor.moedas;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Conversor {
    private final HttpsHandler connection;
    private final JsonHandler json;

    public Conversor(HttpsHandler conn) {
        this.connection = conn;
        this.json = new JsonHandler("{}");
    }

    public void setJson(String value) {
        this.json.setData(value);
    }

    public BigDecimal[] convertCurrency(String code, String val) {
        String uri = "%s%s".formatted(this.connection.getBaseURI(), code);
        this.connection.build(uri);
        setJson(this.connection.send().body());

        BigDecimal value = new BigDecimal(val);
        BigDecimal ratio = new BigDecimal(this.json.getRate("BRL")) ;
        BigDecimal convertedValue = ratio.multiply(value).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal[] result = {convertedValue, ratio};

        return result;
    }
}
