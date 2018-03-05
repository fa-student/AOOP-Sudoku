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

    public Square getSquare(int x, int y) {
        for (Square s: squares) {
            if (s.getX() == x && s.getY() == y) {
                return s;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    /** Return an unmodifiable list of all the squares of this board. */
    public List<Square> squares() {
        return Collections.unmodifiableList(squares);
    }


    /**
     *
     * @param x
     * @param y
     * @param val
     * @return
     */
	public boolean updateBoard(int x, int y, int val) {
        ConsoleUI msg = new ConsoleUI();

		if (x < size && x >= 0 || y < size && y >= 0) {
            Square change = getSquare(x,y);

            if (val == 0) {
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

	public boolean checkRow(Square s, int y, int val) {
        for (int x = 0; x < size; x++) {
           if  (getSquare(x, y).getValue() == val) {

               return false;
           }
        }
        return true;
    }

    public boolean checkCol(Square s, int x, int val) {
        for (int y = 0; y < size; y++) {
            if (getSquare(x,y).getValue() == val) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSubBlock(Square s, int val) {
        int initX = s.getX();
        int initY = s.getY();

        int row= initY-initY%(int)Math.sqrt(size);
        int col = initX-initX%(int)Math.sqrt(size);

        for (int i = 0; i < (int)Math.sqrt(size); i++) {
            for (int j = 0; j < (int)Math.sqrt(size); j++) {
                if (getSquare(row+i, col+j) .getValue()== val) {
                    return false;
                }
            }
        }
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