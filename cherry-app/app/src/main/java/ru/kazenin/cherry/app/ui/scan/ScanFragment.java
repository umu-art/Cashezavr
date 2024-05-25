package ru.kazenin.cherry.app.ui.scan;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.ViewModelProvider;
import lombok.var;
import ru.kazenin.cherry.app.R;
import ru.kazenin.cherry.app.image.ImageAnalyzer;

public class ScanFragment extends Fragment {

    private ScanViewModel mViewModel;

    public static ScanFragment newInstance() {
        return new ScanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ScanViewModel.class);

        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CAMERA}, 20);
        bindCamera();
    }

    private void bindCamera() {
        var cameraProviderFuture = ProcessCameraProvider.getInstance(this.getActivity());
        cameraProviderFuture.addListener(() -> {
            try {
                var cameraProvider = cameraProviderFuture.get();

                var preview = new Preview.Builder().build();
                PreviewView previewView = this.getActivity().findViewById(R.id.cameraPreview);
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                var analysis =
                        new ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build();

                analysis.setAnalyzer(ContextCompat.getMainExecutor(this.getActivity()),
                        new ImageAnalyzer(qr -> Log.i("QR", "new qr: " + qr)));

                cameraProvider.unbindAll();

                cameraProvider.bindToLifecycle(
                        this, CameraSelector.DEFAULT_BACK_CAMERA, preview, analysis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this.getActivity()));
    }

}