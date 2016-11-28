import java.awt.Color;
import java.util.List;


public abstract class DShapeModel {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private List<ModelListener> listeners;

	public DShapeModel() {

		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.color = Color.GRAY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public List<ModelListener> getListeners() {
		return listeners;
	}

	public void setModelListeners(List<ModelListener> listeners) {
		this.listeners = listeners;
	
	}
}
