package Controller;

import Model.Attivita;
import Model.Progetto;
import Model.Sequenza;
import View.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by nicola on 02/03/16.
 */
public class RicercaController {

    private RootFrame rootFrame;
    private Ricerca ricerca;
    private int mod;
    private String campoDiRIcerca;


    public RicercaController(RootFrame rootFrame) {
        this.rootFrame=rootFrame;
        this.ricerca= new Ricerca();
        this.mod=1;
        stampaRisultato();

        //attivo i listner
        listnerComboBox();
        listnerCombo();
        listnerCerca();
        return;
    }

    public void apriPannelloRicerca(){
        rootFrame.setMainScrollPane(ricerca.getPrimoPanel());
    }

    /**
     * Listner della combobox principale
     */
    public void listnerComboBox(){
        JComboBox comboBox = ricerca.getComboBoxRicerca();
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if(e.getItem()=="Utente"){
                   modalitaView(1);
               }else if(e.getItem()=="Attività"){
                   modalitaView(2);
               }else if(e.getItem()=="Sequenza") {
                   modalitaView(3);
               }else if(e.getItem()=="Progetto") {
                   modalitaView(4);
               }

            }
        });
    }

    /**
     * ItemListner della combobox di progetti/sequenze
     */
    public void listnerCombo(){
        JComboBox box = ricerca.getComboProgettiSequenze();

        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setCampoDiRIcerca(e.getItem().toString());
                stampaRisultato();
            }
        });
    }

    /**
     * keylistner di ricerca che chiamano il metodo stampa risultato
     */
    public void listnerCerca(){
        //JButton cerca =ricerca.getCerca();
        JTextField textField1 = ricerca.getCampo1();
        JTextField textField2 = ricerca.getCampo2();

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                stampaRisultato();
            }
        });

        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                stampaRisultato();
            }
        });

    }


    /**
     * metodo che stampa a video sotto forma tabellare il risultato della ricerca
     */
    protected void stampaRisultato(){
        switch (mod){
            case 1 :{

                    //ricerca per nome
                    Model.Gruppi.Gruppo g = new Model.Gruppi.Gruppo();
                    g.createFrom("nome+cognome",ricerca.getTestoCampo1(),ricerca.getTestoCampo2());
                    TabellaUtenti tabellaUtenti = new TabellaUtenti();
                    tabellaUtenti.setModelTabella(g.getElenco());
                    ricerca.setScrollRIcerca(tabellaUtenti.getPannelloPrincipale());
                break;
            }
            case 2:{

                //ricerca attività
                Sequenza s = new Sequenza("descrizione",ricerca.getTestoCampo1());
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                tabellaAttivita.setModelTabella(s.getStato());
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());
                break;
            }
            case 3:{
                //ricerca Sequenza
                Sequenza s = new Sequenza(this.campoDiRIcerca);
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                tabellaAttivita.setModelTabella(s.getStato());
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());
                break;
            }
            case 4:{
                //ricerca progetto
                Progetto p = new Progetto(this.campoDiRIcerca);
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                ArrayList<Attivita> risultatoRicerca = new ArrayList<Attivita>();

                //seleziono tutte le attività di un determinato progetto considerando le sue sequenze
                for(Sequenza appoggio:p.getStato()){
                    for(Attivita app:appoggio.getStato()){
                        risultatoRicerca.add(risultatoRicerca.size(),app);
                    }
                }

                tabellaAttivita.setModelTabella(risultatoRicerca);
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());

               return;
            }
        }
    }

    /**
     * metodo che setta la MODALIÀ DI VIEW
     * @param mod
     */
    public void modalitaView(int mod){
        switch (mod){
            case 1 :{
                ricerca.CercaUtente();
                this.mod=1;
                break;
            }
            case 2:{
                ricerca.CercaAttivita();
                this.mod=2;
                break;
            }
            case 3:{
                ricerca.CercaSequenza();
                this.mod=3;
                break;
            }
            case 4:{
                ricerca.CercaProgetto();
                this.mod=4;
                break;
            }
        }
    }



    public void setCampoDiRIcerca(String campoDiRIcerca) {
        this.campoDiRIcerca = campoDiRIcerca;
    }
}
