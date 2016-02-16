package Controller;

import View.RootFrame;
import View.Login;
import View.Spalla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nicola on 14/02/16.
 */
public class LoginController {
    private RootFrame view;
    private Login loginView;
    private Spalla spalla;

    public LoginController(RootFrame view) {
        this.view=view;

        loginView = new Login();

        //all'atto della creazione si inseriscono la maschera di login al mainSP e la spalla vuota all sideSP
        view.setMainScrollPane(new Login().getPannelloPrincipale());
        this.spalla = new Spalla();
        spalla.loginMode();
        view.setSideScrollPane(spalla.getPannelloPrincipale());
        view.setMainScrollPane(loginView.getPannelloPrincipale());
        return;
    }

    public void loginListener(){
        JButton loginButton = loginView.getEntraButton();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
    }


    private void loginAction(){
        loginView.getLoginValue(); //serve per leggere i due textfield
        Acl controllo= new Acl(loginView.getLog_mat(),loginView.getLog_pass());

        //meccanismo di display dei risultati

        if(controllo.getPermesso()==Permesso.US || controllo.getPermesso()==Permesso.GL || controllo.getPermesso()==Permesso.TL){
            HomeController h = new HomeController(controllo.getPermesso(),spalla,loginView.getLog_mat());
        }else
        {
            loginView.displayErrorMessage("Matricola o password erati");
        }


    }
}
