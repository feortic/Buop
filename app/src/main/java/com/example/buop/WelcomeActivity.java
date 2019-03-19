package com.example.buop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String user="names";
    TextView txtUser;
    private Button botonActualizarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        txtUser =(TextView)findViewById(R.id.textser);
        String user = getIntent().getStringExtra("names");
        txtUser.setText("¡Bienvenido "+ user +"!");

        botonActualizarPerfil = (Button) findViewById(R.id.btnActualizarPerfil);
        botonActualizarPerfil.setOnClickListener(this);
    }

    private  void irActualizarDatos() {
        Toast.makeText(WelcomeActivity.this, "Yendo a cambiar contraseña: ", Toast.LENGTH_LONG).show();
        Intent intencion = new Intent(getApplicationContext(), CambiarPassActivity.class);
        startActivity(intencion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnActualizarPerfil:
                //Invocamos al m彋odo:
                irActualizarDatos();
                break;


        }
    }
}
