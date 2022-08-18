package com.app.addvertisementapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.app.addvertisementapp.Utils.SharedPref;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton SubmitAdd,GetAdds;
    SharedPref sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sf = new SharedPref(getApplicationContext());
        SubmitAdd = findViewById(R.id.SubmitAdd);

        GetAdds=findViewById(R.id.GetAdds);
        GetAdds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.app.addvertisementapp.GetAdds.class);
                startActivity(intent);
            }
        });
        SubmitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SubmitAdvertisement.class);
                startActivity(intent);
            }
        });

    }


    public void Exit(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("توجه");
        alertDialog.setMessage("آیا مایل به خروج از برنامه هستید؟");
        alertDialog.setPositiveButton("بله",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                        dialog.dismiss();

                    }
                });
        alertDialog.setNegativeButton("خیر",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.dismiss();
                    }
                });
        // Showing Alert Message
        alertDialog.show();


    }

    public void Logout(View view) {
        sf.setIsLogin(false);
        sf.setUserId("");
        Toast.makeText(MainActivity.this, "شما با موفقیت از حساب خارج شدید", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);

    }
}