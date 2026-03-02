//Name: Tony Ren(100%)
//Description: Template class for Tile
package Model;

import java.awt.Image;
import java.util.Arrays;

//This is a template class for tiles on the board
public class Tile {

	// Fields
	String type;
	String shape;
	int direction;
	boolean movable;
	int x;
	int y;
	boolean[] openings;
	Image[] image;

	// Constructor Method
	public Tile(String type, String shape, int direction, boolean movable, int x, int y, Image[] image) {
		super();

		// Assign Values
		this.type = type;
		this.shape = shape;
		setDirection(direction); // Set direction for the -1
		this.movable = movable;
		this.x = x;
		this.y = y;
		setOpenings();
		this.image = image;

	}

	// Getter and Setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getDirection() {
		return direction;
	}

	// This method set direction for tile and random direction for empty tile
	public void setDirection(int direction) {

		// Check for empty tile
		if (direction == -1) {

			// Randomize direction for unfixed tiles
			this.direction = (int) (Math.random() * 4);
		} else
			this.direction = direction;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean[] getOpenings() {
		return openings;
	}

	// Set the Openings for different shape with direction
	public void setOpenings() {

		// Indicates the Openings for each tile
		if (shape.equalsIgnoreCase("I")) {
			if (direction == 0)
				this.openings = new boolean[] { true, false, true, false };
			if (direction == 1)
				this.openings = new boolean[] { false, true, false, true };
			if (direction == 2)
				this.openings = new boolean[] { true, false, true, false };
			if (direction == 3)
				this.openings = new boolean[] { false, true, false, true };

		} else if (shape.equalsIgnoreCase("L")) {
			if (direction == 0)
				this.openings = new boolean[] { true, true, false, false };
			if (direction == 1)
				this.openings = new boolean[] { false, true, true, false };
			if (direction == 2)
				this.openings = new boolean[] { false, false, true, true };
			if (direction == 3)
				this.openings = new boolean[] { true, false, false, true };
		} else if (shape.equalsIgnoreCase("T")) {
			if (direction == 0)
				this.openings = new boolean[] { false, true, true, true };
			if (direction == 1)
				this.openings = new boolean[] { true, false, true, true };
			if (direction == 2)
				this.openings = new boolean[] { true, true, false, true };
			if (direction == 3)
				this.openings = new boolean[] { true, true, true, false };

		}
	}

	public Image[] getImage() {
		return image;
	}

	public void setImage(Image image[]) {
		this.image = image;

	}

	@Override
	public String toString() {
		return type + "," + shape + "," + direction + "," + movable + "," + x + "," + y;
	}

}
