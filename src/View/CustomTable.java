package View;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classe tabelle custom
 */
public class CustomTable extends DefaultTableModel{

    public CustomTable(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

//todo eliminare la possibilità di spostare le colonne della tabella altrimenti genera degli errori con i mouse listner su gestisci