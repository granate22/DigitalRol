package digitalrol.android.com.digitalrol.model.weather;

import android.net.Uri;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.web.client.RestTemplate;

/**
 * Created by diego.mazzitelli on 02/08/2015.
 */
public class ForecastRequest extends SpringAndroidSpiceRequest<Forecast> {

    public ForecastRequest() {
        super(Forecast.class);
    }

    @Override
    public Forecast loadDataFromNetwork() throws Exception {
        Uri.Builder uriBuilder = Uri.parse("http://api.openweathermap.org/data/2.5/forecast").buildUpon();

        uriBuilder.appendQueryParameter("id","3430863");
        uriBuilder.appendQueryParameter("units","metric");
        uriBuilder.appendQueryParameter("lang","es");

        String url = uriBuilder.build().toString();
        RestTemplate restTemplate = getRestTemplate();
        Forecast forecast = restTemplate.getForObject(url, Forecast.class);

        return forecast;

    }

    public String createCacheKey() {
        return "symbol.FORECAST" ;
    }

}
