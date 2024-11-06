package com.example.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrgiangvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgiangvien);

        ImageView qrCodeImageView = findViewById(R.id.qrCodeImageView);
        Button generateButton = findViewById(R.id.generateButton);

        // Sự kiện nhấn nút để tạo mã QR
        generateButton.setOnClickListener(v -> {
            String qrContent = "Attendance Code: [Mã hoặc thông tin điểm danh]";

            try {
                // Tạo mã QR từ nội dung `qrContent`
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(qrContent, BarcodeFormat.QR_CODE, 400, 400);
                qrCodeImageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                // Hiển thị thông báo lỗi nếu không thể tạo mã QR
                Toast.makeText(this, "Không thể tạo mã QR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
