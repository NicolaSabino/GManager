package View;

import javax.swing.*;

/**
 * Created by nicola on 21/03/16.
 */
public class infoSulProgramma {
    private JPanel panelloPrincipale;
    private JButton buttonIndietro;
    private JLabel mailNicola;
    private JLabel mailEdoardo;
    private JLabel mailJacopo;

    public infoSulProgramma() {
        mailNicola.setEnabled(true);
        mailEdoardo.setEnabled(true);
        mailJacopo.setEnabled(true);
    }

    public JPanel getPanelloPrincipale() {
        return panelloPrincipale;
    }

    public JButton getButtonIndietro() {
        return buttonIndietro;
    }
}
