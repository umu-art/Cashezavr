package ru.kazenin.cashezavr.app;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import lombok.var;
import ru.kazenin.cashezavr.app.data.ApiHolder;
import ru.kazenin.cashezavr.app.data.DataHolder;
import ru.kazenin.cashezavr.app.data.LoginHolder;
import ru.kazenin.cashezavr.app.databinding.ActivityMainBinding;
import ru.kazenin.cashezavr.app.ui.login.LoginActivity;

import static java.util.Objects.isNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 20);

        ApiHolder.init();
        DataHolder.init();
        LoginHolder.tryLoadFromMem(this);

        if (isNull(LoginHolder.loggedInUser) || isNull(LoginHolder.loggedInUser.getAuth())) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        var navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}