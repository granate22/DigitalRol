package digitalrol.android.com.digitalrol.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.PendingRequestListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.activities.UpActivity;
import digitalrol.android.com.digitalrol.model.Constants;
import digitalrol.android.com.digitalrol.model.SpiceService;
import digitalrol.android.com.digitalrol.model.WindDirection;
import digitalrol.android.com.digitalrol.model.weather.Forecast;
import digitalrol.android.com.digitalrol.model.weather.ForecastRequest;
import digitalrol.android.com.digitalrol.model.weather.List;
import digitalrol.android.com.digitalrol.model.weather.OpenWeather;
import digitalrol.android.com.digitalrol.model.weather.OpenWeatherRequest;

/**
 * Created by diego.mazzitelli on 26/04/2015.
 */
public class DashboardFragment extends Fragment
{
    public static final String TAG = "DashboardFragment";
    private LinearLayout llNewRol;
    private LinearLayout llNewRol2;
    private TextView tvNewRol;
    private LinearLayout llWindActual, llWind3, llWind6, llWind9, llDivisor1, llDivisor2, llDivisor3;
    private ProgressBar progressBarActual, progressBarNext;
    private SpiceManager spiceManager = new SpiceManager(SpiceService.class);
    private String lastWeatherCacheKey, lastForecastCacheKey;
    private TextView tvActualSpeed, tvActualDirection, tv3Time, tv3Speed, tv3Direction;
    private TextView tv6Time, tv6Speed, tv6Direction, tv9Time, tv9Speed, tv9Direction;
    private ImageView imgActual, img3, img6, img9;
    private NumberFormat formatter = new DecimalFormat("#0.0");

