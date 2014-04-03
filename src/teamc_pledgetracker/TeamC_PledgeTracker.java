/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamc_pledgetracker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author leverett
 */
public class TeamC_PledgeTracker extends JFrame
{
    
    public TeamC_PledgeTracker()
    {
        setup_GUI();
    }

    public static void main(String[] args) {
        TeamC_PledgeTracker myPledgeTracker = new TeamC_PledgeTracker();
        myPledgeTracker.setVisible(true);
    }
    
    private void setup_GUI()
    {
        // JFrame methods to configure the main frame.
        this.setTitle("Pledge Tracker Application");
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configure menu and items
        this.setJMenuBar(new JMenuBar());
        this.getJMenuBar().add(new JMenu("File"));
        this.getJMenuBar().getMenu(0).add(new JMenuItem("Open"));
        this.getJMenuBar().getMenu(0).add(new JMenuItem("Save"));
        this.getJMenuBar().getMenu(0).add(new JMenuItem("Save As"));
        this.getJMenuBar().getMenu(0).addSeparator();
        this.getJMenuBar().getMenu(0).add(new JMenuItem("Quit"));

        // Create the main JPanel and set the layout.
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BoxLayout(mainJPanel, BoxLayout.X_AXIS));
        this.getContentPane().add(mainJPanel);

        mainJPanel.add(Box.createHorizontalGlue());

        JPanel middleJPanel = new JPanel();
        middleJPanel.setLayout(new BoxLayout(middleJPanel, BoxLayout.Y_AXIS));
        mainJPanel.add(middleJPanel);

        middleJPanel.add(Box.createVerticalGlue());

        // Create/add a label element.
        JLabel myJLabel = new JLabel("Hello World!");
        myJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myJLabel.setSize(new Dimension(200, 30));
        middleJPanel.add(myJLabel);

        middleJPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton exitJButton = new JButton("Exit");
        exitJButton.setMaximumSize(new Dimension(200, 30));
        exitJButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        System.exit(0);
                    }
                }
        );
        middleJPanel.add(exitJButton);

        middleJPanel.add(Box.createVerticalGlue());

        mainJPanel.add(Box.createHorizontalGlue());
    }    
}
