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
	private DefaultTableModel tableModel;
	 
	public Canvas() {
		showCanvasGUI();
		initializeTable();
		initializeMouseEvent();
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
		tableModel = new DefaultTableModel();
		 tableModel.addColumn("X");
		 tableModel.addColumn("Y");
		 tableModel.addColumn("Width");
		 tableModel.addColumn("Height");
		
	}

	public TableModel getTableModel() {
        return tableModel;
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
}
