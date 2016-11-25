import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Canvas extends JPanel{
	final static int CANVAS_SIZE = 400;
	private List<DShape> shapes = new ArrayList<DShape>();
	private DefaultTableModel table;
	private Integer[] columnsData = new Integer[4]; //4 columns
	 
	public Canvas() {
		showCanvasGUI();
		initializeTable();
		initializeMouseEvent();
	}
	
	public void addShapeToList(DShape shape) {
		shapes.add(shape);
		repaint();
	}
	private void initializeMouseEvent() {
		this.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			
			
			@Override
			public void mouseDragged(MouseEvent e) {
				//only this method needed
				
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

    }
	/**
	 * bottleneck method
	 * later use to open from a file
	 * @param shape
	 */
	public void addShape(DShapeModel dShapeModel) {
		 
    }
	
	protected void updateTable(DShape shape){
		 columnsData[0] = shape.getdShapeModel().getX();
		 columnsData[1] = shape.getdShapeModel().getY();
		 columnsData[2] = shape.getdShapeModel().getHeight();
		 columnsData[3] = shape.getdShapeModel().getWidth();

	        table.addRow(columnsData);
	}
}
