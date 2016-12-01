
public class DTextModel extends DShapeModel {
	private String text;
    private String fontName;
    
    public DTextModel() {
    	//default data of dtext
		this.text = "Hello";
		this.fontName ="Dialog";
	}
    /**
     * set text
     * @param text
     */
    public void setText(String text) {
		this.text = text;
		notifyModelChanged();
	}
    /**
     * get text
     * @return text
     */
    public String getText() {
		return this.text;
	}
    /**
     * set font name
     * @param fontName
     */
    public void setFontName(String fontName) {
		this.fontName = fontName;
		notifyModelChanged();
	}
    /**
     * get font name
     * @return fontName
     */
    public String getFontName() {
		return this.fontName;
	}
    
   
}
