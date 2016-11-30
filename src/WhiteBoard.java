import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;



public class WhiteBoard extends JFrame implements ModelListener {

	
	private Canvas canvas;
	
	
	 public WhiteBoard() {
		 	
	        showWhiteBoardGUI();
	    }
	    
	   
	 public Canvas getCanvas() {
		return this.canvas;
	}
	 
	private void showWhiteBoardGUI() {
		//whole whiteboard gui + canvas
			canvas = new Canvas();
			
			ControlGui controlGui = this.new ControlGui(canvas.getTableModel());
	
			
			JFrame frame = new JFrame("WhiteBoard");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.add(canvas, BorderLayout.CENTER);
			frame.add(controlGui, BorderLayout.WEST);
			frame.setVisible(true);
			frame.pack();
	}





	public static void main(String[] args) {
		WhiteBoard whiteBoard = new WhiteBoard();

	}


	class ControlGui extends JPanel {
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
		//private Canvas canvas;
		private JTable table;

		public ControlGui(TableModel model) {
			
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
		 * add shape with random x,y width and hight
		 * use addShape(dShapemodel) from canvas
		 * @param dShapemodel 
		 */
		private void addShape(DShapeModel dShapemodel)
		{
			
			Random ran = new Random();
			dShapemodel.setX(20);
			dShapemodel.setY(20);
			dShapemodel.setWidth(ran.nextInt(100));
			dShapemodel.setHeight(ran.nextInt(100));
			canvas.addShape(dShapemodel);
		}

		

		
	}


	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		
	}
	
	 
}


