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
import cakrait.com.contentarticle.model.retrofit.ResponseRegister;
import cakrait.com.contentarticle.network.InitRetrofit;
import cakrait.com.contentarticle.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    TextView txt_login;
    EditText username, email, password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_login = (TextView) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);

        btn_register = (Button) findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") || email.getText().toString().equals("") ||
                        password.getText().toString().equals("") || phone.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Input mohon untuk diisi", Toast.LENGTH_SHORT).show();
                } else {
                    register();
                }
            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    private void register() {
        RestApi restApi = InitRetrofit.getInstance();

        Call<ResponseRegister> registerCall = restApi.registerUser(
                username.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                phone.getText().toString()
        );

        registerCall.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if(response.isSuccessful()) {
                    String result = response.body().getResponse();

                    if(result.equals("success")) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                    } else if (result.equals("failed")) {
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Periksa kembali koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
