package digitalrol.android.com.digitalrol.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import digitalrol.android.com.digitalrol.R;

/**
 * Created by diego.mazzitelli on 02/08/2015.
 */
public class SplashActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    public static boolean logOutGoogle = false;

    Button btnLogin;
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;
    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    @Override
    public void onStart()
    {
        super.onStart();
        if (!logOutGoogle)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();

        findViewById(R.id.btn_google).setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();
        logOutGoogle = false;

        // Show a message to the user that we are signing in.
        //mStatusTextView.setText(R.string.signing_in);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("G+", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            if (!logOutGoogle)
                mGoogleApiClient.connect();
        }
    }

    private void goToLogin()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (logOutGoogle)
        {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                mGoogleApiClient.disconnect();
            }

        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d("G+", "onConnected:" + bundle);
        mShouldResolve = false;

        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        if (currentPerson != null) {
            Intent intent = new Intent(this, MainActivity.class);
            String name = currentPerson.getDisplayName();
            String image = currentPerson.getImage().getUrl();
            intent.putExtra("UserName", name);
            intent.putExtra("FromGoogle",true);
            intent.putExtra("UserImage",image);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d("G+", "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("G+", "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    if (!logOutGoogle)
                        mGoogleApiClient.connect();
                }
            } else {
                //showErrorDialog(connectionResult);
            }
        } else {
            //showSignedOutUI();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_google) {
            onSignInClicked();
        }
    }
}
