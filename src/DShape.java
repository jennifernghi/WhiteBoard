import java.awt.Graphics;

public abstract class DShape {
	boolean selected;
	private DShapeModel dShapeModel;
	 public void draw(Graphics g) {
		 
	}
	 
	public DShapeModel getdShapeModel() {
		return this.dShapeModel;
	}
	
	public void setdShapeModel(DShapeModel dShapeModel) {
		this.dShapeModel = dShapeModel;
	}
}
