//Name: Tony Ren(100%)
//Description: This program loads the game file into the program

package Controller;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Model.Card;
import Model.Player;
import Model.Tile;

public class loadGame {

	Tile freeTile;
	Player[] playerArray = new Player[4];
	int currentPlayer;
	int lastButtonGroup;
	int lastButtonButton;

	public loadGame(Player[] playerArray, Tile[][] tileArray, Tile freeTile, int currentPlayer, int lastButtonGroup,
			int lastButtonButton) {

		// Create the Scanner
		Scanner inputFile;

		// Surround with Try - Catch in case file not found
		try {

			// Assign the file to the scanner variable
			// Insert the location of the file within the " "
			inputFile = new Scanner(new File("csv//save.csv"));

			// Assign the separator
			inputFile.useDelimiter(",|\r\n");// Separates the file by line and comma

			// Loop through every item and assign them to the array
			for (int pCount = 0; pCount < 4; pCount++) {
				String name = inputFile.next();
				Card[] cardArray = new Card[inputFile.nextInt()];
				for (int count = 0; count < cardArray.length; count++) {
					String type = inputFile.next();

					boolean status = inputFile.nextBoolean();

					try {
						cardArray[count] = (new Card(type.replace("\n", ""), status,
								ImageIO.read(new File("TileImage//Card" + type.replace("\n", "") + ".png"))));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Image Not Found");
					}
				}

				this.playerArray[pCount] = new Player(name, null, cardArray, null, null);
				this.playerArray[pCount].setX(inputFile.nextInt());
				this.playerArray[pCount].setY(inputFile.nextInt());
			}
			// Images
			ImageIcon car = new ImageIcon(
					new ImageIcon("Image//car.png").getImage().getScaledInstance(100, 72, Image.SCALE_SMOOTH));
			ImageIcon shoes = new ImageIcon(
					new ImageIcon("Image//shoes.png").getImage().getScaledInstance(70, 72, Image.SCALE_SMOOTH));
			ImageIcon ship = new ImageIcon(
					new ImageIcon("Image//ship.png").getImage().getScaledInstance(160, 75, Image.SCALE_SMOOTH));
			ImageIcon hat = new ImageIcon(
					new ImageIcon("Image//hat.png").getImage().getScaledInstance(82, 72, Image.SCALE_SMOOTH));
			this.playerArray[0].setColour(Color.red);
			this.playerArray[1].setColour(Color.blue);
			this.playerArray[2].setColour(Color.green);
			this.playerArray[3].setColour(Color.yellow);
			this.playerArray[0].setImage(car);
			this.playerArray[1].setImage(shoes);
			this.playerArray[2].setImage(ship);
			this.playerArray[3].setImage(hat);

			String type = inputFile.next();
			String shape = inputFile.next();

			int direction = inputFile.nextInt();

			boolean movable = inputFile.nextBoolean();

			int x = inputFile.nextInt();

			int y = inputFile.nextInt();

			// Empty Tile has different image name
			if (type.equalsIgnoreCase("EmptyTile")) {

				// Only import image for the movable empty tiles
				try {
//						System.out.println("TileImage//" + shape + "0.png");
//						System.out.println("TileImage//" + shape + "1.png");
//						System.out.println("TileImage//" + shape + "2.png");
//						System.out.println("TileImage//" + shape + "3.png");
					this.freeTile = new Tile(type, shape, direction, movable, x, y,
							new Image[] { ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "0.png")),
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "1.png")),
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "2.png")),
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "3.png")) });

					// In case the image file is not found
				} catch (IOException e) {
					System.out.println("Image not Found");
				}
			} else {
				try {
//						System.out.println("TileImage//" + shape + "0.png");
//						System.out.println("TileImage//" + shape + "1.png");
//						System.out.println("TileImage//" + shape + "2.png");
//						System.out.println("TileImage//" + shape + "3.png");
					this.freeTile = new Tile(type, shape, direction, movable, x, y,
							new Image[] { ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "0.png")),
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "1.png")),
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "2.png")),
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "3.png")) });

					// In case the image file is not found
				} catch (IOException e) {
					System.out.println("Image not Found");
				}
			}
			this.currentPlayer = inputFile.nextInt();

			this.lastButtonGroup = inputFile.nextInt();

			this.lastButtonButton = inputFile.nextInt();

			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					String type1 = inputFile.next();
					String shape1 = inputFile.next();
					int direction1 = inputFile.nextInt();
					boolean movable1 = inputFile.nextBoolean();
					int x1 = inputFile.nextInt();
					int y1 = inputFile.nextInt();
					if (type1.equalsIgnoreCase("EmptyTile")) {
						// Only import image for the movable empty tiles
						if (movable1 == true) {
							try {
//							System.out.println("TileImage//" + shape + "0.png");
//							System.out.println("TileImage//" + shape + "1.png");
//							System.out.println("TileImage//" + shape + "2.png");
//							System.out.println("TileImage//" + shape + "3.png");
								tileArray[row][col] = (new Tile(type1, shape1, direction1, movable1, x1, y1,
										new Image[] {
												ImageIO.read(
														new File("TileImage//" + shape1.replace("\n", "") + "0.png")),
												ImageIO.read(
														new File("TileImage//" + shape1.replace("\n", "") + "1.png")),
												ImageIO.read(
														new File("TileImage//" + shape1.replace("\n", "") + "2.png")),
												ImageIO.read(new File(
														"TileImage//" + shape1.replace("\n", "") + "3.png")) }));

								// In case the image file is not found
							} catch (IOException e) {
								System.out.println("Image not Found");
							}
						} else // Import transparent image for the fixed tiles
							try {
//								System.out.println("TileImage//" + shape + "0.png");
//								System.out.println("TileImage//" + shape + "1.png");
//								System.out.println("TileImage//" + shape + "2.png");
//								System.out.println("TileImage//" + shape + "3.png");
								tileArray[row][col] = (new Tile(type1, shape1, direction1, movable1, x1, y1,
										new Image[] { ImageIO.read(new File("TileImage//transparent.png")),
												ImageIO.read(new File("TileImage//transparent.png")),
												ImageIO.read(new File("TileImage//transparent.png")),
												ImageIO.read(new File("TileImage//transparent.png")) }));

								// In case the image file is not found
							} catch (IOException e) {
								System.out.println("Image not Found");
							}
					} else {
						if (movable1 == true) { // Only import image for the movable treasures
							try {

								tileArray[row][col] = (new Tile(type1, shape1, direction1, movable1, x1, y1,
										new Image[] {
												ImageIO.read(
														new File("TileImage//" + type1.replace("\n", "") + "0.png")),
												ImageIO.read(
														new File("TileImage//" + type1.replace("\n", "") + "1.png")),
												ImageIO.read(
														new File("TileImage//" + type1.replace("\n", "") + "2.png")),
												ImageIO.read(new File(
														"TileImage//" + type1.replace("\n", "") + "3.png")) }));

								// In case the image file is not found
							} catch (IOException e) {
								System.out.println("Image not Found");
							}
						} else // Import transparent image for the fixed tiles
							try {
//								System.out.println("TileImage//" + shape + "0.png");
//								System.out.println("TileImage//" + shape + "1.png");
//								System.out.println("TileImage//" + shape + "2.png");
//								System.out.println("TileImage//" + shape + "3.png");
								tileArray[row][col] = (new Tile(type1, shape1, direction1, movable1, x1, y1,
										new Image[] { ImageIO.read(new File("TileImage//transparent.png")),
												ImageIO.read(new File("TileImage//transparent.png")),
												ImageIO.read(new File("TileImage//transparent.png")),
												ImageIO.read(new File("TileImage//transparent.png")) }));

								// In case the image file is not found
							} catch (IOException e) {
								System.out.println("Image not Found");
							}
					}
				}

			}
			// Close the Scanner

			inputFile.close();

		} catch (
		// If file not found, display the file not found.

		FileNotFoundException e) {
			System.out.println("File not Found");

		}

	}

	public Tile getTile() {
		return freeTile;
	}

	/**
	 * @return the playerArray
	 */
	public Player[] getPlayerArray() {
		return playerArray;
	}

	/**
	 * @param playerArray the playerArray to set
	 */
	public void setPlayerArray(Player[] playerArray) {
		this.playerArray = playerArray;
	}

	/**
	 * @return the freeTile
	 */
	public Tile getFreeTile() {
		return freeTile;
	}

	/**
	 * @param freeTile the freeTile to set
	 */
	public void setFreeTile(Tile freeTile) {
		this.freeTile = freeTile;
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

	/**
	 * @return the lastButtonGroup
	 */
	public int getLastButtonGroup() {
		System.out.println(lastButtonGroup);
		return lastButtonGroup;
	}

	/**
	 * @param lastButtonGroup the lastButtonGroup to set
	 */
	public void setLastButtonGroup(int lastButtonGroup) {
		this.lastButtonGroup = lastButtonGroup;
	}

	/**
	 * @return the lastButtonButton
	 */
	public int getLastButtonButton() {
		return this.lastButtonButton;
	}

	/**
	 * @param lastButtonButton the lastButtonButton to set
	 */
	public void setLastButtonButton(int lastButtonButton) {
		this.lastButtonButton = lastButtonButton;
	}

}
