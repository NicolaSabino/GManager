package Controller;

import Model.*;
import View.*;


import javax.swing.*;
import java.awt.*;

/**
 * Created by nicola on 13/02/16.
 */
public class MainController {
    RootFrame view; //conterrà la view
    private LoginController loginController;

    /**
     * Costruttore del controller principale che fa avviare tutti i sotto-controller
     */
    public MainController(){
        //creo il frame principale
        this.view = new RootFrame();

        //prima di far partire i vari controller faccio partire quello del login
        loginController = new LoginController(this.view);
        loginController.loginListener();

        return;
    }





}
