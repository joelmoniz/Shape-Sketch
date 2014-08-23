package jm.shape_sketch.tool;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIFrame extends JFrame{
	
	static GUIFrame mainFrame;
	private JPanel outerPanel;

	private GUIFrame(){
		super();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(ShapeSketch.TOOL_NAME);
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			// Invoked when a window is in the process of being closed.
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
		outerPanel.add(new ShapeToolbar(250),BorderLayout.LINE_START);
		outerPanel.add(new OptionsToolbar(250),BorderLayout.PAGE_START);
		add(outerPanel);
	}
	
}
