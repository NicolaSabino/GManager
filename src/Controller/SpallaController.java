package Controller;

import Model.Utente;
import View.RootFrame;
import View.Spalla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Crea la spalla
 */
public class SpallaController {

    private Utente utilizzatore;
    private Permesso permesso;
    private RootFrame rootFrame;
    private Spalla spalla;

    private HomeController homeController;
    private RicercaController ricercaController;
    private ImpostazioniController impostazioniController;
    private GestisciController gestisciControler;
    private OrdiniController ordiniController;

    /**
     * Costruttore della spalla
     *
     * @param p enum del ruolo dell'utilizzatore
     * @param s view della spalla
     * @param mat matricola utente
     * @param r view del rootFrame
     * @param mat_utilizzatore matricola utilizzatore
     */
    public SpallaController(Permesso p, Spalla s, String mat,RootFrame r,String mat_utilizzatore) {

        this.permesso=p;
        this.utilizzatore= new Utente(mat);
        this.rootFrame=r;
        this.spalla=s;

        switch (p){
            case US:{
                //setto le informazioni dell'utente in alto a sinistra sulla spalla
                spalla.usMode(utilizzatore);
                break;
            }
            case GL:{
                spalla.proMode(utilizzatore);
                break;
            }
            case TL:{
                spalla.proMode(utilizzatore);
                break;
            }

        }


        //faccio partire i controller delle varie schede

        this.homeController         = new HomeController(rootFrame,mat_utilizzatore);  //di default quando creo HomeController si imposta automaticamente sul mainScrollpane
        this.ricercaController      = new RicercaController(rootFrame);
        this.impostazioniController = new ImpostazioniController(rootFrame,utilizzatore);
        this.gestisciControler      = new GestisciController(rootFrame,utilizzatore,homeController);
        this.ordiniController       = new OrdiniController(rootFrame,utilizzatore,homeController);

        //passo al controler di ordini la view gestisci così da poterla aggiornare ogni qualvolta viene creato un nuovo ordine
        this.ordiniController.setGestisci(this.gestisciControler.getGestisci());
        //passo al controller di gestisci la view di ordini così da poterla aggiornare ogni qualvolta vine approvato o non approvato un ordine
        this.gestisciControler.setOrdiniController(this.ordiniController);


        //creo i listner
        this.ricercaListner();
        this.homeListner();
        this.impostazioniListner();
        this.gestisciListner();
        this.ordiniListener();
        return;
    }

    /**
     * listner del bottone home
     */
    private void homeListner(){
        JButton home = spalla.getHomeButton();
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaHome();
            }
        });
        return;
    }

    /**
     * listner del bottone impostazioni
     */
    private void impostazioniListner(){
        JButton impostazioni = spalla.getImpostazioniButton();
        impostazioni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaImpostazioni();
            }
        });
        return;
    }

    /**
     * listner del bottone ricerca
     */
    protected void ricercaListner(){
        JButton ricerca=spalla.getRicercaButton();
        ricerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaRicerca();
            }
        });
        return;
    }

    /**
     * listner del bottone gestisci
     */
    private void gestisciListner(){
        JButton gestisci=spalla.getGestisciButton();
        gestisci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaGestisci();
            }
        });
    }

    /**
     * listner del bottone ordini
     */
    private void ordiniListener(){
        JButton ordini=spalla.getOrdiniButton();
        ordini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avviaOrdini();
            }
        });
    }




    //azioni dei bottoni

    private void avviaRicerca(){
        ricercaController.apriPannelloRicerca();
    }

    private void avviaHome(){
        homeController.apriHome();
    }

    private void avviaImpostazioni(){
        impostazioniController.apriImpostazioni();
    }

    private void avviaGestisci(){
        gestisciControler.apriGestisci();
    }

    private void avviaOrdini(){
        ordiniController.apriOrdini();
    }


}