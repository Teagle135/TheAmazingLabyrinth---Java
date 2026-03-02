package View;

import java.util.Objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class LabyrinthGameFrame extends JFrame implements KeyListener {

	private JPanel P1Label = new JPanel();
	private JPanel P2Label = new JPanel();
	private JPanel P3Label = new JPanel();
	private JPanel P4Label = new JPanel();

	public BoardPanel BoardPanel = new BoardPanel(null);

	public JButton cwButton = new JButton(
			new ImageIcon(new ImageIcon("Image//cw.png").getImage().getScaledInstance(72, 72, Image.SCALE_SMOOTH)));
	public JButton ccwButton = new JButton(
			new ImageIcon(new ImageIcon("Image//ccw.png").getImage().getScaledInstance(72, 72, Image.SCALE_SMOOTH)));
	public JLabel tileLabel = new JLabel();
	public JButton endButton = new JButton("End Turn");
	public JButton saveButton = new JButton("Save Game");
	private int BoardLabelAngle = 0;

	private boolean insert = false;

	public LabyrinthGameFrame(BoardPanel boardPanel, playerPanel[] playerPanelArray) {
		if (Objects.equals(null, boardPanel) == false) {
			BoardPanel = boardPanel;
			add(BoardPanel);
		}

		setSize(1390, 830);
		getContentPane().setBackground(Color.decode("#fff2cc"));
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);

		P1Label.setBounds(0, 0, 345, 405);
		P2Label.setBounds(0, 405, 345, 405);
		P3Label.setBounds(1045, 0, 345, 405);
		P4Label.setBounds(1045, 405, 345, 405);

		BoardPanel.setBounds(345, 0, 700, 700);
		BoardPanel.setBackground(Color.gray);
		add(BoardPanel);
		cwButton.setBounds(460, 700, 72, 72);
		add(cwButton);
		cwButton.setBackground(Color.decode("#ffe599"));
		endButton.setBounds(930, 700, 100, 72);
		endButton.setBackground(Color.decode("#ffe599"));
		add(endButton);
		saveButton.setBounds(350, 700, 100, 72);
		saveButton.setBackground(Color.decode("#ffe599"));
		add(saveButton);

		ccwButton.setBounds(850, 700, 72, 72);
		add(ccwButton);
		ccwButton.setBackground(Color.decode("#ffe599"));
		for (int count = 0; count < 4; count++)
			if (playerPanelArray[count] != null) {
				switch (count) {
				case 0:
					playerPanelArray[count].setBounds(P1Label.getBounds());
					add(playerPanelArray[count]);
					break;
				case 1:
					playerPanelArray[count].setBounds(P2Label.getBounds());
					add(playerPanelArray[count]);

					break;
				case 2:
					playerPanelArray[count].setBounds(P3Label.getBounds());
					add(playerPanelArray[count]);

					break;
				case 3:
					playerPanelArray[count].setBounds(P4Label.getBounds());
					add(playerPanelArray[count]);

					break;
				}
			}
		if (BoardPanel.getBoard() != null)
			setFreeTile();

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);

	}

	public void setFreeTile() {
		tileLabel.setBounds(660, 700, 72, 72);
		tileLabel.setIcon(new ImageIcon(
				BoardPanel.getBoard().getFreeTile().getImage()[BoardPanel.getBoard().getFreeTile().getDirection()]
						.getScaledInstance(72, 72, Image.SCALE_SMOOTH)));
		add(tileLabel);
		tileLabel.setBackground(Color.decode("#ffe599"));

	}

	public JButton getCwButton() {
		return cwButton;
	}

	public void setCwButton(JButton cwButton) {
		this.cwButton = cwButton;
	}

	public JButton getCcwButton() {
		return ccwButton;
	}

	public void setCcwButton(JButton ccwButton) {
		this.ccwButton = ccwButton;
	}

	public JLabel getTileLabel() {
		return tileLabel;
	}

	public void setTileLabel(JLabel tileLabel) {
		this.tileLabel = tileLabel;
	}

	public BoardPanel getBoardPanel() {
		return BoardPanel;
	}

	public void setBoardPanel(BoardPanel boardPanel) {
		BoardPanel = boardPanel;
	}

	// Getters and Setters
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

