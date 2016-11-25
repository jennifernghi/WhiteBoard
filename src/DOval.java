import java.awt.Graphics;

public class DOval extends DShape {

	private DOvalModel dOvalModel;
	
        public DOval(){
	        this.dOvalModel = new DOvalModel();
	   		 super.setdShapeModel(this.dOvalModel);
	   	     defaultDOval();
        }
        
        private void defaultDOval() {
			// TODO Auto-generated method stub
        	dOvalModel.setX(50);
            dOvalModel.setY(50);
            dOvalModel.setWidth(100);
            dOvalModel.setHeight(30);
		}

		@Override
    	public void draw(Graphics g){
			 int x = dOvalModel.getX();
		      int y = dOvalModel.getY();
		      int  width = dOvalModel.getWidth();
		      int height = dOvalModel.getHeight();
		        g.setColor(dOvalModel.getColor());
		        g.fillOval(x, y, width, height);
		        g.drawOval(x, y, width, height);
		        super.draw(g);
    	}
}
