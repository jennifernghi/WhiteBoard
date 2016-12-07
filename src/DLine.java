import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class DLine extends DShape {
	

	@Override
	public void draw(Graphics g){
		DLineModel dLineModel = (DLineModel) getdShapeModel();
		Point p1 = dLineModel.getP1();
		Point p2 = dLineModel.getP2();
		Graphics2D dLine = (Graphics2D) g;
		dLine.setColor(dLineModel.getColor());
		dLine.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	@Override
 	/**
 	 * this method will give DLine 2 knobs instead of 4
 	 */
 	public List<Point> getKnobs() {
 		DLineModel dLineModel = (DLineModel) getdShapeModel();
 		List<Point> points = new ArrayList<Point>();
 		points.add(dLineModel.getP1());
 		points.add(dLineModel.getP2());
 		
 		return points;
 	}
}
