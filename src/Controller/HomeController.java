package Controller;

import Model.Attivita;
import Model.MessaggioBroadcast;
import Model.Sequenza;
import Model.Utente;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Controller della schermata home
 */
public class HomeController {
    RootFrame rootFrame;
    Utente utilizzatore;
    ListaNotifiche notifiche;
    TabellaAttivita tabellaAttivita;
    MessaggiController messaggiController;
    Home home;

    /**
     * Costruttore di home controller
     *
     * @param r frame principale dove settare le viste
     * @param mat matricola dell'utilizzatore
     */
    public HomeController(RootFrame r, String mat) {
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;
        this.home=new Home();

        r.setMainScrollPane(home.getPannelloPrincipale());

        aggiornaProgressBar();

        aggiornaTabellaAttivita();

        //mostro a video gli appuntamenti
        TabellaIncontri t2= new TabellaIncontri();
        t2.setModelTabella(utilizzatore.getAppuntamenti());
        home.setScrollEventi(t2.getPannelloPrincipale());

        //mostro l'elenco dei messaggi-notifiche
        this.messaggiController= new MessaggiController(this.utilizzatore,this.home);
        this.notifiche=messaggiController.getListaNotifiche();
        home.setScrollNotifiche(notifiche.getPannelloPrincipale());

    }

    /**
     * Metodo che aggiorna la progress bar della percentuale di completamento della sequenza
     */
    private void aggiornaProgressBar(){

        Sequenza sequenzaDiAppartenenza = new Sequenza(utilizzatore.selezionaSequenzaUtente());
        home.setSequenza(utilizzatore.selezionaSequenzaUtente());
        home.setProgressBarCompl((int) sequenzaDiAppartenenza.getPercentComplSeq());
    }

    /**
     * listener doppio click sulla tabella attività
     */
    private void listenerSelezionaAttivita(){
        this.tabellaAttivita.getTabella().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // doppio click
                    approvaFineAttivita(tabellaAttivita.getTabella().getSelectedRow());
                }
            }
        });
    }

    /**
     * Metodo che imposta la data corrente come data finale dell'attività
     *
     * @param riga
     */
    protected void approvaFineAttivita(int riga){
        JTable tabellaAttivitaHome = tabellaAttivita.getTabella();
        Attivita attivita = new Attivita((Integer) tabellaAttivitaHome.getValueAt(riga, 0));

        int id = attivita.getId();

        int response=JOptionPane.showConfirmDialog(null,"hai ultimato  l' attività "+ id + "?"
                ,"apporvazione ultimazione attività di " + id,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION){

            int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
            int meseCorrente = Calendar.getInstance().get(Calendar.MONTH) +1; //il conteggio dei mesi parte da 0
            int giornoCorrente = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            //se l'utente approva l'ultimazione dell'attività
            attivita.updateIntoSQL("datafine", annoCorrente + "-" + meseCorrente + "-"
                    + giornoCorrente);

            //riaggiorno la tabella nella home
           aggiornaTabellaAttivita();

            //aggiorno la progress bar
            aggiornaProgressBar();
        }


    }

    /**
     * Metodo che aggiorna la tabella degli appuntamenti nella home
     *
     */
    public void aggiornoAppuntamenti(){
        utilizzatore.popolaAppuntamenti();
        TabellaIncontri t2= new TabellaIncontri();
        t2.setModelTabella(utilizzatore.getAppuntamenti());
        home.setScrollEventi(t2.getPannelloPrincipale());
    }

    /**
     * Metodo che aggiorna la tabella delle attività
     */
    private void aggiornaTabellaAttivita(){
        this.tabellaAttivita= new TabellaAttivita();
        utilizzatore.popolaIncarichi();//aggiorno gli incarichi
        tabellaAttivita.setModelTabella(utilizzatore.getIncarichi());
        home.setScrollCompiti(tabellaAttivita.getPannelloPrincipale());
        //aggiungo il listener di selezione
        listenerSelezionaAttivita();
    }

    public void apriHome(){
        rootFrame.setMainScrollPane(home.getPannelloPrincipale());
    }


    //getter

    public MessaggiController getMessaggiController() {
        return messaggiController;
    }

}