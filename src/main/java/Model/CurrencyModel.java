package Model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonAutoDetect
public class CurrencyModel {

    @JsonProperty("disclaimer")
    private String disclaimer;

    @JsonProperty("license")
    private String license;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("base")
    private String base;

    @JsonProperty
    private Map<String, Double> rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}