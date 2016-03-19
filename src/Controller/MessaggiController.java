package Controller;

import Model.MessaggioBroadcast;
import Model.Utente;
import View.Home;
import View.ListaNotifiche;
import View.NuovoMessaggio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Gestische la schermata dei messaggi
 */
public class MessaggiController {

    private ListaNotifiche listaNotifiche;
    private ArrayList<String> elencoNotifiche;
    private Utente utilizzatore;
    private NuovoMessaggio n;
    private Home h;

    /**
     * Costruttore della schermata dei messaggi nella home
     *
     * @param u
     * @param home
     */
    public MessaggiController(Utente u,Home home){
        this.utilizzatore=u;
        MessaggioBroadcast m = new MessaggioBroadcast();

        //genero l'elenco delle notifiche
        this.elencoNotifiche = m.selezionaNotifiche();
        //creo la nuova view con le notifiche create
        this.listaNotifiche =new ListaNotifiche(elencoNotifiche);

        //creo il listener che controlla il bottone NUOVO MESSAGGIO
        this.NuovoMessaggioListener();

        //mi conservo la home per aggiornarla successivamente
        this.h=home;

        return;
    }

    public ListaNotifiche getListaNotifiche() {
        return listaNotifiche;
    }

    /**
     * Action per la creazione di un nuovo messaggio
     */
    protected void NuovoMessaggioListener(){
        JButton b = listaNotifiche.getButtonNuovo();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setN(new NuovoMessaggio());
                CreaMessaggioListener();
            }
        });

        return;
    }

    /**
     * Metodo per creare un nuovo mmesagio
     */
    protected void CreaMessaggioListener(){
        JButton b = n.getInviaButton();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessaggioBroadcast messaggio = new MessaggioBroadcast();
                messaggio.setMessaggio(n.getTesto().replace("\n", " "));//non si può andare a capo scrivendo un  nuovo messaggio
                messaggio.setData("CURRENT_DATE");
                messaggio.setMittente(utilizzatore.getNome() + " " + utilizzatore.getCognome());
                messaggio.setTipo(utilizzatore.getRuolo());
                if(messaggio.insertIntoSQL()){
                    aggiorna();

                    //dispose chiude la finestra
                    n.dispose();
                }else {
                    JOptionPane.showMessageDialog(new JFrame("errore"), "massimo 160 caratteri!");
                }

                //aggiorno l'interfaccia grafica
            }
        });
    }

    /**
     * aggiorna la schermata dei messaggi
     */
    public void aggiorna(){
        MessaggioBroadcast m = new MessaggioBroadcast();

        //genero il nuovo elenco delle notifiche
        this.elencoNotifiche = m.selezionaNotifiche();

        //creo la nuova view con le notifiche create
        this.listaNotifiche =new ListaNotifiche(elencoNotifiche);
        this.h.setScrollNotifiche(listaNotifiche.getPannelloPrincipale());

        //creo il listener che controlla il bottone NUOVO MESSAGGIO
        this.NuovoMessaggioListener();
    }

    //getter and setter
    public NuovoMessaggio getN() {
        return n;
    }

    public void setN(NuovoMessaggio n) {
        this.n = n;
    }
}
