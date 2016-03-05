package View;

import Model.Model;

import javax.swing.*;
import java.awt.*;

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
    private JCheckBox checkBox;
    private JLabel label1;
    private JLabel label2;
    private JButton Cerca;
    private JScrollPane ScrollRIcerca;


    public Ricerca(){
        setVisible(true);
        comboBoxRicerca.setSelectedIndex(0); //setto di default la ricerca su utente
        CercaUtente();
    }

    public void CercaUtente(){
        label1.setText("Nome");
        label2.setText("Cognome");
        label2.setVisible(true);
        campo2.setVisible(true);
        Cerca.setVisible(true);
    }

    public void CercaAttivita(){
        label1.setText("Descrizione");
        label2.setVisible(false);
        campo2.setVisible(false);
        Cerca.setVisible(true);
    }

    public void CercaSequenzaProgetto(){
        label1.setText("Nome");
        label2.setVisible(false);
        campo2.setVisible(false);
        Cerca.setVisible(true);
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
}
