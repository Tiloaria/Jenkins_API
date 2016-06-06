package ru.spbau.mit.yulia.jenkins_api_for_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View view) {
        String s = ((TextView) findViewById(R.id.jenkins_adress)).getText().toString();
        if(checkServerAddr(s)) {
            Toast.makeText(LoginActivity.this, "Adress is correct, but no we can't give you information!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, StatusActivity.class);
            intent.putExtra("addr", s);

            startActivity(intent);
        }
        else {
            Toast.makeText(LoginActivity.this, "Adress is incorrect! \n Put it with http or https", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean checkServerAddr(String addr) {
        return Pattern.matches("^(http|https).*", addr);
    }

}
