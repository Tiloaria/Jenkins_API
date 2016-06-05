package ru.spbau.mit.yulia.jenkins_api_for_android;

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

        ((Button) findViewById(R.id.bottom)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ((TextView) findViewById(R.id.jenkins_adress)).getText().toString();
                if(checkServerAddr(s)) {

                }
                else {
                    Toast.makeText(LoginActivity.this, "Adress is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkServerAddr(String addr) {
        return Pattern.matches("^(http|https).*", addr);
    }

}
