//Name: Lauson Liu(60%), Tony Ren(40%)
//Description: Template class for card

package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Card {

	// fields
	private String type;
	private boolean status;
	private Image image;

	// Constructor Method
	public Card(String type, boolean status, Image image) {
		super();
		this.type = type;
		this.status = status;
		this.image = image;
	}

	// Getters and Setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	// Tostring Method
	@Override
	public String toString() {
		return type + "," + status;
	}
}
