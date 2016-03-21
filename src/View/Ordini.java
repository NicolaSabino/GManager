package View;

import Model.Ordine;
import Model.Utente;

import javax.swing.*;
import java.awt.*;

/**
 * Created by edoardo on 06/03/16.
 */
public class Ordini extends JPanel{

    private JButton aggiungiOrdineButton;
    private JPanel pannelloPrincipale;
    private JTextField campoId;
    private JTextField campoDescrizione;
    private JSpinner spinnerQuantità;
    private JLabel AttivitaLabel;
    private JTextField campoAttivita;
    private JTextField fieldPrezzo;
    private JScrollPane scrollOrdini;

    public Ordini(){
        setVisible(true);
        StaticMethod.textFieldLimitLength(campoDescrizione,60);
        StaticMethod.textFieldLimitOnlyDouble(fieldPrezzo);
        StaticMethod.textFieldLimitOnlyInt(campoAttivita,4);
    }






    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public void setPannelloPrincipale(JPanel pannelloPrincipale) {
        this.pannelloPrincipale = pannelloPrincipale;
    }

    public Ordine getModifiche(Utente utilizzatore){

        Ordine appoggio= new Ordine();
        appoggio.setMatricola(utilizzatore.getMatricola());             //matricola
        appoggio.setQuantita(Integer.parseInt(spinnerQuantità.getValue().toString()));     //Quantità
        appoggio.setPrezzo((Float.parseFloat(fieldPrezzo.getText())*appoggio.getQuantita())); //Prezzo
        appoggio.setDescrizione(campoDescrizione.getText());            //Descrizione
        appoggio.setAttivita(Integer.parseInt(campoAttivita.getText()));       //Attività

        return appoggio;
    }

    public void clean(){
        campoDescrizione.setText("");
        campoAttivita.setText("");
        spinnerQuantità.setValue(0);
        fieldPrezzo.setText("");
    }

    public void setScrollOrdini(Component scrollOrdini) {
        this.scrollOrdini.setViewportView(scrollOrdini);
    }

    public JButton getAggiungiOrdineButton() {
        return aggiungiOrdineButton;
    }

    public JTextField getCampoId() {
        return campoId;
    }

    public JTextField getCampoDescrizione() {
        return campoDescrizione;
    }

    public JSpinner getSpinnerQuantità() {
        return spinnerQuantità;
    }

    public JLabel getAttivitaLabel() {
        return AttivitaLabel;
    }

    public JTextField getCampoAttivita() {
        return campoAttivita;
    }

    public JTextField getFieldPrezzo() {
        return fieldPrezzo;
    }
}
