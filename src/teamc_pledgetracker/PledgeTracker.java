package teamc_pledgetracker;
//*****************************************************************************
// Program:     PledgeTracker
// Description: Final project for PRG/421.
//
// Author:      Learning Team C
// Date:        Various
// Version:     Versioned using Mercurial.
//*****************************************************************************
//
//      Panel layout for the GUI:
//
//      +––––––––––––––––––––––––––––––––––––––––––––+
//      | mainPanel                                  |
//      | +––––––––––––––––––––––––––––––––––––––––+ |
//      | |                                        | |
//      | | titlePanel                             | |
//      | |                                        | |
//      | +––––––––––––––––––––––––––––––––––––––––+ |
//      |                                            |
//      | +––––––––––––––––––––––––––––––––––––––––+ |
//      | |  contentPanel                          | |
//      | | +––––––––––––––––––––––––––––––––––––+ | |
//      | | |                                    | | |
//      | | | topPanel                           | | |
//      | | |                                    | | |
//      | | +––––––––––––––––––––––––––––––––––––+ | |
//      | |                                        | |
//      | | +––––––––––––––––––––––––––––––––––––+ | |
//      | | |                                    | | |
//      | | | Table goes here.                   | | |
//      | | |                                    | | |
//      | | |                                    | | |
//      | | |                                    | | |
//      | | +––––––––––––––––––––––––––––––––––––+ | |
//      | |                                        | |
//      | +––––––––––––––––––––––––––––––––––––––––+ |
//      |                                            |
//      +––––––––––––––––––––––––––––––––––––––––––––+
//
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PledgeTracker extends JFrame
{
    DAL dal;
    private Popup tooltipPopup;

    String[] colNames = {
            "Philanthropist Name",
            "Donation Amount (USD)",
            "Charity Name"
    };

    String[] charityNames = {
        "Wounded Warrior Project",
        "American Red Cross",
        "Doctors Without Borders",
        "St. Jude Children's Research Hospital",
        "World Vision",
        "Save the Children",
        "American Cancer Society",
        "American Society for the Prevention of Cruelty to Animals",
        "Disabled American Veterans",
        "Charitable Service Trust",
        "United Way",
        "Salvation Army",
        "American Cancer Society",
        "Food for the Poor",
        "YMCA of the USA",
        "Feed the Children",
        "AmeriCares Foundation",
        "Catholic Charities USA",
        "Gifts in Kind International"
    };

    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel contentPanel;
    private JPanel topPanel;

    private JLabel titleLabel;
    //
    private JTable mainTable;
    private DefaultTableModel mainTableModel;
    private JScrollPane mainTableScrollPane;
    //
    private JButton donateButton;
    //
    private JLabel nameLabel;
    private JLabel amntLabel;
    private JLabel gotoLabel;
    //
    private JTextField nameTextField;
    private JComboBox charityComboBox;
    private JSpinner charitySpinner;


    public PledgeTracker()
    {
        // JFrame methods to configure the main frame.
        setTitle("City of Kelsey Pledge Tracker");
        setSize(480, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gui_setup_panels();
        gui_create_elements();
        gui_configure_elements();
        gui_add_elements();

        dal = new DAL();
        java.util.List<Pledge> m_pledges = dal.GetPledges();
        if(m_pledges != null)
        {
            for (Pledge pledge : m_pledges)
            {
                add_pledge_to_table(pledge);
            }
        }
    }

    public static void main(String[] args)
    {
        PledgeTracker myPledgeTracker = new PledgeTracker();
        myPledgeTracker.setVisible(true);
    }

    private void gui_setup_panels()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        getContentPane().add(mainPanel);

        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(0, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        GridLayout topPanelLayout = new GridLayout(4, 2);
        topPanelLayout.setHgap(10);
        topPanelLayout.setVgap(10);

        topPanel = new JPanel();
        topPanel.setLayout(topPanelLayout);
        contentPanel.add(topPanel, BorderLayout.PAGE_START);
    }

    private void gui_create_elements()
    {
        ImageIcon image = new ImageIcon(getClass().getResource("./title.jpg"));
        titleLabel = new JLabel("", image, JLabel.CENTER);
        titleLabel.setPreferredSize(new Dimension(480, 100));

        nameLabel = new JLabel("Philanthropist Full Name: ");
        amntLabel = new JLabel("Donation Amount (USD): ");
        gotoLabel = new JLabel("Charity Organization Name: ");

        donateButton = new JButton("Donate");

        Arrays.sort(charityNames);
        charityComboBox = new JComboBox(charityNames);
        nameTextField = new JTextField();

        charitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));

        mainTableModel = new DefaultTableModel();
        mainTable = new JTable(mainTableModel);
        mainTableScrollPane = new JScrollPane(mainTable);
    }

    private void gui_configure_elements()
    {
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        amntLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gotoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        nameTextField.setHorizontalAlignment(SwingConstants.CENTER);

        donateButton.addActionListener(do_donate);

        mainTableModel.setColumnIdentifiers(colNames);
        mainTable.setFillsViewportHeight(true);
    }

    private void gui_add_elements()
    {
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        topPanel.add(nameLabel);
        topPanel.add(nameTextField);

        topPanel.add(amntLabel);
        topPanel.add(charitySpinner);

        topPanel.add(gotoLabel);
        topPanel.add(charityComboBox);

        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(donateButton);

        contentPanel.add(mainTableScrollPane, BorderLayout.CENTER);
    }

    private void add_pledge_to_table(Pledge pledge)
    {
        mainTableModel.addRow(
                new String[] {
                        pledge.getName(),
                        Integer.toString(pledge.getPledgeamt()),
                        pledge.getCharity(),
                }
        );
    }
    
    private void showTooltip(int seconds) {
      tooltipPopup.show();
      javax.swing.Timer t= new javax.swing.Timer(seconds*1000, new ActionListener() {
          @Override
        public void actionPerformed(ActionEvent e) {
       tooltipPopup.hide(); // disposes of the popup.
        }
      });
      t.setRepeats(false);
      t.start();
    }
    
    private boolean ValidateFields() {
        boolean bRet = false;
        JTextField tf =  ((JSpinner.DefaultEditor)charitySpinner.getEditor()).getTextField();           
        String name = nameTextField.getText();
        if (name.length() <= 0) {
            return bRet;
        }
        Integer amount = (Integer)charitySpinner.getValue();
        if (amount <= 0) {
            tf.setBackground(Color.RED);                  
            tf.requestFocusInWindow();
            final PopupFactory popupFactory = PopupFactory.getSharedInstance();
            final JToolTip toolTip = new JToolTip();
            toolTip.setTipText("You must enter a valid donation.");
            tooltipPopup = popupFactory.getPopup(tf, toolTip,
                     tf.getLocationOnScreen().x +10,
                     tf.getLocationOnScreen().y +25);
            showTooltip(5);
            return bRet;
        }
        bRet = true;
        return bRet;
    }
    
    private void ClearFields() {
        nameTextField.setText("");
        charitySpinner.setValue(new Integer("0"));
        charityComboBox.setSelectedIndex(0);
    }

    ActionListener do_donate = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //reset background to white in case it was red for invalid entry
            JTextField tf =  ((JSpinner.DefaultEditor)charitySpinner.getEditor()).getTextField();
            tf.setBackground(Color.WHITE);
            //check entered data for validity
            if (!ValidateFields()) {
                return;
            }
            
            String name = nameTextField.getText();
            Integer amount = (Integer)charitySpinner.getValue();          
            String charity = charityComboBox.getSelectedItem().toString();
            Pledge pledge = new Pledge(name, charity, amount);

            if (dal.SavePledge(pledge))
            {
                add_pledge_to_table(pledge);
                ClearFields();
            }
            else
            {
                // Something???
            }
        }
    };
}
