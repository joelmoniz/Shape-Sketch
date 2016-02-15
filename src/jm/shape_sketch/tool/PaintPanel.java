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
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jm.shape_sketch.tool.shapes.Shape;
import jm.shape_sketch.tool.shapes.Shape.ShapeTypes;
import processing.app.Base;
import processing.app.Formatter;

public class PaintPanel extends JPanel {
	private static final long serialVersionUID = 3592247058189697712L;

	public static final Color DEFAULT_BACKGROUND = new Color(204, 204, 204);
	
	public static PaintPanel paintPanel;

	private int x1, x2, y1, y2;

	private List<Shape> shapesList = null;
	private Shape currentShape = null;
	
	private ShapeTypes currentShapeType = null;
	
	private static int methodNumber = 0;
	
	private static Base base;
	private static Formatter formatter;

	public static PaintPanel getPaintPanel(Dimension size) {
		if (paintPanel == null && size != null)
			paintPanel = new PaintPanel(size);
		return paintPanel;
	}

	public static PaintPanel getPaintPanel() {
		return paintPanel;
	}

  public static PaintPanel getPaintPanel(Dimension size, Base base) {
    if (paintPanel == null && size != null) {
      if (base == null) {
        paintPanel = new PaintPanel(size);
      }
      else {
        paintPanel = new PaintPanel(size, base);
      }
    }
    return paintPanel;
  }

  private PaintPanel(Dimension size, Base base) {
    this(size);
    PaintPanel.base = base;
    PaintPanel.formatter = base.getActiveEditor().createFormatter();
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
		setBackground(OptionsToolbar.getBackgroundColor() == null ? PaintPanel.DEFAULT_BACKGROUND : OptionsToolbar.getBackgroundColor());
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
	
	public static int getWidthToAdjust() {
	  return (paintPanel == null || paintPanel.getWidth() == 0)? GUIFrame.DEFAULT_WIDTH : paintPanel.getWidth();
	}
  
  public static int getHeightToAdjust() {
    return (paintPanel == null || paintPanel.getHeight() == 0)? GUIFrame.DEFAULT_HEIGHT : paintPanel.getHeight();
  }

  public static String convertShapesToCode() {
    
    if (OptionsToolbar.getBackgroundColor() == null && paintPanel.shapesList.isEmpty()) {
      Object[] optionK = {"OK"};
      JOptionPane.showOptionDialog(PaintPanel.getPaintPanel(), 
                          "<html>Nothing has been drawn yet. The function will be empty.</html>",
                           "Shapes to Code",
                           JOptionPane.PLAIN_MESSAGE,
                           JOptionPane.PLAIN_MESSAGE,
                           null,
                           optionK,
                           optionK[0]);
      return null;
    }
    
    String mName = getMethodName();
    
    if (mName == null)
      return  null;
    
    StringBuilder codeBuilder = new StringBuilder("void ");
    codeBuilder.append(mName);
    codeBuilder.append("(){");
    
    if (OptionsToolbar.getBackgroundColor() != null) {
      codeBuilder.append("background(");
      codeBuilder.append(OptionsToolbar.getBackgroundColor().getRed());
      codeBuilder.append(",");
      codeBuilder.append(OptionsToolbar.getBackgroundColor().getGreen());
      codeBuilder.append(",");
      codeBuilder.append(OptionsToolbar.getBackgroundColor().getBlue());
      codeBuilder.append(");");
    }      
    
    if (paintPanel.shapesList.size() > 0) {
      Color prevStrokeColor = paintPanel.shapesList.get(0).getLineColor();
      Color prevFillColor = paintPanel.shapesList.get(0).getFillColor();
      int prevThickness = paintPanel.shapesList.get(0).getLineThickness();
      
      Color currStrokeColor, currFillColor;
      int currThickness;
      
      // TODO: codeFillColor
      boolean codeFillColor = !prevFillColor.equals(Shape.DEFAULT_FILL); 
      boolean codeLineColor = !prevStrokeColor.equals(Color.BLACK);
      boolean codeLineThickness = !(prevThickness == 1);
      codeBuilder.append(paintPanel.shapesList.get(0).toCode(codeFillColor, codeLineColor, codeLineThickness));
      
      for (int i=1; i<paintPanel.shapesList.size(); i++) {
        currStrokeColor = paintPanel.shapesList.get(i).getLineColor();
        currFillColor = paintPanel.shapesList.get(i).getFillColor();
        currThickness = paintPanel.shapesList.get(i).getLineThickness();
        
        codeBuilder.append(paintPanel.shapesList.get(i).toCode(!currFillColor.equals(prevFillColor), !currStrokeColor.equals(prevStrokeColor), prevThickness!=currThickness));
        
        prevStrokeColor = currStrokeColor;
        prevFillColor = currFillColor;
        prevThickness = currThickness;
      }
    }
    
    codeBuilder.append("}");
    return formatter.format(codeBuilder.toString());
  }

  private static String getMethodName() {
      final JTextField methodName = new JTextField();
      
      // TODO: Replace this with a hint instead?
      methodName.setText("shapeSketch" + methodNumber);
      
      final JComponent[] comps = new JComponent[] {
        new JLabel("Method name:"),
        methodName
      };
//      JOptionPane.showOptionDialog(null, null, "Line Thckness", JOptionPane.OK_CANCEL_OPTION, arg4, arg5, arg6, arg7)
      int ans = JOptionPane.showConfirmDialog(PaintPanel.getPaintPanel(), comps, "Shapes to Code", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (ans == JOptionPane.OK_OPTION) {
        String name = methodName.getText();
        while (ans == JOptionPane.OK_OPTION && !isValidFunctionName(name)) {
          Object[] optionK = {"OK"};
          JOptionPane.showOptionDialog(PaintPanel.getPaintPanel(), 
                              "<html>Please enter a valid method name. A valid name can be any combination<br>"
                                  + "of letters, numbers, and underscores (but cannot start with a number).</html>",
                               "Shapes to Code",
                               JOptionPane.PLAIN_MESSAGE,
                               JOptionPane.PLAIN_MESSAGE,
                               null,
                               optionK,
                               optionK[0]);
          ans = JOptionPane.showConfirmDialog(PaintPanel.getPaintPanel(), comps, "Line Thickness", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
          name = methodName.getText();
        }
        if (ans == JOptionPane.OK_OPTION && isValidFunctionName(name)) {
          if (name.equals("shapeSketch" + methodNumber))
            methodNumber++;
          return name;
        }
        else
          return null;
      }
      return null;
  }
  
  private static boolean isValidFunctionName(String fn) {
    if (fn == null || fn.split("\\s+").length > 1)
      return false;
    Pattern FN_NAME_PATTERN = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");
    return FN_NAME_PATTERN.matcher(fn).find();
  }

  public static void appendShapesCodeToActiveEditor() {
    String fnCode = PaintPanel.convertShapesToCode();
    
    if (fnCode == null)
      return;
    
    StringBuilder sb = new StringBuilder(base.getActiveEditor().getText());
    sb.append("\n\n");
    sb.append(fnCode);
    base.getActiveEditor().setText(sb.toString());
  }

}
