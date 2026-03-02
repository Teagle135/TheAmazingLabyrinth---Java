package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LabyrinthTitleFrame extends JFrame {

	// label
	JLabel background = new JLabel();
	// buttons
	public JButton start = new JButton();
	public JButton load = new JButton();
	public JButton help = new JButton();

	public LabyrinthTitleFrame() {
		// labels
		// ImageIcon backgroundImage = new ImageIcon(new
		// ImageIcon("image/background.png").getImage().getScaledInstance(1280, 800,
		// Image.SCALE_SMOOTH));
		ImageIcon backgroundImage = new ImageIcon("Image//TitleFrame.png");
		background.setIcon(backgroundImage);
		background.setBounds(0, 0, 1390, 810);

		// buttons
		// start button
		start.setBounds(190, 390, 250, 100);
		start.setFocusable(false);
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);

		// load button
		load.setBounds(190, 545, 250, 100);
		load.setFocusable(false);
		load.setOpaque(false);
		load.setContentAreaFilled(false);
		load.setBorderPainted(false);

		// help button
		help.setBounds(17, 715, 60, 60);
		help.setFocusable(false);
		help.setOpaque(false);
		help.setContentAreaFilled(false);
		help.setBorderPainted(false);

		// set the basic properties of the JFrame
		this.setTitle("Labyrinth Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1390, 830);
		this.setVisible(true);
		this.setLayout(null);

		// add all the elements
		this.add(start);
		this.add(load);
		this.add(help);
		this.add(background);

	}

}
