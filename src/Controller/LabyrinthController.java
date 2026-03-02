//Name: Tony Ren (100%)
//Description: This Program controls the entire programs function

//Package Name
package Controller;

//Import
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.*;
import View.*;

//This class controls the Labyrinth game
public class LabyrinthController implements ActionListener, FocusListener {

	// Fields

	// Tile Array
	private Stack<Tile> tileStack = new Stack<Tile>();

	// Card Stack
	private Stack<Card> cardStack = new Stack<Card>();

	// Card Array - for each player
	private Card[] player1Card;
	private Card[] player2Card;
	private Card[] player3Card;
	private Card[] player4Card;

	// Board
	public Board board;

	// Player Array
	private Player[] playerArray = new Player[4];

	// Player Panel
	private playerPanel[] playerPanelArray = new playerPanel[4];

	// Board Panel
	private BoardPanel boardPanel;

	// JFrame - Labyrinth Frames
	private LabyrinthTitleFrame titleFrame = new LabyrinthTitleFrame();
	private LabyrinthChooseFrame chooseFrame = new LabyrinthChooseFrame();
	private LabyrinthGameFrame gameFrame = new LabyrinthGameFrame(boardPanel, playerPanelArray);
	private HelpFrameOne helpFrame1 = new HelpFrameOne();
	private HelpFrameTwo helpFrame2 = new HelpFrameTwo();
	private HelpFrameThree helpFrame3 = new HelpFrameThree();
	private HelpFrameFour helpFrame4 = new HelpFrameFour();

	private boolean insert = true;
	private boolean win = true;
	private int lastButtonGroup = -1;
	private int lastButtonButton = -1;
	private int currentButtonGroup = -1;
	private int currentButtonButton = -1;

	public Tile freeTile;
	public Tile[][] tileArray = new Tile[7][7];
	public int currentPlayer;

	// Constructor Method
	public LabyrinthController() {

		// Import all the csv file
		new FileInput(tileStack, cardStack);

		// Set the TitleFrame visible
		titleFrame.setVisible(true);

		// Set the other frame invisible
		chooseFrame.setVisible(false);
		helpFrame1.setVisible(false);
		helpFrame2.setVisible(false);
		helpFrame3.setVisible(false);
		helpFrame4.setVisible(false);

		// Add Action Listener to Buttons
		setActionListener();
	}

	// This method add action listener for all the buttons
	private void setActionListener() {

		// Title Frame Button
		titleFrame.start.addActionListener(this);
		titleFrame.load.addActionListener(this);
		titleFrame.help.addActionListener(this);

		// Choose Frame Button
		chooseFrame.nextButton.addActionListener(this);
		chooseFrame.backButton.addActionListener(this);

		// Choose Frame Text Field

		// Setup up Focus Listener for each Text Field

		// Player 1 Text Field
		chooseFrame.player1TF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				chooseFrame.player1TF.setText(""); // Clear text when user clicks
			}

