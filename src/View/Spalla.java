package View;

import Model.Utente;

import javax.swing.*;

/**
 * Created by nicola on 16/02/16.
 */
public class Spalla {
    private JPanel pannelloPrincipale;
    private JButton homeButton;
    private JButton impostazioniButton;
    private JButton ricercaButton;
    private JButton ordiniButton;
    private JButton gestisciButton;
    private JButton appuntamentiButton;
    private JPanel InfoUtente;
    private JLabel Utente;
    private JLabel nomeUtente;
    private JLabel utenteMatricola;
    private JLabel Ruolo;
    private JLabel ruoloUtente;
    private JLabel Matricola;

    /**
     * Metodo che nasconde l'interfaccia della spalla all'utente
     */
    public void loginMode(){

        //non posso semplicemente settare invisibile il
        //panello principale altrimenti vedrei tutto bianco

        homeButton.setVisible(false);
        impostazioniButton.setVisible(false);
        ricercaButton.setVisible(false);
        ordiniButton.setVisible(false);
        gestisciButton.setVisible(false);
        appuntamentiButton.setVisible(false);
        InfoUtente.setVisible(false);


    }

    /**
     * Mostra solo i comandi disponiblili per un utente con i minimi privilegi
     * @param u
     */
    public void usMode(Utente u){
        homeButton.setVisible(true);
        impostazioniButton.setVisible(true);
        ricercaButton.setVisible(true);
        ordiniButton.setVisible(true);

        gestisciButton.setVisible(false);
        appuntamentiButton.setVisible(false);

        //informazioni dell'utente
        InfoUtente.setVisible(true);
        nomeUtente.setText(u.getNome() + " " + u.getCognome());
        utenteMatricola.setText(u.getMatricola());
        ruoloUtente.setText(u.getRuolo());




    }

    /**
     * Mostra i comandi per un utente con i massimi privilegi
     * @param u
     */
    public void proMode(Utente u){
        homeButton.setVisible(true);
        impostazioniButton.setVisible(true);
        ricercaButton.setVisible(true);
        ordiniButton.setVisible(true);

        gestisciButton.setVisible(true);
        appuntamentiButton.setVisible(true);


        //informazioni dell'utente
        InfoUtente.setVisible(true);
        nomeUtente.setText(u.getNome() + " " + u.getCognome());
        utenteMatricola.setText(u.getMatricola());
        ruoloUtente.setText(u.getRuolo());


    }



    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }
}
