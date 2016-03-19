package Controller;

import View.NuovoMessaggio;
import View.RootFrame;
import View.Login;
import View.Spalla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestisce il login
 */
public class LoginController {
    private RootFrame rootFrame;
    private Login loginView;
    private Spalla spalla;

    /**
     * costruttore del controller di login
     * @param view frame principale
     */
    public LoginController(RootFrame view) {
        this.rootFrame=view;

        loginView = new Login();

        //all'atto della creazione si inseriscono la maschera di login al mainSP e la spalla vuota all sideSP
        view.setMainScrollPane(new Login().getPannelloPrincipale());
        this.spalla = new Spalla();
        spalla.loginMode();
        view.setSideScrollPane(spalla.getPannelloPrincipale());
        view.setMainScrollPane(loginView.getPannelloPrincipale());
        loginListener();
        return;
    }

    /**
     * listener del bottone di login
     */
    public void loginListener(){
        JButton loginButton = loginView.getEntraButton();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
    }

    /**
     * metodo che controlla che la matricola e la password sia corretti
     */
    private void loginAction(){
        loginView.getLoginValue(); //serve per leggere i due textfield
        WhiteList controllo= new WhiteList(loginView.getLog_mat(),loginView.getLog_pass());

        //meccanismo di display dei risultati

        if(controllo.getPermesso()==Permesso.US || controllo.getPermesso()==Permesso.GL || controllo.getPermesso()==Permesso.TL){
            SpallaController s = new SpallaController(controllo.getPermesso(),spalla,loginView.getLog_mat(),rootFrame,loginView.getLog_mat());
        }else
        {
            loginView.displayErrorMessage("Matricola o password erati");
        }



    }
}
