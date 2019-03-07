package com.example.buop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PruebaBD extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    TextView txtUsuario;
    TextView txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_bd);
        txtUsuario= (TextView) findViewById(R.id.txvUsuario);
        txtPass= (TextView) findViewById(R.id.txvPassword);
    }

    private void addUser(String idBD,String user, String pass){

        mDatabase.child("usuarios").child(idBD).child("username").setValue(user);
        mDatabase.child("usuarios").child(idBD).child("password").setValue(pass);
    }

    public void EnviarABD(View view) {
        addUser("45943285",(String) txtUsuario.getText(),(String)txtPass.getText());
    }
}
