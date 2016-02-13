package View;

import javax.swing.*;


/**
 * Created by nicola on 12/02/16.
 */
public class RootFrame extends JFrame {
    private JPanel rootPanel;
    private JScrollPane sideBarScroll;
    private JScrollPane mainScroll;

    public RootFrame(){
        super("Root Frame");

        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        sideBarScroll.setViewportView(new Spalla_login().getSpallaLogin());
        mainScroll.setViewportView(new LoginBox().getLoginBox());
        
        setExtendedState(MAXIMIZED_BOTH);

        setVisible(true);
    }
}
