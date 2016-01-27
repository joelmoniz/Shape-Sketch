package jm.shape_sketch.tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import processing.app.ui.ColorChooser;

public class OptionsToolbar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2679779333943632914L;
	
	private static OptionsToolbar optionsToolbar;
	private static Color fillColor, lineColor, backgroundColor;
	private static BasicStroke lineThicknessStroke;
	private static ColorChooser colorChooser;
	
	private int width;
	public static final int HEIGHT = 31;
	public static final int IMAGE_HEIGHT = 27;
	public static final int VERTICAL_BN_BORDER = (HEIGHT - IMAGE_HEIGHT) / 2;
	public static final int HORIZONTAL_BN_BORDER = 1;
	public static final int EXTRA_PANEL_WIDTH = 4;
	public static final Color BACKGROUND = new Color(240, 240, 240);

	private static final String DIVIDER_IMG_LOCN = "/data/divider.png";

	public static OptionsToolbar getOptionsToolbar(int length) {
		if (optionsToolbar == null)
			optionsToolbar = new OptionsToolbar(length);
		return optionsToolbar;
	}

	private OptionsToolbar(int length) {
		super();
		this.width = length;
		fillColor = lineColor = backgroundColor = null;
		lineThicknessStroke = new BasicStroke(1);

		colorChooser = new ColorChooser(null, true, Color.RED, "OK",this);
		
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
		add(addButton("/data/paintbucket.png", "paintbucketColor", "/data/paintbucket_rollover.png", "/data/paintbucket_selected.png"));
		add(addButton("/data/background_color2.png", "backgroundColor", "/data/background_color_rollover2.png", "/data/background_color_selected.png"));
		add(addButton("/data/line_color.png", "lineColor", "/data/line_color_rollover.png", "/data/line_color_selected.png"));
		add(addButton("/data/line_thickness.png", "lineThickness", "/data/line_thickness_rollover.png", "/data/line_thickness_selected.png"));
		add(addButton("/data/size.png", "size", "/data/size_rollover.png", "/data/size_selected.png"));
		// TODO: Commenting out undo, to be added in later
		//		add(addButton("/data/reset.png", "reset", "/data/reset_rollover.png", "/data/reset_selected.png"));
		add(addButton("/data/bug.png", "bug", "/data/bug_rollover.png", "/data/bug_selected.png"));
		add(addButton("/data/help.png", "help", "/data/help_rollover.png", "/data/help_selected.png"));
		// TODO: Add fill toggle
		// TODO: Add background toggle
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

	public static Color getFillColor() {
		return fillColor;
	}

	public static Color getLineColor() {
		return lineColor;
	}

	public static Color getBackgroundColor() {
		return backgroundColor;
	}

	public static BasicStroke getLineThickness() {
		return lineThicknessStroke;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Color color;
		// new ColorChooser(null, true, Color.RED, "OK", new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// color = Color.RED;
		// }
		// }).show();
		if (e.getActionCommand().equals("paintbucketColor")) {
			if (OptionsToolbar.fillColor != null)
				colorChooser.setColor(OptionsToolbar.fillColor);
			colorChooser.show();
			OptionsToolbar.fillColor = colorChooser.getColor();
		} else if (e.getActionCommand().equals("backgroundColor")) {
			if (OptionsToolbar.backgroundColor != null)
				colorChooser.setColor(OptionsToolbar.backgroundColor);
			colorChooser.show();
			OptionsToolbar.backgroundColor = colorChooser.getColor();
			PaintPanel p = PaintPanel.getPaintPanel();
			if (p != null)
			  p.repaint();
		} else if (e.getActionCommand().equals("lineColor")) {
			if (OptionsToolbar.lineColor != null)
				colorChooser.setColor(OptionsToolbar.lineColor);
			colorChooser.show();
			OptionsToolbar.lineColor = colorChooser.getColor();
		} else if (e.getActionCommand().equals("lineThickness")) {
			// TODO: Set line thickness
		  getLineThicknessFromUser();
		} else if (e.getActionCommand().equals("OK"))
			colorChooser.hide();
	}
	
	public void getLineThicknessFromUser() {
	  final JSpinner lineThickness = new JSpinner(new SpinnerNumberModel(lineThicknessStroke.getLineWidth(), 1.0, 100.0, 1.0));
	  final JComponent[] comps = new JComponent[] {
	    new JLabel("Thickness"),
	    lineThickness
	  };
//	  JOptionPane.showOptionDialog(null, null, "Line Thckness", JOptionPane.OK_CANCEL_OPTION, arg4, arg5, arg6, arg7)
	  int ans = JOptionPane.showConfirmDialog(PaintPanel.getPaintPanel(), comps, "Line Thckness", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	  if (ans == JOptionPane.OK_OPTION) {
	    double t = (Double)(lineThickness.getValue());
	    lineThicknessStroke = new BasicStroke((float)t);
	  }
//	    System.out.println("Cancelled");
//	  else
//	    System.out.println("OK: " + lineThickness.getText());
	}
	
}
