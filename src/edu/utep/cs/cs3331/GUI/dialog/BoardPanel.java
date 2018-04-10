package edu.utep.cs.cs3331.GUI.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

//import sudoku.model.Board;
import edu.utep.cs.cs3331.sudoku.Board;

/**
 * A special panel class to display a Sudoku board modeled by the
 * {@link edu.utep.cs.cs3331.sudoku.Board} class. You need to write code for
 * the paint() method.
 *
 * @see edu.utep.cs.cs3331.sudoku.Board
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
    
	public interface ClickListener {
		
		/** Callback to notify clicking of a square. 
		 * 
		 * @param x 0-based column index of the clicked square
		 * @param y 0-based row index of the clicked square
		 */
		void clicked(int x, int y);
	}
	
    /** Background color of the board. */
	private static final Color boardColor = new Color(188,255,191);

    /** Board to be displayed. */
    private Board board;

    /** Width and height of a square in pixels. */
    private int squareSize;

    /** Create a new board panel to display the given board. */
    public BoardPanel(Board board, ClickListener listener) {
        this.board = board;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	int xy = locateSquaree(e.getX(), e.getY());
            	if (xy >= 0) {
            		listener.clicked(xy / 100, xy % 100);
            	}
            }
        });
    }

    /**
     *
     * @return
     */
    public int getSquareSize(){
        return squareSize;
    }
    /** Set the board to be displayed. */
    public void setBoard(Board board) {
    	this.board = board;
    }
    
    /**
     * Given a screen coordinate, return the indexes of the corresponding square
     * or -1 if there is no square.
     * The indexes are encoded and returned as x*100 + y, 
     * where x and y are 0-based column/row indexes.
     */
    private int locateSquaree(int x, int y) {
    	if (x < 0 || x > board.size * squareSize
    			|| y < 0 || y > board.size * squareSize) {
    		return -1;
    	}
    	int xx = x / squareSize;
    	int yy = y / squareSize;
    	return xx * 100 + yy;
    }

    /** Draw the associated board. */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 

        // determine the square size
        Dimension dim = getSize();
        squareSize = Math.min(dim.width, dim.height) / board.size;

        // draw background
        final Color oldColor = g.getColor();
        g.setColor(boardColor);
        g.fillRect(0, 0, squareSize * board.size, squareSize * board.size);

        // WRITE YOUR CODE HERE ...
        // i.e., draw grid and squares.
        //g.drawLine(0, 0, 4, 200);

        int height=dim.height-10;
        int width=dim.width-5;
        g.setColor(Color.BLACK);
        for (int i=0; i<=board.size; ++i) {
            g.drawLine(0,i*height/board.size, width, i*height/board.size);  // horizontal
            g.drawLine(i*width/board.size, 0, i*width/board.size, height);

            if (i%Math.sqrt(board.size)==0) {  // draw bold lines around 3x3 boxes
                g.drawLine(0, i*height/board.size+1, width, i*height/board.size+1);
                g.drawLine(0, i*height/board.size+2, width, i*height/board.size+2);
                g.drawLine(i*width/board.size+1, 0, i*width/board.size+1, height);
                g.drawLine(i*width/board.size+2, 0, i*width/board.size+2, height);
            }
        }
        //draw numbers
        for (int x = 0; x<board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                int toDraw = board.getSquare(x, y).getValue();
                if (toDraw != 0) {
                    g.setColor(Color.BLACK);
                    g.drawString(toDraw + "", x * height / board.size() + 10, y * height / board.size() + 20);
               }
            }
        }
    }
}
