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

public class Canvas extends JPanel{
	final static int CANVAS_SIZE = 400;
	private List<DShape> shapes = new ArrayList<DShape>();
	private DefaultTableModel table;
	private Integer[] columnsData = new Integer[4]; //4 columns
	private DShape selected ;
	private List<Point> selectedKnobs = new ArrayList<Point>(); 
	private JTextField cursor;  //pointer of the selected shape on Canvas

	public Canvas() {
		showCanvasGUI();
		initializeTable();
		initializeMouseEvent();
		//System.out.println("hi this is happening");
	}
	
	public void addShapeToList(DShape shape) {
		shapes.add(shape);
		repaint();
	}
	private void initializeMouseEvent() {
		this.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO call getBound and get rect boundary. ask if it is a shape 
				//loop through the shapes and see if the rect contains this point
				//if yes then put x on it and assign selected to the shape; if not keep looping
				//if at the end all is not within bound, then quit
				//System.out.println("hi this is happening");

				int x = e.getX();
				int y = e.getY();

				for (int i = shapes.size()-1; i>=0; i--){
					DShape shape = shapes.get(i);
					Rectangle bound = shape.getdShapeModel().getBound();

					if (bound.contains(x, y)){
						//System.out.println("hi this works");
						selected = shape; 
						selectedKnobs = selected.getKnobs();
						break;
		
					}
					else
						selectedKnobs.clear();
				}
				repaint();

			}
			
		});
		
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				//only this method needed
				DShapeModel selectedModel = selected.getdShapeModel();

				int x = e.getX();
				int y = e.getY();

				selectedModel.setX(x);
				selectedModel.setY(y);

				selectedKnobs = selected.getKnobs();
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
		});
	}

	private void initializeTable() {
		table = new DefaultTableModel();
		 table.addColumn("X");
		 table.addColumn("Y");
		 table.addColumn("Width");
		 table.addColumn("Height");
		
	}

	public TableModel getTableModel() {
        return table;
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
        	//loop through all the shapes and draw them
            shape.draw(g);
        }

        if(selectedKnobs != null){
        	for(Point p: selectedKnobs){
        		g.drawRect((int) p.getX(), (int) p.getY(), 3, 3);
        		g.setColor(Color.BLACK);
        		g.fillRect((int) p.getX(), (int) p.getY(), 3, 3);
        	}
        }

    }
	/**
	 * bottleneck method
	 * later use to open from a file
	 * @param shape
	 */
	public void addShape(DShapeModel dShapeModel) {
			DShape dShape = null;
			if (dShapeModel instanceof DRectModel) {
				dShape = new DRect();
			}
			else if (dShapeModel instanceof DOvalModel) {
				dShape = new DOval();
			}
			else if (dShapeModel instanceof DLineModel) {
				dShape = new DLine();
			}
			else if(dShapeModel instanceof DTextModel){
				
				dShape = new DText(); 
			}
			
			dShape.setdShapeModel(dShapeModel);
			
			dShape.setCanvas(this);
			shapes.add(dShape);
			repaint();
    }
	
	protected void updateTable(DShape shape){
		 columnsData[0] = shape.getdShapeModel().getX();
		 columnsData[1] = shape.getdShapeModel().getY();
		 columnsData[2] = shape.getdShapeModel().getHeight();
		 columnsData[3] = shape.getdShapeModel().getWidth();

	        table.addRow(columnsData);
	}

	/**
	 *get selected shape
	 *return null if selected is null  or shape
	*/

	public DShape getSelected(){
		return selected; 
	}
}
