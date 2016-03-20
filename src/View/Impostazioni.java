package View;

import Model.Utente;

import javax.swing.*;

/**
 * Schermata delle imppostazioni
 */
public class Impostazioni extends JPanel{


    private JPanel pannelloPrincipale;
    private JTextField campoNome;
    private JTextField campoCognome;
    private JTextField campoEmail;
    private JButton buttonModifica;
    private JButton buttonSalva;
    private JButton buttonLogout;
    private JPasswordField campoPassword;
    private JTextField campoTelefono;
    private JPasswordField campoPasswordCheck;

    public Impostazioni(){
        setVisible(true);
    }

    public void disabilitaCampi(boolean controllo){

        this.campoNome.setEnabled(!controllo);
        this.campoCognome.setEnabled(!controllo);
        this.campoEmail.setEnabled(!controllo);

        this.campoPassword.setEnabled(!controllo);
        this.campoPasswordCheck.setEnabled(!controllo);
        this.campoTelefono.setEnabled(!controllo);
        this.buttonSalva.setEnabled(!controllo);
        this.buttonModifica.setEnabled(controllo);
        this.buttonLogout.setEnabled(controllo);
    }


    //getter and setter
    public JPanel getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public void setPannelloPrincipale(JPanel pannelloPrincipale) {
        this.pannelloPrincipale = pannelloPrincipale;
    }

    public JTextField getCampoNome() {
        return campoNome;
    }

    public void setCampoNome(String campoNome) {
        this.campoNome.setText(campoNome);
    }


    public void setCampoCognome(String campoCognome) {
        this.campoCognome.setText(campoCognome);
    }


    public void setCampoEmail(String campoEmail) {
        this.campoEmail.setText(campoEmail);
    }


    public void setCampoPassword(String campoPassword) {
        this.campoPassword.setText(campoPassword);
    }


    public void setCampoTelefono(String campoTelefono){
        this.campoTelefono.setText(campoTelefono);
    }

    public JButton getButtonModifica() {
        return buttonModifica;
    }

    public JButton getButtonSalva() {
        return buttonSalva;
    }

    public JButton getButtonLogout() {
        return buttonLogout;
    }

    public JPasswordField getCampoPasswordCheck() {
        return campoPasswordCheck;
    }

    public void setCampoPasswordCheck(String campoPasswordCheck) {
        this.campoPasswordCheck.setText(campoPasswordCheck);
    }

    public JPasswordField getCampoPassword() {
        return campoPassword;
    }

    public Utente getModifiche(){
        Utente appoggio= new Utente();
        appoggio.setNome(campoNome.getText());
        appoggio.setCognome(campoCognome.getText());
        appoggio.setTelefono(campoTelefono.getText());
        appoggio.setMail(campoEmail.getText());
        appoggio.setPwd(new String(campoPassword.getPassword()));
        return appoggio;
    }

    public void displayErrorMessage(String errorMessage,String errorTitle){
       JOptionPane messaggioErrore = new JOptionPane(errorMessage,JOptionPane.ERROR_MESSAGE  );
        JDialog dialog = messaggioErrore.createDialog(errorTitle);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

}
