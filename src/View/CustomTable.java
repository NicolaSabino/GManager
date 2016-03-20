package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classe tabelle custom
 */
public class CustomTable extends DefaultTableModel{

    public CustomTable(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
}