package View;

import javax.swing.*;
import java.awt.*;

/**
 * Struttura della finestra principale
 */
public class RootFrame extends JFrame{
    private JPanel rootPanel;
    private JPanel bottomPanell;
    private JPanel leftPanell;
    private JScrollPane mainScrollPane; // Scroll SX
    private JScrollPane sideScrollPane; // Scroll DX

    /**
     * Costruttore del frame principale
     */
    public RootFrame(){
        super("G");
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1900, 900);
        setLocationRelativeTo(null);
        //setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

        Image icon = Toolkit.getDefaultToolkit().getImage("immagini/Logo_GManagerpiccolo.png");
        this.setIconImage(icon);

    }

    //setter
    public void setMainScrollPane(Component pane) {
        this.mainScrollPane.setViewportView(pane);
    }

    public void setSideScrollPane(Component pane) {
        this.sideScrollPane.setViewportView(pane);
    }

}
