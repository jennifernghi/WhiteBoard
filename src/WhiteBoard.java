
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class WhiteBoard extends JFrame implements ModelListener {
	public static final String NORMAL = "normal";
	public static final String SERVER = "server";
	public static final String CLIENT = "client";
	public static final String ADD = "add";
	public static final String REMOVE = "remove";
	public static final String FRONT = "front";
	public static final String BACK = "back";
	public static final String CHANGE = "change";
	public static final String IP = "127.0.0.1";
	public static final int PORT = 32122;
	private int defaultId = 0;
	// List of object streams to which we send data
	private List<ObjectOutputStream> outputs = new ArrayList<ObjectOutputStream>();
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
	private File file;
	private String status;

	public WhiteBoard() {
		this.status = NORMAL;
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
		JLabel label = new JLabel("Status: ");
		networkStatus = new JLabel(status);

		serverStartButton = new JButton("Start Server");
		serverStartButton.addActionListener(e -> serverStart());
		clientStartButton = new JButton("Start Client");
		clientStartButton.addActionListener(e -> clientStart());

		netWorkBox.add(serverStartButton);
		netWorkBox.add(clientStartButton);
		netWorkBox.add(label);
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
		if (status.equals(NORMAL) || status.equals(SERVER)) {
			if (canvas.getSelected() == null) {
				return;
			} else {
				DShapeModel shapeModel = canvas.getSelected().getdShapeModel();

				Color newColor = JColorChooser.showDialog(colorChooser, "Color Picker", shapeModel.getColor());
				shapeModel.setColor(newColor);
			}

		} else {
			JOptionPane.showMessageDialog(this, "Unavailable! Client is read-only!");
		}
	}

	/**
	 * handle add text
	 */
	private void addText() {
		if (status.equals(NORMAL) || status.equals(SERVER)) {
			DShapeModel text = new DTextModel();
			addShape(text);
		} else {
			JOptionPane.showMessageDialog(this, "Unavailable! Client is read-only!");
		}
	}

	/**
	 * handle add line
	 */
	private void addLine() {
		if (status.equals(NORMAL) || status.equals(SERVER)) {
			DShapeModel line = new DLineModel();
			addShape(line);
		} else {
			JOptionPane.showMessageDialog(this, "Unavailable! Client is read-only!");
		}

	}

	/**
	 * handle add oval
	 */
	private void addOval() {
		if (status.equals(NORMAL) || status.equals(SERVER)) {
			DShapeModel oval = new DOvalModel();
			addShape(oval);
		} else {
			JOptionPane.showMessageDialog(this, "Unavailable! Client is read-only!");
		}
	}

	/**
	 * handle add rect
	 */
	private void addRect() {
		if (status.equals(NORMAL) || status.equals(SERVER)) {
			DShapeModel rectangle = new DRectModel();
			addShape(rectangle);
		} else {
			JOptionPane.showMessageDialog(this, "Unavailable! Client is read-only!");
		}
	}

	/**
	 * handle saveImage
	 */
	private void saveImage() {
		String input = JOptionPane.showInputDialog("PNG File Name: ");
		if (input == null) {
			// handle null pointer exception
			return;
		} else {
			file = new File(input + ".png");
			if (file != null) {
				canvas.saveImage(file);

			} else {
				return;
			}
		}
	}

	/**
	 * handle open
	 */
	private void open() {
		String input = JOptionPane.showInputDialog("File Name: ");
		if (input == null) {
			// handle null pointer exception
			return;
		} else {
			if (status.equals(NORMAL)) {
				file = new File(input);

				if (file != null) {
					try {
						canvas.open(file);
						registerViewListener();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(this, "File Not Found");
					}
				} else {
					return;// handle nullpointerexception
				}
			} else {
				JOptionPane.showMessageDialog(this, "this feature is unavailable in " + status + " mode");
			}
		}
	}

	/**
	 * handle save
	 */
	private void save() {
		String input = JOptionPane.showInputDialog("File Name: ");
		if (input == null) {
			// handle null pointer exception
			return;
		} else {
			file = new File(input);
			if (file != null)
				try {
					canvas.save(file);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "No file entered");
				}
		}

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
		if (canvas.getSelected() == null) {
			return;
		} else {
			canvas.moveBack(canvas.getSelected());
		}
	}

	/**
	 * handle move Front
	 */
	private void moveFront() {
		if (canvas.getSelected() == null) {
			return;
		} else {
			canvas.moveFront(canvas.getSelected());
		}
	}

	/**
	 * handle client-side networking
	 */
	private void clientStart() {
		if (status.equals(SERVER)) {
			int input = JOptionPane.showConfirmDialog(this,
					"program is on server mode, can't switch to client. Do you want to quit?");
			if (input == JOptionPane.YES_OPTION) {
				System.exit(0);
			}

		} else if (status.equals(CLIENT)) {
			JOptionPane.showMessageDialog(this, "Program already connect to " + IP + ":" + PORT);
		} else {
			String address = JOptionPane.showInputDialog("Connect to", IP + ":" + PORT);
			System.out.println(address);
			setStatus(CLIENT);
			networkStatus.setText(status);
			ClientHandler client = new ClientHandler(IP, PORT);
			client.start();
		}
	}

	/**
	 * handle server-side networking
	 */
	private void serverStart() {
		if (status.equals(CLIENT)) {
			int input = JOptionPane.showConfirmDialog(this,
					"program is on client mode, can't switch to server. Do you want to quit?");
			if (input == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				return;
			}
		} else if (status.equals(SERVER)) {
			JOptionPane.showMessageDialog(this, "You are already in server mode");
		} else {
			String port = JOptionPane.showInputDialog("Port Number:", PORT);
			if (Integer.parseInt(port.trim()) < 0 || Integer.parseInt(port.trim()) > 65535) {
				JOptionPane.showMessageDialog(this, "available port: 0 - 65535");
			} else {
				setStatus(SERVER);
				networkStatus.setText(status);
				ServerAccepter server = new ServerAccepter(PORT);
				server.start();
			}
		}
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
		// sent to client if server/client available
		sentToClient(ADD, dShapemodel);
		// Whiteboard listens to changes of shape
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
		dShapemodel.setID(++defaultId);
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
		new WhiteBoard();
		new WhiteBoard();

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

			// update textField and JCombobox with selected text shape' text and
			// font name
			setTextControlGUI(dTextModel.getText(), dTextModel.getFontName());

		} else {

			// disable textField & JCombobox
			enableTextControlGUI(false);

		}
	}

	/**
	 * after open a file, lets whiteboard listens to changes of shapes
	 */
	private void registerViewListener() {
		List<DShape> shapes = canvas.getShapes();
		for (DShape shape : shapes) {
			shape.getdShapeModel().addModelListener(this);
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Server thread accepts incoming client connections
	class ServerAccepter extends Thread {
		private int port;

		ServerAccepter(int port) {
			this.port = port;
		}

		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				while (true) {
					Socket toClient = null;
					// this blocks, waiting for a Socket to the client
					toClient = serverSocket.accept();

					// Get an output stream to the client, and add it to
					// the list of outputs
					// (our server only uses the output stream of the
					// connection)
					addOutput(new ObjectOutputStream(toClient.getOutputStream()));

				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	class ClientHandler extends Thread {
		private String name;
		private int port;

		ClientHandler(String name, int port) {
			this.name = name;
			this.port = port;
		}

		public void run() {
			try {
				// make connection to the server name/port
				Socket toServer = new Socket(name, port);
				// get input stream to READ from server and wrap in object input
				// stream
				ObjectInputStream in = new ObjectInputStream(toServer.getInputStream());

				// we could do this if we wanted to write to server in addition
				// to reading
				// out = new ObjectOutputStream(toServer.getOutputStream());
				while (true) {
					// Get the xml string, decode to a Message object.
					// Blocks in readObject(), waiting for server to send
					// something.
					String command = (String) in.readObject();
					String dShapeModel = (String) in.readObject();
					XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(dShapeModel.getBytes()));
					DShapeModel model = (DShapeModel) decoder.readObject();
					decoder.close();

					if (command.equals(ADD)) {
						canvas.addShape(model);
						canvas.setSelected(null);
					}

				}
			} catch (Exception ex) { // IOException and ClassNotFoundException
				ex.printStackTrace();
			}

		}
	}

	private void sentToClient(String command, DShapeModel dShapeModel) {
		for (ObjectOutputStream out : outputs) {
			try {
				out.writeObject(command);
				OutputStream memStream = new ByteArrayOutputStream();
				XMLEncoder encoder = new XMLEncoder(memStream);
				encoder.writeObject(dShapeModel);
				encoder.close();
				String xmlString = memStream.toString();
				out.writeObject(xmlString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private synchronized void addOutput(ObjectOutputStream objectOutputStream) {
		outputs.add(objectOutputStream);
	}
}