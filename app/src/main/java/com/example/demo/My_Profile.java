package com.example.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class My_Profile extends Fragment {

    private TextView textViewUsername;
    private EditText editTextName;
    private DatabaseHelper databaseHelper;

    public My_Profile() {
        // Required empty public constructor
    }

    public static My_Profile newInstance() {
        return new My_Profile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my__profile, container, false);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        editTextName = view.findViewById(R.id.editTextName);
        Button buttonUpdate = view.findViewById(R.id.buttonUpdate);

        databaseHelper = new DatabaseHelper(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");
        Log.d("My_Profile", "Username from SharedPreferences: " + username);

        String userNameFromDb = databaseHelper.getUsername(username);
        Log.d("My_Profile", "Username from Database: " + userNameFromDb);

        if (userNameFromDb != null) {
            textViewUsername.setText(userNameFromDb);
            editTextName.setText(userNameFromDb);
        } else {
            Toast.makeText(getActivity(), "User not found", Toast.LENGTH_SHORT).show();
        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editTextName.getText().toString();
                if (!newName.isEmpty()) {
                    updateUserProfile(username, newName);
                    textViewUsername.setText(newName);  // Cập nhật tên người dùng trên giao diện
                    Toast.makeText(getActivity(), "Profile updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void updateUserProfile(String oldUsername, String newName) {
        boolean isUpdated = databaseHelper.updateUsername(oldUsername, newName);

        if (isUpdated) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", newName);
            editor.apply();
        } else {
            Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_SHORT).show();
        }
    }
}
