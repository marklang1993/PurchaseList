package purchaselist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class QueryFrame extends JFrame {

    final MainFrame MF;

    public QueryFrame(MainFrame MF) {
        this.MF = MF;
        Initialize();
    }

    private void Initialize() {
        Container ContentPane = getContentPane();
        ContentPane.setLayout(new BorderLayout());
        //Add TabbedPane
        JTabbedPane TabbedPane = new JTabbedPane();
        TabbedPane.addTab("Accurate", new TabbedPane_QueryWithAccurateConditions(MF));
        TabbedPane.addTab("ID", new TabbedPane_QueryWithID(MF));
        TabbedPane.addTab("SQL", new TabbedPane_QueryWithSQL(MF));
        ContentPane.add(TabbedPane);
        //Add Listener
        addWindowListener(new QueryFrameAdapter(this));
    }
}

//For JTabbedPane
class TabbedPane_QueryWithAccurateConditions extends JPanel implements ActionListener {

    private Data_Package QueryDataPackage;
    final MainFrame MF;
    //Components
    final private JTextField YearTextField;
    final private JTextField MonthTextField;
    final private JTextField DayTextField;
    final private JTextField CurrencyTextField;
    final private JTextField TrackingNumberTextField;
    final private JTextField ExpressCompanyTextField;
    final private JTextField ExpressageTextField;
    final private JTextField TitleTextField;
    final private JTextField CategoryTextField;
    final private JTextField ProducerTextField;
    final private JTextField PriceTextField;
    final private JTextField AdditionalFeeTextField;
    final private JTextField AmountTextField;
    final private JComboBox ImportanceComboBox;
    final private JComboBox ConditionsComboBox;
    final private JComboBox BoughtComboBox;
    final private JTextField URLTextField;
    final private JTextArea InfoTextArea;

