package jm.shape_sketch.tool;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShapeToolbar extends JPanel {
	private int length;
	public static final int breadth = 25;

	public ShapeToolbar(int length) {
		super();
		this.length = length;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(breadth, length));
		setPreferredSize(new Dimension(breadth, length));
		setMaximumSize(new Dimension(breadth, length));
		setBackground(Color.BLACK);
	}

}
