package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class HelpFrameOne extends JFrame {

	private ImageIcon Background = new ImageIcon("Image//HelpFrame3.png");
	private JLabel BGLabel = new JLabel(Background);
	public JButton backButton = new JButton("Next");
	public JButton nextButton = new JButton("Back");

	public HelpFrameOne() {

		setSize(1390, 830);
		getContentPane().setBackground(Color.decode("#fff2cc"));
		setLayout(null);

		Font ButtonFont = new Font("Helvetica", Font.PLAIN, 20);

		BGLabel.setBounds(0, 0, 1390, 810);
		add(BGLabel);

		backButton.setBounds(59, 750, 175, 55);
		backButton.setFont(ButtonFont);
		backButton.setBackground(Color.decode("#ffe599"));
		backButton.setOpaque(false);
		backButton.setBorderPainted(false);
		backButton.setFocusable(false);
		backButton.setContentAreaFilled(false);
		add(backButton);

		nextButton.setBounds(1240, 750, 175, 55);
		nextButton.setFont(ButtonFont);
		nextButton.setBackground(Color.decode("#ffe599"));
		nextButton.setOpaque(false);
		nextButton.setBorderPainted(false);
		nextButton.setFocusable(false);
		nextButton.setContentAreaFilled(false);
		add(nextButton);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);

	}

}
