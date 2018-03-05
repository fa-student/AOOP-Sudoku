package edu.utep.cs.cs3331.sudoku;

public class hw1_sudoku {
	ConsoleUI ui = new ConsoleUI();
	Board board = new Board();

	public static void main(String[] args) {
		new hw1_sudoku().play();
	}

	public void play() {
		ui.welcome();
		do {
			try {
				int size = Integer.parseInt(ui.askSize());
				board = new Board(size);
			}
			catch (NumberFormatException e) {
				ui.showMessage("bad size");
			}
		}
		while (!board.goodBoard());

		while (!board.isSolved()) {
			ui.displayBoard(board.getBoard());

			int[] move = ui.nextMove();
			if (move[0] == -1 && move[1] == -1 && move[2] == -1) {
				break;
			}
			board.updateBoard(move[0], move[1], move[2]);
		}
		if (board.isSolved()) {
			ui.showMessage("solved");
		}
		else {
			ui.showMessage("quitter");
		}
	}

}
