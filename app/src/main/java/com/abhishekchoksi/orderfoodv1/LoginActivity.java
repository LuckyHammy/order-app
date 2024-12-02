package com.abhishekchoksi.orderfoodv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewSignUp;
    private EditText email, password;
    private Button btnSignIn;
    private ProgressBar loadingPB;
    private static final String LOGIN_URL = "https://www.takeawayordering.com/appserver/appserver.php?tag=shoplogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        textViewSignUp = findViewById(R.id.textViewSignUp);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        loadingPB = findViewById(R.id.idPBLoadingSignIn);
        // Adding click listener for Sign In button.
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);

                // Getting data from our EditText.
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                // Checking if the text fields are empty or not.
                if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)) {
                    Toast.makeText(LoginActivity.this, "Please add your credentials.", Toast.LENGTH_SHORT).show();
                    loadingPB.setVisibility(View.GONE);
                } else if (userPassword.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Password too short, please enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    loadingPB.setVisibility(View.GONE);
                } else {
                    // Send login request
                    loginUser(userEmail, userPassword);
                }
            }
        });

        // Adding on click listener for Sign Up textView.
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });
    }

    private void loginUser(String phone, String pin) {
        // Creating a new request using Volley
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingPB.setVisibility(View.GONE);
                        try {
                            // Parse the response JSON
                            JSONObject jsonResponse = new JSONObject(response);
                            int success = jsonResponse.getInt("success");

                            if (success == 1) {
                                // Login successful, redirect to MainActivity
                                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                // Login failed, show error message
                                Toast.makeText(LoginActivity.this, "Login Failed: " + jsonResponse.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error parsing response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error handling
                        loadingPB.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Adding parameters to POST request
                Map<String, String> params = new HashMap<>();
                params.put("employee_phone", phone);
                params.put("employee_pin", pin);
                return params;
            }
        };

        // Adding the request to the request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
