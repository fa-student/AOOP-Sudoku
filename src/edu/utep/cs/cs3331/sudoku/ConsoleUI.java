package edu.utep.cs.cs3331.sudoku;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
//import sudoku_demo1.hw1_sudoku;

public class ConsoleUI {
	
	private InputStream in;
	private PrintStream out;

	public ConsoleUI() {
		in = System.in;
		out = System.out;
	}

	public void consoleUI(InputStream in, PrintStream out) {
		in = System.in;
		out = System.out;
	}
	public void welcome(){
		
	}
	public String askSize() {
		showMessage("select board size (4: 4x4\t9: 9x9)\n");
		String size = usrInput();
		showMessage("enter q at any time to quit");
		return size;
	}

	public void displayBoard(int[][] brd) {
        //print
		//top +---+---+---+---+---+---+---+---+---+
        //mid +---+---+---*---+---+---*---+---+---+
        //new box +===+===+===*===+===+===*===+===+===+
		out.println("\n");
//		for (int top = 0; top <= brd.length; top++) {
//			out.print("+---");
//		}
//		out.println("+");
        for (int i = 0; i < brd.length; i++) {
			for(int j = 0; j < brd.length; j++) {
				out.print("| " + brd[i][j] + " ");

			}
			out.println("|");
		}
		out.println("\n");
	}

	public int[] nextMove() {
		showMessage("next move? (x y val)");
		int[] xyVal = new int[3];
		String ans = usrInput();
		if (ans.equals("q")) {
			xyVal[0] = -1;
			xyVal[1] = -1;
			xyVal[2] = -1;
		}
		else {
			String[] tmp = ans.split(" ");
			if (tmp.length < 3) {
				return nextMove();
			}
			try {
				//change indexes 0 and 1 java uses x for rows
				xyVal[1] = Integer.parseInt(tmp[0])-1;
				xyVal[0] = Integer.parseInt(tmp[1])-1;
				xyVal[2] = Integer.parseInt(tmp[2]);
			}
			catch (NumberFormatException e) {
				return nextMove();
			}
		}
		return xyVal;
	}

	public String usrInput() {
		String fromUsr = " ";
		Scanner input =new Scanner(in);
		fromUsr = input.nextLine();

		return fromUsr;
	}

	public void showMessage(String msg) {
		out.println(msg);
	}
}
