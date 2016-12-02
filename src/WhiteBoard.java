
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class WhiteBoard extends JFrame implements ModelListener {

	private Canvas canvas;

	private JButton serverStartButton, clientStartButton, saveButton, openButton, saveImageButton, addRectButton,
			addOvalButton, addLineButton, addTextButton, setColorButton, moveFrontButton, moveBackButton,
			removeShapeButton;
	private JLabel networkStatus;
	private JColorChooser colorChooser = new JColorChooser();
	private JComboBox<String> fontChooser;
	private JTextField textField;
	// private Canvas canvas;
	private JTable table;

	public WhiteBoard() {

		showWhiteBoardGUI();
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	private void showWhiteBoardGUI() {
		// whole whiteboard gui + canvas
		canvas = new Canvas();

		JFrame frame = new JFrame("WhiteBoard");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.add(showControlGUI(), BorderLayout.WEST);
		frame.setVisible(true);
		frame.pack();
	}

	private Box showControlGUI() {

		Box controlBox = Box.createVerticalBox();

		Box networkBox = showNetWorkBox();
		Box fileBox = showFileBox();
		Box addShapesBox = showAddShapesBox();
		Box setColorBox = showSetColorBox();
		Box editTextBox = showEditTextBox();
		Box editShapeBox = showEditShapeBox();
		JScrollPane scrollPane = showTableGUI();

		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(networkBox);
		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(fileBox);
		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(addShapesBox);
		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(setColorBox);
		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(editTextBox);
		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(editShapeBox);
		controlBox.add(Box.createVerticalStrut(10));
		controlBox.add(scrollPane);
		// align all components to the left
		for (Component comp : controlBox.getComponents()) {
			((JComponent) comp).setAlignmentX(LEFT_ALIGNMENT);
		}

		return controlBox;
	}

	private Box showSetColorBox() {
		Box setColorBox = Box.createHorizontalBox();
		setColorButton = new JButton("Set Color");

		setColorButton.addActionListener(e -> setColor());
		setColorBox.add(setColorButton);
		return setColorBox;
	}

	private Box showNetWorkBox() {

		Box netWorkBox = Box.createHorizontalBox();

		networkStatus = new JLabel("Status:");

		serverStartButton = new JButton("Start Server");
		serverStartButton.addActionListener(e -> serverStart());
		clientStartButton = new JButton("Start Client");
		clientStartButton.addActionListener(e -> clientStart());

		netWorkBox.add(serverStartButton);
		netWorkBox.add(clientStartButton);
		netWorkBox.add(networkStatus);
		return netWorkBox;
	}

	private Box showFileBox() {
		Box fileBox = Box.createHorizontalBox();
		saveButton = new JButton("Save");
		openButton = new JButton("Open");
		saveImageButton = new JButton("Save Image");

		saveButton.addActionListener(e -> save());
		openButton.addActionListener(e -> open());
		saveImageButton.addActionListener(e -> saveImage());
		fileBox.add(saveButton);
		fileBox.add(openButton);
		fileBox.add(saveImageButton);
		return fileBox;
	}

	private Box showAddShapesBox() {
		Box addShapeBox = Box.createHorizontalBox();
		addRectButton = new JButton("Rect");
		addOvalButton = new JButton("Oval");
		addLineButton = new JButton("Line");
		addTextButton = new JButton("Text");

		addRectButton.addActionListener(e -> addRect());
		addOvalButton.addActionListener(e -> addOval());
		addLineButton.addActionListener(e -> addLine());
		addTextButton.addActionListener(e -> addText());

		addShapeBox.add(addRectButton);
		addShapeBox.add(addOvalButton);
		addShapeBox.add(addLineButton);
		addShapeBox.add(addTextButton);
		return addShapeBox;
	}

	private Box showEditTextBox() {
		Box editTextBox = Box.createHorizontalBox();
		textField = new JTextField("");
		textField.setEnabled(false);
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		fontChooser = new JComboBox<String>(fonts);
		fontChooser.setEnabled(false);
		fontChooser.addActionListener(e -> chooseFont());

		/**
		 * handle changes in the textfield
		 */
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// repaint text on canvas
				repaintText(textField.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// repaint text on canvas
				repaintText(textField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// repaint text on canvas
				repaintText(textField.getText());
			}
		});
		editTextBox.add(textField);
		editTextBox.add(fontChooser);

		return editTextBox;

	}

	/**
	 * choose font from JComboBox
	 */
	private void chooseFont() {
		String fontName = (String) fontChooser.getSelectedItem();

		canvas.setFontName(fontName);
	}

	private JScrollPane showTableGUI() {

		table = new JTable(canvas.getTableModel());

		JScrollPane scrollPane = new JScrollPane(table);

		return scrollPane;
	}

	private Box showEditShapeBox() {
		Box editShapeBox = Box.createHorizontalBox();

		moveFrontButton = new JButton("Move To Front");
		moveBackButton = new JButton("Move To Back");
		removeShapeButton = new JButton("Remove Shape");

		moveFrontButton.addActionListener(e -> moveFront());
		moveBackButton.addActionListener(e -> moveBack());
		removeShapeButton.addActionListener(e -> removeShape());

		editShapeBox.add(moveFrontButton);
		editShapeBox.add(moveBackButton);
		editShapeBox.add(removeShapeButton);
		return editShapeBox;
	}

	/**
	 * handle setColor
	 */
	private void setColor() {
		if(canvas.getSelected()==null){
			return;
		}else{
			DShapeModel shapeModel = canvas.getSelected().getdShapeModel();
	
			Color newColor = JColorChooser.showDialog(colorChooser, "Color Picker", shapeModel.getColor());
			shapeModel.setColor(newColor);
		}

	}

	/**
	 * handle add text
	 */
	private void addText() {
		DShapeModel text = new DTextModel();
		addShape(text);
	}

	/**
	 * handle add line
	 */
	private void addLine() {
		DShapeModel line = new DLineModel();
		addShape(line);

	}

	/**
	 * handle add oval
	 */
	private void addOval() {
		DShapeModel oval = new DOvalModel();
		addShape(oval);
	}

	/**
	 * handle add rect
	 */
	private void addRect() {
		DShapeModel rectangle = new DRectModel();
		addShape(rectangle);
	}

	/**
	 * handle saveImage
	 */
	private void saveImage() {

	}

	/**
	 * handle open
	 */
	private void open() {

	}

	/**
	 * handle save
	 */
	private void save() {

	}

	/**
	 * handle remove shapes
	 */
	private void removeShape() {
		canvas.deleteShape();
	}

	/**
	 * handle move Back
	 */
	private void moveBack() {
		if(canvas.getSelected()==null){
			return;
		}else{
			canvas.moveBack(canvas.getSelected());
		}
	}

	/**
	 * handle move Front
	 */
	private void moveFront() {
		if(canvas.getSelected()==null){
			return;
		}else{
			canvas.moveFront(canvas.getSelected());
		}
	}

	/**
	 * handle client-side networking
	 */
	private void clientStart() {

	}

	/**
	 * handle server-side networking
	 */
	private void serverStart() {

	}

	/**
	 * add shape with random x,y width and height use addShape(dShapemodel) from
	 * canvas
	 * 
	 * @param dShapemodel
	 */
	private void addShape(DShapeModel dShapemodel) {
		// initial shape
		defaultShape(dShapemodel);

		// register the view WhiteBoard to listener
		dShapemodel.addModelListener(this);

		// create correct shape using addShape() on canvas
		canvas.addShape(dShapemodel);
	}

	/**
	 * default shape
	 * 
	 * @param dShapemodel
	 */
	private void defaultShape(DShapeModel dShapemodel) {
		dShapemodel.setX(10);
		dShapemodel.setY(10);
		dShapemodel.setWidth(20);
		dShapemodel.setHeight(20);

	}

	/**
	 * repaint dText
	 * 
	 * @param text
	 */
	private void repaintText(String text) {
		canvas.repaintText(text);
	}

	/**
	 * if true, enable textField and JCombobox if false, disable textField and
	 * JCombobox
	 * 
	 * @param state
	 */
	public void enableTextControlGUI(boolean state) {
		if (state == true) {
			textField.setEnabled(true);
			fontChooser.setEnabled(true);
		} else {
			textField.setText("");
			textField.setEnabled(false);
			fontChooser.setEnabled(false);
		}

	}

	/**
	 * update textfield and JCombobox with selected text' text and font name
	 * 
	 * @param text
	 * @param fontName
	 */
	public void setTextControlGUI(String text, String fontName) {
		// TODO Auto-generated method stub
		textField.setText(text);
		fontChooser.setSelectedItem(fontName);
	}

	public static void main(String[] args) {
		WhiteBoard whiteBoard = new WhiteBoard();

	}

	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void individualChanged(DShapeModel model) {
		// if selected is a dtextmodel
		if (model instanceof DTextModel) {
			DTextModel dTextModel = (DTextModel) model;
			enableTextControlGUI(true); // enable textField and JCombobox
			
			//update textField and JCombobox with selected text shape' text and font name
			setTextControlGUI(dTextModel.getText(), dTextModel.getFontName());

		} else {
			// disable textField & JCombobox
			enableTextControlGUI(false);
		}
	}

}

