package Controller;

import Model.*;
import Model.Gruppi.Gruppo;
import Model.Gruppi.GruppoProgetti;
import Model.Gruppi.GruppoSequenze;
import View.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller della schermata gestisci
 *
 */
public class GestisciController {
    private RootFrame rootFrame;
    private Gestisci gestisci;
    private Utente utilizzatore;
    private HomeController homeController;
    private boolean statoListener;
    private OrdiniController ordiniController;


    /**
     * Costruttore
     *
     * @param rootFrame
     * @param utente
     * @param home
     */
    public GestisciController(RootFrame rootFrame, Utente utente,HomeController home) {
        this.rootFrame=rootFrame;
        this.gestisci= new Gestisci();
        this.utilizzatore=utente;
        this.homeController=home;
        this.statoListener=true; //da qui in poi i listener di selezione sulle tabelle sono abilitati

        popolaElencoIncarichi();

        //verranno disabilitati quando si entra in modalità modifica

        //disabilito alcuni campi per i group leader
        if(utilizzatore.getRuolo().equals("GL")) {
            gestisci.glMode();
        }else{
            listenerSelezioneProgetto();
            listenerSelezioneSequenza();
            listenerRendiDefinitivo();
            listenerRifiutatoDalRettorato();
            listenerEliminaOrdine();
        }

        //listener
        listenerCreaProgetto();
        listenerCreaSequenza();
        listenerCreaAttivita();
        listenerCreaAppuntamento();

        listenerCreaUtente();


        listenerSelezioneAttivita();
        listenerSelezionaAppuntamento();
        listenerSelezionaUtente();
        listenerSelezionaOrdine();
        listenerSelezionaAttivita_assegnazione();
        listenerSelezionaUtente_assegnazione();

        listenerModificaProgetto();
        listenerModificaSequenza();
        listenerModificaAttivita();
        listenerModificaUtente();
        listenerModificaAppuntamento();

        listenerEliminaProgetto();
        listenerEliminaSequenza();
        listenerEliminaAttivita();
        listenerEliminaUtente();
        listenerEliminaAppuntamento();
        listenerEliminaIncarico();

        listenerMostraInvitati();
        listenerNascondiInvitati();

        listenerAssegna();
        listenerSelezionaIncarico();

    }

    public void apriGestisci(){
        rootFrame.setMainScrollPane(gestisci.getPanelloPrincipale());
    }

