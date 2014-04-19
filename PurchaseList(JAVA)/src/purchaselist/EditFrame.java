package purchaselist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EditFrame extends JFrame implements ActionListener
{
    final private MainFrame MF;
    final private int EditRowIndex; //May be "long"
    private Data_Package EditDataPackage;
    private long Edit_ID;
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
    
    public EditFrame(MainFrame MF, int EditRowIndex)
    {
        this.MF = MF;
        this.EditRowIndex = EditRowIndex;
        Container ContentPane = getContentPane();
        ContentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        
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
        JButton EditButton = new JButton("Edit");
        //Set all values of old record
        boolean SetAllInputs = SetAllInputs();
        if (false == SetAllInputs) dispose(); //Failed to Set all values of old record
        //Add Components
        ContentPane.add(DateSet);
        ContentPane.add(CurrencySet);
        ContentPane.add(TrackingNumberSet);
        ContentPane.add(ExpressCompanySet);
        ContentPane.add(ExpressageSet);
        ContentPane.add(TitleSet);
        ContentPane.add(CategorySet);
        ContentPane.add(ProducerSet);
        ContentPane.add(PriceSet);
        ContentPane.add(ImportanceSet);
        ContentPane.add(AttributeSet);
        ContentPane.add(URLSet);
        ContentPane.add(InfoLabel);
        ContentPane.add(InfoTextAreaJSP);
        ContentPane.add(EditButton);     
        //Listener
        addWindowListener(new EditFrameAdapter(this));
        EditButton.addActionListener(this);
    }
    private boolean SetAllInputs()
    {
        Edit_ID = MF.getIDFromTable(EditRowIndex); //Get ID of old record.
        if (-1 == Edit_ID)
        {
            JOptionPane.showMessageDialog(this, "No row is selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (-2 == Edit_ID)
        {
            JOptionPane.showMessageDialog(this, "NumberFormatException! (Maybe the selected row is invalid!)", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            //Get data
            SQL_Process sql_pro = MF.getSQL_Process();
            Query_Package Edit_QueryPackage = sql_pro.QueryRecord(Edit_ID);
            Data_Package[] Edit_DataPackage = Edit_QueryPackage.GetData();
            if (null == Edit_DataPackage)
            {
                JOptionPane.showMessageDialog(this, "Query the old record falied!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else
            {
                //Get old record
                Edit_DataPackage[0].SecondstoDate(Edit_DataPackage[0].Seconds);
                //Set all values of old record
                YearTextField.setText(String.valueOf(Edit_DataPackage[0].year));
                MonthTextField.setText(String.valueOf(Edit_DataPackage[0].month));
                DayTextField.setText(String.valueOf(Edit_DataPackage[0].day));
                CurrencyTextField.setText(Edit_DataPackage[0].Currency);
                TrackingNumberTextField.setText(Edit_DataPackage[0].TrackingNumber);
                ExpressCompanyTextField.setText(Edit_DataPackage[0].ExpressCompany);
                ExpressageTextField.setText(String.valueOf(Edit_DataPackage[0].Expressage));
                TitleTextField.setText(Edit_DataPackage[0].Title);
                CategoryTextField.setText(Edit_DataPackage[0].Category);
                ProducerTextField.setText(Edit_DataPackage[0].Producer);
                PriceTextField.setText(String.valueOf(Edit_DataPackage[0].Price));
                AdditionalFeeTextField.setText(String.valueOf(Edit_DataPackage[0].AdditionalFee));
                AmountTextField.setText((String.valueOf(Edit_DataPackage[0].Amount)));
                ImportanceComboBox.setSelectedIndex(Edit_DataPackage[0].Importance - 1);
                ConditionsComboBox.setSelectedIndex(Edit_DataPackage[0].Conditions - 1);
                BoughtComboBox.setSelectedIndex(Edit_DataPackage[0].Bought);
                URLTextField.setText(Edit_DataPackage[0].URL);
                InfoTextArea.setText(Edit_DataPackage[0].Info);
                return true;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if ("Edit".equals(ae.getActionCommand())) 
        {
            //Check Empty(Date)
            if ("".equals(YearTextField.getText()) || "".equals(MonthTextField.getText()) || "".equals(DayTextField.getText()))
            {
                //MsgBox: Date cannot be empty
                JOptionPane.showMessageDialog(this, "Date cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Validation(Date)
            EditDataPackage = new Data_Package();
            try
            {
                //Parse
                EditDataPackage.year = Integer.parseInt(YearTextField.getText());
                EditDataPackage.month = Integer.parseInt(MonthTextField.getText());
                EditDataPackage.day = Integer.parseInt(DayTextField.getText());
                //Validate
                //Year
                if(EditDataPackage.year < 1970) 
                {
                    //MsgBox: Year cannot be early than 1970!
                    JOptionPane.showMessageDialog(this, "Year cannot be early than 1970!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Month
                if (EditDataPackage.month > 12 || EditDataPackage.month < 1)
                {
                    //MsgBox: Month is invalid!
                    JOptionPane.showMessageDialog(this, "Month is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Day
                if (EditDataPackage.month == 1 || EditDataPackage.month == 3 || EditDataPackage.month == 5 ||
                    EditDataPackage.month == 7 || EditDataPackage.month == 8 || EditDataPackage.month == 10 ||
                    EditDataPackage.month == 12)
                {
                    if (EditDataPackage.day > 31 || EditDataPackage.day < 1)
                    {
                        //MsgBox: Day is invalid!
                        JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if (EditDataPackage.month == 4 || EditDataPackage.month == 6 || EditDataPackage.month == 9 ||
                         EditDataPackage.month == 11)
                {
                    if (EditDataPackage.day > 30 || EditDataPackage.day < 1)
                    {
                        //MsgBox: Day is invalid!
                        JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else //Feb.
                {
                    if(EditDataPackage.year%400 == 0)
                    {
                        if (EditDataPackage.day > 29 || EditDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else if(EditDataPackage.year%4 == 0)
                    {
                        if (EditDataPackage.day > 29 || EditDataPackage.day < 1)
                        {
                            //MsgBox: Day is invalid!
                            JOptionPane.showMessageDialog(this, "Day is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else
                    {
                        if (EditDataPackage.day > 28 || EditDataPackage.day < 1)
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
            EditDataPackage.DatetoSeconds(EditDataPackage.year, EditDataPackage.month, EditDataPackage.day); //Process Date
            //Other Data
            try
            {
                EditDataPackage.Currency = CurrencyTextField.getText();
                EditDataPackage.TrackingNumber = TrackingNumberTextField.getText();
                EditDataPackage.ExpressCompany = ExpressCompanyTextField.getText();
                if ("".equals(ExpressageTextField.getText()))
                {
                    EditDataPackage.Expressage = 0.0;
                }
                else
                {
                    EditDataPackage.Expressage = Double.parseDouble(ExpressageTextField.getText());
                }
                EditDataPackage.Title = TitleTextField.getText();
                if ("".equals(AmountTextField.getText()))
                {
                    EditDataPackage.Amount = 0;
                }
                else
                {
                    EditDataPackage.Amount = Long.parseLong(AmountTextField.getText());
                }
                EditDataPackage.Category = CategoryTextField.getText();
                EditDataPackage.Producer = ProducerTextField.getText();
                if ("".equals(PriceTextField.getText()))
                {
                    EditDataPackage.Price = 0.0;
                }
                else
                {
                    EditDataPackage.Price = Double.parseDouble(PriceTextField.getText());
                }
                if ("".equals(AdditionalFeeTextField.getText()))
                {
                    EditDataPackage.AdditionalFee = 0.0;
                }
                else
                {
                    EditDataPackage.AdditionalFee = Double.parseDouble(AdditionalFeeTextField.getText());
                }
                EditDataPackage.Importance = ImportanceComboBox.getSelectedIndex() + 1;
                EditDataPackage.Conditions = ConditionsComboBox.getSelectedIndex() + 1;
                EditDataPackage.Bought = BoughtComboBox.getSelectedIndex();
                EditDataPackage.URL = URLTextField.getText();
                EditDataPackage.Info = InfoTextArea.getText();
            }
            catch(NumberFormatException e)
            {
                //MsgBox: Typed Number Values are invalid!
                JOptionPane.showMessageDialog(this, "Typed Number Values are invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //ID
            EditDataPackage.ID = Edit_ID;
            //Confirm
            int ConfirmResult;
            ConfirmResult = JOptionPane.showConfirmDialog(this, "Are you sure edit this record?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (0 == ConfirmResult) //Press Yes
            {
                //Edit
                SQL_Process sql_pro = MF.getSQL_Process();
                boolean EditResult;
                EditResult = sql_pro.DeleteRecord(Edit_ID);
                if (false == EditResult) //Delete Failed!
                {
                    JOptionPane.showMessageDialog(this, "Connection failed! (Part: deleting old Record!)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else
                {
                    EditResult = sql_pro.AddRecord(EditDataPackage);
                    if (true == EditResult) //Success
                    {
                        JOptionPane.showMessageDialog(this, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose(); //Close this frame
                        return;
                    } else //Failed
                    {
                        JOptionPane.showMessageDialog(this, "Connection failed! (Part: adding edited Record!)", "Error", JOptionPane.ERROR_MESSAGE);
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

class EditFrameAdapter extends WindowAdapter {

    final private JFrame F;

    public EditFrameAdapter(JFrame F) {
        this.F = F;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        F.setVisible(false);
    }
}
