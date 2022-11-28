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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        username = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConformPassword);
        register = findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                if (isEmpty(username.getText().toString())) {
                    username.setError("username is required!");
                }
                else if(isEmpty( email.getText().toString())) {
                    email.setError("email is required!");
                }
                else if(!isEmailValid(email.getText().toString())) {
                    email.setError("email format is wrong");
                }
                else if(isEmpty(password.getText().toString())) {
                    password.setError("password is required!");
                }
                else if(isEmpty( confirmPassword.getText().toString())) {
                    confirmPassword.setError("confirm you password");
                }else if(password.equals(confirmPassword) ){
                    Toast t = Toast.makeText(RegisterActivity.this, "Password doesn't match!", Toast.LENGTH_SHORT);
                    t.show();
                }
                else{
                    User user= new User( username+"",email+"",password+"");
                    try {
                        dbHelper.addUser(user);
                    } catch (Exception e) {
                        System.out.println("6");

                    }
                }
            }
        });
        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
