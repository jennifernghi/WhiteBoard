import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;

public class Canvas extends JPanel {
	final static int CANVAS_SIZE = 400;
	final static int KNOB_SIZE = 3;
	private List<DShape> shapes = new ArrayList<DShape>();
	private DefaultTableModel table;
	private Integer[] columnsData = new Integer[4]; // 4 columns
	private DShape selected;
	private List<Point> selectedKnobs = new ArrayList<Point>();
	private JTextField cursor; // pointer of the selected shape on Canvas
	private DShapeModelTable dShapeTableModel;

	public Canvas() {
		showCanvasGUI();
		initializeTable();
		initializeMouseEvent();

	}

	/**
	 * set selected shape notify changes on that selected shape
	 * 
	 * @param shape
	 */
	public void setSelected(DShape shape) {
		selected = shape;
		if (shape != null) {
			selected.getdShapeModel().notifyIndividualChange();

		}
		repaint();
	}

	/**
	 * get selected shape
	 * 
	 * @return selected DShape
	 */
	public DShape getSelected() {
		return selected;
	}

	public void addShapeToList(DShape shape) {
		shapes.add(shape);
		repaint();
	}

	private void initializeMouseEvent() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO call getBound and get rect boundary. ask if it is a
				// shape
				// loop through the shapes and see if the rect contains this
				// point
				// if yes then put x on it and assign selected to the shape; if
				// not keep looping
				// if at the end all is not within bound, then quit
				// System.out.println("hi this is happening");

				int x = e.getX();
				int y = e.getY();

				for (int i = shapes.size() - 1; i >= 0; i--) {
					DShape shape = shapes.get(i);
					Rectangle bound = shape.getdShapeModel().getBounds();

					if (bound.contains(x, y)) {
						selectedKnobs = shape.getKnobs();
						setSelected(shape);
						break;
					} else {
						selectedKnobs.clear();
						setSelected(null);
					}
				}

			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// only this method needed

				if (selected != null) {
					DShapeModel selectedModel = selected.getdShapeModel();

					int x = e.getX();
					int y = e.getY();

					selectedModel.setX(x);
					selectedModel.setY(y);

					selectedKnobs = selected.getKnobs();
				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
	}

	private void initializeTable() {
		dShapeTableModel = new DShapeModelTable();
		dShapeTableModel.setCanvas(this);

	}

	public List<DShape> getShapes() {
		return this.shapes;
	}

	private void showCanvasGUI() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));

	}

	/**
	 * paintComponent() loop through all the shapes and draw them
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (DShape shape : shapes) {
			// loop through all the shapes and draw them
			shape.draw(g);
			if (shape.equals(selected)) {
				Rectangle biggerBounds = shape.getBiggerBounds();
				g.drawRect(biggerBounds.x, biggerBounds.y, biggerBounds.width, biggerBounds.height);
			}
		}

		if (selectedKnobs != null) {
			for (Point p : selectedKnobs) {
				g.drawRect((int) p.getX(), (int) p.getY(), KNOB_SIZE, KNOB_SIZE);
				g.setColor(Color.BLACK);
				g.fillRect((int) p.getX(), (int) p.getY(), KNOB_SIZE, KNOB_SIZE);
			}
		}

	}

	/**
	 * bottleneck method later use to open from a file
	 * 
	 * @param shape
	 */
	public void addShape(DShapeModel dShapeModel) {
		DShape dShape = null;
		if (dShapeModel instanceof DRectModel) {
			dShape = new DRect();
		} else if (dShapeModel instanceof DOvalModel) {
			dShape = new DOval();
		} else if (dShapeModel instanceof DLineModel) {
			dShape = new DLine();
		} else if (dShapeModel instanceof DTextModel) {

			dShape = new DText();
		}
		//register listener on dShapeTableModel
		dShapeModel.addModelListener(dShapeTableModel);
		
		//set correct dShapeModel for dShape
		dShape.setdShapeModel(dShapeModel);
		dShape.setCanvas(this);
		
		//add this dShape to shapes list
		addShapeToList(dShape);
		
		//update dShapeTableModel
		dShapeTableModel.fireTableDataChanged();

	}

	    public void deleteShape(){
    	if(selected !=null){
    		//TODO Remove from list, WhiteBoard listeners and dShape listeners, dShapeModel
    		//get selected model, callremove listeners, remove knobs 
    		selected.getdShapeModel().removeAllModelListeners(); //remove all listeners: whiteBoard and Dsahpe   		
    		shapes.remove(selected); //remove from list
    		selectedKnobs.clear(); 
    		setSelected(null);
    		dShapeTableModel.fireTableDataChanged();
    	}
    }

	/**
	 * if selected shape is a dtext repaint it
	 * 
	 * @param text
	 */
	public void repaintText(String text) {
		if (selected != null) {
			if (selected instanceof DText) {
				DText dText = (DText) selected;
				dText.setText(text);
				repaint();
			}
		}

	}

	/**
	 * set font name for dText Shape
	 * 
	 * @param font
	 */
	public void setFontName(String font) {
		if (selected != null) {
			if (selected instanceof DText) {
				DText dText = (DText) selected;
				dText.setFontName(font);
				repaint();
			}
		}
	}

	public TableModel getTableModel() {
		return dShapeTableModel;
	}

	public void moveFront(DShape selected) {
		shapes.remove(selected);
		shapes.add(shapes.size(), selected);
		repaint();
		dShapeTableModel.fireTableDataChanged();
		
	}

	public void moveBack(DShape selected) {
		shapes.remove(selected);
		shapes.add(0, selected);
		repaint();
		dShapeTableModel.fireTableDataChanged();
		
	}

}