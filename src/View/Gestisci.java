package View;

import Model.Attivita;
import Model.Gruppi.GruppoAppuntamenti;
import Model.Gruppi.GruppoProgetti;
import Model.Incontro;
import Model.Progetto;
import Model.Sequenza;

import javax.swing.*;
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
    private JTabbedPane tabSequenze;
    private JTabbedPane tabAttivita;
    private JTabbedPane tabbedPane3;
    private JButton buttonCreaSequenza;
    private JTextField fieldNomeSequenza_modifica;
    private JButton buttonEliminaSequenza;
    private JButton buttonModificaSequenza;
    private JTextField fieldCostoAttivita;
    private JTextField fieldDescrizioneAttivtia;
    private JTextField textField3;
    private JTextField fieldPrecedenzaAttivita;
    private JTextField textField5;
    private JTextField textField6;
    private JButton buttonCreaAttivita;
    private JTextField textField7;
    private JTextField fieldCostoAttivta_modifica;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField fieldPrecedenzaAttivita_modifica;
    private JTextField fieldDescrizioneAttivita_modifica;
    private JButton buttonModificaAttivita;
    private JButton buttonEliminaAttivita;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField fieldLuogoAppuntamento;
    private JTextField textField16;
    private JTextField textField17;
    private JButton buttonCreaAppuntamento;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField fieldLuogoAppuntamento_modifica;
    private JTextField textField21;
    private JTextField textField22;
    private JButton buttonModifcaAppuntamento;
    private JButton butonCancellaAppuntamento;
    private JPanel panelProgetti;
    private JPanel panelUtenti;
    private JScrollPane scrollProgetti;
    private JTable tableProgetti;
    private JTabbedPane tabProgetti;
    private JTable tableAttivita;
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
    private JComboBox comboGiornoFineSequenza;
    private JComboBox comboMeseFineSequenza;
    private JComboBox comboAnnoFineSequenza;
    private JComboBox comboGiornoFineSequenza_modifica;
    private JComboBox comboMeseFineSequenza_modifica;
    private JComboBox comboAnnoFineSequenza_modifica;
    private JTable tableSequenze;
    private JLabel lableNomeSequenza;
    private JTextField fieldNomeSequenza;
    private JButton buttonSalvaModificheSequenza;
    private JComboBox comboProgetti_modifica;
    private JComboBox comboProgetti;
    private JComboBox comboGiornoInizoAttivita;
    private JComboBox comboMeseInizioAttivita;
    private JComboBox comboAnnoInizioAttivita;
    private JComboBox comboGiornoFineAttivita;
    private JComboBox comboMeseFineAttivita;
    private JComboBox comboAnnoFineAttivita;
    private JComboBox comboGiornoInizioAttivita_modifica;
    private JComboBox comboMeseInizioAttivita_modifica;
    private JComboBox comboAnnoInizioAttivita_modifica;
    private JComboBox comboGiornoFineAttivita_modifica;
    private JComboBox comboMeseFineAttivita_modifica;
    private JComboBox comboAnnoFineAttivita_modifica;
    private JButton buttonSalvaModificheAttivita;
    private JComboBox comboSequenze;
    private JComboBox comboSequenze_modifica;
    private JComboBox comboTipoAppuntamento;
    private JTable tableApuntamenti;
    private JComboBox comboGiornoAppuntamento;
    private JComboBox comboMeseAppuntamento;
    private JComboBox comboAnnoAppuntamento;
    private JComboBox comboOraAppuntamento;
    private JComboBox comboMinutiAppuntamento;
    private JButton buttonSalvaAppuntamento;
    private JComboBox comboTipoAppuntamento_modifica;
    private JTextArea areaVerbaleAppuntamento;
    private JComboBox comboGiornoAppuntamento_modifica;
    private JComboBox comboMeseAppuntamento_modifica;
    private JComboBox comboAnnoAppuntamento_modifca;
    private JComboBox comboOraAppuntamento_modifica;
    private JComboBox comboMinutiAppuntamento_modifica;
    private JRadioButton invitaTuttiRadioButton;
    private JRadioButton invitaDaUnaSequenzaRadioButton;
    private JButton buttonInvitati;
    private JComboBox comboSequenzeAppuntamento;
    private JTable table1;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JTable table3;
    private JTabbedPane tabbedPane1;
    private JRadioButton direttivoRadioButton;
    private JComboBox comboGiornoInizioSequenza;
    private JComboBox comboMeseInizioSequenza;
    private JComboBox comboAnnoInizioSequenza;
    private JComboBox comboGiornoInizioSequenza_modifica;
    private JComboBox comboMeseInizioSequenza_modifica;
    private JComboBox comboAnnoInizioSequenza_modifica;

    public Gestisci() {
        //setto gestisci progetto
        buttonEliminaProgetto.setForeground(Color.red);
        labelModificaProgetto.setText("<html><b>...Seleziona il progetto da modificare...</b></html>");
        labelModificaSequenza.setText("<html><b>...Seleziona la sequenza da modificare...</b></html>");
        labelModificaAttivita.setText("<html><b>...Seleziona l'attività da modificare...</b></html>");


        //popolo le tabelle
        popolaProgetti();
        popolaSequenze();
        popolaAttivita();
        popolaAppuntamenti();


        disabilitaComponenti(true,fieldNomeProgetto_modifica, comboGiornoProgetto_modifica, comboMeseProgetto_modifica, comboAnnoProgetto_modifica);
        disabilitaComponenti(true,fieldNomeSequenza_modifica,comboProgetti_modifica);
        disabilitaComponenti(true,fieldDescrizioneAttivita_modifica,fieldPrecedenzaAttivita_modifica,fieldCostoAttivta_modifica,
                comboSequenze_modifica,comboGiornoInizioAttivita_modifica,comboMeseInizioAttivita_modifica,comboAnnoInizioAttivita_modifica,
                comboGiornoFineAttivita_modifica,comboMeseFineAttivita_modifica,comboAnnoFineAttivita_modifica);


        buttonSalvaModificheProgetto.setVisible(false);
        buttonEliminaProgetto.setEnabled(false);
        buttonModificaProgetto.setEnabled(false);



        buttonSalvaModificheSequenza.setVisible(false);
        buttonEliminaSequenza.setEnabled(false);
        buttonModificaSequenza.setEnabled(false);



        buttonSalvaModificheAttivita.setVisible(false);
        buttonEliminaAttivita.setEnabled(false);
        buttonModificaAttivita.setEnabled(false);



        //limitazioni
        StaticMethod.textFieldLimitLength(fieldDescrizioneAttivtia,80);
        StaticMethod.textFieldLimitLength(fieldCostoAttivta_modifica,80);
        StaticMethod.textFieldLimitOnlyInt(fieldPrecedenzaAttivita,4);
        StaticMethod.textFieldLimitLength(fieldPrecedenzaAttivita_modifica,4);
        StaticMethod.textFieldLimitOnlyDouble(fieldCostoAttivita);
        StaticMethod.textFieldLimitOnlyDouble(fieldCostoAttivta_modifica);
        fieldCostoAttivita.setText("0");
        fieldCostoAttivta_modifica.setText("0");

        StaticMethod.textFieldLimitLength(fieldNomeProgetto,20);
        StaticMethod.textFieldLimitLength(fieldNomeProgetto_modifica,20);

        StaticMethod.populateData(comboGiornoProgetto,comboMeseProgetto,comboAnnoProgetto,5,0);
        StaticMethod.populateData(comboGiornoProgetto_modifica,comboMeseProgetto_modifica,comboAnnoProgetto_modifica,5,0);
        StaticMethod.populateData(comboGiornoInizoAttivita,comboMeseInizioAttivita,comboAnnoInizioAttivita,5,0);
        StaticMethod.populateData(comboGiornoFineAttivita,comboMeseFineAttivita,comboAnnoFineAttivita,5,0);
        StaticMethod.populateData(comboGiornoInizioAttivita_modifica,comboMeseInizioAttivita_modifica,comboAnnoInizioAttivita_modifica,5,0);
        StaticMethod.populateData(comboGiornoFineAttivita_modifica,comboMeseFineAttivita_modifica,comboAnnoFineAttivita_modifica,5,0);
        StaticMethod.populateData(comboGiornoAppuntamento,comboMeseAppuntamento,comboAnnoAppuntamento,5,0);
        StaticMethod.populateData(comboGiornoAppuntamento_modifica,comboMeseAppuntamento_modifica,comboAnnoAppuntamento_modifca,5,0);


        StaticMethod.popolaComboProgetti(comboProgetti);
        StaticMethod.popolaComboProgetti(comboProgetti_modifica);

        StaticMethod.popolaComboSequenze(comboSequenze);
        StaticMethod.popolaComboSequenze(comboSequenze_modifica);
        comboProgetti_modifica.setEnabled(false);




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

    public void popolaSequenze(){
        String col[]={"Nome Sequenza" , "Nome Progetto" , "Fine" , "Costo"};
        CustomTable t = new CustomTable(col,0);

        GruppoProgetti progetti = new GruppoProgetti();

        for(Progetto appoggio:progetti.getStato()){
            for(Sequenza sequenza:appoggio.getStato()){

                String nomeSequenza = sequenza.getNome();
                String nomeProgetto = appoggio.getNome();
                String fine         = sequenza.getFine();
                Double costo        = sequenza.getCosto();

                Object[] data = {nomeSequenza,nomeProgetto,fine,costo};
                t.addRow(data);
            }
        }
        tableSequenze.setModel(t);
    }

    public void popolaAttivita(){
        String col[]={"Id","Descrizione","Precedenza","Nome Sequenza","Inizio","Fine Prevista","Fine","Costo"};
        CustomTable t = new CustomTable(col,0);

        GruppoProgetti progetti = new GruppoProgetti();
        for(Progetto progetto:progetti.getStato()){
            for(Sequenza sequenza:progetto.getStato()){
                for(Attivita attivita:sequenza.getStato()){

                    int id              = attivita.getId();
                    String descrizione  = attivita.getDescrizione();
                    int precedenza      = attivita.getPrecedenza();
                    String nomesequenza = attivita.getNomesequenza();
                    String inizio       = attivita.getDatainizio();
                    String fineprevista = attivita.getDatafineprevista();
                    String fine         = attivita.getDatafine();
                    double costo        = attivita.getCosto();

                    Object[] data = {id,descrizione,precedenza,nomesequenza,inizio,fineprevista,fine,costo};
                    t.addRow(data);
                }
            }
        }
        tableAttivita.setModel(t);
    }

    public void popolaAppuntamenti(){
        String col[]={"Tipo","Data","Ora","Luogo","Verbale"};

        CustomTable t = new CustomTable(col,0);
        GruppoAppuntamenti appuntamenti = new GruppoAppuntamenti();

        for(Incontro appoggio: appuntamenti.getStato()){

            String tipo     = appoggio.getTipo();
            String data     = appoggio.getData();
            String ora      = appoggio.getOra();
            String luogo    = appoggio.getLuogo();
            String verbale  = appoggio.getVerbale();

            Object[] dati = {tipo,data,ora,luogo,verbale};
            t.addRow(dati);
        }

        tableApuntamenti.setModel(t);

    }

    public void glMode(){
        tableProgetti.setEnabled(false);
        buttonCreaProgetto.setEnabled(false);
        fieldNomeProgetto.setEnabled(false);
        comboAnnoProgetto.setEnabled(false);
        comboMeseProgetto.setEnabled(false);
        comboGiornoProgetto.setEnabled(false);
        labelModificaProgetto.setVisible(false);
    }

    /* getter */
    public JPanel getPanelloPrincipale() {
        return panelloPrincipale;
    }

    public Progetto getParametriCreaProgetto(){
        Progetto p = new Progetto();
        p.setNome(fieldNomeProgetto.getText());
        p.setDeadline(comboAnnoProgetto.getSelectedItem().toString() + "-" + comboMeseProgetto.getSelectedItem().toString() + "-" + comboGiornoProgetto.getSelectedItem().toString());
        return p;
    }

    public Sequenza getParametriCreaSequenza(){
        Sequenza s = new Sequenza();
        s.setNome(fieldNomeSequenza.getText());
        s.setNomeprogetto(comboProgetti.getSelectedItem().toString());
        s.setFine("null");
        return s;
    }

    public Attivita getParametriCreaAttivita(){
        Attivita a = new Attivita();
        a.setDescrizione(fieldDescrizioneAttivtia.getText());
        a.setNomesequenza(comboSequenze.getSelectedItem().toString());
        a.setDatainizio(comboAnnoInizioAttivita.getSelectedItem().toString() + "-" + comboMeseInizioAttivita.getSelectedItem().toString() + "-" + comboGiornoInizoAttivita.getSelectedItem().toString());
        a.setDatafineprevista(comboAnnoFineAttivita.getSelectedItem().toString() + "-" + comboMeseFineAttivita.getSelectedItem().toString() + "-" + comboGiornoFineAttivita.getSelectedItem().toString());
        return a;
    }

    public Incontro getParametriCreaIncontro(){
        Incontro i = new Incontro();
        i.setTipo(comboTipoAppuntamento.getSelectedItem().toString());
        i.setLuogo(fieldLuogoAppuntamento.getText());
        i.setOra(comboOraAppuntamento.getSelectedItem().toString() + ":" + comboMinutiAppuntamento.getSelectedItem().toString() + ":00");
        i.setData(comboAnnoAppuntamento.getSelectedItem().toString() + "-" + comboMeseAppuntamento.getSelectedItem().toString() + "-" + comboGiornoAppuntamento.getSelectedItem().toString());
        return i;
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

    public JButton getButtonCreaSequenza() {
        return buttonCreaSequenza;
    }

    public JTextField getFieldNomeSequenza_modifica() {
        return fieldNomeSequenza_modifica;
    }

    public JButton getButtonEliminaSequenza() {
        return buttonEliminaSequenza;
    }

    public JButton getButtonModificaSequenza() {
        return buttonModificaSequenza;
    }

    public JTextField getFieldNomeSequenza() {
        return fieldNomeSequenza;
    }

    public JComboBox getComboGiornoFineSequenza() {
        return comboGiornoFineSequenza;
    }

    public JComboBox getComboMeseFineSequenza() {
        return comboMeseFineSequenza;
    }

    public JComboBox getComboAnnoFineSequenza() {
        return comboAnnoFineSequenza;
    }

    public JComboBox getComboProgetti() {
        return comboProgetti;
    }

    public JTable getTableSequenze() {
        return tableSequenze;
    }

    public JTabbedPane getTabbedPanePrincipale() {
        return tabbedPanePrincipale;
    }

    public JPanel getPanelSequenze() {
        return panelSequenze;
    }

    public JPanel getPanelAttivita() {
        return panelAttivita;
    }

    public JPanel getPanelAppuntamenti() {
        return panelAppuntamenti;
    }

    public JPanel getPanelOrdini() {
        return panelOrdini;
    }

    public JPanel getPanelSinistro() {
        return panelSinistro;
    }

    public JPanel getPanelDestro() {
        return panelDestro;
    }

    public JScrollPane getScrollSequenze() {
        return scrollSequenze;
    }

    public JTabbedPane getTabSequenze() {
        return tabSequenze;
    }

    public JTabbedPane getTabAttivita() {
        return tabAttivita;
    }

    public JTabbedPane getTabbedPane3() {
        return tabbedPane3;
    }

    public JTextField getFieldCostoAttivita() {
        return fieldCostoAttivita;
    }

    public JTextField getFieldDescrizioneAttivtia() {
        return fieldDescrizioneAttivtia;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getFieldPrecedenzaAttivita() {
        return fieldPrecedenzaAttivita;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JButton getButtonCreaAttivita() {
        return buttonCreaAttivita;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public JTextField getFieldCostoAttivta_modifica() {
        return fieldCostoAttivta_modifica;
    }

    public JTextField getTextField9() {
        return textField9;
    }

    public JTextField getTextField10() {
        return textField10;
    }

    public JTextField getFieldPrecedenzaAttivita_modifica() {
        return fieldPrecedenzaAttivita_modifica;
    }

    public JTextField getFieldDescrizioneAttivita_modifica() {
        return fieldDescrizioneAttivita_modifica;
    }

    public JButton getButtonModificaAttivita() {
        return buttonModificaAttivita;
    }

    public JButton getButtonEliminaAttivita() {
        return buttonEliminaAttivita;
    }

    public JTextField getTextField13() {
        return textField13;
    }

    public JTextField getTextField14() {
        return textField14;
    }

    public JTextField getFieldLuogoAppuntamento() {
        return fieldLuogoAppuntamento;
    }

    public JTextField getTextField16() {
        return textField16;
    }

    public JTextField getTextField17() {
        return textField17;
    }

    public JButton getButtonCreaAppuntamento() {
        return buttonCreaAppuntamento;
    }

    public JTextField getTextField18() {
        return textField18;
    }

    public JTextField getTextField19() {
        return textField19;
    }

    public JTextField getFieldLuogoAppuntamento_modifica() {
        return fieldLuogoAppuntamento_modifica;
    }

    public JTextField getTextField21() {
        return textField21;
    }

    public JTextField getTextField22() {
        return textField22;
    }

    public JButton getButtonModifcaAppuntamento() {
        return buttonModifcaAppuntamento;
    }

    public JButton getButonCancellaAppuntamento() {
        return butonCancellaAppuntamento;
    }

    public JPanel getPanelProgetti() {
        return panelProgetti;
    }

    public JPanel getPanelUtenti() {
        return panelUtenti;
    }

    public JScrollPane getScrollProgetti() {
        return scrollProgetti;
    }

    public JTable getTableAttivita() {
        return tableAttivita;
    }

    public JScrollPane getScrollAttività() {
        return scrollAttività;
    }

    public JLabel getLabelModificaAttivita() {
        return labelModificaAttivita;
    }

    public JLabel getLabelModificaAppuntamento() {
        return labelModificaAppuntamento;
    }

    public JLabel getLabelNomeProgetto() {
        return labelNomeProgetto;
    }

    public JLabel getLabelFineProgetto() {
        return labelFineProgetto;
    }

    public JLabel getLabelAnno() {
        return labelAnno;
    }

    public JLabel getLableMese() {
        return lableMese;
    }

    public JLabel getLableGiorno() {
        return lableGiorno;
    }

    public JLabel getLabelNomeProgetto_modifica() {
        return labelNomeProgetto_modifica;
    }

    public JLabel getLabelDataFine_modifica() {
        return labelDataFine_modifica;
    }

    public JComboBox getComboGiornoFineSequenza_modifica() {
        return comboGiornoFineSequenza_modifica;
    }

    public JComboBox getComboMeseFineSequenza_modifica() {
        return comboMeseFineSequenza_modifica;
    }

    public JComboBox getComboAnnoFineSequenza_modifica() {
        return comboAnnoFineSequenza_modifica;
    }

    public JLabel getLableNomeSequenza() {
        return lableNomeSequenza;
    }

    public JButton getButtonSalvaModificheSequenza() {
        return buttonSalvaModificheSequenza;
    }

    public JComboBox getComboProgetti_modifica() {
        return comboProgetti_modifica;
    }

    public JComboBox getComboGiornoInizioSequenza() {
        return comboGiornoInizioSequenza;
    }

    public JComboBox getComboMeseInizioSequenza() {
        return comboMeseInizioSequenza;
    }

    public JComboBox getComboAnnoInizioSequenza() {
        return comboAnnoInizioSequenza;
    }

    public JComboBox getComboGiornoInizioSequenza_modifica() {
        return comboGiornoInizioSequenza_modifica;
    }

    public JComboBox getComboMeseInizioSequenza_modifica() {
        return comboMeseInizioSequenza_modifica;
    }

    public JComboBox getComboAnnoInizioSequenza_modifica() {
        return comboAnnoInizioSequenza_modifica;
    }

    public JLabel getLabelModificaSequenza() {
        return labelModificaSequenza;


    }

    public JComboBox getComboGiornoInizoAttivita() {
        return comboGiornoInizoAttivita;
    }

    public JComboBox getComboMeseInizioAttivita() {
        return comboMeseInizioAttivita;
    }

    public JComboBox getComboAnnoInizioAttivita() {
        return comboAnnoInizioAttivita;
    }

    public JComboBox getComboGiornoFineAttivita() {
        return comboGiornoFineAttivita;
    }

    public JComboBox getComboMeseFineAttivita() {
        return comboMeseFineAttivita;
    }

    public JComboBox getComboAnnoFineAttivita() {
        return comboAnnoFineAttivita;
    }

    public JComboBox getComboGiornoInizioAttivita_modifica() {
        return comboGiornoInizioAttivita_modifica;
    }

    public JComboBox getComboMeseInizioAttivita_modifica() {
        return comboMeseInizioAttivita_modifica;
    }

    public JComboBox getComboAnnoInizioAttivita_modifica() {
        return comboAnnoInizioAttivita_modifica;
    }

    public JComboBox getComboGiornoFineAttivita_modifica() {
        return comboGiornoFineAttivita_modifica;
    }

    public JComboBox getComboMeseFineAttivita_modifica() {
        return comboMeseFineAttivita_modifica;
    }

    public JComboBox getComboAnnoFineAttivita_modifica() {
        return comboAnnoFineAttivita_modifica;
    }

    public JButton getButtonSalvaModificheAttivita() {
        return buttonSalvaModificheAttivita;
    }

    public JComboBox getComboSequenze() {
        return comboSequenze;
    }

    public JComboBox getComboSequenze_modifica() {
        return comboSequenze_modifica;
    }

    public JComboBox getComboTipoAppuntamento() {
        return comboTipoAppuntamento;
    }

    public JTable getTableApuntamenti() {
        return tableApuntamenti;
    }

    public JComboBox getComboGiornoAppuntamento() {
        return comboGiornoAppuntamento;
    }

    public JComboBox getComboMeseAppuntamento() {
        return comboMeseAppuntamento;
    }

    public JComboBox getComboAnnoAppuntamento() {
        return comboAnnoAppuntamento;
    }

    public JComboBox getComboOraAppuntamento() {
        return comboOraAppuntamento;
    }

    public JComboBox getComboMinutiAppuntamento() {
        return comboMinutiAppuntamento;
    }

    public JButton getButtonSalvaAppuntamento() {
        return buttonSalvaAppuntamento;
    }

    public JComboBox getComboTipoAppuntamento_modifica() {
        return comboTipoAppuntamento_modifica;
    }

    public JTextArea getAreaVerbaleAppuntamento() {
        return areaVerbaleAppuntamento;
    }

    public JComboBox getComboGiornoAppuntamento_modifica() {
        return comboGiornoAppuntamento_modifica;
    }

    public JComboBox getComboMeseAppuntamento_modifica() {
        return comboMeseAppuntamento_modifica;
    }

    public JComboBox getComboAnnoAppuntamento_modifca() {
        return comboAnnoAppuntamento_modifca;
    }

    public JComboBox getComboOraAppuntamento_modifica() {
        return comboOraAppuntamento_modifica;
    }

    public JComboBox getComboMinutiAppuntamento_modifica() {
        return comboMinutiAppuntamento_modifica;
    }

    public JRadioButton getInvitaTuttiRadioButton() {
        return invitaTuttiRadioButton;
    }

    public JRadioButton getInvitaDaUnaSequenzaRadioButton() {
        return invitaDaUnaSequenzaRadioButton;
    }

    public JButton getButtonInvitati() {
        return buttonInvitati;
    }

    public JComboBox getComboSequenzeAppuntamento() {
        return comboSequenzeAppuntamento;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable2() {
        return table2;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JTable getTable3() {
        return table3;
    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public JRadioButton getDirettivoRadioButton() {
        return direttivoRadioButton;
    }


}
