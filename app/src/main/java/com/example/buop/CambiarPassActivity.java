package com.example.buop;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CambiarPassActivity extends AppCompatActivity {

    private static final String TAG = "";
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText etxtNewEmail;
    private EditText etxtNewPassword;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);


        etxtNewEmail = (EditText) findViewById(R.id.etxtNewEmail);
        etxtNewPassword = (EditText) findViewById(R.id.etxtNewPassword);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
           @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                 if (user == null) {
                      Intent intent = new Intent(CambiarPassActivity.this, MainActivity.class);
                     intent.putExtra("Yendo a la página principal", true);
                     startActivity(intent);
                     finish();
                 } else {

                     etxtNewEmail.setText(user.getEmail());
                 }
             }
          };
    }

    public void sendPasswordResetEmail(View view) {
        String emailAddress = etxtNewEmail.getText().toString();
        mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CambiarPassActivity.this, "Se ha enviado ya un correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserProviderProfileInfo(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();

                Toast.makeText(this, "id : " + providerId + ", uid : " + uid
                        + " email : " + email, Toast.LENGTH_SHORT).show();
            };
        }
    }



    public void setUserEmailAddr(View view) {
        String newEmail = etxtNewEmail.getText().toString();
        if (TextUtils.isEmpty(newEmail))
            return;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(CambiarPassActivity.this, "Email actualizado", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setUserPassword(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        String newPassword = etxtNewPassword.getText().toString();
        if (TextUtils.isEmpty(newPassword))
            return;

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(CambiarPassActivity.this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void userVerification(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CambiarPassActivity.this, "Email enviado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteUser(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(CambiarPassActivity.this, "Usuario borrado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signOut(View view) {
        mAuth.signOut();
    }
}
