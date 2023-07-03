package com.tienquanev7.mychess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RealLogin_Activity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    FirebaseDatabase database;
    DatabaseReference loginCheck;
    FragmentManager fragmentManager = getFragmentManager();
    Button signupButton,dangnhapButton;
    EditText tdk,pw;
    CheckBox cbRemember;
    public static String intertdn,interpass;
    int LAUNCH_SECOND_ACTIVITY = 1;
    HashMap<String, String> loginMap = new HashMap<String, String>();
    public static FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_login);
        sharedPreferences=getSharedPreferences("dataLogin",MODE_PRIVATE);
        database = FirebaseDatabase.getInstance();
        loginCheck = database.getReference();
        cbRemember=(CheckBox)findViewById(R.id.checkBox);
        signupButton = (Button) findViewById(R.id.signupbutton);
        signupButton.setOnClickListener(view -> {
            Intent intent = new Intent(RealLogin_Activity.this,Signup_Fragment.class);
            startActivity(intent);
        });
        mAuth = FirebaseAuth.getInstance();
        dangnhapButton=(Button) findViewById(R.id.buttondangnhap);
        tdk = (EditText) findViewById(R.id.logintext);
        pw = (EditText) findViewById(R.id.passwordtext);
        tdk.setText(sharedPreferences.getString("username",""));
        pw.setText(sharedPreferences.getString("password",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));
        dangnhapButton.setOnClickListener(view -> {
            String email=tdk.getText().toString();
            String password=pw.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                intertdn=email;
                                interpass=password;
                                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                if(cbRemember.isChecked()){
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username",email);
                                    editor.putString("password",password);
                                    editor.putBoolean("checked",true);
                                    editor.commit();
                                }else{
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("username");
                                    editor.remove("password");
                                    editor.remove("checked");
                                    editor.commit();
                                }

                                Intent intent = new Intent(RealLogin_Activity.this, OptionActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

}