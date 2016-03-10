package View;
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
    public static void populateDataEmissione(JComboBox giorno, JComboBox mese, JComboBox anno) {
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
     * Metodo che popola le tre combo box per le date in cui va inserito un anno passato
     *
     * @param giorno    JComboBox per inserire i giorni
     * @param mese      JComboBox per inserire i mesi
     * @param anno      JComboBox per inserire gli anni
     * @param interval  anni da inserire prima dell'anno corrente
     * @param pos       anno da selezionare di default (0 = posizione anno più lontano, max = interval -1)
     */
    public static void populateData(JComboBox giorno, JComboBox mese, JComboBox anno, int interval, int pos){
        populateDataEmissione(giorno, mese, null);

        Integer i;
        Vector<Integer> anniNascita = new Vector<>();
        Integer annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
        int j = 0;
        for (i = interval; i >= 0; i--){
            anniNascita.add(j, annoCorrente + i);
        }

        anno.setModel(new DefaultComboBoxModel<Integer>(anniNascita));

        giorno.setSelectedIndex(0);
        mese.setSelectedIndex(0);
        anno.setSelectedIndex(pos);
    }

    /**
     * Metodo che popola una generica combo box con i dati passati in una arrayList di string
     * (esempio: combo box per tutti i dipendenti o tutti i preventivi)
     *
     * @param combo  JComboBox da popolare
     * @param valori ArrayList di String con le stringhe da inserire
     */
    public static void populateGenericCombo(JComboBox combo, List<String> valori) {

        Vector<String> elem = new Vector<>(valori.size() + 1);
        elem.add(0, "");
        int i = 1;
        if (valori.size() != 0){
            for (String val : valori) {
                elem.add(i, val);
                i++;
            }
        }

        combo.setModel(new DefaultComboBoxModel<String>(elem));
        combo.setSelectedIndex(0);
    }

    /**
     * Metodo che preleva il contenuto all'interno delle parentesi tonde presente nella string passata
     *
     * @param stringa Stringa da splittare
     * @return subString col valore da prelevare
     */
    public static String splitCodice(String stringa) {
        String result = "";

        int pos1, pos2;

        if (!stringa.equalsIgnoreCase("")) {
            pos1 = stringa.indexOf('(');
            pos2 = stringa.indexOf(')');

            result = stringa.substring(pos1 + 1, pos2);
        }

        return result;
    }

    /**
     * Metodo che setta il valore passato come secondo parametro all'interno della comboBox nel caso fosse presente
     *
     * @param combo JComboBox contenente i valori da verificare
     * @param value String da confrontare
     */
    public static void setOldGenericCombo(JComboBox combo, String value) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (value.equalsIgnoreCase(combo.getItemAt(i).toString())) {
                combo.setSelectedIndex(i);
            }
        }
    }

    /**
     * Metodo che setta la data passata come parametro, come elementi selezionati
     *
     * @param giorno       JComboBox contenenti i giorni
     * @param mese         JComboBox contenenti i mesi
     * @param anno         JComboBox contenenti gli anni
     * @param datiOld      Map nella quale è contenuta la data
     * @param dataKeyValue chiave della mappa dove c'è la data
     */
    public static void setOldData(JComboBox giorno, JComboBox mese, JComboBox anno, Map datiOld, String dataKeyValue) {
        String aa, month, gg;

        aa = datiOld.get(dataKeyValue).toString().substring(0, 4);
        month = datiOld.get(dataKeyValue).toString().substring(5, 7);
        gg = datiOld.get(dataKeyValue).toString().substring(8, 10);

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
     * Metodo che setta un singolo elemente nella combo box
     *
     * @param combo JComboBox da popolare
     * @param value String da inserire
     */
    public static void setSingleElemGeneric(JComboBox combo, String value) {
        Vector<String> vector = new Vector<>();
        vector.add(0, value);
        combo.setModel(new DefaultComboBoxModel<String>(vector));
        combo.setSelectedIndex(0);
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
     * Metodo per limitare la lunghezza massima di caratteri immissibili nel campo password
     *
     * @param passwordField JPasswordFiel da limitare
     * @param limit         numero massimo di caratteri
     */
    public static void limitPassword(JPasswordField passwordField, int limit) {
        passwordField.addKeyListener(new KeyAdapter() {
            private int count = 0;

            @Override
            public void keyTyped(KeyEvent e) {
                count = passwordField.getText().length();
                char c = e.getKeyChar();
                if (count > limit - 1 && !(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
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
     * Metodo che crea una finestra di dialogo con una combo box
     *
     * @param title     titolo della finestra
     * @param nomeCampo descrizione della combo box
     * @param valori    valori da inserire nella combo box
     * @return string selezionata
     */
    public static String comboBoxDialog(String title, String nomeCampo, List<String> valori) {
        String result = "";

        JComboBox<String> comboBox = new JComboBox<>();
        Vector<String> vector = new Vector<>();
        vector.add(0, "");
        vector.addAll(valori);

        comboBox.setModel(new DefaultComboBoxModel<String>(vector));
        comboBox.setSelectedIndex(0);

        Object[] message = {
                title,
                nomeCampo, comboBox
        };

        int option = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            result = comboBox.getSelectedItem().toString();
        }

        return result;
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

}
