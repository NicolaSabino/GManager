package View;

import Model.Utente;

import javax.swing.*;

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

    //getter
    public JButton getInviaButton() {
        return inviaButton;
    }

    public String getTesto() {
        return this.testo.getText();
    }
}
