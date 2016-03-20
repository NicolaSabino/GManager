package View;

import Controller.RicercaController;
import Model.Attivita;
import Model.Gruppi.GruppoProgetti;
import Model.Gruppi.GruppoSequenze;
import Model.Progetto;
import Model.Sequenza;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Rappresentazione della schermata di ricerca
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
    private JFileChooser fileChooser;

    public Ricerca(){
        setVisible(true);
        comboBoxRicerca.setSelectedIndex(0); //setto di default la ricerca su utente
        CercaUtente();


    }

    /**
     * Metodo che permette all'utente di scegliere il path dove collocare il file di esportazione
     *
     * @return stringa del path
     */
    public String getFileName(){
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter textFilter1 = new FileNameExtensionFilter("Text Files","txt");
        FileNameExtensionFilter textFilter2 = new FileNameExtensionFilter("Csv Files","csv");
        fc.addChoosableFileFilter(textFilter1);
        fc.addChoosableFileFilter(textFilter2);

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            return fc.getSelectedFile().getPath();
        }else return "";//se non approva ritorna una stringa vuota
    }


    /**
     * fa visualizzare i campi per la ricerca per Utente
     */
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

    /**
     * fa visualizzare i campi pe ricarca per Attività
     */
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

    /**
     * fa visualizzare i campi pe ricarca per Sequenza
     */
    public void CercaSequenza(){
        //nascondo label 1 e 2
        label1.setVisible(false);
        label2.setVisible(false);
        //nascondo campo 1 e 2
        campo1.setVisible(false);
        campo2.setVisible(false);


        comboProgettiSequenze.setVisible(true);

        StaticMethod.popolaComboSequenze(comboProgettiSequenze);
    }

    /**
     * fa visualizzare i campi pe ricarca per Progetto
     */
    public void CercaProgetto(){
        //nascondo label 1 e 2
        label1.setVisible(false);
        label2.setVisible(false);
        //nascondo campo 1 e 2
        campo1.setVisible(false);
        campo2.setVisible(false);

        //setto la combo
        comboProgettiSequenze.setVisible(true);

        StaticMethod.popolaComboProgetti(comboProgettiSequenze);
    }

    /**
     * Dialog per la segnalazione di errori
     *
     * @param errorMessage
     * @param errorTitle
     */
    public void displayErrorMessage(String errorMessage,String errorTitle){
        JOptionPane messaggioErrore = new JOptionPane(errorMessage,JOptionPane.ERROR_MESSAGE  );
        JDialog dialog = messaggioErrore.createDialog(errorTitle);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


    //getter e setter
    public JButton getSalvaRicerca() {
        return salvaRicerca;
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