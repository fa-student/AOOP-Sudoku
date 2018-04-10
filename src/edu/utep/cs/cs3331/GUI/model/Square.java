package edu.utep.cs.cs3331.GUI.model;


public class Square {
    private int x;
    private int y;
    private int value = 0;

    /**
     *
     * @param x
     * @param y
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param val
     */
    public void setVal(int val) {
        this.value = val;
    }
}
