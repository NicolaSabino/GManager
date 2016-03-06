package View;

import Model.Ordine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by nicola on 06/03/16.
 */
public class TabellaOrdini {
    private JPanel pannelloPrincipale;
    private JTable tabella;
    private JScrollPane scroll;

    public void setModelTabella(ArrayList<Ordine> elenco) {
        String col[] = {"Id","Descrizione","Quantità","Data","Attività","Approvazione",};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        //trasformo gli incarichi per generare il modello per la tabellsa
        for(Ordine appoggio:elenco) {

            int id              = appoggio.getId();
            String descrizione  = appoggio.getDescrizione();
            int quantita        = appoggio.getQuantita();
            String data         = appoggio.getDataOrdine();
            int attivita        = appoggio.getAttivita();
            boolean approvazione= appoggio.isApprovazione();

            Object[] objects = {id,descrizione,quantita,data,attivita,approvazione};

            tableModel.addRow(objects);
        }

        tabella.setModel(tableModel);
        tabella.setEnabled(false);

    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }
}
