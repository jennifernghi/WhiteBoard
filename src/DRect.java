import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class DRect extends DShape {
	

	@Override
		public void draw(Graphics g){
		Rectangle dRect = new Rectangle(getdShapeModel().getX(), getdShapeModel().getY(), getdShapeModel().getWidth(), getdShapeModel().getHeight());
		Graphics2D s = (Graphics2D) g;
		s.setColor(getdShapeModel().getColor());
		s.fill(dRect);
		}

	
}
