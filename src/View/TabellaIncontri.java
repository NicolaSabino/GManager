package View;

import Model.Incontro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Rappresentazione tabella incontri
 */
public class TabellaIncontri {
    private JPanel pannelloPrincipale;
    private JScrollPane scroll;
    private JLabel incontri;
    private JTable Tabella;

    public void setModelTabella(ArrayList<Incontro> elenco) {
        String col[] = {"Tipo","Data","Ora", "Luogo"};

        CustomTable tableModel = new CustomTable(col, 0);


        for(Incontro appoggio:elenco) {
            String tipo = appoggio.getTipo();
            String Data = appoggio.getData();
            String ora = appoggio.getOra();
            String luogo= appoggio.getLuogo();

            Object[] data = { tipo , Data , ora ,luogo};

            tableModel.addRow(data);
        }

        Tabella.setModel(tableModel);


    }
    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }
}
