package jm.shape_sketch.tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import jm.shape_sketch.tool.shapes.Shape;
import jm.shape_sketch.tool.shapes.Shape.ShapeTypes;

public class PaintPanel extends JPanel {
	private static final long serialVersionUID = 3592247058189697712L;

	public static PaintPanel paintPanel;

	private int x1, x2, y1, y2;

	private List<Shape> shapesList = null;
	private Shape currentShape = null;
	
	private ShapeTypes currentShapeType = null;

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
				if (currentShapeType != null) {
					x1 = m.getX();
					y1 = m.getY();
					x2 = x1;
					y2 = y1;
					if (currentShape == null)
						currentShape = new Shape(currentShapeType);
//					currentShape.setStartingPoint(m.getPoint());
//					currentShape.setDimension(new Dimension(x2 - x1, y2 - y1));
					currentShape.setEndPoints(x1, x2, y1, y2);
					repaint();
				}
			}

			public void mouseReleased(MouseEvent m) {
				if (currentShapeType != null) {
					x2 = m.getX();
					y2 = m.getY();
//					currentShape.setStartingPoint(new Point(Math.min(x1, x2), Math.min(y1, y2)));
//					currentShape.setEndPoint(new Dimension(Math.abs(x2 - x1), Math.abs(y2 - y1)));
					
					Dimension panelSize = getSize();
					if (x2 <= panelSize.getWidth() && y2 <= panelSize.getHeight() && x2 >= 0 && y2 >= 0) {
						// currentShape.setStartingPoint(new Point(Math.min(x1,
						// x2), Math.min(y1, y2)));
						// currentShape.setDimension(new Dimension(Math.abs(x2 -
						// x1), Math.abs(y2 - y1)));
						currentShape.setEndPoints(x1, x2, y1, y2);
					}
					else {
						int x2n, y2n;
						x2n = x2>=0 ? Math.min(x2, panelSize.width - 1) : 0;
						y2n = y2>=0 ? Math.min(y2, panelSize.height - 1) : 0;
						currentShape.setEndPoints(x1, x2n, y1, y2n);
					}
					
					shapesList.add(currentShape);
					currentShape = null;
					repaint();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent m) {
				if (currentShapeType != null) {
					x2 = m.getX();
					y2 = m.getY();
					
					Dimension panelSize = getSize();
					if (x2 <= panelSize.getWidth() && y2 <= panelSize.getHeight() && x2 >= 0 && y2 >= 0) {
						// currentShape.setStartingPoint(new Point(Math.min(x1,
						// x2), Math.min(y1, y2)));
						// currentShape.setDimension(new Dimension(Math.abs(x2 -
						// x1), Math.abs(y2 - y1)));
						currentShape.setEndPoints(x1, x2, y1, y2);
					}
					else {
						int x2n, y2n;
						x2n = x2>=0 ? Math.min(x2, panelSize.width - 1) : 0;
						y2n = y2>=0 ? Math.min(y2, panelSize.height - 1) : 0;
						currentShape.setEndPoints(x1, x2n, y1, y2n);
					}
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
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
		// g.drawRect(x1, y1, x2 - x1, y2 - y1);
		for (Shape drawn: shapesList)
			drawn.drawShape(g2);
		if (currentShape != null)
			currentShape.drawShape(g2);
	}

	public Shape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
	}

	public ShapeTypes getCurrentShapeType() {
		return currentShapeType;
	}

	public void setCurrentShapeType(ShapeTypes currentShapeType) {
		this.currentShapeType = currentShapeType;
	}

}
