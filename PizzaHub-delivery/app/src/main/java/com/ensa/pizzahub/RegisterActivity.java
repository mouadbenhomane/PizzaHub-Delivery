package com.ensa.pizzahub;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ensa.pizzahub.model.User;


public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button register;

    private final AppCompatActivity activity = RegisterActivity.this;
    DBHelper dbHelper= new DBHelper(activity);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        username = findViewById(R.id.inputUsername);
//        email = findViewById(R.id.inputEmail);
//        password = findViewById(R.id.inputPassword);
//        confirmPassword = findViewById(R.id.inputConformPassword);
//        register = findViewById(R.id.btnRegister);
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("click");
//                if (isEmpty((CharSequence) username)) {
//                    Toast t = Toast.makeText(RegisterActivity.this, "You must enter username to register!", Toast.LENGTH_SHORT);
//                    t.show();
//                }
//                else if(isEmpty((CharSequence) email)) {
//                    Toast t = Toast.makeText(RegisterActivity.this, "You must enter email to register!", Toast.LENGTH_SHORT);
//                    t.show();
//                }
//                else if(isEmpty((CharSequence) password)) {
//                    Toast t = Toast.makeText(RegisterActivity.this, "You must enter password to register!", Toast.LENGTH_SHORT);
//                    t.show();
//                }
//                else if(isEmpty((CharSequence) confirmPassword)) {
//                    Toast t = Toast.makeText(RegisterActivity.this, "You must confirm password to register!", Toast.LENGTH_SHORT);
//                    t.show();
//                }else if(password.equals(confirmPassword) ){
//                    Toast t = Toast.makeText(RegisterActivity.this, "Password doesn't match!", Toast.LENGTH_SHORT);
//                    t.show();
//                }
//                else{
//                    User user= new User( username+"",email+"",password+"");
//                    try {
//                        dbHelper.addUser(user);
//                    } catch (Exception e) {
//                        Toast t = Toast.makeText(RegisterActivity.this, "Email already exist!", Toast.LENGTH_SHORT);
//                        t.show();
//                    }
//                }
//            }
//        });
        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }
}
