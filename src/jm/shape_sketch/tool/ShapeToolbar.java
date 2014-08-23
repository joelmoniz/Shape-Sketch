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
	
	public static final int WIDTH = 25;
	public static final int IMAGE_WIDTH = 19;
	public static final int HORIZONTAL_BN_BORDER = (WIDTH-IMAGE_WIDTH)/2;
	public static final int VERTICAL_BN_BORDER = 1;
	public static final Color BACKGROUND = Color.BLACK;

	public ShapeToolbar(int length) {
		super();
		this.height = length;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(WIDTH, height));
		setPreferredSize(new Dimension(WIDTH, height));
		setMaximumSize(new Dimension(WIDTH, height));
		setBackground(BACKGROUND);

		add(addButton("data/rect.png", "rectangleButton", "data/rect.png"));
		add(addButton("data/circle.png", "circleButton", "data/circle.png"));
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
		Border emptyBorder = BorderFactory.createEmptyBorder(
				VERTICAL_BN_BORDER, HORIZONTAL_BN_BORDER, VERTICAL_BN_BORDER,
				HORIZONTAL_BN_BORDER);
		b.setBorder(emptyBorder);
		b.setName(buttonName);
		// b.addActionListener(this);

		return b;
	}

}
