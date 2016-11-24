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
			this.canvas = canvas;
			JFrame theFrame = new JFrame("WhiteBoard");
	        theFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        theFrame.setLayout(new BorderLayout());
	        theFrame.add(canvas, BorderLayout.CENTER);
	        //theFrame.add(showControllerGUI(), BorderLayout.WEST);
	        theFrame.setVisible(true);
	        theFrame.pack();
	}



	private Component showControllerGUI() {
		return null;
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
