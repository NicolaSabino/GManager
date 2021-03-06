package View;

import Model.*;
import Model.Gruppi.Gruppo;
import Model.Gruppi.GruppoAppuntamenti;
import Model.Gruppi.GruppoOrdini;
import Model.Gruppi.GruppoProgetti;
import Model.Incontro;
import Model.Progetto;
import Model.Sequenza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


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
    private JTabbedPane tabAppuntamenti;
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
    private JButton buttonModificaAppuntamento;
    private JButton buttonEliminaAppuntamento;
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
    private JTable tableAppuntamenti;
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
    private JButton buttonMostraInvitati;
    private JComboBox comboSequenzeAppuntamento;
    private JTable tableOrdini;
    private JTable tableVoti;
    private JButton buttonApprova;
    private JButton buttonNonApprova;
    private JTable tableUtenti;
    private JTabbedPane tabUtenti;
    private JRadioButton direttivoRadioButton;
    private JButton modificaUtenteButton;
    private JTextField textField1;
    private JTextField fieldMatricolaUtente_modifica;
    private JComboBox comboBox1;
    //private JButton buttonCreaUtente;
    private JButton buttonCreaUtente;
    private JTextField fieldMatricolaUtente;
    private JComboBox comboRuoloUtente;
    private JTextField textField2;
    private JButton buttonModificaUtente;
    private JComboBox comboRuoloUtente_modifica;
    private JTextField fieldNomeUtente;
    private JTextField fieldNomeUtente_modifica;
    private JTextField fieldCognomeUtente_modifica;
    private JTextField fieldCognomeUtente;
    private JTextField fieldMailUtente_modifica;
    private JTextField fieldMailUtente;
    private JTextField fieldTelefonoUtente;
    private JTextField fieldTelefonoUtente_modifica;
    private JLabel labelModificaUtente;
    private JButton buttonSalvaModificheUtente;
    private JButton buttonEliminaUtente;
    private JButton buttonNascondiInvitati;
    private JTable tableInvitati;
    private JScrollPane scrollTabbella;
    private JPanel panelInvitati;
    private JScrollPane scrollOrdini;
    private JLabel labelApprovaOrdini;
    private JPanel panelApprovazioni;
    private JPanel panelBottoni;
    private JButton rendiDefinitivoButton;
    private JButton rifiutatoDalRettoratoButton;
    private JButton eliminaOrdineButton;
    private JPanel panelIncarichi;
    private JTabbedPane tabIncarichi;
    private JScrollPane scrollUtenti_assegnazione;
    private JScrollPane scrollAttivita_assegnazione;
    private JButton assegnaButton;
    private JTable tableUtenti_assegnazione;
    private JTable tableAttivita_assegnazione;
    private JTable tableElencoAssegnazioni;
    private JButton eliminaIncaricoButton;
    private JLabel labelSelezionaUtente;
    private JLabel labelSelezionaAttività;
    private JLabel labelIncarichi;
    private JTable tableNonAssegnati;
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
        labelModificaAppuntamento.setText("<html><b>...Seleziona l'Appuntamento da modificare...</b></html>");
        labelModificaUtente.setText("<html><b>...Seleziona l'utente da modificare...</b></html>");
        labelApprovaOrdini.setText("<html><b>...Seleziona un ordine...</b></html>");
        labelSelezionaUtente.setText("<html><b>...Seleziona un utente...</b></html>");
        labelSelezionaAttività.setText("<html><b>...Seleziona un'attività...</b></html>");
        labelIncarichi.setText("<html><b>...Seleziona un incarico per eliminarlo...</b></html>");


        //popolo le tabelle
        popolaProgetti();
        popolaSequenze();
        popolaAttivita();
        popolaAppuntamenti();
        popolaUtenti();
        popolaOrdini();
        popolaAttivita_assegnazione();
        popolaUtenti_assegnazione();
        popolaElencoNonAssegnati();


        disabilitaComponenti(true,fieldNomeProgetto_modifica, comboGiornoProgetto_modifica, comboMeseProgetto_modifica, comboAnnoProgetto_modifica);
        disabilitaComponenti(true,fieldNomeSequenza_modifica,comboProgetti_modifica);
        disabilitaComponenti(true,fieldDescrizioneAttivita_modifica,fieldPrecedenzaAttivita_modifica,fieldCostoAttivta_modifica,
                comboSequenze_modifica,comboGiornoInizioAttivita_modifica,comboMeseInizioAttivita_modifica,comboAnnoInizioAttivita_modifica,
                comboGiornoFineAttivita_modifica,comboMeseFineAttivita_modifica,comboAnnoFineAttivita_modifica);

        disabilitaComponenti(true,comboTipoAppuntamento_modifica,fieldLuogoAppuntamento_modifica,comboGiornoAppuntamento_modifica,comboMeseAppuntamento_modifica,
                                comboAnnoAppuntamento_modifca,comboOraAppuntamento_modifica,comboMinutiAppuntamento_modifica,areaVerbaleAppuntamento);


        buttonSalvaModificheProgetto.setVisible(false);
        buttonEliminaProgetto.setEnabled(false);
        buttonModificaProgetto.setEnabled(false);



        buttonSalvaModificheSequenza.setVisible(false);
        buttonEliminaSequenza.setEnabled(false);
        buttonModificaSequenza.setEnabled(false);



        buttonSalvaModificheAttivita.setVisible(false);
        buttonEliminaAttivita.setEnabled(false);
        buttonModificaAttivita.setEnabled(false);

        invitaTuttiRadioButton.setSelected(true);
        buttonModificaAppuntamento.setEnabled(false);
        buttonSalvaAppuntamento.setVisible(false);
        buttonEliminaAppuntamento.setEnabled(false);

        disabilitaComponenti(true,fieldMatricolaUtente_modifica,fieldNomeUtente_modifica,fieldCognomeUtente_modifica,
                comboRuoloUtente_modifica,fieldMailUtente_modifica,fieldTelefonoUtente_modifica);

        buttonModificaUtente.setEnabled(false);
        buttonSalvaModificheUtente.setVisible(false);
        buttonEliminaUtente.setEnabled(false);
        buttonMostraInvitati.setEnabled(false);
        buttonNascondiInvitati.setVisible(false);
        panelInvitati.setVisible(false);





        //limitazioni
        StaticMethod.textFieldLimitLength(fieldDescrizioneAttivtia,80);
        StaticMethod.textFieldLimitLength(fieldCostoAttivta_modifica,80);
        StaticMethod.textFieldLimitOnlyInt(fieldPrecedenzaAttivita,4);
        StaticMethod.textFieldLimitLength(fieldPrecedenzaAttivita_modifica,4);
        StaticMethod.textFieldLimitOnlyDouble(fieldCostoAttivita);
        StaticMethod.textFieldLimitOnlyDouble(fieldCostoAttivta_modifica);
        StaticMethod.textFieldLimitOnlyInt(fieldTelefonoUtente,14);
        StaticMethod.textFieldLimitOnlyInt(fieldTelefonoUtente_modifica,14);
        StaticMethod.textFieldLimitOnlyInt(fieldMatricolaUtente, 7);
        StaticMethod.textFieldLimitOnlyInt(fieldMatricolaUtente_modifica, 7);
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
        StaticMethod.popolaComboSequenze(comboSequenzeAppuntamento);
        comboProgetti_modifica.setEnabled(false);


        listnerSelezioneIncontro1();
        listnerSelezioneIncontro2();
        listnerSelezioneIncontro3();

        labelApprovaOrdini.setVisible(true);
        panelApprovazioni.setVisible(false);
        panelBottoni.setVisible(false);

    }

    /**
     *
     * @param b
     * @param elementi
     */
    public void disabilitaComponenti(boolean b,Component... elementi){
        for(Component appoggio:elementi){
            appoggio.setEnabled(!b);
        }
    }

    /**
     * Metodo che riempie la tabella dei progetti
     *
     */
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

    /**
     * Metodo che riempie la tabella delle sequenze
     *
     */
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

    /**
     * Metodo che riempie la tabella delle sequenze
     *
     */

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

    /**
     * Metodo che riempie la tabella degli utenti
     *
     */
    public void popolaUtenti(){
        String col[]={"Matricola" , "Nome" , "Cognome" , "Ruolo" , "indirizzo e-mail" , "telefono"};
        CustomTable t = new CustomTable(col,0);
        Gruppo utenti = new Gruppo();
        utenti.createFrom("utenti");

        for(Utente appoggio:utenti.getElenco()){
            String matricola = appoggio.getMatricola();
            String nome = appoggio.getNome();
            String cognome = appoggio.getCognome();
            String mail = appoggio.getMail();
            String telefono = appoggio.getTelefono();
            String ruolo = appoggio.getRuolo();

            Object[] data = {matricola, nome, cognome, ruolo, mail, telefono};
            t.addRow(data);
        }
        tableUtenti.setModel(t);


    }

    /**
     * Metodo che riempie la tabella degli invitati
     *
     * @param gruppo gruppo di utenti
     */
    public void popolaInvitati(Gruppo gruppo){
        String col[]={"Matricola" , "Nome" , "Cognome" , "Ruolo" , "indirizzo e-mail" , "telefono"};
        CustomTable t = new CustomTable(col,0);

        for(Utente appoggio:gruppo.getElenco()){
            String matricola = appoggio.getMatricola();
            String nome = appoggio.getNome();
            String cognome = appoggio.getCognome();
            String mail = appoggio.getMail();
            String telefono = appoggio.getTelefono();
            String ruolo = appoggio.getRuolo();
            Object[] data = {matricola, nome, cognome, ruolo, mail, telefono};
            t.addRow(data);
        }
        tableInvitati.setModel(t);
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

    /**
     * Metodo che riempie la tabella degli appuntamenti
     *
     */

    public void popolaAppuntamenti(){
        String col[]={"Id","Tipo","Data","Ora","Luogo","Verbale"};

        CustomTable t = new CustomTable(col,0);
        GruppoAppuntamenti appuntamenti = new GruppoAppuntamenti();

        for(Incontro appoggio: appuntamenti.getStato()){
            int    id       = appoggio.getId();
            String tipo     = appoggio.getTipo();
            String data     = appoggio.getData();
            String ora      = appoggio.getOra();
            String luogo    = appoggio.getLuogo();
            String verbale  = appoggio.getVerbale();

            Object[] dati = {id,tipo,data,ora,luogo,verbale};
            t.addRow(dati);
        }

        tableAppuntamenti.setModel(t);

    }

    /**
     * Metodo che riempie la tabella degli ordini
     *
     */

    public void popolaOrdini(){

        String col[] = {"Id","Descrizione","Quantità","Prezzo","Data","Attività","Approvazione",};
        GruppoOrdini ordini = new GruppoOrdini();
        CustomTable tableModel = new CustomTable(col, 0);

        //trasformo gli incarichi per generare il modello per la tabella
        for(Ordine appoggio:ordini.getStato()) {
            int id =appoggio.getId();
            String descrizione = appoggio.getDescrizione();
            int quantita = appoggio.getQuantita();
            float prezzo = appoggio.getPrezzo();
            String data = appoggio.getDataOrdine();
            int attivita = appoggio.getAttivita();
            String approvazione;

            if (appoggio.getApprovazione().compareTo("Approvato")==0) {
                approvazione="<html><font color=green>Approvato</font></html>";
            } else if(appoggio.getApprovazione().compareTo("Da Approvare")==0){
                approvazione="<html><font color=orange>Da Approvare</font></html>";
            }else if(appoggio.getApprovazione().compareTo("Non Approvato")==0){
                approvazione="<html><font color=red>NonApprovato</font></html>";
            }else{
                approvazione="null";
            }



            Object[] objects = {id,descrizione,quantita,prezzo,data,attivita,approvazione};

            tableModel.addRow(objects);
        }

        tableOrdini.setModel(tableModel);
    }

    /**
     * Metodo che riempie la tabella delle approvazioni con
     * nome, cognome , matricola e voto per ogni utente che ha
     * il diritto di votazione
     *
     * @param riga
     */
    public void popolaTabellaApprovazioni(int riga){
        int id= (Integer) tableOrdini.getValueAt(riga,0);
        Ordine ordine = new Ordine(id);

        String col[]={"Nome" , "Cognome" , "Matricola" , "Voto"};
        CustomTable tableModel = new CustomTable(col,0);

        for(Map appoggio :ordine.getVotazione()){

            Utente utente= new Utente(appoggio.get("matricola").toString());
            String v="";

            if( appoggio.get("voto").toString().equalsIgnoreCase("Da Approvare")){
                v="<html><font color=orange>In attesa</font></html>";
            }else if(appoggio.get("voto").toString().equalsIgnoreCase("Approvato")){
                v="<html><font color=green>Approvato</font></html>";
            }else if(appoggio.get("voto").toString().equalsIgnoreCase("Non Approvato")){
                v="<html><font color=red>Non Approvato</font></html>";
            }

            Object[] objects={utente.getNome(),utente.getCognome(),utente.getMatricola(),v};

            tableModel.addRow(objects);
        }
        tableVoti.setModel(tableModel);
    }

    public void popolaUtenti_assegnazione(){
        String col[]={"Matricola" , "Nome" , "Cognome"};
        Gruppo g = new Gruppo();
        g.createFrom("utenti");
        CustomTable tableModel= new CustomTable(col,0);

        for (Utente appoggio:g.getElenco()){

            String matricola    = appoggio.getMatricola();
            String nome         = appoggio.getNome();
            String cognome      = appoggio.getCognome();

            Object[] objects={matricola,nome,cognome};
            tableModel.addRow(objects);
        }
        tableUtenti_assegnazione.setModel(tableModel);
    }

    public void popolaAttivita_assegnazione(){
        String col[]={"id" ,"descrizione","Precedenza" ,"NomeSequenza", "Data inizio" , "Data fine prevista"};
        CustomTable table = new CustomTable(col,0);
        GruppoProgetti g = new GruppoProgetti();

        for(Progetto progetto:g.getStato()){
            for(Sequenza sequenza:progetto.getStato()){
                for(Attivita attivita:sequenza.getStato()){

                    int id              = attivita.getId();
                    String descrizione  = attivita.getDescrizione();
                    int precedenza      = attivita.getPrecedenza();
                    String nomesequenza = attivita.getNomesequenza();
                    String inizio       = attivita.getDatainizio();
                    String fine         = attivita.getDatafineprevista();

                    Object[] data = {id,descrizione,precedenza,nomesequenza,inizio,fine};
                    table.addRow(data);
                }
            }
        }

        tableAttivita_assegnazione.setModel(table);
    }

    public void popolaElencoNonAssegnati(){

        String col[]={"Matricola" , "Nome" , "Cognome" , "indirizzo e-mail" , "telefono"};
        CustomTable t = new CustomTable(col,0);
        Gruppo g = new Gruppo();
        g.createFrom("non assegnati");

        for(Utente appoggio:g.getElenco()){
            String matricola = appoggio.getMatricola();
            String nome = appoggio.getNome();
            String cognome = appoggio.getCognome();
            String mail = appoggio.getMail();
            String telefono = appoggio.getTelefono();

            Object[] data = {matricola, nome, cognome, mail, telefono};
            t.addRow(data);
        }
        tableNonAssegnati.setModel(t);

    }



    public void listnerSelezioneIncontro1(){
        invitaTuttiRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invitaDaUnaSequenzaRadioButton.setSelected(false);
                direttivoRadioButton.setSelected(false);
            }
        });
    }

    public void listnerSelezioneIncontro2(){
        invitaDaUnaSequenzaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invitaTuttiRadioButton.setSelected(false);
                direttivoRadioButton.setSelected(false);
            }
        });

    }

    public void listnerSelezioneIncontro3(){
        direttivoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invitaTuttiRadioButton.setSelected(false);
                invitaDaUnaSequenzaRadioButton.setSelected(false);
            }
        });
    }


    public void glMode(){
        tableProgetti.setEnabled(false);
        tableSequenze.setEnabled(false);
        buttonCreaProgetto.setEnabled(false);
        fieldNomeProgetto.setEnabled(false);
        fieldNomeSequenza.setEnabled(false);
        comboProgetti.setEnabled(false);
        comboAnnoProgetto.setEnabled(false);
        comboMeseProgetto.setEnabled(false);
        comboGiornoProgetto.setEnabled(false);
        labelModificaProgetto.setVisible(false);
        rendiDefinitivoButton.setVisible(false);
        rifiutatoDalRettoratoButton.setVisible(false);
        buttonCreaSequenza.setEnabled(false);
        labelModificaSequenza.setVisible(false);
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

    public Utente getParametriCreaUtente(){
        Utente u = new Utente();
        u.setMatricola(fieldMatricolaUtente.getText());
        u.setNome(fieldNomeUtente.getText());
        u.setCognome(fieldCognomeUtente.getText());
        u.setRuolo(comboRuoloUtente.getSelectedItem().toString());
        u.setTelefono(fieldTelefonoUtente.getText());
        u.setMail(fieldMailUtente.getText());

        return u;
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

    public JTabbedPane getTabAppuntamenti() {
        return tabAppuntamenti;
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

    public JButton getButtonModificaAppuntamento() {
        return buttonModificaAppuntamento;
    }

    public JButton getButtonEliminaAppuntamento() {
        return buttonEliminaAppuntamento;
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

    public JTable getTableAppuntamenti() {
        return tableAppuntamenti;
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

    public JComboBox getComboSequenzeAppuntamento() {
        return comboSequenzeAppuntamento;
    }

    public JTable getTableOrdini() {
        return tableOrdini;
    }

    public JTable getTableVoti() {
        return tableVoti;
    }

    public JButton getButtonApprova() {
        return buttonApprova;
    }

    public JButton getButtonNonApprova() {
        return buttonNonApprova;
    }

    public JTable getTableUtenti() {
        return tableUtenti;
    }

    public JTabbedPane getTabUtenti() {
        return tabUtenti;
    }

    public JRadioButton getDirettivoRadioButton() {
        return direttivoRadioButton;
    }

    public JButton getModificaUtenteButton() {
        return modificaUtenteButton;
    }

    public JTextField getFieldMatricolaUtente_modifica() {
        return fieldMatricolaUtente_modifica;
    }

    public JButton getButtonCreaUtente() {
        return buttonCreaUtente;
    }

    public JTextField getFieldMatricolaUtente() {
        return fieldMatricolaUtente;
    }

    public JComboBox getComboRuoloUtente() {
        return comboRuoloUtente;
    }

    public JComboBox getComboRuoloUtente_modifica() {
        return comboRuoloUtente_modifica;
    }

    public JButton getButtonModificaUtente() {
        return buttonModificaUtente;
    }

    public JTextField getFieldNomeUtente() {
        return fieldNomeUtente;
    }

    public JTextField getFieldNomeUtente_modifica() {
        return fieldNomeUtente_modifica;
    }

    public JTextField getFieldCognomeUtente_modifica() {
        return fieldCognomeUtente_modifica;
    }

    public JTextField getFieldCognomeUtente() {
        return fieldCognomeUtente;
    }

    public JTextField getFieldMailUtente_modifica() {
        return fieldMailUtente_modifica;
    }

    public JTextField getFieldMailUtente() {
        return fieldMailUtente;
    }

    public JTextField getFieldTelefonoUtente_modifica() {
        return fieldTelefonoUtente_modifica;
    }

    public JTextField getFieldTelefonoUtente() {
        return fieldTelefonoUtente;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JLabel getLabelModificaUtente() {
        return labelModificaUtente;
    }

    public JButton getButtonSalvaModificheUtente() {
        return buttonSalvaModificheUtente;
    }

    public JButton getButtonEliminaUtente() {
        return buttonEliminaUtente;
    }

    public JButton getButtonNascondiInvitati() {
        return buttonNascondiInvitati;
    }

    public JTable getTableInvitati() {
        return tableInvitati;
    }

    public JScrollPane getScrollTabbella() {
        return scrollTabbella;
    }

    public JPanel getPanelInvitati() {
        return panelInvitati;
    }

    public JButton getButtonMostraInvitati() {
        return buttonMostraInvitati;
    }

    public JScrollPane getScrollOrdini() {
        return scrollOrdini;
    }

    public JLabel getLabelApprovaOrdini() {
        return labelApprovaOrdini;
    }

    public JPanel getPanelApprovazioni() {
        return panelApprovazioni;
    }

    public JPanel getPanelBottoni() {
        return panelBottoni;
    }

    public JButton getRendiDefinitivoButton() {
        return rendiDefinitivoButton;
    }

    public JButton getRifiutatoDalRettoratoButton() {
        return rifiutatoDalRettoratoButton;
    }

    public JButton getEliminaOrdineButton() {
        return eliminaOrdineButton;
    }

    public JPanel getPanelIncarichi() {
        return panelIncarichi;
    }

    public JTabbedPane getTabIncarichi() {
        return tabIncarichi;
    }

    public JScrollPane getScrollUtenti_assegnazione() {
        return scrollUtenti_assegnazione;
    }

    public JScrollPane getScrollAttivita_assegnazione() {
        return scrollAttivita_assegnazione;
    }

    public JButton getAssegnaButton() {
        return assegnaButton;
    }

    public JTable getTableUtenti_assegnazione() {
        return tableUtenti_assegnazione;
    }

    public JTable getTableAttivita_assegnazione() {
        return tableAttivita_assegnazione;
    }

    public JTable getTableElencoAssegnazioni() {
        return tableElencoAssegnazioni;
    }

    public JButton getEliminaIncaricoButton() {
        return eliminaIncaricoButton;
    }

    public JLabel getLabelSelezionaUtente() {
        return labelSelezionaUtente;
    }

    public JLabel getLabelSelezionaAttività() {
        return labelSelezionaAttività;
    }

    public JLabel getLabelIncarichi() {
        return labelIncarichi;
    }
}
