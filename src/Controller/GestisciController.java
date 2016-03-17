package Controller;

import Model.*;
import Model.Gruppi.Gruppo;
import Model.Gruppi.GruppoProgetti;
import Model.Gruppi.GruppoSequenze;
import View.Gestisci;
import View.RootFrame;
import View.StaticMethod;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicola on 02/03/16.
 */
public class GestisciController {
    private RootFrame rootFrame;
    private Gestisci gestisci;
    private Utente utilizzatore;
    private HomeController homeController;
    private boolean statoListener;


    public GestisciController(RootFrame rootFrame, Utente utente,HomeController home) {
        this.rootFrame=rootFrame;
        this.gestisci= new Gestisci();
        this.utilizzatore=utente;
        this.homeController=home;
        this.statoListener=true; //da qui in poi i listener di selezione sulle tabelle sono abilitati

        //verranno disabilitati quando si entra in modalità modifica

        //disabilito alcuni campi per i group leader
        if(utilizzatore.getRuolo().equals("GL")) {
            gestisci.glMode();
        }else{
            listenerSelezioneProgetto();
            listenerSelezioneSequenza();
        }

        //listener
        listenerCreaProgetto();
        listenerCreaSequenza();
        listenerCreaAttivita();
        listenerCreaAppuntamento();

        listenerCreaUtente();

        //TODO */

        listenerSelezioneAttivita();
        listenerSelezionaAppuntamento();
        listenerSelezionaUtente();

        listenerModificaProgetto();
        listenerModificaSequenza();
        listenerModificaAttivita();
        listenerModificaUtente();

        listenerEliminaProgetto();
        listenerEliminaSequenza();
        listenerEliminaAttivita();

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
    }//TODO ok!


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

