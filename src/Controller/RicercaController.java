package Controller;

import Model.Gruppo;
import View.Impostazioni;
import View.Ricerca;
import View.RootFrame;
import View.TabellaUtenti;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by nicola on 02/03/16.
 */
public class RicercaController {

    private RootFrame rootFrame;
    private Ricerca ricerca;
    private int mod;


    public RicercaController(RootFrame rootFrame) {
        this.rootFrame=rootFrame;
        this.ricerca= new Ricerca();
        this.mod=1;

        listnerComboBox();
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

                    //ricerca
                    Gruppo g = new Gruppo();
                    g.createFrom("nome+cognome",ricerca.getTestoCampo1(),ricerca.getTestoCampo2());
                    TabellaUtenti tabellaUtenti = new TabellaUtenti();
                    tabellaUtenti.setModelTabella(g.getElenco());
                    ricerca.setScrollRIcerca(tabellaUtenti.getPannelloPrincipale());
                break;
            }
            case 2:{

                break;
            }
            case 3:{

                break;
            }
            case 4:{
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
                ricerca.CercaSequenzaProgetto();
                this.mod=3;
                break;
            }
            case 4:{
                ricerca.CercaSequenzaProgetto();
                this.mod=4;
                break;
            }
        }
    }
}
