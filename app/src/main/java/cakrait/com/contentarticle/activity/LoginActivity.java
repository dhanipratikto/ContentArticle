package cakrait.com.contentarticle.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cakrait.com.contentarticle.R;
import cakrait.com.contentarticle.model.retrofit.ResponseLogin;
import cakrait.com.contentarticle.network.InitRetrofit;
import cakrait.com.contentarticle.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    TextView txt_register;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_register = (TextView) findViewById(R.id.register);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        btn_login = (Button) findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Harap isi email dan password", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void login() {
        RestApi restApi = InitRetrofit.getInstance();

        Call<ResponseLogin> loginCall = restApi.loginUser(
            email.getText().toString(),
            password.getText().toString()
        );

        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()) {
                    String result = response.body().getResponse();

                    if(result.equals("success")) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    } else if (result.equals("failed")) {
                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Periksa kembali koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
