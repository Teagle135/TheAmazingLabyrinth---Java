//Name: Lauson Liu(60%), Tony Ren(40%)
//Description: Template class for player

package Model;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Player {

	// fields
	private String name;
	private Color colour;
	private Card[] cardArray;
	private Tile tile;
	private ImageIcon image;
	private int x;
	private int y;

	// constructor
	public Player(String name, Color colour, Card[] cardArray, Tile tile, ImageIcon image) {
		super();
		this.name = name;
		this.colour = colour;
		this.cardArray = cardArray;
		this.tile = tile;
		this.image = image;
	}

	// getters and setters
	public Card[] getCardArray() {
		return cardArray;
	}

	public void setCardArray(Card[] cardArray) {
		this.cardArray = cardArray;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	// toString
	@Override
	public String toString() {
		return "Player [cardArray=" + Arrays.toString(cardArray) + ", name=" + name + ", colour=" + colour + ", "
				+ image + "]";
	}

}