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
	}

	public void setStartingPoint(Point p) {
		startingPoint = p;
	}
	
	public void setEndPoint(Dimension d) {
		dim = d;
		
		switch (type) {
		case RECTANGLE:
			rectangle = new Rectangle(startingPoint, dim);
			break;
		case ELLIPSE:
			ellipse = new Ellipse2D() {

				@Override
				public Rectangle2D getBounds2D() {
					return null;
				}

				@Override
				public double getHeight() {
					return this.getHeight();
				}

				@Override
				public double getWidth() {
					return this.getWidth();
				}

				@Override
				public double getX() {
					return this.getCenterX();
				}

				@Override
				public double getY() {
					return this.getCenterY();
				}

				@Override
				public boolean isEmpty() {
					return false;
				}

				@Override
				public void setFrame(double arg0, double arg1, double arg2,
						double arg3) {}

			};
			ellipse.setFrame(startingPoint, dim);
			break;
		case LINE:
			line = new Line2D() {
				
				@Override
				public Rectangle2D getBounds2D() {
					return null;
				}
				
				@Override
				public void setLine(double x1, double y1, double x2, double y2) {}
				
				@Override
				public double getY2() {
					return 0;
				}
				
				@Override
				public double getY1() {
					return 0;
				}
				
				@Override
				public double getX2() {
					return 0;
				}
				
				@Override
				public double getX1() {
					return 0;
				}
				
				@Override
				public Point2D getP2() {
					return null;
				}
				
				@Override
				public Point2D getP1() {
					return null;
				}
			};
			break;
		case POINT:
			break;
		case POLYGON:
			break;
		default:
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
//			g.drawLine(startingPoint.x, startingPoint.y, dim.width, dim.height);
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
