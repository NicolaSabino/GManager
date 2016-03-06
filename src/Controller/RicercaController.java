package Controller;

import Model.Attivita;
import Model.Gruppo;
import Model.Progetto;
import Model.Sequenza;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by nicola on 02/03/16.
 */
public class RicercaController {

    private RootFrame rootFrame;
    private Ricerca ricerca;
    private int mod;
    private String campoProgetto;


    public RicercaController(RootFrame rootFrame) {
        this.rootFrame=rootFrame;
        this.ricerca= new Ricerca();
        this.mod=1;

        //attivo i listner
        listnerComboBox();
        listnerComboProgetto();
        listnerBottoneCerca();
        return;
    }

    public void apriPannelloRicerca(){
        rootFrame.setMainScrollPane(ricerca.getPrimoPanel());
    }

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

    public void listnerComboProgetto(){
        JComboBox box = ricerca.getComboProgetti();
        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setCampoProgetto(e.getItem().toString());
            }
        });
    }

    public void listnerBottoneCerca(){
        JButton cerca =ricerca.getCerca();
        cerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stampaRisultato();
            }
        });
    }



    protected void stampaRisultato(){
        switch (mod){
            case 1 :{

                    //ricerca per nome
                    Gruppo g = new Gruppo();
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
                //ricerca attività
                Sequenza s = new Sequenza(ricerca.getTestoCampo1());
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                tabellaAttivita.setModelTabella(s.getStato());
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());
                break;
            }
            case 4:{
                //ricerca progetto
                Progetto p = new Progetto(this.campoProgetto);
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

    public void setCampoProgetto(String campoProgetto) {
        this.campoProgetto = campoProgetto;
    }
}
