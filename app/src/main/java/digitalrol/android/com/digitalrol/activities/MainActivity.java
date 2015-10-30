package digitalrol.android.com.digitalrol.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.fragments.BrujulaFragment;
import digitalrol.android.com.digitalrol.fragments.DashboardFragment;
import digitalrol.android.com.digitalrol.fragments.ListadoFragment;
import digitalrol.android.com.digitalrol.fragments.NewRolFragment;
import digitalrol.android.com.digitalrol.model.weather.Main;

/**
 * Created by diego.mazzitelli on 19/04/2015.
 */
public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView tvuserName;
    private ImageView imgUserImage;
    private static String userName;
    private static String userImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            userName = getIntent().getExtras().getString("UserName", "");
            boolean fromGoogle = getIntent().getExtras().getBoolean("FromGoogle");
            if (fromGoogle) {
                Toast.makeText(getApplicationContext(), "Sesión iniciada con Google desde el usuario: " + userName, Toast.LENGTH_LONG).show();
            }
            userImage = getIntent().getExtras().getString("UserImage","");
        }
        catch (Exception e)
        {

        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.inicio:
                        changeFragment(DashboardFragment.newInstance());
                        return true;
                    case R.id.rol_nuevo:
                        newRol();
                        return true;
                    case R.id.listado:
                        changeFragment(ListadoFragment.newInstance());
                        return true;
                    case R.id.brujula:
                        changeFragment(BrujulaFragment.newInstance());
                        return true;
                    case R.id.mapas:
                        showMap();
                        return true;
                    case R.id.logout:
                        SplashActivity.logOutGoogle = true;
                        finish();
                        return true;
                    default:
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view

        tvuserName = (TextView) findViewById(R.id.user_name);
        tvuserName.setText(userName);

        imgUserImage = (ImageView) findViewById(R.id.circleView);
        if (userImage.length()>0)
            Picasso.with(this).load(userImage).into(imgUserImage);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        changeFragment(DashboardFragment.newInstance());

    }

    private void newRol()
    {
        Intent intent = new Intent(this, UpActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }

    private void changeFragment(Fragment f)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, f, NewRolFragment.TAG);
        ft.commit();
    }

    private void showMap()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void onBackPressed() {

    }
}
