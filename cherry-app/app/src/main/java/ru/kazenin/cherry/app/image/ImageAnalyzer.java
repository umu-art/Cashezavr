package ru.kazenin.cherry.app.image;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

@RequiredArgsConstructor
public class ImageAnalyzer implements ImageAnalysis.Analyzer {
    private final QrFoundListener listener;

    @NotNull
    private static BinaryBitmap getBinaryBitmap(@NotNull ImageProxy image) {
        ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
        byte[] imageData = new byte[byteBuffer.capacity()];
        byteBuffer.get(imageData);

        PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(
                imageData,
                image.getWidth(), image.getHeight(),
                0, 0,
                image.getWidth(), image.getHeight(),
                false
        );

        return new BinaryBitmap(new HybridBinarizer(source));
    }

    @Override
    public void analyze(@NonNull @NotNull ImageProxy image) {
        BinaryBitmap binaryBitmap = getBinaryBitmap(image);

        try {
            Result result = new QRCodeMultiReader().decode(binaryBitmap);
            listener.qrCodeFounded(result.getText());
        } catch (Exception ignored) {
        }

        image.close();
    }
}
