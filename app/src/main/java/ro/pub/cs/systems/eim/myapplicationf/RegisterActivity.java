package ro.pub.cs.systems.eim.myapplicationf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import ro.pub.cs.systems.eim.myapplicationf.network.RegisterAsyncTask;

public class RegisterActivity extends Activity {

    EditText numeEditText;
    EditText prenumeEditText;
    EditText emailEditText;
    EditText parolaEditText;
    EditText telefonEditText;

    Button register;
    Button inapoi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);

        numeEditText = findViewById(R.id.edit_nume_register);
        prenumeEditText = findViewById(R.id.edit_prenume_register);
        emailEditText = findViewById(R.id.edit_email_register);
        parolaEditText = findViewById(R.id.edit_password_register);
        telefonEditText = findViewById(R.id.edit_telefon_register);
        register = findViewById(R.id.button_register);
        inapoi = findViewById(R.id.exit_register);

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

        inapoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public EditText getNumeEditText() {
        return numeEditText;
    }

    public void setNumeEditText(EditText numeEditText) {
        this.numeEditText = numeEditText;
    }

    public EditText getPrenumeEditText() {
        return prenumeEditText;
    }

    public void setPrenumeEditText(EditText prenumeEditText) {
        this.prenumeEditText = prenumeEditText;
    }

    public EditText getEmailEditText() {
        return emailEditText;
    }

    public void setEmailEditText(EditText emailEditText) {
        this.emailEditText = emailEditText;
    }

    public EditText getParolaEditText() {
        return parolaEditText;
    }

    public void setParolaEditText(EditText parolaEditText) {
        this.parolaEditText = parolaEditText;
    }

    public EditText getTelefonEditText() {
        return telefonEditText;
    }

    public void setTelefonEditText(EditText telefonEditText) {
        this.telefonEditText = telefonEditText;
    }

    public Button getRegister() {
        return register;
    }

    public void setRegister(Button register) {
        this.register = register;
    }
}
