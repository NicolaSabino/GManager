package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicola on 13/02/16.
 */
public class Login {
    private JPanel pannelloPrincipale;
    private JPanel logBox;
    private JTextField matricolaTexrField;
    private JLabel matricolaLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel titolo;
    private JButton entraButton;

    private String log_mat;
    private char[] log_pass;

    public Login() {
        return;
    }

    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }


    //mostra una finestra di errore

    public void displayErrorMessage(String errorMessage){
        //da ricontrollare
        JOptionPane.showMessageDialog(new JFrame("errore"),errorMessage);

    }

    public JButton getEntraButton(){
        return entraButton;
    }

    public  void getLoginValue(){

        this.log_mat=matricolaTexrField.getText();
        this.log_pass=passwordField.getPassword();

    }

    public String getLog_mat() {
        return log_mat;
    }

    public char[] getLog_pass() {
        return log_pass;
    }
}
