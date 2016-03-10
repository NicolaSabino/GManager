package View;

import Model.Gruppi.GruppoProgetti;
import Model.Progetto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by edoardo on 02/03/16.
 */
public class Gestisci {
    private JTabbedPane tabbedPanePrincipale;
    private JPanel panelloPrincipale;
    private JPanel panelSequenze;
    private JPanel panelAttivita;
    private JPanel panelAppuntamenti;
    private JPanel panelOrdini;
    private JPanel panelSinistro;
    private JPanel panelDestro;
    private JScrollPane scrollSequenze;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTextField textFieldCosto;
    private JTextField textFieldFine;
    private JButton creaSeqenzaButton;
    private JTextField textFieldNomeMod;
    private JTextField textFieldFineMod;
    private JTextField textFieldCostoMod;
    private JButton eliminaSequenzaButton;
    private JButton modificaSequenza;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton creaAttivitaButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JButton modificaAttivitàButton;
    private JButton eliminaButton;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JButton creaButton;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JButton modificaApuntamentoButton;
    private JButton cancellaAppuntamentoButton;
    private JPanel panelProgetti;
    private JPanel panelUtenti;
    private JScrollPane scrollProgetti;
    private JTable tableProgetti;
    private JTabbedPane tabProgetti;
    private JTable table1;
    private JScrollPane scrollAttività;
    private JLabel labelModificaAttivita;
    private JLabel labelModificaSequenza;
    private JLabel labelModificaAppuntamento;
    private JLabel labelNomeProgetto;
    private JLabel labelFineProgetto;
    private JTextField fieldNomeProgetto;
    private JComboBox comboGiornoProgetto;
    private JComboBox comboMeseProgetto;
    private JComboBox comboAnnoProgetto;
    private JButton buttonCreaProgetto;
    private JLabel labelAnno;
    private JLabel lableMese;
    private JLabel lableGiorno;
    private JTextField fieldNomeProgetto_modifica;
    private JComboBox comboGiornoProgetto_modifica;
    private JComboBox comboMeseProgetto_modifica;
    private JComboBox comboAnnoProgetto_modifica;
    private JButton buttonModificaProgetto;
    private JButton buttonEliminaProgetto;
    private JLabel labelModificaProgetto;
    private JLabel labelNomeProgetto_modifica;
    private JLabel labelDataFine_modifica;
    private JButton buttonSalvaModificheProgetto;

    public Gestisci() {
        //setto gestisci progetto
        buttonEliminaProgetto.setForeground(Color.red);
        labelModificaProgetto.setText("<html><b>...Seleziona il progetto da modificare...</b></html>");
        disabilitaComponenti(true,fieldNomeProgetto_modifica, comboGiornoProgetto_modifica, comboMeseProgetto_modifica, comboAnnoProgetto_modifica);
        buttonSalvaModificheProgetto.setVisible(false);
        buttonEliminaProgetto.setEnabled(false);
        popolaProgetti();

        //limitazioni
        StaticMethod.textFieldLimitLength(fieldNomeProgetto,20);
        StaticMethod.textFieldLimitLength(fieldNomeProgetto_modifica,20);

        StaticMethod.populateData(comboGiornoProgetto,comboMeseProgetto,comboAnnoProgetto,5,0);
        StaticMethod.populateData(comboGiornoProgetto_modifica,comboMeseProgetto_modifica,comboAnnoProgetto_modifica,5,0);



        //setto le
    }

    public void disabilitaComponenti(boolean b,Component ...elementi){
        for(Component appoggio:elementi){
            appoggio.setEnabled(!b);
        }
    }


    public void popolaProgetti(){
        String col[] = {"Nome Progetto" , "Deadline" , "Costo"};

        CustomTable t = new CustomTable(col,0);

        GruppoProgetti progetti = new GruppoProgetti();

        for(Progetto appoggio:progetti.getStato()) {

            String nome = appoggio.getNome();
            String deadline = appoggio.getDeadline();
            double costo = appoggio.getCosto();

            Object[] data = {nome,deadline,costo};

            t.addRow(data);
        }

        tableProgetti.setModel(t);

    }


    public JPanel getPanelloPrincipale() {
        return panelloPrincipale;
    }

    /**
     * metodo che restituisce un oggetto progetto come risultato dell'inserimento da parte dell'utente in crea progeto
     * @return
     */
    public Progetto getParametriCreaProgetto(){
        Progetto p = new Progetto();
        p.setNome(fieldNomeProgetto.getText());
        p.setDeadline(comboAnnoProgetto.getSelectedItem().toString() + "-" + comboMeseProgetto.getSelectedItem().toString() + "-" + comboGiornoProgetto.getSelectedItem().toString());
        return p;
    }

    public Progetto getParametriModificaProgetto(){
        Progetto p = new Progetto();
        p.setNome(fieldNomeProgetto_modifica.getText());
        p.setDeadline(comboAnnoProgetto_modifica.getSelectedItem().toString() + "-" + comboMeseProgetto_modifica.getSelectedItem().toString()
                + "-" + comboGiornoProgetto_modifica.getSelectedItem().toString());
        return p;
    }

    public JButton getButtonCreaProgetto() {
        return buttonCreaProgetto;
    }

    public JTextField getFieldNomeProgetto() {
        return fieldNomeProgetto;
    }

    public JTextField getFieldNomeProgetto_modifica() {
        return fieldNomeProgetto_modifica;
    }

    public JComboBox getComboGiornoProgetto() {
        return comboGiornoProgetto;
    }

    public JComboBox getComboMeseProgetto() {
        return comboMeseProgetto;
    }

    public JComboBox getComboAnnoProgetto() {
        return comboAnnoProgetto;
    }

    public JComboBox getComboGiornoProgetto_modifica() {
        return comboGiornoProgetto_modifica;
    }

    public JComboBox getComboMeseProgetto_modifica() {
        return comboMeseProgetto_modifica;
    }

    public JComboBox getComboAnnoProgetto_modifica() {
        return comboAnnoProgetto_modifica;
    }

    public JTable getTableProgetti() {
        return tableProgetti;
    }

    public JButton getButtonModificaProgetto() {
        return buttonModificaProgetto;
    }

    public JLabel getLabelModificaProgetto() {
        return labelModificaProgetto;
    }

    public JButton getButtonSalvaModificheProgetto() {
        return buttonSalvaModificheProgetto;
    }

    public JTabbedPane getTabProgetti() {
        return tabProgetti;
    }

    public JButton getButtonEliminaProgetto() {
        return buttonEliminaProgetto;
    }
}
