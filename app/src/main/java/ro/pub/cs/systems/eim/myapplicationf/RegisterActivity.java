package ro.pub.cs.systems.eim.myapplicationf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import ro.pub.cs.systems.eim.myapplicationf.network.RegisterAsyncTask;

public class RegisterActivity extends Activity {

    EditText numeEditText;
    EditText prenumeEditText;
    EditText emailEditText;
    EditText parolaEditText;
    EditText telefonEditText;

    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        numeEditText = findViewById(R.id.nume_edit);
        prenumeEditText = findViewById(R.id.prenume_edit);
        emailEditText = findViewById(R.id.email_edit);
        parolaEditText = findViewById(R.id.password_edit);
        telefonEditText = findViewById(R.id.telefon_edit);
        register = findViewById(R.id.button_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RegisterAsyncTask(RegisterActivity.this)
                        .execute(numeEditText.getText().toString(),
                                prenumeEditText.getText().toString(),
                                emailEditText.getText().toString(),
                                parolaEditText.getText().toString(),
                                telefonEditText.getText().toString()

                        );
            }
        });






    }



}
