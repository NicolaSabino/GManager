import Model.*;
import View.*;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created by nicola on 10/02/16.
 */
public class GManager {
    public static void main(String args[]) {
	  
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

        RootFrame rootFrame = new RootFrame();
    }

}
