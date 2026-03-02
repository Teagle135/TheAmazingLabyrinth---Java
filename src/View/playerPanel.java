package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Model.Player;

@SuppressWarnings("serial")
public class playerPanel extends JPanel {

	private Player player;
	private JLabel nameLabel;
	private JLabel avatar;
	private JLayeredPane cardPane = new JLayeredPane();
	private JPanel cardPanel = new JPanel();
	private JPanel statusPanel = new JPanel();
	private JLabel[] cardArray;
	private JLabel currentTurn = new JLabel("Your Turn!");
	private JLabel[] statusArray;
	public boolean[] foundArray;

	public playerPanel(Player player) {
		// Assign value to the temporary player
		this.player = player;

		// Setup the Panel
		setSize(345, 415);
		setLayout(null);
		setVisible(true);
		setBorder(new LineBorder(player.getColour(), 10));

		nameLabel = new JLabel(this.player.getName());
		nameLabel.setBounds(60, 50, 150, 30);
		nameLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
		nameLabel.setForeground(player.getColour());
		add(nameLabel);

		avatar = new JLabel(this.player.getImage());
		avatar.setBounds(220, 30, 100, 100);
		add(avatar);

		statusPanel.setBounds(20, 150, 305, 230);
		statusPanel.setSize(305, 230);
		statusPanel.setOpaque(false);
		statusPanel.setLayout(new GridLayout(2, 3));
		statusPanel.setBorder(new LineBorder(player.getColour(), 5));
		add(statusPanel);

		cardPanel.setBounds(0, 0, 305, 230);
		cardPanel.setLayout(new GridLayout(2, 3));
		cardPanel.setBorder(new LineBorder(player.getColour(), 5));
		cardPanel.setOpaque(false);
		cardPane.add(cardPanel, JLayeredPane.DEFAULT_LAYER);

		cardPane.setLayout(null);
		cardPane.setBounds(20, 150, 305, 230);
		cardPane.moveToBack(cardPanel);
		add(cardPane);

		currentTurn.setBounds(60, 90, 200, 30);
		currentTurn.setFont((new Font("Comic Sans", Font.BOLD, 25)));
		currentTurn.setVisible(false);
		add(currentTurn);

		cardArray = new JLabel[player.getCardArray().length];
		statusArray = new JLabel[player.getCardArray().length];
		foundArray = new boolean[player.getCardArray().length];
		for (int count = 0; count < cardArray.length; count++) {
			if (player.getCardArray()[count].isStatus())
				refreshStatus(count);
		}
		for (int count = 0; count < cardArray.length; count++) {
			cardArray[count] = new JLabel(new ImageIcon(
					player.getCardArray()[count].getImage().getScaledInstance(72, 92, Image.SCALE_SMOOTH)));
			statusArray[count] = new JLabel("Found", (int) CENTER_ALIGNMENT);
			statusArray[count].setFont(new Font("Comic Sans", Font.BOLD, 20));
			statusArray[count].setVisible(false);
			statusPanel.add(statusArray[count]);
			cardPanel.add(cardArray[count]);
		}

	}

	/**
	 * @return the currentTurn
	 */
	public JLabel getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @param currentTurn the currentTurn to set
	 */
	public void setCurrentTurn(JLabel currentTurn) {
		this.currentTurn = currentTurn;
	}

	/**
	 * @return the statusPanel
	 */
	public JPanel getStatusPanel() {
		return statusPanel;
	}

	/**
	 * @param statusPanel the statusPanel to set
	 */
	public void setStatusPanel(JPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	/**
	 * @return the statusArray
	 */
	public JLabel[] getStatusArray() {
		return statusArray;
	}

	/**
	 * @param statusArray the statusArray to set
	 */

	public void refreshStatus(int count2) {
		foundArray[count2] = true;
		statusPanel.removeAll();
		statusPanel.repaint();
		statusPanel.revalidate();
		for (int count = 0; count < cardArray.length; count++) {
			statusArray[count] = new JLabel("Found", (int) CENTER_ALIGNMENT);
			statusArray[count].setFont(new Font("Comic Sans", Font.BOLD, 20));
			statusArray[count].setVisible(false);
			statusPanel.add(statusArray[count]);
		}
		for (int count3 = 0; count3 < foundArray.length; count3++)
			if (foundArray[count3])
				statusArray[count3].setVisible(true);
		statusPanel.repaint();
		statusPanel.revalidate();
	}

}
