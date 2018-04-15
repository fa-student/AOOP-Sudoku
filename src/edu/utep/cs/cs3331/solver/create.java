package edu.utep.cs.cs3331.solver;

import edu.utep.cs.cs3331.sudoku.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class create {


//        public Boolean genGrid2(Board g, int x, int y) {
//            //System.out.println(used.size());
//            if (y > 8) {
//                return true;
//            }
//            if (x > 8) {
//                x = 0;
//                y++;
//            }
//            if (used.size() >= 9) {
//                used.clear();
//                return false;
//            }
//
//            int val = ThreadLocalRandom.current().nextInt(1, g.size+1);
//
//            if ((!used.contains(val)) && g.updateBoard(x,y,val)) {
//                x++;
//
//                while (!genGrid(g,x,y)) {
//                    val = ThreadLocalRandom.current().nextInt(1, g.size+1);
//
//                }
//            }
//            else {
//                if (!used.contains(val)) {
//                    used.add(val);
//                }
//                genGrid(g, x, y);
//            }
//            return true;
//        }

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

}
