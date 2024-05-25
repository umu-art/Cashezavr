package ru.kazenin.cherry.app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import lombok.var;
import ru.kazenin.cherry.app.MainActivity;
import ru.kazenin.cherry.app.data.LoginHolder;
import ru.kazenin.cherry.app.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final EditText emailEditText = binding.email;
        final EditText phoneEditText = binding.phone;
        final Button registerButton = binding.register;
        final Button goLoginButton = binding.goLogin;

        registerButton.setOnClickListener(v -> {
            var registerSuccess = LoginHolder.registerAttempt(
                    usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    phoneEditText.getText().toString());
            if (registerSuccess) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {

            }
        });

        goLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}