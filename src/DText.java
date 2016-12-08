import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

public class DText extends DShape {
	 

		@Override
		public void draw(Graphics g){
			DTextModel dTextModel = (DTextModel) getdShapeModel();
		
		    	    
		    Graphics2D dText = (Graphics2D) g;
		    
		    Font font = computeFont();
		    dText.setFont(font);
		    dText.setColor(getdShapeModel().getColor());
		    
		    //get current clip
		    Shape clip = dText.getClip();
		    
		    //intersect the clip with the text shape bound
		    dText.setClip(clip.getBounds().createIntersection(getBounds()));
		    //draw text
		    dText.drawString(dTextModel.getText(), getX(), getY() + getHeight());
		    
		    //restore old clip
		    g.setClip(clip);
		}
		/**
		 * called by draw(g)
		 * @return
		 */
		 private Font computeFont() {
			 	double size = 1.0;
				double previous=0;
				Font font = new Font(getFontName(),Font.PLAIN, (int) size);
				FontMetrics fontMetrics = new FontMetrics(font) {};
				while (fontMetrics.getHeight() <= this.getHeight())
				{
					previous = size;
					size = (size * 1.0) + 1;
					Font newSizeFont = font.deriveFont((float)size);
					fontMetrics = new FontMetrics(newSizeFont) {};
				}
				return font.deriveFont((float)previous);
			 
		}
		 /**
		  * set font name
		  * @param fontName string
		  */
		 public void setFontName(String fontName) {
			DTextModel dTextModel = (DTextModel)getdShapeModel();
			dTextModel.setFontName(fontName);
		}
		 /*
		  * set text
		  * 
		  */
		 public void setText(String text) {
				DTextModel dTextModel = (DTextModel)getdShapeModel();
				dTextModel.setText(text);
		}
		 /**
		  * get font name
		  * @return fontName string
		  */
		public String getFontName() {
			DTextModel dTextModel = (DTextModel)getdShapeModel();
			return dTextModel.getFontName();
		} 
		/**
		 * get text
		 * @return string text
		 */
		public String getText() {
			DTextModel dTextModel = (DTextModel)getdShapeModel();
			return dTextModel.getText();
		}
		
}
