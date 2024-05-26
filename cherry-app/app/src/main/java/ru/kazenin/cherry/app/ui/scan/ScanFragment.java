package ru.kazenin.cherry.app.ui.scan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import lombok.var;
import ru.kazenin.cherry.app.R;
import ru.kazenin.cherry.app.image.ImageAnalyzer;
import ru.kazenin.cherry.app.ui.load.ReceiptLoadActivity;

public class ScanFragment extends Fragment {

    public static boolean loading = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        var activity = this.getActivity();
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 20);

        bindCamera(activity);

        return inflater.inflate(R.layout.fragment_scan, container, false);
    }

    private void bindCamera(Activity activity) {
        var cameraProviderFuture = ProcessCameraProvider.getInstance(activity);
        cameraProviderFuture.addListener(() -> {
            try {
                var cameraProvider = cameraProviderFuture.get();
                var preview = new Preview.Builder().build();
                PreviewView previewView = activity.findViewById(R.id.cameraPreview);
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                var analysis =
                        new ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build();

                analysis.setAnalyzer(ContextCompat.getMainExecutor(activity),
                        new ImageAnalyzer(qr -> {
                            if (loading) {
                                return;
                            }
                            loading = true;

                            startActivity(new Intent(this.getActivity(), ReceiptLoadActivity.class)
                                    .putExtra("qr", qr));
                        }));

                cameraProvider.unbindAll();

                cameraProvider.bindToLifecycle(
                        this, CameraSelector.DEFAULT_BACK_CAMERA, preview, analysis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(activity));
    }

}