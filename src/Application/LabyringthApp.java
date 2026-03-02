/* Name: Tony Ren (80%), Lauson Liu (10%), Enzo Zhu(10%),
 * 		Tony Ren: Controller - Labyrinth Controller(100%), FileInput(100%),loadGame(100%)
 * 			 Model- Tile (100%), Board (100%), Card(40%), Player(40%), 
 * 			 View - ChooseFrame(100%), BoardPanel(100%), playerPanel(100%), GameFrame(100%)
 * 		Lauson Liu: Model - Card (60%), Player(60%), PathFinder(100%)(Unable to Work)
 * 			   View - TitleFrame(100%)
 * 		Enzo Zhu: View - HelpFrameOne(100%),HelpFrameTwo(100%),HelpFrameThree(100%),HelpFrameFour(100%)
 * 				  Controller - AIPlayer(100%) (Unable to work)
 * Date: 2024-11-24
 * Course Code: ICS4U1-02
 * Title: Amazing Labyrinth by LET (Lauson, Enzo, Tony)
 * Description: This is a java online version of the family board game labyrinth.
 * Features: User can enjoy the game Labyrinth, the game can also be saved and loaded.
 * Areas of Concerns: AI vs Player was not Finished, Potential Path is not highlighted, 
 * 					   Invisible tile might appear due to Scanner Bug, reopen game when seen.
 */

// Define the package name for the application
package Application;

// Import the LabyrinthController class from the Controller package
import Controller.LabyrinthController;

// Main class of the application
public class LabyringthApp {
	// Main method - Entry point of the application
	public static void main(String[] args) {
		// Create a new instance of LabyrinthController, initializing the application's
		// main controller
		new LabyrinthController();
	}
}
