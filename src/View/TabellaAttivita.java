package View;

import Model.Attivita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by nicola on 17/02/16.
 */
public class TabellaAttivita {
    private JPanel pannelloPrincipale;

    public TabellaAttivita(ArrayList<Attivita> elenco) {
        DefaultTableModel m = new DefaultTableModel();
        String[] intestazione= {"Id","Nome Sequenza" , "Precedenza " , "Descrizione" , "data Inizio","data Fine prevista","data Fine", "costo"};

        for(Attivita appoggio:elenco){

        }
        JTable= new JTable(elenco,intestazione);
    }
}
