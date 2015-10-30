package digitalrol.android.com.digitalrol.model.weather;

import android.net.Uri;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.web.client.RestTemplate;

/**
 * Created by diego.mazzitelli on 23/03/2015.
 */
public class OpenWeatherRequest extends SpringAndroidSpiceRequest<OpenWeather> {

    public OpenWeatherRequest() {
        super(OpenWeather.class);
    }

    @Override
    public OpenWeather loadDataFromNetwork() throws Exception {
        Uri.Builder uriBuilder = Uri.parse("http://api.openweathermap.org/data/2.5/weather").buildUpon();

        uriBuilder.appendQueryParameter("id","3430863");
        uriBuilder.appendQueryParameter("units","metric");
        uriBuilder.appendQueryParameter("lang","es");

        String url = uriBuilder.build().toString();
        RestTemplate restTemplate = getRestTemplate();
        OpenWeather map = restTemplate.getForObject(url, OpenWeather.class);

        return map;

    }

    public String createCacheKey() {
        return "symbol.WIND" ;
    }
}
