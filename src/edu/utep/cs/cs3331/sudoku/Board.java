package edu.utep.cs.cs3331.sudoku;

import edu.utep.cs.cs3331.GUI.model.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
	public int size;

    public Board() {
		size = 0;
	}

    /** Squares of this board. */
    private List<Square> squares = null;

    public Board(int size) {
        this.size = size;
        squares= new ArrayList<>(size * size);
        for (int x = 0; x < size; x++) { // store in column-major
            for (int y = 0; y < size; y++) {
                squares.add(new Square(x, y));
            }
        }
    }

    /**
     * returns the square at given coordinates
     * @param x
     * @param y
     * @return
     */
    public Square getSquare(int x, int y) {
        for (Square s: squares) {
            if (s.getX() == x && s.getY() == y) {
                return s;
            }
        }
        return null;
    }

    /**
     * return size
     * @return size
     */
    public int size() {
        return size;
    }

    /** Return an unmodifiable list of all the squares of this board. */
    public List<Square> squares() {
        return Collections.unmodifiableList(squares);
    }


    /**
     * updates values in board
     * @param x x coordinate to insert
     * @param y y coordinate to insert
     * @param val   value to insert
     * @return  true if it was properly inserted
     */
	public boolean updateBoard(int x, int y, int val) {
        ConsoleUI msg = new ConsoleUI();
        if (val > size) {
            return false;
        }
		if (x < size && x >= 0 || y < size && y >= 0) {
            Square change = getSquare(x,y);

            if (val <= 0) {
                change.setVal(val);
                return true;
            }
            if (checkCol(change, x, val) && checkRow(change, y, val) && checkSubBlock(change, val)) {
                change.setVal(val);
                return   true;
            }
		}
		return false;
	}

    /**
     * checks for same value in row
     * @param s square to place new value
     * @param y row to check
     * @param val   value to check for
     * @return  false if it can't be placed
     */
	public boolean checkRow(Square s, int y, int val) {
        for (int x = 0; x < size; x++) {
           if  (getSquare(x, y).getValue() == val) {

               return false;
           }
        }
        return true;
    }

    /**
     * check column for same value to be inserted
     * @param s square to insert value
     * @param x column to check
     * @param val   value to be inserted
     * @return  false if the value can't be inserted
     */
    public boolean checkCol(Square s, int x, int val) {
        for (int y = 0; y < size; y++) {
            if (getSquare(x,y).getValue() == val) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks sub grid for same value
     * @param s square to insert value
     * @param val   value to be inserted
     * @return  false if the value can't be inserted
     */
    public boolean checkSubBlock(Square s, int val) {
        int x = s.getX();
        int y = s.getY();
//comment this in
        for (int i = 0; i < Math.sqrt(size); i++) {
            for (int j = 0; j < Math.sqrt(size); j++) {
                if ((getSquare(x - (x % (int) Math.sqrt(size)) + i, y - (y % (int) Math.sqrt(size)) +j)).getValue() == val) {
               // (x [x - (x % (int) Math.sqrt(size)) + i] [y - (y % (int) Math.sqrt(size)) +j] == val) {
                    //if this is true don't place value
                    return false;
                }
            }
        }
//        int row= initY-initY%(int)Math.sqrt(size);
//        int col = initX-initX%(int)Math.sqrt(size);
//        //FIXME this doesn't work
//        for (int i = 0; i < (int)Math.sqrt(size); i++) {
//            for (int j = 0; j < (int)Math.sqrt(size); j++) {
//                if (getSquare(row*i, col*j) .getValue()== val) {
//                    return false;
//                }
//            }
//        }

        return true;
    }

	//public static Random rand = new Random();

	//check if the board is in a good state
	public boolean goodBoard() {
        if (size == 4 || size == 9) {
            return true;
        }
        return false;
	}

    /**
     * check if the borad is solved
     * @return  true if it is solved
     */
	public boolean isSolved() {
	    for (int x = 0; x < size; x++) {
	        for (int y = 0; y < size; y++) {
	            if (getSquare(x,y).getValue() == 0) {
	                return false;
                }
            }
        }
		return true;
	}


}