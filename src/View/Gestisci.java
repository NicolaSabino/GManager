package View;

import Model.Attivita;
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
    private JTabbedPane tabbedPane4;
    private JTable table1;
    private JScrollPane scrollAttività;
    private JLabel labelModificaAttivita;
    private JLabel labelModificaSequenza;
    private JLabel labelModificaAppuntamento;
    private JLabel labelNomeProgetto;
    private JLabel labelFineProgetto;
    private JTextField fieldNomeProgetto;
    private JTextField fieldGiornoProgetto;
    private JTextField fieldMeseProgetto;
    private JTextField fieldAnnoProgetto;
    private JButton buttonCreaProgetto;
    private JLabel labelAnno;
    private JLabel lableMese;
    private JLabel lableGiorno;
    private JTextField fieldNomeProgetto_modifica;
    private JTextField fieldGiornoProgetto_modifica;
    private JTextField fieldMeseProgetto_modifica;
    private JTextField fieldAnnoProgetto_modifica;
    private JButton buttonModificaProgetto;
    private JButton buttonEliminaProgetto;
    private JLabel labelModificaProgetto;
    private JLabel labelNomeProgetto_modifica;
    private JLabel labelDataFine_modifica;

    public Gestisci() {
        //setto gestisci progetto
        buttonEliminaProgetto.setForeground(Color.red);
        labelModificaProgetto.setText("<html><b>...Seleziona il progetto da modificare...</b></html>");
        disabilitaComponenti(fieldNomeProgetto_modifica,fieldGiornoProgetto_modifica,fieldMeseProgetto_modifica,fieldAnnoProgetto_modifica);
        popolaProgetti();

        //limitazioni
        StaticMethod.textFieldLimitLength(fieldNomeProgetto,20);
        StaticMethod.textFieldLimitLength(fieldNomeProgetto_modifica,20);

        StaticMethod.textFieldLimitOnlyInt(fieldAnnoProgetto,4);
        StaticMethod.textFieldLimitOnlyInt(fieldMeseProgetto,2);
        StaticMethod.textFieldLimitOnlyInt(fieldGiornoProgetto,2);

        StaticMethod.textFieldLimitOnlyInt(fieldAnnoProgetto_modifica,4);
        StaticMethod.textFieldLimitOnlyInt(fieldMeseProgetto_modifica,2);
        StaticMethod.textFieldLimitOnlyInt(fieldGiornoProgetto_modifica,2);


        //setto le
    }

    public void disabilitaComponenti(Component ...elementi){
        for(Component appoggio:elementi){
            appoggio.setEnabled(false);
        }
    }

    public void popolaProgetti(){
        String col[] = {"Nome Progetto" , "Deadline" , "Costo"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        GruppoProgetti progetti = new GruppoProgetti();

        for(Progetto appoggio:progetti.getStato()) {

            String nome = appoggio.getNome();
            String deadline = appoggio.getDeadline();
            double costo = appoggio.getCosto();

            Object[] data = {nome,deadline,costo};

            tableModel.addRow(data);
        }

        tableProgetti.setModel(tableModel);
        tableProgetti.setEnabled(false);
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
        p.setDeadline(fieldAnnoProgetto.getText() + "-" + fieldMeseProgetto.getText() + "-" + fieldGiornoProgetto.getText());
        return p;
    }

    public Progetto getParametriModificaProgetto(){
        Progetto p = new Progetto();
        p.setNome(fieldNomeProgetto_modifica.getText());
        p.setDeadline(fieldAnnoProgetto_modifica.getText() + "-" + fieldMeseProgetto_modifica.getText() + "-" + fieldGiornoProgetto_modifica.getText());
        return p;
    }

    public JButton getButtonCreaProgetto() {
        return buttonCreaProgetto;
    }
}
