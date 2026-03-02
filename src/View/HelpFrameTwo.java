package View;

import java.awt.event.*;
import java.util.EventObject;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class HelpFrameTwo extends JFrame {

	private ImageIcon Background = new ImageIcon("Image//HelpFrame4.png");
	private JLabel BGLabel = new JLabel(Background);
	public JButton backButton = new JButton("Back");
	public JButton nextButton = new JButton("Next");

	public HelpFrameTwo() {

		setSize(1390, 830);
		setLayout(null);

		Font ButtonFont = new Font("Helvetica", Font.PLAIN, 20);

		BGLabel.setBounds(0, 0, 1390, 810);
		getContentPane().setBackground(Color.decode("#fff2cc"));
		add(BGLabel);

		backButton.setBounds(10, 743, 175, 55);
		backButton.setFont(ButtonFont);
		backButton.setBackground(Color.decode("#ffe599"));
		backButton.setOpaque(false);
		backButton.setBorderPainted(false);
		backButton.setFocusable(false);
		add(backButton);

		nextButton.setBounds(1250, 743, 175, 55);
		nextButton.setFont(ButtonFont);
		nextButton.setBackground(Color.decode("#ffe599"));
		nextButton.setOpaque(false);
		nextButton.setBorderPainted(false);
		nextButton.setFocusable(false);
		add(nextButton);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);

	}

}