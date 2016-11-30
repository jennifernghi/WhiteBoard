import java.awt.Point;
import java.awt.Rectangle;


public class DLineModel extends DShapeModel {
	/**
	 * a line contains 2 points  p1 and p2
	 */
	private Point p1;
	private Point p2;
	//pin is a point used as an anchor
	//private Point pin;
	/**
	 * construction
	 * uses p1 as pin
	 */
	public DLineModel() {
		p1 = new Point();
		p2= new Point();
		setAnchorPoint(p1);
	}
	public void setP1(Point p1) {
		this.p1=p1;
		setBound();	
	}
	
	public Point getP1() {
		return this.p1;
	}
	
	public void setP2(Point p2) {
		this.p2=p2;
		setBound();
	}
	
	public Point getP2() {
		return this.p2;
	}
	
	
	/*
	 * set the conceptual bound for a line
	 */
	private void setBound()
	{
		Rectangle bound= new Rectangle(Math.min(p1.x, p2.x), Math.min(p1.y,p2.y), Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		super.setBound(bound);
	}
	
	@Override
	/**
	 * set the correct x coordinate of p1 and p2
	 * @param x given X
	 */
	public void setX(int x) {
		int newX = x;
		p1.x += newX;
		p2.x += newX;
		setBound();
	}
	
	@Override
	/**
	 * set the correct y coordinate of p1 and p2
	 * @param y given Y
	 */
	public void setY(int y) {
		int newY = y;
		p1.y += newY;
		p2.y += newY;
		setBound();
	}
	
	@Override
	/**
	 * Set correct distance (width) between p1 and p2
	 * @param width  
	 */
	public void setWidth(int width) {
		if (super.getAnchorPoint()==p1){
			p2.x = p1.x + width;
		}
		else{
			p1.x = p2.x + width;
		}
		setBound();
	}
	
	@Override
	/**
	 * Set correct distance (height) between p1 and p2
	 * @param height  
	 */
	public void setHeight(int height) {
		if (super.getAnchorPoint()==p1){
			p2.y = p1.y + height;
		}
		else{
			p1.y = p2.y + height;
		}
		setBound();
	}
	
	
	
	
}
