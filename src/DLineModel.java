
public class DLineModel extends DShapeModel {
	/**
	 * DLineModel gets X and Y from DShapeModel
	 * a line contains 2 points (x, y) and (x2, y2)
	 */
	private int x2;
	private int y2;
	
	public void setX2(int x2) {
		this.x2=x2;
	}
	
	public int getX2() {
		return this.x2;
	}
	
	public void setY2(int y2) {
		this.y2=y2;
	}
	
	public int getY2() {
		return this.y2;
	}
	
	
	public double calculateSlope() {
		return ((double)(this.getY2() - getY()))/((double)(this.getX2() - getX()));
	}
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DLine";
	}
}
