package ru.kazenin.cherry.app.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import lombok.var;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.databinding.FragmentProfileBinding;

import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

public class ProfileFragment extends Fragment {

    private final Timer timer = new Timer();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        var binding = FragmentProfileBinding.inflate(inflater, container, false);
        var activity = this.getActivity();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setUserinfo(activity, binding);
            }
        }, 0, DataHolder.updatePeriod);


        binding.logout.setOnClickListener((a) -> {
            // TODO: logout
        });


        return binding.getRoot();
    }

    private void setUserinfo(Activity activity, FragmentProfileBinding binding) {
        DataHolder.fillClientDtoIfNull();
        var user = DataHolder.clientDto;

        activity.runOnUiThread(() -> {
            binding.username.setText("Привет, " + user.getUsername());
            binding.phone.setText(user.getPhone());
            binding.email.setText(user.getEmail());
            binding.balance.setText("Ваш актуальный баланс: " + user.getActualBalance()
                    .setScale(2, RoundingMode.HALF_EVEN).toString());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
        timer.purge();
    }
}