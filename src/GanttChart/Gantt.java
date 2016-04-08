package GanttChart;

/**
 * Created by nicola on 06/04/16.
 */

/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * ---------------
 * GanttDemo1.java
 * ---------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: GanttDemo1.java,v 1.12 2004/04/26 19:11:54 taqua Exp $
 *
 * Changes
 * -------
 * 06-Jun-2002 : Version 1 (DG);
 * 10-Oct-2002 : Modified to use DemoDatasetFactory (DG);
 * 10-Jan-2003 : Renamed GanttDemo --> GanttDemo1 (DG);
 * 16-Oct-2003 : Shifted dataset from DemoDatasetFactory to this class (DG);
 *
 */

        import Model.Attivita;
        import Model.Progetto;
        import Model.Sequenza;
        import com.sun.javaws.jnl.JavaFXAppDesc;
        import org.jfree.chart.ChartFactory;
        import org.jfree.chart.ChartPanel;
        import org.jfree.chart.JFreeChart;
        import org.jfree.data.category.IntervalCategoryDataset;
        import org.jfree.data.gantt.Task;
        import org.jfree.data.gantt.TaskSeries;
        import org.jfree.data.gantt.TaskSeriesCollection;
        import org.jfree.data.time.SimpleTimePeriod;

        import javax.swing.*;
        import java.awt.*;
        import java.util.Calendar;
        import java.util.Date;




/**
 * Classe che mostra a schermo il gantt di un
 *
 * [ si utilizzano le API di JFreeChart ]
 *
 */
public class Gantt extends JFrame {

    private String nomeProgetto;
    private Progetto progetto;

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public Gantt(String title, String nomeProgetto) {

        super(title);

        this.nomeProgetto= nomeProgetto;
        this.progetto= new Progetto(nomeProgetto);

        setVisible(true);
        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(700,750));
        setContentPane(chartPanel);

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    *
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************

    /**
     * Crea un dataset per il gantt
     *
     * @return dataset
     */
    public  IntervalCategoryDataset createDataset() {

        // fff

        TaskSeries taskseries = new TaskSeries("Elendo delle attività di " + progetto.getNome());
           /* Day day = new Day();
            Day day1 = new Day();
            for (int i = 0; i < 50; i++) {
                int j = (int) (Math.random() * 10D) + 1;
                for (int k = 0; k < j; k++)
                    day1 = (Day) day1.next();

                taskseries.add(new Task("Task " + i, new Date(day.getMiddleMillisecond()), new Date(day1.getMiddleMillisecond())));
                day = (Day) day1.next();
                day1 = (Day) day1.next();
            }*/

        for (Sequenza sequenza:progetto.getStato()){

            //aggiungo le attività alla sequenza
            for(Attivita attivita:sequenza.getStato()){
                taskseries.add(attivita.getTask());
            }
        }

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(taskseries);
        return dataset;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
                this.nomeProgetto,  // chart title
                "Attività",              // domain axis label
                "Data",              // range axis label
                dataset,             // data
                false,                // include legend
                true,                // tooltips
                true                // urls
        );
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;
    }

}
