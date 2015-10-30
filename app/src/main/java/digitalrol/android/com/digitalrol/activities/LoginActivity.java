package digitalrol.android.com.digitalrol.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.prefs.Preferences;

import digitalrol.android.com.digitalrol.R;
import digitalrol.android.com.digitalrol.model.Constants;


public class LoginActivity extends Activity {

    Button btnIngresar;
    EditText txtUsuario, txtClave;
    Switch swRemember;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtClave = (EditText) findViewById(R.id.txtClave);
        swRemember = (Switch) findViewById(R.id.switch_remember);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        sharedPreferences = this.getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        swRemember.setChecked(sharedPreferences.getBoolean(Constants.REMEMBER_USERNAME,false));
        if (swRemember.isChecked())
        {
            txtUsuario.setText(sharedPreferences.getString(Constants.USERNAME,""));
        }

    }


    private void login()
    {
        String user= txtUsuario.getText().toString();
        String clave = txtClave.getText().toString();

        if (user.length()==0)
        {
            Toast.makeText(getApplicationContext(),getString(R.string.login_missing_user),Toast.LENGTH_SHORT).show();
            txtUsuario.requestFocus();
            return;
        }
        if (clave.length()==0)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.login_missing_clave), Toast.LENGTH_SHORT).show();
            txtClave.requestFocus();
            return;
        }

        callService(user,clave);
    }

    private void callService(String user, String clave)
    {
        loginSucces();
    }

    private void loginSucces()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.REMEMBER_USERNAME,swRemember.isChecked());

        if (swRemember.isChecked())
        {
            editor.putString(Constants.USERNAME,txtUsuario.getText().toString());
        }
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = intent.getExtras();
        intent.putExtra("UserName", txtUsuario.getText().toString());
        intent.putExtra("FromGoogle",false);
        intent.putExtra("UserImage","");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
