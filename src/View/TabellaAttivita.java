package View;

import Model.Attivita;
import Model.Ordine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Created by nicola on 17/02/16.
 */
public class TabellaAttivita {
    private JPanel pannelloPrincipale;
    private JTable Tabella;
    private JScrollPane scroll;

    /**
     * Genera dinamicamente la tabella mediante l'elenco delle attivita da mostrare
     * @param elenco
     */
    public void setModelTabella(ArrayList<Attivita> elenco) {
        String col[] = {"Id","nome Sequenza","Precedenza", "Descrizione", "Data Inizio", "Data Fine Prevista", "Data Fine", "€" };

        CustomTable tableModel = new CustomTable(col, 0);

        //trasformo gli incarichi per generare il modello per la tabellsa
        for(Attivita appoggio:elenco) {

            int id                  = appoggio.getId();
            String nomesequenza     = appoggio.getNomesequenza();
            int precedenza          = appoggio.getPrecedenza();
            String descrizione      = appoggio.getDescrizione();
            String datainizio       = appoggio.getDatainizio();
            String datafneprevista  = appoggio.getDatafineprevista();
            String datafine         = appoggio.getDatafine();
            double costo            = appoggio.getCosto();

            Object[] data = {id, nomesequenza, precedenza, descrizione, datainizio, datafneprevista, datafine, costo};

            tableModel.addRow(data);
        }

        Tabella.setModel(tableModel);



    }




    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public JTable getTabella() {
        return Tabella;
    }
}
