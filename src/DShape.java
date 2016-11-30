import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class DShape implements ModelListener {
	boolean selected;
	private DShapeModel dShapeModel;
	private Canvas canvas;
	 
	public DShape() {
		dShapeModel=null;
	}
	//abstract method draw()
	public abstract void draw(Graphics g);
	 
	/**
	 * get the correct model for the shape
	 * @return a correct DShapeModel
	 */
	public DShapeModel getdShapeModel() {
		return this.dShapeModel;
	}
	/**
	 * set the correct model
	 * add this shape to listener
	 * @param dShapeModel
	 */
	public void setdShapeModel(DShapeModel dShapeModel) {
		this.dShapeModel = dShapeModel;
		dShapeModel.addModelListener(this);
	}

	/*
	//TODO getBounds
	
	public Rectangle getBounds(){
		return dShapeModel.getBounds();
	}*/
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	@Override
	public void modelChanged(DShapeModel model) {
		canvas.repaint();
	}
}
