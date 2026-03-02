//Package Name
package Controller;

//Import 
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.Card;
import Model.Tile;

//This class reads in CSV File and import it in to the program
public class FileInput {

	// Constructor Method
	public FileInput(Stack<Tile> tileStack, Stack<Card> cardStack) {

		// Setup Tile Stack
		setTileStack(tileStack);

		// Setup the Card Stack
		setCardStack(cardStack);

	}

	// This method fill the card stack
	private void setCardStack(Stack<Card> cardStack) {

		// Create the Scanner
		Scanner inputFile;

		// Surround with Try - Catch in case file not found
		try {

			// Assign the file to the scanner variable
			// Insert the location of the file within the " "
			inputFile = new Scanner(new File("csv//Cards.csv"));

			// Assign the separator
			inputFile.useDelimiter(",|\r\n");// Separates the file by line and comma

			// Loop through every item and assign them to the array
			while (inputFile.hasNext()) {
				// Assign Values
				String type = inputFile.next();
				boolean status = inputFile.nextBoolean();
				try {
					cardStack.add(new Card(type.replace("\n", ""), status,
							ImageIO.read(new File("TileImage//Card" + type.replace("\n", "") + ".png"))));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Image Not Found");
				}
			}

			// Close the Scanner
			inputFile.close();

			// If file not found, display the file not found.
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");

		}
	}

	private BufferedImage loadImage(String imagePath) {
		try {
			return ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("Image not Found for " + imagePath);
			return null; // Returning null or a default image
		}
	}

	// This method fill the tile stack
	private void setTileStack(Stack<Tile> tileStack) {

		// Create the Scanner
		Scanner inputFile;

		// Surround with Try - Catch in case file not found
		try {

			// Assign the file to the scanner variable
			// Insert the location of the file within the " "
			inputFile = new Scanner(new File("csv//Treasures.csv"));

			// Assign the separator
			inputFile.useDelimiter(",|\r\n");// Separates the file by line and comma

			// Loop through every item and assign them to the array
			while (inputFile.hasNext()) {

				// Assign Values
				String type = inputFile.next();
				String shape = inputFile.next();
				int direction = inputFile.nextInt();
				boolean movable = inputFile.nextBoolean();
				int x = inputFile.nextInt();
				int y = inputFile.nextInt();

				// Add the values together and save as a tile

				// Empty Tile has different image name
				if (type.equalsIgnoreCase("EmptyTile"))

					// Only import image for the movable empty tiles
					if (movable == true)
						try {
//						System.out.println("TileImage//" + shape + "0.png");
//						System.out.println("TileImage//" + shape + "1.png");
//						System.out.println("TileImage//" + shape + "2.png");
//						System.out.println("TileImage//" + shape + "3.png");
							tileStack.add(new Tile(type, shape, direction, movable, x, y, new Image[] {
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "0.png")),
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "1.png")),
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "2.png")),
									ImageIO.read(new File("TileImage//" + shape.replace("\n", "") + "3.png")) }));

							// In case the image file is not found
						} catch (IOException e) {
							System.out.println("Image not Found");
						}
					else // Import transparent image for the fixed tiles
						try {
//							System.out.println("TileImage//" + shape + "0.png");
//							System.out.println("TileImage//" + shape + "1.png");
//							System.out.println("TileImage//" + shape + "2.png");
//							System.out.println("TileImage//" + shape + "3.png");
							tileStack.add(new Tile(type, shape, direction, movable, x, y,
									new Image[] { ImageIO.read(new File("TileImage//transparent.png")),
											ImageIO.read(new File("TileImage//transparent.png")),
											ImageIO.read(new File("TileImage//transparent.png")),
											ImageIO.read(new File("TileImage//transparent.png")) }));

							// In case the image file is not found
						} catch (IOException e) {
							System.out.println("Image not Found");
						}
				else {
					if (movable == true) { // Only import image for the movable treasures
						try {
//						System.out.println("TileImage//" + shape + "0.png");
//						System.out.println("TileImage//" + shape + "1.png");
//						System.out.println("TileImage//" + shape + "2.png");
//						System.out.println("TileImage//" + shape + "3.png");
							tileStack.add(new Tile(type, shape, direction, movable, x, y, new Image[] {
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "0.png")),
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "1.png")),
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "2.png")),
									ImageIO.read(new File("TileImage//" + type.replace("\n", "") + "3.png")) }));

							// In case the image file is not found
						} catch (IOException e) {
							System.out.println("Image not Found");
						}
					} else // Import transparent image for the fixed tiles
						try {
//							System.out.println("TileImage//" + shape + "0.png");
//							System.out.println("TileImage//" + shape + "1.png");
//							System.out.println("TileImage//" + shape + "2.png");
//							System.out.println("TileImage//" + shape + "3.png");
							tileStack.add(new Tile(type, shape, direction, movable, x, y,
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
			// Close the Scanner
			inputFile.close();
			// If file not found, display the file not found.
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");

		}
	}
}
