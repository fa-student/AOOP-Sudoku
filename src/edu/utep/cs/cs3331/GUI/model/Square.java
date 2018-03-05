package edu.utep.cs.cs3331.GUI.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Square {
    private int x;
    private int y;
    private int value = 0;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public void setVal(int val){
        this.value = val;
    }
    //
    // or a slow but more robust version:
//    public Square getSquare(int x, int y) {
//        for (Square s: squares) {
//            if (s.getX() == x && s.getY() == y) {
//                return s;
//            }
//        }
//        return null;
//    }
}
