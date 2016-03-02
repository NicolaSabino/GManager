package Controller;

import Model.Utente;
import View.RootFrame;
import View.Spalla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicola on 02/03/16.
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

        //todo ipotetica implementazione mediante metodi statici
        //faccio partire i controller delle varie schede

        this.homeController         = new HomeController(permesso,rootFrame,mat_utilizzatore);  //di default quando creo HomeControlle si imposta automaticamente sul mainScrollpane
        this.ricercaController      = new RicercaController(rootFrame);
        this.impostazioniController = new ImpostazioniController(rootFrame,utilizzatore);
        this.gestisciControler      = new GestisciController(rootFrame);
        //GestisciController    gestisciController     = new GestisciController();


        //creo i listner
        this.ricercaListner();
        this.homeListner();
        this.impostazioniListner();
        this.gestisciListner();
        return;
    }

    /**
     * listner del bottone home
     */
    public void homeListner(){
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
    public void impostazioniListner(){
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




    //azioni dei bottoni

    protected void avviaRicerca(){
        ricercaController.apriPannelloRicerca();
    }

    protected void avviaHome(){
        homeController.apriHome();
    }

    protected void avviaImpostazioni(){
        impostazioniController.apriImpostazioni();
    }

    protected void avviaGestisci(){
        gestisciControler.apriGestisci();
    }
}
