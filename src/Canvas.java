import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;

public class Canvas extends JPanel {
	final static int CANVAS_SIZE = 400;
	final static int KNOB_SIZE = 9;
	private List<DShape> shapes = new ArrayList<DShape>();
	private DefaultTableModel table;
	private Integer[] columnsData = new Integer[4]; // 4 columns
	private DShape selected;
	private List<Point> selectedKnobs = new ArrayList<Point>();
	private DShapeModelTable dShapeTableModel;
	private Point movingPt;
	private Point anchorPt;
	private int indexOfMoving;
	private int selectedX;
	private int selectedY;

	public Canvas() {
		showCanvasGUI();
		initializeTable();
		initializeMouseEvent();

	}

	/**
	 * set selected shape notify changes on that selected shape
	 * 
	 * @param shape
	 */
	public void setSelected(DShape shape) {
		selected = shape;
		if (shape != null) {
			selected.getdShapeModel().notifyIndividualChange();

		}
		repaint();
	}

	/**
	 * get selected shape
	 * 
	 * @return selected DShape
	 */
	public DShape getSelected() {
		return selected;
	}

	public void addShapeToList(DShape shape) {
		shapes.add(shape);
		repaint();
	}

	private void initializeMouseEvent() {

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO call getBound and get rect boundary. ask if it is a
				// shape
				// loop through the shapes and see if the rect contains this
				// point
				// if yes then put x on it and assign selected to the shape; if
				// not keep looping
				// if at the end all is not within bound, then quit
				// System.out.println("hi this is happening");

				 selectedX = e.getX();
				 selectedY= e.getY();

				 int x = e.getX();
				 int y = e.getY();


				for (int i = shapes.size() - 1; i >= 0; i--) {
					DShape shape = shapes.get(i);
					Rectangle biggerBound = shape.getBiggerBounds();

					if (biggerBound.contains(x, y)) {
						selectedKnobs = shape.getKnobs();
						//selectedX = x;
						//selectedY = y;

						for (Point p : selectedKnobs) {
							 
							 //System.out.println(p.getX()+" "+p.getY());

							Rectangle knobBound = new Rectangle((int) (p.getX() - 4.5), (int) (p.getY() - 4.5),
									KNOB_SIZE, KNOB_SIZE);
							if (knobBound.contains(x, y)) {
								// System.out.println("hi this works");

								movingPt = p;

								// find anchor point
								indexOfMoving = selectedKnobs.indexOf(movingPt);
								anchorPt = selectedKnobs.get((indexOfMoving + 2) % 4);
								// System.out.println(((index+2)%4)+" "+index+"
								// "+anchorPt.getX() +" "+anchorPt.getY());

								break;
							} else {
								movingPt = null;
							}
						}
						setSelected(shape);
						break;
					} else {
						selectedKnobs.clear();
						setSelected(null);
					}

				}
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// only this method needed

				if (selected != null) {

					if (movingPt != null) {
						movingPt = new Point(e.getX(), e.getY());
						Rectangle newBound;
						// when moving knob is upper left
						if (indexOfMoving == 0) {
							newBound = new Rectangle(
									(int) movingPt.getX(), 
									(int) movingPt.getY(),
									(int) Math.abs(anchorPt.getX() - movingPt.getX()),
									(int) Math.abs(anchorPt.getY() - movingPt.getY()));
						} else if (indexOfMoving == 1) {
							// when moving knob is upper right
							newBound = new Rectangle(
									(int) (movingPt.getX() - (int) Math.abs(anchorPt.getX() - movingPt.getX())),
									(int) movingPt.getY(), 
									(int) Math.abs(anchorPt.getX() - movingPt.getX()),
									(int) Math.abs(anchorPt.getY() - movingPt.getY()));
						} else if (indexOfMoving == 2) {
							// when moving knob is lower right
							newBound = new Rectangle(
									(int) (movingPt.getX() - (int) Math.abs(anchorPt.getX() - movingPt.getX())),
									(int) (movingPt.getY() - (int) Math.abs(anchorPt.getY() - movingPt.getY())),
									(int) Math.abs(anchorPt.getX() - movingPt.getX()),
									(int) Math.abs(anchorPt.getY() - movingPt.getY()));
						} else {
							// when moving knob is lower left
							newBound = new Rectangle(
									(int) movingPt.getX(),
									(int) (movingPt.getY() - (int) Math.abs(anchorPt.getY() - movingPt.getY())),
									(int) Math.abs(anchorPt.getX() - movingPt.getX()),
									(int) Math.abs(anchorPt.getY() - movingPt.getY()));

						}

						//if the shape is flipped horizontal
						if (newBound.getWidth() ==0){
							if (indexOfMoving ==2) 
								indexOfMoving = 3;
							else if (indexOfMoving ==3)
								indexOfMoving = 2;
							else if (indexOfMoving ==0)
								indexOfMoving =1;
							else
								indexOfMoving =0;
						}

						//if the shape is flipped vertical
						if (newBound.getHeight() == 0){
							if(indexOfMoving==2)
								indexOfMoving = 1;
							else if (indexOfMoving ==3)
								indexOfMoving =0;
							else if(indexOfMoving ==0)
								indexOfMoving =3;
							else
								indexOfMoving =2;
						}


						selected.getdShapeModel().setX((int) newBound.getX());
						selected.getdShapeModel().setY((int) newBound.getY());
						selected.getdShapeModel().setWidth((int) newBound.getWidth());
						selected.getdShapeModel().setHeight((int) newBound.getHeight());
						selectedKnobs = selected.getKnobs();
						anchorPt =selectedKnobs.get((indexOfMoving + 2) % 4);

					} else {
						DShapeModel selectedModel = selected.getdShapeModel();
					
						int originalX = selectedModel.getX();
						int originalY = selectedModel.getY();

						int x = e.getX(); //getting the current point
						int y = e.getY();

						int xmoved = x-selectedX;
						int ymoved = y-selectedY;

						selectedX = x;
						selectedY = y; 


						//int xmoved = (originalX+e.getX())- (originalX+selectedX);
						//int ymoved = (originalY+e.getY())- (originalY+selectedY);


						selectedModel.setX(originalX+xmoved);
						selectedModel.setY(originalY+ymoved);

						


					}

					selectedKnobs = selected.getKnobs();

				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
	}

	private void initializeTable() {
		dShapeTableModel = new DShapeModelTable();
		dShapeTableModel.setCanvas(this);

	}

	public List<DShape> getShapes() {
		return this.shapes;
	}

	private void showCanvasGUI() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));

	}

	/**
	 * paintComponent() loop through all the shapes and draw them
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (DShape shape : shapes) {
			// loop through all the shapes and draw them
			shape.draw(g);
			// if (shape.equals(selected)) {
			// Rectangle biggerBounds = shape.getBiggerBounds();
			// g.drawRect(biggerBounds.x, biggerBounds.y, biggerBounds.width,
			// biggerBounds.height);
			// }
		}

		if (selectedKnobs != null) {
			for (Point p : selectedKnobs) {
				g.drawRect((int) (p.getX() - 4.5), (int) (p.getY() - 4.5), KNOB_SIZE, KNOB_SIZE);
				g.setColor(Color.BLACK);
				g.fillRect((int) (p.getX() - 4.5), (int) (p.getY() - 4.5), KNOB_SIZE, KNOB_SIZE);
			}
		}

	}

	/**
	 * bottleneck method later use to open from a file
	 * 
	 * @param shape
	 */
	public void addShape(DShapeModel dShapeModel) {
		DShape dShape = null;
		if (dShapeModel instanceof DRectModel) {
			dShape = new DRect();
		} else if (dShapeModel instanceof DOvalModel) {
			dShape = new DOval();
		} else if (dShapeModel instanceof DLineModel) {
			dShape = new DLine();
		} else if (dShapeModel instanceof DTextModel) {

			dShape = new DText();
		}
		// register listener on dShapeTableModel
		dShapeModel.addModelListener(dShapeTableModel);

		// set correct dShapeModel for dShape
		dShape.setdShapeModel(dShapeModel);
		dShape.setCanvas(this);

		// add this dShape to shapes list
		addShapeToList(dShape);

		// update dShapeTableModel
		dShapeTableModel.fireTableDataChanged();

	}
	
	public DShape getShape(int id) {
		DShape dShape = null;
		for(DShape shape: shapes){
			DShapeModel dShapeModel = shape.getdShapeModel();
			if(dShapeModel.getID()==id){
				dShape= shape;
			}
		}
		if(dShape==null){
			System.err.println("not found!");
		}
		return dShape;
	}
	public void deleteShape() {
		if (selected != null) {
			// TODO Remove from list, WhiteBoard listeners and dShape listeners,
			// dShapeModel
			// get selected model, callremove listeners, remove knobs
			selected.getdShapeModel().removeAllModelListeners(); // remove all
																	// listeners:
																	// whiteBoard
																	// and
																	// Dsahpe
			shapes.remove(selected); // remove from list
			selectedKnobs.clear();
			setSelected(null);
			dShapeTableModel.fireTableDataChanged();
		}
	}

	/**
	 * if selected shape is a dtext repaint it
	 * 
	 * @param text
	 */
	public void repaintText(String text) {
		if (selected != null) {
			if (selected instanceof DText) {
				DText dText = (DText) selected;
				dText.setText(text);
				repaint();
			}
		}

	}

	/**
	 * set font name for dText Shape
	 * 
	 * @param font
	 */
	public void setFontName(String font) {
		if (selected != null) {
			if (selected instanceof DText) {
				DText dText = (DText) selected;
				dText.setFontName(font);
				repaint();
			}
		}
	}

	public TableModel getTableModel() {
		return dShapeTableModel;
	}

	public void moveFront(DShape selected) {
		shapes.remove(selected);
		shapes.add(shapes.size(), selected);
		repaint();
		dShapeTableModel.fireTableDataChanged();

	}

	public void moveBack(DShape selected) {
		shapes.remove(selected);
		shapes.add(0, selected);
		repaint();
		dShapeTableModel.fireTableDataChanged();

	}
	/**
	 * save into xml file
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void save(File file) throws FileNotFoundException {
		setSelected(null);
		selectedKnobs.clear();
		XMLEncoder xmlOut = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
		DShapeModel[] models = new DShapeModel[getShapes().size()];
		for (int i = 0; i < getShapes().size(); i++) {
			models[i] = getShapes().get(i).getdShapeModel();
		}

		xmlOut.writeObject(models);
		xmlOut.flush();
		xmlOut.close();

	}
	/**
	 * open xml file
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void open(File file) throws FileNotFoundException {
		XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		DShapeModel[] models = (DShapeModel[]) xmlIn.readObject();
		clear();
		for (DShapeModel dShapeModel : models) {
			addShape(dShapeModel);
		}
		dShapeTableModel.fireTableDataChanged();
		setSelected(null);
		xmlIn.close();

	}
	/**
	 * clear shaps, selectedKnobs and update dShapeModel
	 */
	private void clear() {
		shapes.clear();
		selectedKnobs.clear();
		dShapeTableModel.fireTableDataChanged();
		repaint();
	}
	/**
	 * save PNG image
	 * @param file
	 */
	public void saveImage(File file) {
		selected = null;
		selectedKnobs.clear();

		repaint();
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		paint(image.getGraphics());

		try {
			javax.imageio.ImageIO.write(image, "PNG", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}