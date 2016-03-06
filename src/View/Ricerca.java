package View;

import Model.Progetti;
import Model.Progetto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JButton Cerca;
    private JScrollPane ScrollRIcerca;
    private JButton salvaRicerca;
    private JComboBox comboProgetti;
    private JLabel labelProgetti;


    public Ricerca(){
        setVisible(true);
        comboBoxRicerca.setSelectedIndex(0); //setto di default la ricerca su utente
        CercaUtente();

        //genero i progetti disponibili nellla modalità cerca progetto e li inserisco nella combobox dei progetti
        Progetti p = new Progetti();
        ArrayList<String> s = new ArrayList<String>();

        //primo campo di default
        s.add(s.size(),"<html><font color=#778899>Seleziona il progetto</font></html>");
        for(Progetto appoggio:p.getStato()){
            s.add(s.size(),appoggio.getNome());
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(s.toArray());
        comboProgetti.setModel(comboBoxModel);

    }

    public void CercaUtente(){
        label1.setText("Nome");
        label2.setText("Cognome");
        label2.setVisible(true);
        campo2.setVisible(true);
        campo1.setVisible(true);
        Cerca.setVisible(true);
        labelProgetti.setVisible(false);
        comboProgetti.setVisible(false);
    }

    public void CercaAttivita(){
        label1.setText("Descrizione");
        label2.setVisible(false);
        campo2.setVisible(false);
        Cerca.setVisible(true);
        labelProgetti.setVisible(false);
        comboProgetti.setVisible(false);
    }

    public void CercaSequenza(){
        label1.setText("Nome");
        label2.setVisible(false);
        campo2.setVisible(false);
        Cerca.setVisible(true);
        labelProgetti.setVisible(false);
        comboProgetti.setVisible(false);
    }

    public void CercaProgetto(){
        label1.setVisible(false);
        label2.setVisible(false);
        campo1.setVisible(false);
        campo2.setVisible(false);
        Cerca.setVisible(true);
        labelProgetti.setVisible(true);
        comboProgetti.setVisible(true);
    }



    public JPanel getPrimoPanel() {
        return primoPanel;
    }

    public JComboBox getComboBoxRicerca(){
        return comboBoxRicerca;
    }

    public JButton getCerca() {
        return Cerca;
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

    public JComboBox getComboProgetti() {
        return comboProgetti;
    }
}
