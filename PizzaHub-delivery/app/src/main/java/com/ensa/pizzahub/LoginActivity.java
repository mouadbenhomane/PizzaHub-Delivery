package com.ensa.pizzahub;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button register;
    CheckBox saveLoginCheckBox;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    Boolean saveLogin;


    private final AppCompatActivity activity = LoginActivity.this;
    DBHelper dbHelper= new DBHelper(activity);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        register = findViewById(R.id.btnlogin);

        saveLoginCheckBox = (CheckBox)findViewById(R.id.checkBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPreferences.getString("email", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
            if(dbHelper.checkUser(email.getText().toString(),password.getText().toString())){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                if(isEmpty( email.getText().toString())) {
                    email.setError("email is required!");
                }
                else if(!isEmailValid(email.getText().toString())) {
                    email.setError("email format is wrong");
                }
                else if(isEmpty(password.getText().toString())) {
                    password.setError("password is required!");
                }
                else if(password.getText().toString().length()<8) {
                    password.setError("password must be more than 8 characters !");
                }

                else{
                    if (saveLoginCheckBox.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("email", email.getText().toString());
                        loginPrefsEditor.putString("password", password.getText().toString());
                        loginPrefsEditor.commit();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                    if(dbHelper.checkUser(email.getText().toString(),password.getText().toString())){

                        Toast t = Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT);
                        t.show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else{
                        Toast t = Toast.makeText(LoginActivity.this, "email or password incorrect", Toast.LENGTH_SHORT);
                        t.show();
                    }

                }
            }
        });
        TextView btn=findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
