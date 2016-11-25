import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;



public class WhiteBoard extends JFrame implements ModelListener{

	
	private Canvas canvas;
	
	
	 public WhiteBoard() {
		 	
	        showWhiteBoardGUI();
	    }
	    
	   
	 
	private void showWhiteBoardGUI() {
		//whole whiteboard gui + canvas
			Canvas canvas = new Canvas();
			ControlGui controlGui = new ControlGui(canvas,canvas.getTableModel());
			this.canvas = canvas;
			
			JFrame frame = new JFrame("WhiteBoard");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.add(canvas, BorderLayout.CENTER);
			frame.add(controlGui, BorderLayout.WEST);
			frame.setVisible(true);
			frame.pack();
	}





	public static void main(String[] args) {
		WhiteBoard whiteBoard = new WhiteBoard();

	}



	@Override
	public void modelChanged(DShapeModel model) {
		//as model changed repaint components
		repaint();
	}
	 
}

//for testing purposes
