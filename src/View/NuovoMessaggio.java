package View;

import Model.Utente;

import javax.swing.*;

/**
 * Created by nicola on 19/02/16.
 */
public class NuovoMessaggio extends JFrame{
    private JPanel pannelloPrincipale;
    private JLabel nuovoM;
    private JButton inviaButton;
    private JEditorPane testo;

    public NuovoMessaggio(){
        super("Nuovo Messaggio");
        setContentPane(pannelloPrincipale);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getInviaButton() {
        return inviaButton;
    }

    public String getTesto() {
        return this.testo.getText();
    }
}
