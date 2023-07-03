package com.tienquanev7.mychess;
import static com.tienquanev7.mychess.RealLogin_Activity.mAuth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class OptionActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    //FirebaseDatabase database;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        btn1=(Button) findViewById(R.id.button3);
        btn2=(Button) findViewById(R.id.button4);
        btn3=(Button) findViewById(R.id.button5);
        btn4=(Button) findViewById(R.id.button6);

        btn1.setOnClickListener(view -> {
            Intent intent = new Intent(OptionActivity.this, Pickroom.class);
            startActivity(intent);
        });
        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(OptionActivity.this, Playoffline.class);
            startActivity(intent);
        });

        btn3.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(OptionActivity.this, RealLogin_Activity.class);
            startActivity(intent);
        });
        btn4.setOnClickListener(view -> {
            Intent intent = new Intent(OptionActivity.this, PuzzleActivity.class);
            startActivity(intent);
        });


    }
}