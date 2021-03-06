package Controller;

import GanttChart.Gantt;
import GanttChart.SlidingGantt;
import Model.Attivita;
import Model.Progetto;
import Model.Sequenza;
import View.*;
import org.jfree.ui.RefineryUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Controller della schermata di ricerca
 *
 */
public class RicercaController {

    private RootFrame rootFrame;
    private Ricerca ricerca;
    private int mod;
    private String campoDiRIcerca;
    private JTable tabellaOttenuta;


    public RicercaController(RootFrame rootFrame) {
        this.rootFrame=rootFrame;
        this.ricerca= new Ricerca();
        this.mod=1;

        stampaRisultato();

        //attivo i listner
        listnerComboBox();
        listnerCombo();
        listnerCerca();
        listnerSalvaRicerca();
        listenerGantt();
        return;
    }


    public void apriPannelloRicerca(){
        rootFrame.setMainScrollPane(ricerca.getPrimoPanel());
    }

    /**
     * Listner della combobox principale
     */
    private void listnerComboBox(){
        JComboBox comboBox = ricerca.getComboBoxRicerca();
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if(e.getItem()=="Utente"){
                   modalitaView(1);
               }else if(e.getItem()=="Attività"){
                   modalitaView(2);
               }else if(e.getItem()=="Sequenza") {
                   modalitaView(3);
               }else if(e.getItem()=="Progetto") {
                   modalitaView(4);
               }

            }
        });
    }

    /**
     * ItemListner della combobox di progetti/sequenze
     */
    private void listnerCombo(){
        JComboBox box = ricerca.getComboProgettiSequenze();

        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setCampoDiRIcerca(e.getItem().toString());
                stampaRisultato();
                ricerca.getGanttButton().setEnabled(true);
            }
        });
    }

    /**
     * keylistner di ricerca che chiamano il metodo stampa risultato
     */
    private void listnerCerca(){
        //JButton cerca =ricerca.getCerca();
        JTextField textField1 = ricerca.getCampo1();
        JTextField textField2 = ricerca.getCampo2();

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                stampaRisultato();
            }
        });

        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                stampaRisultato();
            }
        });

    }

    /**
     * keylistener che richiama un frame contenente il grafico del gantt
     */
    private void listenerGantt(){
        JButton button = ricerca.getGanttButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*
                SlidingGantt gantt = new SlidingGantt(" gantt ",ricerca.getComboProgettiSequenze().getSelectedItem().toString());
                gantt.pack();
                RefineryUtilities.centerFrameOnScreen(gantt);
                gantt.setVisible(true);
                */


                Gantt demo = new Gantt("Gantt",ricerca.getComboProgettiSequenze().getSelectedItem().toString());
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
                
            }
        });
    }


    /**
     * metodo che stampa a video sotto forma tabellare il risultato della ricerca
     */
    private void stampaRisultato(){
        switch (mod){
            case 1 :{

                    //ricerca per nome
                    Model.Gruppi.Gruppo g = new Model.Gruppi.Gruppo();
                    g.createFrom("nome+cognome",ricerca.getTestoCampo1(),ricerca.getTestoCampo2());
                    TabellaUtenti tabellaUtenti = new TabellaUtenti();
                    tabellaUtenti.setModelTabella(g.getElenco());
                    ricerca.setScrollRIcerca(tabellaUtenti.getPannelloPrincipale());

                    this.tabellaOttenuta=tabellaUtenti.getElencoUtenti();
                break;
            }
            case 2:{

                //ricerca attività
                Sequenza s = new Sequenza("descrizione",ricerca.getTestoCampo1());
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                tabellaAttivita.setModelTabella(s.getStato());
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());
                this.tabellaOttenuta=tabellaAttivita.getTabella();
                break;
            }
            case 3:{
                //ricerca Sequenza
                Sequenza s = new Sequenza(this.campoDiRIcerca);
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                tabellaAttivita.setModelTabella(s.getStato());
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());
                this.tabellaOttenuta=tabellaAttivita.getTabella();
                break;
            }
            case 4:{
                //ricerca progetto
                Progetto p = new Progetto(this.campoDiRIcerca);
                TabellaAttivita tabellaAttivita = new TabellaAttivita();
                ArrayList<Attivita> risultatoRicerca = new ArrayList<Attivita>();

                //seleziono tutte le attività di un determinato progetto considerando le sue sequenze
                for(Sequenza appoggio:p.getStato()){
                    for(Attivita app:appoggio.getStato()){
                        risultatoRicerca.add(risultatoRicerca.size(),app);
                    }
                }

                tabellaAttivita.setModelTabella(risultatoRicerca);
                ricerca.setScrollRIcerca(tabellaAttivita.getPannelloPrincipale());
                this.tabellaOttenuta=tabellaAttivita.getTabella();

               return;
            }
        }
    }

    /**
     * metodo che setta la MODALIÀ DI VIEW
     * @param mod
     */
    private void modalitaView(int mod){
        switch (mod){
            case 1 :{
                ricerca.CercaUtente();
                this.mod=1;
                break;
            }
            case 2:{
                ricerca.CercaAttivita();
                this.mod=2;
                break;
            }
            case 3:{
                ricerca.CercaSequenza();
                this.mod=3;
                break;
            }
            case 4:{
                ricerca.CercaProgetto();
                this.mod=4;
                break;
            }
        }
    }


    /**
     * Action per l'esportazione in file .csv
     * con dialog per la scelta del path
     *
     */
    private void listnerSalvaRicerca() {
        this.tabellaOttenuta = tabellaOttenuta;
        JButton salva = ricerca.getSalvaRicerca();
        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "";
                String filename = ricerca.getFileName();
                if(!filename.isEmpty()) {
                    String estensione = filename.substring(filename.length() - 4, filename.length());
                    if (estensione.equals(".csv") || estensione.equals(".txt")){
                        // va bene
                        path = filename;
                    } else {
                        // non va bene
                        path = filename + ".csv";
                    }
                }
                try {
                    salvaCSV(path);
                } catch (FileNotFoundException e1) {
                    ricerca.displayErrorMessage("Errore, file non creato!", "errore!");//messaggio in caso id errore
                }
            }
        });
    }

    /**
     * Metodo per la creazione di file .csv o .txt
     *
     *
     * @param posizione String del path di destinazione
     * @throws FileNotFoundException
     */
    private void salvaCSV(String posizione)throws FileNotFoundException{

        try {
            File file = new File(posizione);
            FileWriter fileWriter = new FileWriter(file);
            String appoggio;
            for(int col = 0;col < tabellaOttenuta.getModel().getColumnCount();col ++){//header
                appoggio= tabellaOttenuta.getModel().getColumnName(col);
                appoggio = appoggio.replace("\n", " ");
                fileWriter.write(appoggio);//column name stampa header
                if(col +1 == tabellaOttenuta.getModel().getColumnCount())fileWriter.write("\n");
                else fileWriter.write(", ");
            }

            for (int rig = 0; rig < tabellaOttenuta.getModel().getRowCount();rig ++) {
                for (int col = 0;col < tabellaOttenuta.getModel().getColumnCount();col ++) {
                    appoggio= tabellaOttenuta.getModel().getValueAt(rig, col) + "";
                    appoggio = appoggio.replace("\n", " "); //correzione bug \n
                    fileWriter.write(appoggio);
                    if (col + 1 == tabellaOttenuta.getModel().getColumnCount()) fileWriter.write("\n");
                    else fileWriter.write(", ");
                }
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setCampoDiRIcerca(String campoDiRIcerca) {
        this.campoDiRIcerca = campoDiRIcerca;
    }
}
