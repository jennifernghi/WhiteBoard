import java.awt.Graphics;

public class DLine extends DShape {
	private DLineModel dLineModel;
	
    public DLine(){
    	 this.dLineModel = new DLineModel();
		 super.setdShapeModel(this.dLineModel);
	     defaultDLine();
	}
    
    private void defaultDLine() {
		dLineModel.setX(100);
		dLineModel.setY(100);
		dLineModel.setX2(200);
		dLineModel.setY2(200);
		
	}

	@Override
	public void draw(Graphics g){
		 int x = dLineModel.getX();
	      int y = dLineModel.getY();
	      int  x2 = dLineModel.getX2();
	      int y2 = dLineModel.getY2();
	        g.setColor(dLineModel.getColor());
	        
	        g.drawLine(x, y, x2, y2);
	        super.draw(g);
	}
}
