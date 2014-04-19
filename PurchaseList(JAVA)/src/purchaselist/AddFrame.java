package purchaselist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AddFrame extends JFrame
{
    final MainFrame MF;
    
    public AddFrame(MainFrame MF)
    {
        this.MF = MF;
        Initialize();
    }
    private void Initialize() {
        Container ContentPane = getContentPane();
        ContentPane.setLayout(new BorderLayout());
        //Add Component
        ContentPane.add(new AddNewRecord(MF));
        //Add Listener
        addWindowListener(new AddFrameAdapter(this));
    }
}

class AddNewRecord extends JPanel implements ActionListener
{
    final MainFrame MF;
    private Data_Package AddDataPackage;
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

    public AddNewRecord(MainFrame MF)
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
        JButton AddButton = new JButton("Add");
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
        add(AddButton);
        //Listener
        AddButton.addActionListener(this);
    }
    
    private void ClearAllInputs()
    {
        YearTextField.setText("");
        MonthTextField.setText("");
        DayTextField.setText("");
        CurrencyTextField.setText("");
        TrackingNumberTextField.setText("");
        ExpressCompanyTextField.setText("");
        ExpressageTextField.setText("");
        TitleTextField.setText("");
        CategoryTextField.setText("");
        ProducerTextField.setText("");
        PriceTextField.setText("");
        AdditionalFeeTextField.setText("");
        AmountTextField.setText("");
        ImportanceComboBox.setSelectedIndex(0);
        ConditionsComboBox.setSelectedIndex(0);
        BoughtComboBox.setSelectedIndex(0);
        URLTextField.setText("");
        InfoTextArea.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if ("Add".equals(ae.getActionCommand())) 
        {
            //Check Empty(Date)
            if ("".equals(YearTextField.getText()) || "".equals(MonthTextField.getText()) || "".equals(DayTextField.getText()))
            {
                //MsgBox: Date cannot be empty
                JOptionPane.showMessageDialog(this, "Date cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Validation(Date)
            AddDataPackage = new Data_Package();
            try
            {
                //Parse
                AddDataPackage.year = Integer.parseInt(YearTextField.getText());
                AddDataPackage.month = Integer.parseInt(MonthTextField.getText());
                AddDataPackage.day = Integer.parseInt(DayTextField.getText());
                //Validate
                //Year
                if(AddDataPackage.year < 1970) 
                {
                    //MsgBox: Year cannot be early than 1970!
                    JOptionPane.showMessageDialog(this, "Year cannot be early than 1970!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Month
                if (AddDataPackage.month > 12 || AddDataPackage.month < 1)
                {
                    //MsgBox: Month is invalid!
                    JOptionPane.showMessageDialog(this, "Month is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Day
                if (AddDataPackage.month == 1 || AddDataPackage.month == 3 || AddDataPackage.month == 5 ||
                    AddDataPackage.month == 7 || AddDataPackage.month == 8 || AddDataPackage.month == 10 ||
                    AddDataPackage.month == 12)
                {
                    if (AddDataPackage.day > 31 || AddDataPackage.day < 1)
                    {
                        //MsgBox: Day is invalid!
                        JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if (AddDataPackage.month == 4 || AddDataPackage.month == 6 || AddDataPackage.month == 9 ||
                         AddDataPackage.month == 11)
                {
                    if (AddDataPackage.day > 30 || AddDataPackage.day < 1)
                    {
                        //MsgBox: Day is invalid!
                        JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else //Feb.
                {
                    if(AddDataPackage.year%400 == 0)
                    {
                        if (AddDataPackage.day > 29 || AddDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else if(AddDataPackage.year%4 == 0)
                    {
                        if (AddDataPackage.day > 29 || AddDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else
                    {
                        if (AddDataPackage.day > 28 || AddDataPackage.day < 1)
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
            AddDataPackage.DatetoSeconds(AddDataPackage.year, AddDataPackage.month, AddDataPackage.day); //Process Date
            //Other Data
            try
            {
                AddDataPackage.Currency = CurrencyTextField.getText();
                AddDataPackage.TrackingNumber = TrackingNumberTextField.getText();
                AddDataPackage.ExpressCompany = ExpressCompanyTextField.getText();
                if ("".equals(ExpressageTextField.getText()))
                {
                    AddDataPackage.Expressage = 0.0;
                }
                else
                {
                    AddDataPackage.Expressage = Double.parseDouble(ExpressageTextField.getText());
                }
                AddDataPackage.Title = TitleTextField.getText();
                if ("".equals(AmountTextField.getText()))
                {
                    AddDataPackage.Amount = 0;
                }
                else
                {
                    AddDataPackage.Amount = Long.parseLong(AmountTextField.getText());
                }
                AddDataPackage.Category = CategoryTextField.getText();
                AddDataPackage.Producer = ProducerTextField.getText();
                if ("".equals(PriceTextField.getText()))
                {
                    AddDataPackage.Price = 0.0;
                }
                else
                {
                    AddDataPackage.Price = Double.parseDouble(PriceTextField.getText());
                }
                if ("".equals(AdditionalFeeTextField.getText()))
                {
                    AddDataPackage.AdditionalFee = 0.0;
                }
                else
                {
                    AddDataPackage.AdditionalFee = Double.parseDouble(AdditionalFeeTextField.getText());
                }
                AddDataPackage.Importance = ImportanceComboBox.getSelectedIndex() + 1;
                AddDataPackage.Conditions = ConditionsComboBox.getSelectedIndex() + 1;
                AddDataPackage.Bought = BoughtComboBox.getSelectedIndex();
                AddDataPackage.URL = URLTextField.getText();
                AddDataPackage.Info = InfoTextArea.getText();
            }
            catch(NumberFormatException e)
            {
                //MsgBox: Typed Number Values are invalid!
                JOptionPane.showMessageDialog(this, "Typed Number Values are invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Confirm
            int ConfirmResult;
            ConfirmResult = JOptionPane.showConfirmDialog(this, "Are you sure add this record into database?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (0 == ConfirmResult) //Press Yes
            {
                //Add
                SQL_Process sql_pro = MF.getSQL_Process();
                //Determine the ID for new Record
                AddDataPackage.ID = sql_pro.QueryMaxID();
                /*
                If it is a new table (i.e. no any record), the MaxID is 0
                */
                if (-1 == AddDataPackage.ID) //Error: fail to find MaxID
                {
                    JOptionPane.showMessageDialog(this, "Connection failed! (Part: finding MaxID!)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else
                {
                    boolean AddResult;
                    AddDataPackage.ID = AddDataPackage.ID + 1;
                    AddResult = sql_pro.AddRecord(AddDataPackage);
                    if (true == AddResult) //Success
                    {
                        JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        ClearAllInputs();
                        return;
                    }
                    else //Failed
                    {
                        JOptionPane.showMessageDialog(this, "Connection failed! (Part: adding Record!)", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            else //Other situations
            {
                return;
            }
        }
    }
}


class AddFrameAdapter extends WindowAdapter {

    final private JFrame F;

    public AddFrameAdapter(JFrame F) {
        this.F = F;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        F.setVisible(false);
    }

}
