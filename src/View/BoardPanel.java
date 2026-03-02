//Package Name
package View;
//Import

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.LabyrinthController;
import Model.Board;
import Model.Player;
import Model.Tile;

//This class implements the board on to the JPanel
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	// JLayered Pane
	private JLayeredPane boardPane = new JLayeredPane();

	// JPanel

	// The board/tile panel
	public JPanel tilePanel = new JPanel();

	// The map / game board panel
	public JPanel mapPanel = new JPanel();

	// The character movement/arrow button panel
	public JPanel topPanel = new JPanel();

	// The Arrow button array / Panel
	public JButton[][] arrowButtonArray = new JButton[4][3];
	public JPanel[] arrowPanelArray = new JPanel[4];

	// Temporary Board variable
	public Board board;

	// The map image
	private JLabel mapLabel = new JLabel(new ImageIcon("Image//GameBoard.png"));

	private int[][] intArray = new int[7][7];

	private int group;
	private int button;

	public int player1x = 0;
	public int player1y = 0;
	public int player2x = 0;
	public int player2y = 6;
	public int player3x = 6;
	public int player3y = 6;
	public int player4x = 6;
	public int player4y = 0;

	public int currentPlayer;

	// Constructor
	public BoardPanel(Board board) {
		currentPlayer = 0;
		intArray[0][0] = 1;
		intArray[0][6] = 2;
		intArray[6][6] = 3;
		intArray[6][0] = 4;
		// Assign value to the temporary board
		this.board = board;

		// Setup the Panel
		setSize(700, 700);
		setLayout(null);
		setVisible(true);

		// Set up layeredPane
		setLayeredPane();

		// Setup the tilePanel - tiles and arrows
		settilePanel();

		// Setup the mapPanel
		setMapPanel();

		setTopPanel();

		// Setup the arrows
		setArrowPanel();

	}

	public BoardPanel(Player[] playerArray, Board board, int currentPlayer) {
		this.board = board;
		this.currentPlayer = currentPlayer;

		player1x = playerArray[0].getX();
		player1y = playerArray[0].getY();
		player2x = playerArray[1].getX();
		player2y = playerArray[1].getY();
		player3x = playerArray[2].getX();
		player3y = playerArray[2].getY();
		player4x = playerArray[3].getX();
		player4y = playerArray[3].getY();

		intArray[player1x][player1y] = 1;
		intArray[player2x][player2y] = 2;
		intArray[player3x][player3y] = 3;
		intArray[player4x][player4y] = 4;

		// Setup the Panel
		setSize(700, 700);
		setLayout(null);
		setVisible(true);

		// Set up layeredPane
		setLayeredPane();

		// Setup the tilePanel - tiles and arrows
		settilePanel();

		// Setup the mapPanel
		setMapPanel();

		setTopPanel();

		// Setup the arrows
		setArrowPanel();
	}

	private void setTopPanel() {

		topPanel.setLayout(new GridLayout(7, 7));
		topPanel.setBounds(100, 100, 500, 500);
		topPanel.setOpaque(false);
		for (int row = 0; row < 7; row++)
			for (int col = 0; col < 7; col++) {
				if (intArray[row][col] != 0)
					switch (intArray[row][col]) {
					case 1:
						topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//car.png").getImage()
								.getScaledInstance(50, 40, Image.SCALE_SMOOTH))));
						break;
					case 2:
						topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//shoes.png").getImage()
								.getScaledInstance(50, 40, Image.SCALE_SMOOTH))));
						break;

					case 3:
						topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//ship.png").getImage()
								.getScaledInstance(100, 60, Image.SCALE_SMOOTH))));
						break;

					case 4:
						topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//hat.png").getImage()
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
						break;

					}
				else
					topPanel.add(new JLabel(" "));
			}
	}

	// This method setup the arrow panel
	private void setArrowPanel() {

		// Setup each panel with buttons
		for (int count = 0; count < 4; count++) {

			// Setup each panel - Setting up the fieldsF
			arrowPanelArray[count] = new JPanel();
			if (count == 0)
				arrowPanelArray[0].setBounds(100, 0, 500, 100); // Up Panel Bound
			else if (count == 1)
				arrowPanelArray[1].setBounds(600, 100, 100, 500);// Right Panel Bound
			else if (count == 2)
				arrowPanelArray[count].setBounds(100, 600, 500, 100);// Bottom Panel Bound
			else if (count == 3)
				arrowPanelArray[count].setBounds(0, 100, 100, 500);// Left Panel Bound
			arrowPanelArray[count].setOpaque(false);
			if (count % 2 == 0)
				arrowPanelArray[count].setLayout(new GridLayout(1, 7)); // Up and Bottom Layout
			else
				arrowPanelArray[count].setLayout(new GridLayout(7, 1)); // Right and Left Layout

			boardPane.add(arrowPanelArray[count], JLayeredPane.DEFAULT_LAYER);

			// Setup Buttons for each Panel
			for (int count2 = 0; count2 < 3; count2++) {

				switch (count) {
				case 0:
					arrowButtonArray[count][count2] = new JButton("↓"); // Add a empty label to fill the grid layout
					break;
				case 1:
					arrowButtonArray[count][count2] = new JButton("←"); // Add a empty label to fill the grid layout
					break;
				case 2:
					arrowButtonArray[count][count2] = new JButton("↑"); // Add a empty label to fill the grid layout
					break;
				case 3:
					arrowButtonArray[count][count2] = new JButton("→"); // Add a empty label to fill the grid layout
					break;
				}

				// Common fields for each button
				arrowButtonArray[count][count2].setBounds(0, 0, 100, 72);
				arrowButtonArray[count][count2].setFont(new Font("Comic Sans", Font.BOLD, 50));
				arrowPanelArray[count].add(new JLabel(" "));

				arrowPanelArray[count].add(arrowButtonArray[count][count2]);

			}
			arrowPanelArray[count].add(new JLabel(" ")); // Add a empty label at the end to fill the last column

		}
	}

	// This method setup the map panel
	private void setMapPanel() {

		// Setup the map panel
		mapPanel.setLayout(null);
		mapPanel.setBounds(100, 100, 500, 500);
		mapPanel.setBackground(Color.white);
		mapLabel.setBounds(0, 0, 500, 500);
		mapPanel.add(mapLabel);
	}

	// This method setup the layered pane
	private void setLayeredPane() {

		// Add panels to the layered pane
		boardPane.setSize(700, 700);
		boardPane.add(tilePanel, JLayeredPane.DEFAULT_LAYER);
		boardPane.add(topPanel, JLayeredPane.DEFAULT_LAYER);
		boardPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);
		boardPane.moveToFront(tilePanel);
		boardPane.moveToFront(boardPane);
		boardPane.moveToFront(topPanel);
		boardPane.moveToBack(mapPanel);
		add(boardPane);

	}

	// This method setup the back panel
	public void settilePanel() {

		// Setup the back Panel
		tilePanel.setLayout(new GridLayout(7, 7));
		tilePanel.setBounds(100, 100, 500, 500);
		tilePanel.setOpaque(false);

		// Check if the board is null
		if (board != null) {
			// Display every tile
			for (int row = 0; row < 7; row++)
				for (int col = 0; col < 7; col++) {
					JLabel tileImage = new JLabel(new ImageIcon(
							board.getTileArray()[row][col].getImage()[board.getTileArray()[row][col].getDirection()]));
					tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					tileImage.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					tilePanel.add(tileImage);
				}

		}
		tilePanel.revalidate();
		tilePanel.repaint();
		tilePanel.setVisible(true); // Set panel as visible

	}

	// Getters and Setters
	public JLayeredPane getBoardPane() {
		return boardPane;
	}

	public void setBoardPane(JLayeredPane boardPane) {
		this.boardPane = boardPane;
	}

	public JPanel gettilePanel() {
		return tilePanel;
	}

	public void settilePanel(JPanel tilePanel) {
		this.tilePanel = tilePanel;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public JButton[][] getArrowButtonArray() {
		return arrowButtonArray;
	}

	public void setArrowButtonArray(JButton[][] arrowButtonArray) {
		this.arrowButtonArray = arrowButtonArray;
	}

	public JPanel[] getArrowPanelArray() {
		return arrowPanelArray;
	}

	public void setArrowPanelArray(JPanel[] arrowPanelArray) {
		this.arrowPanelArray = arrowPanelArray;
	}

	// Check if insert button is clicked
	public boolean inserButtonClick(ActionEvent e) {
		if (e.getSource() == arrowButtonArray[0][0]) {
			this.button = 0;
			this.group = 0;
			return true;
		} else if (e.getSource() == arrowButtonArray[0][1]) {
			this.button = 1;
			this.group = 0;
			return true;
		} else if (e.getSource() == arrowButtonArray[0][2]) {
			this.button = 2;
			this.group = 0;
			return true;
		} else if (e.getSource() == arrowButtonArray[1][0]) {
			this.button = 0;
			this.group = 1;
			return true;
		} else if (e.getSource() == arrowButtonArray[1][1]) {
			this.button = 1;
			this.group = 1;
			return true;
		} else if (e.getSource() == arrowButtonArray[1][2]) {
			this.button = 2;
			this.group = 1;
			return true;
		} else if (e.getSource() == arrowButtonArray[2][0]) {
			this.button = 0;
			this.group = 2;
			return true;
		} else if (e.getSource() == arrowButtonArray[2][1]) {
			this.button = 1;
			this.group = 2;
			return true;
		} else if (e.getSource() == arrowButtonArray[2][2]) {
			this.button = 2;
			this.group = 2;
			return true;
		} else if (e.getSource() == arrowButtonArray[3][0]) {
			this.button = 0;
			this.group = 3;
			return true;
		} else if (e.getSource() == arrowButtonArray[3][1]) {
			this.button = 1;
			this.group = 3;
			return true;
		} else if (e.getSource() == arrowButtonArray[3][2]) {
			this.button = 2;
			this.group = 3;
			return true;
		}
		return false;
	}

	public int getInsertButtonGroup() {
		return group;

	}

	public int getInsertButtonButton() {
		return button;

	}

	/**
	 * @return the intArray
	 */
	public int[][] getIntArray() {
		return intArray;
	}

	/**
	 * @param intArray the intArray to set
	 */
	public void setIntArray(int[][] intArray) {
		this.intArray = intArray;
	}

	/**
	 * @return the currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