    public TabbedPane_QueryWithAccurateConditions(MainFrame MF) 
    {
        this.MF = MF;
        setLayout(new FlowLayout(FlowLayout.LEFT));

        //Components
        //DateSet Panel
        JPanel DateSet = new JPanel(new FlowLayout());
        {
            JLabel YearLabel = new JLabel("Year: ", JLabel.CENTER);
            YearTextField = new JTextField("", 5);
            JLabel MonthLabel = new JLabel("Month: ", JLabel.CENTER);
            MonthTextField = new JTextField("", 3);
            JLabel DayLabel = new JLabel("Day: ", JLabel.CENTER);
            DayTextField = new JTextField("", 3);
            DateSet.add(YearLabel);
            DateSet.add(YearTextField);
            DateSet.add(MonthLabel);
            DateSet.add(MonthTextField);
            DateSet.add(DayLabel);
            DateSet.add(DayTextField);
        }
        //CurrencySet Panel
        JPanel CurrencySet = new JPanel(new FlowLayout());
        {
            JLabel CurrencyLabel = new JLabel("Currency: ", JLabel.CENTER);
            CurrencyTextField = new JTextField("", 4);
            CurrencySet.add(CurrencyLabel);
            CurrencySet.add(CurrencyTextField);
        }
        //TrackingNumberSet Panel
        JPanel TrackingNumberSet = new JPanel(new FlowLayout());
        {
            JLabel TrackingNumberLabel = new JLabel("TrackingNumber: ", JLabel.CENTER);
            TrackingNumberTextField = new JTextField("", 25);
            TrackingNumberSet.add(TrackingNumberLabel);
            TrackingNumberSet.add(TrackingNumberTextField);
        }
        //ExpressCompanySet Panel
        JPanel ExpressCompanySet = new JPanel(new FlowLayout());
        {
            JLabel ExpressCompanyLabel = new JLabel("ExpressCompany: ", JLabel.CENTER);
            ExpressCompanyTextField = new JTextField("", 20);
            ExpressCompanySet.add(ExpressCompanyLabel);
            ExpressCompanySet.add(ExpressCompanyTextField);
        }
        //ExpressageSet Panel
        JPanel ExpressageSet = new JPanel(new FlowLayout());
        {
            JLabel ExpressageLabel = new JLabel("Expressage: ", JLabel.CENTER);
            ExpressageTextField = new JTextField("", 10);
            ExpressageSet.add(ExpressageLabel);
            ExpressageSet.add(ExpressageTextField);
        }
        //TitleSet Panel
        JPanel TitleSet = new JPanel(new FlowLayout());
        {
            JLabel TitleLabel = new JLabel("Title: ", JLabel.CENTER);
            TitleTextField = new JTextField("", 20);
            JLabel AmountLabel = new JLabel("Amount: ", JLabel.CENTER);
            AmountTextField = new JTextField("", 5);
            TitleSet.add(TitleLabel);
            TitleSet.add(TitleTextField);
            TitleSet.add(AmountLabel);
            TitleSet.add(AmountTextField);
        }
        //CategorySet Panel
        JPanel CategorySet = new JPanel(new FlowLayout());
        {
            JLabel CategoryLabel = new JLabel("Category: ", JLabel.CENTER);
            CategoryTextField = new JTextField("", 10);
            CategorySet.add(CategoryLabel);
            CategorySet.add(CategoryTextField);
        }
        //ProducerSet Panel
        JPanel ProducerSet = new JPanel(new FlowLayout());
        {
            JLabel ProducerLabel = new JLabel("Producer: ", JLabel.CENTER);
            ProducerTextField = new JTextField("", 20);
            ProducerSet.add(ProducerLabel);
            ProducerSet.add(ProducerTextField);
        }
        //PriceSet Panel
        JPanel PriceSet = new JPanel(new FlowLayout());
        {
            JLabel PriceLabel = new JLabel("Price: ", JLabel.CENTER);
            PriceTextField = new JTextField("", 10);
            JLabel AdditionalFeeLabel = new JLabel("AdditionalFee: ", JLabel.CENTER);
            AdditionalFeeTextField = new JTextField("", 10);
            PriceSet.add(PriceLabel);
            PriceSet.add(PriceTextField);
            PriceSet.add(AdditionalFeeLabel);
            PriceSet.add(AdditionalFeeTextField);
        }
        //ImportanceSet Panel
        JPanel ImportanceSet = new JPanel(new FlowLayout());
        {
            JLabel ImportanceLabel = new JLabel("Importance: ", JLabel.CENTER);
            ImportanceComboBox = new JComboBox(MF.ImportanceText);
            ImportanceSet.add(ImportanceLabel);
            ImportanceSet.add(ImportanceComboBox);
        }
        //AttributeSet Panel
        JPanel AttributeSet = new JPanel(new FlowLayout());
        {
            JLabel ConditionsLabel = new JLabel("Condition: ", JLabel.CENTER);
            ConditionsComboBox = new JComboBox(MF.ConditionsText);
            JLabel BoughtLabel = new JLabel("Bought? ", JLabel.CENTER);
            BoughtComboBox = new JComboBox(MF.BoughtText);
            AttributeSet.add(ConditionsLabel);
            AttributeSet.add(ConditionsComboBox);
            AttributeSet.add(BoughtLabel);
            AttributeSet.add(BoughtComboBox);
        }
        //URLSet Panel
        JPanel URLSet = new JPanel(new FlowLayout());
        {
            JLabel URLLabel = new JLabel("URL: ", JLabel.CENTER);
            URLTextField = new JTextField("", 30);
            URLSet.add(URLLabel);
            URLSet.add(URLTextField);
        }
        //Info
        JLabel InfoLabel = new JLabel(" Info:      ", JLabel.CENTER);
        InfoTextArea = new JTextArea(12, 38);
        JScrollPane InfoTextAreaJSP = new JScrollPane(InfoTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //QueryButton
        JButton QueryButton = new JButton("Query");
        //Add Components
        add(DateSet);
        add(CurrencySet);
        add(TrackingNumberSet);
        add(ExpressCompanySet);
        add(ExpressageSet);
        add(TitleSet);
        add(CategorySet);
        add(ProducerSet);
        add(PriceSet);
        add(ImportanceSet);
        add(AttributeSet);
        add(URLSet);
        add(InfoLabel);
        add(InfoTextAreaJSP);
        add(QueryButton);
        //Listener
        QueryButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if ("Query".equals(ae.getActionCommand())) 
        {
            //Check Empty(Date)
            if ("".equals(YearTextField.getText()) || "".equals(MonthTextField.getText()) || "".equals(DayTextField.getText()))
            {
                //MsgBox: Date cannot be empty
                JOptionPane.showMessageDialog(this, "Date cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Validation(Date)
            QueryDataPackage = new Data_Package();
            try
            {
                //Parse
                QueryDataPackage.year = Integer.parseInt(YearTextField.getText());
                QueryDataPackage.month = Integer.parseInt(MonthTextField.getText());
                QueryDataPackage.day = Integer.parseInt(DayTextField.getText());
                //Validate
                //Year
                if(QueryDataPackage.year < 1970) 
                {
                    //MsgBox: Year cannot be early than 1970!
                    JOptionPane.showMessageDialog(this, "Year cannot be early than 1970!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Month
                if (QueryDataPackage.month > 12 || QueryDataPackage.month < 1)
                {
                    //MsgBox: Month is invalid!
                    JOptionPane.showMessageDialog(this, "Month is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Day
                if (QueryDataPackage.month == 1 || QueryDataPackage.month == 3 || QueryDataPackage.month == 5 ||
                    QueryDataPackage.month == 7 || QueryDataPackage.month == 8 || QueryDataPackage.month == 10 ||
                    QueryDataPackage.month == 12)
                {
                    if (QueryDataPackage.day > 31 || QueryDataPackage.day < 1)
                    {
                        //MsgBox: Day is invalid!
                        JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if (QueryDataPackage.month == 4 || QueryDataPackage.month == 6 || QueryDataPackage.month == 9 ||
                         QueryDataPackage.month == 11)
                {
                    if (QueryDataPackage.day > 30 || QueryDataPackage.day < 1)
                    {
                        //MsgBox: Day is invalid!
                        JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else //Feb.
                {
                    if(QueryDataPackage.year%400 == 0)
                    {
                        if (QueryDataPackage.day > 29 || QueryDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else if(QueryDataPackage.year%4 == 0)
                    {
                        if (QueryDataPackage.day > 29 || QueryDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else
                    {
                        if (QueryDataPackage.day > 28 || QueryDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }
            catch(NumberFormatException e)
            {
                //MsgBox: Date is invalid!
                JOptionPane.showMessageDialog(this, "Date is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            QueryDataPackage.DatetoSeconds(QueryDataPackage.year, QueryDataPackage.month, QueryDataPackage.day); //Process Date
            //Other Data
            try
            {
                QueryDataPackage.Currency = CurrencyTextField.getText();
                QueryDataPackage.TrackingNumber = TrackingNumberTextField.getText();
                QueryDataPackage.ExpressCompany = ExpressCompanyTextField.getText();
                if ("".equals(ExpressageTextField.getText()))
                {
                    QueryDataPackage.Expressage = 0.0;
                }
                else
                {
                    QueryDataPackage.Expressage = Double.parseDouble(ExpressageTextField.getText());
                }
                QueryDataPackage.Title = TitleTextField.getText();
                if ("".equals(AmountTextField.getText()))
                {
                    QueryDataPackage.Amount = 0;
                }
                else
                {
                    QueryDataPackage.Amount = Long.parseLong(AmountTextField.getText());
                }
                QueryDataPackage.Category = CategoryTextField.getText();
                QueryDataPackage.Producer = ProducerTextField.getText();
                if ("".equals(PriceTextField.getText()))
                {
                    QueryDataPackage.Price = 0.0;
                }
                else
                {
                    QueryDataPackage.Price = Double.parseDouble(PriceTextField.getText());
                }
                if ("".equals(AdditionalFeeTextField.getText()))
                {
                    QueryDataPackage.AdditionalFee = 0.0;
                }
                else
                {
                    QueryDataPackage.AdditionalFee = Double.parseDouble(AdditionalFeeTextField.getText());
                }
                QueryDataPackage.Importance = ImportanceComboBox.getSelectedIndex() + 1;
                QueryDataPackage.Conditions = ConditionsComboBox.getSelectedIndex() + 1;
                QueryDataPackage.Bought = BoughtComboBox.getSelectedIndex();
                QueryDataPackage.URL = URLTextField.getText();
                QueryDataPackage.Info = InfoTextArea.getText();
            }
            catch(NumberFormatException e)
            {
                //MsgBox: Typed Number Values are invalid!
                JOptionPane.showMessageDialog(this, "Typed Number Values are invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Query
            SQL_Process sql_pro = MF.getSQL_Process();
            Query_Package query_package = sql_pro.QueryRecord(QueryDataPackage);
            if (null == query_package)
            {
                //MsgBox: Query failed! Please check your inputs.
                JOptionPane.showMessageDialog(this, "Query failed! Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (0 == query_package.GetAmount()) 
            {
                //MsgBox: No Such Record.
                JOptionPane.showMessageDialog(this, "No Such Record.", "Message", JOptionPane.INFORMATION_MESSAGE);
                return;
            } 
            else 
            {
                MF.setQueryPackage(query_package);
                MF.RefreshTable();
                return;
            }
        }
    }
}

class TabbedPane_QueryWithID extends JPanel implements ActionListener {

    final MainFrame MF;
    final private JTextField IDTextField;

    public TabbedPane_QueryWithID(MainFrame MF) {
        this.MF = MF;
        setLayout(new FlowLayout());
        //Components
        JLabel IDLabel = new JLabel("Query ID: ", JLabel.CENTER);
        IDTextField = new JTextField("", 10);
        JButton QueryButton = new JButton("Query");
        add(IDLabel, CENTER_ALIGNMENT);
        add(IDTextField, CENTER_ALIGNMENT);
        add(QueryButton, CENTER_ALIGNMENT);
        //Listener
        QueryButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Query".equals(ae.getActionCommand())) {
            Long TypedInID;
            try {
                TypedInID = Long.parseLong(IDTextField.getText());
            } catch (NumberFormatException e) {
                //Msgbox: Incorrect ID!
                JOptionPane.showMessageDialog(this, "Incorrect ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Validation
            if (TypedInID.longValue() > 0) {
                //Run Query
                SQL_Process sql_pro = MF.getSQL_Process();
                Query_Package query_package = sql_pro.QueryRecord(TypedInID.longValue());
                if (null == query_package) 
                {
                    //MsgBox: Query failed! Please check your inputs.
                    JOptionPane.showMessageDialog(this, "Query failed! Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                else if (0 == query_package.GetAmount()) 
                {
                    //MsgBox: No Such Record.
                    JOptionPane.showMessageDialog(this, "No Such Record.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else 
                {
                    MF.setQueryPackage(query_package);
                    MF.RefreshTable();
                    return;
                }
            } 
            else 
            {
                //Msgbox: Incorrect ID!
                JOptionPane.showMessageDialog(this, "Incorrect ID!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}

class TabbedPane_QueryWithSQL extends JPanel implements ActionListener {

    final MainFrame MF;
    final private JTextArea SQLTextArea;

    public TabbedPane_QueryWithSQL(MainFrame MF) {
        this.MF = MF;
        setLayout(new FlowLayout());
        //Components
        JLabel SQLLabel = new JLabel("Conditions", JLabel.CENTER);
        SQLTextArea = new JTextArea("", 20, 40);
        JButton QueryButton = new JButton("Query");
        JScrollPane textarea_scrollpane = new JScrollPane(SQLTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(SQLLabel, CENTER_ALIGNMENT);
        add(textarea_scrollpane, CENTER_ALIGNMENT);
        add(QueryButton, CENTER_ALIGNMENT);
        //Listener
        QueryButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Query".equals(ae.getActionCommand())) {
            String sql_conditions = SQLTextArea.getText();
            //Validation
            if ("".equals(sql_conditions)) {
                //Msgbox: Empty Conditions!
                JOptionPane.showMessageDialog(this, "Empty Conditions!", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                //Run Query
                SQL_Process sql_pro = MF.getSQL_Process();
                Query_Package query_package = sql_pro.QueryRecordWithCondition(sql_conditions);
                if (null == query_package) 
                {
                    //MsgBox: Query failed! Please check your inputs.
                    JOptionPane.showMessageDialog(this, "Query failed! Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
                else if (0 == query_package.GetAmount()) 
                {
                    //MsgBox: No Such Record.
                    JOptionPane.showMessageDialog(this, "No Such Record.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else 
                {
                    MF.setQueryPackage(query_package);
                    MF.RefreshTable();
                    return;
                }
            }
        }
    }
}

class QueryFrameAdapter extends WindowAdapter {

    final private JFrame F;

    public QueryFrameAdapter(JFrame F) {
        this.F = F;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        F.setVisible(false);
    }

}
