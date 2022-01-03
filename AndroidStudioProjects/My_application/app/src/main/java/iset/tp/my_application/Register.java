package iset.tp.my_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText RegisterFullName,registerEmail,RegisterPassword,registerConfpass;
    Button registerUserBtn , gotoLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterFullName = findViewById(R.id.RegisterFullName);
        registerEmail = findViewById(R.id.registerEmail);
        RegisterPassword = findViewById(R.id.RegisterPassword);
        registerConfpass = findViewById(R.id.confPassword);
        registerUserBtn = findViewById(R.id.RegisterBtn);
        gotoLogin = findViewById(R.id.gotoLogin);

        fAuth = FirebaseAuth.getInstance();

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });


        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract the data from form
                String fullName = RegisterFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = RegisterPassword.getText().toString();
                String confPass = registerConfpass.getText().toString();


                if(fullName.isEmpty()){
                    RegisterFullName.setError("FullName is required");
                    return;
                }
                if(email.isEmpty()){
                    registerEmail.setError("Email is required");
                    return;
                }
                if(password.isEmpty()){
                    RegisterPassword.setError("password is required");
                    return;
                }
                if(confPass.isEmpty()){
                    RegisterPassword.setError("Confirmation password is required");
                    return;
                }
                if(!password.equals(confPass)){
                    registerConfpass.setError("password Do not match.");
                    return;
                }
                Toast.makeText(Register.this, "Data Validated", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //send user to next page
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}