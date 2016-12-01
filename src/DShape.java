import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public abstract class DShape implements ModelListener {
	boolean selected; //is this needed ?
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

	public List<Point> getKnobs(){
		List<Point> points = new ArrayList<Point>();

		Rectangle bound = dShapeModel.getBound();

		points.add(new Point((int)(bound.getX()), (int)(bound.getY())));
		points.add(new Point((int)(bound.getX()+bound.getWidth()), (int)bound.getY()));
		points.add(new Point((int)(bound.getX()), (int)(bound.getY()+bound.getHeight())));
		points.add(new Point((int)(bound.getX()+bound.getWidth()), (int)(bound.getY()+bound.getHeight())));

		return points;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	@Override
	public void modelChanged(DShapeModel model) {
		canvas.repaint();
	}
}
