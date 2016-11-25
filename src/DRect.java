import java.awt.Graphics;


public class DRect extends DShape {
	private DRectModel dRectModel;
	public DRect() {
		
		 this.dRectModel = new DRectModel();
		 super.setdShapeModel(this.dRectModel);
	     defaultDRect();
	}
	
	 private void defaultDRect() {
		
		 dRectModel.setX(10);
	     dRectModel.setY(10);
	     dRectModel.setWidth(20);
	     dRectModel.setHeight(20);
		
	}

	@Override
		public void draw(Graphics g){
	       int x = dRectModel.getX();
	       int y = dRectModel.getY();
	       int  width = dRectModel.getWidth();
	       int height = dRectModel.getHeight();
	        g.setColor(dRectModel.getColor());
	        g.fillRect(x, y, width, height);
	        g.drawRect(x, y, width, height);
	        super.draw(g);
		}
}
