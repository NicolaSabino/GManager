package View;

import Model.Attivita;
import Model.Gruppi.GruppoProgetti;
import Model.Gruppi.GruppoSequenze;
import Model.Progetto;
import Model.Sequenza;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by edoardo on 01/03/16.
 */
public class Ricerca extends JPanel {
    private JPanel primoPanel;
    private JPanel panelRicerca;
    private JComboBox comboBoxRicerca;
    private JTextField campo1;
    private JTextField campo2;
    private JLabel titoloRicerca;
    private JLabel label1;
    private JLabel label2;
    private JScrollPane ScrollRIcerca;
    private JButton salvaRicerca;
    private JComboBox comboProgettiSequenze;
    private JCheckBox chechkstato;


    public Ricerca(){
        setVisible(true);
        comboBoxRicerca.setSelectedIndex(0); //setto di default la ricerca su utente
        CercaUtente();


    }

    public void CercaUtente(){
        label1.setText("Nome");
        label2.setText("Cognome");
        label1.setVisible(true);
        label2.setVisible(true);
        campo2.setVisible(true);
        campo1.setVisible(true);
        campo1.setText("");
        comboProgettiSequenze.setVisible(false);

    }

    public void CercaAttivita(){
        //mostro label 1 labelcombo e nascondo label 2
        label1.setText("Descrizione");
        label2.setVisible(false);
        //mostro campo 1 e nascondo campo 2
        campo1.setVisible(true);
        campo2.setVisible(false);
        comboProgettiSequenze.setVisible(false);
        comboProgettiSequenze.setVisible(false);
    }

    public void CercaSequenza(){
        //nascondo label 1 e 2
        label1.setVisible(false);
        label2.setVisible(false);
        //nascondo campo 1 e 2
        campo1.setVisible(false);
        campo2.setVisible(false);


        comboProgettiSequenze.setVisible(true);

        comboPerSequenza();
    }

    public void CercaProgetto(){
        //nascondo label 1 e 2
        label1.setVisible(false);
        label2.setVisible(false);
        //nascondo campo 1 e 2
        campo1.setVisible(false);
        campo2.setVisible(false);

        //setto la combo
        comboProgettiSequenze.setVisible(true);

        comboPerProgetto();
    }

    public void comboPerProgetto(){

        //genero i progetti disponibili nellla modalità cerca progetto e li inserisco nella combobox
        GruppoProgetti p = new GruppoProgetti();
        ArrayList<String> s = new ArrayList<String>();

        //primo campo di default
        s.add(s.size(),"<html><font color=#778899>Seleziona il progetto</font></html>");
        for(Progetto appoggio:p.getStato()){
            s.add(s.size(),appoggio.getNome());
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(s.toArray());
        comboProgettiSequenze.setModel(comboBoxModel);
    }

    public void comboPerSequenza(){
        //genero le sequenze disponibili nellla modalità cerca sequenze e li inserisco nella combobox
        GruppoSequenze s = new GruppoSequenze();
        ArrayList<String> strings = new ArrayList<String>();

        //primo campo di default
        strings.add(strings.size(),"<html><font color=#778899>Seleziona la sequenza</font></html>");
        for(Sequenza appoggio:s.getStato()){
            strings.add(strings.size(),appoggio.getNome());
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(strings.toArray());
        comboProgettiSequenze.setModel(comboBoxModel);
    }


    public JPanel getPrimoPanel() {
        return primoPanel;
    }

    public JComboBox getComboBoxRicerca(){
        return comboBoxRicerca;
    }


    public String getTestoCampo1() {
        return campo1.getText();
    }

    public String getTestoCampo2() {
        return campo2.getText();
    }

    public void setScrollRIcerca(Component panel) {
        this.ScrollRIcerca.setViewportView(panel);
    }

    public JComboBox getComboProgettiSequenze() {
        return comboProgettiSequenze;
    }

    public JTextField getCampo1() {
        return campo1;
    }

    public JTextField getCampo2() {
        return campo2;
    }

    public JCheckBox getChechkstato() {
        return chechkstato;
    }
}
