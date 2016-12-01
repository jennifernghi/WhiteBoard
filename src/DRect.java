import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class DRect extends DShape {
	

	@Override
		public void draw(Graphics g){
		DRectModel dRectModel = (DRectModel) getdShapeModel();
		Rectangle dRect = new Rectangle(dRectModel.getX(), dRectModel.getY(), dRectModel.getWidth(), dRectModel.getHeight());
		Graphics2D s = (Graphics2D) g;
		s.setColor(getdShapeModel().getColor());
		s.fill(dRect);
		}

	
}
