package ru.kazenin.cherry.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import lombok.var;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.data.ApiHolder;
import ru.kazenin.cherry.app.data.LoginHolder;
import ru.kazenin.cherry.app.databinding.ActivityMainBinding;
import ru.kazenin.cherry.app.ui.login.LoginActivity;

import static java.util.Objects.isNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiHolder.init();
        DataHolder.init();

        if (isNull(LoginHolder.loggedInUser)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        var navView = findViewById(R.id.nav_view);

        var appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.navigation_qrscan,
                        R.id.navigation_profile,
                        R.id.navigation_bills,
                        R.id.navigation_receipts,
                        R.id.navigation_support)
                        .build();
        var navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}