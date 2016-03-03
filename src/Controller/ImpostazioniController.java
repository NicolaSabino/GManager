package Controller;

import Model.Utente;
import View.Impostazioni;
import View.RootFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicola on 02/03/16.
 */
public class ImpostazioniController {

    private RootFrame rootFrame;
    private Impostazioni impostazioni;

    public ImpostazioniController(RootFrame rootFrame, Utente utilizzatore) {

        this.rootFrame=rootFrame;
        this.impostazioni= new Impostazioni();

        //imposto i campi dell'interfaccia
        this.impostazioni.setCampoNome(utilizzatore.getNome());
        this.impostazioni.setCampoCognome(utilizzatore.getCognome());
        this.impostazioni.setCampoEmail(utilizzatore.getMail());
        this.impostazioni.setCampoPassword(utilizzatore.getPwd());
        this.impostazioni.setCampoTelefono(utilizzatore.getTelefono());

        //setto tutti i campi come disabilitati
        this.impostazioni.disabilitaCampi(true);

        //imposto i listner
        this.modificaListner();

        return;
    }

    public void apriImpostazioni(){
        rootFrame.setMainScrollPane(impostazioni.getPannelloPrincipale());
    }

    protected void modificaListner(){
        JButton modifica = impostazioni.getButtonModifica();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaCampi();
            }
        });
    }

    protected void modificaCampi(){
        this.impostazioni.disabilitaCampi(false);
    }
}
