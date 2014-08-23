package jm.shape_sketch.tool;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OptionsToolbar extends JPanel {

	private int width;
	public static final int height = 25;

	public OptionsToolbar(int length) {
		super();
		this.width = length;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setBackground(Color.BLACK);
	}
	
}
