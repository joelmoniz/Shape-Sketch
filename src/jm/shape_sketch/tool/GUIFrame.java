package jm.shape_sketch.tool;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.app.Base;

@SuppressWarnings("serial")
public class GUIFrame extends JFrame {
	
	static GUIFrame mainFrame;
	public JPanel outerPanel;
	private ShapeToolbar sToolbar;
	private OptionsToolbar oToolbar;
	private PaintPanel paintArea;
	
	private static final String PAINT_PANEL_NAME = "paintPanel";
  
	static final int DEFAULT_WIDTH = 500;
  static final int DEFAULT_HEIGHT = 500;

	private GUIFrame(Base base){
		super();
		
		Dimension drawingArea = OptionsToolbar.getDimensionFromUser();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(ShapeSketch.TOOL_NAME);
		setResizable(false);
		
		oToolbar = OptionsToolbar.getOptionsToolbar(250);
    sToolbar = ShapeToolbar.getShapeToolbar(250);
		paintArea = PaintPanel.getPaintPanel((drawingArea==null) ? new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT) : drawingArea, base);
		paintArea.setName(PAINT_PANEL_NAME);
		
		addWindowListener(new WindowAdapter() {
			// Invoked when a window is in the process of being closed.
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		outerPanel = new JPanel(new BorderLayout());
		// TODO: Set sizes here, incl margins, size(x,y), etc.
		
		setupGUIFrameComponents();
		
		pack();
	}
	
	public static GUIFrame getGUIFrame(Base base) {
		if (mainFrame == null)
			mainFrame = new GUIFrame(base);
		return mainFrame;
	}
  
  public static GUIFrame getGUIFrame() {
    return mainFrame;
  }
	
	public void setupGUIFrameComponents() {
		outerPanel.add(sToolbar,BorderLayout.LINE_START);
		outerPanel.add(oToolbar,BorderLayout.PAGE_START);
		outerPanel.add(paintArea,BorderLayout.CENTER);
		add(outerPanel);
	}

  public static void adjustSize(Dimension newDim) {
    PaintPanel.getPaintPanel().setPreferredSize(newDim);
    PaintPanel.getPaintPanel().setMaximumSize(newDim);
    PaintPanel.getPaintPanel().setMinimumSize(newDim);
    // Not required- automatically called:
//    GUIFrame.getGUIFrame().outerPanel.revalidate();
    GUIFrame.getGUIFrame().pack();
  }
}
