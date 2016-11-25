import java.awt.Graphics;

public class DText extends DShape {
	 private DTextModel dTextModel;
	    
	    public DText() {
	    	this.dTextModel = new DTextModel();
			 super.setdShapeModel(this.dTextModel);
		     defaultDText();
	    }
	    
	    private void defaultDText() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void draw(Graphics g){
	      
		}
}
