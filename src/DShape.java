import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point; 

import java.util.List;
import java.util.ArrayList;

public abstract class DShape implements ModelListener {
	
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
	 * call getBounds() in dShapeModel
	 */
	public Rectangle getBounds(){
		return dShapeModel.getBounds();
	}
	


	public List<Point> getKnobs(){
		List<Point> points = new ArrayList<Point>();

		Rectangle bound = dShapeModel.getBounds();

		points.add(new Point((int)(bound.getX()), (int)(bound.getY())));
		points.add(new Point((int)(bound.getX()+bound.getWidth()), (int)bound.getY()));
		points.add(new Point((int)(bound.getX()), (int)(bound.getY()+bound.getHeight())));
		points.add(new Point((int)(bound.getX()+bound.getWidth()), (int)(bound.getY()+bound.getHeight())));

		return points;
	}

	/**
	 * set canvas
	 * @param canvas
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	/*
	 * as modelChanged canvas repaint
	 * 
	 */
	public void modelChanged(DShapeModel model) {
		canvas.repaint();
	}
	
	@Override
	public void individualChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * get X from dShapeModel
	 * @return X
	 */
	public int getX() {
		return this.dShapeModel.getX();
	}
	
	/**
	 * get Y from dShapeModel
	 * @return Y
	 */
	public int getY() {
		return this.dShapeModel.getY();
	}
	/**
	 * get width from dShapeModel
	 * @return width
	 */
	public int getWidth() {
		return this.dShapeModel.getWidth();
	}
	/**
	 * get height from dShapeModel
	 * @return height
	 */
	public int getHeight() {
		return this.dShapeModel.getHeight();
	}
	/**
	 * get the bigger rcetangle that bound outside the selected shape
	 * @return Rectangle
	 */
	public Rectangle getBiggerBounds()
	{
		return new Rectangle (this.getBounds().x -2, this.getBounds().y -2, this.getBounds().width + 4, this.getBounds().height+4);
	}
}
