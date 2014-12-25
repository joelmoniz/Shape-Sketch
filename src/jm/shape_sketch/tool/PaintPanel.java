package jm.shape_sketch.tool;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import jm.shape_sketch.tool.shapes.Shape;

public class PaintPanel extends JPanel {
	private static final long serialVersionUID = 3592247058189697712L;

	public static PaintPanel paintPanel;

	private int x1, x2, y1, y2;

	private List<Shape> shapesList = null;
	private Shape currentShape = null;

	public static PaintPanel getPaintPanel(Dimension size) {
		if (paintPanel == null && size != null)
			paintPanel = new PaintPanel(size);
		return paintPanel;
	}

	public static PaintPanel getPaintPanel() {
		return paintPanel;
	}

	private PaintPanel(Dimension size) {
		super();

		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMinimumSize(size);

		shapesList = new ArrayList<Shape>();
		setBackground(Color.WHITE);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent m) {
				if (currentShape != null) {
					x1 = m.getX();
					y1 = m.getY();
					x2 = x1;
					y2 = y1;
					currentShape.setStartingPoint(m.getPoint());
					currentShape.setEndPoint(new Dimension(x2 - x1, y2 - y1));
					repaint();
				}
			}

			public void mouseReleased(MouseEvent m) {
				if (currentShape != null) {
					x2 = m.getX();
					y2 = m.getY();
					currentShape.setEndPoint(new Dimension(x2 - x1, y2 - y1));
					shapesList.add(currentShape);
					currentShape = null;
					repaint();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent m) {
				if (currentShape != null) {
					x2 = m.getX();
					y2 = m.getY();
					currentShape.setEndPoint(new Dimension(x2 - x1, y2 - y1));
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				paintPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}

		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawRect(x1, y1, x2 - x1, y2 - y1);
		if (currentShape != null)
			currentShape.drawShape(g);
	}

	public Shape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
	}

}
