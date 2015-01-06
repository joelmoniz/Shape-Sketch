package jm.shape_sketch.tool.shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Shape {

	private Rectangle rectangle = null;
	private Line2D line = null;
	private Point2D point = null;
	private Ellipse2D ellipse = null;
	private GeneralPath polygon = null;
	
	private Point startingPoint;
	private Dimension dim;

	public enum ShapeTypes {
		SQUARE, RECTANGLE, ELLIPSE, CIRCLE, LINE, POINT, POLYGON;
	}

	public ShapeTypes type = null;

	public Shape(ShapeTypes type) {
		this.type = type;	
		
		switch (type) {
		case RECTANGLE:
			rectangle = new Rectangle();//startingPoint, dim);
			break;
		case ELLIPSE:
			ellipse = new Ellipse2D.Double();
//			ellipse.setFrame(startingPoint, dim);
			break;
		case LINE:
			line = new Line2D.Double();//startingPoint, new Point(dim.width + startingPoint.x, dim.height + startingPoint.y));
			break;
		case POINT:
			break;
		case POLYGON:
			break;
		default:
			break;
		}
	}

	public void setStartingPoint(Point p) {
		startingPoint = p;
	}
	
	public void setDimension(Dimension d) {
		dim = d;
		
		switch (type) {
		case RECTANGLE:
			rectangle = new Rectangle(startingPoint, dim);
			break;
		case ELLIPSE:
			ellipse = new Ellipse2D.Double();
			ellipse.setFrame(startingPoint, dim);
			break;
		case LINE:
			line = new Line2D.Double(startingPoint, new Point(dim.width, dim.height));
			break;
		case POINT:
			break;
		case POLYGON:
			break;
		default:
			break;
		}
	}
	
	public void setEndPoints(int x1, int x2, int y1, int y2) {
		switch (type) {
		case LINE:
			setStartingPoint(new Point(x1,y1));
			setDimension(new Dimension(x2-x1,y2-y1));
			break;
		default:
			setStartingPoint(new Point(Math.min(x1, x2), Math.min(y1, y2)));
			setDimension(new Dimension(Math.abs(x2 - x1), Math.abs(y2 - y1)));
			break;
		}
	}
	
	public void drawShape(Graphics g) {
		switch (type) {
		case CIRCLE:
//			g.setColor(Color.BLUE);
			g.drawOval(startingPoint.x, startingPoint.y, dim.width, dim.height);
			break;
		case ELLIPSE:
			g.drawOval(startingPoint.x, startingPoint.y, dim.width, dim.height);
			break;
		case LINE:
			g.drawLine(startingPoint.x, startingPoint.y, dim.width + startingPoint.x, dim.height + startingPoint.y);
			break;
		case POINT:
			break;
		case POLYGON:
			break;
		case RECTANGLE:
//			g.setColor(Color.RED);
			g.drawRect(startingPoint.x, startingPoint.y, dim.width, dim.height);
			break;
		case SQUARE:
//			g.setColor(Color.RED);
			g.drawRect(startingPoint.x, startingPoint.y, dim.width, dim.height);
			break;
		default:
			break;
		}
	}
}
