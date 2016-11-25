import java.awt.Graphics;

public class DLine extends DShape {
	private DLineModel dLineModel;
	
    public DLine(){
    	 this.dLineModel = new DLineModel();
		 super.setdShapeModel(this.dLineModel);
	     defaultDLine();
	}
    
    private void defaultDLine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g){
      
	}
}
