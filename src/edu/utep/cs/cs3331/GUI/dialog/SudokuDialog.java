package edu.utep.cs.cs3331.GUI.dialog;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

import edu.utep.cs.cs3331.GUI.dialog.BoardPanel;
//import sudoku.model.Board;
import edu.utep.cs.cs3331.sudoku.Board;

/**
 * A dialog template for playing simple Sudoku games.
 * You need to write code for three callback methods:
 * newClicked(int), numberClicked(int) and boardClicked(int,int).
 *
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class SudokuDialog extends JFrame {
    private static int x,y;
    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(310, 430);

    private final static String IMAGE_DIR = "/image/";

    /** Sudoku board. */
    private Board board;

    /** Special panel to display a Sudoku board. */
    private BoardPanel boardPanel;

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel("");

    /** Create a new dialog. */
    public SudokuDialog() {
    	this(DEFAULT_SIZE);
    }
    
    /** Create a new dialog of the given screen dimension. */
    public SudokuDialog(Dimension dim) {
        super("Sudoku");
        setSize(dim);
        board = new Board(9);
        boardPanel = new BoardPanel(board, this::boardClicked);
        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
    }

    /**
     * Callback to be invoked when a square of the board is clicked.
     * @param x 0-based row index of the clicked square.
     * @param y 0-based column index of the clicked square.
     */
    private void boardClicked(int x, int y) {
        this.x = x;
        this.y = y;
        //TODO needs to show selection
        boardPanel.repaint();
        showMessage(String.format("Board clicked: x = %d, y = %d",  x, y));
    }
    
    /**
     * Callback to be invoked when a number button is clicked.
     * @param number Clicked number (1-9), or 0 for "X".
     */
    private void numberClicked(int number) {
        showMessage("Number clicked: " + number);
         if (!board.updateBoard(x, y, number)) {
             showMessage("bad selection");
         }
        boardPanel.repaint();
        if (board.isSolved()) {
            showMessage("you won!");
        }
    }
    
    /**
     * Callback to be invoked when a new button is clicked.
     * If the current game is over, start a new game of the given size;
     * otherwise, prompt the user for a confirmation and then proceed
     * accordingly.
     * @param size Requested puzzle size, either 4 or 9.
     */
    private void newClicked(int size) {
        JFrame confirm = new JFrame();
        int answer = JOptionPane.showConfirmDialog(confirm, "You are about to start a new game");
        if (answer == JOptionPane.YES_OPTION) {
            board = new Board(size);
            boardPanel.setBoard(board);
            boardPanel.repaint();
        }
        showMessage("New clicked: " + board.size());
    }

    /**
     * Display the given string in the message bar.
     * @param msg Message to be displayed.
     */
    private void showMessage(String msg) {
        msgBar.setText(msg);
    }

    /** Configure the UI. */
    private void configureUI() {
        setIconImage(createImageIcon("sudoku.png").getImage());
        setLayout(new BorderLayout());
        
        JPanel buttons = makeControlPanel();
        // boarder: top, left, bottom, right
        buttons.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        add(buttons, BorderLayout.NORTH);
        
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        board.setLayout(new GridLayout(1,1));
        board.add(boardPanel);
        add(board, BorderLayout.CENTER);
        
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
        showMessage("welcome");
    }
      
    /** Create a control panel consisting of new and number buttons. */
    private JPanel makeControlPanel() {
    	JPanel newButtons = new JPanel(new FlowLayout());
        JButton new4Button = new JButton("New (4x4)");
        for (JButton button: new JButton[] { new4Button, new JButton("New (9x9)") }) {
        	button.setFocusPainted(false);
            button.addActionListener(e -> {
                newClicked(e.getSource() == new4Button ? 4 : 9);
            });
            newButtons.add(button);
    	}
    	newButtons.setAlignmentX(LEFT_ALIGNMENT);
        
    	// buttons labeled 1, 2, ..., 9, and X.
        JPanel numberButtons = new JPanel(new FlowLayout());
    	int maxNumber = board.size() + 1;
    	for (int i = 1; i <= maxNumber; i++) {
            int number = i % maxNumber;
            JButton button = new JButton(number == 0 ? "X" : String.valueOf(number));
            button.setFocusPainted(false);
            button.setMargin(new Insets(0,2,0,2));
            button.addActionListener(e -> numberClicked(number));
    		numberButtons.add(button);
    	}
    	numberButtons.setAlignmentX(LEFT_ALIGNMENT);

    	JPanel content = new JPanel();
    	content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(newButtons);
        content.add(numberButtons);
        return content;
    }

    /** Create an image icon from the given image file. */
    private ImageIcon createImageIcon(String filename) {
        URL imageUrl = getClass().getResource(IMAGE_DIR + filename);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        }
        return null;
    }

    public static void main(String[] args) {
        new SudokuDialog();
    }
}
