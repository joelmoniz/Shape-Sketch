package jm.shape_sketch.tool.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import jm.shape_sketch.tool.OptionsToolbar;
import jm.shape_sketch.tool.PaintPanel;

public class Shape {

	public static final Color DEFAULT_FILL = Color.WHITE;
  private Rectangle rectangle = null;
	private Line2D line = null;
	private Point2D point = null;
	private Ellipse2D ellipse = null;
	private GeneralPath polygon = null;
	
	private Point startingPoint, endPoint;
	
	private Color lineColor, fillColor;
	private BasicStroke lineThicknessStroke;
	private boolean isFilled, isStroked;

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

	private void setStartingPoint(Point p) {
		startingPoint = p;
	}
	
	private void setEndPoint(Point p) {
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
	
	private void colourize() {
		this.lineColor = OptionsToolbar.getLineColor() == null ? Color.BLACK : OptionsToolbar.getLineColor();
		this.fillColor = OptionsToolbar.getFillColor() == null ? Shape.DEFAULT_FILL : OptionsToolbar.getFillColor();
		this.lineThicknessStroke = OptionsToolbar.getLineThickness();
		this.isFilled = OptionsToolbar.getFillColor() == null;
		this.isStroked = OptionsToolbar.getLineColor() == null;
	}
	
	public Color getLineColor() {
    return lineColor;
  }

  public Color getFillColor() {
    return fillColor;
  }

  public int getLineThickness() {
    return (int)lineThicknessStroke.getLineWidth();
  }

  public void setEndPoints(int x1, int x2, int y1, int y2) {
		colourize();
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

	public void drawShape(Graphics2D g) {
		switch (type) {
		case CIRCLE:
		case ELLIPSE:
//			g.setColor(Color.RED);
//			g.fillOval((int)ellipse.getMinX()-3, (int)ellipse.getMinY()-3, (int)ellipse.getWidth()+6, (int)ellipse.getHeight()+6);
//			g.setColor(Color.BLUE);
//			g.fillOval((int)ellipse.getMinX(), (int)ellipse.getMinY(), (int)ellipse.getWidth(), (int)ellipse.getHeight());
			g.setColor(fillColor);
	        g.fillOval((int)ellipse.getMinX(), (int)ellipse.getMinY(), (int)ellipse.getWidth(), (int)ellipse.getHeight());      
//	        g.setStroke(new BasicStroke(1.0f));
	        g.setColor(lineColor);
	        g.setStroke(lineThicknessStroke);
	        g.drawOval((int)ellipse.getMinX(), (int)ellipse.getMinY(), (int)ellipse.getWidth(), (int)ellipse.getHeight());      
			break;
		case LINE:
			g.setColor(lineColor);
			g.setStroke(lineThicknessStroke);
			g.drawLine(startingPoint.x, startingPoint.y, endPoint.x, endPoint.y);
			break;
		case POINT:
			break;
		case POLYGON:
			break;
		case SQUARE:
		case RECTANGLE:
			g.setColor(fillColor);
			g.setStroke(lineThicknessStroke);
//			g.fillRect(rectangle.x+1,rectangle.y+1,rectangle.width-1,rectangle.height-1);
			g.fillRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
			g.setColor(lineColor);
//			g.setStroke(new BasicStroke(5.0f));
			g.drawRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
			break;
		default:
			break;
		}
	}

  public String toCode(boolean codeFillColor, boolean codeLineColor, boolean codeLineThickness) {
    StringBuilder sb = new StringBuilder("");
    if (codeFillColor) {
      sb.append("fill(");
      sb.append(fillColor.getRed());
      sb.append(',');
      sb.append(fillColor.getGreen());
      sb.append(',');
      sb.append(fillColor.getBlue());
      sb.append(");");
    }
    if (codeLineColor) {
      sb.append("stroke(");
      sb.append(lineColor.getRed());
      sb.append(',');
      sb.append(lineColor.getGreen());
      sb.append(',');
      sb.append(lineColor.getBlue());
      sb.append(");");
    }
    if (codeLineThickness) {
      sb.append("strokeWeight(");
      sb.append((int)lineThicknessStroke.getLineWidth());
      sb.append(");");
    }
    switch (type) {
    case CIRCLE:
    case ELLIPSE:
      sb.append("ellipse(");
      sb.append((float)(ellipse.getMinX()+ellipse.getWidth()/2.0));
      sb.append(",");
      sb.append((float)(ellipse.getMinY()+ellipse.getHeight()/2.0));
      sb.append(",");
      sb.append((float)ellipse.getWidth());
      sb.append(",");
      sb.append((float)ellipse.getHeight());
      sb.append(");");
      break;
    case LINE:
      sb.append("line(");
      sb.append((float)startingPoint.x);
      sb.append(",");
      sb.append((float)startingPoint.y);
      sb.append(",");
      sb.append((float)endPoint.x);
      sb.append(",");
      sb.append((float)endPoint.y);
      sb.append(");");
      break;
    case POINT:
      break;
    case POLYGON:
      break;
    case SQUARE:
    case RECTANGLE:
      sb.append("rect(");
      sb.append((float)rectangle.x);
      sb.append(",");
      sb.append((float)rectangle.y);
      sb.append(",");
      sb.append((float)rectangle.width);
      sb.append(",");
      sb.append((float)rectangle.height);
      sb.append(");");
      break;
    default:
      break;
    }
    return sb.toString();
  }
}
