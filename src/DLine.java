import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class DLine extends DShape {
	

	@Override
	public void draw(Graphics g){
		DLineModel dLineModel = (DLineModel) getdShapeModel();
		Point p1 = dLineModel.getP1();
		Point p2 = dLineModel.getP2();
		Graphics2D dLine = (Graphics2D) g;
		dLine.setColor(getdShapeModel().getColor());
		dLine.drawLine(p1.x, p1.y, p2.x, p2.y);
	}
}
