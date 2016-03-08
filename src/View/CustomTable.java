package View;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by nicola on 08/03/16.
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
