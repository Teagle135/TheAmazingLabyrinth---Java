//Name: Tony Ren (100%)
//Description: Displaying the choose frame, setting up the game

//Package Name
package View;

//Import 
import java.awt.Color;

import java.awt.Font;

import javax.swing.*;

//This class creates the frame for 
@SuppressWarnings("serial")
public class LabyrinthChooseFrame extends JFrame {

	// Fields

	// JPanel - separate GUI swings and Background Image
	private JPanel backPanel = new JPanel();
	private JPanel topPanel = new JPanel();

	// JLayeredPane - contain the two JPanel
	private JLayeredPane layeredPane = new JLayeredPane();

	// JLabel(JImageIcon) - Background Image
	private JLabel backgroundImage = new JLabel(new ImageIcon("Image//ChooseFrame.png"));

	// JButtons - Buttons
	public JButton backButton = new JButton("");
	public JButton nextButton = new JButton("");

	// JTextFields - Player Name
	public JTextField player1TF = new JTextField("Player 1");
	public JTextField player2TF = new JTextField("Player 2");
	public JTextField player3TF = new JTextField("Player 3");
	public JTextField player4TF = new JTextField("Player 4");

	// JCombobox - Player AI selection
	private JComboBox<String> player1CB = new JComboBox<String>();
	private JComboBox<String> player2CB = new JComboBox<String>();
	private JComboBox<String> player3CB = new JComboBox<String>();
	private JComboBox<String> player4CB = new JComboBox<String>();

	// JSlider - Allows Card Number Selection
	public JSlider cardNumSlider = new JSlider(1, 6);

	// Font - Font for different GUI swings
	private Font fontTF = new Font("Comic Sans MS", Font.PLAIN, 28);
	private Font fontCB = new Font("Comic Sans MS", Font.PLAIN, 15);

	// Constructor Method
	public LabyrinthChooseFrame() {

		// Setup the Frame
		setSize(1390, 830);
		setTitle("Setup Your Game");
		setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Setup the buttons
		setButtons();

		// Setup the Combo Box
		setComboBox();

		// Setup the Text Fields
		setTextField();

		// Setup the Radio Buttons
		setcardNumSlider();

		// Setup background Image
		setBackgroundImage();

		// Add Panel to Frame
		setPanel();

	}

	// This method setup the panel
	private void setPanel() {

		// Back Panel
		backPanel.setBounds(0, 0, 1440, 830);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		layeredPane.add(backPanel, JLayeredPane.DEFAULT_LAYER);

		// Top Panel
		topPanel.setBounds(0, 0, 1440, 810);
		topPanel.setOpaque(false);
		topPanel.setLayout(null);
		layeredPane.add(topPanel, JLayeredPane.DEFAULT_LAYER);

		// Add both panel to the layered pane
		layeredPane.moveToFront(topPanel);
		layeredPane.moveToBack(backPanel);
		add(layeredPane);

	}

	// Set up the slider to allow selection of card number
	private void setcardNumSlider() {

		// Setup the Slider
		cardNumSlider.setBounds(400, 705, 900, 70);
		cardNumSlider.setMajorTickSpacing(1);
		cardNumSlider.setMinorTickSpacing(1);
		cardNumSlider.setSnapToTicks(true);
		cardNumSlider.setPaintLabels(true);
		cardNumSlider.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		topPanel.add(cardNumSlider);

	}

	// Set up the combo box for each player
	private void setComboBox() {

		// Player 1 Combo Box
		player1CB.setBounds(67, 200, 250, 20);
		player1CB.setFont(fontCB);
		player1CB.setPreferredSize(player1CB.getPreferredSize());
		((JLabel) player1CB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		player1CB.addItem("Player 1");
		player1CB.addItem("AI");
		topPanel.add(player1CB);

		// Player 2 Combo Box
		player2CB.setBounds(412, 200, 250, 20);
		player2CB.setFont(fontCB);
		player2CB.setPreferredSize(player2CB.getPreferredSize());
		((JLabel) player2CB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		player2CB.addItem("Player 2");
		player2CB.addItem("AI");
		topPanel.add(player2CB);

		// Player 3 Combo Box
		player3CB.setBounds(749, 200, 250, 20);
		player3CB.setFont(fontCB);
		((JLabel) player3CB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		player3CB.addItem("Player 3");
		player3CB.addItem("AI");
		topPanel.add(player3CB);

		// Player 4 Combo Box
		player4CB.setBounds(1088, 200, 230, 20);
		player4CB.setFont(fontCB);
		((JLabel) player4CB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		player4CB.addItem("Player 4");
		player4CB.addItem("AI");
		topPanel.add(player4CB);

	}

	// Set up the text field for each player
	private void setTextField() {

		// Player 1 Text Field
		player1TF.setBounds(67, 580, 250, 50);
		player1TF.setPreferredSize(player1TF.getPreferredSize());
		player1TF.setFont(fontTF);
		player1TF.setHorizontalAlignment(JTextField.CENTER);
		player1TF.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		player1TF.setForeground(Color.red);
		topPanel.add(player1TF);

		// Player 2 Text Field
		player2TF.setBounds(412, 580, 250, 50);
		player2TF.setPreferredSize(player2TF.getPreferredSize());
		player2TF.setFont(fontTF);
		player2TF.setHorizontalAlignment(JTextField.CENTER);
		player2TF.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		player2TF.setForeground(Color.blue);
		topPanel.add(player2TF);

		// Player 3 Text Field
		player3TF.setBounds(749, 580, 250, 50);
		player3TF.setFont(fontTF);
		player3TF.setPreferredSize(player3TF.getPreferredSize());
		player3TF.setHorizontalAlignment(JTextField.CENTER);
		player3TF.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		player3TF.setForeground(Color.green);
		topPanel.add(player3TF);

		// Player 4 Text Field
		player4TF.setBounds(1088, 580, 250, 50);
		player4TF.setFont(fontTF);
		player4TF.setPreferredSize(player4TF.getPreferredSize());
		player4TF.setHorizontalAlignment(JTextField.CENTER);
		player4TF.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		player4TF.setForeground(Color.yellow);
		topPanel.add(player4TF);

	}

	// Set up the next and back buttons
	private void setButtons() {

		// Setup - back button
		backButton.setBounds(6, 35, 200, 70);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setFocusable(false);
		topPanel.add(backButton);

		// Setup - next button
		nextButton.setBounds(1185, 32, 200, 70);
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setFocusable(false);
		topPanel.add(nextButton);

	}

	// This method set up the background image
	private void setBackgroundImage() {

		// Setup
		backgroundImage.setBounds(0, 0, 1390, 810);
		backPanel.add(backgroundImage);
	}

}
