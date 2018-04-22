package Affichage_graphique;
import javax.swing.table.AbstractTableModel;

/**
 * Tableau pour les messages
 * @author Etonam et Dan
 *
 */
public class FenetreMessage extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Message message = new Message();

	private final String[] entetes = { " INTERFACE DE L'APPLICATION " };

	public FenetreMessage() {
		super();
	}

	public Message getMessage() {
		return message;
	}

	public int getRowCount() {
		return message.getType1().size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return message.getType1().get(rowIndex);

		default:
			return null; 
		}
	}

	public void addLigne(String ligne) {
		message.getType1().add(ligne);

		fireTableRowsInserted(message.getType1().size() - 1, message.getType1()
				.size() - 1);
	}

	public void print(String ligne) {
		message.setMessagePrint(message.getMessagePrint() + ligne);
	}

	public void clearLigne(int rowIndex) {
		message.getType1().remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}
}