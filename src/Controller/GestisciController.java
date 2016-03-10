package Controller;

import Model.Gruppi.GruppoProgetti;
import Model.MessaggioBroadcast;
import Model.Progetto;
import Model.Utente;
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
        }

        //listner
        listnerCrea();
        listnerModificaProgetto();
        listnerEliminaProgetto();

        return;
    }

    public void apriGestisci(){
        rootFrame.setMainScrollPane(gestisci.getPanelloPrincipale());
    }

    protected void listnerCrea(){
        JButton crea = gestisci.getButtonCreaProgetto();
        crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creaProgetto();
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

    protected void listnerModificaProgetto(){
        JButton modifica=gestisci.getButtonModificaProgetto();
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abilitaModificaProgetto();
            }
        });
    }

    protected void listnerSalvaModifiche(String nomevecchioprogetto){
        JButton salva = gestisci.getButtonSalvaModificheProgetto();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaModifiche(nomevecchioprogetto);
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

    protected void creaProgetto(){

        Progetto p = gestisci.getParametriCreaProgetto();
        GruppoProgetti elenco = new GruppoProgetti();
        int errore1=0;



        for(Progetto appoggio:elenco.getStato()){
            if(appoggio.getNome().compareToIgnoreCase(p.getNome())==0){
                System.out.println(appoggio.getNome().compareTo(p.getNome()));
                errore1++;
            }
        }

        if(errore1!=0){
            JOptionPane.showMessageDialog(new JFrame("errore"),p.getNome() + " é gia presente nel programma!");
        }else{
            p.insertIntoSQL();

            //inserisco un nuovo messaggio
            MessaggioBroadcast messaggioBroadcast = new MessaggioBroadcast();
            messaggioBroadcast.setMessaggio(utilizzatore.getNome() + " " + utilizzatore.getCognome() +
                    " ha creato un nuovo progetto:" + p.getNome());
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
        }

    }

    protected void abilitaModificaProgetto(){
        //creo il listner del salvataggio
        listnerSalvaModifiche(gestisci.getFieldNomeProgetto_modifica().getText());

        //modifico lo stato dell'interfaccia grafica
        gestisci.getButtonSalvaModificheProgetto().setVisible(true);
        gestisci.getButtonModificaProgetto().setVisible(false);

        gestisci.disabilitaComponenti(false,gestisci.getFieldNomeProgetto_modifica(),
                gestisci.getComboGiornoProgetto_modifica(),gestisci.getComboMeseProgetto_modifica(),
                gestisci.getComboAnnoProgetto_modifica());
    }

    protected void salvaModifiche(String nome){

        Progetto p = new Progetto(nome);

        String nuovoNome=gestisci.getFieldNomeProgetto_modifica().getText().toString();

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

            gestisci.getFieldNomeProgetto_modifica().setText("");
            gestisci.getComboGiornoProgetto_modifica().setSelectedIndex(0);
            gestisci.getComboMeseProgetto_modifica().setSelectedIndex(0);
            gestisci.getComboAnnoProgetto_modifica().setSelectedIndex(0);
            gestisci.getLabelModificaProgetto().setVisible(true);
            gestisci.getButtonEliminaProgetto().setEnabled(false);
        }


    }

    protected void popolaCampiModificaProgetto(int riga){
        JTable tabellaProgetti = gestisci.getTableProgetti();
        gestisci.getFieldNomeProgetto_modifica().setText(tabellaProgetti.getValueAt(riga,0).toString());
        Map data = new HashMap<>();
        data.put("data", tabellaProgetti.getValueAt(riga,1).toString());
        StaticMethod.setOldData(gestisci.getComboGiornoProgetto_modifica(), gestisci.getComboMeseProgetto_modifica(), gestisci.getComboAnnoProgetto_modifica(), data, "data");
    }


}
