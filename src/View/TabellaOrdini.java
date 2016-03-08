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
    private String Utente;

    public TabellaOrdini(String matricolautente){
        this.Utente=matricolautente;
    }

    public void setModelTabella(ArrayList<Ordine> elenco) {
        String col[] = {"Descrizione","Quantità","Prezzo","Data","Attività","Approvazione",};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        //trasformo gli incarichi per generare il modello per la tabellsa
        for(Ordine appoggio:elenco) {

            String descrizione;
            if(appoggio.getMatricola().compareTo(this.Utente)==0){
                descrizione = "<html><p font color=" + "blue>" + appoggio.getDescrizione() + "</p></html>";
            }else{
                descrizione = appoggio.getDescrizione();
            }
            int quantita = appoggio.getQuantita();
            float prezzo = appoggio.getPrezzo();
            String data = appoggio.getDataOrdine();
            int attivita = appoggio.getAttivita();
            String approvazione;


            if (appoggio.getApprovazione().compareTo("Approvato")==0) {
                approvazione="<html><font color=green>Approvato</font></html>";
            } else if(appoggio.getApprovazione().compareTo("Da Approvare")==0){
                approvazione="<html><font color=orange>Da Approvare</font></html>";
            }else if(appoggio.getApprovazione().compareTo("Non Approvato")==0){
                approvazione="<html><font color=red>Non Approvato</font></html>";
            }else{
                approvazione="null";
            }



            Object[] objects = {descrizione,quantita,prezzo,data,attivita,approvazione};

            tableModel.addRow(objects);
        }

        tabella.setModel(tableModel);
        tabella.setEnabled(false);


    }


    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }


}
