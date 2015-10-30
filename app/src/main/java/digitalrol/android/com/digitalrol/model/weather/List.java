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
        "dt",
        "main",
        "weather",
        "clouds",
        "wind",
        "sys",
        "dt_txt",
        "rain"
})
public class List {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("weather")
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("sys")
    private Sys_ sys;
    @JsonProperty("dt_txt")
    private String dtTxt;
    @JsonProperty("rain")
    private Rain rain;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The dt
     */
    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    /**
     *
     * @param dt
     * The dt
     */
    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     *
     * @return
     * The main
     */
    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    /**
     *
     * @param main
     * The main
     */
    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     *
     * @return
     * The weather
     */
    @JsonProperty("weather")
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     *
     * @param weather
     * The weather
     */
    @JsonProperty("weather")
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     *
     * @return
     * The clouds
     */
    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    /**
     *
     * @param clouds
     * The clouds
     */
    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     *
     * @return
     * The wind
     */
    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    /**
     *
     * @param wind
     * The wind
     */
    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     *
     * @return
     * The sys
     */
    @JsonProperty("sys")
    public Sys_ getSys() {
        return sys;
    }

    /**
     *
     * @param sys
     * The sys
     */
    @JsonProperty("sys")
    public void setSys(Sys_ sys) {
        this.sys = sys;
    }

    /**
     *
     * @return
     * The dtTxt
     */
    @JsonProperty("dt_txt")
    public String getDtTxt() {
        return dtTxt;
    }

    /**
     *
     * @param dtTxt
     * The dt_txt
     */
    @JsonProperty("dt_txt")
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    /**
     *
     * @return
     * The rain
     */
    @JsonProperty("rain")
    public Rain getRain() {
        return rain;
    }

    /**
     *
     * @param rain
     * The rain
     */
    @JsonProperty("rain")
    public void setRain(Rain rain) {
        this.rain = rain;
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

