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
	
	private Point startingPoint, endPoint;

	public enum ShapeTypes {
		SQUARE, RECTANGLE, ELLIPSE, CIRCLE, LINE, POINT, POLYGON;
	}

	public ShapeTypes type = null;

	public Shape(ShapeTypes type) {
		this.type = type;	
		
		switch (type) {
		case SQUARE:
		case RECTANGLE:
			rectangle = new Rectangle();//startingPoint, dim);
			break;
		case CIRCLE:
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
	
	public void setEndPoint(Point p) {
//		switch (type) {
//		case SQUARE:
//		case CIRCLE:
//			int end = Math.min(Math.abs(startingPoint.x - p.x), Math.abs(startingPoint.y - p.y));
//			this.endPoint = new Point(end, end);
//			break;
//		default:
//			this.endPoint = p;
//		}
		
		this.endPoint = p;
		
		switch (type) {
		case RECTANGLE:
//			rectangle.setBounds(Math.min(startingPoint.x, endPoint.x),Math.min(startingPoint.y, endPoint.y),Math.abs(startingPoint.x-endPoint.x),Math.abs(startingPoint.y-endPoint.y));
			rectangle.setBounds(startingPoint.x,startingPoint.y,endPoint.x-startingPoint.x,endPoint.y-startingPoint.y);
			break;
		case SQUARE:
			rectangle.setBounds(startingPoint.x, startingPoint.y, endPoint.x-startingPoint.x, endPoint.y-startingPoint.y);
			break;
		case ELLIPSE:
//			ellipse = new Ellipse2D.Double();
			ellipse.setFrame(startingPoint.x,startingPoint.y,endPoint.x-startingPoint.x,endPoint.y-startingPoint.y);
			break;
		case CIRCLE:
			ellipse.setFrame(startingPoint.x, startingPoint.y, endPoint.x-startingPoint.x, endPoint.y-startingPoint.y);
			break;
//			ellipse = new Ellipse2D.Double();
//			ellipse.setFrame(startingPoint, dim);
//			break;
//		case LINE:
//			line = new Line2D.Double(startingPoint, new Point(dim.width, dim.height));
//			break;
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
			setEndPoint(new Point(x2,y2));
			break;
		case CIRCLE:
		case SQUARE:
			if (x2>=x1 && y2>=y1) {
				setStartingPoint(new Point(x1, y1));
				int side = Math.min(x2-x1, y2-y1);
				setEndPoint(new Point(x1 + side, y1 + side));
			}
			else if (x1>x2 && y2>y1) {
				int side = Math.min(x1-x2, y2-y1);
				setStartingPoint(new Point(x1-side,y1));
				setEndPoint(new Point(x1, y1+side));
			}
			else if (x2>x1 && y1>y2) {
				int side = Math.min(x2-x1, y1-y2);
				setStartingPoint(new Point(x1,y1 - side));
				setEndPoint(new Point(x1 + side, y1));
			}
			else { //x1>=x2 && y1>=y2
				int side = Math.min(x1 - x2, y1 - y2);
				setStartingPoint(new Point(x1 - side, y1 - side));
				setEndPoint(new Point(x1, y1));
			}
			break;
		default:
			setStartingPoint(new Point(Math.min(x1, x2), Math.min(y1, y2)));
			setEndPoint(new Point(Math.max(x2, x1), Math.max(y2, y1)));
			break;
		}
	}
	
	public void drawShape(Graphics g) {
		switch (type) {
//		case CIRCLE:
////			g.setColor(Color.BLUE);
//			g.drawOval(startingPoint.x, startingPoint.y, dim.width, dim.height);
//			break;
		case CIRCLE:
		case ELLIPSE:
			g.drawOval((int)ellipse.getMinX(), (int)ellipse.getMinY(), (int)ellipse.getWidth(), (int)ellipse.getHeight());
			break;
		case LINE:
			g.drawLine(startingPoint.x, startingPoint.y, endPoint.x, endPoint.y);
			break;
//		case POINT:
//			break;
//		case POLYGON:
//			break;
		case SQUARE:
		case RECTANGLE:
//			g.setColor(Color.RED);
			g.drawRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
			break;
//			g.setColor(Color.RED);
//			g.drawRect(startingPoint.x, startingPoint.y, dim.width, dim.height);
//			break;
		default:
			break;
		}
	}
}
