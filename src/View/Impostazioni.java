package View;

import javax.swing.*;

/**
 * Created by edoardo on 02/03/16.
 */
public class Impostazioni extends JPanel{


    private JPanel pannelloPrincipale;
    private JTextField campoNome;
    private JTextField campoCognome;
    private JTextField campoEmail;
    private JButton buttonEdit;
    private JButton buttonSave;
    private JButton buttonLogout;
    private JPasswordField campoPassword;

    public Impostazioni(){
        setVisible(true);
    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }
}
