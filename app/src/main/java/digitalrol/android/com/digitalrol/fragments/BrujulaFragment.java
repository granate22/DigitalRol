package digitalrol.android.com.digitalrol.fragments;

import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import digitalrol.android.com.digitalrol.R;

/**
 * Created by diego.mazzitelli on 28/06/2015.
 */
public class BrujulaFragment extends Fragment implements SensorEventListener {

    private ImageView image;
    private float currentDegree = 0f;
    private SensorManager mSensorManager;
    private TextView tvHeading;

    public static BrujulaFragment newInstance()
    {
        return new BrujulaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brujula_fragment,container,false);

        image = (ImageView) view.findViewById(R.id.imageViewCompass);
        tvHeading = (TextView) view.findViewById(R.id.tvHeading);

        mSensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getActivity().getString(R.string.brujula_title));
        getActivity().invalidateOptionsMenu();
        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        tvHeading.setText("Apuntando a: " + Float.toString(degree) + " grados");

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        ra.setDuration(210);
        ra.setFillAfter(true);

        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
}