    public static DashboardFragment newInstance()
    {
        DashboardFragment f = new DashboardFragment();
        return f;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop()
    {
        if (spiceManager.isStarted())
            spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getActivity().getString(R.string.app_name));
        getActivity().invalidateOptionsMenu();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        if (view!=null) {
            llNewRol = (LinearLayout) view.findViewById(R.id.ll_new_rol);
            llNewRol2 = (LinearLayout) view.findViewById(R.id.ll_new_rol2);
            tvNewRol = (TextView) view.findViewById(R.id.tv_new_rol);
            llNewRol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewRol();
                }
            });

            llNewRol2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llNewRol.performClick();
                }
            });

            tvNewRol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llNewRol.performClick();
                }
            });

            llDivisor1 = (LinearLayout) view.findViewById(R.id.divisor1);
            llDivisor2 = (LinearLayout) view.findViewById(R.id.divisor2);
            llDivisor3 = (LinearLayout) view.findViewById(R.id.divisor3);
            llWindActual = (LinearLayout) view.findViewById(R.id.ll_wind_actual);
            llWind3 = (LinearLayout) view.findViewById(R.id.ll_wind_3);
            llWind6 = (LinearLayout) view.findViewById(R.id.ll_wind_6);
            llWind9 = (LinearLayout) view.findViewById(R.id.ll_wind_9);
            progressBarActual = (ProgressBar) view.findViewById(R.id.actual_progressBar);
            progressBarNext = (ProgressBar) view.findViewById(R.id.next_progressBar);

            tvActualSpeed = (TextView) view.findViewById(R.id.wind_actual_speed);
            imgActual = (ImageView) view.findViewById(R.id.wind_actual_arrow);
            tvActualDirection = (TextView) view.findViewById(R.id.wind_actual_direction);
            tv3Time = (TextView) view.findViewById(R.id.wind_3_time);
            tv3Speed = (TextView) view.findViewById(R.id.wind_3_speed);
            img3 = (ImageView) view.findViewById(R.id.wind_3_arrow);
            tv3Direction = (TextView) view.findViewById(R.id.wind_3_direction);
            tv6Time = (TextView) view.findViewById(R.id.wind_6_time);
            tv6Speed = (TextView) view.findViewById(R.id.wind_6_speed);
            img6 = (ImageView) view.findViewById(R.id.wind_6_arrow);
            tv6Direction = (TextView) view.findViewById(R.id.wind_6_direction);
            tv9Time = (TextView) view.findViewById(R.id.wind_9_time);
            tv9Speed = (TextView) view.findViewById(R.id.wind_9_speed);
            img9 = (ImageView) view.findViewById(R.id.wind_9_arrow);
            tv9Direction = (TextView) view.findViewById(R.id.wind_9_direction);
        }

        callWindServices();

        return view;
    }

    private void callWindServices()
    {
        OpenWeatherRequest weatherRequest = new OpenWeatherRequest();
        lastWeatherCacheKey = weatherRequest.createCacheKey();
        spiceManager.execute(weatherRequest,lastWeatherCacheKey, DurationInMillis.ONE_HOUR,new OpenWeatherListener());

        ForecastRequest forecastRequest = new ForecastRequest();
        lastForecastCacheKey = forecastRequest.createCacheKey();
        spiceManager.execute(forecastRequest, lastForecastCacheKey, DurationInMillis.ONE_HOUR, new ForecastListener());
    }

    private void NewRol()
    {
        Intent intent = new Intent(getActivity(), UpActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        /*
        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, NewRolFragment.newInstance(), NewRolFragment.TAG).addToBackStack("NewRol");
        ft.commit();*/
    }

    public WindDirection getDirection(Double deg)
    {
        WindDirection windDirection = new WindDirection();
        if (deg<=22.5 || deg>337.5)
        {
            windDirection.setName("N");
            windDirection.setImage(R.drawable.arrow_n);
        }
        if (deg>22.5 && deg<=68.5)
        {
            windDirection.setName("NE");
            windDirection.setImage(R.drawable.arrow_ne);
        }
        if (deg>68.5 && deg<=113.5)
        {
            windDirection.setName("E");
            windDirection.setImage(R.drawable.arrow_e);
        }
        if (deg>113.5 && deg<=158.5)
        {
            windDirection.setName("SE");
            windDirection.setImage(R.drawable.arrow_se);
        }
        if (deg>158.5 && deg<=203.5)
        {
            windDirection.setName("S");
            windDirection.setImage(R.drawable.arrow_s);
        }
        if (deg>203.5 && deg<=248.5)
        {
            windDirection.setName("SO");
            windDirection.setImage(R.drawable.arrow_so);
        }
        if (deg>248.5 && deg<=293.5)
        {
            windDirection.setName("O");
            windDirection.setImage(R.drawable.arrow_o);
        }
        if (deg>293.5 && deg<=337.5)
        {
            windDirection.setName("NO");
            windDirection.setImage(R.drawable.arrow_no);
        }


        return windDirection;
    }

    class OpenWeatherListener implements PendingRequestListener<OpenWeather>
    {
        @Override
        public void onRequestNotFound() {

        }

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.d(spiceException.getMessage(), spiceException.getLocalizedMessage());
        }

        @Override
        public void onRequestSuccess(OpenWeather openWeather) {
            if (openWeather != null)
            {
                Double wind = openWeather.getWind().getSpeed();
                Double deg = openWeather.getWind().getDeg();
                WindDirection windDirection = getDirection(deg);
                Double windKilometers = wind* Constants.KILOMETERS_HOUR;


                tvActualSpeed.setText(formatter.format(windKilometers) + " " + Constants.KILOMETERS_HOUR_TEXT);
                imgActual.setImageResource(windDirection.getImage());
                tvActualDirection.setText(windDirection.getName());

                progressBarActual.setVisibility(View.GONE);
                llWindActual.setVisibility(View.VISIBLE);
                llDivisor1.setVisibility(View.VISIBLE);

            }

        }
    }

    class ForecastListener implements PendingRequestListener<Forecast> {
        @Override
        public void onRequestNotFound() {

        }

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.d(spiceException.getMessage(), spiceException.getLocalizedMessage());
        }

        @Override
        public void onRequestSuccess(Forecast forecast) {
            List data= null;
            if (forecast != null) {
                if (forecast.getList()!=null) {
                    progressBarNext.setVisibility(View.GONE);

                    data = forecast.getList().get(0);
                    if (data!=null)
                    {

                        Double wind = data.getWind().getSpeed();
                        Double deg = data.getWind().getDeg();
                        WindDirection windDirection = getDirection(deg);
                        Double windKilometers = wind* Constants.KILOMETERS_HOUR;
                        int hour = Integer.valueOf(data.getDtTxt().substring(11,13));
                        hour=hour-3;
                        if (hour<0)
                        {
                            hour=21;
                        }

                        tv3Time.setText(hour+" Hs:");
                        tv3Speed.setText(formatter.format(windKilometers) + " " + Constants.KILOMETERS_HOUR_TEXT);
                        img3.setImageResource(windDirection.getImage());
                        tv3Direction.setText(windDirection.getName());
                        llWind3.setVisibility(View.VISIBLE);

                    }

                    data = forecast.getList().get(1);
                    if (data!=null)
                    {

                        Double wind = data.getWind().getSpeed();
                        Double deg = data.getWind().getDeg();
                        WindDirection windDirection = getDirection(deg);
                        Double windKilometers = wind* Constants.KILOMETERS_HOUR;
                        int hour = Integer.valueOf(data.getDtTxt().substring(11,13));
                        hour=hour-3;
                        if (hour<0)
                        {
                            hour=21;
                        }

                        tv6Time.setText(hour+" Hs:");
                        tv6Speed.setText(formatter.format(windKilometers) + " " + Constants.KILOMETERS_HOUR_TEXT);
                        img6.setImageResource(windDirection.getImage());
                        tv6Direction.setText(windDirection.getName());
                        llDivisor2.setVisibility(View.VISIBLE);
                        llWind6.setVisibility(View.VISIBLE);

                    }

                    data = forecast.getList().get(2);
                    if (data!=null)
                    {

                        Double wind = data.getWind().getSpeed();
                        Double deg = data.getWind().getDeg();
                        WindDirection windDirection = getDirection(deg);
                        Double windKilometers = wind* Constants.KILOMETERS_HOUR;
                        int hour = Integer.valueOf(data.getDtTxt().substring(11,13));
                        hour=hour-3;
                        if (hour<0)
                        {
                            hour=21;
                        }

                        tv9Time.setText(hour+" Hs:");
                        tv9Speed.setText(formatter.format(windKilometers) + " " + Constants.KILOMETERS_HOUR_TEXT);
                        img9.setImageResource(windDirection.getImage());
                        tv9Direction.setText(windDirection.getName());
                        llDivisor3.setVisibility(View.VISIBLE);
                        llWind9.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }
}


