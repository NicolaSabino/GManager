package Controller;

import Model.*;
import Model.Gruppi.Gruppo;
import Model.Gruppi.GruppoProgetti;
import Model.Gruppi.GruppoSequenze;
import View.Gestisci;
import View.RootFrame;
import View.StaticMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

    public GestisciController(RootFrame rootFrame, Utente utente,HomeController home) {
        this.rootFrame=rootFrame;
        this.gestisci= new Gestisci();
        this.utilizzatore=utente;
        this.homeController=home;

        //disabilito alcuni campi per i group leader
        if(utilizzatore.getRuolo().equals("GL")) {
            gestisci.glMode();
        }else{
            listnerSelezioneProgetto();
            listnerSelezioneSequenza();
        }

        //listner
        listnerCreaProgetto();
        listnerCreaSequenza();
        listnerCreaAttivita();
        listnerSelezioneAttivita();
        listnerModificaProgetto();
        listnerModificaSequenza();
        listnerEliminaProgetto();
        listnerEliminaSequenza();

    }

    public void apriGestisci(){
        rootFrame.setMainScrollPane(gestisci.getPanelloPrincipale());
    }

    protected void listnerCreaProgetto(){
        JButton crea = gestisci.getButtonCreaProgetto();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaProgetto();
            }
        });
    }

    protected void listnerCreaSequenza(){
        JButton crea = gestisci.getButtonCreaSequenza();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaSequenza();
            }
        });
    }

    protected void listnerCreaAttivita(){
        JButton crea = gestisci.getButtonCreaAttivita();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaAttivita();
            }
        });
    }

    protected void listnerSelezioneProgetto(){
        JTable tabellaProgetti = gestisci.getTableProgetti();
        tabellaProgetti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                popolaCampiModificaProgetto(tabellaProgetti.getSelectedRow());
                gestisci.getLabelModificaProgetto().setVisible(false);
                gestisci.getButtonModificaProgetto().setEnabled(true);
                gestisci.getButtonEliminaProgetto().setEnabled(true);
                gestisci.getTabProgetti().setSelectedIndex(1);

            }

        });
    }

    protected void listnerSelezioneSequenza(){
        JTable tabellaSequenze = gestisci.getTableSequenze();
        tabellaSequenze.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popolaCampiModificaSequenza(tabellaSequenze.getSelectedRow());
                gestisci.getLabelModificaSequenza().setVisible(false);
                gestisci.getButtonModificaSequenza().setEnabled(true);
                gestisci.getButtonEliminaSequenza().setEnabled(true);
                gestisci.getTabSequenze().setSelectedIndex(1);
            }
        });
    }

    protected void listnerSelezioneAttivita(){
        JTable tabellaAttivita = gestisci.getTableAttivita();
        tabellaAttivita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popolaCampimodificaAttivita(tabellaAttivita.getSelectedRow());
                gestisci.getLabelModificaAttivita().setVisible(false);
                gestisci.getButtonModificaAttivita().setEnabled(true);
                gestisci.getButtonEliminaAttivita().setEnabled(true);
                gestisci.getTabAttivita().setSelectedIndex(1);
            }
        });
    }

    protected void listnerModificaProgetto(){
        JButton modifica=gestisci.getButtonModificaProgetto();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificaProgetto();
            }
        });
    }

    protected void listnerModificaSequenza(){
        JButton modifica=gestisci.getButtonModificaSequenza();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificaSequenza();
            }
        });
    }

    protected void listnerSalvaModificheProgeto(String nomevecchioprogetto){
        JButton salva = gestisci.getButtonSalvaModificheProgetto();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheProgetto(nomevecchioprogetto);
            }
        });
    }

    protected void listnerSalvaModificheSequenza(String nomevecchiasequenza){
        JButton salva = gestisci.getButtonSalvaModificheSequenza();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModificheSequenza(nomevecchiasequenza);
            }
        });
    }

    protected void listnerEliminaProgetto(){
        JButton elimina=gestisci.getButtonEliminaProgetto();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaProgetto();
            }
        });
    }

    protected void listnerEliminaSequenza(){
        JButton elimina=gestisci.getButtonEliminaSequenza();
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaSequenza();
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
        }else{

                try {
                    a.setCosto(Double.parseDouble(gestisci.getFieldCostoAttivita().getText()));
                }catch (NumberFormatException e){
                    a.setCosto(0);
                }

                if(!gestisci.getFieldPrecedenzaAttivita().getText().equals("")){
                    String precedenza= gestisci.getFieldPrecedenzaAttivita().getText();
                    a.insertIntoSQL(precedenza);
                }else{
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



                //aggionro il costo della sequenza
                Sequenza s = new Sequenza(a.getNomesequenza());
                double nuovoCosto = s.getCosto() + a.getCosto();



                if(s.getCosto()!=nuovoCosto){
                    s.updateIntoSQL("costo", Double.toString(nuovoCosto));
                    //inserisco un nuovo messaggio
                    MessaggioBroadcast m = new MessaggioBroadcast();
                    m.setMessaggio("Aggiornato il costo della sequenza " + a.getNomesequenza() + ": " + nuovoCosto + " €");
                    m.setTipo("AUTO");
                    m.setMittente("AUTO");
                    m.insertIntoSQL();

                    //aggionro i messaggi
                    homeController.getMessaggiController().aggiorna();
                }



                //aggiorno l'elenco delle Attività
                gestisci.popolaAttivita();
                gestisci.popolaSequenze();

                //ripulisco crea
                gestisci.getFieldDescrizioneAttivtia().setText("");
                gestisci.getFieldCostoAttivita().setText("0");
                gestisci.getFieldPrecedenzaAttivita().setText("");
                gestisci.getComboSequenze().setSelectedIndex(0);

        }
    }

    protected void abilitaModificaProgetto(){
        //creo il listner del salvataggio
        listnerSalvaModificheProgeto(gestisci.getFieldNomeProgetto_modifica().getText());

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaModificheProgetto().setVisible(true);
        gestisci.getButtonModificaProgetto().setVisible(false);

        gestisci.disabilitaComponenti(false,gestisci.getFieldNomeProgetto_modifica(),
                gestisci.getComboGiornoProgetto_modifica(),gestisci.getComboMeseProgetto_modifica(),
                gestisci.getComboAnnoProgetto_modifica());
    }

    protected void abilitaModificaSequenza(){

        //creo il listner del salvataggio
        listnerSalvaModificheSequenza(gestisci.getFieldNomeSequenza_modifica().getText());

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaModificheSequenza().setVisible(true);
        gestisci.getButtonModificaSequenza().setVisible(false);

        gestisci.disabilitaComponenti(false,gestisci.getFieldNomeSequenza_modifica(),gestisci.getComboProgetti_modifica());

    }

    protected void salvaModificheProgetto(String nome){

        Progetto p = new Progetto(nome);

        String nuovoNome=gestisci.getFieldNomeProgetto_modifica().getText();

        String data= gestisci.getComboAnnoProgetto_modifica().getSelectedItem().toString() + "-"
                + gestisci.getComboMeseProgetto_modifica().getSelectedItem().toString() + "-"
                + gestisci.getComboGiornoProgetto_modifica().getSelectedItem().toString();


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
            //StaticMethod.popolaComboProgetti(gestisci.getComboProgetti());
        }
    }

    protected void popolaCampiModificaProgetto(int riga){
        JTable tabellaProgetti = gestisci.getTableProgetti();
        gestisci.getFieldNomeProgetto_modifica().setText(tabellaProgetti.getValueAt(riga,0).toString());
        Map data = new HashMap<>();
        data.put("data", tabellaProgetti.getValueAt(riga,1).toString());
        StaticMethod.setOldData(gestisci.getComboGiornoProgetto_modifica(), gestisci.getComboMeseProgetto_modifica(), gestisci.getComboAnnoProgetto_modifica(), data, "data");
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
            data.put("datainizio", tabellaAttivita.getValueAt(riga, 4).toString());
            data.put("datafineprevista", tabellaAttivita.getValueAt(riga, 5).toString());
            StaticMethod.setOldData(gestisci.getComboGiornoInizioAttivita_modifica(), gestisci.getComboMeseInizioAttivita_modifica(), gestisci.getComboAnnoInizioAttivita_modifica(), data, "datainizio");
            StaticMethod.setOldData(gestisci.getComboGiornoFineAttivita_modifica(), gestisci.getComboMeseFineAttivita_modifica(), gestisci.getComboAnnoFineAttivita_modifica(), data, "datafineprevista");
        }catch (Exception e){
            gestisci.getComboGiornoInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboMeseInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoInizioAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboGiornoFineAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboMeseFineAttivita_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoFineAttivita_modifica().setSelectedIndex(0);
        }
    }


}

//todo l'utente non deve poter inserire campi vuoti all'interno del programma
