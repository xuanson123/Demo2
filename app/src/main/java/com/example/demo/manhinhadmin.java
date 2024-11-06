package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class manhinhadmin extends AppCompatActivity {

    private TextView adminNameTextView;
    private Button addClassButton, addLecturerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manhinhadmin);

        // Liên kết các thành phần giao diện với mã Java
        adminNameTextView = findViewById(R.id.adminNameTextView);
        addClassButton = findViewById(R.id.addClassButton);
        addLecturerButton = findViewById(R.id.addLecturerButton);

        // Giả sử bạn lấy tên quản trị viên từ cơ sở dữ liệu hoặc SharedPreferences
        String adminName = getAdminNameFromDatabase();
        adminNameTextView.setText(adminName);

        // Xử lý sự kiện khi nhấn nút Thêm Giảng Viên
        addLecturerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manhinhadmin.this, Register_Page.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Hàm giả sử lấy tên quản trị viên từ cơ sở dữ liệu
    private String getAdminNameFromDatabase() {
        // Ở đây bạn cần triển khai việc lấy dữ liệu từ cơ sở dữ liệu thực tế
        return "Nguyễn Văn Quản Trị";
    }
}
