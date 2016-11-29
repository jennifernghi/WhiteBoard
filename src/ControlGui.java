import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;

public class ControlGui extends JPanel {
	// JPanel box = new JPanel();
	private JPanel networkPanel = new JPanel();
	private JPanel fileControlPanel = new JPanel();
	private JPanel addShapesPanel = new JPanel();
	private JPanel setColorPanel = new JPanel();
	private JPanel editTextPanel = new JPanel();
	private JPanel editShapePanel = new JPanel();
	private JButton serverStartButton, clientStartButton, saveButton, openButton, saveImageButton, addRectButton,
			addOvalButton, addLineButton, addTextButton, setColorButton, moveFrontButton, moveBackButton,
			removeShapeButton;
	private JLabel networkStatus;
	private JColorChooser colorChooser = new JColorChooser();
	private JComboBox<String> fontChooser;
	private JTextField textField;
	private Canvas canvas;
	private JTable table;

	public ControlGui(Canvas canvas, TableModel model) {
		this.canvas = canvas;
		showControlGUI(model);
	}

	private void showControlGUI(TableModel model) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setAlignmentX(LEFT_ALIGNMENT);

		showNetWorkPanel();
		showFilePanel();
		showAddShapesControl();
		showSetColorGui();
		showEditTextPanel();
		showEditShapePanel();
		showTableGUI(model);

	}

	private void showEditShapePanel() {
		editShapePanel.setLayout(new FlowLayout());
		moveFrontButton = new JButton("Move To Front");
		moveBackButton = new JButton("Move To Back");
		removeShapeButton = new JButton("Remove Shape");

		moveFrontButton.addActionListener(e -> moveFront());
		moveBackButton.addActionListener(e -> moveBack());
		removeShapeButton.addActionListener(e -> removeShape());

		editShapePanel.add(moveFrontButton);
		editShapePanel.add(moveBackButton);
		editShapePanel.add(removeShapeButton);

		this.add(editShapePanel);
	}

	/**
	 * handle remove shapes
	 */
	private void removeShape() {

	}

	/**
	 * handle move Back
	 */
	private void moveBack() {

	}

	/**
	 * handle move Front
	 */
	private void moveFront() {

	}

	private void showEditTextPanel() {

		textField = new JTextField("whiteboard!");
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontChooser = new JComboBox<String>(fonts);
		fontChooser.addActionListener(e -> chooseFont());

		/**
		 * handle changes in the textfield
		 */
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});
		editTextPanel.add(textField);

		editTextPanel.add(fontChooser);
		add(editTextPanel);
	}

	/**
	 * handle choose font
	 */
	private void chooseFont() {

	}

	private void showNetWorkPanel() {

		networkStatus = new JLabel("Status:");

		serverStartButton = new JButton("Start Server");
		serverStartButton.addActionListener(e -> serverStart());
		clientStartButton = new JButton("Start Client");
		clientStartButton.addActionListener(e -> clientStart());
		networkPanel.add(serverStartButton);
		networkPanel.add(clientStartButton);
		networkPanel.add(networkStatus);
		add(networkPanel);

	}

	private void showFilePanel() {

		saveButton = new JButton("Save");
		openButton = new JButton("Open");
		saveImageButton = new JButton("Save Image");

		saveButton.addActionListener(e -> save());
		openButton.addActionListener(e -> open());
		saveImageButton.addActionListener(e -> saveImage());
		fileControlPanel.add(saveButton);
		fileControlPanel.add(openButton);
		fileControlPanel.add(saveImageButton);
		add(fileControlPanel);

	}

	private void showTableGUI(TableModel model) {

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}

	private void showSetColorGui() {

		setColorButton = new JButton("Set Color");

		setColorButton.addActionListener(e -> setColor());
		setColorPanel.add(setColorButton);
		add(setColorPanel);

	}

	private void setColor() {

	}

	private void showAddShapesControl() {

		addRectButton = new JButton("Rect");
		addOvalButton = new JButton("Oval");
		addLineButton = new JButton("Line");
		addTextButton = new JButton("Text");

		addRectButton.addActionListener(e -> addRect());
		addOvalButton.addActionListener(e -> addOval());
		addLineButton.addActionListener(e -> addLine());
		addTextButton.addActionListener(e -> addText());

		addShapesPanel.add(addRectButton);
		addShapesPanel.add(addOvalButton);
		addShapesPanel.add(addLineButton);
		addShapesPanel.add(addTextButton);
		add(addShapesPanel);

	}

	/**
	 * handle add text
	 */
	private void addText() {

	}

	/**
	 * handle add line
	 */
	private void addLine() {
		DLine line = new DLine();

		canvas.addShapeToList(line);
		canvas.updateTable(line);
	}

	/**
	 * handle add oval
	 */
	private void addOval() {
		DOval oval = new DOval();

		canvas.addShapeToList(oval);
		canvas.updateTable(oval);
	}

	/**
	 * handle add rect
	 */
	private void addRect() {
		DRect rect = new DRect();

		canvas.addShapeToList(rect);
		canvas.updateTable(rect);
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
	 * handle client-side networking
	 */
	private void clientStart() {

	}

	/**
	 * handle server-side networking
	 */
	private void serverStart() {

	}
}
