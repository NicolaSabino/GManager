package Controller;

import Model.MessaggioBroadcast;
import Model.Utente;
import View.ListaNotifiche;
import View.NuovoMessaggio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by nicola on 20/02/16.
 */
public class MessaggiController {

    private ListaNotifiche listaNotifiche;
    private ArrayList<String> elencoNotifiche;
    private Utente utilizzatore;
    private NuovoMessaggio n;

    public MessaggiController(Utente u){
        this.utilizzatore=u;
        MessaggioBroadcast m = new MessaggioBroadcast();
        this.elencoNotifiche = m.selezionaNotifiche();
        this.listaNotifiche =new ListaNotifiche(elencoNotifiche);
        this.NuovoMessaggioListener();
        return;
    }

    public ListaNotifiche getListaNotifiche() {
        return listaNotifiche;
    }

    public void NuovoMessaggioListener(){
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

    public void CreaMessaggioListener(){
        JButton b = n.getInviaButton();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println();
                MessaggioBroadcast messaggio = new MessaggioBroadcast();
                messaggio.setMessaggio(n.getTesto());
                messaggio.setData("CURRENT_DATE");
                messaggio.setMittente(utilizzatore.getNome() + utilizzatore.getCognome());
                messaggio.setTipo(utilizzatore.getRuolo());
                if(messaggio.insertIntoSQL()){
                    JOptionPane.showMessageDialog(new JFrame("errore"),"messaggio inserito con successo!");
                }
            }
        });
    }

    //getter and setter
    public NuovoMessaggio getN() {
        return n;
    }

    public void setN(NuovoMessaggio n) {
        this.n = n;
    }
}
