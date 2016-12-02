import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;


public abstract class DShapeModel {
	
	//the rectangle "conceptual" bound of the shape
	private Rectangle conceptualBound; 
	private Point anchorPoint; //used for resizing, this point(s) not moving while resizing; no need this
	private Color color;
	private ArrayList<ModelListener> listeners = new ArrayList<>();

	public DShapeModel() {
		conceptualBound = new Rectangle(0, 0, 0, 0);
		this.color = Color.GRAY;
	}
	/*
	 * set X of conceptual bound
	 * @param x int
	 */
	public void setX(int x) {
		this.conceptualBound.x = x;
		notifyModelChanged();//notify this shape model has changed
	}
	/**
	 * get x of conceptual bound
	 * @return x
	 */
	public int getX() {
		return this.conceptualBound.x;
	}
	/*
	 * set Y of conceptual bound
	 * @param y int
	 */
	public void setY(int y) {
		this.conceptualBound.y = y;
		notifyModelChanged();//notify this shape model has changed
	}
	
	/**
	 * get y of conceptual bound
	 * @return y
	 */
	public int getY() {
		return conceptualBound.y;
	}
	/*
	 * set width of conceptual bound
	 * @param width int
	 */
	public void setWidth(int width) {
		this.conceptualBound.width = width;
		notifyModelChanged();//notify this shape model has changed
	}
	/**
	 * get width of conceptual bound
	 * @return width
	 */
	public int getWidth() {
		return this.conceptualBound.width;
	}
	/*
	 * set height of conceptual bound
	 * @param height int
	 */
	public void setHeight(int height) {
		this.conceptualBound.height = height;
		notifyModelChanged();//notify this shape model has changed
	}
	/**
	 * get height of conceptual bound
	 * @return height
	 */
	public int getHeight() {
		return this.conceptualBound.height;
	}
	/**
	 * setColor
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
		notifyModelChanged();//notify this shape model has changed
	}
	/**
	 * get color
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	
	/**
	 * add listeners
	 * @param listener
	 */
	public void addModelListener(ModelListener listener) {
		this.listeners.add(listener);
	}
	/**
	 * remove listeners
	 * @param listener
	 */
	public void removeModelListener(ModelListener listener) {
		this.listeners.remove(listener);
	}

	public void removeAllModelListeners(){
        listeners.clear();
    }
	/**
	 * when this model change, loop through listeners
	 * send modelChanged()
	 */
	public void notifyModelChanged() {
		for(ModelListener modelListener: listeners){
			modelListener.modelChanged(this);
		}
	}
	/**
	 * when selected model change, loop through listeners
	 * send modelChanged()
	 */
	 
	public void notifyIndividualChange() {
		for (ModelListener modelListener: listeners)
		{
			modelListener.individualChanged(this);
		}
	}
	/**
	 * get the conceptual bound
	 */
	public void setBounds(Rectangle conceptualBound) {
		this.conceptualBound= conceptualBound;
		notifyModelChanged();
	}
	/**
	 * get the conceptual rectangle bound of the shape
	 * @return
	 */
	public Rectangle getBounds() {
		return this.conceptualBound;
	}
	
	/**
	 * get anchor point
	 * @return point
	 */
	public Point getAnchorPoint() {
		return this.anchorPoint;
	}
	/**
	 * set anchor point
	 * @param point
	 */
	public void setAnchorPoint(Point point) {
		this.anchorPoint=point;
	}
}
