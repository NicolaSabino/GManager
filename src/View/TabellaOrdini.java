package View;

import Model.Ordine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by nicola on 06/03/16.
 */
public class TabellaOrdini {
    private JPanel pannelloPrincipale;
    private JTable tabella;
    private JScrollPane scroll;

    public void setModelTabella(ArrayList<Ordine> elenco) {
        String col[] = {"Id","Descrizione","Quantità","Prezzo","Data","Attività","Approvazione",};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        //trasformo gli incarichi per generare il modello per la tabellsa
        for(Ordine appoggio:elenco) {

            int id = appoggio.getId();
            String descrizione = appoggio.getDescrizione();
            int quantita = appoggio.getQuantita();
            float prezzo = appoggio.getPrezzo();
            String data = appoggio.getDataOrdine();
            int attivita = appoggio.getAttivita();
            String approvazione;

            //todo correggere
            if (appoggio.getApprovazione()=="Approvato") {
                approvazione="<html><font color=green>Approvato</font></html>";
            } else if(appoggio.getApprovazione()=="Da Approvare"){
                approvazione="<html><font color=orange>Da Approvare</font></html>";
            }else if(appoggio.getApprovazione()=="Non Approvato"){
                approvazione="<html><font color=red>Da Approvare</font></html>";
            }else{
                approvazione="null";
            }



            Object[] objects = {id,descrizione,quantita,prezzo,data,attivita,approvazione};

            tableModel.addRow(objects);
        }

        tabella.setModel(tableModel);
        tabella.setEnabled(false);


    }


    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }


}
