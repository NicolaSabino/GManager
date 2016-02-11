import Model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by nicola on 10/02/16.
 */
public class GManager {
    public static void main(String args[]) {

        Incontro i = new Incontro("2016-01-22","milestone");
        Incontro a = new Incontro("2016-01-22","milestone");


        System.out.println((i==a) + "  " + (i.equals(a)));

    }

}
