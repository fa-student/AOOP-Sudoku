package edu.utep.cs.cs3331.solver;

import edu.utep.cs.cs3331.sudoku.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class create {

    public boolean genGrid(Board g, int x, int y) {
        List<Integer> used = new ArrayList<>();
        int val = ThreadLocalRandom.current().nextInt(1, g.size+1);
        used.add(val);
        if (y>g.size-1) {
            return true;
        }
        if (x > g.size-1) {
            x = 0;
            y++;
        }
        while (!g.updateBoard(x, y, val)) {
            if (used.size() > g.size-1) {
                return false;
            }
            val = ThreadLocalRandom.current().nextInt(1, g.size);
            used.add(val);
        }
        if (!genGrid(g, ++x, y)) {
            val = ThreadLocalRandom.current().nextInt(1, g.size);
            used.add(val);
            g.updateBoard(x,y,val);
            genGrid(g, x, y);
        }

        return true;
    }




    public boolean BrianMeth(Board board) {
        for (int row = 0; row < board.size; row++) {
            for (int column = 0; column < board.size; column++) {
                if (board.getSquare(row,column).getValue() == 0) {
                    for (int k = 0; k <= board.size; k++) {
                        //board[row][column] = k;
                        if (board.updateBoard(row,column,k) && board.isSolved()) {
                            return true;
                        }
                        board.updateBoard(row,column,0);
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