//	KeyEvent. VK_LEFT, KeyEvent. VK_RIGHT, KeyEvent. VK_UP	
	public void keyPressed(KeyEvent e) {
		if (insert == true) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				switch (BoardPanel.currentPlayer) {
				case 0:
					if (BoardPanel.player1y > 0) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player1x][BoardPanel.player1y].getOpenings()[3]
								&& BoardPanel.board.getTileArray()[BoardPanel.player1x][BoardPanel.player1y - 1]
										.getOpenings()[1]) {
							BoardPanel.player1y--;
							refreshBoard();
						}
					}
					break;
				case 1:
					if (BoardPanel.player2y > 0) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player2x][BoardPanel.player2y].getOpenings()[3]
								&& BoardPanel.board.getTileArray()[BoardPanel.player2x][BoardPanel.player2y - 1]
										.getOpenings()[1]) {
							BoardPanel.player2y--;
							refreshBoard();
						}

					}
					break;
				case 2:
					if (BoardPanel.player3y > 0) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player3x][BoardPanel.player3y].getOpenings()[3]
								&& BoardPanel.board.getTileArray()[BoardPanel.player3x][BoardPanel.player3y - 1]
										.getOpenings()[1]) {
							BoardPanel.player3y--;
							refreshBoard();
						}

					}
					break;
				case 3:
					if (BoardPanel.player4y > 0) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player4x][BoardPanel.player4y].getOpenings()[3]
								&& BoardPanel.board.getTileArray()[BoardPanel.player4x][BoardPanel.player4y - 1]
										.getOpenings()[1]) {
							BoardPanel.player4y--;
							refreshBoard();
						}

					}
					break;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				switch (BoardPanel.currentPlayer) {
				case 0:
					if (BoardPanel.player1y < 6) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player1x][BoardPanel.player1y].getOpenings()[1]
								&& BoardPanel.board.getTileArray()[BoardPanel.player1x][BoardPanel.player1y + 1]
										.getOpenings()[3]) {
							BoardPanel.player1y++;
							refreshBoard();
						}

					}
					break;
				case 1:
					if (BoardPanel.player2y < 6) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player2x][BoardPanel.player2y].getOpenings()[1]
								&& BoardPanel.board.getTileArray()[BoardPanel.player2x][BoardPanel.player2y + 1]
										.getOpenings()[3]) {
							BoardPanel.player2y++;
							refreshBoard();
						}

					}
					break;

				case 2:
					if (BoardPanel.player3y < 6) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player3x][BoardPanel.player3y].getOpenings()[1]
								&& BoardPanel.board.getTileArray()[BoardPanel.player3x][BoardPanel.player3y + 1]
										.getOpenings()[3]) {
							BoardPanel.player3y++;
							refreshBoard();
						}

					}
					break;

				case 3:
					if (BoardPanel.player4y < 6) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player4x][BoardPanel.player4y].getOpenings()[1]
								&& BoardPanel.board.getTileArray()[BoardPanel.player4x][BoardPanel.player4y + 1]
										.getOpenings()[3]) {
							BoardPanel.player4y++;
							refreshBoard();
						}

					}
					break;

				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				switch (BoardPanel.currentPlayer) {
				case 0:
					if (BoardPanel.player1x > 0) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player1x][BoardPanel.player1y].getOpenings()[0]
								&& BoardPanel.board.getTileArray()[BoardPanel.player1x - 1][BoardPanel.player1y]
										.getOpenings()[2]) {

							BoardPanel.player1x--;
							refreshBoard();
						}

					}
					break;
				case 1:
					if (BoardPanel.player2x > 0) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player2x][BoardPanel.player2y].getOpenings()[0]
								&& BoardPanel.board.getTileArray()[BoardPanel.player2x - 1][BoardPanel.player2y]
										.getOpenings()[2]) {

							BoardPanel.player2x--;
							refreshBoard();
						}

					}
					break;

				case 2:
					if (BoardPanel.player3x > 0) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player3x][BoardPanel.player3y].getOpenings()[0]
								&& BoardPanel.board.getTileArray()[BoardPanel.player3x - 1][BoardPanel.player3y]
										.getOpenings()[2]) {

							BoardPanel.player3x--;
							refreshBoard();
						}

					}
					break;

				case 3:
					if (BoardPanel.player4x > 0) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player4x][BoardPanel.player4y].getOpenings()[0]
								&& BoardPanel.board.getTileArray()[BoardPanel.player4x - 1][BoardPanel.player4y]
										.getOpenings()[2]) {
							BoardPanel.player4x--;
							refreshBoard();
						}

					}
					break;

				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				switch (BoardPanel.currentPlayer) {
				case 0:
					if (BoardPanel.player1x < 6) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player1x][BoardPanel.player1y].getOpenings()[2]
								&& BoardPanel.board.getTileArray()[BoardPanel.player1x + 1][BoardPanel.player1y]
										.getOpenings()[0]) {
							BoardPanel.player1x++;
							refreshBoard();
						}

					}
					break;
				case 1:
					if (BoardPanel.player2x < 6) {

						if (BoardPanel.board.getTileArray()[BoardPanel.player2x][BoardPanel.player2y].getOpenings()[2]
								&& BoardPanel.board.getTileArray()[BoardPanel.player2x + 1][BoardPanel.player2y]
										.getOpenings()[0]) {

							BoardPanel.player2x++;
							refreshBoard();
						}
					}
					break;

				case 2:
					if (BoardPanel.player3x < 6) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player3x][BoardPanel.player3y].getOpenings()[2]
								&& BoardPanel.board.getTileArray()[BoardPanel.player3x + 1][BoardPanel.player3y]
										.getOpenings()[0]) {
							BoardPanel.player3x++;
							refreshBoard();
						}
					}
					break;

				case 3:
					if (BoardPanel.player4x < 6) {
						if (BoardPanel.board.getTileArray()[BoardPanel.player4x][BoardPanel.player4y].getOpenings()[2]
								&& BoardPanel.board.getTileArray()[BoardPanel.player4x + 1][BoardPanel.player4y]
										.getOpenings()[0]) {
							BoardPanel.player4x++;
							refreshBoard();
						}
					}
					break;

				}
			}
		} else
			JOptionPane.showMessageDialog(this, "Insert the free tile before moving");

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void refreshBoard() {
		int[][] intArray = new int[7][7];
		intArray[BoardPanel.player1x][BoardPanel.player1y] = 1;
		intArray[BoardPanel.player2x][BoardPanel.player2y] = 2;
		intArray[BoardPanel.player3x][BoardPanel.player3y] = 3;
		intArray[BoardPanel.player4x][BoardPanel.player4y] = 4;
		BoardPanel.topPanel.repaint();
		BoardPanel.topPanel.revalidate();
		BoardPanel.topPanel.removeAll();
		BoardPanel.topPanel.repaint();
		BoardPanel.topPanel.revalidate();
		BoardPanel.topPanel.setLayout(new GridLayout(7, 7));
		BoardPanel.topPanel.setBounds(100, 100, 500, 500);
		BoardPanel.topPanel.setOpaque(false);
		for (int row = 0; row < 7; row++)
			for (int col = 0; col < 7; col++) {
				if (intArray[row][col] != 0)
					switch (intArray[row][col]) {
					case 1:
						BoardPanel.topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//car.png").getImage()
								.getScaledInstance(50, 40, Image.SCALE_SMOOTH))));
						break;
					case 2:
						BoardPanel.topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//shoes.png").getImage()
								.getScaledInstance(50, 40, Image.SCALE_SMOOTH))));
						break;

					case 3:
						BoardPanel.topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//ship.png").getImage()
								.getScaledInstance(100, 60, Image.SCALE_SMOOTH))));
						break;

					case 4:
						BoardPanel.topPanel.add(new JLabel(new ImageIcon(new ImageIcon("Image//hat.png").getImage()
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
						break;

					}
				else
					BoardPanel.topPanel.add(new JLabel(" "));
			}

	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int centerX = 398 + 656 / 2;
		int centerY = 30 + 656 / 2;

		g2d.rotate(Math.toRadians(BoardLabelAngle), centerX, centerY);

		g2d.rotate(-Math.toRadians(BoardLabelAngle), centerX, centerY);
	}

	/**
	 * @return the insert
	 */
	public boolean isInsert() {
		return insert;
	}

	/**
	 * @param insert the insert to set
	 */
	public void setInsert(boolean insert) {
		this.insert = insert;
	}

}