			@Override
			public void focusLost(FocusEvent e) {
				// None
			}
		});

		// Player 2 Text Field
		chooseFrame.player2TF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				chooseFrame.player2TF.setText(""); // Clear text when user clicks
			}

			@Override
			public void focusLost(FocusEvent e) {
				// None
			}
		});

		// Player 3 Text Field
		chooseFrame.player3TF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				chooseFrame.player3TF.setText(""); // Clear text when user clicks
			}

			@Override
			public void focusLost(FocusEvent e) {
				// None
			}
		});

		// Player 4 Text Field
		chooseFrame.player4TF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				chooseFrame.player4TF.setText(""); // Clear text when user clicks
			}

			@Override
			public void focusLost(FocusEvent e) {
				// None
			}
		});

		// Help Frame One
		helpFrame1.backButton.addActionListener(this);
		helpFrame1.nextButton.addActionListener(this);

		// Help Frame Two
		helpFrame2.backButton.addActionListener(this);
		helpFrame2.nextButton.addActionListener(this);

		// Help Frame Three
		helpFrame3.backButton.addActionListener(this);
		helpFrame3.nextButton.addActionListener(this);

		// Help Frame Four
		helpFrame4.backButton.addActionListener(this);
		helpFrame4.nextButton.addActionListener(this);

		// Game Frame Rotate
		gameFrame.cwButton.addActionListener(this);
		gameFrame.ccwButton.addActionListener(this);

		// Game Frame end turn
		gameFrame.endButton.addActionListener(this);
		gameFrame.saveButton.addActionListener(this);
		// Game Frame Arrows
		if (boardPanel != null)
			for (int group = 0; group < 4; group++)
				for (int button = 0; button < 3; button++)
					if (gameFrame.BoardPanel.arrowButtonArray[group][button] != null)
						gameFrame.BoardPanel.arrowButtonArray[group][button].addActionListener(this);
	}

	// Create all the functions of buttons or arrow keys in the project
	@Override
	public void actionPerformed(ActionEvent e) {

		// Title Frame

		// Title Frame - Start Button
		if (e.getSource() == titleFrame.start) {

			// Set the Choose Frame Visible
			titleFrame.setVisible(false);
			chooseFrame.setVisible(true);
		}

		// Title Frame - Help Button
		else if (e.getSource() == titleFrame.help) {

			// Set the Help Frame Visible
			titleFrame.setVisible(false);
			helpFrame1.setVisible(true);
		}

		else if (e.getSource() == titleFrame.load) {
			// Initialize the variable to store the user's confirmation response
			int answer = 0;

			// Show confirmation dialog to the user for loading the saved game
			JOptionPane.showConfirmDialog(titleFrame, "Are you sure you want to load your previously saved game?",
					"Notice", answer);

			// If the user confirms (YES option)
			if (answer == JOptionPane.YES_OPTION) {
				// Create a new loadGame object to retrieve saved game data
				loadGame load = new loadGame(playerArray, tileArray, freeTile, gameFrame.getBoardPanel().currentPlayer,
						lastButtonGroup, lastButtonButton);

				// Update the free tile using the loaded game data
				freeTile = load.getTile();

				// Update player data with the loaded game state
				playerArray = load.getPlayerArray();

				// Set the current player index
				currentPlayer = load.getCurrentPlayer();

				// Set the last moved button's group and button indices
				lastButtonButton = load.getLastButtonButton();
				lastButtonGroup = load.getLastButtonGroup();

				// Recreate the board using loaded tile array and free tile
				board = new Board(tileArray, freeTile);

				// Recreate the board panel with the updated board and player states
				boardPanel = new BoardPanel(playerArray, board, currentPlayer);

				// Extract card arrays for each player from the loaded data
				player1Card = playerArray[0].getCardArray();
				player2Card = playerArray[1].getCardArray();
				player3Card = playerArray[2].getCardArray();
				player4Card = playerArray[3].getCardArray();

				// Create new player panels for all players based on loaded player data
				for (int count = 0; count < 4; count++) {
					playerPanelArray[count] = new playerPanel(playerArray[count]);
				}

				// Refresh the status of each player's cards in their panel
				for (int count = 0; count < player1Card.length; count++) {
					if (playerArray[0].getCardArray()[count].isStatus())
						playerPanelArray[0].refreshStatus(count);
					if (playerArray[1].getCardArray()[count].isStatus())
						playerPanelArray[1].refreshStatus(count);
					if (playerArray[2].getCardArray()[count].isStatus())
						playerPanelArray[2].refreshStatus(count);
					if (playerArray[3].getCardArray()[count].isStatus())
						playerPanelArray[3].refreshStatus(count);
				}

				// Hide the Title Frame as the game transitions to the Game Frame
				titleFrame.setVisible(false);

				// Recreate the game frame to update the value
				gameFrame = new LabyrinthGameFrame(boardPanel, playerPanelArray);

				// Re add the action listener
				setActionListener();
				gameFrame.setVisible(true);
				playerPanelArray[gameFrame.getBoardPanel().getCurrentPlayer()].getCurrentTurn().setVisible(true);
				gameFrame.repaint();
				gameFrame.revalidate();
				switch (lastButtonGroup) {
				case 0:
					gameFrame.BoardPanel.arrowButtonArray[2][lastButtonButton].setVisible(false);
					break;
				case 1:
					gameFrame.BoardPanel.arrowButtonArray[3][lastButtonButton].setVisible(false);
					break;
				case 2:
					gameFrame.BoardPanel.arrowButtonArray[0][lastButtonButton].setVisible(false);
					break;
				case 3:
					gameFrame.BoardPanel.arrowButtonArray[1][lastButtonButton].setVisible(false);
					break;
				}
			} else
				;

		} else if (e.getSource() == gameFrame.saveButton)

		{
			int answer = 0;
			JOptionPane.showConfirmDialog(gameFrame,
					"Game will be only saved from last turn! Finish your turn and then save.\n Save Game?", "Notice",
					answer);
			if (answer == JOptionPane.YES_OPTION)
				try {
					File file = new File("csv//save.csv");
					file.createNewFile();

					BufferedWriter writer = new BufferedWriter(new FileWriter(file));

					writer.write(playerArray[0].getName() + "," + playerArray[0].getCardArray().length + ",");
					for (Card card : playerArray[0].getCardArray())
						writer.write(card.toString() + ",");
					writer.write(gameFrame.getBoardPanel().player1x + "," + gameFrame.getBoardPanel().player1y);
					writer.newLine();

					writer.write(playerArray[1].getName() + "," + playerArray[1].getCardArray().length + ",");
					for (Card card : playerArray[1].getCardArray())
						writer.write(card.toString() + ",");
					writer.write(gameFrame.getBoardPanel().player2x + "," + gameFrame.getBoardPanel().player2y);
					writer.newLine();

					writer.write(playerArray[2].getName() + "," + playerArray[2].getCardArray().length + ",");
					for (Card card : playerArray[2].getCardArray())
						writer.write(card.toString() + ",");
					writer.write(gameFrame.getBoardPanel().player3x + "," + gameFrame.getBoardPanel().player3y);
					writer.newLine();

					writer.write(playerArray[3].getName() + "," + playerArray[3].getCardArray().length + ",");
					for (Card card : playerArray[3].getCardArray())
						writer.write(card.toString() + ",");
					writer.write(gameFrame.getBoardPanel().player4x + "," + gameFrame.getBoardPanel().player4y);
					writer.newLine();
					writer.write(gameFrame.getBoardPanel().getBoard().getFreeTile().toString() + ","
							+ gameFrame.getBoardPanel().currentPlayer + "," + lastButtonGroup + "," + lastButtonButton);
					writer.newLine();
					for (int row = 0; row < 7; row++)
						for (int col = 0; col < 7; col++)
							writer.write(gameFrame.getBoardPanel().getBoard().getTileArray()[row][col] + ",");
					writer.close();
				} catch (FileNotFoundException ex) {
					System.out.println("File Not Found");
					System.exit(1);
				} catch (IOException er) {
					System.out.println("something messed up");
					System.exit(1);
				}
		}

		// Choose Frame

		// Choose Frame - Back Button
		else if (e.getSource() == chooseFrame.backButton) {

			// Set the Title Frame Visible
			chooseFrame.setVisible(false);
			titleFrame.setVisible(true);
		}

		// Choose Frame - Next Button
		else if (e.getSource() == chooseFrame.nextButton) {

			// Distribute Card Evenly
			distributeCard();

			// Setup Player
			setupPlayer();

			// Create a new board
			board = new Board(tileStack);

			// Create a new board panel
			boardPanel = new BoardPanel(board);

			// Create a new player panel
			for (int count = 0; count < 4; count++) {
				playerPanelArray[count] = new playerPanel(playerArray[count]);
			}

			// Set the Title Frame Visible
			chooseFrame.setVisible(false);

			// Recreate the game frame to update the value
			gameFrame = new LabyrinthGameFrame(boardPanel, playerPanelArray);

			// Re add the action listener
			setActionListener();
			gameFrame.setVisible(true);

			gameFrame.getBoardPanel().setCurrentPlayer(0);
			playerPanelArray[gameFrame.getBoardPanel().getCurrentPlayer()].getCurrentTurn().setVisible(true);

		}

		// Help Frame1

		// Help Frame One - Back Button
		else if (e.getSource() == helpFrame1.backButton) {

			// Set the Title Frame Visible
			helpFrame1.setVisible(false);
			titleFrame.setVisible(true);
		}

		// Help Frame One - Next Button
		else if (e.getSource() == helpFrame1.nextButton) {

			// Set the Next Help Frame Visible
			helpFrame1.setVisible(false);
			helpFrame2.setVisible(true);
		}

		// Help Frame2

		// Help Frame Two - Back Button
		else if (e.getSource() == helpFrame2.backButton) {

			// Set the Last Help Frame Visible
			helpFrame2.setVisible(false);
			helpFrame1.setVisible(true);
		}

		// Help Frame Two - Next Button
		else if (e.getSource() == helpFrame2.nextButton) {

			// Set the Next Help Frame Visible
			helpFrame2.setVisible(false);
			helpFrame3.setVisible(true);
		}

		// Help Frame3

		// Help Frame Three - Back Button
		else if (e.getSource() == helpFrame3.backButton) {

			// Set the Last Help Frame Visible
			helpFrame3.setVisible(false);
			helpFrame2.setVisible(true);
		}

		// Help Frame Three - Next Button
		else if (e.getSource() == helpFrame3.nextButton) {

			// Set the Next Help Frame Visible
			helpFrame3.setVisible(false);
			helpFrame4.setVisible(true);
		}

		// Help Frame4

		// Help Frame Four - Back Button
		else if (e.getSource() == helpFrame4.backButton) {

			// Set the Last Help Frame Visible
			helpFrame4.setVisible(false);
			helpFrame3.setVisible(true);
		}

		// Help Frame Four - Next Button
		else if (e.getSource() == helpFrame4.nextButton) {

			// Set the Title Visible
			helpFrame4.setVisible(false);
			titleFrame.setVisible(true);
		}

		// Game Frame - Rotate Button

		// Only invoke when board has already updated
		if (boardPanel != null)

			// Rotate CCW
			if (e.getSource() == gameFrame.ccwButton) {
				if (gameFrame.BoardPanel.getBoard().getFreeTile().getDirection() > 0) {
					gameFrame.BoardPanel.getBoard().getFreeTile()
							.setDirection(gameFrame.BoardPanel.getBoard().getFreeTile().getDirection() - 1);// Change
					gameFrame.setFocusable(true);
					gameFrame.requestFocusInWindow();// the
				} // direction
				else
					this.gameFrame.getBoardPanel().getBoard().getFreeTile().setDirection(3);
				gameFrame.tileLabel.setIcon(new ImageIcon(
						gameFrame.BoardPanel.getBoard().getFreeTile().getImage()[gameFrame.BoardPanel.getBoard()// Update
																												// the
																												// new
																												// image
								.getFreeTile().getDirection()].getScaledInstance(72, 72, Image.SCALE_SMOOTH)));
				gameFrame.setFocusable(true);
				gameFrame.requestFocusInWindow();

				// Rotate CW
			} else if (e.getSource() == gameFrame.cwButton) {
				if (gameFrame.BoardPanel.getBoard().getFreeTile().getDirection() < 3) {
					gameFrame.BoardPanel.getBoard().getFreeTile()
							.setDirection(gameFrame.BoardPanel.getBoard().getFreeTile().getDirection() + 1); // Change
					gameFrame.setFocusable(true);
					gameFrame.requestFocusInWindow(); // the
				} // direction
				else
					gameFrame.BoardPanel.getBoard().getFreeTile().setDirection(0);
				gameFrame.tileLabel.setIcon(new ImageIcon(
						gameFrame.BoardPanel.getBoard().getFreeTile().getImage()[gameFrame.BoardPanel.getBoard() // Update
																													// the
																													// new
								// image
								.getFreeTile().getDirection()].getScaledInstance(72, 72, Image.SCALE_SMOOTH)));
				gameFrame.setFocusable(true);
				gameFrame.requestFocusInWindow();

			}

		// Game Frame Arrow
		Tile tempt = null;
		boolean outer = false;
		boolean moved = false;

		if (gameFrame.getBoardPanel().inserButtonClick(e)) {
			lastButtonGroup = currentButtonGroup;
			currentButtonGroup = gameFrame.getBoardPanel().getInsertButtonGroup();
			if (insert) {
				insert = false;
				gameFrame.setInsert(true);
				// Do different action for different button
				switch (currentButtonGroup) {
				case 0:
					lastButtonButton = currentButtonButton;
					currentButtonButton = gameFrame.getBoardPanel().getInsertButtonButton();
					switch (currentButtonButton) {
					case 0:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[6][1];
						for (int row = 5; row >= 0; row--) {
							if (1 == gameFrame.getBoardPanel().player1y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player1x
											&& 1 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1x = 0;
										gameFrame.refreshBoard();
										moved = true;
									} else {
										gameFrame.getBoardPanel().player1x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player2y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player2x
											&& 1 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player3y
									&& row + 1 == gameFrame.getBoardPanel().player3y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player3x) {
										gameFrame.getBoardPanel().player3x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player4y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player4x
											&& 1 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[row + 1][1] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[row][1];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[0][1] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 1:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[6][3];
						for (int row = 5; row >= 0; row--) {
							if (3 == gameFrame.getBoardPanel().player1y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player1x
											&& 3 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1x = 0;
										gameFrame.refreshBoard();
										moved = true;
									} else {
										gameFrame.getBoardPanel().player1x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player2y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player2x
											&& 3 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player3y
									&& row + 3 == gameFrame.getBoardPanel().player3y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player3x) {
										gameFrame.getBoardPanel().player3x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player4y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player4x
											&& 3 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[row + 1][3] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[row][3];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[0][3] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 2:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[6][5];
						for (int row = 5; row >= 0; row--) {
							if (5 == gameFrame.getBoardPanel().player1y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player1x
											&& 5 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1x = 0;
										gameFrame.refreshBoard();
										moved = true;
									} else {
										gameFrame.getBoardPanel().player1x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player2y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player2x
											&& 5 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player3y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player3x
											&& 5 == gameFrame.getBoardPanel().player3y) {
										gameFrame.getBoardPanel().player3x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player4y) {
								if (moved == false)
									if (6 == gameFrame.getBoardPanel().player4x
											&& 5 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4x = 0;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4x++;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[row + 1][5] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[row][5];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[0][5] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					}
					if (outer)
						break;
				case 1:
					lastButtonButton = currentButtonButton;
					currentButtonButton = gameFrame.getBoardPanel().getInsertButtonButton();
					switch (gameFrame.getBoardPanel().getInsertButtonButton()) {
					case 0:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[1][0];
						for (int col = 0; col <= 5; col++) {
							if (1 == gameFrame.getBoardPanel().player1x) {
								if (moved == false)
									if (1 == gameFrame.getBoardPanel().player1x
											&& 0 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1y = 6;
										gameFrame.refreshBoard();
									} else {
										gameFrame.getBoardPanel().player1y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player2x) {
								if (moved == false)
									if (1 == gameFrame.getBoardPanel().player2x
											&& 0 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player3x) {
								if (moved == false)
									if (1 == gameFrame.getBoardPanel().player3x
											&& 0 == gameFrame.getBoardPanel().player3y) {
										gameFrame.getBoardPanel().player3y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player4x) {
								if (moved == false)
									if (1 == gameFrame.getBoardPanel().player4x
											&& 0 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4y--;
										gameFrame.refreshBoard();

										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[1][col] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[1][col + 1];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[1][6] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 1:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[3][0];
						for (int col = 0; col <= 5; col++) {
							if (3 == gameFrame.getBoardPanel().player1x) {
								if (moved == false)
									if (3 == gameFrame.getBoardPanel().player1x
											&& 0 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1y = 6;
										gameFrame.refreshBoard();
									} else {
										gameFrame.getBoardPanel().player1y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player2x) {
								if (moved == false)
									if (3 == gameFrame.getBoardPanel().player2x
											&& 0 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player3x) {
								if (moved == false)
									if (3 == gameFrame.getBoardPanel().player3x
											&& 0 == gameFrame.getBoardPanel().player3y) {
										gameFrame.getBoardPanel().player3y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player4x) {
								if (moved == false)
									if (3 == gameFrame.getBoardPanel().player4x
											&& 0 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4y--;
										gameFrame.refreshBoard();

										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[3][col] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[3][col + 1];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[3][6] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 2:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[5][0];
						for (int col = 0; col <= 5; col++) {
							if (5 == gameFrame.getBoardPanel().player1x) {
								if (moved == false)
									if (5 == gameFrame.getBoardPanel().player1x
											&& 0 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1y = 6;
										gameFrame.refreshBoard();
									} else {
										gameFrame.getBoardPanel().player1y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player2x) {
								if (moved == false)
									if (5 == gameFrame.getBoardPanel().player2x
											&& 0 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player3x) {
								if (moved == false)
									if (5 == gameFrame.getBoardPanel().player3x
											&& 0 == gameFrame.getBoardPanel().player3y) {
										gameFrame.getBoardPanel().player3y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3y--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player4x) {
								if (moved == false)
									if (5 == gameFrame.getBoardPanel().player4x
											&& 0 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4y = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4y--;
										gameFrame.refreshBoard();

										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[5][col] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[5][col + 1];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[5][6] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;

					}
					if (outer)
						break;
					// Down
				case 2:
					lastButtonButton = currentButtonButton;
					currentButtonButton = gameFrame.getBoardPanel().getInsertButtonButton();
					switch (gameFrame.getBoardPanel().getInsertButtonButton()) {
					case 0:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[0][1];
						for (int row = 0; row <= 5; row++) {
							if (1 == gameFrame.getBoardPanel().player1y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player1x
											&& 1 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1x = 6;
										gameFrame.refreshBoard();
										moved = true;
									} else {
										gameFrame.getBoardPanel().player1x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player2y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player2x
											&& 1 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player3y
									&& row + 1 == gameFrame.getBoardPanel().player3y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player3x) {
										gameFrame.getBoardPanel().player3x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (1 == gameFrame.getBoardPanel().player4y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player4x
											&& 1 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[row][1] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[row + 1][1];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[6][1] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 1:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[0][3];
						for (int row = 0; row <= 5; row++) {
							if (3 == gameFrame.getBoardPanel().player1y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player1x
											&& 3 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1x = 6;
										gameFrame.refreshBoard();
									} else {
										gameFrame.getBoardPanel().player1x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player2y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player2x
											&& 3 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player3y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player3x
											&& 3 == gameFrame.getBoardPanel().player3y) {
										gameFrame.getBoardPanel().player3x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (3 == gameFrame.getBoardPanel().player4y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player4x
											&& 3 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4x--;
										gameFrame.refreshBoard();

										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[row][3] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[row + 1][3];
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[6][3] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 2:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[0][5];
						for (int row = 0; row <= 5; row++) {
							if (5 == gameFrame.getBoardPanel().player1y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player1x
											&& 5 == gameFrame.getBoardPanel().player1y) {
										gameFrame.getBoardPanel().player1x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player1x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player2y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player2x
											&& 5 == gameFrame.getBoardPanel().player2y) {
										gameFrame.getBoardPanel().player2x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player2x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player3y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player3x
											&& 5 == gameFrame.getBoardPanel().player3y) {
										gameFrame.getBoardPanel().player3x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player3x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							if (5 == gameFrame.getBoardPanel().player4y) {
								if (moved == false)
									if (0 == gameFrame.getBoardPanel().player4x
											&& 5 == gameFrame.getBoardPanel().player4y) {
										gameFrame.getBoardPanel().player4x = 6;
										gameFrame.refreshBoard();
										moved = true;

									} else {
										gameFrame.getBoardPanel().player4x--;
										gameFrame.refreshBoard();
										moved = true;
									}
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[row][5] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[row + 1][5];
						}

						gameFrame.getBoardPanel().getBoard().getTileArray()[6][5] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;

					}
					if (outer)
						break;

				case 3:
					lastButtonButton = currentButtonButton;
					currentButtonButton = gameFrame.getBoardPanel().getInsertButtonButton();
					switch (gameFrame.getBoardPanel().getInsertButtonButton()) {
					case 0:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[1][6];
						for (int col = 6; col >= 1; col--) {
							if (1 == gameFrame.getBoardPanel().player1x
									&& col - 1 == gameFrame.getBoardPanel().player1y) {
								gameFrame.getBoardPanel().player1y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (1 == gameFrame.getBoardPanel().player2x
									&& col - 1 == gameFrame.getBoardPanel().player2y) {
								gameFrame.getBoardPanel().player2y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (1 == gameFrame.getBoardPanel().player3x
									&& col - 1 == gameFrame.getBoardPanel().player3y) {
								gameFrame.getBoardPanel().player3y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (1 == gameFrame.getBoardPanel().player4x
									&& col - 1 == gameFrame.getBoardPanel().player4y) {
								gameFrame.getBoardPanel().player4y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[1][col] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[1][col - 1];
						}
						if (moved == false) {
							if (1 == gameFrame.getBoardPanel().player1x && 6 == gameFrame.getBoardPanel().player1y) {
								gameFrame.getBoardPanel().player1y = 0;
								gameFrame.refreshBoard();
							} else if (1 == gameFrame.getBoardPanel().player2x
									&& 6 == gameFrame.getBoardPanel().player2y) {
								gameFrame.getBoardPanel().player2y = 0;
								gameFrame.refreshBoard();
							} else if (1 == gameFrame.getBoardPanel().player3x
									&& 6 == gameFrame.getBoardPanel().player3y) {
								gameFrame.getBoardPanel().player3y = 0;
								gameFrame.refreshBoard();
							} else if (1 == gameFrame.getBoardPanel().player4x
									&& 6 == gameFrame.getBoardPanel().player4y) {
								gameFrame.getBoardPanel().player4y = 0;
								gameFrame.refreshBoard();
							}
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[1][0] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 1:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[3][6];
						for (int col = 6; col >= 1; col--) {
							if (3 == gameFrame.getBoardPanel().player1x
									&& col - 1 == gameFrame.getBoardPanel().player1y) {
								gameFrame.getBoardPanel().player1y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (3 == gameFrame.getBoardPanel().player2x
									&& col - 1 == gameFrame.getBoardPanel().player2y) {
								gameFrame.getBoardPanel().player2y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (3 == gameFrame.getBoardPanel().player3x
									&& col - 1 == gameFrame.getBoardPanel().player3y) {
								gameFrame.getBoardPanel().player3y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (3 == gameFrame.getBoardPanel().player4x
									&& col - 1 == gameFrame.getBoardPanel().player4y) {
								gameFrame.getBoardPanel().player4y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[3][col] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[3][col - 1];
						}
						if (moved == false) {
							if (3 == gameFrame.getBoardPanel().player1x && 6 == gameFrame.getBoardPanel().player1y) {
								gameFrame.getBoardPanel().player1y = 0;
								gameFrame.refreshBoard();
							} else if (3 == gameFrame.getBoardPanel().player2x
									&& 6 == gameFrame.getBoardPanel().player2y) {
								gameFrame.getBoardPanel().player2y = 0;
								gameFrame.refreshBoard();
							} else if (3 == gameFrame.getBoardPanel().player3x
									&& 6 == gameFrame.getBoardPanel().player3y) {
								gameFrame.getBoardPanel().player3y = 0;
								gameFrame.refreshBoard();
							} else if (3 == gameFrame.getBoardPanel().player4x
									&& 6 == gameFrame.getBoardPanel().player4y) {
								gameFrame.getBoardPanel().player4y = 0;
								gameFrame.refreshBoard();
							}
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[3][0] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;
					case 2:
						tempt = gameFrame.getBoardPanel().getBoard().getTileArray()[5][6];
						for (int col = 6; col >= 1; col--) {
							if (5 == gameFrame.getBoardPanel().player1x
									&& col - 1 == gameFrame.getBoardPanel().player1y) {
								gameFrame.getBoardPanel().player1y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (5 == gameFrame.getBoardPanel().player2x
									&& col - 1 == gameFrame.getBoardPanel().player2y) {
								gameFrame.getBoardPanel().player2y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (5 == gameFrame.getBoardPanel().player3x
									&& col - 1 == gameFrame.getBoardPanel().player3y) {
								gameFrame.getBoardPanel().player3y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							if (5 == gameFrame.getBoardPanel().player4x
									&& col - 1 == gameFrame.getBoardPanel().player4y) {
								gameFrame.getBoardPanel().player4y++;
								gameFrame.refreshBoard();
								moved = true;
							}
							gameFrame.getBoardPanel().getBoard().getTileArray()[5][col] = gameFrame.getBoardPanel()
									.getBoard().getTileArray()[5][col - 1];

						}
						if (moved == false) {
							if (5 == gameFrame.getBoardPanel().player1x && 6 == gameFrame.getBoardPanel().player1y) {
								gameFrame.getBoardPanel().player1y = 0;
								gameFrame.refreshBoard();
							} else if (5 == gameFrame.getBoardPanel().player2x
									&& 6 == gameFrame.getBoardPanel().player2y) {
								gameFrame.getBoardPanel().player2y = 0;
								gameFrame.refreshBoard();
							} else if (5 == gameFrame.getBoardPanel().player3x
									&& 6 == gameFrame.getBoardPanel().player3y) {
								gameFrame.getBoardPanel().player3y = 0;
								gameFrame.refreshBoard();
							} else if (5 == gameFrame.getBoardPanel().player4x
									&& 6 == gameFrame.getBoardPanel().player4y) {
								gameFrame.getBoardPanel().player4y = 0;
								gameFrame.refreshBoard();
							}
						}
						gameFrame.getBoardPanel().getBoard().getTileArray()[5][0] = gameFrame.getBoardPanel().getBoard()
								.getFreeTile();
						gameFrame.getBoardPanel().getBoard().setFreeTile(tempt);
						gameFrame.getBoardPanel().tilePanel.removeAll();
						gameFrame.getBoardPanel().tilePanel.revalidate();
						gameFrame.getBoardPanel().tilePanel.repaint();
						gameFrame.tileLabel.revalidate();
						gameFrame.tileLabel.removeAll();
						gameFrame.tileLabel.repaint();
						gameFrame.setFreeTile();
						gameFrame.getBoardPanel().settilePanel();
						gameFrame.setFocusable(true);
						gameFrame.requestFocusInWindow();
						outer = true;
						break;

					}
					if (outer)
						break;

				}
				switch (currentButtonGroup) {
				case 0:
					gameFrame.BoardPanel.arrowButtonArray[2][currentButtonButton].setVisible(false);
					break;
				case 1:
					gameFrame.BoardPanel.arrowButtonArray[3][currentButtonButton].setVisible(false);
					break;
				case 2:
					gameFrame.BoardPanel.arrowButtonArray[0][currentButtonButton].setVisible(false);
					break;
				case 3:
					gameFrame.BoardPanel.arrowButtonArray[1][currentButtonButton].setVisible(false);
					break;
				}
				if (lastButtonButton != -1 & lastButtonGroup != -1) {
					switch (lastButtonGroup) {
					case 0:
						gameFrame.BoardPanel.arrowButtonArray[2][lastButtonButton].setVisible(true);
						break;
					case 1:
						gameFrame.BoardPanel.arrowButtonArray[3][lastButtonButton].setVisible(true);
						break;
					case 2:
						gameFrame.BoardPanel.arrowButtonArray[0][lastButtonButton].setVisible(true);
						break;
					case 3:
						gameFrame.BoardPanel.arrowButtonArray[1][lastButtonButton].setVisible(true);
						break;
					}
				}
			} else
				JOptionPane.showMessageDialog(gameFrame, "You Have Already Inserted A Tile.");

		} else if (e.getSource() == gameFrame.endButton)

		{
			gameFrame.setInsert(false);
			insert = true;
			switch (gameFrame.getBoardPanel().getCurrentPlayer()) {
			case 0:
				for (int count = 0; count < player1Card.length; count++) {
					if ((player1Card[count].getType()
							.equalsIgnoreCase(gameFrame.getBoardPanel().getBoard()
									.getTileArray()[gameFrame.BoardPanel.player1x][gameFrame.BoardPanel.player1y]
									.getType()))) {

						playerPanelArray[0].refreshStatus(count);
						break;
					}
				}
				break;
			case 1:
				for (int count = 0; count < player2Card.length; count++) {
					if ((player2Card[count].getType()
							.equalsIgnoreCase(gameFrame.getBoardPanel().getBoard()
									.getTileArray()[gameFrame.BoardPanel.player2x][gameFrame.BoardPanel.player2y]
									.getType()))) {

						playerPanelArray[1].refreshStatus(count);
						break;
					}
				}
				break;
			case 2:
				for (int count = 0; count < player3Card.length; count++) {
					if ((player3Card[count].getType()
							.equalsIgnoreCase(gameFrame.getBoardPanel().getBoard()
									.getTileArray()[gameFrame.BoardPanel.player3x][gameFrame.BoardPanel.player3y]
									.getType()))) {

						playerPanelArray[2].refreshStatus(count);
						break;
					}
				}
				break;
			case 3:
				for (int count = 0; count < player4Card.length; count++) {
					if ((player4Card[count].getType()
							.equalsIgnoreCase(gameFrame.getBoardPanel().getBoard()
									.getTileArray()[gameFrame.BoardPanel.player4x][gameFrame.BoardPanel.player4y]
									.getType()))) {

						playerPanelArray[3].refreshStatus(count);
						break;
					}
				}
				break;

			}
			win = true;
			for (boolean check : playerPanelArray[gameFrame.getBoardPanel().currentPlayer].foundArray) {
				if (check == false)
					win = false;
			}
			if (win) {
				JOptionPane.showMessageDialog(gameFrame, "Player " + gameFrame.getBoardPanel().currentPlayer + 1 + ": "
						+ playerArray[gameFrame.getBoardPanel().currentPlayer].getName() + " won the game.");
				System.exit(0);
			}
			playerPanelArray[gameFrame.getBoardPanel().getCurrentPlayer()].getCurrentTurn().setVisible(false);
			if (gameFrame.getBoardPanel().getCurrentPlayer() < 3) {
				gameFrame.getBoardPanel().setCurrentPlayer(gameFrame.getBoardPanel().getCurrentPlayer() + 1);
				playerPanelArray[gameFrame.getBoardPanel().getCurrentPlayer()].getCurrentTurn().setVisible(true);
				gameFrame.setFocusable(true);
				gameFrame.requestFocusInWindow();
			} else {
				gameFrame.getBoardPanel().setCurrentPlayer(0);
				playerPanelArray[gameFrame.getBoardPanel().getCurrentPlayer()].getCurrentTurn().setVisible(true);
				gameFrame.setFocusable(true);
				gameFrame.requestFocusInWindow();
			}

		}

	}

	// This method distribute the cards evenly
	private void distributeCard() {

		// Shuffle the card stack
		Collections.shuffle(cardStack);

		// Create the card array for each player
		player1Card = new Card[chooseFrame.cardNumSlider.getValue()];
		player2Card = new Card[chooseFrame.cardNumSlider.getValue()];
		player3Card = new Card[chooseFrame.cardNumSlider.getValue()];
		player4Card = new Card[chooseFrame.cardNumSlider.getValue()];

		// Distribute card to each player
		for (int count = 0; count < chooseFrame.cardNumSlider.getValue(); count++) {
			player1Card[count] = cardStack.pop();
			player2Card[count] = cardStack.pop();
			player3Card[count] = cardStack.pop();
			player4Card[count] = cardStack.pop();
		}

	}

	// This method setup the player
	private void setupPlayer() {

		// Images
		ImageIcon car = new ImageIcon(
				new ImageIcon("Image//car.png").getImage().getScaledInstance(100, 72, Image.SCALE_SMOOTH));
		ImageIcon shoes = new ImageIcon(
				new ImageIcon("Image//shoes.png").getImage().getScaledInstance(70, 72, Image.SCALE_SMOOTH));
		ImageIcon ship = new ImageIcon(
				new ImageIcon("Image//ship.png").getImage().getScaledInstance(160, 75, Image.SCALE_SMOOTH));
		ImageIcon hat = new ImageIcon(
				new ImageIcon("Image//hat.png").getImage().getScaledInstance(82, 72, Image.SCALE_SMOOTH));

		// Create the Player
		playerArray[0] = new Player(chooseFrame.player1TF.getText(), Color.red, player1Card, null, car);
		playerArray[1] = new Player(chooseFrame.player2TF.getText(), Color.blue, player2Card, null, shoes);
		playerArray[2] = new Player(chooseFrame.player3TF.getText(), Color.green, player3Card, null, ship);
		playerArray[3] = new Player(chooseFrame.player4TF.getText(), Color.yellow, player4Card, null, hat);
	}

	// Abstract Method for Focus Listener
	@Override
	public void focusGained(FocusEvent e) {
		// None
	}

	@Override
	public void focusLost(FocusEvent e) {
		// None
	}

}