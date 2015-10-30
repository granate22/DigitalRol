package digitalrol.android.com.digitalrol.model.weather;

/**
 * Created by diego.mazzitelli on 02/08/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "city",
        "cod",
        "message",
        "cnt",
        "list"
})
public class Forecast {

    @JsonProperty("city")
    private City city;
    @JsonProperty("cod")
    private String cod;
    @JsonProperty("message")
    private Double message;
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("list")
    private java.util.List<digitalrol.android.com.digitalrol.model.weather.List> list = new ArrayList<digitalrol.android.com.digitalrol.model.weather.List>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The city
     */
    @JsonProperty("city")
    public City getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    @JsonProperty("city")
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The cod
     */
    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    /**
     *
     * @param cod
     * The cod
     */
    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     *
     * @return
     * The message
     */
    @JsonProperty("message")
    public Double getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    @JsonProperty("message")
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The cnt
     */
    @JsonProperty("cnt")
    public Integer getCnt() {
        return cnt;
    }

    /**
     *
     * @param cnt
     * The cnt
     */
    @JsonProperty("cnt")
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     *
     * @return
     * The list
     */
    @JsonProperty("list")
    public java.util.List<digitalrol.android.com.digitalrol.model.weather.List> getList() {
        return list;
    }

    /**
     *
     * @param list
     * The list
     */
    @JsonProperty("list")
    public void setList(java.util.List<digitalrol.android.com.digitalrol.model.weather.List> list) {
        this.list = list;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
