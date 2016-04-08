package View;
import Model.Gruppi.GruppoProgetti;
import Model.Gruppi.GruppoSequenze;
import Model.Progetto;
import Model.Sequenza;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class StaticMethod {

    /**
     * Metodo per popolare le combo box da utilizzare per l'immisione di una data
     * (data di default quella corrente)
     *
     * @param giorno JComboBox da popolare con i giorni
     * @param mese   JComboBox da popolare con i mesi
     * @param anno   JComboBox da popolare con gli anni
     */
    public static void populateData1(JComboBox giorno, JComboBox mese, JComboBox anno) {
        Integer i;

        int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
        int meseCorrente = Calendar.getInstance().get(Calendar.MONTH);
        int giornoCorrente = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int posGiorno = 0, posMese = 0;

        Vector<String> giorni = new Vector<>(31);
        for (i = 1; i <= 31; i++) {
            if (i < 10) {
                giorni.add("0" + i.toString());
            } else {
                giorni.add(i.toString());
            }
            if (i == giornoCorrente) {
                posGiorno = i;
            }

        }

        Vector<String> mesi = new Vector<>(12);
        for (i = 1; i <= 12; i++) {
            if (i < 10) {
                mesi.add("0" + i.toString());
            } else {
                mesi.add(i.toString());
            }
            if (i == meseCorrente) {
                posMese = i;
            }
        }

        Vector<String> anni = new Vector<>(15);
        for (i = annoCorrente - 1; i < annoCorrente + 14; i++) {
            anni.add(i.toString());
        }

        if (giorno != null) {
            giorno.setModel(new DefaultComboBoxModel<String>(giorni));
            giorno.setSelectedIndex(posGiorno - 1);
        }

        if (mese != null) {
            mese.setModel(new DefaultComboBoxModel<String>(mesi));
            mese.setSelectedIndex(posMese);
        }

        if (anno != null) {
            anno.setModel(new DefaultComboBoxModel<String>(anni));
            anno.setSelectedIndex(1);
        }
    }

    /**
     * Metodo che popola le tre combo box per le date
     *
     * @param giorno    JComboBox per inserire i giorni
     * @param mese      JComboBox per inserire i mesi
     * @param anno      JComboBox per inserire gli anni
     * @param interval  anni da inserire prima dell'anno corrente
     * @param pos       anno da selezionare di default (0 = posizione anno più lontano, max = interval -1)
     */
    public static void populateData(JComboBox giorno, JComboBox mese, JComboBox anno, int interval, int pos){
        populateData1(giorno,mese,anno);
        Integer i;
        Vector<Integer> anni = new Vector<>();
        Integer annoCorrente = (Calendar.getInstance().get(Calendar.YEAR)) -1 ;
        int j = 0;
        for (i = interval; i >= 0; i--){
            anni.add(j, annoCorrente + i);
        }

        anno.setModel(new DefaultComboBoxModel<Integer>(anni));

        giorno.setSelectedIndex(0);
        mese.setSelectedIndex(0);
        anno.setSelectedIndex(pos);
    }





    /**
     * Metodo che setta la data passata come parametro, come elementi selezionati
     *
     * @param giorno       JComboBox contenenti i giorni
     * @param mese         JComboBox contenenti i mesi
     * @param anno         JComboBox contenenti gli anni
     * @param data         Stringa contentente la data da rappresentare
     */
    public static void setData(JComboBox giorno, JComboBox mese, JComboBox anno,String data) {
        String aa, month, gg;

        aa = data.substring(0, 4);
        month = data.substring(5, 7);
        gg = data.substring(8, 10);

        for (int i = 0; i < anno.getItemCount(); i++) {
            if (anno.getItemAt(i).toString().equalsIgnoreCase(aa)) {
                anno.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < mese.getItemCount(); i++) {
            if (mese.getItemAt(i).toString().equalsIgnoreCase(month)) {
                mese.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < giorno.getItemCount(); i++) {
            if (giorno.getItemAt(i).toString().equalsIgnoreCase(gg)) {
                giorno.setSelectedIndex(i);
            }
        }
    }
    /**
     * Metodo che setta l'ora passata come parametro, come elementi selezionati
     *
     */
    public static  void setOra(JComboBox ora,JComboBox minuti,String orario){
        int oo,mm;

        oo=Integer.parseInt(orario.substring(0,2));
        mm=Integer.parseInt(orario.substring(3,5));

        for (int i=0;i<ora.getItemCount();i++){
            if(Integer.parseInt(ora.getItemAt(i).toString())==oo){
                ora.setSelectedIndex(i);
            }
        }

        for (int i=0;i<minuti.getItemCount();i++){
            if(Integer.parseInt(minuti.getItemAt(i).toString())==mm){
                minuti.setSelectedIndex(i);
            }
        }
    }




    /**
     * Metodo che imposta un limite massimo di caratteri per un JTextField
     *
     * @param textField JTextField su cui applicare il limite
     * @param limit     Numero di caratteri permessi
     */
    public static void textFieldLimitLength(JTextField textField, int limit) {
        textField.addKeyListener(new KeyAdapter() {
            private int count = 0;

            @Override
            public void keyTyped(KeyEvent e) {
                count = textField.getText().length();
                char c = e.getKeyChar();
                if (count > limit - 1 && !(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Metodo che limita l'immissione ai soli numeri con lunghezza max uguale al valore di length
     *
     * @param textField JTextField su cui applicare il limite
     * @param length    numero massimo di numeri inseribili
     */
    public static void textFieldLimitOnlyInt(JTextField textField, int length) {
        textField.addKeyListener(new KeyAdapter() {
            private int countCar = 0;

            public void keyTyped(KeyEvent e) {
                countCar = textField.getText().length();
                char c = e.getKeyChar();

                if (!((c >= '0') && (c <= '9') && countCar < length) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Metodo che consente l'inserimento di soli numeri decimali nel textField
     *
     * @param textField JTextField su cui applicare il limite
     */
    public static void textFieldLimitOnlyDouble(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            private int numDot = 0;
            private int countCar = 0;

            public void keyTyped(KeyEvent e) {
                countCar = textField.getText().length();
                char c = e.getKeyChar();
                if (textField.getText().indexOf('.') == -1) {
                    numDot = 0;
                } else {
                    numDot = 2;
                }
                if (!((c >= '0') && (c <= '9') || ((c == '.') && numDot <= 1 && countCar != 0) || ((c == '-') && countCar == 0) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }


        });
    }


    /**
     * Metodo per limitare la lunghezza massima di caratteri immissibili nella textarea
     *
     * @param textArea  textArea da limitare
     * @param limit     numero massimo di caratteri
     */
    public static void limitTextArea(JTextArea textArea, int limit){
        textArea.addKeyListener(new KeyAdapter() {
            private int count = 0;

            @Override
            public void keyTyped(KeyEvent e) {
                count = textArea.getText().length();
                char c = e.getKeyChar();
                if (count > limit - 1 && !(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
    }

    /**
     * Metodo che controlla se un campo in cui va inserito un numero è vuoto, in tal caso inserisce uno zero,
     * altrimenti lascia il numero inserito
     *
     * @param textField     JTextField su cui effettuare il controllo
     * @return              Stringa di ritorno
     */
    public static String controllValueInt(JTextField textField){
        String value;
        if (textField.getText().equalsIgnoreCase("")){
            value = "0";
        } else {
            value = textField.getText();
        }

        return value;
    }


    /**
     * Metodo che rimuove tutti i listener presenti associati precedentemente all JButton passato come parametro
     *
     * @param button    JButton a cui togliere i listener
     */
    public static void removeAllActionListener(JButton button){
        for(ActionListener act : button.getActionListeners()) {
            button.removeActionListener(act);
        }
    }

    public static void popolaComboProgetti( JComboBox box){

        //genero i progetti disponibili nellla modalità cerca progetto e li inserisco nella combobox
        GruppoProgetti p = new GruppoProgetti();
        ArrayList<String> s = new ArrayList<String>();

        //primo campo di default
        s.add(s.size(),"<html><font color=#778899>Seleziona il progetto</font></html>");
        for(Progetto appoggio:p.getStato()){
            s.add(s.size(),appoggio.getNome());
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(s.toArray());
        box.setModel(comboBoxModel);
    }

    public static void popolaComboSequenze(JComboBox box){
        //genero le sequenze disponibili nellla modalità cerca sequenze e li inserisco nella combobox
        GruppoSequenze s = new GruppoSequenze();
        ArrayList<String> strings = new ArrayList<String>();

        //primo campo di default
        strings.add(strings.size(),"<html><font color=#778899>Seleziona la sequenza</font></html>");
        for(Sequenza appoggio:s.getStato()){
            strings.add(strings.size(),appoggio.getNome());
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(strings.toArray());
        box.setModel(comboBoxModel);
    }

    public static boolean AmaggioreB (JComboBox ggA, JComboBox mmA, JComboBox aaA, JComboBox ggB, JComboBox mmB, JComboBox aaB ){

        int annoA,meseA,giornoA;
        int annoB,meseB,giornoB;
        boolean risultato=false;


        annoA=Integer.parseInt(aaA.getSelectedItem().toString());
        annoB=Integer.parseInt (aaB.getSelectedItem().toString());
        meseA=Integer.parseInt(mmA.getSelectedItem().toString());
        meseB=Integer.parseInt(mmB.getSelectedItem().toString());
        giornoA=Integer.parseInt(ggA.getSelectedItem().toString());
        giornoB=Integer.parseInt(ggB.getSelectedItem().toString());

        if (annoA>annoB){

            risultato=true;

        }else if (meseA>meseB){

            risultato=true;

        }else if (giornoA>giornoB){

            risultato=true;
        }

        return risultato;
    }


}
