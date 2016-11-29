import java.awt.Graphics;
import java.awt.Rectangle;

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

	//TODO getBounds

	public Rectangle getBounds(){
		return dShapeModel.getBounds();
	}
}
