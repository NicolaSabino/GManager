import GanttChart.Gantt;
import Model.*;
import View.*;
import Controller.*;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by nicola on 10/02/16.
 */
public class GManager {
    public static void main(String args[]) {


        //try catch per il look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        //creo il controller

        MainController controller_G = new MainController();

        /*
        final Gantt demo = new Gantt("Gantt Chart Demo 1");
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        */
    }

}
