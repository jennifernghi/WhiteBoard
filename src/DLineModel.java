import java.awt.Point;
import java.awt.Rectangle;


public class DLineModel extends DShapeModel {
	/**
	 * a line contains 2 points  p1 and p2
	 */
	private Point p1;
	private Point p2;

	
	/**
	 * construction
	 * uses p1 as anchorPoint
	 */
	public DLineModel() {
		p1 = new Point();
		p2= new Point();
		setAnchorPoint(p1);
	}
	/**
	 * set P1
	 * @param p1
	 */
	public void setP1(Point p1) {
		this.p1=p1;
		setBounds();	
	}
	/**
	 * get P1
	 * @return p1
	 */
	public Point getP1() {
		return this.p1;
	}
	/**
	 * set P2
	 * @param p2
	 */
	public void setP2(Point p2) {
		this.p2=p2;
		setBounds();
	}
	/**
	 * get P2
	 * @return
	 */
	public Point getP2() {
		return this.p2;
	}
	
	
	/*
	 * set the conceptual bound for a line
	 */
	private void setBounds()
	{
		Rectangle bound= new Rectangle(Math.min(p1.x, p2.x), Math.min(p1.y,p2.y), Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		super.setBounds(bound);
	}
	
	@Override
	/**
	 * set the correct x coordinate of p1 and p2
	 * @param x given X
	 */
	public void setX(int x) {
		int newX = x - super.getX();
		p1.x += newX;
		p2.x += newX;
		setBounds();
	}
	
	@Override
	/**
	 * set the correct y coordinate of p1 and p2
	 * @param y given Y
	 */
	public void setY(int y) {
		int newY = y - super.getY();
		p1.y += newY;
		p2.y += newY;
		setBounds();
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
		setBounds();
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
		setBounds();
	}
	

}
