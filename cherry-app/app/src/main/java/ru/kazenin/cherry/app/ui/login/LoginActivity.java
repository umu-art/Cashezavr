package ru.kazenin.cherry.app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import lombok.var;
import ru.kazenin.cherry.app.MainActivity;
import ru.kazenin.cherry.app.R;
import ru.kazenin.cherry.app.data.LoginHolder;
import ru.kazenin.cherry.app.databinding.ActivityLoginBinding;

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
        final ProgressBar loadingProgressBar = binding.loading;

        loginButton.setOnClickListener(v -> {
            runOnUiThread(() -> loadingProgressBar.setVisibility(View.VISIBLE));

            var loginSuccess = LoginHolder.loginAttempt(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            if (loginSuccess) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                usernameEditText.setError(getString(R.string.login_failed));
                passwordEditText.setError(getString(R.string.login_failed));
            }
            runOnUiThread(() -> loadingProgressBar.setVisibility(View.INVISIBLE));
        });

        goRegisterButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}