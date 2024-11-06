package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Register_Page extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private TextView loginLink;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // Khởi tạo các thành phần giao diện
        usernameEditText = findViewById(R.id.register_username);
        passwordEditText = findViewById(R.id.register_password);
        confirmPasswordEditText = findViewById(R.id.register_confirm_password);
        registerButton = findViewById(R.id.register_button);
        loginLink = findViewById(R.id.login_link);

        // Khởi tạo DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Xử lý sự kiện khi nhấn nút Đăng ký
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (databaseHelper.isUserExists(username)) {
                    Toast.makeText(Register_Page.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                } else if (password.equals(confirmPassword)) {
                    boolean isRegistered = databaseHelper.registerUser(username, password);
                    if (isRegistered) {
                        Toast.makeText(Register_Page.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register_Page.this, Login_Page.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Register_Page.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register_Page.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào liên kết "Login here"
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(Register_Page.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
