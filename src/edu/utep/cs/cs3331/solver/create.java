package edu.utep.cs.cs3331.solver;

import edu.utep.cs.cs3331.GUI.model.Square;
import edu.utep.cs.cs3331.sudoku.Board;
import edu.utep.cs.cs3331.sudoku.BoardInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class create implements BoardInterface {


        @Override
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
                val = ThreadLocalRandom.current().nextInt(1, g.size+1);
                used.add(val);
            }
            if (!genGrid(g, ++x, y)) {
                val = ThreadLocalRandom.current().nextInt(1, g.size+1);
                used.add(val);
                g.updateBoard(x,y,val);
                genGrid(g, x, y);
            }

            return true;
        }


        public void remove(List<Square> grid) {
            List<Integer> used = new ArrayList<>();
            for (int i = 0; i < grid.size() - 22; i++) {
                int val = ThreadLocalRandom.current().nextInt(0, grid.size());
                if (!used.contains(val)) {
                    used.add(val);
                    grid.get(val).setVal(0);
                }
                else {
                    i--;
                }
            }
        }
}
