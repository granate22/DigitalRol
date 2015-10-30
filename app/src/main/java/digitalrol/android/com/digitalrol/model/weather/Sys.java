package digitalrol.android.com.digitalrol.model.weather;

/**
 * Created by diego.mazzitelli on 23/03/2015.
 */
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
        "population"
})
public class Sys {

    @JsonProperty("population")
    private Integer population;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The population
     */
    @JsonProperty("population")
    public Integer getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     * The population
     */
    @JsonProperty("population")
    public void setPopulation(Integer population) {
        this.population = population;
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