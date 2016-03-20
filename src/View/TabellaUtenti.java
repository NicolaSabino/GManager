package View;

import Model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Rappresentazione tabella utenti
 */
public class TabellaUtenti {
    private JPanel pannelloPrincipale;
    private JTable ElencoUtenti;

    public void setModelTabella(ArrayList<Utente> elenco){
        String col[] = {"Matricola","Nome","Cognome", "Ruolo","Telefono", "Mail"};

        CustomTable tableModel = new CustomTable(col, 0);

        //trasformo gli incarichi per generare il modello per la tabellsa
        for(Utente appoggio:elenco) {

            String matricola    = appoggio.getMatricola();
            String nome         = appoggio.getNome();
            String cognome      = appoggio.getCognome();
            String ruolo        = appoggio.getRuolo();
            String telefono     = appoggio.getTelefono();
            String mail         = appoggio.getMail();

            Object[] data = {matricola, nome, cognome, ruolo, telefono,mail};

            tableModel.addRow(data);
        }

        ElencoUtenti.setModel(tableModel);

    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public JTable getElencoUtenti() {
        return ElencoUtenti;
    }
}
