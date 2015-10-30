package digitalrol.android.com.digitalrol.model.weather;

/**
 * Created by diego.mazzitelli on 02/08/2015.
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
        "pod"
})
public class Sys_ {

    @JsonProperty("pod")
    private String pod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The pod
     */
    @JsonProperty("pod")
    public String getPod() {
        return pod;
    }

    /**
     *
     * @param pod
     * The pod
     */
    @JsonProperty("pod")
    public void setPod(String pod) {
        this.pod = pod;
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
