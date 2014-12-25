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

@SuppressWarnings("serial")
public class GUIFrame extends JFrame {
	
	static GUIFrame mainFrame;
	private JPanel outerPanel;
	private ShapeToolbar sToolbar;
	private OptionsToolbar oToolbar;
	private PaintPanel paintArea;
	
	private static final String PAINT_PANEL_NAME = "paintPanel";

	private GUIFrame(){
		super();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(ShapeSketch.TOOL_NAME);
		setResizable(false);
		
		sToolbar = new ShapeToolbar(250);
		oToolbar = new OptionsToolbar(250);
		paintArea = PaintPanel.getPaintPanel(new Dimension(500,500));
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
	
	public static GUIFrame getGUIFrame() {
		if (mainFrame == null)
			mainFrame = new GUIFrame();
		return mainFrame;
	}
	
	public void setupGUIFrameComponents() {
		outerPanel.add(sToolbar,BorderLayout.LINE_START);
		outerPanel.add(oToolbar,BorderLayout.PAGE_START);
		outerPanel.add(paintArea,BorderLayout.CENTER);
		add(outerPanel);
	}
}
