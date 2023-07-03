package com.tienquanev7.mychess;

import static com.tienquanev7.mychess.RealLogin_Activity.intertdn;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView t7,t8;

    static ImageView x63,x62,x61,x60,x59,x58,x57,x56,x55,x54,x53,x52,x51,x50,x49,x48,x47,x46,x45,
            x44,x43,x42,x41,x40,x39,x38,x37,x36,x35,x34,x33,x32,x31,x30,x29,x28,x27,x26,x25,x24,
            x23,x22,x21,x20,x19,x18,x17,x16,x15,x14,x13,x12,x11,x10,x9,x8,x7,x6,x5,x4,x3,x2,x1,x0;

    static int[] imageViews = {R.id.p0,R.id.p1,R.id.p2,R.id.p3,R.id.p4,R.id.p5,R.id.p6,R.id.p7,R.id.p8,R.id.p9,
            R.id.p10,R.id.p11,R.id.p12,R.id.p13,R.id.p14,R.id.p15,R.id.p16,R.id.p17,R.id.p18,R.id.p19,
            R.id.p20,R.id.p21,R.id.p22,R.id.p23,R.id.p24,R.id.p25,R.id.p26,R.id.p27,R.id.p28,R.id.p29,
            R.id.p30,R.id.p31,R.id.p32,R.id.p33,R.id.p34,R.id.p35,R.id.p36,R.id.p37,R.id.p38,R.id.p39,
            R.id.p40,R.id.p41,R.id.p42,R.id.p43,R.id.p44,R.id.p45,R.id.p46,R.id.p47,R.id.p48,R.id.p49,
            R.id.p50,R.id.p51,R.id.p52,R.id.p53,R.id.p54,R.id.p55,R.id.p56,R.id.p57,R.id.p58,R.id.p59,
            R.id.p60,R.id.p61,R.id.p62,R.id.p63};

    static ImageView[] chessImage = {x0,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15,
            x16,x17,x18,x19,x20,x21,x22,x23,x24,x25,x26,x27,x28,x29,x30,x31,
            x32,x33,x34,x35,x36,x37,x38,x39,x40,x41,x42,x43,x44,x45,x46,x47,
            x48,x49,x50,x51,x52,x53,x54,x55,x56,x57,x58,x59,x60,x61,x62,x63};

    static char[] theBoard = {'R','N','B','Q','K','B','N','R','P','P','P','P','P','P','P','P','*','*','*','*',
            '*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*',
            '*','*','*','*','*','p','p','p','p','p','p','p','p','r','n','b','q','k','b','n','r'};
    int phe,pP,dem=0,number,dem2=0,moveCountVar=0,change,checkking=0,checksafe=0;

    Button btn,btnhientin,btngui;
    public static String allChat;
    EditText etnhaptin;
    String theBoardStr,StrmoveCount,sp,host,guestname,hostname;
    FirebaseDatabase database;
    DatabaseReference myBoard;
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        sp = intent.getStringExtra("roomnumber");
        host = intent.getStringExtra("host");
        btn=(Button) findViewById(R.id.resetButton);
        database = FirebaseDatabase.getInstance();
        myBoard = database.getReference();
        String[] arrOfStr = intertdn.split("@", 2);
        t7=(TextView) findViewById(R.id.textView7);
        t8=(TextView) findViewById(R.id.textView8);
        myBoard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("Room"+sp).hasChild("hostname")){
                    myBoard.child("Room" + sp).child("hostname").setValue("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myBoard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.child("Room"+sp).hasChild("guestname")){
                    myBoard.child("Room" + sp).child("guestname").setValue("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myBoard.child("Room" + sp).child("guestname").setValue("");
        if(stringCompare(host,"1")==0){
            myBoard.child("Room" + sp).child("hostname").setValue(arrOfStr[0]);
        }else{
            myBoard.child("Room" + sp).child("guestname").setValue(arrOfStr[0]);
        }
        if(stringCompare(host,"1")==0){
            t8.setText("Host: "+arrOfStr[0]);
            myBoard.child("Room" + sp).child("guestname").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    guestname=snapshot.getValue().toString();
                    t7.setText("Guest: "+guestname);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else{
            t8.setText("Guest: "+arrOfStr[0]);
            myBoard.child("Room" + sp).child("hostname").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    hostname=snapshot.getValue().toString();
                    t7.setText("Host: "+hostname);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        if(stringCompare(host,"1")==0){
            Random rand = new Random();
            int int_random = rand.nextInt(2);
            if(int_random==0){
                myBoard.child("Room"+sp).child("host").setValue("while");
                phe=0;
            }else{
                myBoard.child("Room"+sp).child("host").setValue("black");
                phe=1;
            }
        }
        myBoard.child("Room" + sp).child("host").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(stringCompare(host,"0")==0){
                    String layphe=snapshot.getValue().toString();
                    if(stringCompare(layphe,"while")==0){
                        phe=1;
                    }else{
                        phe=0;
                    }
                    drawBoardPieces();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for (int i=0; i<64; i++) {
            chessImage[i]=(ImageView)findViewById(imageViews[i]);
        }
        if(stringCompare(host,"1")==0){
            btn.setOnClickListener(view -> {
                char [] theBoard2 = {'R','N','B','Q','K','B','N','R','P','P','P','P','P','P','P','P','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','p','p','p','p','p','p','p','p','r','n','b','q','k','b','n','r'};
                for(int i=0;i<64;i++){
                    theBoard[i]=theBoard2[i];
                }
                Random rand = new Random();
                int int_random = rand.nextInt(2);
                if(int_random==0){
                    myBoard.child("Room"+sp).child("host").setValue("while");
                    phe=0;
                }   else{
                    myBoard.child("Room"+sp).child("host").setValue("black");
                    phe=1;
                }
                drawBoardPieces();
                theBoardStr=String.valueOf(theBoard);
                myBoard.child("Room" + sp).child("theBoard").setValue(theBoardStr);
                moveCountVar=0;
            });
        }else{
            btn.setText("");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        drawBoardPieces();
        myBoard.child("Room"+sp).child("countMove").setValue(0);
        myBoard.child("Room" + sp).child("theBoard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                theBoardStr = snapshot.getValue().toString();
                for(int i=0;i<=63;i++){
                    theBoard[i]=theBoardStr.charAt(i);
                }
                drawBoardPieces();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myBoard.child("Room"+sp).child("countMove").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StrmoveCount = snapshot.getValue().toString();
                change=Integer.parseInt(StrmoveCount);
                moveCountVar=change;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myBoard.child("Room" + sp).child("chat").setValue(arrOfStr[0]+": tham gia phòng");
        myBoard.child("Room"+sp).child("allChat").setValue(arrOfStr[0]+": tham gia phòng\n");
        btngui=(Button) findViewById(R.id.gui);
        btnhientin=(Button) findViewById(R.id.hientin);
        etnhaptin=(EditText) findViewById(R.id.nhaptin);
        btngui.setOnClickListener(view -> {
            String tinnhan=etnhaptin.getText().toString();
            myBoard.child("Room"+sp).child("chat").setValue(arrOfStr[0]+": "+tinnhan);
            myBoard.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    allChat=snapshot.child("Room" + sp).child("allChat").getValue().toString()+arrOfStr[0]+": "+tinnhan+"\n";
                    myBoard.child("Room"+sp).child("allChat").setValue(allChat);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        myBoard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allChat=snapshot.child("Room" + sp).child("allChat").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myBoard.child("Room" + sp).child("chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String chat=snapshot.getValue().toString();
                btnhientin.setText(chat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnhientin.setOnClickListener(view -> {
            fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            Fragment chatFragment=new chatFragment();
            fragmentTransaction.add(R.id.chatFrame,chatFragment,"chatF");
            fragmentTransaction.commit();
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(stringCompare(host,"1")==0){
            myBoard.child("Room"+sp).removeValue();
        }else{
            myBoard.child("Room"+sp).child("full").setValue("0");
            myBoard.child("Room"+sp).child("guestname").setValue("");
        }
    }
    public void moveCount(){
        if(checkking==0){
            if(theBoard[number]>='a'&&theBoard[number]<='z'){
                myBoard.child("Room"+sp).child("countMove").setValue(moveCountVar-1);
                moveCountVar-=1;
            } else{
                myBoard.child("Room"+sp).child("countMove").setValue(moveCountVar+1);
                moveCountVar+=1;
            }
        }
    }
    public void moveablePiece(View view){
        if(phe==moveCountVar){
            number = Integer.parseInt(view.getTag().toString());
            if(phe==1){
                number=63-number;
            }
            if(moveCountVar==0){
                if (theBoard[number]>='A' && theBoard[number]<='Z'){
                    pP=number;
                } else {
                    makeMove();
                }
            } else {
                if (theBoard[number] >= 'a' && theBoard[number] <= 'z') {
                    pP = number;
                } else {
                    makeMove();
                }
            }
            drawBoardPieces();
            theBoardStr=String.valueOf(theBoard);
            myBoard.child("Room" + sp).child("theBoard").setValue(theBoardStr);
        }
    }
    public void makeMove() {
        if (theBoard[pP] == 'R') {
            moveRookW();
        }
        if (theBoard[pP] == 'r') {
            moveRookB();
        }
        if (theBoard[pP] == 'P') {
            movePawnW();
        }
        if (theBoard[pP] == 'p') {
            movePawnB();
        }
        if (theBoard[pP] == 'N') {
            moveKnightW();
        }
        if (theBoard[pP] == 'n') {
            moveKnightB();
        }
        if (theBoard[pP] == 'B') {
            moveBishopW();
        }
        if (theBoard[pP] == 'b') {
            moveBishopB();
        }
        if (theBoard[pP] == 'Q') {
            moveQueenW();
        }
        if (theBoard[pP] == 'q') {
            moveQueenB();
        }
        if (theBoard[pP] == 'K') {
            moveKingW();
        }
        if (theBoard[pP] == 'k') {
            moveKingB();
        }
    }
    public int betweenRook(){
        if(number/8==pP/8){
            if(number<pP){
                for (int i=pP;i>=number+1;i--){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
            if(number>pP){
                for (int i=pP;i<=number-1;i++){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
        }
        if(number%8==pP%8){
            if(number<pP){
                for (int i=pP;i>=number+8;i=i-8){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
            if(number>pP){
                for (int i=pP;i<=number-8;i=i+8){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
        }
        return 1;
    }
    public void moveRookW(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if((number/8==pP/8 || number%8==pP%8) && betweenRook()==0) {
            if (theBoard[number]=='*' || theBoard[number]>'Z'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(wkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        }
    }
    public void moveRookB(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if((number/8==pP/8 || number%8==pP%8) && betweenRook()==0) {
            if (theBoard[number]=='*' || theBoard[number]<'a'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(bkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        }
    }
    public void movePawnW(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if(pP>=8 && pP<=15){
            if(((number==pP+8 || number==pP+16)&&theBoard[number]=='*')||(number==pP+9&&(theBoard[number]>='a'&&theBoard[number]<='z'))||(number==pP+7&&(theBoard[number]>='a'&&theBoard[number]<='z'))){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(wkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        } else{
            if((number==pP+8&&theBoard[number]=='*')||(number==pP+9&&(theBoard[number]>='a'&&theBoard[number]<='z'))||(number==pP+7&&(theBoard[number]>='a'&&theBoard[number]<='z'))) {
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                if(checksafe==0){
                    if(wkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        }
    }
    public void movePawnB(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if(pP>=48 && pP<=55){
            if(((number==pP-8||number==pP-16)&&theBoard[number]=='*')||(number==pP-9&&(theBoard[number]>='A'&&theBoard[number]<='Z'))||(number==pP-7&&(theBoard[number]>='A'&&theBoard[number]<='Z'))){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(bkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        } else{
            if((number==pP-8&&theBoard[number]=='*')||(number==pP-9&&(theBoard[number]>='A'&&theBoard[number]<='Z'))||(number==pP-7&&(theBoard[number]>='A'&&theBoard[number]<='Z'))){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(bkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        }
    }
    public void moveKnightW(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if((number==pP+15||number==pP+6||number==pP+17||number==pP+10||number==pP-15||number==pP-6||number==pP-17||number==pP-10)&&(theBoard[number]=='*'||theBoard[number]>'Z')){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            if(checksafe==0){
                if(wkingischecked()==1){
                    number=tnum;
                    pP=tpP;
                    theBoard[number]=btnum;
                    theBoard[pP]=btpP;
                }else{
                    moveCount();
                }
            }
        }
    }
    public void moveKnightB(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if((number==pP+15||number==pP+6||number==pP+17||number==pP+10||number==pP-15||number==pP-6||number==pP-17||number==pP-10)&&(theBoard[number]=='*'||theBoard[number]<'a')){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            if(checksafe==0){
                if(bkingischecked()==1){
                    number=tnum;
                    pP=tpP;
                    theBoard[number]=btnum;
                    theBoard[pP]=btpP;
                }else{
                    moveCount();
                }
            }
        }
    }
    public int limitBishop(){
        int a=number-pP;
        if(pP/8==0&&pP%8==0){
            if(a>0 && (a%9==0)){
                for(int i=0;pP+i*9<=number;i++){
                    if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==0&&pP%8==7){
            if(a>0 && (a%7==0)){
                for(int i=0;pP+i*7<=number;i++){
                    if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==7&&pP%8==0){
            if(a<0 && (a%7==0)){
                for(int i=0;pP-i*7>=number;i++){
                    if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==7&&pP%8==7){
            if(a<0 && (a%9==0)){
                for(int i=0;pP-i*9>=number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP%8==0){
            if(a>0 && (a%9==0)){
                if(a/9+pP/8>=8) return 1;
                for(int i=0;pP+i*9<=number;i++){
                    if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%7==0)){
                if((-a)/7>(pP/8)) return 1;
                for(int i=0;pP-i*7>=number;i++){
                    if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP%8==7){
            if(a>0 && (a%7==0)){
                if(a/7+pP/8>=8) return 1;
                for(int i=0;pP+i*7<=number;i++){
                    if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%9==0)){
                if((-a)/9>pP/8) return 1;
                for(int i=0;pP-i*9>=number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==0){
            if(a>0 && (a%7==0)){
                if(a/7>pP%8) return 1;
                for(int i=0;pP+i*7<=number;i++){
                    if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a>0 && (a%9==0)){
                if(a/9+pP%8>=8) return 1;
                for(int i=0;pP+i*9<=number;i++){
                    if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==7){
            if(a<0 && (a%9==0)){
                if((-a)/9>pP%8) return 1;
                for(int i=0;pP-i*9>=number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%7==0)){
                if((-a)/7+pP%8>7) return 1;
                for(int i=0;pP-i*7>=number;i++){
                    if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(a>0 && (a%9==0)){
            if(a/9+pP/8>7) return 1;
            for(int i=0;pP+i*9<=number;i++){
                if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        if(a>0 && (a%7==0)){
            if(a/7>pP/8) return 1;
            for(int i=0;pP+i*7<=number;i++){
                if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        if(a<0 && (a%9==0)){
            if((-a)/9>pP%8) return 1;
            for(int i=0;pP-i*9>=number;i++){
                if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        if(a<0 && (a%7==0)){
            if((-a)/7+pP%8>7) return 1;
            for(int i=0;pP-i*7>=number;i++){
                if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        return 1;
    }
    public void moveBishopW(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if (limitBishop()==0){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            if(checksafe==0){
                if(wkingischecked()==1){
                    number=tnum;
                    pP=tpP;
                    theBoard[number]=btnum;
                    theBoard[pP]=btpP;
                }else{
                    moveCount();
                }
            }
        }
    }
    public void moveBishopB(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if (limitBishop()==0){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            if(checksafe==0){
                if(bkingischecked()==1){
                    number=tnum;
                    pP=tpP;
                    theBoard[number]=btnum;
                    theBoard[pP]=btpP;
                }else{
                    moveCount();
                }
            }
        }
    }
    public void moveQueenW(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if((limitBishop()==0) || ((number/8==pP/8 || number%8==pP%8) && betweenRook()==0)){
            if (theBoard[number]=='*' || theBoard[number]>'Z'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(wkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        }
    }
    public void moveQueenB(){
        int tnum=number;
        int tpP=pP;
        char btnum=theBoard[number];
        char btpP=theBoard[pP];
        if((limitBishop()==0) || ((number/8==pP/8 || number%8==pP%8) && betweenRook()==0)){
            if (theBoard[number]=='*' || theBoard[number]<'a'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                if(checksafe==0){
                    if(bkingischecked()==1){
                        number=tnum;
                        pP=tpP;
                        theBoard[number]=btnum;
                        theBoard[pP]=btpP;
                    }else{
                        moveCount();
                    }
                }
            }
        }
    }
    public int limitKing(){
        if(pP%8==0 && pP/8!=0 && pP/8!=7){
            if((pP-number==8)||(pP-number==7)||(number-pP==1)||(number-pP==8)||(number-pP==9)){
                return 0;
            }
        }
        if(pP%8==0 && pP/8==0){
            if((number-pP==1)||(number-pP==8)||(number-pP==9)){
                return 0;
            }
        }
        if(pP%8==0 && pP/8==7){
            if((pP-number==8)||(pP-number==7)||(number-pP==1)){
                return 0;
            }
        }
        if(pP%8==7 && pP/8==0){
            if((pP-number==1)||(number-pP==9)||(number-pP==8)){
                return 0;
            }
        }
        if(pP%8==7 && pP/8==7){
            if((pP-number==1)||(pP-number==9)||(pP-number==8)){
                return 0;
            }
        }
        if(pP%8==0 && pP/8!=0 && pP/8!=7){
            if((pP-number==1)||(pP-number==9)||(pP-number==8)||(number-pP==7)||(number-pP==8)){
                return 0;
            }
        }
        if(pP/8==0 && pP%8!=0 && pP%8!=7){
            if((pP-number==1)||(number-pP==1)||(number-pP==8)||(number-pP==7)||(number-pP==9)){
                return 0;
            }
        }
        if(pP/8==7 && pP%8!=0 && pP%8!=7){
            if((pP-number==1)||(number-pP==1)||(pP-number==8)||(pP-number==7)||(pP-number==9)){
                return 0;
            }
        }
        if((pP-number==1)||(number-pP==1)||(pP-number==8)||(pP-number==7)||(pP-number==9)||(number-pP==7)||(number-pP==8)||(number-pP==9)){
            return 0;
        }
        return 1;
    }
    public void moveKingW(){
        if ((number==2)&&(theBoard[0]=='R')&&(theBoard[1]=='*')&&(theBoard[2]=='*')&&(theBoard[3]=='*')&&(theBoard[4]=='K')){
            theBoard[0]='*';
            theBoard[1]='*';
            theBoard[2]='K';
            theBoard[3]='R';
            theBoard[4]='*';
            moveCountVar+=1;
        }else{
            if ((number==6)&&(theBoard[7]=='R')&&(theBoard[6]=='*')&&(theBoard[5]=='*')&&(theBoard[4]=='K')){
                theBoard[7]='*';
                theBoard[6]='K';
                theBoard[5]='R';
                theBoard[4]='*';
                moveCountVar+=1;
            }else{
                int tnum=number;
                int tpP=pP;
                char btnum=theBoard[number];
                char btpP=theBoard[pP];
                if(limitKing()==0){
                    theBoard[number]=theBoard[pP];
                    theBoard[pP]='*';
                    if(checksafe==0){
                        if(wkingischecked()==1){
                            number=tnum;
                            pP=tpP;
                            theBoard[number]=btnum;
                            theBoard[pP]=btpP;
                        }else{
                            moveCount();
                        }
                    }
                }
            }
        }
    }
    public void moveKingB(){
        if ((number==58)&&(theBoard[56]=='r')&&(theBoard[57]=='*')&&(theBoard[58]=='*')&&(theBoard[59]=='*')&&(theBoard[60]=='k')){
            theBoard[56]='*';
            theBoard[57]='*';
            theBoard[58]='k';
            theBoard[59]='r';
            theBoard[60]='*';
            moveCountVar-=1;
        }else{
            if ((number==62)&&(theBoard[63]=='r')&&(theBoard[62]=='*')&&(theBoard[61]=='*')&&(theBoard[60]=='k')){
                theBoard[63]='*';
                theBoard[62]='k';
                theBoard[61]='r';
                theBoard[60]='*';
                moveCountVar-=1;
            }else{
                int tnum=number;
                int tpP=pP;
                char btnum=theBoard[number];
                char btpP=theBoard[pP];
                if(limitKing()==0){
                    theBoard[number]=theBoard[pP];
                    theBoard[pP]='*';
                    if(checksafe==0){
                        if(bkingischecked()==1){
                            number=tnum;
                            pP=tpP;
                            theBoard[number]=btnum;
                            theBoard[pP]=btpP;
                        }else{
                            moveCount();
                        }
                    }
                }
            }
        }
    }
    public int wkingischecked(){
        checkking=1;
        checksafe=1;
        int tnum=number,tnum2;
        int tpP=pP,tpP2;
        char btnum=theBoard[number],btnum2;
        char btpP=theBoard[pP],btpP2;
        for(int i=0;i<64;i++){
            if (theBoard[i]=='K'){
                number=i;
            }
        }
        for(int i=0;i<64;i++){
            if(theBoard[i]>='a'&&theBoard[i]<='z'){
                pP=i;
                tnum2=number;
                tpP2=pP;
                btnum2=theBoard[number];
                btpP2=theBoard[pP];
                makeMove();
                if(theBoard[number]!='K'){
                    number=tnum2;
                    pP=tpP2;
                    theBoard[pP]=btpP2;
                    theBoard[number]=btnum2;
                    number=tnum;
                    pP=tpP;
                    theBoard[pP]=btpP;
                    theBoard[number]=btnum;
                    checkking=0;
                    checksafe=0;
                    return 1;
                } else{
                    theBoard[tpP]=btpP;
                    theBoard[tnum]=btnum;
                    number=tnum2;
                }
            }
        }
        number=tnum;
        pP=tpP;
        theBoard[pP]=btpP;
        theBoard[number]=btnum;
        checkking=0;
        checksafe=0;
        return 0;
    }
    public int bkingischecked(){
        checkking=1;
        checksafe=1;
        int tnum=number,tnum2;
        int tpP=pP,tpP2;
        char btnum=theBoard[number],btnum2;
        char btpP=theBoard[pP],btpP2;
        for(int i=0;i<64;i++){
            if (theBoard[i]=='k'){
                number=i;
            }
        }
        for(int i=0;i<64;i++){
            if(theBoard[i]>='A'&&theBoard[i]<='Z'){
                pP=i;
                tnum2=number;
                tpP2=pP;
                btnum2=theBoard[number];
                btpP2=theBoard[pP];
                makeMove();
                if(theBoard[number]!='k'){
                    number=tnum2;
                    pP=tpP2;
                    theBoard[pP]=btpP2;
                    theBoard[number]=btnum2;
                    number=tnum;
                    pP=tpP;
                    theBoard[pP]=btpP;
                    theBoard[number]=btnum;
                    checkking=0;
                    checksafe=0;
                    return 1;
                } else{
                    theBoard[tpP]=btpP;
                    theBoard[tnum]=btnum;
                    number=tnum2;
                }
            }
        }
        number=tnum;
        pP=tpP;
        theBoard[pP]=btpP;
        theBoard[number]=btnum;
        checkking=0;
        checksafe=0;
        return 0;
    }
    public void drawBoardPieces() {
        if(phe==1){
            for (int j = 0; j <64; j++) {
                int i=63-j;
                switch (theBoard[j]) {
                    case '*':
                        chessImage[i].setImageResource(R.drawable.empty);
                        break;
                    case 'P':
                        chessImage[i].setImageResource(R.drawable.wp);
                        break;
                    case 'R':
                        chessImage[i].setImageResource(R.drawable.wr);
                        break;
                    case 'N':
                        chessImage[i].setImageResource(R.drawable.wn);
                        break;
                    case 'B':
                        chessImage[i].setImageResource(R.drawable.wb);
                        break;
                    case 'Q':
                        chessImage[i].setImageResource(R.drawable.wq);
                        break;
                    case 'K':
                        chessImage[i].setImageResource(R.drawable.wk);
                        break;
                    case 'p':
                        chessImage[i].setImageResource(R.drawable.bp);
                        break;
                    case 'r':
                        chessImage[i].setImageResource(R.drawable.br);
                        break;
                    case 'n':
                        chessImage[i].setImageResource(R.drawable.bn);
                        break;
                    case 'b':
                        chessImage[i].setImageResource(R.drawable.bb);
                        break;
                    case 'q':
                        chessImage[i].setImageResource(R.drawable.bq);
                        break;
                    case 'k':
                        chessImage[i].setImageResource(R.drawable.bk);
                        break;
                }
            }
        }else{
            for (int i = 0; i < 64; i++) {
                switch (theBoard[i]) {
                    case '*':
                        chessImage[i].setImageResource(R.drawable.empty);
                        break;
                    case 'P':
                        chessImage[i].setImageResource(R.drawable.wp);
                        break;
                    case 'R':
                        chessImage[i].setImageResource(R.drawable.wr);
                        break;
                    case 'N':
                        chessImage[i].setImageResource(R.drawable.wn);
                        break;
                    case 'B':
                        chessImage[i].setImageResource(R.drawable.wb);
                        break;
                    case 'Q':
                        chessImage[i].setImageResource(R.drawable.wq);
                        break;
                    case 'K':
                        chessImage[i].setImageResource(R.drawable.wk);
                        break;
                    case 'p':
                        chessImage[i].setImageResource(R.drawable.bp);
                        break;
                    case 'r':
                        chessImage[i].setImageResource(R.drawable.br);
                        break;
                    case 'n':
                        chessImage[i].setImageResource(R.drawable.bn);
                        break;
                    case 'b':
                        chessImage[i].setImageResource(R.drawable.bb);
                        break;
                    case 'q':
                        chessImage[i].setImageResource(R.drawable.bq);
                        break;
                    case 'k':
                        chessImage[i].setImageResource(R.drawable.bk);
                        break;
                }
            }
        }

    }
    /*
    public void makeMove() {
        if (theBoard[pP] == 'R') {
            moveRookW();
        }
        if (theBoard[pP] == 'r') {
            moveRookB();
        }
        if (theBoard[pP] == 'P') {
            movePawnW();
        }
        if (theBoard[pP] == 'p') {
            movePawnB();
        }
        if (theBoard[pP] == 'N') {
            moveKnightW();
        }
        if (theBoard[pP] == 'n') {
            moveKnightB();
        }
        if (theBoard[pP] == 'B') {
            moveBishopW();
        }
        if (theBoard[pP] == 'b') {
            moveBishopB();
        }
        if (theBoard[pP] == 'Q') {
            moveQueenW();
        }
        if (theBoard[pP] == 'q') {
            moveQueenB();
        }
        if (theBoard[pP] == 'K') {
            moveKingW();
        }
        if (theBoard[pP] == 'k') {
            moveKingB();
        }
    }
    public int betweenRook(){
        if(number/8==pP/8){
            if(number<pP){
                for (int i=pP;i>=number+1;i--){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
            if(number>pP){
                for (int i=pP;i<=number-1;i++){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
        }
        if(number%8==pP%8){
            if(number<pP){
                for (int i=pP;i>=number+8;i=i-8){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
            if(number>pP){
                for (int i=pP;i<=number-8;i=i+8){
                    if(theBoard[i]!='*'){
                        dem+=1;
                    }
                }
                if(dem>1){
                    dem=0;
                    return 1;
                } else{
                    dem=0;
                    return 0;
                }
            }
        }
        return 1;
    }
    public void moveRookW(){
        if((number/8==pP/8 || number%8==pP%8) && betweenRook()==0) {
            if (theBoard[number]=='*' || theBoard[number]>'Z'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        }
    }
    public void moveRookB(){
        if((number/8==pP/8 || number%8==pP%8) && betweenRook()==0) {
            if (theBoard[number]=='*' || theBoard[number]<'a'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        }
    }
    public void movePawnW(){
        if(pP>=8 && pP<=15){
            if(((number==pP+8 || number==pP+16)&&theBoard[number]=='*')||(number==pP+9&&(theBoard[number]>='a'&&theBoard[number]<='z'))||(number==pP+7&&(theBoard[number]>='a'&&theBoard[number]<='z'))){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        } else{
            if((number==pP+8&&theBoard[number]=='*')||(number==pP+9&&(theBoard[number]>='a'&&theBoard[number]<='z'))||(number==pP+7&&(theBoard[number]>='a'&&theBoard[number]<='z'))) {
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                moveCount();
            }
        }
    }
    public void movePawnB(){
        if(pP>=48 && pP<=55){
            if(((number==pP-8||number==pP-16)&&theBoard[number]=='*')||(number==pP-9&&(theBoard[number]>='A'&&theBoard[number]<='Z'))||(number==pP-7&&(theBoard[number]>='A'&&theBoard[number]<='Z'))){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        } else{
            if((number==pP-8&&theBoard[number]=='*')||(number==pP-9&&(theBoard[number]>='A'&&theBoard[number]<='Z'))||(number==pP-7&&(theBoard[number]>='A'&&theBoard[number]<='Z'))){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        }
    }
    public void moveKnightW(){
        if((number==pP+15||number==pP+6||number==pP+17||number==pP+10||number==pP-15||number==pP-6||number==pP-17||number==pP-10)&&(theBoard[number]=='*'||theBoard[number]>'Z')){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            moveCount();
        }
    }
    public void moveKnightB(){
        if((number==pP+15||number==pP+6||number==pP+17||number==pP+10||number==pP-15||number==pP-6||number==pP-17||number==pP-10)&&(theBoard[number]=='*'||theBoard[number]<'a')){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            moveCount();
        }
    }
    public int limitBishop(){
        int a=number-pP;
        if(pP/8==0&&pP%8==0){
            if(a>0 && (a%9==0)){
                for(int i=0;pP+i*9==number;i++){
                    if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==0&&pP%8==7){
            if(a>0 && (a%7==0)){
                for(int i=0;pP+i*7==number;i++){
                    if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==7&&pP%8==0){
            if(a<0 && (a%7==0)){
                for(int i=0;pP-i*7==number;i++){
                    if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==7&&pP%8==7){
            if(a<0 && (a%9==0)){
                for(int i=0;pP-i*9==number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP%8==0){
            if(a>0 && (a%9==0)){
                for(int i=0;pP+i*9==number;i++){
                    if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%7==0)){
                for(int i=0;pP-i*7==number;i++){
                    if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP%8==7){
            if(a>0 && (a%7==0)){
                for(int i=0;pP+i*7==number;i++){
                    if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%9==0)){
                for(int i=0;pP-i*9==number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==0){
            if(a>0 && (a%7==0)){
                for(int i=0;pP+i*7==number;i++){
                    if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%7==0)){
                for(int i=0;pP-i*7==number;i++){
                    if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(pP/8==7){
            if(a<0 && (a%9==0)){
                for(int i=0;pP-i*9==number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
            if(a<0 && (a%9==0)){
                for(int i=0;pP-i*9==number;i++){
                    if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                        return 1;
                    }
                }
                return 0;
            }
        }
        if(a>0 && (a%9==0)){
            for(int i=0;pP+i*9==number;i++){
                if ((theBoard[i*9+pP]!=theBoard[number])&&(theBoard[i*9+pP]!='*')&&(theBoard[i*9+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        if(a>0 && (a%7==0)){
            for(int i=0;pP+i*7==number;i++){
                if ((theBoard[i*7+pP]!=theBoard[number])&&(theBoard[i*7+pP]!='*')&&(theBoard[i*7+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        if(a<0 && (a%9==0)){
            for(int i=0;pP-i*9==number;i++){
                if ((theBoard[-i*9+pP]!=theBoard[number])&&(theBoard[-i*9+pP]!='*')&&(theBoard[-i*9+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        if(a<0 && (a%7==0)){
            for(int i=0;pP-i*7==number;i++){
                if ((theBoard[-i*7+pP]!=theBoard[number])&&(theBoard[-i*7+pP]!='*')&&(theBoard[-i*7+pP]!=theBoard[pP])){
                    return 1;
                }
            }
            return 0;
        }
        return 1;
    }
    public void moveBishopW(){
        if (limitBishop()==0){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            moveCount();
        }
    }
    public void moveBishopB(){
        if (limitBishop()==0){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            moveCount();
        }
    }
    public void moveQueenW(){
        if((limitBishop()==0) || ((number/8==pP/8 || number%8==pP%8) && betweenRook()==0)){
            if (theBoard[number]=='*' || theBoard[number]>'Z'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        }
    }
    public void moveQueenB(){
        if((limitBishop()==0) || ((number/8==pP/8 || number%8==pP%8) && betweenRook()==0)){
            if (theBoard[number]=='*' || theBoard[number]<'a'){
                theBoard[number]=theBoard[pP];
                theBoard[pP]='*';
                moveCount();
            }
        }
    }
    public int limitKing(){
        if(pP%8==0 && pP/8!=0 && pP/8!=7){
            if((pP-number==8)||(pP-number==7)||(number-pP==1)||(number-pP==8)||(number-pP==9)){
                return 0;
            }
        }
        if(pP%8==0 && pP/8==0){
            if((number-pP==1)||(number-pP==8)||(number-pP==9)){
                return 0;
            }
        }
        if(pP%8==0 && pP/8==7){
            if((pP-number==8)||(pP-number==7)||(number-pP==1)){
                return 0;
            }
        }
        if(pP%8==7 && pP/8==0){
            if((pP-number==1)||(number-pP==9)||(number-pP==8)){
                return 0;
            }
        }
        if(pP%8==7 && pP/8==7){
            if((pP-number==1)||(pP-number==9)||(pP-number==8)){
                return 0;
            }
        }
        if(pP%8==0 && pP/8!=0 && pP/8!=7){
            if((pP-number==1)||(pP-number==9)||(pP-number==8)||(number-pP==7)||(number-pP==8)){
                return 0;
            }
        }
        if(pP/8==0 && pP%8!=0 && pP%8!=7){
            if((pP-number==1)||(number-pP==1)||(number-pP==8)||(number-pP==7)||(number-pP==9)){
                return 0;
            }
        }
        if(pP/8==7 && pP%8!=0 && pP%8!=7){
            if((pP-number==1)||(number-pP==1)||(pP-number==8)||(pP-number==7)||(pP-number==9)){
                return 0;
            }
        }
        if((pP-number==1)||(number-pP==1)||(pP-number==8)||(pP-number==7)||(pP-number==9)||(number-pP==7)||(number-pP==8)||(number-pP==9)){
            return 0;
        }
        return 1;
    }
    public void moveKingW(){
        if(limitKing()==0){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            moveCount();
        }
    }
    public void moveKingB(){
        if(limitKing()==0){
            theBoard[number]=theBoard[pP];
            theBoard[pP]='*';
            moveCount();
        }
    }
    public void drawBoardPieces() {
        if(phe==1){
            for (int j = 0; j <64; j++) {
                int i=63-j;
                switch (theBoard[j]) {
                    case '*':
                        chessImage[i].setImageResource(R.drawable.empty);
                        break;
                    case 'P':
                        chessImage[i].setImageResource(R.drawable.wp);
                        break;
                    case 'R':
                        chessImage[i].setImageResource(R.drawable.wr);
                        break;
                    case 'N':
                        chessImage[i].setImageResource(R.drawable.wn);
                        break;
                    case 'B':
                        chessImage[i].setImageResource(R.drawable.wb);
                        break;
                    case 'Q':
                        chessImage[i].setImageResource(R.drawable.wq);
                        break;
                    case 'K':
                        chessImage[i].setImageResource(R.drawable.wk);
                        break;
                    case 'p':
                        chessImage[i].setImageResource(R.drawable.bp);
                        break;
                    case 'r':
                        chessImage[i].setImageResource(R.drawable.br);
                        break;
                    case 'n':
                        chessImage[i].setImageResource(R.drawable.bn);
                        break;
                    case 'b':
                        chessImage[i].setImageResource(R.drawable.bb);
                        break;
                    case 'q':
                        chessImage[i].setImageResource(R.drawable.bq);
                        break;
                    case 'k':
                        chessImage[i].setImageResource(R.drawable.bk);
                        break;
                }
            }
        }else{
            for (int i = 0; i < 64; i++) {
                switch (theBoard[i]) {
                    case '*':
                        chessImage[i].setImageResource(R.drawable.empty);
                        break;
                    case 'P':
                        chessImage[i].setImageResource(R.drawable.wp);
                        break;
                    case 'R':
                        chessImage[i].setImageResource(R.drawable.wr);
                        break;
                    case 'N':
                        chessImage[i].setImageResource(R.drawable.wn);
                        break;
                    case 'B':
                        chessImage[i].setImageResource(R.drawable.wb);
                        break;
                    case 'Q':
                        chessImage[i].setImageResource(R.drawable.wq);
                        break;
                    case 'K':
                        chessImage[i].setImageResource(R.drawable.wk);
                        break;
                    case 'p':
                        chessImage[i].setImageResource(R.drawable.bp);
                        break;
                    case 'r':
                        chessImage[i].setImageResource(R.drawable.br);
                        break;
                    case 'n':
                        chessImage[i].setImageResource(R.drawable.bn);
                        break;
                    case 'b':
                        chessImage[i].setImageResource(R.drawable.bb);
                        break;
                    case 'q':
                        chessImage[i].setImageResource(R.drawable.bq);
                        break;
                    case 'k':
                        chessImage[i].setImageResource(R.drawable.bk);
                        break;
                }
            }
        }

    }
    */
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