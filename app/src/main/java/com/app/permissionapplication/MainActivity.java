package com.app.permissionapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int CALL = 101;
//    private ActivityResultLauncher<String> requestPermissionLauncher =
//            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
//                if (isGranted) {
//                    // Permission is granted. Continue the action or workflow in your
//                    // app.
//                } else {
//                    requestPermissions(new String[]{"android.permission.CALL_PHONE", "android.permission.INTERNET"}, 101);
//                }
//            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            Log.d("Rational1","You need to allow access to both the permissions");
            Toast.makeText(getApplicationContext(), "Call Permission Provided", Toast.LENGTH_SHORT).show();
        } else if (shouldShowRequestPermissionRationale("android.permission.CALL_PHONE")) {
            Log.d("Rational2","You need to allow access to both the permissions");
            showMessageOKCancel("You need to allow access to both the permissions",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 101);
                        }
                    });
        } else {
            Log.d("Rational3","You need to allow access to both the permissions");
            requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 101);
        }

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                    Log.d("Rational1","You need to allow access to both the permissions");
                    Toast.makeText(getApplicationContext(), "Call Permission Provided", Toast.LENGTH_SHORT).show();
//                } else if (shouldShowRequestPermissionRationale("android.permission.CALL_PHONE")) {
//                    Log.d("Rational2","You need to allow access to both the permissions");
//                    showMessageOKCancel("You need to allow access to both the permissions",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 101);
//                                }
//                            });
                } else {
                    Log.d("Rational3","You need to allow access to both the permissions");
                    requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 101);
                }
            }
        });

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}