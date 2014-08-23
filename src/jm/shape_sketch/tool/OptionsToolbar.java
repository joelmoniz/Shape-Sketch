package jm.shape_sketch.tool;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OptionsToolbar extends JPanel {

	private int width;
	public static final int HEIGHT = 25;
	public static final Color BACKGROUND = Color.BLACK;

	public OptionsToolbar(int length) {
		super();
		this.width = length;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(width, HEIGHT));
		setPreferredSize(new Dimension(width, HEIGHT));
		setMaximumSize(new Dimension(width, HEIGHT));
		setBackground(BACKGROUND);
	}
	
}
