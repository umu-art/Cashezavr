package ru.kazenin.cashezavr.app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import lombok.var;
import ru.kazenin.cashezavr.app.MainActivity;
import ru.kazenin.cashezavr.app.R;
import ru.kazenin.cashezavr.app.data.LoginHolder;
import ru.kazenin.cashezavr.app.databinding.ActivityLoginBinding;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final Button goRegisterButton = binding.goRegister;
        var activity = this;

        loginButton.setOnClickListener(v -> {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    var loginSuccess = LoginHolder.loginAttempt(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                    if (loginSuccess) {
                        startActivity(new Intent(activity, MainActivity.class));
                        finish();
                    } else {
                        runOnUiThread(() -> {
                            usernameEditText.setError(getString(R.string.login_failed));
                            passwordEditText.setError(getString(R.string.login_failed));
                        });
                    }
                }
            }, 0);
        });

        goRegisterButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}