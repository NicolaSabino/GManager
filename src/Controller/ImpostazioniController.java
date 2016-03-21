package Controller;

import Model.Utente;
import View.Impostazioni;
import View.RootFrame;
import View.infoSulProgramma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicola on 02/03/16.
 */
public class ImpostazioniController {

    private RootFrame rootFrame;
    private Impostazioni impostazioni;
    private Utente utilizzatore;
    private infoSulProgramma infoSulProgramma;

    public ImpostazioniController(RootFrame rootFrame, Utente utilizzatore) {
        this.infoSulProgramma = new infoSulProgramma();
        this.rootFrame=rootFrame;
        this.impostazioni= new Impostazioni();
        this.utilizzatore=utilizzatore;


        //imposto i campi dell'interfaccia
        this.impostazioni.setCampoNome(utilizzatore.getNome());
        this.impostazioni.setCampoCognome(utilizzatore.getCognome());
        this.impostazioni.setCampoEmail(utilizzatore.getMail());
        this.impostazioni.setCampoPassword(utilizzatore.getPwd());
        this.impostazioni.setCampoPasswordCheck(utilizzatore.getPwd());
        this.impostazioni.setCampoTelefono(utilizzatore.getTelefono());

        //setto tutti i campi come disabilitati
        this.impostazioni.disabilitaCampi(true);

        //imposto i listner
        modificaListener();
        salvaListener();
        logoutListener();
        infoListener();
        listenerIndietro();

        return;
    }

    public void apriImpostazioni(){
        rootFrame.setMainScrollPane(impostazioni.getPannelloPrincipale());
    }

    protected void modificaListener(){
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


    protected void salvaListener() {
        JButton salva = impostazioni.getButtonSalva();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaCampi();
            }
        });
    }

    protected void logoutListener() {
        JButton salva = impostazioni.getButtonLogout();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController l = new LoginController(rootFrame);
            }
        });
    }

    protected void infoListener(){
        JButton info = impostazioni.getInfoButton();
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootFrame.setMainScrollPane(infoSulProgramma.getPanelloPrincipale());
            }
        });
    }

    private void listenerIndietro(){
        JButton indietro=infoSulProgramma.getButtonIndietro();
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rootFrame.setMainScrollPane(impostazioni.getPannelloPrincipale());
            }
        });
    }


    protected void salvaCampi(){
        if(impostazioni.getModifiche().getPwd().equals(new String(impostazioni.getCampoPasswordCheck().getPassword()))) {
            utilizzatore.updateIntoSQL("pwd", impostazioni.getModifiche().getPwd());
            utilizzatore.updateIntoSQL("nome", impostazioni.getModifiche().getNome());
            utilizzatore.updateIntoSQL("cognome", impostazioni.getModifiche().getCognome());
            utilizzatore.updateIntoSQL("telefono", impostazioni.getModifiche().getTelefono());
            utilizzatore.updateIntoSQL("mail", impostazioni.getModifiche().getMail());
        }else impostazioni.displayErrorMessage("Errore inserimento password!", "errore!");
        this.impostazioni.disabilitaCampi(true);
    }

}
