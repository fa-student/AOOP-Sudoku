package edu.utep.cs.cs3331.solver;

import edu.utep.cs.cs3331.sudoku.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class create {

        public void genGrid(Board g) {
            for (int i = 0; i < g.size; i++) {
                List<Integer> used = new ArrayList<>();
                for (int j = 0; j < g.size; j++) {
                    int val = ThreadLocalRandom.current().nextInt(0, g.size);
                    if (!g.updateBoard(i, j, val)) {
                        used.add(val);
                        //used.contains(shit)
                        j--;
                    }
                    used.clear();
                }
            }
        }
}
