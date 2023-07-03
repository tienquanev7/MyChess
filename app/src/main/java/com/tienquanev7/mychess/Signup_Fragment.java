package com.tienquanev7.mychess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup_Fragment extends AppCompatActivity {
    EditText tendangki,passdc,passdc2;
    Button setSignUpp;
    String tdk,pw;
    int check=0;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_fragment);
        tendangki=(EditText) findViewById(R.id.tendangky);
        passdc=(EditText) findViewById(R.id.passdk);
        passdc2=(EditText) findViewById(R.id.passdk2);
        setSignUpp=(Button) findViewById(R.id.setSignup);
        mAuth = FirebaseAuth.getInstance();
        setSignUpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passdc.getText().toString().trim()!="" && passdc2.getText().toString().trim()!="" && tendangki.getText().toString().trim()!="") {
                    if (stringCompare(passdc.getText().toString().trim(),passdc2.getText().toString().trim())==0) {
                        String email = tendangki.getText().toString();
                        String password = passdc.getText().toString();
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Signup_Fragment.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Signup_Fragment.this, RealLogin_Activity.class);
                                            startActivity(intent);
                                        } else{
                                            Toast.makeText(getApplicationContext(), "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập lại tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void signUp(){
        String email=tdk;
        String password=pw;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup_Fragment.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(Signup_Fragment.this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }
}
