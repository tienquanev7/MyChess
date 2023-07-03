package com.tienquanev7.mychess;

public class Puzzle {
    private String Boardrt;
    private String Movert;
    private int Ratingrt;
    public Puzzle(int ratingrt,String boardrt, String movert){
        Boardrt=boardrt;
        Ratingrt=ratingrt;
        Movert=movert;
    }

    public String getBoardrt() {
        return Boardrt;
    }

    public void setBoardrt(String boardrt) {
        Boardrt = boardrt;
    }

    public String getMovert() {
        return Movert;
    }

    public void setMovert(String movert) {
        Movert = movert;
    }

    public int getRatingrt() {
        return Ratingrt;
    }

    public void setRatingrt(int ratingrt) {
        Ratingrt = ratingrt;
    }
}