package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nicola on 13/02/16.
 */
public class RootFrame extends JFrame{
    private JPanel rootPanel;
    private JPanel bottomPanell;
    private JPanel leftPanell;
    private JScrollPane mainScrollPane;
    private JScrollPane sideScrollPane;
    private JLabel link;

    /**
     * Costruttore del frame principale
     */
    public RootFrame(){
        super("G");
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);
        //setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        //all'atto della creazione si inseriscono la maschera di login al mainSP e la spalla vuota all sideSP
        this.setMainScrollPane(new Login().getPannelloPrincipale());
        this.setSideScrollPane(new SpallaLogin().getSpallaLogin());
    }


    public void setMainScrollPane(Component pane) {
        this.mainScrollPane.setViewportView(pane);
    }

    public void setSideScrollPane(Component pane) {
        this.sideScrollPane.setViewportView(pane);
    }

}