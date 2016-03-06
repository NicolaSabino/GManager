package View;

import Model.Ordine;
import Model.Utente;

import javax.swing.*;
import java.awt.*;

/**
 * Created by edoardo on 06/03/16.
 */
public class Ordini extends JPanel{
    private JTextField campoPrezzo;
    private JTextField campoQuantita;
    private JTextField campoNomePezzo;
    private JButton aggiungiOrdineButton;
    private JPanel pannelloPrincipale;
    private JTextField campoId;
    private JTextField campoDescrizione;
    private JSpinner spinnerQuantità;
    private JLabel AttivitaLabel;
    private JFormattedTextField campoAttivita;
    private JSpinner spinnerPrezzo;
    private JScrollPane scrollOrdini;

    public Ordini(){ setVisible(true);}


    public void cancellaCampi(){
        this.campoPrezzo.setText("");
        this.campoQuantita.setText("");
        this.campoNomePezzo.setText("");
    }

    public JTextField getCampoPrezzo() {
        return campoPrezzo;
    }

    public JTextField getCampoQuantita() {
        return campoQuantita;
    }

    public JTextField getCampoNomePezzo() {
        return campoNomePezzo;
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
        appoggio.setPrezzo((Float) spinnerPrezzo.getValue());           //Prezzo
        appoggio.setQuantita((Integer) spinnerQuantità.getValue());     //Quantità
        appoggio.setDescrizione(campoDescrizione.getText());            //Descrizione
        appoggio.setAttivita((Integer) campoAttivita.getValue());                  //Attività

        return appoggio;
    }

    public void setScrollOrdini(Component scrollOrdini) {
        this.scrollOrdini.setViewportView(scrollOrdini);
    }
}
