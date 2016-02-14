package jm.shape_sketch.tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import processing.app.Platform;
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

  private static final String URL_BUG = "https://github.com/joelmoniz/Shape-Sketch/issues/new";

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
	
  add(Box.createHorizontalStrut(10));
  JSeparator vertSeperator = new JSeparator(SwingConstants.VERTICAL);
  vertSeperator.setMaximumSize( new Dimension(1, Integer.MAX_VALUE) );
  add(vertSeperator);
  add(Box.createHorizontalStrut(10));
  
    add(addButton("/data/bug.png", "bug", "/data/bug_rollover.png", "/data/bug_selected.png"));
//		add(addButton("/data/help.png", "help", "/data/help_rollover.png", "/data/help_selected.png"));
		// TODO: Add fill toggle
		// TODO: Add background toggle
		// TODO: Add line color toggle
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
		  float thickness = (float)getLineThicknessFromUser();
		  if (thickness > 0)
		    lineThicknessStroke = new BasicStroke(thickness);
		} else if (e.getActionCommand().equals("OK"))
			colorChooser.hide();
		else if (e.getActionCommand().equals("size")) {
		  Dimension newDim = getDimensionFromUser();
		  if (newDim != null) {
		    GUIFrame.adjustSize(newDim);
		  }
		}
		else if (e.getActionCommand().equals("bug")) {
		  Platform.openURL(OptionsToolbar.URL_BUG);
		}
	}
	
	public static double getLineThicknessFromUser() {
	  final JSpinner lineThickness = new JSpinner(new SpinnerNumberModel(lineThicknessStroke.getLineWidth(), 1.0, 100.0, 1.0));
	  final JComponent[] comps = new JComponent[] {
	    new JLabel("Thickness"),
	    lineThickness
	  };
//	  JOptionPane.showOptionDialog(null, null, "Line Thckness", JOptionPane.OK_CANCEL_OPTION, arg4, arg5, arg6, arg7)
	  int ans = JOptionPane.showConfirmDialog(PaintPanel.getPaintPanel(), comps, "Line Thickness", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	  if (ans == JOptionPane.OK_OPTION) {
	    double t = (Double)(lineThickness.getValue());
	    return t;
	  }
//	    System.out.println("Cancelled");
//	  else
//	    System.out.println("OK: " + lineThickness.getText());
	  return -1.0;
	}
  
  public static Dimension getDimensionFromUser() {
    
    final JPanel dimensionPanel = new JPanel(new FlowLayout());
    dimensionPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    dimensionPanel.add(new JLabel("Sketch Size: "));
    final JSpinner width = new JSpinner(new SpinnerNumberModel(PaintPanel.getWidthToAdjust(), 170, Integer.MAX_VALUE, 10));
    final JSpinner height = new JSpinner(new SpinnerNumberModel(PaintPanel.getHeightToAdjust(), 215, Integer.MAX_VALUE, 10));
    dimensionPanel.add(width);
    dimensionPanel.add(new JLabel("x"));
    dimensionPanel.add(height);
//    dimensionPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

    final JPanel popupPanel = new JPanel();
    popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
//    popupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    JLabel descr = new JLabel("<html>Enter the required dimensions of the Shape-Sketch draw area.<br>Usually, this is identical to the sketch's dimension.<br>Note that the tool becomes unusable for dimensions smaller than 170 x 215.</html>");
//    descr.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    popupPanel.add(descr);
    popupPanel.add(dimensionPanel);
    // TODO: Maybe add in a description?
//    int ans = JOptionPane.showConfirmDialog(PaintPanel.getPaintPanel(), popupPanel, "Line Thckness", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    int ans = JOptionPane.showConfirmDialog(PaintPanel.getPaintPanel(), dimensionPanel, "Sketch Size (width x height)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (ans == JOptionPane.OK_OPTION) {
      return new Dimension((Integer)width.getValue(), (Integer)height.getValue());
    }
    
    return null;
  }

}