    protected void listenerCreaProgetto(){
        JButton crea = gestisci.getButtonCreaProgetto();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaProgetto();
            }
        });
    }

    protected void listenerCreaSequenza(){
        JButton crea = gestisci.getButtonCreaSequenza();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaSequenza();
            }
        });
    }

    protected void listenerCreaAttivita(){
        JButton crea = gestisci.getButtonCreaAttivita();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    creaAttivita();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    protected void listenerCreaAppuntamento(){
        JButton crea = gestisci.getButtonCreaAppuntamento();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaAppuntamento();
            }
        });
    }

    protected void listenerCreaUtente(){
        JButton crea=gestisci.getButtonCreaUtente();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaUtente();
            }
        });
    }


    protected void listenerSelezioneProgetto(){
        JTable tabellaProgetti = gestisci.getTableProgetti();
        tabellaProgetti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener) {
                    popolaCampiModificaProgetto(tabellaProgetti.getSelectedRow());
                    gestisci.getLabelModificaProgetto().setVisible(false);
                    gestisci.getButtonModificaProgetto().setEnabled(true);
                    gestisci.getButtonEliminaProgetto().setEnabled(false);
                    gestisci.getTabProgetti().setSelectedIndex(1);
                }
            }

        });
    }

    protected void listenerSelezioneSequenza(){
        JTable tabellaSequenze = gestisci.getTableSequenze();
        tabellaSequenze.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener) {
                    popolaCampiModificaSequenza(tabellaSequenze.getSelectedRow());
                    gestisci.getLabelModificaSequenza().setVisible(false);
                    gestisci.getButtonModificaSequenza().setEnabled(true);
                    gestisci.getButtonEliminaSequenza().setEnabled(false);
                    gestisci.getTabSequenze().setSelectedIndex(1);
                }
            }
        });
    }

    protected void listenerSelezioneAttivita(){
        JTable tabellaAttivita = gestisci.getTableAttivita();
        tabellaAttivita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener) {
                    popolaCampimodificaAttivita(tabellaAttivita.getSelectedRow());
                    gestisci.getLabelModificaAttivita().setVisible(false);
                    gestisci.getButtonModificaAttivita().setEnabled(true);
                    gestisci.getButtonEliminaAttivita().setEnabled(false);
                    gestisci.getTabAttivita().setSelectedIndex(1);
                }
            }
        });
    }

    protected void listenerSelezionaUtente(){
        JTable tabellaUtenti = gestisci.getTableUtenti();
        tabellaUtenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener){
                    popolaCampimodificaUtente(tabellaUtenti.getSelectedRow());
                    gestisci.getLabelModificaUtente().setVisible(false);
                    gestisci.getButtonModificaUtente().setEnabled(true);
                    gestisci.getButtonEliminaUtente().setEnabled(false);
                    gestisci.getTabUtenti().setSelectedIndex(1);
                }
            }
        });
    }

    protected void listenerSelezionaAppuntamento(){
        JTable tabellaAppuntamenti = gestisci.getTableAppuntamenti();
        tabellaAppuntamenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener){
                    popolaCampiModificaAppuntamento(tabellaAppuntamenti.getSelectedRow());
                    gestisci.getLabelModificaAppuntamento().setVisible(false);
                    gestisci.getButtonModificaAppuntamento().setEnabled(true);
                    gestisci.getButtonEliminaAppuntamento().setEnabled(false);
                    gestisci.getTabAppuntamenti().setSelectedIndex(1);

                }
            }
        });
    }

    protected void listenerSelezionaOrdine(){
        JTable tabellaOrdini = gestisci.getTableOrdini();
        tabellaOrdini.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener){
                    gestisci.popolaTabellaApprovazioni(tabellaOrdini.getSelectedRow());
                    gestisci.getLabelApprovaOrdini().setVisible(false);
                    gestisci.getPanelApprovazioni().setVisible(true);
                    gestisci.getPanelBottoni().setVisible(true);
                    gestisci.getEliminaOrdineButton().setEnabled(true);

                    //creo il listener di approvazione e di non approvazione
                    listenerApprovaOrdine();
                    listenerNonApprovareOrdine();
                }
            }
        });
    }

    protected void listenerSelezionaUtente_assegnazione(){
        JTable tabella= gestisci.getTableUtenti_assegnazione();
        tabella.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gestisci.getLabelSelezionaUtente().setVisible(false);
            }
        });
    }

    protected void listenerSelezionaAttivita_assegnazione(){
        JTable tablla= gestisci.getTableAttivita_assegnazione();
        tablla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gestisci.getLabelSelezionaAttività().setVisible(false);
            }
        });
    }

    protected void listenerSelezionaIncarico(){
        JTable table= gestisci.getTableElencoAssegnazioni();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gestisci.getLabelIncarichi().setVisible(false);
            }
        });

    }


    protected void listenerModificaProgetto(){
        JButton modifica=gestisci.getButtonModificaProgetto();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificaProgetto();
                gestisci.getButtonEliminaProgetto().setEnabled(true);

                //disabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableProgetti().setRowSelectionAllowed(false);
                statoListener=false;
            }
        });
    }

    protected void listenerModificaSequenza(){
        JButton modifica=gestisci.getButtonModificaSequenza();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificaSequenza();
                gestisci.getButtonEliminaSequenza().setEnabled(true);

                //disabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableSequenze().setRowSelectionAllowed(false);
                statoListener=false;
            }
        });
    }

    protected void listenerModificaAttivita(){
        JButton modifica=gestisci.getButtonModificaAttivita();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificheAttività();
                gestisci.getButtonEliminaAttivita().setEnabled(true);

                //disabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableAttivita().setRowSelectionAllowed(false);
                statoListener=false;
            }
        });
    }

    protected void listenerModificaUtente(){
        JButton modifica = gestisci.getButtonModificaUtente();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificheUtente();
                gestisci.getButtonEliminaUtente().setEnabled(true);

                //disabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableUtenti().setRowSelectionAllowed(false);
                statoListener=false;
            }
        });
    }

    protected void listenerModificaAppuntamento(){
        JButton modifica = gestisci.getButtonModificaAppuntamento();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificheAppuntamento();
                gestisci.getButtonEliminaAppuntamento().setEnabled(true);

                //disabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableAppuntamenti().setRowSelectionAllowed(false);
                statoListener=false;
            }
        });
    }




    protected void listenerSalvaModificheProgeto(String nomevecchioprogetto){
        JButton salva = gestisci.getButtonSalvaModificheProgetto();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheProgetto(nomevecchioprogetto);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableProgetti().setRowSelectionAllowed(true);
                statoListener=true;
            }
        });
    }

    protected void listenerSalvaModificheSequenza(String nomevecchiasequenza){
        JButton salva = gestisci.getButtonSalvaModificheSequenza();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheSequenza(nomevecchiasequenza);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableSequenze().setRowSelectionAllowed(true);
                statoListener=true;
            }
        });
    }

    protected void listenerSalvaModificheAttivita(int id_attivita){
        JButton salva = gestisci.getButtonSalvaModificheAttivita();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheAttivita(id_attivita);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableAttivita().setRowSelectionAllowed(true);
                statoListener=true;
            }
        });
    }

    protected void listenerSalvaModificheUtente(String matricola){
        JButton salva = gestisci.getButtonSalvaModificheUtente();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheUtente(matricola);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableUtenti().setRowSelectionAllowed(true);
                statoListener=true;
            }
        });
    }

    protected void listenerSalvaModificheAppuntamento(int id_Appuntamento){
        JButton salva = gestisci.getButtonSalvaAppuntamento();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheAppuntamento(id_Appuntamento);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableAppuntamenti().setRowSelectionAllowed(true);
                statoListener=true;
            }
        });
    }




    protected void listenerEliminaProgetto(){
        JButton elimina=gestisci.getButtonEliminaProgetto();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaProgetto();
            }
        });
    }

    protected void listenerEliminaSequenza(){
        JButton elimina=gestisci.getButtonEliminaSequenza();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaSequenza();
            }
        });
    }

    protected void listenerEliminaAttivita(){
        JButton elimina=gestisci.getButtonEliminaAttivita();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaAttivita();
            }
        });
    }

    protected void listenerEliminaUtente(){
        JButton elimina=gestisci.getButtonEliminaUtente();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaUtente();
            }
        });
    }

    protected void listenerEliminaAppuntamento(){
        JButton elimina=gestisci.getButtonEliminaAppuntamento();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaAppuntamento();
            }
        });
    }

    protected void listenerEliminaOrdine(){
        JButton elimina=gestisci.getEliminaOrdineButton();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaOrdine();
            }
        });
    }

    protected void listenerEliminaIncarico(){
        JButton elimina=gestisci.getEliminaIncaricoButton();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminaIncarico();
                }catch (Exception ex){
                    gestisci.displayErrorMessage("Seleziona un elemento","Errore di eliminazione");
                }

            }
        });
    }



    protected void listenerMostraInvitati(){
        JButton mostra=gestisci.getButtonMostraInvitati();
        mostra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraInvitati();
            }
        });
    }

    protected void listenerNascondiInvitati(){
        JButton nascondi=gestisci.getButtonNascondiInvitati();
        nascondi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nascondiInvitati();
            }
        });
    }

    protected void listenerApprovaOrdine(){
        JTable tabellaOrdini=gestisci.getTableOrdini();
        JButton approva=gestisci.getButtonApprova();
        approva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaVotazioneOrdine(tabellaOrdini.getSelectedRow(),"Approvato");
            }
        });
    }

    protected void listenerNonApprovareOrdine(){
        JTable tabellaOrdini=gestisci.getTableOrdini();
        JButton nonApprova=gestisci.getButtonNonApprova();
        nonApprova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaVotazioneOrdine(tabellaOrdini.getSelectedRow(),"Non Approvato");
            }
        });
    }

    protected void listenerRendiDefinitivo(){
        JButton rendiDefinitivo=gestisci.getRendiDefinitivoButton();
        JTable tabellaVoti=gestisci.getTableVoti();
        rendiDefinitivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rendiOrdineDefinitivo(tabellaVoti);
            }
        });
    }

    protected void listenerRifiutatoDalRettorato(){
        JButton rifiuto =gestisci.getRifiutatoDalRettoratoButton();
        rifiuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rifiutoRettorato();
            }
        });

    }

    protected void listenerAssegna(){
        JButton assegna=gestisci.getAssegnaButton();
        assegna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    assegna();
                }catch (Exception ex){
                    gestisci.displayErrorMessage("Devi selezionare una coppia utente attività","Errore");
                }
            }
        });
    }



    protected void creaProgetto(){

        Progetto p = gestisci.getParametriCreaProgetto();
        GruppoProgetti elenco = new GruppoProgetti();
        int errore=0;



        for(Progetto appoggio:elenco.getStato()){
            if(appoggio.getNome().compareToIgnoreCase(p.getNome())==0){

                errore++;
            }
        }

        if(errore!=0){
            JOptionPane.showMessageDialog(new JFrame("errore"),p.getNome() + " é gia presente nel programma!");
        }else{
            p.insertIntoSQL();

            //inserisco un nuovo messaggio
            MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
            messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                    " ha creato un nuovo progetto: " + p.getNome());
            messaggioBroadcast.setTipo("AUTO");
            messaggioBroadcast.setMittente("AUTO");
            messaggioBroadcast.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

            //aggiorno l'elenco dei progetti
            gestisci.popolaProgetti();

            //ripulisco crea
            gestisci.getFieldNomeProgetto().setText("");
            gestisci.getComboAnnoProgetto().setSelectedIndex(0);
            gestisci.getComboMeseProgetto().setSelectedIndex(0);
            gestisci.getComboGiornoProgetto().setSelectedIndex(0);

            //aggiorno il pannello gestisci sequenze
            StaticMethod.popolaComboProgetti(gestisci.getComboProgetti());
            StaticMethod.popolaComboProgetti(gestisci.getComboProgetti_modifica());

            //rimuovo e ricreo l'acction listener
            StaticMethod.removeAllActionListener(gestisci.getButtonCreaProgetto());
            listenerCreaProgetto();
        }

    }

    protected void creaSequenza() {
        Sequenza s = gestisci.getParametriCreaSequenza();
        GruppoSequenze gruppoSequenze = new GruppoSequenze();
        int errore = 0;

        for (Sequenza appoggio : gruppoSequenze.getStato()) {
            if (appoggio.getNome().equals(s.getNome())) {
                errore++;
            }
        }

        if (errore != 0) {
            JOptionPane.showMessageDialog(new JFrame("errore"), s.getNome() + " é gia presente nel programma!");
        }else if (gestisci.getComboProgetti().getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(new JFrame("errore")," seleziona un progetto!");
        }else{

            s.insertIntoSQL();
            //inserisco un nuovo messaggio
            MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
            messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                    " ha creato una nuova sequenza in " + s.getNomeprogetto() + ": " + s.getNome());
            messaggioBroadcast.setTipo("AUTO");
            messaggioBroadcast.setMittente("AUTO");
            messaggioBroadcast.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

            //aggiorno l'elenco delle Sequenze
            gestisci.popolaSequenze();

            //ripulisco crea
            gestisci.getFieldNomeSequenza().setText("");
            gestisci.getComboProgetti().setSelectedIndex(0);

            StaticMethod.popolaComboSequenze(gestisci.getComboSequenze());
            StaticMethod.popolaComboSequenze(gestisci.getComboSequenze_modifica());

            //rimuovo e ricreo l'acction listener
            StaticMethod.removeAllActionListener(gestisci.getButtonCreaSequenza());
            listenerCreaSequenza();
        }
    }

    protected void creaAttivita(){
        Attivita a = gestisci.getParametriCreaAttivita();
        GruppoSequenze gruppoSequenze = new GruppoSequenze();
        int errore = 0;

        for (Sequenza sequenza : gruppoSequenze.getStato()) {
            for(Attivita attivita : sequenza.getStato()){
                if(attivita.getDescrizione().equalsIgnoreCase(a.getDescrizione())){
                    errore++;
                }
            }
        }

        if (errore != 0) {
            JOptionPane.showMessageDialog(new JFrame("errore"),"esiste già un attività con la medesima descrizione!");
        }else if(gestisci.getComboSequenze().getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(new JFrame("errore"),"seleziona una sequenza!");
        }else if (StaticMethod.AmaggioreB(gestisci.getComboGiornoInizoAttivita(),gestisci.getComboMeseInizioAttivita(),gestisci.getComboAnnoInizioAttivita(),
                gestisci.getComboGiornoFineAttivita(),gestisci.getComboMeseFineAttivita(),gestisci.getComboAnnoFineAttivita())){
            JOptionPane.showMessageDialog(new JFrame("Errore"), "La data di inizio attività deve essere minore di quella di fine");
        }else {


            //se l'utente non inserisce alcun valore nel campo costo il try catch inserisce di defaut 0
            try {
                a.setCosto(Double.parseDouble(gestisci.getFieldCostoAttivita().getText()));
            }catch (NumberFormatException e){
                a.setCosto(0);
            }




            //se ho la precedenza faccio un inserimento altrimenti ne faccio un altro

            //se inserisco delle date scorrette ottengo un sql exception

            if (!gestisci.getFieldPrecedenzaAttivita().getText().equals("")) {
                String precedenza = gestisci.getFieldPrecedenzaAttivita().getText();

                a.insertIntoSQL(precedenza);

            } else {
                a.insertIntoSQL();


            }



            //inserisco un nuovo messaggio
            MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
            messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                    " ha creato una nuova Attività in " + a.getNomesequenza() + ": " + a.getDescrizione());
            messaggioBroadcast.setTipo("AUTO");
            messaggioBroadcast.setMittente("AUTO");

            messaggioBroadcast.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();


            //aggionro la fine della sequenza
            Sequenza s = new Sequenza(a.getNomesequenza());
            s.updateIntoSQL("fine", s.getFine());



            //aggiorno l'elenco delle Attività
            gestisci.popolaAttivita();
            gestisci.popolaSequenze();
            gestisci.popolaProgetti();

            //ripulisco crea
            gestisci.getFieldDescrizioneAttivtia().setText("");
            gestisci.getFieldCostoAttivita().setText("0");
            gestisci.getFieldPrecedenzaAttivita().setText("");
            gestisci.getComboSequenze().setSelectedIndex(0);

        }

        //aggiorno Incarichi
        gestisci.popolaAttivita_assegnazione();

        //rimuovo e ricreo l'acction listener
        StaticMethod.removeAllActionListener(gestisci.getButtonCreaAttivita());
        listenerCreaAttivita();
    }

    protected void creaAppuntamento(){
        Incontro incontro =gestisci.getParametriCreaIncontro();

        incontro.insertIntoSQL();

        //inserisco un nuovo messaggio
        MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
        messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                " ha creato un nuovo incontro: " + incontro.getLuogo() + " alle "  + incontro.getOra() + " del " + incontro.getData());
        messaggioBroadcast.setTipo("AUTO");
        messaggioBroadcast.setMittente("AUTO");
        messaggioBroadcast.insertIntoSQL();



        Gruppo gruppo = new Gruppo();

        if(gestisci.getInvitaTuttiRadioButton().isSelected()){

            //invito tutti
            gruppo.createFrom("utenti");

        }else if(gestisci.getDirettivoRadioButton().isSelected()){

            //invito i grouopleader e i teamleader
            gruppo.createFrom("direttivo");
        }else if(gestisci.getInvitaDaUnaSequenzaRadioButton().isSelected()){

            String nomeSequenza=gestisci.getComboSequenzeAppuntamento().getSelectedItem().toString();
            gruppo.createFrom("sequenza",nomeSequenza);
        }

        for(Utente appoggio: gruppo.getElenco()){

            utilizzatore.invita(appoggio,incontro);
        }

        //aggiorno la home e gestisci
        homeController.getMessaggiController().aggiorna();
        homeController.aggiornoAppuntamenti();
        gestisci.popolaAppuntamenti();

        //resetto l'interfaccia
        gestisci.getComboTipoAppuntamento().setSelectedIndex(0);
        gestisci.getFieldLuogoAppuntamento().setText("");
        gestisci.getComboGiornoAppuntamento().setSelectedIndex(0);
        gestisci.getComboMeseAppuntamento().setSelectedIndex(0);
        gestisci.getComboAnnoAppuntamento().setSelectedIndex(0);
        gestisci.getComboOraAppuntamento().setSelectedIndex(0);
        gestisci.getComboMinutiAppuntamento().setSelectedIndex(0);
        gestisci.getInvitaTuttiRadioButton().setSelected(true);
        gestisci.getDirettivoRadioButton().setSelected(false);
        gestisci.getInvitaDaUnaSequenzaRadioButton().setSelected(false);
        gestisci.getComboSequenzeAppuntamento().setSelectedIndex(0);


        //rimuovo e ricreo l'acction listener
        StaticMethod.removeAllActionListener(gestisci.getButtonCreaAppuntamento());
        listenerCreaAppuntamento();
    }

    protected void creaUtente(){
        Utente utente = gestisci.getParametriCreaUtente();
        Gruppo gruppoUtenti = new Gruppo();
        int errore=0;
        gruppoUtenti.createFrom("utenti");

        for (Utente appoggio : gruppoUtenti.getElenco()){
            if(utente.getMatricola().equals(appoggio.getMatricola())) {
                errore++;
            }
        }
        if(errore!=0){
            gestisci.displayErrorMessage("Esiste già un utente con  la matricola" + utente.getMatricola() , "Errore creazione utente");
        }else{
            utente.insertIntoSQL();
        }


        //inserisco un nuovo messaggio
        MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();

        messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                " ha creato una nuovo Utente con matricola " + utente.getMatricola());

        messaggioBroadcast.setTipo("AUTO");
        messaggioBroadcast.setMittente("AUTO");

        messaggioBroadcast.insertIntoSQL();

        //aggionro i messaggi
        homeController.getMessaggiController().aggiorna();


        //aggiorno l'elenco degli Utenti
        gestisci.popolaUtenti();

        //ripulisco crea
        gestisci.getFieldMatricolaUtente().setText("");
        gestisci.getFieldMailUtente().setText("");
        gestisci.getFieldNomeUtente().setText("");
        gestisci.getFieldCognomeUtente().setText("");
        gestisci.getComboRuoloUtente().setSelectedIndex(0);
        gestisci.getFieldTelefonoUtente().setText("");
        gestisci.getFieldMailUtente().setText("");

        //aggiorno incarichi
        gestisci.popolaUtenti_assegnazione();

        //aggiorno non assegnati
        gestisci.popolaElencoNonAssegnati();

        //rimuovo e ricreo l'action listener
        StaticMethod.removeAllActionListener(gestisci.getButtonCreaUtente());
        listenerCreaUtente();

    }



    protected void abilitaModificaProgetto(){
        //creo il listner del salvataggio
        listenerSalvaModificheProgeto(gestisci.getFieldNomeProgetto_modifica().getText());

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaModificheProgetto().setVisible(true);
        gestisci.getButtonModificaProgetto().setVisible(false);

        gestisci.disabilitaComponenti(false,gestisci.getFieldNomeProgetto_modifica(),
                gestisci.getComboGiornoProgetto_modifica(),gestisci.getComboMeseProgetto_modifica(),
                gestisci.getComboAnnoProgetto_modifica());
    }

    protected void abilitaModificaSequenza(){

        //creo il listner del salvataggio
        listenerSalvaModificheSequenza(gestisci.getFieldNomeSequenza_modifica().getText());

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaModificheSequenza().setVisible(true);
        gestisci.getButtonModificaSequenza().setVisible(false);

        gestisci.disabilitaComponenti(false,gestisci.getFieldNomeSequenza_modifica(),gestisci.getComboProgetti_modifica());

    }

    protected void abilitaModificheAttività(){

        int row=gestisci.getTableAttivita().getSelectedRow();

        //prelevo l'id dell'attività da modificare
        int id=Integer.parseInt(gestisci.getTableAttivita().getValueAt(row,0).toString());

        //creo il listner di salvataggio
        listenerSalvaModificheAttivita(id);

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaModificheAttivita().setVisible(true);
        gestisci.getButtonModificaAttivita().setVisible(false);

        gestisci.disabilitaComponenti(false,gestisci.getFieldDescrizioneAttivita_modifica(),gestisci.getFieldPrecedenzaAttivita_modifica(),gestisci.getFieldCostoAttivta_modifica(),
                gestisci.getComboGiornoInizioAttivita_modifica(),gestisci.getComboMeseInizioAttivita_modifica(),
                gestisci.getComboMeseInizioAttivita_modifica(),gestisci.getComboGiornoFineAttivita_modifica(),
                gestisci.getComboMeseFineAttivita_modifica(),gestisci.getComboAnnoFineAttivita_modifica(),gestisci.getComboAnnoInizioAttivita_modifica(),
                gestisci.getComboSequenze_modifica());

    }

    protected void abilitaModificheUtente(){
        JTable tabella = gestisci.getTableUtenti();

        //prendo la riga selezionata dall'utente nella tabella
        String matricolaUtenteSelezionato=tabella.getValueAt(tabella.getSelectedRow(),0).toString();

        //passo l'utente e quindi tutti campi di modifica

        listenerSalvaModificheUtente(matricolaUtenteSelezionato);
        //TODO listenerEliminaUtente();

        gestisci.getButtonSalvaModificheUtente().setVisible(true);
        gestisci.getButtonModificaUtente().setVisible(false);

        //gestisci.getButtonEliminaUtente().setVisible(true);
        gestisci.getButtonEliminaUtente().setEnabled(true);

        //abilito la modifica di tutti i campi
        gestisci.disabilitaComponenti(false, gestisci.getFieldNomeUtente_modifica(),
                gestisci.getFieldCognomeUtente_modifica(), gestisci.getFieldMailUtente_modifica(),
                gestisci.getFieldTelefonoUtente_modifica(),gestisci.getComboRuoloUtente_modifica());
    }

    protected void abilitaModificheAppuntamento(){
        JTable tabella    = gestisci.getTableAppuntamenti();
        int id            =(Integer) tabella.getValueAt(tabella.getSelectedRow(),0);
        Incontro incontro = new Incontro(id);

        //creo il listener di salvataggio
        listenerSalvaModificheAppuntamento(id);

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaAppuntamento().setVisible(true);
        gestisci.getButtonModificaAppuntamento().setVisible(false);
        gestisci.getButtonEliminaAppuntamento().setEnabled(true);

        gestisci.disabilitaComponenti(false,gestisci.getComboTipoAppuntamento_modifica(),gestisci.getFieldLuogoAppuntamento_modifica(),
                                        gestisci.getComboGiornoAppuntamento_modifica(),gestisci.getComboMeseAppuntamento_modifica(),
                                        gestisci.getComboAnnoAppuntamento_modifca(),gestisci.getComboOraAppuntamento_modifica(),
                                        gestisci.getComboMinutiAppuntamento_modifica(),gestisci.getAreaVerbaleAppuntamento());


    }


    protected void salvaModificheProgetto(String nome){

        Progetto p = new Progetto(nome);

        String nuovoNome=gestisci.getFieldNomeProgetto_modifica().getText();

        String data=    gestisci.getComboAnnoProgetto_modifica().getSelectedItem().toString() + "-" +
                gestisci.getComboMeseProgetto_modifica().getSelectedItem().toString() + "-" +
                gestisci.getComboGiornoProgetto_modifica().getSelectedItem().toString();


        if(!data.equals(p.getDeadline())){
            p.updateIntoSQL("deadline",data);
            MessaggioBroadcast messaggio = new MessaggioBroadcast();
            messaggio.setMittente("AUTO");
            messaggio.setTipo("AUTO");
            messaggio.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha modificato la DeadLine del progeto " + nome + " alla data " + data );
            messaggio.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();
        }



        if(!nome.equals(nuovoNome)){
            p.updateIntoSQL("nome",nuovoNome);
            MessaggioBroadcast messaggio = new MessaggioBroadcast();
            messaggio.setMittente("AUTO");
            messaggio.setTipo("AUTO");
            messaggio.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha aggiornato il nome del progetto " + nome + " in " + nuovoNome);
            messaggio.insertIntoSQL();


            //aggiorno tutte le sotto sequenze
            for(Sequenza appoggio:p.getStato()){
                appoggio.updateIntoSQL("nomeprogetto",nuovoNome);
            }
            //aggiorno le combo progetti

            StaticMethod.popolaComboProgetti(gestisci.getComboProgetti());
            StaticMethod.popolaComboProgetti(gestisci.getComboProgetti_modifica());

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();
        }


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheProgetto().setVisible(false);

        gestisci.getButtonModificaProgetto().setVisible(true);

        gestisci.getButtonEliminaProgetto().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getFieldNomeProgetto_modifica(),
                gestisci.getComboGiornoProgetto_modifica(),gestisci.getComboMeseProgetto_modifica(),
                gestisci.getComboAnnoProgetto_modifica());


        gestisci.popolaProgetti();
        gestisci.popolaSequenze();


        //infine rimuovo il listner di salvamodifiche
        StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheProgetto());
    }

    protected void salvaModificheSequenza(String nome){

        Sequenza s = new Sequenza(nome);

        String nuovoNome=gestisci.getFieldNomeSequenza_modifica().getText();

        String nuovoNomeProgetto=gestisci.getComboProgetti_modifica().getSelectedItem().toString();


        if(!nuovoNomeProgetto.equalsIgnoreCase(s.getNomeprogetto())){
            s.updateIntoSQL("nomeprogetto",nuovoNomeProgetto);
            MessaggioBroadcast messaggio = new MessaggioBroadcast();
            messaggio.setMittente("AUTO");
            messaggio.setTipo("AUTO");
            messaggio.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha riassegnato la sequenza " + s.getNome() + " al progetto " + nuovoNomeProgetto);
            messaggio.insertIntoSQL();



            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();
        }



        if(!nuovoNome.equalsIgnoreCase(nome)){
            s.updateIntoSQL("nome",nuovoNome);
            MessaggioBroadcast messaggio = new MessaggioBroadcast();
            messaggio.setMittente("AUTO");
            messaggio.setTipo("AUTO");
            messaggio.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha aggiornato il nome della Sequenza " + nome + " in " + nuovoNome);
            messaggio.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

            //aggiornoIncarichi
            gestisci.popolaAttivita_assegnazione();

            //aggiorno tutte le sotto sequenze
            for(Attivita appoggio:s.getStato()){
                appoggio.updateIntoSQL("nomesequenza",nuovoNome);
            }
        }


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheSequenza().setVisible(false);

        gestisci.getButtonModificaSequenza().setVisible(true);

        gestisci.getButtonEliminaSequenza().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getFieldNomeSequenza_modifica(),gestisci.getComboProgetti_modifica());

        gestisci.popolaSequenze();
        gestisci.popolaAttivita();

        //infine rimuovo il listner di salvamodifiche
        StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheSequenza());
    }

    protected void salvaModificheAttivita(int id){
        Attivita a = new Attivita(id);
        MessaggioBroadcast m = new MessaggioBroadcast();
        m.setMittente("AUTO");
        m.setTipo("AUTO");

        String nuovoaDescrizione=gestisci.getFieldDescrizioneAttivita_modifica().getText();
        if(!a.getDescrizione().equalsIgnoreCase(nuovoaDescrizione)){

            a.updateIntoSQL("descrizione",nuovoaDescrizione);


            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha aggiornato la descrizione dell attività " + id);
            m.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

        }


        String costo=gestisci.getFieldCostoAttivta_modifica().getText();
        if(a.getCosto()!=Double.parseDouble(costo)){


            a.updateIntoSQL("costo",costo);

            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha aggiornato il costo dell attività " + id + " in " + costo + " €" );
            m.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

        }

        if(a.getPrecedenza()!=Integer.parseInt(gestisci.getFieldPrecedenzaAttivita_modifica().getText())){
            String precedenza=gestisci.getFieldPrecedenzaAttivita_modifica().getText();

            a.updateIntoSQL("precedenza",precedenza);
        }

        if(!a.getNomesequenza().equalsIgnoreCase(gestisci.getComboSequenze_modifica().getSelectedItem().toString())){

            String sequenza = gestisci.getComboSequenze_modifica().getSelectedItem().toString();
            a.setId(id);
            a.updateIntoSQL("nomesequenza",sequenza);

            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha riassegnato l attività " + id + " alla sequenza " + sequenza );
            m.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();
        }

        String inizio = gestisci.getComboAnnoInizioAttivita_modifica().getSelectedItem().toString() + "-" +
                gestisci.getComboMeseInizioAttivita_modifica().getSelectedItem().toString() + "-" +
                gestisci.getComboGiornoInizioAttivita_modifica().getSelectedItem().toString();

        String fineprevista =   gestisci.getComboAnnoFineAttivita_modifica().getSelectedItem().toString() + "-" +
                gestisci.getComboMeseFineAttivita_modifica().getSelectedItem().toString() + "-" +
                gestisci.getComboGiornoFineAttivita_modifica().getSelectedItem().toString();

        if(!inizio.equals(a.getDatainizio())){
            a.setId(id);
            a.updateIntoSQL("datainizio",inizio);

            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha modificato la data di inizio dell attività " + id + " al " + inizio );
            m.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();
        }


        if(!fineprevista.equals(a.getDatafineprevista())){

            try {
                a.updateIntoSQL("datafineprevista", fineprevista);

                m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                        "ha modificato la data di fine prevista dell attività " + id + " al " + fineprevista );
                m.insertIntoSQL();

                //aggionro i messaggi
                homeController.getMessaggiController().aggiorna();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new JFrame("errore"), a.getId() + " ha una fine stimata non coerente con la relativa deadline di progetto!");
            }



        }

        gestisci.popolaProgetti();
        gestisci.popolaSequenze();
        gestisci.popolaAttivita();



        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheAttivita().setVisible(false);

        gestisci.getButtonModificaAttivita().setVisible(true);

        gestisci.getButtonEliminaAttivita().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getFieldDescrizioneAttivita_modifica(),gestisci.getFieldPrecedenzaAttivita_modifica(),gestisci.getFieldCostoAttivta_modifica()
                ,gestisci.getComboGiornoInizioAttivita_modifica(),gestisci.getComboMeseInizioAttivita_modifica(),
                gestisci.getComboMeseInizioAttivita_modifica(),gestisci.getComboGiornoFineAttivita_modifica(),
                gestisci.getComboMeseFineAttivita_modifica(),gestisci.getComboAnnoFineAttivita_modifica(),gestisci.getComboAnnoInizioAttivita_modifica(),
                gestisci.getComboSequenze_modifica());

        // aggiorno incarichi
        gestisci.popolaAttivita_assegnazione();

        //infine rimuovo il listner di salvamodifiche
        StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheAttivita());
    }

    protected void salvaModificheUtente(String matricola){

        Utente u = new Utente(matricola);
        MessaggioBroadcast m = new MessaggioBroadcast();
        m.setMittente("AUTO");
        m.setTipo("AUTO");

        boolean mod=false;

        //controllo se il campo del telefono è vuoto o non modificato
        if (!gestisci.getFieldTelefonoUtente_modifica().getText().equalsIgnoreCase(u.getTelefono()) || !gestisci.getFieldTelefonoUtente_modifica().getText().isEmpty()){
            u.updateIntoSQL("telefono", gestisci.getFieldTelefonoUtente_modifica().getText());
            mod=true;
        }

        //controllo se il campo del nome è vuoto o non modificato
        if (!gestisci.getFieldNomeUtente_modifica().getText().equalsIgnoreCase(u.getNome()) || !gestisci.getFieldNomeUtente_modifica().getText().isEmpty()){
            u.updateIntoSQL("Nome", gestisci.getFieldNomeUtente_modifica().getText());
            mod=true;
        }

        //controllo se il campo del cognome è vuoto o non modificato
        if (!gestisci.getFieldCognomeUtente_modifica().getText().equalsIgnoreCase(u.getCognome()) || !gestisci.getFieldCognomeUtente_modifica().getText().isEmpty()){
            u.updateIntoSQL("cognome", gestisci.getFieldCognomeUtente_modifica().getText());
            mod=true;
        }

        //controllo se il campo del indirizzo e-mail è vuoto o non modificato
        if (!gestisci.getFieldMailUtente_modifica().getText().equalsIgnoreCase(u.getMail()) || !gestisci.getFieldMailUtente_modifica().getText().isEmpty()){
            u.updateIntoSQL("mail",gestisci.getFieldMailUtente_modifica().getText());
            mod=true;
        }

        //controllo se la combobox del ruolo è stata modificata
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("US", 0);
        map.put("GL", 1);
        map.put("TL", 2);

        int ind =map.get(u.getRuolo()); //intValue

        if (ind!=gestisci.getComboRuoloUtente_modifica().getSelectedIndex()){
            u.updateIntoSQL("ruolo", gestisci.getComboRuoloUtente_modifica().getSelectedItem().toString());
            mod=true;
        }


        //mando messaggio diverso caso per caso
        if(mod){
            //MessaggioBroadcast m = new MessaggioBroadcast();
            m.setMittente("AUTO");
            m.setTipo("AUTO");

            String nuovoRuolo = gestisci.getComboRuoloUtente_modifica().getSelectedItem().toString();

            if(ind < gestisci.getComboRuoloUtente_modifica().getSelectedIndex()) {
                m.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() + " " +
                        "ha promosso " + u.getMatricola() + " da " + u.getRuolo() + " a " + nuovoRuolo);
            }else
            if(ind > gestisci.getComboRuoloUtente_modifica().getSelectedIndex()) {
                m.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() + " " +
                        "ha declassato " + u.getMatricola() + " da " + u.getRuolo() + " a " + nuovoRuolo);
            }else
                m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                        "ha modificato  l utente " + u.getMatricola());
            m.insertIntoSQL();
            u.updateIntoSQL("ruolo", nuovoRuolo);


        }


        //aggiorno i messaggi nella home
        homeController.getMessaggiController().aggiorna();


        //ripopolo l'elenco degli utenti
        gestisci.popolaUtenti();


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheUtente().setVisible(false);

        gestisci.getButtonModificaUtente().setVisible(true);

        gestisci.getButtonEliminaUtente().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getFieldNomeUtente_modifica(),gestisci.getFieldCognomeUtente_modifica()
                ,gestisci.getFieldTelefonoUtente_modifica(),gestisci.getComboRuoloUtente_modifica()
                ,gestisci.getFieldMailUtente_modifica());

        //aggiorno incarichi
        gestisci.popolaUtenti_assegnazione();

        //aggiorno non assegnati
        gestisci.popolaElencoNonAssegnati();

        //infine rimuovo il listner di salvamodifiche
        StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheUtente());

        //riabilito la modifica della tabella
        gestisci.getTableUtenti().setRowSelectionAllowed(true);
        statoListener=true;

    }

    protected void salvaModificheAppuntamento(int id){
        Incontro incontro = new Incontro(id);
        MessaggioBroadcast m = new MessaggioBroadcast();
        m.setMittente("AUTO");
        m.setTipo("AUTO");

        String nuovoTipo=gestisci.getComboTipoAppuntamento_modifica().getSelectedItem().toString();
        if(!incontro.getTipo().equalsIgnoreCase(nuovoTipo)){
            incontro.updateIntoSQL("tipo",nuovoTipo);
        }

        String nuovoLuogo = gestisci.getFieldLuogoAppuntamento_modifica().getText();
        if(!incontro.getLuogo().equalsIgnoreCase(nuovoLuogo)){

            incontro.updateIntoSQL("luogo",nuovoLuogo);

            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha spostato il luogo dell Appuntamento " + incontro.getId() + " del " + incontro.getData() + " in " + nuovoLuogo);
            m.insertIntoSQL();
        }

        String nuovadata=gestisci.getComboAnnoAppuntamento_modifca().getSelectedItem().toString() + "-"
                + gestisci.getComboMeseAppuntamento_modifica().getSelectedItem().toString() + "-"
                + gestisci.getComboGiornoAppuntamento_modifica().getSelectedItem().toString();
        if(!incontro.getData().equals(nuovadata)){

            incontro.updateIntoSQL("data",nuovadata);

            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha spostato l Appuntamento " + incontro.getId() + " del " + incontro.getData() + " al " + nuovadata);
            m.insertIntoSQL();
        }

        String nuovaora=gestisci.getComboOraAppuntamento_modifica().getSelectedItem().toString() + ":" + gestisci.getComboMinutiAppuntamento_modifica().getSelectedItem().toString() + ":00";
        if(!incontro.getOra().equals(nuovaora)){

            incontro.updateIntoSQL("ora",nuovaora);

            m.setMessaggio(utilizzatore.getNome() +" " + utilizzatore.getCognome() + " " +
                    "ha spostato  l Appuntamento " + incontro.getId() + " del " + incontro.getData() + " alle ore " + nuovaora);
            m.insertIntoSQL();
        }

        String nuovoverbale=gestisci.getAreaVerbaleAppuntamento().getText();
        if(!incontro.getVerbale().equalsIgnoreCase(nuovoverbale)){
            incontro.updateIntoSQL("verbale",nuovoverbale);
        }


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaAppuntamento().setVisible(false);
        gestisci.getButtonModificaAppuntamento().setVisible(true);
        gestisci.getButtonEliminaAppuntamento().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getComboTipoAppuntamento_modifica(),gestisci.getFieldLuogoAppuntamento_modifica(),
                gestisci.getComboGiornoAppuntamento_modifica(),gestisci.getComboMeseAppuntamento_modifica(),
                gestisci.getComboAnnoAppuntamento_modifca(),gestisci.getComboOraAppuntamento_modifica(),
                gestisci.getComboMinutiAppuntamento_modifica(),gestisci.getAreaVerbaleAppuntamento());

        gestisci.popolaAppuntamenti();


        //infine rimuovo il listner di salvamodifiche
        StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheProgetto());

        //aggiorno i messaggi nella home
        homeController.getMessaggiController().aggiorna();
    }


    protected void eliminaProgetto(){
        String nomeProgetto = gestisci.getFieldNomeProgetto_modifica().getText();
        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare il progetto " + nomeProgetto + "? \n" +
                        "[VERRANNO ELIMINATE TUTTE LE SOTTOSEQUENZE E SOTTOATTIVITÀ COLLEGATE]","Cancellazione di " + nomeProgetto,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION){
            Progetto p = new Progetto(nomeProgetto);
            p.deleteIntoSQL();

            MessaggioBroadcast m = new MessaggioBroadcast();
            m.setTipo("AUTO");
            m.setMittente("AUTO");
            m.setMessaggio(utilizzatore.getNome() + utilizzatore.getCognome() + " ha eliminato il progetto " + nomeProgetto);
            m.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

            gestisci.popolaProgetti();
            gestisci.popolaSequenze();
            gestisci.popolaAttivita();
            //gestisci.popolaAttivita();

            gestisci.getFieldNomeProgetto_modifica().setText("");
            gestisci.getComboGiornoProgetto_modifica().setSelectedIndex(0);
            gestisci.getComboMeseProgetto_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoProgetto_modifica().setSelectedIndex(0);
            gestisci.getLabelModificaProgetto().setVisible(true);
            gestisci.getButtonEliminaProgetto().setEnabled(false);

            gestisci.getButtonModificaProgetto().setVisible(true);
            gestisci.getButtonModificaProgetto().setEnabled(false);
            gestisci.getButtonEliminaProgetto().setEnabled(false);
            gestisci.getButtonSalvaModificheProgetto().setVisible(false);

            gestisci.disabilitaComponenti(true,gestisci.getFieldNomeProgetto_modifica(),
                    gestisci.getComboGiornoProgetto_modifica(),gestisci.getComboMeseProgetto_modifica(),
                    gestisci.getComboAnnoProgetto_modifica());

            //aggiorno il pannello  delle sequenze
            StaticMethod.popolaComboProgetti(gestisci.getComboProgetti());


            //riabilito la selezione
            gestisci.getTableProgetti().setRowSelectionAllowed(true);
            statoListener=true;
        }


    }

    protected void eliminaSequenza(){
        String nomeSequenza = gestisci.getFieldNomeSequenza_modifica().getText();
        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare la sequenza " + nomeSequenza + "? \n" +
                        "[VERRANNO ELIMINATE TUTTE LE SOTTOATTIVITÀ COLLEGATE]","Cancellazione di " + nomeSequenza,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            Sequenza s = new Sequenza(nomeSequenza);
            s.deleteIntoSQL();

            MessaggioBroadcast m = new MessaggioBroadcast();
            m.setTipo("AUTO");
            m.setMittente("AUTO");
            m.setMessaggio(utilizzatore.getNome() + utilizzatore.getCognome() + " ha eliminato la sequenza " + nomeSequenza);
            m.insertIntoSQL();

            //aggionro i messaggi
            homeController.getMessaggiController().aggiorna();

            gestisci.popolaSequenze();
            //gestisci.popolaAttivita();

            gestisci.getFieldNomeSequenza_modifica().setText("");
            gestisci.getComboProgetti_modifica().setSelectedIndex(0);
            gestisci.getLabelModificaSequenza().setVisible(true);
            gestisci.getButtonEliminaSequenza().setEnabled(false);

            //aggiorno il pannello  delle Attività
            StaticMethod.popolaComboSequenze(gestisci.getComboSequenze());

            gestisci.getButtonModificaSequenza().setVisible(true);
            gestisci.getButtonModificaSequenza().setEnabled(false);
            gestisci.getButtonEliminaSequenza().setEnabled(false);
            gestisci.getButtonSalvaModificheSequenza().setVisible(false);

            gestisci.disabilitaComponenti(true,gestisci.getFieldNomeSequenza_modifica(),gestisci.getComboProgetti_modifica());

            //riabilito la selezione
            gestisci.getTableSequenze().setRowSelectionAllowed(true);
            statoListener=true;

        }
    }

    protected void eliminaAttivita(){
        int id = Integer.parseInt(gestisci.getTableAttivita().getValueAt(gestisci.getTableAttivita().getSelectedRow(),0).toString());
        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare l attivita "+ id + "?","Cancellazione di " + id,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            Attivita a = new Attivita(id);
            a.deleteIntoSQL();

            MessaggioBroadcast m = new MessaggioBroadcast();
            m.setTipo("AUTO");
            m.setMittente("AUTO");
            m.setMessaggio(utilizzatore.getNome() + utilizzatore.getCognome() + " ha eliminato " + id);
            m.insertIntoSQL();

            //aggiorno i messaggi
            homeController.getMessaggiController().aggiorna();

            gestisci.popolaAttivita();


            gestisci.getFieldDescrizioneAttivita_modifica().setText("");
            gestisci.getFieldPrecedenzaAttivita_modifica().setText("");
            gestisci.getFieldCostoAttivta_modifica().setText("0");
            gestisci.getComboSequenze_modifica().setSelectedIndex(0);
            gestisci.getComboGiornoInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboMeseInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboGiornoFineAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboMeseFineAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoFineAttivita_modifica().setSelectedIndex(0);

            gestisci.getButtonModificaAttivita().setVisible(true);
            gestisci.getButtonModificaAttivita().setEnabled(false);
            gestisci.getButtonEliminaAttivita().setEnabled(false);
            gestisci.getButtonSalvaModificheAttivita().setVisible(false);

            gestisci.disabilitaComponenti(true,gestisci.getFieldDescrizioneAttivita_modifica(),gestisci.getFieldPrecedenzaAttivita_modifica(),gestisci.getFieldCostoAttivta_modifica(),
                    gestisci.getComboGiornoInizioAttivita_modifica(),gestisci.getComboMeseInizioAttivita_modifica(),
                    gestisci.getComboMeseInizioAttivita_modifica(),gestisci.getComboGiornoFineAttivita_modifica(),
                    gestisci.getComboMeseFineAttivita_modifica(),gestisci.getComboAnnoFineAttivita_modifica(),gestisci.getComboAnnoInizioAttivita_modifica(),
                    gestisci.getComboSequenze_modifica());

            //aggiorno Incarichi
            gestisci.popolaAttivita_assegnazione();

            //riabilito la selezione
            gestisci.getTableAttivita().setRowSelectionAllowed(true);
            statoListener=true;
        }
    }

    protected void eliminaUtente(){

        String matricola = gestisci.getFieldMatricolaUtente_modifica().getText();

        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare l' utente "+ matricola + "?","Cancellazione di " + matricola,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            Utente u = new Utente(matricola);
            u.deleteIntoSQL();

            MessaggioBroadcast m = new MessaggioBroadcast();

            m.setTipo("AUTO");
            m.setMittente("AUTO");
            m.setMessaggio(utilizzatore.getNome() + utilizzatore.getCognome() + " ha eliminato " + matricola);
            m.insertIntoSQL();

            //aggiorno i messaggi
            homeController.getMessaggiController().aggiorna();

            //aggirno la tabella
            gestisci.popolaUtenti();


            //annullo tutti i campi di modifica
            gestisci.getFieldMailUtente_modifica().setText("");
            gestisci.getFieldNomeUtente_modifica().setText("");
            gestisci.getFieldCognomeUtente_modifica().setText("");
            gestisci.getComboRuoloUtente_modifica().setSelectedIndex(0);
            gestisci.getFieldTelefonoUtente_modifica().setText("");
            gestisci.getFieldMatricolaUtente_modifica().setText("");


            gestisci.getButtonEliminaUtente().setEnabled(false);
            gestisci.getButtonEliminaUtente().setVisible(false);

            //aggiorno Incarichi ed elimino tutti gli incarichi associati all'utente da eliminare
            utilizzatore.eliminaIncarico(matricola);
            gestisci.popolaUtenti_assegnazione();

            //aggiorno non assegnati nel caso l'utente non fosse assegnato a nessun'attività
            gestisci.popolaElencoNonAssegnati();

            //ridisabilito i campi e reimposto i bottoni
            gestisci.disabilitaComponenti(true, gestisci.getFieldNomeUtente_modifica(),
                    gestisci.getFieldCognomeUtente_modifica(), gestisci.getFieldMailUtente_modifica(),
                    gestisci.getFieldTelefonoUtente_modifica(),gestisci.getComboRuoloUtente_modifica());

            gestisci.getButtonModificaUtente().setVisible(true);
            gestisci.getButtonModificaUtente().setEnabled(false);
            gestisci.getButtonSalvaModificheUtente().setVisible(false);
            gestisci.getButtonEliminaUtente().setVisible(true);
            gestisci.getButtonEliminaUtente().setEnabled(false);
            gestisci.getLabelModificaUtente().setVisible(true);

            StaticMethod.removeAllActionListener(gestisci.getButtonEliminaUtente());
            listenerEliminaUtente();

            //rendo dinuovo disponibile la selezione
            gestisci.getTableUtenti().setRowSelectionAllowed(true);
            statoListener=true;
        }
    }

    protected void eliminaAppuntamento(){
        int id = Integer.parseInt(gestisci.getTableAppuntamenti().getValueAt(gestisci.getTableAppuntamenti().getSelectedRow()   ,0).toString());
        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare l appuntamento "+ id + "?","Cancellazione di " + id,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            Incontro a = new Incontro(id);
            a.deleteIntoSQL();

            MessaggioBroadcast m = new MessaggioBroadcast();
            m.setTipo("AUTO");
            m.setMittente("AUTO");
            m.setMessaggio(utilizzatore.getNome() + utilizzatore.getCognome() + " ha eliminato l appuntamento" + id);
            m.insertIntoSQL();

            //aggionro
            homeController.getMessaggiController().aggiorna();
            homeController.aggiornoAppuntamenti();
            gestisci.popolaAppuntamenti();

            nascondiInvitati();
            gestisci.getButtonNascondiInvitati().setVisible(false);
            gestisci.getButtonMostraInvitati().setVisible(true);
            gestisci.getButtonMostraInvitati().setEnabled(false);

            gestisci.getLabelModificaAppuntamento().setVisible(true);
            gestisci.getButtonSalvaAppuntamento().setVisible(false);
            gestisci.getButtonModificaAppuntamento().setVisible(true);
            gestisci.getButtonEliminaAppuntamento().setEnabled(false);

            gestisci.disabilitaComponenti(true,gestisci.getComboTipoAppuntamento_modifica(),gestisci.getFieldLuogoAppuntamento_modifica(),
                    gestisci.getComboGiornoAppuntamento_modifica(),gestisci.getComboMeseAppuntamento_modifica(),
                    gestisci.getComboAnnoAppuntamento_modifca(),gestisci.getComboOraAppuntamento_modifica(),
                    gestisci.getComboMinutiAppuntamento_modifica(),gestisci.getAreaVerbaleAppuntamento());

            gestisci.getComboTipoAppuntamento_modifica().setSelectedIndex(0);
            gestisci.getFieldLuogoAppuntamento_modifica().setText("");
            gestisci.getComboAnnoAppuntamento_modifca().setSelectedIndex(0);
            gestisci.getComboMeseAppuntamento_modifica().setSelectedIndex(0);
            gestisci.getComboGiornoAppuntamento_modifica().setSelectedIndex(0);
            gestisci.getComboOraAppuntamento_modifica().setSelectedIndex(0);
            gestisci.getComboMinutiAppuntamento_modifica().setSelectedIndex(0);
            gestisci.getAreaVerbaleAppuntamento().setText("");

            gestisci.getButtonModificaAppuntamento().setVisible(true);
            gestisci.getButtonModificaAppuntamento().setEnabled(false);
            gestisci.getButtonEliminaAppuntamento().setEnabled(false);


            //riabilito il listener
            gestisci.getTableAppuntamenti().setRowSelectionAllowed(true);
            statoListener=true;
        }
    }

    protected void eliminaOrdine(){
        int id = Integer.parseInt(gestisci.getTableOrdini().getValueAt(gestisci.getTableOrdini().getSelectedRow()   ,0).toString());
        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare l ordine "+ id + "?","Cancellazione di " + id,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            Ordine o = new Ordine(id);
            Gruppo g = new Gruppo();
            g.createFrom("direttivo");
            o.deleteIntoSQL();
            g.eliminaDaVotazione(o.getId());
            //eliminare anche le votazioni

            MessaggioBroadcast m = new MessaggioBroadcast();
            m.setTipo("AUTO");
            m.setMittente("AUTO");
            m.setMessaggio(utilizzatore.getNome() + utilizzatore.getCognome() + " ha eliminato l ordine" + id);
            m.insertIntoSQL();

            //aggionro
            homeController.getMessaggiController().aggiorna();
            ordiniController.popolaOrdini();
            gestisci.popolaOrdini();

            gestisci.getPanelApprovazioni().setVisible(false);
            gestisci.getPanelBottoni().setVisible(false);
            gestisci.getEliminaOrdineButton().setEnabled(false);
            gestisci.getLabelApprovaOrdini().setVisible(true);

        }
    }

    protected void eliminaIncarico()throws Exception{

        int     riga        = gestisci.getTableElencoAssegnazioni().getSelectedRow();
        String  matricola   = gestisci.getTableElencoAssegnazioni().getValueAt(riga,0).toString();
        int     id          = (Integer) gestisci.getTableElencoAssegnazioni().getValueAt(riga,3);

        int response=JOptionPane.showConfirmDialog(null,"vuoi veramente eliminare la coppia "+ matricola + " - " + id + "?","Cancellazione dell'incarico",
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {

            utilizzatore.eliminaIncarico(matricola, id);

            popolaElencoIncarichi();
        }
    }


    protected void popolaCampiModificaProgetto(int riga){
        JTable tabellaProgetti = gestisci.getTableProgetti();
        gestisci.getFieldNomeProgetto_modifica().setText(tabellaProgetti.getValueAt(riga,0).toString());
        StaticMethod.setData(gestisci.getComboGiornoProgetto_modifica(), gestisci.getComboMeseProgetto_modifica(), gestisci.getComboAnnoProgetto_modifica(),tabellaProgetti.getValueAt(riga,1).toString());
    }

    protected void popolaCampiModificaSequenza(int riga){
        JTable tabellaSequenze = gestisci.getTableSequenze();
        gestisci.getFieldNomeSequenza_modifica().setText(tabellaSequenze.getValueAt(riga,0).toString());


        ComboBoxModel model=gestisci.getComboProgetti_modifica().getModel();
        for(int i=0;i<model.getSize();i++){
            String a=tabellaSequenze.getValueAt(riga,1).toString();
            String b=model.getElementAt(i).toString();

            if(a.equals(b)){
                gestisci.getComboProgetti_modifica().setSelectedIndex(i);
            }
        }
    }

    protected void popolaCampimodificaAttivita(int riga){
        JTable tabellaAttivita = gestisci.getTableAttivita();

        gestisci.getFieldDescrizioneAttivita_modifica().setText(tabellaAttivita.getValueAt(riga,1).toString());
        gestisci.getFieldPrecedenzaAttivita_modifica().setText(tabellaAttivita.getValueAt(riga,2).toString());
        gestisci.getFieldCostoAttivta_modifica().setText(tabellaAttivita.getValueAt(riga,7).toString());

        ComboBoxModel model = gestisci.getComboSequenze_modifica().getModel();
        for(int i=0;i<model.getSize();i++){
            String a = tabellaAttivita.getValueAt(riga,3).toString();
            String b = model.getElementAt(i).toString();

            if(a.equals(b)){
                gestisci.getComboSequenze_modifica().setSelectedIndex(i);
            }
        }

        try {
            String datainizio=tabellaAttivita.getValueAt(riga, 4).toString();
            String datafineprevista= tabellaAttivita.getValueAt(riga, 5).toString();
            StaticMethod.setData(gestisci.getComboGiornoInizioAttivita_modifica(), gestisci.getComboMeseInizioAttivita_modifica(), gestisci.getComboAnnoInizioAttivita_modifica(),datainizio);
            StaticMethod.setData(gestisci.getComboGiornoFineAttivita_modifica(), gestisci.getComboMeseFineAttivita_modifica(), gestisci.getComboAnnoFineAttivita_modifica(), datafineprevista);
        }catch (Exception e){
            gestisci.getComboGiornoInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboMeseInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboGiornoFineAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboMeseFineAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoFineAttivita_modifica().setSelectedIndex(0);
        }
    }

    protected void popolaCampimodificaUtente(int riga) {
        JTable tabellaUtenti = gestisci.getTableUtenti();

        Utente utente = new Utente(tabellaUtenti.getValueAt(riga, 0).toString());

        gestisci.getFieldMatricolaUtente_modifica().setText(utente.getMatricola());
        gestisci.getFieldNomeUtente_modifica().setText(utente.getNome());
        gestisci.getFieldCognomeUtente_modifica().setText(utente.getCognome());
        gestisci.getFieldMailUtente_modifica().setText(utente.getMail());
        gestisci.getFieldTelefonoUtente_modifica().setText(utente.getTelefono());

        //caso per caso l'indice della combobox in base ruolo utente da visualizzare
        int i=0;
        if(utente.getRuolo().equals("US")) i=0;
        else if(utente.getRuolo().equals("GL"))i=1;
        else if (utente.getRuolo().equals("TL"))i=2;
        //else i=0;
        gestisci.getComboRuoloUtente_modifica().setSelectedIndex(i);

    }

    protected void popolaCampiModificaAppuntamento(int riga){

        //fix sull'interfaccia grafica
        gestisci.getButtonModificaAppuntamento().setVisible(true);
        gestisci.getButtonSalvaAppuntamento().setVisible(false);
        gestisci.getButtonEliminaAppuntamento().setEnabled(false);


        JTable tabellaAppuntamenti=gestisci.getTableAppuntamenti();
        Incontro i = new Incontro((Integer)tabellaAppuntamenti.getValueAt(riga,0));

        String tipo     = i.getTipo();
        String luogo    = i.getLuogo();
        String data     = i.getData();
        String ora      = i.getOra();
        String verbale  = i.getVerbale();

        switch (tipo){

            case "milestone":{
                gestisci.getComboTipoAppuntamento_modifica().setSelectedIndex(0);
                break;
            }

            case "checkpoint":{
                gestisci.getComboTipoAppuntamento_modifica().setSelectedIndex(1);
                break;
            }
        }

        gestisci.getFieldLuogoAppuntamento_modifica().setText(luogo);
        gestisci.getAreaVerbaleAppuntamento().setText(verbale);
        gestisci.getButtonMostraInvitati().setEnabled(true);

        StaticMethod.setData(gestisci.getComboGiornoAppuntamento_modifica(), gestisci.getComboMeseAppuntamento_modifica(), gestisci.getComboAnnoAppuntamento_modifca(), data);
        StaticMethod.setOra(gestisci.getComboOraAppuntamento_modifica(),gestisci.getComboMinutiAppuntamento_modifica(),ora);

        Gruppo invitati = new Gruppo();
        Integer id=i.getId();
        invitati.createFrom("incontro",id.toString());

        gestisci.popolaInvitati(invitati);

    }

    protected void mostraInvitati(){
        gestisci.getPanelInvitati().setVisible(true);
        gestisci.getButtonMostraInvitati().setVisible(false);
        gestisci.getButtonNascondiInvitati().setVisible(true);
    }

    protected void nascondiInvitati(){
        gestisci.getPanelInvitati().setVisible(false);
        gestisci.getButtonMostraInvitati().setVisible(true);
        gestisci.getButtonNascondiInvitati().setVisible(false);
    }

    protected void aggiornaVotazioneOrdine(int riga,String voto){
        Ordine ordine = new Ordine((Integer) gestisci.getTableOrdini().getValueAt(riga,0));
        ordine.aggiornaVotazione(voto,utilizzatore.getMatricola());
        gestisci.popolaTabellaApprovazioni(riga);
    }

    protected void rendiOrdineDefinitivo(JTable tabellaVoti){
        int approvato=0,nonapprovato=0,inAttesa=0;

        for(int i=0;i<tabellaVoti.getModel().getRowCount();i++){
            if(tabellaVoti.getValueAt(i,3).toString().equalsIgnoreCase("<html><font color=orange>In attesa</font></html>")){
                inAttesa++;
            }else if(tabellaVoti.getValueAt(i,3).toString().equalsIgnoreCase("<html><font color=red>Non Approvato</font></html>")){
                nonapprovato++;
            }else if(tabellaVoti.getValueAt(i,3).toString().equalsIgnoreCase("<html><font color=green>Approvato</font></html>")){
                approvato++;
            }
        }

        if(inAttesa==0){
            JTable tabellaOrdini = gestisci.getTableOrdini();
            Ordine o = new Ordine((Integer) tabellaOrdini.getValueAt(tabellaOrdini.getSelectedRow(),0));
            MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
            messaggioBroadcast.setMittente("AUTO");
            messaggioBroadcast.setTipo("AUTO");

            if(approvato>nonapprovato){
                Attivita a =new Attivita(o.getAttivita());
                Double nuovoCostoAttivita = a.getCosto()+o.getPrezzo();
                o.updateIntoSQL("approvazione","Approvato");
                a.updateIntoSQL("costo",nuovoCostoAttivita.toString());
                messaggioBroadcast.setMessaggio("l ordine " + o.getId() + " (" + o.getDescrizione() + ") è stato approvato, il prezzo di " + a.getId() + " è stato aggiornato");
            }else{
                o.updateIntoSQL("approvazione","Non Approvato");
                messaggioBroadcast.setMessaggio("l ordine" + o.getId() + " (" + o.getDescrizione() + ") non è stato approvato");
            }

            messaggioBroadcast.insertIntoSQL();
        }else{
            gestisci.displayErrorMessage("Non tutti hanno espresso il proprio voto!","Errore");
        }

        //aggiorno le tabelle delle attività
        gestisci.popolaAttivita();
        gestisci.popolaAttivita_assegnazione();

        //ricarico le tabella degli ordini
        gestisci.popolaOrdini();
        ordiniController.popolaOrdini();
        //aggiorno i messaggi sulla home
        homeController.getMessaggiController().aggiorna();

    }

    protected void rifiutoRettorato(){
        JTable tabellaOrdini = gestisci.getTableOrdini();
        Ordine o = new Ordine((Integer) tabellaOrdini.getValueAt(tabellaOrdini.getSelectedRow(),0));
        MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
        messaggioBroadcast.setMittente("AUTO");
        messaggioBroadcast.setTipo("AUTO");
        messaggioBroadcast.setMessaggio("l ordine " + o.getId() + " (" + o.getDescrizione() + ") non è stato approvato dal rettorato");
        o.updateIntoSQL("approvazione","Non Approvato");
        messaggioBroadcast.insertIntoSQL();


        //aggiorno i messaggi sulla home
        homeController.getMessaggiController().aggiorna();
        //ricarico le tabella ordini
        ordiniController.popolaOrdini();
        gestisci.popolaOrdini();

    }

    public void assegna() throws Exception{
        JTable tabellaUtente    = gestisci.getTableUtenti_assegnazione();
        JTable tabellaAttivita  = gestisci.getTableAttivita_assegnazione();

        Utente      utente      = new Utente(tabellaUtente.getValueAt(tabellaUtente.getSelectedRow(),0).toString());
        Attivita    attivita    = new Attivita((Integer) tabellaAttivita.getValueAt(tabellaAttivita.getSelectedRow(),0));

        int response=JOptionPane.showConfirmDialog(null,"Vuoi assegare "+ utente.getNome() + " " + utente.getCognome() + " all'attività " + attivita.getId() + " ?",
                "Assegnazione dell'utente " + utente.getMatricola(),
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            utilizzatore.assegnaUtente(utente.getMatricola(),attivita.getId());
            popolaElencoIncarichi();
            gestisci.popolaElencoNonAssegnati();
            homeController.aggiornaTabellaAttivita();

        }
    }

    public void popolaElencoIncarichi(){
        //seleziono tutti gli incarichi dal db
        ArrayList<Map> elencoDegliIncarichi=utilizzatore.selezionaIncarchidalDB();

        String col[] = {"Matricola","Nome","Cognome","Id","Descrizione","Data inizio","Data fine prevista"};
        CustomTable table = new CustomTable(col,0);
        for(Map appoggio:elencoDegliIncarichi){

            Object[] objects={appoggio.get("matricola"),appoggio.get("nome"),appoggio.get("cognome"),
                    appoggio.get("id"),appoggio.get("descrizione"),appoggio.get("datafine"),appoggio.get("datainizio"),appoggio.get("datafineprevista")};

            table.addRow(objects);
        }
        gestisci.getTableElencoAssegnazioni().setModel(table);
    }

    public Gestisci getGestisci() {
        return gestisci;
    }

    public void setOrdiniController(OrdiniController controller){
        this.ordiniController=controller;
    }
}

