import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class DOval extends DShape {

        
       

		@Override
    	public void draw(Graphics g){
			Shape dOval = new Ellipse2D.Double(getdShapeModel().getX(), getdShapeModel().getY(), getdShapeModel().getWidth(), getdShapeModel().getHeight());
			Graphics2D s = (Graphics2D) g;
			s.setColor(getdShapeModel().getColor());
			s.fill(dOval);
    	}
}
