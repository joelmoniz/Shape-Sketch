package jm.shape_sketch.tool;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ShapeToolbar extends JPanel {
	private int height;
	public static final int width = 25;

	public ShapeToolbar(int length) {
		super();
		this.height = length;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setBackground(Color.BLACK);

		add(addButton("data/rect.png", "rectangleButton", "data/rect.png"));
		add(addButton("data/circle.png", "rectangleButton", "data/circle.png"));
	}

	private JButton addButton(String imageLocation, String buttonName,
			String rolloverImageLocation) {
		JButton b = new JButton();
		ImageIcon icon = new ImageIcon(//this.getClass().getResource(
				imageLocation);
		ImageIcon rolloverIcon = new ImageIcon(//this.getClass().getResource(
				rolloverImageLocation);
		// b.setLayout(new BorderLayout());
		// b.setIcon(icon);
		// JLabel label = new JLabel(icon);
		// b.add(label);
		b.setIcon(icon);
		b.setRolloverEnabled(true);
		b.setRolloverIcon(rolloverIcon);
		// b.setIconTextGap(0);
		// b.setMargin(new Insets(0, 0, 0, 0));
		// b.setSize(d);
		b.setFocusPainted(false);
		b.setBackground(Color.black);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		b.setBorder(emptyBorder);
		b.setName(buttonName);
		// b.addActionListener(this);

		return b;
	}

}
