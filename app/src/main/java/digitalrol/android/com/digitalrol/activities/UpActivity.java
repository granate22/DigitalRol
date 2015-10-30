package digitalrol.android.com.digitalrol.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.fragments.DashboardFragment;
import digitalrol.android.com.digitalrol.fragments.NewRolFragment;

/**
 * Created by diego.mazzitelli on 11/05/2015.
 */
public class UpActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NewRolFragment fragment = NewRolFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, NewRolFragment.TAG);
        ft.commit();
    }
}