    protected void listnerSelezionaUtente(){
        JTable tabellaUtenti = gestisci.getTableUtenti();
        tabellaUtenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener){
                    popolaCampimodificaUtente(tabellaUtenti.getSelectedRow());
                    gestisci.getLabelModificaUtenti().setVisible(false);
                    gestisci.getButtonModificaUtente().setEnabled(true);
                    gestisci.getButtonEliminaUtente().setEnabled(true);
                    gestisci.getTabUtenti().setSelectedIndex(1);
                }
            }
        });
    } //TODO ok!

    protected void listenerSelezionaAppuntamento(){
        JTable tabellaAppuntamenti = gestisci.getTableApuntamenti();
        tabellaAppuntamenti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(statoListener){
                    popolaCampiModificaAppuntamento(tabellaAppuntamenti.getSelectedRow());
                    gestisci.getLabelModificaAppuntamento().setVisible(false);
                    gestisci.getButtonModifcaAppuntamento().setEnabled(true);
                    gestisci.getButtonEliminaAppuntamento().setEnabled(false);
                    gestisci.getTabAppuntamenti().setSelectedIndex(1);

                }
            }
        });
    }


    protected void listenerModificaProgetto(){
        JButton modifica=gestisci.getButtonModificaProgetto();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificaProgetto();

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

                //disabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableUtenti().setRowSelectionAllowed(false);
                statoListener=false;
            }
        });
    }  //TODO ok!


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

    protected void listenerSalvaModificheAttivita(int idattivita){
        JButton salva = gestisci.getButtonSalvaModificheAttivita();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheAttivita(idattivita);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableAttivita().setRowSelectionAllowed(true);
                statoListener=true;
            }
        });
    }

    protected void listenerSalvaModificheUtente(Utente u){
        JButton salva = gestisci.getButtonSalvaModificheUtente();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheUtente(u);

                //riabilito la possibilità di selezionare elementi dalla tabella
                gestisci.getTableUtenti().setRowSelectionAllowed(true);
                statoListner=true;
            }
        });
    } //TODO ok!




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
            gestisci.displayErrorMessage("Errore, crezione utente\n" + "codice errore:" +errore, "errore!");
        }else utente.insertIntoSQL();


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
        gestisci.getFieldMailUtente().setText("");
        gestisci.getFieldNomeUtente().setText("0");
        gestisci.getFieldCognomeUtente().setText("");
        gestisci.getComboRuoloUtente().setSelectedIndex(0);
        gestisci.getFieldTelefonoUtente().setText("");
        gestisci.getFieldMailUtente().setText("");

        //rimuovo e ricreo l'acction listener
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
        Utente utente = gestisci.getParametriModificaUtente();

        //passo l'utente e quindi tutti campi di modifica
        listnerSalvaModificheUtente(gestisci.getParametriModificaUtente());
        gestisci.getButtonSalvaModificheUtente().setVisible(true);
        gestisci.getButtonModificaUtente().setVisible(true);

        gestisci.disabilitaComponenti(false, gestisci.getFieldNomeUtente_modifica(),
                gestisci.getFieldCognomeUtente_modifica(), gestisci.getFieldMailUtente_modifica(),
                gestisci.getFieldTelefonoUtente_modifica(),gestisci.getComboRuoloUtente_modifica());
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
            for(Sequenza appofggio:p.getStato()){
                appofggio.updateIntoSQL("nomeprogetto",nuovoNome);
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
        }


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheSequenza().setVisible(false);

        gestisci.getButtonModificaSequenza().setVisible(true);

        gestisci.getButtonEliminaSequenza().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getFieldNomeSequenza_modifica(),gestisci.getComboProgetti_modifica());

        gestisci.popolaSequenze();

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

            //TODO b è inutile
            //Attivita b = new Attivita(id);

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

        //infine rimuovo il listner di salvamodifiche
        StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheAttivita());
    }

    protected void salvaModificheUtente(Utente u){

        //TODO passaggio oggetto != passaggio matricola
        //Utente u = new Utente(matricola);
        MessaggioBroadcast m = new MessaggioBroadcast();
        m.setMittente("AUTO");
        m.setTipo("AUTO");


        //controllo se nome modificato se si aggiorno se no lascio database non modificato

        String nuovoNome = gestisci.getFieldNomeUtente_modifica().getText();
        if(!u.getNome().equalsIgnoreCase(nuovoNome)){
            u.updateIntoSQL("nome", nuovoNome);
        }

        String nuovoCognome = gestisci.getFieldCognomeUtente_modifica().getText();
        if(!u.getCognome().equalsIgnoreCase(nuovoCognome)) {

            //Utente d = new Utente(matricola);
            u.updateIntoSQL("cognome", nuovoCognome);
        }

        String nuovoTelefono = gestisci.getFieldTelefonoUtente_modifica().getText();
        if(!u.getTelefono().equalsIgnoreCase(nuovoTelefono)) {

            //Utente d = new Utente(matricola);
            u.updateIntoSQL("telefono", nuovoTelefono);
        }

        String nuovoMail = gestisci.getFieldMailUtente_modifica().getText();
        if(!u.getMail().equalsIgnoreCase(nuovoMail)) {
            u.updateIntoSQL("mail", nuovoMail);
        }



        String nuovoRuolo = gestisci.getComboRuoloUtente_modifica().getSelectedItem().toString();
        if(!u.getRuolo().equalsIgnoreCase(nuovoRuolo)){
            u.updateIntoSQL("ruolo", nuovoRuolo);

            //associo ad ogni ruolo un indice (lo stesso indice che nella combobox è associato a ciascun ruolo)
            int ind=0;
            String r="";
            switch (r){
                case "US": ind=0;
                    break;
                case "GL": ind=1;
                    break;
                case "TL": ind=2;
            }
            if(ind > gestisci.getComboRuoloUtente_modifica().getSelectedIndex()) {
                m.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() + " " +
                        "ha promosso " + u.getMatricola() + " da " + u.getRuolo() + " a " + nuovoRuolo);
            }else
                m.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() + " " +
                        "ha declassato " + u.getMatricola() + " da " + u.getRuolo() + " a " + nuovoRuolo);

            m.insertIntoSQL();

            //aggiorno i messaggi nella home
            homeController.getMessaggiController().aggiorna();
        }
        gestisci.popolaUtenti();


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheAttivita().setVisible(false);

        gestisci.getButtonModificaAttivita().setVisible(true);

        gestisci.getButtonEliminaAttivita().setEnabled(false);



        gestisci.disabilitaComponenti(true,gestisci.getFieldDescrizioneAttivita_modifica(),gestisci.getFieldPrecedenzaAttivita_modifica()
                ,gestisci.getFieldCostoAttivta_modifica(),gestisci.getComboGiornoInizioAttivita_modifica(),gestisci.getComboMeseInizioAttivita_modifica(),
                gestisci.getComboMeseInizioAttivita_modifica(),gestisci.getComboGiornoFineAttivita_modifica(),
                gestisci.getComboMeseFineAttivita_modifica(),gestisci.getComboAnnoFineAttivita_modifica(),gestisci.getComboAnnoInizioAttivita_modifica(),
                gestisci.getComboSequenze_modifica());

        //infine rimuovo il listner di salvamodifiche
            StaticMethod.removeAllActionListener(gestisci.getButtonSalvaModificheUtente());
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

            //aggiorno il pannello  delle sequenze
            StaticMethod.popolaComboProgetti(gestisci.getComboProgetti());
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

            //aggionro i messaggi
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
            Map data = new HashMap<>();
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

    protected void popolaCampimodificaUtente(int riga){
        JTable tabellaUtenti = gestisci.getTableUtenti();
        Utente utente = new Utente(tabellaUtenti.getValueAt(riga,0).toString());
        gestisci.getFieldMatricolaUtente_modifica().setText(utente.getMatricola());
        gestisci.getFieldNomeUtente_modifica().setText(utente.getNome());
        gestisci.getFieldCognomeUtente_modifica().setText(utente.getCognome());
        gestisci.getFieldMailUtente_modifica().setText(utente.getMail());
        gestisci.getFieldTelefonoUtente_modifica().setText(utente.getTelefono());
    //TODO
        protected void popolaCampiModificaAppuntamento(int riga){
        JTable tabellaAppuntamenti=gestisci.getTableApuntamenti();

        String tipo     = tabellaAppuntamenti.getValueAt(riga,0).toString();
        String luogo    = tabellaAppuntamenti.getValueAt(riga,3).toString();
        String data     = tabellaAppuntamenti.getValueAt(riga,1).toString();
        String ora      = tabellaAppuntamenti.getValueAt(riga,2).toString();
        String verbale  = tabellaAppuntamenti.getValueAt(riga,4).toString();

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

        StaticMethod.setData(gestisci.getComboGiornoAppuntamento_modifica(), gestisci.getComboMeseAppuntamento_modifica(), gestisci.getComboAnnoAppuntamento_modifca(), data);
        StaticMethod.setOra(gestisci.getComboOraAppuntamento_modifica(),gestisci.getComboMinutiAppuntamento_modifica(),ora);
        System.out.println(ora.substring(0,2)+ " " + ora.substring(3,5));

        //caso per caso l'indice della c0mbobox in base ruolo utente da visualizzare
        int i=0;
        if(utente.getRuolo().equals("US")) i=0;
        else if(utente.getRuolo().equals("GL"))i=1;
        else if (utente.getRuolo().equals("TL"))i=2;
        //else i=0;
        gestisci.getComboRuoloUtente_modifica().setSelectedIndex(i);

        //attivo i campi rendendoli modificabili
        gestisci.disabilitaComponenti(false, gestisci.getFieldMatricolaUtente_modifica(),gestisci.getFieldNomeUtente_modifica(),
                gestisci.getFieldCognomeUtente_modifica(),gestisci.getFieldMailUtente_modifica(),
                gestisci.getFieldTelefonoUtente_modifica(),gestisci.getComboRuoloUtente_modifica());
        gestisci.getButtonSalvaModificheUtente().setVisible(true);


        //reimposto l'interfaccia grafica

        gestisci.getButtonSalvaModificheProgetto().setVisible(false);

        gestisci.getButtonModificaProgetto().setVisible(true);

        gestisci.getButtonEliminaProgetto().setEnabled(false);




    }





    }

