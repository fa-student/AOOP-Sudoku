package edu.utep.cs.cs3331.sudoku;

import edu.utep.cs.cs3331.GUI.model.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
	public int size;
    private int[][] brd = null;
	private int[][] cln = null;
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




	public boolean updateBoard(int x, int y, int val) {
		//FIXME check for good board here
        ConsoleUI msg = new ConsoleUI();

		if (x < size && x >= 0 || y < size && y >= 0) {
            Square change = getSquare(x,y);

            if (val == 0) {
                change.setVal(val);
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
        //FIXME I have no idea how to do this
        int initX = s.getX();
        int initY = s.getY();
        if (getSquare(initX - 1, initY -1).getValue() == val
                || getSquare(initX - 1, initY + 1).getValue() == val
                || getSquare(initX + 1, initY -1).getValue() == val
                || getSquare(initX + 1, initY +1).getValue() == val) {
            return false;
        }
        return true;
    }
	public int[][] getBoard() {
	    return brd;
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
	    for (int i = 0; i < brd.length; i++) {
	        for (int j = 0; j < brd.length; j++) {
	            if (brd[i][j] == 0) {
	                return false;
                }
            }
        }
		return true;
//        for (int i = 0; i < brd.length; i += Math.sqrt(brd.length)) {
//            int subSqr = 0;
//            for (int j = 0; j < Math.sqrt(brd.length); j++) {
//                for (int k = 0; k < Math.sqrt(brd.length); k++) {
//                    subSqr += brd[j][k];
//                }
//                if ((brd.length == 4 && subSqr > 10) || (brd.length == 9 && subSqr > 45)) {
//                    return;
//                }
//            }
//        }
	}


    /** Return the square at the given, 0-based column/row indexes. */
//    public Square getSquare(int x, int y) {
//        return squares.get(x * size + y); // stored in column major order
//    }
//    //
    // or a slow but more robust version:


    // here's a benefit of using a collection, not a 2-d array; no indexes!
//    public void clear() {
//        squares.forEach(s -> s.clear()); // for (Square s: squares) { s.clear(); }
//    }

//    public static int[][] genGrid(int n) {
//        int[][] S = new int[n][n];
//        for(int row = 0; row < n; row++) {
//            int startNum = (int) (Math.sqrt(n)*((row+1) % Math.sqrt(n))) + (int) (row/Math.sqrt(n));
//            for(int column = 0; column < n; column++) {
//                S[row][column] = ((startNum + column) % n) +     1;
//            }
//        }
//        remove(S);
//        return S;
//    }
//
//    public static void remove(int S[][]) {
//        int[] nums = new int[S.length];
//        //fill array with negatives
//        //Arrays.fill(nums, -1);
//        int randomNum = 0;
//        Boolean repeat = true;
//
//        for(int row = 0; row < S.length; row ++) {
//            //generate a random number from 0 to 8
//            //used to change a value of an index
//            randomNum = ThreadLocalRandom.current().nextInt(1, S.length);
//            for (int i = 0; i < S.length; i++) {
//                if (nums[i] == randomNum) {
//                    randomNum = ThreadLocalRandom.current().nextInt(1, S.length);
//                    i=0;
//                    repeat = false;
//                }
//                else
//                    repeat = true;
//            }
//            if (repeat) {
//                nums[row] = randomNum;
//                S[row][randomNum] = 0;
//            }
//        }
//    }

//	public Board(int size) {
//		this.size = size;
//		brd = new int[size][size];//genGrid(size);
//		//cln = clnToStatic(brd);
//	}


//	public int[][] clnToStatic(int[][] brd) {
//        cln = new int[brd.length][brd.length];
//        for (int i = 0; i < brd.length; i++) {
//            for (int j = 0; j < brd.length; j++) {
//                cln[i][j] = brd[i][j];
//            }
//        }
//        return cln;
//    }
}