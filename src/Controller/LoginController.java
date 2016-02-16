package Controller;

import View.RootFrame;
import View.Login;
import View.SpallaLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nicola on 14/02/16.
 */
public class LoginController {
    private RootFrame view;
    private Login loginView;

    public LoginController(RootFrame view) {
        this.view=view;

        loginView = new Login();


        view.setMainScrollPane(loginView.getPannelloPrincipale());
        view.setSideScrollPane(new SpallaLogin().getPannelloPrincipale());
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
        if(controllo.getPermesso()==Permesso.US || controllo.getPermesso()==Permesso.GL || controllo.getPermesso()==Permesso.TL){
            loginView.displayErrorMessage("login avvenuto con successo con privilegi di tipo " + controllo.getPermesso());
        }else
        {
            loginView.displayErrorMessage("Matricola o password erati");
        }


    }
}
