package br.inpe.app.triangle.internalframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * @author Heitor
 * @since 02/06/2016
 */
public class StockProgressBar extends JPanel {

    private final JProgressBar progressBar;
    private final JDialog progressDialog;

    public StockProgressBar() {
        Window thisWin = SwingUtilities.getWindowAncestor(this);
        progressDialog = new JDialog(thisWin, "Please Wait...");

        progressBar = new JProgressBar(0, 100);
        progressBar.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 50));
        progressBar.setValue(0);
        progressBar.setIndeterminate(true);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 10, 20, 10));
        contentPane.setPreferredSize(new Dimension(300, 100));
        contentPane.add(progressBar);

        progressDialog.setContentPane(contentPane);
        progressDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        progressDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(
//                this.getClass().getResource("Resources/Load.png")));

        progressDialog.pack();
        progressDialog.setLocationRelativeTo(null);

        progressDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        final Task task = new Task("StockControl");
        task.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equalsIgnoreCase("progress")) {
                    int progress = (Integer) evt.getNewValue();
                    if (progress == 0) {
                        progressBar.setIndeterminate(true);
                    } else {
                        progressBar.setIndeterminate(false);
                        progressBar.setString(null);
                        progressBar.setValue(progress);
                        progressDialog.dispose();
                    }
                }
            }
        });
        progressDialog.setVisible(true);
        task.execute();

    }

    private class Task extends SwingWorker<Void, Void> {

        private static final int SLEEP_TIME = 1000;
        private String text;

        public Task(String text) {
            this.text = text;
        }

        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            try {
                Thread.sleep(random.nextInt(SLEEP_TIME));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            //JFrame
            TriangleInternalFrame app = new TriangleInternalFrame();
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            app.setSize(1300, 760);
            app.setResizable(false);
            app.setVisible(true);

            progress += random.nextInt(10);
            setProgress(Math.min(progress, 100));
            return null;
        }

        @Override
        public void done() {
            System.out.println(text + " is done Loading");
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StockProgressBar::new);
    }
}