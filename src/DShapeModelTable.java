import javax.swing.table.AbstractTableModel;

public class DShapeModelTable extends AbstractTableModel implements ModelListener {

	private Canvas canvas;
	public static final int COLUMNS = 5;
	public static final int ID_COLUMN = 0;
	public static final int X_COLUMN = 1;
	public static final int Y_COLUMN = 2;
	public static final int WIDTH_COLUMN = 3;
	public static final int HEIGHT_COLUMN = 4;

	public DShapeModelTable() {
		canvas = null;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public int getRowIndex(DShapeModel model){
		for(int i=0; i<canvas.getShapes().size();i++){
			if (canvas.getShapes().get(i).getdShapeModel() == model) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public int getRowCount() {
		if (canvas != null) {
			return canvas.getShapes().size();
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return COLUMNS;
	}

	@Override
	/**
	 * get correct data given row and col
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (canvas != null) {
			DShapeModel dShapeModel = canvas.getShapes().get(rowIndex).getdShapeModel();
			if (columnIndex == X_COLUMN) {
				return dShapeModel.getX();
			} else if (columnIndex == Y_COLUMN) {
				return dShapeModel.getY();
			} else if (columnIndex == WIDTH_COLUMN) {
				return dShapeModel.getWidth();
			} else if (columnIndex == HEIGHT_COLUMN){
				return dShapeModel.getHeight();
			}else{
				return dShapeModel.getID();
			}
		} else {
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		if (column == X_COLUMN) {
			return "x";
		} else if (column == Y_COLUMN) {
			return "y";
		} else if (column == WIDTH_COLUMN) {
			return "width";
		} else if(column == HEIGHT_COLUMN){
			return "height";
		}else{
			return "id";
		}
	}

	@Override
	/**
	 * as model changed call fireTableRowsUpdated on that row
	 */
	public void modelChanged(DShapeModel model) {
		for (int i = 0; i < canvas.getShapes().size(); i++) {
			if (canvas.getShapes().get(i).getdShapeModel() == model) {
				fireTableRowsUpdated(i, i);
			}
		}

	}

	@Override
	public void individualChanged(DShapeModel model) {
		// TODO Auto-generated method stub

	}
	
	

}
