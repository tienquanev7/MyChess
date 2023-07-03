package com.tienquanev7.mychess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pickroom extends AppCompatActivity {

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    Button timb,tao;
    EditText soPhong,taophong;
    int i,check;
    static char[] theBoard = {'R','N','B','Q','K','B','N','R','P','P','P','P','P','P','P','P','*','*','*','*',
            '*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*',
            '*','*','*','*','*','p','p','p','p','p','p','p','p','r','n','b','q','k','b','n','r'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickroom);
        String theBoard2 = String.valueOf(theBoard);
        timb = (Button) findViewById(R.id.button);
        taophong=(EditText) findViewById(R.id.editTextTextPersonNam);
        soPhong= (EditText) findViewById(R.id.editTextTextPersonName) ;
        timb.setOnClickListener(view -> {
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String sp=soPhong.getText().toString();
                if (snapshot.hasChild("Room"+sp) ) {
                    rootRef.child("Room"+sp).child("full").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Pickroom.this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if(stringCompare(String.valueOf(task.getResult().getValue()),"0")==0){
                                    rootRef.child("Room" + sp).child("full").setValue("1");
                                    Intent intent = new Intent(Pickroom.this,MainActivity.class);
                                    intent.putExtra("roomnumber",sp);
                                    intent.putExtra("host",""+0);
                                    startActivity(intent);
                                } else{
                                    Toast.makeText(Pickroom.this, "Phòng đã đầy", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });});
        tao=(Button) findViewById(R.id.tao);
        tao.setOnClickListener(view -> {
            String sp=taophong.getText().toString();
            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.hasChild("Room" + sp)) {
                        rootRef.child("Room" + sp).child("full").setValue("0");
                        rootRef.child("Room" + sp).child("theBoard").setValue(theBoard2);
                        Intent intent = new Intent(Pickroom.this,MainActivity.class);
                        intent.putExtra("roomnumber",sp);
                        intent.putExtra("host",""+1);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Pickroom.this, "Phòng đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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