package ro.pub.cs.systems.eim.myapplicationf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ro.pub.cs.systems.eim.myapplicationf.network.LoginTaskAsync;

public class LoginActivity extends Activity {

    Button loginButton;
    Button inregistrareButton;
    EditText usernameEditText;
    EditText passwordEditText;
    String username = "";
    String password = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        loginButton = (Button) findViewById(R.id.button_login);
        inregistrareButton = (Button) findViewById(R.id.donthaveaccount);
        usernameEditText = (EditText) findViewById(R.id.username_edit);
        passwordEditText = (EditText) findViewById(R.id.password_edit);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();
                if(username == null || username.equals("")){
                    Bundle bundle = new Bundle();
                    bundle.putString("Eroare","Te rog introdu un username");
                    showDialog(1,bundle);
                }else if(password == null || password.equals("")){
                    Bundle bundle = new Bundle();
                    bundle.putString("Eroare","Te rog introdu parola");
                    showDialog(1,bundle);
                }else {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    new LoginTaskAsync(LoginActivity.this).execute(username, password);
                }
            }
        });


        inregistrareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
