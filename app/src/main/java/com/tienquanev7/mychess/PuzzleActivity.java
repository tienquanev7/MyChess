package com.tienquanev7.mychess;
import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PuzzleActivity extends AppCompatActivity {

    ImageView x63, x62, x61, x60, x59, x58, x57, x56, x55, x54, x53, x52, x51, x50, x49, x48, x47, x46, x45,
            x44, x43, x42, x41, x40, x39, x38, x37, x36, x35, x34, x33, x32, x31, x30, x29, x28, x27, x26, x25, x24,
            x23, x22, x21, x20, x19, x18, x17, x16, x15, x14, x13, x12, x11, x10, x9, x8, x7, x6, x5, x4, x3, x2, x1, x0;

    int[] imageViews = {R.id.p0, R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6, R.id.p7, R.id.p8, R.id.p9,
            R.id.p10, R.id.p11, R.id.p12, R.id.p13, R.id.p14, R.id.p15, R.id.p16, R.id.p17, R.id.p18, R.id.p19,
            R.id.p20, R.id.p21, R.id.p22, R.id.p23, R.id.p24, R.id.p25, R.id.p26, R.id.p27, R.id.p28, R.id.p29,
            R.id.p30, R.id.p31, R.id.p32, R.id.p33, R.id.p34, R.id.p35, R.id.p36, R.id.p37, R.id.p38, R.id.p39,
            R.id.p40, R.id.p41, R.id.p42, R.id.p43, R.id.p44, R.id.p45, R.id.p46, R.id.p47, R.id.p48, R.id.p49,
            R.id.p50, R.id.p51, R.id.p52, R.id.p53, R.id.p54, R.id.p55, R.id.p56, R.id.p57, R.id.p58, R.id.p59,
            R.id.p60, R.id.p61, R.id.p62, R.id.p63};

    ImageView[] chessImage = {x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15,
            x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30, x31,
            x32, x33, x34, x35, x36, x37, x38, x39, x40, x41, x42, x43, x44, x45, x46, x47,
            x48, x49, x50, x51, x52, x53, x54, x55, x56, x57, x58, x59, x60, x61, x62, x63};

    char[] theBoard = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', '*', '*', '*', '*',
            '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*',
            '*', '*', '*', '*', '*', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
    char[] theBoard2 = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', '*', '*', '*', '*',
            '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*',
            '*', '*', '*', '*', '*', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
    char[] theBoardbackup = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', '*', '*', '*', '*',
            '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*',
            '*', '*', '*', '*', '*', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};

    String s = "",boardrt,movert;
    TextView tx1,tx2,txfen;
    Button btn1,btn2;
    String[] move2;
    int check2=0,puzzledone=0,tonumlength=0,makemovedone=0,dem2=1,undonum=0,undopP=0,ratingrt,phe,pP,dem=0,number,moveCountVar=0,change,checkking=0,checksafe=0,j=0,i=0,maxarr;
    Puzzle aP[] = new Puzzle[500000];
    Puzzle aP2[] = new Puzzle[100];
    SharedPreferences sharedPreferences;
    int[] tonum=new int[100],arr=new int[500000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        tx1=(TextView) findViewById(R.id.editTextTextPersonName);
        tx2=(TextView) findViewById(R.id.editTextTextPersonName2);
        txfen=(TextView) findViewById(R.id.textView);
        btn1=(Button) findViewById(R.id.button);
        btn2=(Button) findViewById(R.id.button2);
        sharedPreferences=getSharedPreferences("saveratingfilter",MODE_PRIVATE);
        tx1.setText(sharedPreferences.getString("min",""));
        tx2.setText(sharedPreferences.getString("max",""));

        for (int i = 0; i < 64; i++) {
            chessImage[i] = (ImageView) findViewById(imageViews[i]);
        }

        drawBoardPieces();

        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("chesspuzzle.csv"), "UTF-8"));
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                int rating=Integer.parseInt(row[0]);
                aP[j]=new Puzzle(rating,row[1],row[2]);
                arr[j]=rating;
                j+=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        maxarr=j;
        btn1.setOnClickListener(view -> {
            search();
        });
        btn2.setOnClickListener(view -> {
            if(check2==0) check2=1; else check2=0;
            if (check2==1){
                btn2.setText("Next Move");
                for(int i=0;i<64;i++){
                    theBoard2[i]=theBoard[i];
                }
                for(int i=0;i<64;i++){
                    theBoard[i]=theBoardbackup[i];
                }
                drawBoardPieces();
                for(int i=0;i<64;i++){
                    theBoard[i]=theBoard2[i];
                }
            }else {
                drawBoardPieces();
                btn2.setText("Previous Move");
            }
        });
    }
    public void search(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("min",tx1.getText().toString());
        editor.putString("max",tx2.getText().toString());
        editor.commit();
        dem2=1;
        puzzledone=0;
        btn2.setVisibility (View.VISIBLE);
        int min = Integer.parseInt(tx1.getText().toString());
        int max = Integer.parseInt(tx2.getText().toString());
        int h = 0;
        /*for (int i = 0; i < maxarr; i++) {
            ratingrt = aP[i].getRatingrt();
            movert = aP[i].getMovert();
            boardrt = aP[i].getBoardrt();
            if (ratingrt >= min && ratingrt <= max) {
                aP2[h]=new Puzzle(ratingrt,boardrt,movert);
                h += 1;
            }
        }*/
        int ramin=binarySearch(arr,0,maxarr-1,min-1);
        int ramax=binarySearch(arr,0,maxarr-1,max+1);
        Random rand = new Random();
        int n = rand.nextInt(ramax-ramin+1)+ramin;
        ratingrt = aP[n].getRatingrt();
        movert = aP[n].getMovert();
        boardrt = aP[n].getBoardrt();
        txfen.setText(boardrt);
        String[] board2 = boardrt.split(" ");
        String[] board2line = board2[0].split("/");
        for (int i = 0; i < 8; i++) {
            int dem = 0;
            int blength=board2line[i].length();
            for (int j = 0; j < blength; j++) {
                if (board2line[i].charAt(j) >= '0' && board2line[i].charAt(j) <= '9') {
                    int z = Integer.parseInt(String.valueOf(board2line[i].charAt(j)));
                    for (int l = 0; l < z; l++) {
                        theBoard[dem + 8 * (7 - i)] = '*';
                        dem += 1;
                    }
                }else{
                    theBoard[dem + 8 * (7 - i)] = board2line[i].charAt(j);
                    dem += 1;
                }
            }
        }
        if(stringCompare(board2[1],"b")==0){
            phe=0;
            moveCountVar=0;
        } else{
            moveCountVar=1;
            phe=1;
        }
        for(int i=0;i<tonum.length;i++){
            tonum[i]=0;
        }
        move2 = movert.split(" ");
        for(int i=0;i<move2.length;i++){
            tonum[2*i]=stonum(move2[i].charAt(0),move2[i].charAt(1));
            tonum[2*i+1]=stonum(move2[i].charAt(2),move2[i].charAt(3));
        }
        tonumlength=move2.length-1;
        for(int i=0;i<64;i++){
            theBoardbackup[i]=theBoard[i];
        }
        theBoard[tonum[1]]=theBoard[tonum[0]];
        theBoard[tonum[0]]='*';
        drawBoardPieces();
    }
    public int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }
    public int stonum(char a,char b){
        int c=0,d,num;
        d=Integer.parseInt(String.valueOf(b))-1;
        if(a=='a') c=0;
        if(a=='b') c=1;
        if(a=='c') c=2;
        if(a=='d') c=3;
        if(a=='e') c=4;
        if(a=='f') c=5;
        if(a=='g') c=6;
        if(a=='h') c=7;
        num=c+8*d;
        return num;
    }
    public void moveCount() {
        if (checkking == 0) {
            if (theBoard[number] >= 'a' && theBoard[number] <= 'z') {
                moveCountVar -= 1;
            } else {
                moveCountVar += 1;
            }
        }
    }
    public void moveablePiece(View view) {
        number = Integer.parseInt(view.getTag().toString());
        for(int i=0;i<64;i++){
            theBoard2[i]=theBoard[i];
        }
        if(phe==1){
            number=63-number;
        }
        if(moveCountVar==0){
            if (theBoard[number]>='A' && theBoard[number]<='Z'){
                pP=number;
            } else {
                makeMove();
                if(makemovedone==1){
                    btn2.setVisibility (View.INVISIBLE);
                    if(number==tonum[dem2*2+1]&&pP==tonum[dem2*2]){
                        if (dem2==tonumlength){
                            Toast.makeText(this, "Puzzle is done!", Toast.LENGTH_SHORT).show();
                            puzzledone=1;
                        }else{
                            dem2+=1;
                            theBoard[tonum[dem2*2+1]]=theBoard[tonum[dem2*2]];
                            theBoard[tonum[dem2*2]]='*';
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                            dem2+=1;
                        }
                    }else{
                        if(puzzledone==0){
                            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<64;i++){
                                theBoard[i]=theBoard2[i];
                            }
                            number=undonum;
                            pP=undopP;
                            drawBoardPieces();
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                        }
                    }
                    makemovedone=0;
                }
            }
        } else {
            if (theBoard[number] >= 'a' && theBoard[number] <= 'z') {
                pP = number;
            } else {
                makeMove();
                if(makemovedone==1){
                    btn2.setVisibility (View.INVISIBLE);
                    if(number==tonum[dem2*2+1]&&pP==tonum[dem2*2]){
                        if (dem2==tonumlength){
                            Toast.makeText(this, "Puzzle is done!", Toast.LENGTH_SHORT).show();
                            puzzledone=1;
                        }else{
                            dem2+=1;
                            theBoard[tonum[dem2*2+1]]=theBoard[tonum[dem2*2]];
                            theBoard[tonum[dem2*2]]='*';
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                            dem2+=1;
                        }
                    }else{
                        if(puzzledone==0){
                            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<64;i++){
                                theBoard[i]=theBoard2[i];
                            }
                            number=undonum;
                            pP=undopP;
                            drawBoardPieces();
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                        }
                    }
                    makemovedone=0;
                }
            }
        }
        if (moveCountVar == 0) {
            if (theBoard[number] >= 'A' && theBoard[number] <= 'Z') {
                pP = number;
            } else {
                makeMove();
                if(makemovedone==1){
                    btn2.setVisibility (View.INVISIBLE);
                    if(number==tonum[dem2*2+1]&&pP==tonum[dem2*2]){
                        if (dem2==tonumlength){
                            Toast.makeText(this, "Puzzle is done!", Toast.LENGTH_SHORT).show();
                            puzzledone=1;
                        }else{
                            dem2+=1;
                            theBoard[tonum[dem2*2+1]]=theBoard[tonum[dem2*2]];
                            theBoard[tonum[dem2*2]]='*';
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                            dem2+=1;
                        }
                    }else{
                        if(puzzledone==0){
                            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<64;i++){
                                theBoard[i]=theBoard2[i];
                            }
                            number=undonum;
                            pP=undopP;
                            drawBoardPieces();
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                        }
                    }
                    makemovedone=0;
                }
            }
        } else {
            if (theBoard[number] >= 'a' && theBoard[number] <= 'z') {
                pP = number;
            } else {
                makeMove();
                if(makemovedone==1){
                    btn2.setVisibility (View.INVISIBLE);
                    if(number==tonum[dem2*2+1]&&pP==tonum[dem2*2]){
                        if (dem2==tonumlength){
                            Toast.makeText(this, "Puzzle is done!", Toast.LENGTH_SHORT).show();
                            puzzledone=1;
                        }else{
                            dem2+=1;
                            theBoard[tonum[dem2*2+1]]=theBoard[tonum[dem2*2]];
                            theBoard[tonum[dem2*2]]='*';
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                            dem2+=1;
                        }
                    }else{
                        if(puzzledone==0){
                            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<64;i++){
                                theBoard[i]=theBoard2[i];
                            }
                            number=undonum;
                            pP=undopP;
                            drawBoardPieces();
                            if(moveCountVar==0) moveCountVar=1; else moveCountVar=0;
                        }
                    }
                    makemovedone=0;
                }
            }
        }
        drawBoardPieces();
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
    public int betweenRook() {
        if (number / 8 == pP / 8) {
            if (number < pP) {
                for (int i = pP; i >= number + 1; i--) {
                    if (theBoard[i] != '*') {
                        dem += 1;
                    }
                }
                if (dem > 1) {
                    dem = 0;
                    return 1;
                } else {
                    dem = 0;
                    return 0;
                }
            }
            if (number > pP) {
                for (int i = pP; i <= number - 1; i++) {
                    if (theBoard[i] != '*') {
                        dem += 1;
                    }
                }
                if (dem > 1) {
                    dem = 0;
                    return 1;
                } else {
                    dem = 0;
                    return 0;
                }
            }
        }
        if (number % 8 == pP % 8) {
            if (number < pP) {
                for (int i = pP; i >= number + 8; i = i - 8) {
                    if (theBoard[i] != '*') {
                        dem += 1;
                    }
                }
                if (dem > 1) {
                    dem = 0;
                    return 1;
                } else {
                    dem = 0;
                    return 0;
                }
            }
            if (number > pP) {
                for (int i = pP; i <= number - 8; i = i + 8) {
                    if (theBoard[i] != '*') {
                        dem += 1;
                    }
                }
                if (dem > 1) {
                    dem = 0;
                    return 1;
                } else {
                    dem = 0;
                    return 0;
                }
            }
        }
        return 1;
    }
    public void moveRookW() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if ((number / 8 == pP / 8 || number % 8 == pP % 8) && betweenRook() == 0) {
            if (theBoard[number] == '*' || theBoard[number] > 'Z') {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (wkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        }
    }
    public void moveRookB() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if ((number / 8 == pP / 8 || number % 8 == pP % 8) && betweenRook() == 0) {
            if (theBoard[number] == '*' || theBoard[number] < 'a') {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (bkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        }
    }
    public void movePawnW() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if (pP >= 8 && pP <= 15) {
            if (((number == pP + 8 || number == pP + 16) && theBoard[number] == '*') || (number == pP + 9 && (theBoard[number] >= 'a' && theBoard[number] <= 'z')) || (number == pP + 7 && (theBoard[number] >= 'a' && theBoard[number] <= 'z'))) {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (wkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        } else {
            if ((number == pP + 8 && theBoard[number] == '*') || (number == pP + 9 && (theBoard[number] >= 'a' && theBoard[number] <= 'z')) || (number == pP + 7 && (theBoard[number] >= 'a' && theBoard[number] <= 'z'))) {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (wkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        }
    }
    public void movePawnB() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if (pP >= 48 && pP <= 55) {
            if (((number == pP - 8 || number == pP - 16) && theBoard[number] == '*') || (number == pP - 9 && (theBoard[number] >= 'A' && theBoard[number] <= 'Z')) || (number == pP - 7 && (theBoard[number] >= 'A' && theBoard[number] <= 'Z'))) {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (bkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        } else {
            if ((number == pP - 8 && theBoard[number] == '*') || (number == pP - 9 && (theBoard[number] >= 'A' && theBoard[number] <= 'Z')) || (number == pP - 7 && (theBoard[number] >= 'A' && theBoard[number] <= 'Z'))) {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (bkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        }
    }
    public void moveKnightW() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if ((number == pP + 15 || number == pP + 6 || number == pP + 17 || number == pP + 10 || number == pP - 15 || number == pP - 6 || number == pP - 17 || number == pP - 10) && (theBoard[number] == '*' || theBoard[number] > 'Z')) {
            undonum=number;
            undopP=pP;
            theBoard[number] = theBoard[pP];
            theBoard[pP] = '*';
            makemovedone=1;
            if (checksafe == 0) {
                if (wkingischecked() == 1) {
                    number = tnum;
                    pP = tpP;
                    undonum=uunnum;
                    undopP=uunpP;
                    theBoard[number] = btnum;
                    theBoard[pP] = btpP;
                    makemovedone=0;
                } else {
                    moveCount();
                }
            }
        }
    }
    public void moveKnightB() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if ((number == pP + 15 || number == pP + 6 || number == pP + 17 || number == pP + 10 || number == pP - 15 || number == pP - 6 || number == pP - 17 || number == pP - 10) && (theBoard[number] == '*' || theBoard[number] < 'a')) {
            undonum=number;
            undopP=pP;
            theBoard[number] = theBoard[pP];
            theBoard[pP] = '*';
            makemovedone=1;
            if (checksafe == 0) {
                if (bkingischecked() == 1) {
                    number = tnum;
                    pP = tpP;
                    undonum=uunnum;
                    undopP=uunpP;
                    theBoard[number] = btnum;
                    theBoard[pP] = btpP;
                    makemovedone=0;
                } else {
                    moveCount();
                }
            }
        }
    }
    public int limitBishop() {
        int a = number - pP;
        if (pP / 8 == 0 && pP % 8 == 0) {
            if (a > 0 && (a % 9 == 0)) {
                for (int i = 0; pP + i * 9 <= number; i++) {
                    if ((theBoard[i * 9 + pP] != theBoard[number]) && (theBoard[i * 9 + pP] != '*') && (theBoard[i * 9 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP / 8 == 0 && pP % 8 == 7) {
            if (a > 0 && (a % 7 == 0)) {
                for (int i = 0; pP + i * 7 <= number; i++) {
                    if ((theBoard[i * 7 + pP] != theBoard[number]) && (theBoard[i * 7 + pP] != '*') && (theBoard[i * 7 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP / 8 == 7 && pP % 8 == 0) {
            if (a < 0 && (a % 7 == 0)) {
                for (int i = 0; pP - i * 7 >= number; i++) {
                    if ((theBoard[-i * 7 + pP] != theBoard[number]) && (theBoard[-i * 7 + pP] != '*') && (theBoard[-i * 7 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP / 8 == 7 && pP % 8 == 7) {
            if (a < 0 && (a % 9 == 0)) {
                for (int i = 0; pP - i * 9 >= number; i++) {
                    if ((theBoard[-i * 9 + pP] != theBoard[number]) && (theBoard[-i * 9 + pP] != '*') && (theBoard[-i * 9 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP % 8 == 0) {
            if (a > 0 && (a % 9 == 0)) {
                if (a / 9 + pP / 8 >= 8) return 1;
                for (int i = 0; pP + i * 9 <= number; i++) {
                    if ((theBoard[i * 9 + pP] != theBoard[number]) && (theBoard[i * 9 + pP] != '*') && (theBoard[i * 9 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
            if (a < 0 && (a % 7 == 0)) {
                if ((-a) / 7 > (pP / 8)) return 1;
                for (int i = 0; pP - i * 7 >= number; i++) {
                    if ((theBoard[-i * 7 + pP] != theBoard[number]) && (theBoard[-i * 7 + pP] != '*') && (theBoard[-i * 7 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP % 8 == 7) {
            if (a > 0 && (a % 7 == 0)) {
                if (a / 7 + pP / 8 >= 8) return 1;
                for (int i = 0; pP + i * 7 <= number; i++) {
                    if ((theBoard[i * 7 + pP] != theBoard[number]) && (theBoard[i * 7 + pP] != '*') && (theBoard[i * 7 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
            if (a < 0 && (a % 9 == 0)) {
                if ((-a) / 9 > pP / 8) return 1;
                for (int i = 0; pP - i * 9 >= number; i++) {
                    if ((theBoard[-i * 9 + pP] != theBoard[number]) && (theBoard[-i * 9 + pP] != '*') && (theBoard[-i * 9 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP / 8 == 0) {
            if (a > 0 && (a % 7 == 0)) {
                if (a / 7 > pP % 8) return 1;
                for (int i = 0; pP + i * 7 <= number; i++) {
                    if ((theBoard[i * 7 + pP] != theBoard[number]) && (theBoard[i * 7 + pP] != '*') && (theBoard[i * 7 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
            if (a > 0 && (a % 9 == 0)) {
                if (a / 9 + pP % 8 >= 8) return 1;
                for (int i = 0; pP + i * 9 <= number; i++) {
                    if ((theBoard[i * 9 + pP] != theBoard[number]) && (theBoard[i * 9 + pP] != '*') && (theBoard[i * 9 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (pP / 8 == 7) {
            if (a < 0 && (a % 9 == 0)) {
                if ((-a) / 9 > pP % 8) return 1;
                for (int i = 0; pP - i * 9 >= number; i++) {
                    if ((theBoard[-i * 9 + pP] != theBoard[number]) && (theBoard[-i * 9 + pP] != '*') && (theBoard[-i * 9 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
            if (a < 0 && (a % 7 == 0)) {
                if ((-a) / 7 + pP % 8 > 7) return 1;
                for (int i = 0; pP - i * 7 >= number; i++) {
                    if ((theBoard[-i * 7 + pP] != theBoard[number]) && (theBoard[-i * 7 + pP] != '*') && (theBoard[-i * 7 + pP] != theBoard[pP])) {
                        return 1;
                    }
                }
                return 0;
            }
        }
        if (a > 0 && (a % 9 == 0)) {
            if (a / 9 + pP / 8 > 7) return 1;
            for (int i = 0; pP + i * 9 <= number; i++) {
                if ((theBoard[i * 9 + pP] != theBoard[number]) && (theBoard[i * 9 + pP] != '*') && (theBoard[i * 9 + pP] != theBoard[pP])) {
                    return 1;
                }
            }
            return 0;
        }
        if (a > 0 && (a % 7 == 0)) {
            if (a / 7 > pP / 8) return 1;
            for (int i = 0; pP + i * 7 <= number; i++) {
                if ((theBoard[i * 7 + pP] != theBoard[number]) && (theBoard[i * 7 + pP] != '*') && (theBoard[i * 7 + pP] != theBoard[pP])) {
                    return 1;
                }
            }
            return 0;
        }
        if (a < 0 && (a % 9 == 0)) {
            if ((-a) / 9 > pP % 8) return 1;
            for (int i = 0; pP - i * 9 >= number; i++) {
                if ((theBoard[-i * 9 + pP] != theBoard[number]) && (theBoard[-i * 9 + pP] != '*') && (theBoard[-i * 9 + pP] != theBoard[pP])) {
                    return 1;
                }
            }
            return 0;
        }
        if (a < 0 && (a % 7 == 0)) {
            if ((-a) / 7 + pP % 8 > 7) return 1;
            for (int i = 0; pP - i * 7 >= number; i++) {
                if ((theBoard[-i * 7 + pP] != theBoard[number]) && (theBoard[-i * 7 + pP] != '*') && (theBoard[-i * 7 + pP] != theBoard[pP])) {
                    return 1;
                }
            }
            return 0;
        }
        return 1;
    }
    public void moveBishopW() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if (limitBishop() == 0) {
            undonum=number;
            undopP=pP;
            theBoard[number] = theBoard[pP];
            theBoard[pP] = '*';
            makemovedone=1;
            if (checksafe == 0) {
                if (wkingischecked() == 1) {
                    number = tnum;
                    pP = tpP;
                    undonum=uunnum;
                    undopP=uunpP;
                    theBoard[number] = btnum;
                    theBoard[pP] = btpP;
                    makemovedone=0;
                } else {
                    moveCount();
                }
            }
        }
    }
    public void moveBishopB() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if (limitBishop() == 0) {
            undonum=number;
            undopP=pP;
            theBoard[number] = theBoard[pP];
            theBoard[pP] = '*';
            makemovedone=1;
            if (checksafe == 0) {
                if (bkingischecked() == 1) {
                    number = tnum;
                    pP = tpP;
                    undonum=uunnum;
                    undopP=uunpP;
                    theBoard[number] = btnum;
                    theBoard[pP] = btpP;
                    makemovedone=0;
                } else {
                    moveCount();
                }
            }
        }
    }
    public void moveQueenW() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if ((limitBishop() == 0) || ((number / 8 == pP / 8 || number % 8 == pP % 8) && betweenRook() == 0)) {
            if (theBoard[number] == '*' || theBoard[number] > 'Z') {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (wkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        }
    }
    public void moveQueenB() {
        int tnum = number;
        int tpP = pP;
        int uunnum=undonum;
        int uunpP=undopP;
        char btnum = theBoard[number];
        char btpP = theBoard[pP];
        if ((limitBishop() == 0) || ((number / 8 == pP / 8 || number % 8 == pP % 8) && betweenRook() == 0)) {
            if (theBoard[number] == '*' || theBoard[number] < 'a') {
                undonum=number;
                undopP=pP;
                theBoard[number] = theBoard[pP];
                theBoard[pP] = '*';
                makemovedone=1;
                if (checksafe == 0) {
                    if (bkingischecked() == 1) {
                        number = tnum;
                        pP = tpP;
                        undonum=uunnum;
                        undopP=uunpP;
                        theBoard[number] = btnum;
                        theBoard[pP] = btpP;
                        makemovedone=0;
                    } else {
                        moveCount();
                    }
                }
            }
        }
    }
    public int limitKing() {
        if (pP % 8 == 0 && pP / 8 != 0 && pP / 8 != 7) {
            if ((pP - number == 8) || (pP - number == 7) || (number - pP == 1) || (number - pP == 8) || (number - pP == 9)) {
                return 0;
            }
        }
        if (pP % 8 == 0 && pP / 8 == 0) {
            if ((number - pP == 1) || (number - pP == 8) || (number - pP == 9)) {
                return 0;
            }
        }
        if (pP % 8 == 0 && pP / 8 == 7) {
            if ((pP - number == 8) || (pP - number == 7) || (number - pP == 1)) {
                return 0;
            }
        }
        if (pP % 8 == 7 && pP / 8 == 0) {
            if ((pP - number == 1) || (number - pP == 9) || (number - pP == 8)) {
                return 0;
            }
        }
        if (pP % 8 == 7 && pP / 8 == 7) {
            if ((pP - number == 1) || (pP - number == 9) || (pP - number == 8)) {
                return 0;
            }
        }
        if (pP % 8 == 0 && pP / 8 != 0 && pP / 8 != 7) {
            if ((pP - number == 1) || (pP - number == 9) || (pP - number == 8) || (number - pP == 7) || (number - pP == 8)) {
                return 0;
            }
        }
        if (pP / 8 == 0 && pP % 8 != 0 && pP % 8 != 7) {
            if ((pP - number == 1) || (number - pP == 1) || (number - pP == 8) || (number - pP == 7) || (number - pP == 9)) {
                return 0;
            }
        }
        if (pP / 8 == 7 && pP % 8 != 0 && pP % 8 != 7) {
            if ((pP - number == 1) || (number - pP == 1) || (pP - number == 8) || (pP - number == 7) || (pP - number == 9)) {
                return 0;
            }
        }
        if ((pP - number == 1) || (number - pP == 1) || (pP - number == 8) || (pP - number == 7) || (pP - number == 9) || (number - pP == 7) || (number - pP == 8) || (number - pP == 9)) {
            return 0;
        }
        return 1;
    }
    public void moveKingW() {
        if ((number == 2) && (theBoard[0] == 'R') && (theBoard[1] == '*') && (theBoard[2] == '*') && (theBoard[3] == '*') && (theBoard[4] == 'K')) {
            theBoard[0] = '*';
            theBoard[1] = '*';
            theBoard[2] = 'K';
            theBoard[3] = 'R';
            theBoard[4] = '*';
            moveCountVar += 1;
            makemovedone=1;
        } else {
            if ((number == 6) && (theBoard[7] == 'R') && (theBoard[6] == '*') && (theBoard[5] == '*') && (theBoard[4] == 'K')) {
                theBoard[7] = '*';
                theBoard[6] = 'K';
                theBoard[5] = 'R';
                theBoard[4] = '*';
                moveCountVar += 1;
                makemovedone=1;
            } else {
                int tnum = number;
                int tpP = pP;
                int uunnum=undonum;
                int uunpP=undopP;
                char btnum = theBoard[number];
                char btpP = theBoard[pP];
                if (limitKing() == 0) {
                    undonum=number;
                    undopP=pP;
                    theBoard[number] = theBoard[pP];
                    theBoard[pP] = '*';
                    makemovedone=1;
                    if (checksafe == 0) {
                        if (wkingischecked() == 1) {
                            number = tnum;
                            pP = tpP;
                            undonum=uunnum;
                            undopP=uunpP;
                            theBoard[number] = btnum;
                            theBoard[pP] = btpP;
                            makemovedone=0;
                        } else {
                            moveCount();
                        }
                    }
                }
            }
        }
    }
    public void moveKingB() {
        if ((number == 58) && (theBoard[56] == 'r') && (theBoard[57] == '*') && (theBoard[58] == '*') && (theBoard[59] == '*') && (theBoard[60] == 'k')) {
            theBoard[56] = '*';
            theBoard[57] = '*';
            theBoard[58] = 'k';
            theBoard[59] = 'r';
            theBoard[60] = '*';
            moveCountVar -= 1;
        } else {
            if ((number == 62) && (theBoard[63] == 'r') && (theBoard[62] == '*') && (theBoard[61] == '*') && (theBoard[60] == 'k')) {
                theBoard[63] = '*';
                theBoard[62] = 'k';
                theBoard[61] = 'r';
                theBoard[60] = '*';
                moveCountVar -= 1;
            } else {
                int tnum = number;
                int tpP = pP;
                int uunnum=undonum;
                int uunpP=undopP;
                char btnum = theBoard[number];
                char btpP = theBoard[pP];
                if (limitKing() == 0) {
                    undonum=number;
                    undopP=pP;
                    theBoard[number] = theBoard[pP];
                    theBoard[pP] = '*';
                    makemovedone=1;
                    if (checksafe == 0) {
                        if (bkingischecked() == 1) {
                            number = tnum;
                            pP = tpP;
                            undonum=uunnum;
                            undopP=uunpP;
                            theBoard[number] = btnum;
                            theBoard[pP] = btpP;
                            makemovedone=0;
                        } else {
                            moveCount();
                        }
                    }
                }
            }
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
    public int wkingischecked() {
        checkking = 1;
        checksafe = 1;
        int tnum = number, tnum2;
        int tpP = pP, tpP2;
        char btnum = theBoard[number], btnum2;
        char btpP = theBoard[pP], btpP2;
        for (int i = 0; i < 64; i++) {
            if (theBoard[i] == 'K') {
                number = i;
            }
        }
        for (int i = 0; i < 64; i++) {
            if (theBoard[i] >= 'a' && theBoard[i] <= 'z') {
                pP = i;
                tnum2 = number;
                tpP2 = pP;
                btnum2 = theBoard[number];
                btpP2 = theBoard[pP];
                makeMove();
                if (theBoard[number] != 'K') {
                    number = tnum2;
                    pP = tpP2;
                    theBoard[pP] = btpP2;
                    theBoard[number] = btnum2;
                    number = tnum;
                    pP = tpP;
                    theBoard[pP] = btpP;
                    theBoard[number] = btnum;
                    checkking = 0;
                    checksafe = 0;
                    return 1;
                } else {
                    theBoard[tpP] = btpP;
                    theBoard[tnum] = btnum;
                    number = tnum2;
                }
            }
        }
        number = tnum;
        pP = tpP;
        theBoard[pP] = btpP;
        theBoard[number] = btnum;
        checkking = 0;
        checksafe = 0;
        return 0;
    }
    public int bkingischecked() {
        checkking = 1;
        checksafe = 1;
        int tnum = number, tnum2;
        int tpP = pP, tpP2;
        char btnum = theBoard[number], btnum2;
        char btpP = theBoard[pP], btpP2;
        for (int i = 0; i < 64; i++) {
            if (theBoard[i] == 'k') {
                number = i;
            }
        }
        for (int i = 0; i < 64; i++) {
            if (theBoard[i] >= 'A' && theBoard[i] <= 'Z') {
                pP = i;
                tnum2 = number;
                tpP2 = pP;
                btnum2 = theBoard[number];
                btpP2 = theBoard[pP];
                makeMove();
                if (theBoard[number] != 'k') {
                    number = tnum2;
                    pP = tpP2;
                    theBoard[pP] = btpP2;
                    theBoard[number] = btnum2;
                    number = tnum;
                    pP = tpP;
                    theBoard[pP] = btpP;
                    theBoard[number] = btnum;
                    checkking = 0;
                    checksafe = 0;
                    return 1;
                } else {
                    theBoard[tpP] = btpP;
                    theBoard[tnum] = btnum;
                    number = tnum2;
                }
            }
        }
        number = tnum;
        pP = tpP;
        theBoard[pP] = btpP;
        theBoard[number] = btnum;
        checkking = 0;
        checksafe = 0;
        return 0;
    }
    public int stringCompare(String str1, String str2) {

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
