package jm.shape_sketch.tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import processing.app.ColorChooser;

public class OptionsToolbar extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2679779333943632914L;
	private int width;
	public static final int HEIGHT = 31;
	public static final int IMAGE_HEIGHT = 27;
	public static final int VERTICAL_BN_BORDER = (HEIGHT - IMAGE_HEIGHT) / 2;
	public static final int HORIZONTAL_BN_BORDER = 1;
	public static final int EXTRA_PANEL_WIDTH = 4;
	public static final Color BACKGROUND = new Color(240, 240, 240);

	private static final String DIVIDER_IMG_LOCN = "/data/divider.png";

	public OptionsToolbar(int length) {
		super();
		this.width = length;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMinimumSize(new Dimension(width, HEIGHT));
		setPreferredSize(new Dimension(width, HEIGHT));
		setMaximumSize(new Dimension(width, HEIGHT));
		setBackground(BACKGROUND);
		
		//(SQUARE_BUTTON_IMG_LOCN, SQUARE_BUTTON_NAME,SQUARE_BUTTON_ROLLOVER_LOCN, SQUARE_BUTTON_SELECT_LOCN, group))
//		JButton b = new JButton();
//		b.setIcon(new ImageIcon(this.getClass().getResource("/data/paintbucket.png")));
//		b.setRolloverEnabled(true);
//		b.setRolloverIcon(new ImageIcon(this.getClass().getResource("/data/paintbucket_rollover.png")));
//		b.addActionListener(this);
//		Dimension bnDimension = new Dimension(IMAGE_HEIGHT, IMAGE_HEIGHT);
//		b.setPreferredSize(bnDimension);
//		b.setMinimumSize(bnDimension);
//		b.setMaximumSize(bnDimension);
//		b.setFocusPainted(false);
//		b.setBackground(BACKGROUND);
//		b.setOpaque(false);
//		b.setContentAreaFilled(false);
//		b.setBorderPainted(false);
//		final Border emptyBorder = BorderFactory.createEmptyBorder(VERTICAL_BN_BORDER,
//				HORIZONTAL_BN_BORDER, VERTICAL_BN_BORDER, HORIZONTAL_BN_BORDER);
//		b.setBorder(emptyBorder);
//		new ColorChooser(null, true, Color.RED, "OK",this);
		
//		add(Box.createRigidArea(new Dimension(HEIGHT,HEIGHT)));
		add(new JLabel(new ImageIcon(this.getClass().getResource(
		DIVIDER_IMG_LOCN))));
		add(addButton("/data/paintbucket.png", "paintBucket", "/data/paintbucket_rollover.png", "/data/paintbucket_selected.png"));
		add(addButton("/data/background_color2.png", "background", "/data/background_color_rollover2.png", "/data/background_color_selected.png"));
		add(addButton("/data/line_color.png", "background", "/data/line_color_rollover.png", "/data/line_color_selected.png"));
		add(addButton("/data/line_thickness.png", "background", "/data/line_thickness_rollover.png", "/data/line_thickness_selected.png"));
		add(addButton("/data/size.png", "background", "/data/size_rollover.png", "/data/size_selected.png"));
		add(addButton("/data/bug.png", "background", "/data/bug_rollover.png", "/data/bug_selected.png"));
		add(addButton("/data/help.png", "background", "/data/help_rollover.png", "/data/help_selected.png"));
		add(addButton("/data/reset.png", "background", "/data/reset_rollover.png", "/data/reset_selected.png"));
		// Add fill toggle
		// Add background toggle
	}

	private JButton addButton(String imageLocation, String buttonName,
			String rolloverImageLocation, String selectedImageLocation) {
		final JButton b = new JButton();
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				imageLocation));
		ImageIcon rolloverIcon = new ImageIcon(this.getClass().getResource(
				rolloverImageLocation));
		ImageIcon selectedIcon = new ImageIcon(this.getClass().getResource(
				selectedImageLocation));

		b.setIcon(icon);
		b.setRolloverEnabled(true);
		b.setRolloverIcon(rolloverIcon);
		b.setSelectedIcon(selectedIcon);
		b.setPressedIcon(selectedIcon);
		b.setRolloverSelectedIcon(selectedIcon);

		Dimension bnDimension = new Dimension(IMAGE_HEIGHT, IMAGE_HEIGHT);
		b.setPreferredSize(bnDimension);
		b.setMinimumSize(bnDimension);
		b.setMaximumSize(bnDimension);

		b.setFocusPainted(false);
		b.setBackground(BACKGROUND);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);

		final Border emptyBorder = BorderFactory.createEmptyBorder(VERTICAL_BN_BORDER,
				HORIZONTAL_BN_BORDER, VERTICAL_BN_BORDER, HORIZONTAL_BN_BORDER);
		b.setBorder(emptyBorder);

		b.setName(buttonName);
		b.setActionCommand(buttonName);

		b.addActionListener(this);

		return b;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		Color color;
//		new ColorChooser(null, true, Color.RED, "OK", new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				color = Color.RED;
//			}
//		}).show();
		new ColorChooser(null, true, Color.RED, "OK",this).show();
	}
	
}
